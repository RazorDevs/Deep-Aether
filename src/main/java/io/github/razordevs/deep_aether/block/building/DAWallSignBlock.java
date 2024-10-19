package io.github.razordevs.deep_aether.block.building;

import io.github.razordevs.deep_aether.entity.block.DASignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class DAWallSignBlock extends WallSignBlock {
    public DAWallSignBlock(Properties properties, WoodType woodType) {
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DASignBlockEntity(pos, state);
    }
}
