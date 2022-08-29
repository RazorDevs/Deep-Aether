package teamrazor.deepaether.world.feature;

import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import teamrazor.deepaether.init.DeepAetherModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class DeepAetherModConfiguredFeatures {
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD_TREE =
            FeatureUtils.register("rosewood", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(DeepAetherModBlocks.ROSE_LOG.get()),
                    new StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.simple(DeepAetherModBlocks.ROSE_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).build());
    public static final Holder<PlacedFeature> ROSEWOOD_CHECKED = PlacementUtils.register("rosewood_checked", ROSEWOOD_TREE,
            PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.ROSEWOOD_SAPLING.get()));

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>>    ROSEWOOD_SPAWN =
            FeatureUtils.register("rosewood_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ROSEWOOD_CHECKED,
                            0.5F)), ROSEWOOD_CHECKED));
    public static final List<OreConfiguration.TargetBlockState> AETHER_ASETERITE_STONE = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, DeepAetherModBlocks.ASETERITE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ASETERITE = FeatureUtils.register("aseterite",
            Feature.ORE, new OreConfiguration(AETHER_ASETERITE_STONE, 50));
}