package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import teamrazor.deepaether.init.DABlocks;

import java.util.function.Supplier;

public class LightCapMushroomBlock extends MushroomBlock {
    public LightCapMushroomBlock(Properties properties, Supplier<Holder<? extends ConfiguredFeature<?, ?>>> resourceKey) {
        super(properties, resourceKey);
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

    @Override
    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, boolean b) {
        return false;
    }
}