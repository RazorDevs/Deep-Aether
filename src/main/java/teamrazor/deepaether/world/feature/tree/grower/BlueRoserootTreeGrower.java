package teamrazor.deepaether.world.feature.tree.grower;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.datagen.world.DAConfiguredFeatures;

public class BlueRoserootTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        if(random.nextInt(4) == 0)
            return DAConfiguredFeatures.BLUE_ROSEROOT_TREE_LARGE;
        else return DAConfiguredFeatures.BLUE_ROSEROOT_TREE_SMALL;
    }
}
