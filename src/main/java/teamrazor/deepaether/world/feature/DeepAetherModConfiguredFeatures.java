package teamrazor.deepaether.world.feature;


import com.gildedgames.aether.block.AetherBlockStateProperties;
import com.gildedgames.aether.block.AetherBlocks;
import com.gildedgames.aether.data.resources.AetherFeatureRules;
import com.gildedgames.aether.data.resources.AetherFeatureStates;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
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
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraftforge.registries.ForgeRegistries;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DeepAetherModBlocks;
import teamrazor.deepaether.world.feature.tree.decorators.FlowerBlobFoliagePlacer;
import teamrazor.deepaether.world.feature.tree.decorators.FlowerDecorator;
import teamrazor.deepaether.world.feature.tree.decorators.YagrootVineDecorator;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;



public class DeepAetherModConfiguredFeatures {
    private static List decorators = List.of(new FlowerDecorator(1.0F));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSEROOT_TREE_CONFIGURATION = createKey("roseroot_tree");
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


    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(DeepAetherMod.MODID, name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, ROSEROOT_TREE_CONFIGURATION, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(DeepAetherModFeatureStates.ROSE_LOG),
                        new StraightTrunkPlacer(5, 6, 3),
                        new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(DeepAetherModFeatureStates.ROSE_LEAVES, 2).add(DeepAetherModFeatureStates.FLOWERING_ROSE_LEAVES,1)),
                        new FlowerBlobFoliagePlacer(ConstantInt.of(2), ConstantInt.ZERO, ConstantInt.of(3)),
                        new TwoLayersFeatureSize(1, 0, 2)).decorators(decorators).build());


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


        register(context, ROSEROOT_AND_GOLDEN_OAK_TREES_PLACEMENT, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(GOLDEN_OAK_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(AetherBlocks.GOLDEN_OAK_SAPLING.get())), 0.01F)),
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ROSEROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.ROSEWOOD_SAPLING.get()))));


        register(context, YAGROOT_AND_CRUDEROOT_TREES_PLACEMENT, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(CRUDEROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.CRUDEROOT_SAPLING.get())), 0.04F)),
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(YAGROOT_TREE_CONFIGURATION), PlacementUtils.filteredByBlockSurvival(DeepAetherModBlocks.YAGROOT_SAPLING.get()))));


        register(context, AETHER_MOSS_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(DeepAetherModBlocks.AETHER_MOSS_CARPET.get().defaultBlockState(), 25).add(Blocks.GRASS.defaultBlockState(), 50).add(Blocks.TALL_GRASS.defaultBlockState(), 10))));
        register(context, AETHER_MOSS_PATCH_BONEMEAL, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(DeepAetherModBlocks.AETHER_MOSS_BLOCK.get()),
                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(AETHER_MOSS_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.6F, UniformInt.of(1, 2), 0.75F));


        register(context, ORE_SKYJADE_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.SKYJADE_ORE, 9, 1.0F));
        register(context, ORE_SKYJADE_CONFIGURATION_SMALL, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.SKYJADE_ORE, 4, 0.1F));
        register(context, ORE_DIVINE_DEBRIS_CONFIGURATION, Feature.ORE, new OreConfiguration(AetherFeatureRules.HOLYSTONE, DeepAetherModFeatureStates.DIVINE_DEBRIS, 3, 1.0F));
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
