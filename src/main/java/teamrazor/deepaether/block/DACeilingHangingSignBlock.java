package teamrazor.deepaether.block;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import teamrazor.deepaether.entity.block.DAHangingSignBlockEntity;


public class DACeilingHangingSignBlock extends CeilingHangingSignBlock {
    public DACeilingHangingSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DAHangingSignBlockEntity(pos, state);
    }
}
