package teamrazor.deepaether.block.building;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import teamrazor.deepaether.entity.block.DASignBlockEntity;


public class DASignBlock extends StandingSignBlock {
    public DASignBlock(BlockBehaviour.Properties properties, WoodType woodType) {
        super(woodType, properties);
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DASignBlockEntity(pos, state);
    }
}
