package io.github.razordevs.deep_aether.world.biomes;

import com.aetherteam.aether.data.resources.registries.AetherBiomes;
import io.github.razordevs.aerolith.biome.BiomePlacementHelper;
import io.github.razordevs.deep_aether.DeepAetherConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;


public class DARegion {
    //TODO: Massive Biome rebalancing
    //TODO: Weight parameter reintroduction?

    //TODO: Investigate chunk loading times which are awful somehow
    MultiNoiseBiomeSource
    /**
     * For reference see <a href="https://minecraft.fandom.com/wiki/Biome#Overworld_3">Biomes on Wiki</a>
     */
    public static void addBiomes() {
        Climate.Parameter fullRange = Climate.Parameter.span(-1.0F, 1.0F);
        Climate.Parameter temps1 = Climate.Parameter.span(-1.0F, -0.8F);
        Climate.Parameter temps2 = Climate.Parameter.span(-0.8F, 0.0F);
        Climate.Parameter temps3 = Climate.Parameter.span(0.0F, 0.4F);
        Climate.Parameter temps4 = Climate.Parameter.span(0.4F, 0.93F);
        Climate.Parameter temps5 = Climate.Parameter.span(0.93F, 0.94F);
        Climate.Parameter temps6 = Climate.Parameter.span(0.94F, 1.0F);

        Climate.Parameter tempWoodland = Climate.Parameter.span(-1.5F, -0.8F);
        Climate.Parameter tempYagroot = Climate.Parameter.span(-0.8F, -0.4F);
        Climate.Parameter tempAerglow = Climate.Parameter.span(-0.4F, 0.0F);
        Climate.Parameter tempDefault3 = Climate.Parameter.span(0.0F, 0.4F);
        Climate.Parameter tempDefault4 = Climate.Parameter.span(0.4F, 0.8F);
        Climate.Parameter tempDefault5 = Climate.Parameter.span(0.8F, 1.5F);

        //Parameter mapping: temperature, humidity, continentalness, erosion, depth, weirdness, offset

        addBiome(new Climate.ParameterPoint(temps4, fullRange, fullRange, fullRange, fullRange, fullRange, 0),
                    DABiomes.SACRED_LANDS);

        addBiome(new Climate.ParameterPoint(temps4, temps2, fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.YAGROOT_SWAMP);

        //Aerglow
        addBiome(new Climate.ParameterPoint(temps4, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.BLUE_AERGLOW_FOREST);
        addBiome(new Climate.ParameterPoint(temps4, Climate.Parameter.span(0.0F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERGLOW_FOREST);
        addBiome(new Climate.ParameterPoint(temps4, tempDefault5, fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.MYSTIC_AERGLOW_FOREST);

        // Row 3
        addBiome(new Climate.ParameterPoint(temps4, Climate.Parameter.span(0.0F, 0.8F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERLAVENDER_FIELDS);

        // Row 4
        addBiome(new Climate.ParameterPoint(temps4, Climate.Parameter.span(0.8F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERGLOW_FOREST);
        addBiome(new Climate.ParameterPoint(temps4, Climate.Parameter.span(-1.0F, -0.1F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.GOLDEN_GROVE);
        addBiome(new Climate.ParameterPoint(temps4, Climate.Parameter.span(-0.1F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.GOLDEN_HEIGHTS);
        // Row 5
        addBiome(new Climate.ParameterPoint(temps4, Climate.Parameter.span(-1.0F, -0.6F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.BLUE_AERGLOW_FOREST);


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
        if (DeepAetherConfig.COMMON.disable_aerlavenender_field_biomes.get()){
            replaceBiome(DABiomes.AERLAVENDER_FIELDS, AetherBiomes.SKYROOT_MEADOW);
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
