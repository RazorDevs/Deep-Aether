package io.github.razordevs.deep_aether.block.natural;

import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GoldenGrassPlant extends TallGrassBlock {
    public GoldenGrassPlant(Properties properties) {
        super(properties);
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
