package teamrazor.deepaether.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.init.DABlockEntityTypes;

public class DASignBlockEntity extends SignBlockEntity {
    public DASignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return DABlockEntityTypes.SIGN.get();
    }
}