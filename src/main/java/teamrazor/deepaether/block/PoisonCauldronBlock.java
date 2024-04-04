package teamrazor.deepaether.block;

import com.aetherteam.aether.effect.AetherEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import teamrazor.deepaether.block.behavior.DaCauldronInteraction;


public class PoisonCauldronBlock extends AbstractCauldronBlock {

    @Override
    public MapColor getMapColor(BlockState state, BlockGetter level, BlockPos pos, MapColor defaultColor) {
        return super.getMapColor(state, level, pos, defaultColor);
    }

    public PoisonCauldronBlock(BlockBehaviour.Properties properties) {
        super(properties, DaCauldronInteraction.POISON);
    }

    protected double getContentHeight(BlockState p_153500_) {
        return 0.9375D;
    }

    public boolean isFull(BlockState p_153511_) {
        return true;
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (this.isEntityInsideContent(state, pos, entity)) {
            if (entity instanceof LivingEntity livingEntity)
                livingEntity.addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 250, 0, false, false));
        }

    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return 14;
    }
}
