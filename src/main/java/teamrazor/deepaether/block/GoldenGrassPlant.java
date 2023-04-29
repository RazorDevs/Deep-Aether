package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.init.DABlocks;

public class GoldenGrassPlant extends TallGrassBlock {
    public GoldenGrassPlant(Properties p_57318_) {
        super(p_57318_);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {

        if (state.is(DABlocks.MINI_GOLDEN_GRASS.get()))
            level.setBlockAndUpdate(pos, DABlocks.SHORT_GOLDEN_GRASS.get().defaultBlockState());

        else if (state.is(DABlocks.SHORT_GOLDEN_GRASS.get()))
            level.setBlockAndUpdate(pos, DABlocks.MEDIUM_GOLDEN_GRASS.get().defaultBlockState());

        else if (DABlocks.TALL_GOLDEN_GRASS.get().defaultBlockState().canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
            DoublePlantBlock.placeAt(level, DABlocks.TALL_GOLDEN_GRASS.get().defaultBlockState(), pos, 2);
        }
    }

}
