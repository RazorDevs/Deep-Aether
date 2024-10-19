package io.github.razordevs.deep_aether.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class FlowerBlockLargeHitBox extends FlowerBlock {
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    public FlowerBlockLargeHitBox(Supplier<MobEffect> effectSupplier, int amplifier, Properties properties) {
        super(effectSupplier, amplifier, properties);
    }
    public FlowerBlockLargeHitBox(MobEffect p_53512_, int amplifier, Properties properties) {
        super(p_53512_, amplifier, properties);
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
