package io.github.razordevs.deep_aether.world.biomes;

import io.github.razordevs.aerolith.biome.BiomePlacementHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public class DARareRegion {

    public static void addBiomes() {
        Climate.Parameter fullRange = Climate.Parameter.span(-1.5F, 1.5F);

        Climate.Parameter tRareCold = Climate.Parameter.span(-1.5F, -0.75F);
        Climate.Parameter tRareHot = Climate.Parameter.span(0.6F, 1.5F);

        Climate.Parameter hDry    = Climate.Parameter.span(-1.5F, -0.5F);
        Climate.Parameter hMid    = Climate.Parameter.span(-0.5F, 0.5F);
        Climate.Parameter hWet    = Climate.Parameter.span(0.5F, 1.5F);

        Climate.Parameter depthTop = Climate.Parameter.span(-1.5F, 0.15F);
        Climate.Parameter depthBottom = Climate.Parameter.span(0.15F, 1.5F);


        addBiome(new Climate.ParameterPoint(tRareHot, hDry, fullRange, fullRange, depthTop, fullRange, 0),
                DABiomes.CLOUD); // Placeholder for Overgrown Cloud
        addBiome(new Climate.ParameterPoint(tRareHot, hMid, fullRange, fullRange, depthTop, fullRange, 0),
                DABiomes.CLOUD); // Normal Cloud
        addBiome(new Climate.ParameterPoint(tRareHot, hWet, fullRange, fullRange, depthTop, fullRange, 0),
                DABiomes.CLOUD); // Normal Cloud

        addBiome(new Climate.ParameterPoint(tRareHot, hDry, fullRange, fullRange, depthBottom, fullRange, 0),
                DABiomes.LUMINESCENT_FOREST);
        addBiome(new Climate.ParameterPoint(tRareHot, hMid, fullRange, fullRange, depthBottom, fullRange, 0),
                DABiomes.LUMINESCENT_FOREST);
        addBiome(new Climate.ParameterPoint(tRareHot, hWet, fullRange, fullRange, depthBottom, fullRange, 0),
                DABiomes.LUMINESCENT_FOREST);

        addBiome(new Climate.ParameterPoint(tRareCold, hDry, fullRange, fullRange, depthTop, fullRange, 0),
                DABiomes.CLOUD); // Placeholder for Overgrown Cloud
        addBiome(new Climate.ParameterPoint(tRareCold, hMid, fullRange, fullRange, depthTop, fullRange, 0),
                DABiomes.CLOUD); // Normal Cloud
        addBiome(new Climate.ParameterPoint(tRareCold, hWet, fullRange, fullRange, depthTop, fullRange, 0),
                DABiomes.CLOUD); // Normal Cloud

        addBiome(new Climate.ParameterPoint(tRareCold, hDry, fullRange, fullRange, depthBottom, fullRange, 0),
                DABiomes.LUMINESCENT_FOREST);
        addBiome(new Climate.ParameterPoint(tRareCold, hMid, fullRange, fullRange, depthBottom, fullRange, 0),
                DABiomes.LUMINESCENT_FOREST);
        addBiome(new Climate.ParameterPoint(tRareCold, hWet, fullRange, fullRange, depthBottom, fullRange, 0),
                DABiomes.LUMINESCENT_FOREST);
    }

    private static void addBiome(Climate.ParameterPoint parameters, ResourceKey<Biome> biome) {
        BiomePlacementHelper.addAether(biome, parameters);
    }
}