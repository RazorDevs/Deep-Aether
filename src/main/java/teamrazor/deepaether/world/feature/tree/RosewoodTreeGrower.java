package teamrazor.deepaether.world.feature.tree;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import teamrazor.deepaether.world.feature.DAConfiguredFeatures;

public class RosewoodTreeGrower extends AbstractTreeGrower {
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        if(random.nextInt(4) == 0)
            return (Holder<? extends ConfiguredFeature<?, ?>>) DAConfiguredFeatures.ROSEROOT_TREE_LARGE;
        else return (Holder<? extends ConfiguredFeature<?, ?>>) DAConfiguredFeatures.ROSEROOT_TREE_SMALL;
    }
}
