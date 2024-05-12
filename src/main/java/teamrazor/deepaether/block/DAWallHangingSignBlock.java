package teamrazor.deepaether.block;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import teamrazor.deepaether.entity.block.DAHangingSignBlockEntity;


public class DAWallHangingSignBlock extends WallHangingSignBlock {
    public DAWallHangingSignBlock(Properties properties, WoodType woodType) {
        super(woodType, properties);
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DAHangingSignBlockEntity(pos, state);
    }
}
