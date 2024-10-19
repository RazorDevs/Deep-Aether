package io.github.razordevs.deepaether.deepaether.block.natural;

import io.github.razordevs.deepaether.deepaether.init.DABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class LightCapMushroomBlock extends MushroomBlock {
    public LightCapMushroomBlock(Properties properties, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature) {
        super(configuredFeature, properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = level.getBlockState(blockpos);
        if(blockstate.is(DABlocks.ROTTEN_ROSEROOT_LOG.get())) {
            return blockstate.getValue(RotatedPillarBlock.AXIS).isHorizontal();
        }
        else if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
            return true;
        } else {
            return blockstate.canSustainPlant(level, blockpos, net.minecraft.core.Direction.UP, this);
        }
    }
}