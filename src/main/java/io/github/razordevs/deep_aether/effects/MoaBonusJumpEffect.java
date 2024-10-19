package io.github.razordevs.deep_aether.effects;

import com.aetherteam.aether.entity.passive.Moa;
import io.github.razordevs.deep_aether.entity.MoaBonusJump;
import io.github.razordevs.deep_aether.event.DAGeneralEvents;
import io.github.razordevs.deep_aether.mixin.entity.MoaMixin;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

/**
 *  Removes the effect if the LivingEntity isn't a moa
 *  Sets the function in the interface {@link MoaBonusJump} to the amplifier
 *  The function in the interface gets set back to zero in {@link DAGeneralEvents} if the effect is removed. {@link net.neoforged.neoforge.event.entity.living.MobEffectEvent.Remove}
 *  Lastly the interface {@link  MoaBonusJump} is implemented to {@link Moa} through mixin {@link  MoaMixin}. The amplifier of this effect then gets added to the moa's total max jump
 */
public class MoaBonusJumpEffect extends MobEffect {
    public MoaBonusJumpEffect() {
        super(MobEffectCategory.BENEFICIAL, 5031241);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {

    }
}
