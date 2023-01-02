package teamrazor.deepaether.world.feature;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.data.resources.builders.AetherPlacedFeatureBuilders;
import com.gildedgames.aether.data.resources.registries.AetherConfiguredFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.core.Holder;

import teamrazor.deepaether.DeepAetherMod;



import java.util.List;


public class DeepAetherModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> AERGLOW_FOREST_TREES_PLACEMENT = createKey("aerglow_forest_trees_placement");
    public static final ResourceKey<PlacedFeature> AETHER_PLAINS_TREES = createKey("aether_plains_trees");
    public static final ResourceKey<PlacedFeature> AETHER_PLAINS_FLOWER_PATCH = createKey("aether_plains_flower");
    public static final ResourceKey<PlacedFeature> AERLAVENDER_PATCH = createKey("aerlavender_patch");
    public static final ResourceKey<PlacedFeature> ROSEROOT_FOREST_VEGETATION = createKey("roseroot_forest_vegetation");
    public static final ResourceKey<PlacedFeature> SKYJADE = createKey("skyjade_ore");
    public static final ResourceKey<PlacedFeature> SKYJADE_ORE_SMALL = createKey("skyjade_ore_small");
    public static final ResourceKey<PlacedFeature> DIVINE_DEBRIS = createKey("divine_debris");
    public static final ResourceKey<PlacedFeature> ASETERITE = createKey("aseterite");
    public static final ResourceKey<PlacedFeature> GREOTITE = createKey("greotie");
    public static final ResourceKey<PlacedFeature> CLORITE = createKey("clorite");
    public static final ResourceKey<PlacedFeature> DARKERITE = createKey("darkerite");
    public static final ResourceKey<PlacedFeature> YALLESITE = createKey("yallesite");
    public static final ResourceKey<PlacedFeature> JARINITE = createKey("jarinite");


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(DeepAetherMod.MODID, name));
    }


    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, AERGLOW_FOREST_TREES_PLACEMENT, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.ROSEROOT_AND_GOLDEN_OAK_TREES_PLACEMENT),
                DeepAetherPlacedFeatureBuilder.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

        register(context, AETHER_PLAINS_TREES, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.AETHER_PLAINS_TREES_PLACEMENT),
                DeepAetherPlacedFeatureBuilder.treePlacement(PlacementUtils.countExtra(0, 1F, 1)));

        register(context, AETHER_PLAINS_FLOWER_PATCH, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.AETHER_PLAINS_FLOWER_PATCH_CONFIGURATION), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, AERLAVENDER_PATCH,

                configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.AERLAVENDER_PATCH), NoiseThresholdCountPlacement.of(-0.8D, 5, 9), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());


        register(context, ROSEROOT_FOREST_VEGETATION, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.ROSEROOT_FOREST_VEGETATION), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        register(context, ASETERITE, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.ASETERITE_CONFIGURATION),
                AetherPlacedFeatureBuilders.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
        register(context, GREOTITE, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.GREOTITE_CONFIGURATION),
                AetherPlacedFeatureBuilders.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
        register(context, CLORITE, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.CLORITE_CONFIGURATION),
                AetherPlacedFeatureBuilders.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
        register(context, DARKERITE, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.DARKERITE_CONFIGURATION),
                AetherPlacedFeatureBuilders.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
        register(context, YALLESITE, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.YALLESITE_CONFIGURATION),
                AetherPlacedFeatureBuilders.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
        register(context, JARINITE, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.JARINITE_CONFIGURATION),
                AetherPlacedFeatureBuilders.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
        register(context, SKYJADE, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.ORE_SKYJADE_CONFIGURATION),
                AetherPlacedFeatureBuilders.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
        register(context, SKYJADE_ORE_SMALL, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.ORE_SKYJADE_CONFIGURATION),
                AetherPlacedFeatureBuilders.commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
        register(context, DIVINE_DEBRIS, configuredFeatures.getOrThrow(DeepAetherModConfiguredFeatures.ORE_DIVINE_DEBRIS_CONFIGURATION),
                AetherPlacedFeatureBuilders.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0))));
    }


    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}



