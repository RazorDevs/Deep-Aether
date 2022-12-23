package teamrazor.deepaether.world.feature.tree;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.world.feature.DeepAetherModConfiguredFeatures;

import java.util.Random;

public class RosewoodTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
        return DeepAetherModConfiguredFeatures.ROSEROOT_TREE_CONFIGURATION;
    }
}
