package teamrazor.deepaether.block;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import teamrazor.deepaether.entity.DeepAetherModSignBlockEntity;


public class DeepAetherModSignBlock extends StandingSignBlock {
    public DeepAetherModSignBlock(BlockBehaviour.Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DeepAetherModSignBlockEntity(pos, state);
    }
}
