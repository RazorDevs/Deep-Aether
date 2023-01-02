package teamrazor.deepaether.world.feature;


import com.gildedgames.aether.AetherTags;
import com.gildedgames.aether.block.AetherBlocks;
import com.gildedgames.aether.data.resources.AetherFeatureRules;
import com.gildedgames.aether.data.resources.AetherFeatureStates;
import com.gildedgames.aether.data.resources.builders.AetherConfiguredFeatureBuilders;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DeepAetherModBlocks;
import teamrazor.deepaether.world.feature.tree.decorators.FlowerDecorator;
import teamrazor.deepaether.world.feature.tree.foliage.RoserootFoliagePlacer;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;



public class DeepAetherModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDEN_OAK_TREE_CONFIGURATION = createKey("golden_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?,?>> YAGROOT_TREE_CONFIGURATION = createKey("yagroot_tree");
    public static final ResourceKey<ConfiguredFeature<?,?>> CRUDEROOT_TREE_CONFIGURATION = createKey("cruderoot_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSEROOT_AND_GOLDEN_OAK_TREES_PLACEMENT = createKey("roseroot_and_golden_oak_trees_placement");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YAGROOT_AND_CRUDEROOT_TREES_PLACEMENT = createKey("yagroot_and_cruderoot_trees_placement");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AETHER_MOSS_VEGETATION = createKey("aether_moss_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AETHER_MOSS_PATCH_BONEMEAL = createKey("aether_moss_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SKYJADE_CONFIGURATION = createKey("skyjade_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SKYJADE_CONFIGURATION_SMALL = createKey("skyjade_ore_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIVINE_DEBRIS_CONFIGURATION = createKey("divine_debris");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASETERITE_CONFIGURATION = createKey("aseterite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARKERITE_CONFIGURATION = createKey("darkerite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREOTITE_CONFIGURATION = createKey("greotite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JARINITE_CONFIGURATION = createKey("jarinite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLORITE_CONFIGURATION = createKey("clorite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YALLESITE_CONFIGURATION = createKey("yallesite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AETHER_PLAINS_FLOWER_PATCH_CONFIGURATION = createKey("aether_plains_flower_patch_configuration");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AETHER_PLAINS_TREES_PLACEMENT = createKey("aether_plains_trees_placement");

    public static final ResourceKey<ConfiguredFeature<?, ?>> AERLAVENDER_PATCH = createKey("aerlavender_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SKYROOT_TREE_CONFIGURATION = createKey("skyroot_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSEROOT_TREE_LARGE = createKey("roseroot_tree_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSEROOT_TREE_SMALL = createKey("roseroot_tree_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSEROOT_FOREST_VEGETATION = createKey("roseroot_forest_vegetation");


    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(DeepAetherMod.MODID, name));
    }


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SKYROOT_TREE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LOG),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.simple(AetherFeatureStates.SKYROOT_LEAVES),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());

        register(context, ROSEROOT_TREE_LARGE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(DeepAetherModFeatureStates.ROSE_LOG),
                        new StraightTrunkPlacer(9,10,0),
                        new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(DeepAetherModFeatureStates.ROSE_LEAVES, 2).add(DeepAetherModFeatureStates.FLOWERING_ROSE_LEAVES,1)),
                        new RoserootFoliagePlacer(ConstantInt.of(1), ConstantInt.ZERO, ConstantInt.of(1)),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());

        register(context, ROSEROOT_TREE_SMALL, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(DeepAetherModFeatureStates.ROSE_LOG),
                        new StraightTrunkPlacer(5,2,0),
                        new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(DeepAetherModFeatureStates.ROSE_LEAVES, 2).add(DeepAetherModFeatureStates.FLOWERING_ROSE_LEAVES,1)),
                        new RoserootFoliagePlacer(ConstantInt.of(1), ConstantInt.ZERO, ConstantInt.of(1)),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());

        register(context, GOLDEN_OAK_TREE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(AetherFeatureStates.GOLDEN_OAK_LOG),
                        new FancyTrunkPlacer(9, 5, 0),
                        BlockStateProvider.simple(AetherFeatureStates.GOLDEN_OAK_LEAVES),
                        new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(10))
                ).ignoreVines().build());


        register(context, YAGROOT_TREE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(DeepAetherModFeatureStates.YAGROOT_LOG),
                        new UpwardsBranchingTrunkPlacer(2, 1, 4,UniformInt.of(1, 4), 0.5F, UniformInt.of(0, 1),
                                holdergetter.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)), BlockStateProvider.simple(DeepAetherModFeatureStates.YAGROOT_LEAVES),
                        new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 70), Optional.of(
                                new MangroveRootPlacer(UniformInt.of(1, 3), BlockStateProvider.simple(DeepAetherModFeatureStates.YAGROOT_ROOTS), Optional.of(
                                        new AboveRootPlacement(BlockStateProvider.simple(DeepAetherModFeatureStates.AETHER_MOSS_CARPET), 0.5F)),
                                        new MangroveRootPlacement(holdergetter.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), HolderSet.direct(Block::builtInRegistryHolder, DeepAetherModBlocks.AETHER_MUD.get(), DeepAetherModBlocks.MUDDY_YAGROOT_ROOTS.get()), BlockStateProvider.simple(DeepAetherModFeatureStates.YAGROOT_ROOTS), 8, 15, 0.2F))),
                        new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build());


        register(context, CRUDEROOT_TREE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(DeepAetherModFeatureStates.CRUDEROOT_LOG),
                        new StraightTrunkPlacer(5, 6, 3),
                        BlockStateProvider.simple(DeepAetherModFeatureStates.CRUDEROOT_LEAVES),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                        new TwoLayersFeatureSize(1, 0, 2)).build());


        register(context, AETHER_PLAINS_FLOWER_PATCH_CONFIGURATION, Feature.FLOWER,
                AetherConfiguredFeatureBuilders.grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(AetherFeatureStates.PURPLE_FLOWER, 7)
                        .add(AetherFeatureStates.WHITE_FLOWER, 8)), 96));


        register(context, AERLAVENDER_PATCH, Feature.FLOWER,
                AetherConfiguredFeatureBuilders.grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(DeepAetherModFeatureStates.AERLAVENDER, 64)
                        .add(DeepAetherModFeatureStates.TALL_AERLAVENDER, 32)
                        .add(Blocks.TALL_GRASS.defaultBlockState(), 16)
                        .add(AetherFeatureStates.BERRY_BUSH, 1)
                        .add(Blocks.GRASS.defaultBlockState(), 32)), 418));

        register(context, ROSEROOT_FOREST_VEGETATION, Feature.FLOWER,
                AetherConfiguredFeatureBuilders.grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(DeepAetherModFeatureStates.RADIANT_ORCHID, 6)
                        .add(AetherFeatureStates.PURPLE_FLOWER, 3)
                        .add(AetherFeatureStates.WHITE_FLOWER, 3)
                        .add(Blocks.TALL_GRASS.defaultBlockState(), 32)
                        .add(AetherFeatureStates.BERRY_BUSH, 1)
                        .add(Blocks.GRASS.defaultBlockState(), 64)), 400));

        register(context, ROSEROOT_AND_GOLDEN_OAK_TREES_PLACEMENT, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ROSEROOT_TREE_LARGE), PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.ROSEWOOD_SAPLING.get())), 0.33F)),
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ROSEROOT_TREE_SMALL), PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.ROSEWOOD_SAPLING.get()))));

        register(context, AETHER_PLAINS_TREES_PLACEMENT, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GOLDEN_OAK_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.GOLDEN_OAK_SAPLING.get())), 0.33F)),
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(SKYROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.ROSEWOOD_SAPLING.get()))));



        register(context, YAGROOT_AND_CRUDEROOT_TREES_PLACEMENT, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(CRUDEROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.CRUDEROOT_SAPLING.get())), 0.04F)),
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(YAGROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.YAGROOT_SAPLING.get()))));


        register(context, AETHER_MOSS_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(DeepAetherModBlocks.AETHER_MOSS_CARPET.get().defaultBlockState(), 25).add(Blocks.GRASS.defaultBlockState(), 50).add(Blocks.TALL_GRASS.defaultBlockState(), 10))));
        register(context, AETHER_MOSS_PATCH_BONEMEAL, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(DeepAetherModBlocks.AETHER_MOSS_BLOCK.get()),
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AETHER_MOSS_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.6F, UniformInt.of(1, 2), 0.75F));


        register(context, ORE_SKYJADE_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.SKYJADE_ORE, 6, 0.7F));
        register(context, ORE_SKYJADE_CONFIGURATION_SMALL, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.SKYJADE_ORE, 4, 0.3F));
        register(context, ORE_DIVINE_DEBRIS_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.DIVINE_DEBRIS, 3, 0.9F));
        register(context, ASETERITE_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.ASETERITE, 64));
        register(context, DARKERITE_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.DARKERITE, 64));
        register(context, GREOTITE_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.GREOTITE, 64));
        register(context, JARINITE_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.JARINITE, 64));
        register(context, CLORITE_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.CLORITE, 64));
        register(context, YALLESITE_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.YALLESITE, 64));



    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
