package io.github.razordevs.deep_aether.world.biomes;

import com.aetherteam.aether.data.resources.registries.AetherBiomes;
import io.github.razordevs.aerolith.biome.BiomePlacementHelper;
import io.github.razordevs.deep_aether.DeepAetherConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public class DARegion {

    public static void addBiomes() {
        Climate.Parameter fullRange = Climate.Parameter.span(-1.5F, 1.5F);

        Climate.Parameter tCold   = Climate.Parameter.span(-1.5F, -0.4F);
        Climate.Parameter tCool   = Climate.Parameter.span(-0.4F, -0.1F);
        Climate.Parameter tCenter = Climate.Parameter.span(-0.1F, 0.15F);
        Climate.Parameter tWarm   = Climate.Parameter.span(0.15F, 0.5F);
        Climate.Parameter tHot    = Climate.Parameter.span(0.5F, 1.5F);

        addBiome(new Climate.ParameterPoint(tCold, Climate.Parameter.span(-1.5F, -0.2F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_WOODLAND);
        addBiome(new Climate.ParameterPoint(tCold, Climate.Parameter.span(-0.2F, 0.6F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_MEADOW);
        addBiome(new Climate.ParameterPoint(tCold, Climate.Parameter.span(0.6F, 1.5F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.YAGROOT_SWAMP);

        addBiome(new Climate.ParameterPoint(tCool, Climate.Parameter.span(-1.5F, -0.5F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.BLUE_AERGLOW_FOREST);
        addBiome(new Climate.ParameterPoint(tCool, Climate.Parameter.span(-0.5F, 0.1F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.MYSTIC_AERGLOW_FOREST);
        addBiome(new Climate.ParameterPoint(tCool, Climate.Parameter.span(0.1F, 1.5F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_GROVE);

        addBiome(new Climate.ParameterPoint(tCenter, Climate.Parameter.span(-1.5F, -0.3F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_FOREST);
        addBiome(new Climate.ParameterPoint(tCenter, Climate.Parameter.span(-0.3F, 0.3F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERLAVENDER_FIELDS); // Central, very common, but highly fragmented
        addBiome(new Climate.ParameterPoint(tCenter, Climate.Parameter.span(0.3F, 1.5F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_FOREST);

        addBiome(new Climate.ParameterPoint(tWarm, Climate.Parameter.span(-1.5F, -0.4F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.GOLDEN_GROVE);
        addBiome(new Climate.ParameterPoint(tWarm, Climate.Parameter.span(-0.4F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERGLOW_FOREST);
        addBiome(new Climate.ParameterPoint(tWarm, Climate.Parameter.span(0.0F, 0.5F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERLAVENDER_FIELDS); // Bleeds into the Center row
        addBiome(new Climate.ParameterPoint(tWarm, Climate.Parameter.span(0.5F, 1.5F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_FOREST);

        addBiome(new Climate.ParameterPoint(tHot, Climate.Parameter.span(-1.5F, -0.1F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.GOLDEN_HEIGHTS);
        addBiome(new Climate.ParameterPoint(tHot, Climate.Parameter.span(-0.1F, 0.4F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.GOLDEN_GROVE);
        addBiome(new Climate.ParameterPoint(tHot, Climate.Parameter.span(0.4F, 1.5F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_WOODLAND);


        if (DeepAetherConfig.COMMON.disable_yagroot_swap_biomes.get()){
            replaceBiome(DABiomes.YAGROOT_SWAMP, AetherBiomes.SKYROOT_WOODLAND);
        }
        if (DeepAetherConfig.COMMON.disable_roseroot_forest_biomes.get()){
            replaceBiome(DABiomes.AERGLOW_FOREST, AetherBiomes.SKYROOT_FOREST);
            replaceBiome(DABiomes.BLUE_AERGLOW_FOREST, AetherBiomes.SKYROOT_FOREST);
            replaceBiome(DABiomes.MYSTIC_AERGLOW_FOREST, AetherBiomes.SKYROOT_FOREST);
        }
        if (DeepAetherConfig.COMMON.disable_golden_heights_biomes.get()){
            replaceBiome(DABiomes.GOLDEN_HEIGHTS, AetherBiomes.SKYROOT_GROVE);
            replaceBiome(DABiomes.GOLDEN_GROVE, AetherBiomes.SKYROOT_GROVE);
        }
        if (DeepAetherConfig.COMMON.disable_sacred_lands_biomes.get()){
            replaceBiome(AetherBiomes.SKYROOT_WOODLAND, AetherBiomes.SKYROOT_WOODLAND);
        }
    }

    private static void addBiome(Climate.ParameterPoint parameters, ResourceKey<Biome> biome) {
        BiomePlacementHelper.addAether(biome, parameters);
    }

    private static void replaceBiome(ResourceKey<Biome> biomeToReplace, ResourceKey<Biome> newBiome) {
        BiomePlacementHelper.replaceAether(biomeToReplace, newBiome);
    }
}