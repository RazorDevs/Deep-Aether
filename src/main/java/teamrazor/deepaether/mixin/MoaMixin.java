package teamrazor.deepaether.mixin;

import com.aetherteam.aether.api.AetherMoaTypes;
import com.aetherteam.aether.api.registers.MoaType;
import com.aetherteam.aether.entity.WingedBird;
import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.aether.entity.passive.MountableAnimal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.entity.MoaBonusJump;

@Mixin(value = Moa.class, remap = false)
public abstract class MoaMixin extends MountableAnimal implements WingedBird, MoaBonusJump {

    @Shadow
    public abstract MoaType getMoaType();

    @Unique
    private int deep_Aether$bonusJumps = 0;

    @Override
    public void deep_Aether$setBonusJumps(int jumps) {
        this.deep_Aether$bonusJumps = jumps;
    }

    protected MoaMixin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Inject(at = @At("HEAD"), method = "getMaxJumps", cancellable = true, remap = false)
    private void getMaxJumps(CallbackInfoReturnable<Integer> cir) {
        if(deep_Aether$bonusJumps != 0) {
            MoaType moaType = this.getMoaType();
            int jumps = moaType != null ? moaType.getMaxJumps() : AetherMoaTypes.BLUE.get().getMaxJumps();

            jumps += deep_Aether$bonusJumps;
            cir.setReturnValue(jumps);
        }
    }
}
