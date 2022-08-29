package teamrazor.deepaether.world.feature;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.core.Holder;

public class DeepAetherModPlacedFeatures {
        public static final Holder<PlacedFeature> ROSEWOOD_PLACED = PlacementUtils.register("rosewood_placed",
                DeepAetherModConfiguredFeatures.ROSEWOOD_SPAWN, VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(3, 0.1f, 2)));
        public static final Holder<PlacedFeature> ASETERITE_PLACED = PlacementUtils.register("aseterite_placed",
                DeepAetherModConfiguredFeatures.ASETERITE, DeepAetherModOrePlacement.commonOrePlacement(80, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
}
