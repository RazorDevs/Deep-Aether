package io.github.razordevs.deep_aether.block.building;


import io.github.razordevs.deep_aether.entity.block.DAHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;


public class DAWallHangingSignBlock extends WallHangingSignBlock {
    public DAWallHangingSignBlock(Properties properties, WoodType woodType) {
        super(woodType, properties);
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DAHangingSignBlockEntity(pos, state);
    }
}
