package io.github.razordevs.deep_aether.entity.block;

import io.github.razordevs.deep_aether.init.DABlockEntityTypes;
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