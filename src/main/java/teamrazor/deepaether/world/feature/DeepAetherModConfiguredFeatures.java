package teamrazor.deepaether.world.feature;

import com.gildedgames.aether.AetherTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import teamrazor.deepaether.block.FloweringRoseLeavesBlock;
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
import teamrazor.deepaether.world.feature.tree.decorators.FlowerBlobFoliagePlacer;
import teamrazor.deepaether.world.feature.tree.decorators.FlowerDecorator;

import java.util.List;
import java.util.function.Supplier;

public class DeepAetherModConfiguredFeatures {

    private static List decorators = List.of(new FlowerDecorator(1.0F));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ROSEWOOD_TREE =
            FeatureUtils.register("rosewood", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(DeepAetherModBlocks.ROSE_LOG.get()),
                    new StraightTrunkPlacer(5, 6, 3),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(DeepAetherModBlocks.ROSE_LEAVES.get().defaultBlockState(), 2).add(DeepAetherModBlocks.FLOWERING_ROSE_LEAVES.get().defaultBlockState(),1)),
                    new FlowerBlobFoliagePlacer(ConstantInt.of(2), ConstantInt.ZERO, ConstantInt.of(3)),
                    new TwoLayersFeatureSize(1, 0, 2)).decorators(decorators).build());

    public static final Holder<PlacedFeature> ROSEWOOD_CHECKED = PlacementUtils.register("rosewood_checked", ROSEWOOD_TREE,
            PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.ROSEWOOD_SAPLING.get()));

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ROSEWOOD_SPAWN =
            FeatureUtils.register("rosewood_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ROSEWOOD_CHECKED,
                            0.5F)), ROSEWOOD_CHECKED));


    public static final List<OreConfiguration.TargetBlockState> ASETERITE_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.ASETERITE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> JARINITE_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.JARINITE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> GREOTITE_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.GREOTITE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> SKYJADE_ORES = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.SKYJADE_ORE.get().defaultBlockState()));



    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ASETERITE = FeatureUtils.register("aseterite",
            Feature.ORE, new OreConfiguration(ASETERITE_ORE, 64));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JARINITE = FeatureUtils.register("jarinite",
            Feature.ORE, new OreConfiguration(JARINITE_ORE, 64));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> GREOTITE = FeatureUtils.register("greotite",
            Feature.ORE, new OreConfiguration(GREOTITE_ORE, 64));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> SKYJADE_ORE = FeatureUtils.register("greotite",
            Feature.ORE, new OreConfiguration(SKYJADE_ORES, 64));


}