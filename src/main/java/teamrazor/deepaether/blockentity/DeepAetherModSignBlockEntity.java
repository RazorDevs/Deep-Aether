package teamrazor.deepaether.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.init.DeepAetherModBlockEntityTypes;
public class DeepAetherModSignBlockEntity extends SignBlockEntity {

    public DeepAetherModSignBlockEntity(BlockPos p_155700_, BlockState p_155701_) {
        super(p_155700_, p_155701_);
    }
    @Override
    public BlockEntityType<?> getType() {
        return DeepAetherModBlockEntityTypes.SIGN.get();
    }
}
