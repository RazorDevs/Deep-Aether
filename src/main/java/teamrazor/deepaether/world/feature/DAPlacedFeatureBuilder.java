package teamrazor.deepaether.world.feature;

import com.gildedgames.aether.world.placementmodifier.DungeonBlacklistFilter;
import com.gildedgames.aether.world.placementmodifier.ImprovedLayerPlacementModifier;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class DAPlacedFeatureBuilder {
    public static List<PlacementModifier> treePlacement(PlacementModifier count) {
        return treePlacementBase(count).build();
    }


    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier count) {
        return ImmutableList.<PlacementModifier>builder()
                .add(count)
                .add(ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, UniformInt.of(0, 1), 4))
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(BiomeFilter.biome())
                .add(new DungeonBlacklistFilter());
    }


    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
        return orePlacement(CountPlacement.of(count), heightRange);
    }


    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier heightRange) {
        return List.of(count, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }
}

