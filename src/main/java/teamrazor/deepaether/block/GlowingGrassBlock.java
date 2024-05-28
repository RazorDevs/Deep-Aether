package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class GlowingGrassBlock extends DoublePlantBlock {
    public GlowingGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        if(state.getValue(DoublePlantBlock.HALF).equals(DoubleBlockHalf.UPPER))
            return 5;
        else return 0;
    }
}
