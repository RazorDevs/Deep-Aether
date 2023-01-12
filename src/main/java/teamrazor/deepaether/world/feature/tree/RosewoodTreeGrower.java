package teamrazor.deepaether.world.feature.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.world.feature.DAConfiguredFeatures;

public class RosewoodTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        if(random.nextInt(4) == 0)
            return DAConfiguredFeatures.ROSEROOT_TREE_LARGE;
        else return DAConfiguredFeatures.ROSEROOT_TREE_SMALL;
    }
}
