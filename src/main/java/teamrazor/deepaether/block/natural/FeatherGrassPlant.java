package teamrazor.deepaether.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.init.DABlocks;

public class FeatherGrassPlant extends TallGrassBlock {

    public FeatherGrassPlant(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        if (state.is(DABlocks.FEATHER_GRASS.get()))
            if (DABlocks.TALL_FEATHER_GRASS.get().defaultBlockState().canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
                DoublePlantBlock.placeAt(level, DABlocks.TALL_FEATHER_GRASS.get().defaultBlockState(), pos, 2);
            }
    }
}
