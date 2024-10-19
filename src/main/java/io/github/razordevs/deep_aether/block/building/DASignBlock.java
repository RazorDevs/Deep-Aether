package io.github.razordevs.deep_aether.block.building;


import io.github.razordevs.deep_aether.entity.block.DASignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;


public class DASignBlock extends StandingSignBlock {
    public DASignBlock(BlockBehaviour.Properties properties, WoodType woodType) {
        super(woodType, properties);
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DASignBlockEntity(pos, state);
    }
}
