package teamrazor.deepaether.mixin;

import com.aetherteam.aether.entity.WingedBird;
import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.aether.entity.passive.MountableAnimal;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamrazor.deepaether.entity.MoaBonusJump;
import teamrazor.deepaether.init.DAMobEffects;
import teamrazor.deepaether.networking.MoaEffect;

/**
 * Currently has no full implementation
 * Used to increase the amount of times a moa can jump in the air through and effect.
 * For the effect see {@link teamrazor.deepaether.effects.MoaBonusJumpEffect}
 */
@Mixin(value = Moa.class)
public abstract class MoaMixin extends MountableAnimal implements WingedBird, MoaBonusJump {
    protected MoaMixin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }
    @Unique
    int deep_Aether$amplifier = 0;

    @ModifyReturnValue(at = @At("RETURN"), method = "getMaxJumps", remap = false)
    private int getMaxJumps(int original) {
        if(level().isClientSide) {

            MoaEffect.get((Moa) (Object) this).ifPresent((moa) -> {
                deep_Aether$amplifier = moa.getMoaEffectAmplifier();
            });

            return original + deep_Aether$amplifier;
        }
        else {
            MobEffectInstance effectInstance = this.getEffect(DAMobEffects.MOA_BONUS_JUMPS.get());
            if (effectInstance != null)
                return original + effectInstance.getAmplifier();
            else return original;
        }
    }

    @Inject(at = @At("HEAD"), method = "addPassenger")
    private void addPassenger(Entity passenger, CallbackInfo ci) {
        if(passenger instanceof Player && !this.level().isClientSide) {
            MobEffectInstance instance = this.getEffect(DAMobEffects.MOA_BONUS_JUMPS.get());
            if (instance != null) {
                MoaEffect.get((Moa) (Object) this).ifPresent((moaEffect) -> {
                    moaEffect.setSynched(INBTSynchable.Direction.CLIENT, "setMoaEffectAmplifier", instance.getAmplifier());
                });
            }
        }
    }
}
