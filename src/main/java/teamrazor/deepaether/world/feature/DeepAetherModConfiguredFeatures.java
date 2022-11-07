package teamrazor.deepaether.world.feature;

import com.gildedgames.aether.AetherTags;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import teamrazor.deepaether.init.DeepAetherModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import teamrazor.deepaether.world.feature.tree.decorators.FlowerBlobFoliagePlacer;
import teamrazor.deepaether.world.feature.tree.decorators.FlowerDecorator;
import teamrazor.deepaether.world.feature.tree.decorators.YagrootVineDecorator;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static net.minecraft.data.worldgen.features.CaveFeatures.MOSS_VEGETATION;

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

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YAGROOT_TREE = FeatureUtils.register("yagroot_tree", Feature.TREE,
                (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(DeepAetherModBlocks.YAGROOT_LOG.get()), new UpwardsBranchingTrunkPlacer(2, 1, 4, UniformInt.of(1, 4), 0.5F,
                        UniformInt.of(0, 1), Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)), BlockStateProvider.simple(DeepAetherModBlocks.YAGROOT_LEAVES.get()), new RandomSpreadFoliagePlacer(ConstantInt.of(3),
                        ConstantInt.of(0), ConstantInt.of(2), 70), Optional.of(new MangroveRootPlacer(UniformInt.of(1, 3), BlockStateProvider.simple(DeepAetherModBlocks.YAGROOT_ROOTS.get()),
                        Optional.of(new AboveRootPlacement(BlockStateProvider.simple(DeepAetherModBlocks.AETHER_MOSS_CARPET.get()), 0.5F)), new MangroveRootPlacement(Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), HolderSet.direct(Block::builtInRegistryHolder,
                        Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS), BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS), 8, 15, 0.2F))), new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(new YagrootVineDecorator(0.2F))).build());

    public static final Holder<PlacedFeature> YAGROOT_CHECKED = PlacementUtils.register("yagroot_checked", YAGROOT_TREE,
            PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.YAGROOT_SAPLING.get()));

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> YAGROOT_SPAWN =
            FeatureUtils.register("yagroot_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(YAGROOT_CHECKED,
                            0.5F)), YAGROOT_CHECKED));

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CRUDEROOT_TREE =
            FeatureUtils.register("cruderoot_tree", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(DeepAetherModBlocks.CRUDEROOT_LOG.get()),
                    new StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.simple(DeepAetherModBlocks.CRUDEROOT_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).build());

    public static final Holder<PlacedFeature> CRUDEROOT_CHECKED = PlacementUtils.register("cruderoot_checked", CRUDEROOT_TREE,
            PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.CRUDEROOT_SAPLING.get()));

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CRUDEROOT_SPAWN =
            FeatureUtils.register("cruderoot_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(CRUDEROOT_CHECKED,
                            0.5F)), CRUDEROOT_CHECKED));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> AETHER_MOSS_VEGETATION = FeatureUtils.register("aether_moss_vegetation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(DeepAetherModBlocks.AETHER_MOSS_CARPET.get().defaultBlockState(), 25).add(Blocks.GRASS.defaultBlockState(), 50).add(Blocks.TALL_GRASS.defaultBlockState(), 10))));
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> AETHER_MOSS_PATCH_BONEMEAL = FeatureUtils.register("aether_moss_patch_bonemeal", Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(DeepAetherModBlocks.AETHER_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(AETHER_MOSS_VEGETATION), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.6F, UniformInt.of(1, 2), 0.75F));



    public static final List<OreConfiguration.TargetBlockState> VIRULENT_QUICKSAND_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.AETHER_DIRT), DeepAetherModBlocks.VIRULENT_QUICKSAND.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> AETHER_MOSS_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.AETHER_DIRT), DeepAetherModBlocks.AETHER_MOSS_BLOCK.get().defaultBlockState()));


    public static final List<OreConfiguration.TargetBlockState> ASETERITE_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.ASETERITE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> DARKERITE_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.DARKERITE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> CLORITE_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.CLORITE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> JARINITE_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.JARINITE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> GREOTITE_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.GREOTITE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> YALLESITE_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.YALLESITE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> SKYJADE_ORES = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.SKYJADE_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> CLOUDIUM_DEBRIS_ORE = List.of(
            OreConfiguration.target(new TagMatchTest(AetherTags.Blocks.HOLYSTONE), DeepAetherModBlocks.CLOUDIUM_DEBRIS.get().defaultBlockState()));



    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> AETHER_MOSS = FeatureUtils.register("aether_moss",
            Feature.ORE, new OreConfiguration(AETHER_MOSS_ORE, 32));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> VIRULENT_QUICKSAND = FeatureUtils.register("virulent_quicksand",
            Feature.ORE, new OreConfiguration(VIRULENT_QUICKSAND_ORE, 32));


    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ASETERITE = FeatureUtils.register("aseterite",
            Feature.ORE, new OreConfiguration(ASETERITE_ORE, 64));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JARINITE = FeatureUtils.register("jarinite",
            Feature.ORE, new OreConfiguration(JARINITE_ORE, 64));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> GREOTITE = FeatureUtils.register("greotite",
            Feature.ORE, new OreConfiguration(GREOTITE_ORE, 64));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> DARKERITE = FeatureUtils.register("darkerite",
            Feature.ORE, new OreConfiguration(DARKERITE_ORE, 64));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CLORITE = FeatureUtils.register("clorite",
            Feature.ORE, new OreConfiguration(CLORITE_ORE, 64));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> YALLESITE = FeatureUtils.register("yallesite",
            Feature.ORE, new OreConfiguration(YALLESITE_ORE, 64));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CLOUDIUM_DEBRIS = FeatureUtils.register("cloudium_debris",
            Feature.ORE, new OreConfiguration(CLOUDIUM_DEBRIS_ORE, 3, 1.0F));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> SKYJADE_ORE = FeatureUtils.register("skyjade_ore",
            Feature.ORE, new OreConfiguration(SKYJADE_ORES, 9, 1.0F));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> SKYJADE_ORE_SMALL = FeatureUtils.register("skyjade_ore_small",
            Feature.ORE, new OreConfiguration(SKYJADE_ORES, 4, 0.1F));


}