package teamrazor.deepaether.block.natural;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HalfTransperentHugeMushroomBlock extends HugeMushroomBlock {

    public HalfTransperentHugeMushroomBlock(Properties p_54136_) {
        super(p_54136_);
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.is(this) || super.skipRendering(pState, pAdjacentBlockState, pSide);
    }
}
