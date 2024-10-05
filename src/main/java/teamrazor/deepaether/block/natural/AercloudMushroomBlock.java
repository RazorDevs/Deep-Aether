package teamrazor.deepaether.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import teamrazor.deepaether.init.DABlocks;

public class AercloudMushroomBlock extends MushroomBlock {
    public AercloudMushroomBlock(ResourceKey<ConfiguredFeature<?, ?>> configuredFeature, Properties properties) {
        super(configuredFeature, properties);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        return blockstate.is(DABlocks.AERCLOUD_GRASS_BLOCK);
    }
}
