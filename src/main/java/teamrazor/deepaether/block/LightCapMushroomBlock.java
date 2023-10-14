package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import teamrazor.deepaether.init.DABlocks;

import java.util.function.Supplier;

public class LightCapMushroomBlock extends MushroomBlock {

    public LightCapMushroomBlock(Properties properties, Supplier<Holder<? extends ConfiguredFeature<?, ?>>> supplier) {
        super(properties, supplier);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if(state.is(DABlocks.ROSEROOT_LOG.get())) {
            return true;
        }
        return super.canSurvive(state, level, pos);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, boolean b) {
        return false;
    }
}