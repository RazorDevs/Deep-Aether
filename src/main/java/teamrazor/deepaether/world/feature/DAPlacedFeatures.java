package teamrazor.deepaether.world.feature;

import com.aetherteam.aether.world.placementmodifier.DungeonBlacklistFilter;
import com.aetherteam.aether.world.placementmodifier.ImprovedLayerPlacementModifier;
import com.aetherteam.nitrogen.data.resources.builders.NitrogenPlacedFeatureBuilders;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.world.placementmodifier.ImprovedRarityFilter;

import java.util.List;


public class DAPlacedFeatures {

    public static final ResourceKey<PlacedFeature> POISON_LAKE_PLACEMENT = createKey("poison_lake");
    public static final ResourceKey<PlacedFeature> POISON_SPRING_PLACEMENT = createKey("poison_spring");
    public static final ResourceKey<PlacedFeature> AERGLOW_FOREST_TREES_PLACEMENT = createKey("aerglow_forest_trees_placement");
    public static final ResourceKey<PlacedFeature> FALLEN_AERGLOW_FOREST = createKey("fallen_aerglow_forest");

    public static final ResourceKey<PlacedFeature> EMPTY_FALLEN_AERGLOW_FOREST = createKey("empty_fallen_aerglow_forest");

    public static final ResourceKey<PlacedFeature> YAGROOT_SWAMP_TREES_PLACEMENT = createKey("yagroot_swamp_trees_placement");

    public static final ResourceKey<PlacedFeature> BLUE_AERGLOW_FOREST_TREES_PLACEMENT = createKey("blue_aerglow_forest_trees_placement");
    public static final ResourceKey<PlacedFeature> MYSTIC_AERGLOW_FOREST_TREES_PLACEMENT = createKey("mystic_aerglow_forest_trees_placement");
    public static final ResourceKey<PlacedFeature> AERLAVENDER_PATCH = createKey("aerlavender_patch");
    public static final ResourceKey<PlacedFeature> AETHER_CATTAIL_PATCH = createKey("aether_cattail_patch");
    public static final ResourceKey<PlacedFeature> AERGLOW_FOREST_FLOWERS = createKey("aerglow_forest_flowers");
    public static final ResourceKey<PlacedFeature> MYSTIC_AERGLOW_FOREST_FLOWERS = createKey("mystic_aerglow_forest_flowers");

    public static final ResourceKey<PlacedFeature> SKY_TULIPS = createKey("sky_tulips");
    public static final ResourceKey<PlacedFeature> GOLDEN_ASPESS = createKey("golden_aspess");
    public static final ResourceKey<PlacedFeature> ECHAISY = createKey("echaisy");

    public static final ResourceKey<PlacedFeature> AERGLOW_FOREST_GRASS = createKey("aerglow_forest_grass");

    public static final ResourceKey<PlacedFeature> SKYJADE = createKey("skyjade_ore");
    public static final ResourceKey<PlacedFeature> ASETERITE = createKey("aseterite");
    public static final ResourceKey<PlacedFeature> CLORITE = createKey("clorite");
    public static final ResourceKey<PlacedFeature> AETHER_MOSS_PATCHES = createKey("aether_moss");
    public static final ResourceKey<PlacedFeature> GOLDEN_GRASS_PATCH = createKey("golden_grass_patch");
    public static final ResourceKey<PlacedFeature> GOLDEN_VINES_PATCH = createKey("golden_vines_patch");
    public static final ResourceKey<PlacedFeature>  GOLDEN_GRASS_BONEMEAL = createKey("golden_grass_bonemeal");
    public static final ResourceKey<PlacedFeature>  VIRULENT_QUICKSAND_PATCH = createKey("virulent_quicksand_patch");
    public static final ResourceKey<PlacedFeature>  GOLDEN_HEIGHTS_FLOWERS = createKey("golden_heights_flowers");
    public static final ResourceKey<PlacedFeature> GOLDEN_GROVE_TREES = createKey("golden_grove_trees");
    public static final ResourceKey<PlacedFeature> STERLING_AERCLOUD_PLACEMENT = createKey("sterling_aercloud");
    public static final ResourceKey<PlacedFeature> AETHER_COARSE_DIRT_PATCH = createKey("aether_coarse_dirt_patch");
    public static final ResourceKey<PlacedFeature> GOLDEN_GROVE_GRASS_PATCH = createKey("golden_grove_grass_patch");

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(DeepAether.MODID, name));
    }


    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, POISON_LAKE_PLACEMENT, configuredFeatures.getOrThrow(DAConfiguredFeatures.POISON_LAKE_CONFIGURATION),
                CountPlacement.of(20),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                InSquarePlacement.spread(),
                BiomeFilter.biome());

        register(context, POISON_SPRING_PLACEMENT, configuredFeatures.getOrThrow(DAConfiguredFeatures.POISON_SPRING_CONFIGURATION),
                CountPlacement.of(100),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(8), VerticalAnchor.aboveBottom(128)),
                BiomeFilter.biome(),
                new DungeonBlacklistFilter());

        register(context, FALLEN_AERGLOW_FOREST, configuredFeatures.getOrThrow(DAConfiguredFeatures.FALLEN_AERGLOW_TREE),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                new DungeonBlacklistFilter());

        register(context, EMPTY_FALLEN_AERGLOW_FOREST, configuredFeatures.getOrThrow(DAConfiguredFeatures.EMPTY_FALLEN_AERGLOW_TREE),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                new DungeonBlacklistFilter());

        register(context, AERGLOW_FOREST_TREES_PLACEMENT, configuredFeatures.getOrThrow(DAConfiguredFeatures.ROSEROOT_AND_BLUE_ROSEROOT_TREES_PLACEMENT),
                DAPlacedFeatureBuilder.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

        register(context, GOLDEN_GROVE_TREES,  configuredFeatures.getOrThrow(DAConfiguredFeatures.SUNROOT_AND_CONBERRY_TREES_PLACEMENT),
                DAPlacedFeatureBuilder.treePlacement(PlacementUtils.countExtra(30, 0.1F, 1)));

        register(context, YAGROOT_SWAMP_TREES_PLACEMENT, configuredFeatures.getOrThrow(DAConfiguredFeatures.YAGROOT_AND_CRUDEROOT_TREES_PLACEMENT),
                DAPlacedFeatureBuilder.treePlacement(PlacementUtils.countExtra(20, 0.1F, 2)));

        register(context, AETHER_MOSS_PATCHES, configuredFeatures.getOrThrow(DAConfiguredFeatures.AETHER_MOSS_PATCH_BONEMEAL),
                CountPlacement.of(5),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                new DungeonBlacklistFilter());

        register(context, AETHER_COARSE_DIRT_PATCH, configuredFeatures.getOrThrow(DAConfiguredFeatures.AETHER_COARSE_DIRT_PATCH),
                CountPlacement.of(1),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                new DungeonBlacklistFilter());

        register(context, GOLDEN_VINES_PATCH, configuredFeatures.getOrThrow(DAConfiguredFeatures.GOLDEN_VINES_PATCH),
                CountPlacement.of(9),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                new DungeonBlacklistFilter());


        register(context, VIRULENT_QUICKSAND_PATCH, configuredFeatures.getOrThrow(DAConfiguredFeatures.VIRULENT_QUICKSAND_PATCH),
                RarityFilter.onAverageOnceEvery(10),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                new DungeonBlacklistFilter());


        register(context, GOLDEN_GRASS_BONEMEAL, configuredFeatures.getOrThrow(DAConfiguredFeatures.GOLDEN_GRASS_BLOCK_BONEMEAL_PATCH), PlacementUtils.isEmpty());
        register(context, BLUE_AERGLOW_FOREST_TREES_PLACEMENT, configuredFeatures.getOrThrow(DAConfiguredFeatures.BLUE_ROSEROOT_TREES_PLACEMENT),
                DAPlacedFeatureBuilder.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

        register(context, GOLDEN_HEIGHTS_FLOWERS, configuredFeatures.getOrThrow(DAConfiguredFeatures.GOLDEN_HEIGHTS_FLOWERS), RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());


        register(context, MYSTIC_AERGLOW_FOREST_TREES_PLACEMENT, configuredFeatures.getOrThrow(DAConfiguredFeatures.ROSEROOT_TREES_PLACEMENT),
                DAPlacedFeatureBuilder.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

        register(context, AERLAVENDER_PATCH, configuredFeatures.getOrThrow(DAConfiguredFeatures.AERLAVENDER_PATCH), NoiseThresholdCountPlacement.of(-0.8D, 5, 9), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

        register(context, AETHER_CATTAIL_PATCH, configuredFeatures.getOrThrow(DAConfiguredFeatures.AETHER_CATTAILS_PATCH), CountPlacement.of(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        register(context, AERGLOW_FOREST_FLOWERS, configuredFeatures.getOrThrow(DAConfiguredFeatures.ROSEROOT_FOREST_FLOWERS), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, MYSTIC_AERGLOW_FOREST_FLOWERS, configuredFeatures.getOrThrow(DAConfiguredFeatures.MYSTIC_ROSEROOT_FOREST_FLOWERS), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        register(context, SKY_TULIPS, configuredFeatures.getOrThrow(DAConfiguredFeatures.SKY_TULIPS),
                RarityFilter.onAverageOnceEvery(16),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4), BiomeFilter.biome());

        register(context, GOLDEN_ASPESS, configuredFeatures.getOrThrow(DAConfiguredFeatures.GOLDEN_ASPESS),
                RarityFilter.onAverageOnceEvery(16),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4), BiomeFilter.biome());

        register(context, ECHAISY, configuredFeatures.getOrThrow(DAConfiguredFeatures.ECHAISY),
                RarityFilter.onAverageOnceEvery(16),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4), BiomeFilter.biome());


        register(context, AERGLOW_FOREST_GRASS, configuredFeatures.getOrThrow(DAConfiguredFeatures.ROSEROOT_FOREST_GRASS), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        register(context, ASETERITE, configuredFeatures.getOrThrow(DAConfiguredFeatures.ASETERITE_CONFIGURATION),
                NitrogenPlacedFeatureBuilders.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));

        register(context, CLORITE, configuredFeatures.getOrThrow(DAConfiguredFeatures.CLORITE_CONFIGURATION),
                NitrogenPlacedFeatureBuilders.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
        register(context, SKYJADE, configuredFeatures.getOrThrow(DAConfiguredFeatures.ORE_SKYJADE_CONFIGURATION),
                NitrogenPlacedFeatureBuilders.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(60))));
        register(context, GOLDEN_GRASS_PATCH, configuredFeatures.getOrThrow(DAConfiguredFeatures.GOLDEN_GRASS_PATCH), NoiseThresholdCountPlacement.of(-0.8D, 5, 9), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

        register(context, GOLDEN_GROVE_GRASS_PATCH, configuredFeatures.getOrThrow(DAConfiguredFeatures.GOLDEN_GROVE_GRASS_PATCH), NoiseThresholdCountPlacement.of(-0.8D, 5, 9), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

        register(context, STERLING_AERCLOUD_PLACEMENT, configuredFeatures.getOrThrow(DAConfiguredFeatures.STERLING_AERCLOUD_CONFIGURATION),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(200), VerticalAnchor.absolute(255)),
                ImprovedRarityFilter.onAverageOnceEvery(0.0005F),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                new DungeonBlacklistFilter());
    }


    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}



