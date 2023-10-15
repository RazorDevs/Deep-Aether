package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import teamrazor.deepaether.init.DABlocks;

public class LightCapMushroomBlock extends MushroomBlock {
    public LightCapMushroomBlock(Properties p_256027_, ResourceKey<ConfiguredFeature<?, ?>> p_256049_) {
        super(p_256027_, p_256049_);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if(state.is(DABlocks.ROSEROOT_LOG.get())) {
            return true;
        }
        return super.canSurvive(state, level, pos);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_255904_, BlockPos p_54871_, BlockState p_54872_, boolean p_54873_) {
        return false;
    }
}