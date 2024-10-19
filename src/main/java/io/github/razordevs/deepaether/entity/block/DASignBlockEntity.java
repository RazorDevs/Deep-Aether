package io.github.razordevs.deepaether.deepaether.entity.block;

import io.github.razordevs.deepaether.deepaether.init.DABlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DASignBlockEntity extends SignBlockEntity {
    public DASignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return DABlockEntityTypes.SIGN.get();
    }
}