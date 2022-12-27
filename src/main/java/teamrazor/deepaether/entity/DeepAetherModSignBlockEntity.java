package teamrazor.deepaether.entity;

import com.gildedgames.aether.blockentity.AetherBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.init.DeepAetherModBlockEntityTypes;

public class DeepAetherModSignBlockEntity extends SignBlockEntity {
    public DeepAetherModSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return DeepAetherModBlockEntityTypes.SIGN.get();
    }
}