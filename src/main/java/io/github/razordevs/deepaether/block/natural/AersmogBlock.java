package io.github.razordevs.deepaether.deepaether.block.natural;

import com.aetherteam.aether.block.natural.AercloudBlock;
import com.aetherteam.aether.effect.AetherEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AersmogBlock extends AercloudBlock {
    public AersmogBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 250, 0, false, false));
        }
        super.entityInside(state, level, pos, entity);
    }
}
