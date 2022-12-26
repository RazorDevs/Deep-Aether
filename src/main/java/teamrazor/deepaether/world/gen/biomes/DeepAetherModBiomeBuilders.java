package teamrazor.deepaether.world.gen.biomes;
/*
import com.gildedgames.aether.Aether;
import com.gildedgames.aether.client.AetherSoundEvents;


import com.gildedgames.aether.data.resources.builders.AetherBiomeBuilders;
import com.gildedgames.aether.entity.AetherEntityTypes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import teamrazor.deepaether.world.feature.DeepAetherModConfiguredFeatures;
import teamrazor.deepaether.world.feature.DeepAetherModPlacedFeatures;
import teamrazor.deepaether.world.feature.DeepAetherModPlacedFeatures;


import java.util.List;




public class DeepAetherModBiomeBuilders {


    public static Biome aetherPlainsBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        return makeDefaultBiome(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                //.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DeepAetherModPlacedFeatures.AETHER_PLAINS_FLOWER_PATCH)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DeepAetherModPlacedFeatures.AERLAVENDER_PATCH)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DeepAetherModPlacedFeatures.AETHER_PLAINS_TREES)
        );
    }

    public static Biome makeDefaultBiome(BiomeGenerationSettings.Builder builder) {
        return fullDefinition(
                Biome.Precipitation.NONE,
                0.8F,
                0.0F,
                new BiomeSpecialEffects.Builder()
                        .fogColor(0x93_93_bc)
                        .skyColor(0xc0_c0_ff)
                        .waterColor(0x3f_76_e4)
                        .waterFogColor(0x05_05_33)
                        .grassColorOverride(0xb1_ff_cb)
                        .foliageColorOverride(0xb1_ff_cb)
                        .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
                        .backgroundMusic(new Music(AetherSoundEvents.MUSIC_AETHER.getHolder().orElseThrow(), 12000, 24000, true))
                        .build(),
                new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(AetherEntityTypes.WHIRLWIND.get(), 9, 2, 2))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(AetherEntityTypes.EVIL_WHIRLWIND.get(), 1, 2, 2))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(AetherEntityTypes.COCKATRICE.get(), 100, 4, 4))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(AetherEntityTypes.ZEPHYR.get(), 50, 1, 1))
                        .addMobCharge(AetherEntityTypes.ZEPHYR.get(), 0.7, 0.15)
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AetherEntityTypes.BLUE_SWET.get(), 15, 3, 4))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AetherEntityTypes.GOLDEN_SWET.get(), 15, 3, 4))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AetherEntityTypes.AECHOR_PLANT.get(), 29, 1, 1))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AetherEntityTypes.PHYG.get(), 12, 4, 4))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AetherEntityTypes.AERBUNNY.get(), 11, 3, 3))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AetherEntityTypes.SHEEPUFF.get(), 10, 4, 4))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AetherEntityTypes.MOA.get(), 10, 3, 3))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AetherEntityTypes.FLYING_COW.get(), 10, 4, 4))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(AetherEntityTypes.AERWHALE.get(), 2, 1, 1))
                        .build(),
                builder
                        .addFeature(GenerationStep.Decoration.RAW_GENERATION,  DeepAetherModPlacedFeatures.A)
                        /*.addFeature(GenerationStep.Decoration.LAKES, DeepAetherModPlacedFeatures.WATER_LAKE_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DeepAetherModPlacedFeatures.ORE_AETHER_DIRT_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DeepAetherModPlacedFeatures.ORE_ICESTONE_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DeepAetherModPlacedFeatures.ORE_AMBROSIUM_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DeepAetherModPlacedFeatures.ORE_ZANITE_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DeepAetherModPlacedFeatures.ORE_GRAVITITE_COMMON_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DeepAetherModPlacedFeatures.ORE_GRAVITITE_DENSE_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DeepAetherModPlacedFeatures.WATER_SPRING_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DeepAetherModPlacedFeatures.GRASS_PATCH_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DeepAetherModPlacedFeatures.TALL_GRASS_PATCH_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DeepAetherModPlacedFeatures.HOLIDAY_TREE_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DeepAetherModPlacedFeatures.FLOWER_PATCH_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, DeepAetherModPlacedFeatures.CRYSTAL_ISLAND_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, DeepAetherModPlacedFeatures.COLD_AERCLOUD_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, DeepAetherModPlacedFeatures.BLUE_AERCLOUD_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, DeepAetherModPlacedFeatures.GOLDEN_AERCLOUD_PLACEMENT)
                        .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, DeepAetherModPlacedFeatures.PINK_AERCLOUD_PLACEMENT)
                        .build(),
                Biome.TemperatureModifier.NONE
        );
    }

    public static Biome fullDefinition(Biome.Precipitation precipitation, float temperature, float downfall, BiomeSpecialEffects effects, MobSpawnSettings spawnSettings, BiomeGenerationSettings generationSettings, Biome.TemperatureModifier temperatureModifier) {
        return new Biome.BiomeBuilder()
                .precipitation(precipitation)
                .temperature(temperature)
                .downfall(downfall)
                .specialEffects(effects)
                .mobSpawnSettings(spawnSettings)
                .generationSettings(generationSettings)
                .temperatureAdjustment(temperatureModifier)
                .build();
    }


}


*/