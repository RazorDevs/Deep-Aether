package io.github.razordevs.deepaether.deepaether.entity.block;

import io.github.razordevs.deepaether.deepaether.init.DABlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DAHangingSignBlockEntity extends HangingSignBlockEntity {
    public DAHangingSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<DAHangingSignBlockEntity> getType() {
        return DABlockEntityTypes.HANGING_SIGN.get();
    }
}