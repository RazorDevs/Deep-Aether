package teamrazor.deepaether.world.biomes;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.data.resources.builders.AetherBiomeBuilders;
import com.gildedgames.aether.data.resources.registries.AetherBiomes;
import com.google.common.base.Suppliers;
import com.ibm.icu.util.Region;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.core.jmx.Server;
import teamrazor.deepaether.DeepAetherMod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DABiomes {
    public static final ResourceKey<Biome> AETHER_PLAINS = createKey("aether_plains");
    public static final ResourceKey<Biome> AERGLOW_FOREST = createKey("aerglow_forest");

    public static final ResourceKey<Biome> BLUE_AERGLOW_FOREST = createKey("blue_aerglow_forest");

    public static final ResourceKey<Biome> RARE_AERGLOW_FOREST = createKey("rare_aerglow_forest");

    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(DeepAetherMod.MODID, name));
    }
}

/*
    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {

        MinecraftServer server = event.getServer();

        RegistryAccess registryAccess = server.registryAccess();
        Registry<LevelStem> levelStemRegistry = registryAccess.registryOrThrow(Registries.LEVEL_STEM);
        Registry<Biome> biomes = server.registryAccess().registryOrThrow(Registries.BIOME);

        LevelStem stem = levelStemRegistry.get(new ResourceLocation("aether", "the_aether"));

        ChunkGenerator chunkGenerator = stem.generator();

        HolderGetter<Biome> biomesgetter = biomes.asLookup();


        Climate.Parameter fullRange = Climate.Parameter.span(-1.0F, 1.0F);
        Climate.Parameter temps1 = Climate.Parameter.span(-1.0F, -0.8F);
        Climate.Parameter temps2 = Climate.Parameter.span(-0.8F, 0.0F);
        Climate.Parameter temps3 = Climate.Parameter.span(0.0F, 0.4F);
        Climate.Parameter temps4 = Climate.Parameter.span(0.4F, 0.93F);
        Climate.Parameter temps5 = Climate.Parameter.span(0.93F, 0.94F);
        Climate.Parameter temps6 = Climate.Parameter.span(0.94F, 1.0F);

        if (chunkGenerator.getBiomeSource() instanceof MultiNoiseBiomeSource noiseSource) {


            //Row 1 (replaced with deep aether biomes)
            List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameters = new ArrayList<>(noiseSource.parameters.values());

            parameters.add(new Pair<>(new Climate.ParameterPoint(temps1, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 1),
                    biomes.getHolderOrThrow(DABiomes.AETHER_PLAINS)));

            parameters.add(new Pair<>(new Climate.ParameterPoint(temps1, Climate.Parameter.span(0.0F, 1.0F), fullRange, fullRange, fullRange, fullRange, 1),
                    biomes.getHolderOrThrow(DABiomes.ROSEROOT_FOREST)));

            //Row 2
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps2, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(AetherBiomes.SKYROOT_MEADOW)));
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps2, Climate.Parameter.span(0.0F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(AetherBiomes.SKYROOT_FOREST)));

            // Row 3 (replaced with deep aether biomes)
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps3, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(DABiomes.ROSEROOT_FOREST)));
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps3, Climate.Parameter.span(0.0F, 0.8F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(DABiomes.AETHER_PLAINS)));
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps3, Climate.Parameter.span(0.8F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(DABiomes.AETHER_PLAINS)));

            // Row 4
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps4, Climate.Parameter.span(-1.0F, -0.1F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(AetherBiomes.SKYROOT_GROVE)));
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps4, Climate.Parameter.span(-0.1F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(AetherBiomes.SKYROOT_FOREST)));

            // Row 5
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps5, Climate.Parameter.span(-1.0F, -0.6F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(AetherBiomes.SKYROOT_MEADOW)));
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps5, Climate.Parameter.span(-0.6F, -0.3F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(AetherBiomes.SKYROOT_GROVE)));
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps5, Climate.Parameter.span(-0.3F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(AetherBiomes.SKYROOT_FOREST)));

            // Row 6
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps6, Climate.Parameter.span(-1.0F, -0.1F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(AetherBiomes.SKYROOT_MEADOW)));
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps6, Climate.Parameter.span(-0.1F, 0.8F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(AetherBiomes.SKYROOT_WOODLAND)));
            parameters.add(new Pair<>(new Climate.ParameterPoint(temps5, Climate.Parameter.span(0.8F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                    biomes.getHolderOrThrow(AetherBiomes.SKYROOT_FOREST)));

             //noiseSource.parameters.values().addAll(parameters);
            //noiseSource.parameters.values().set(1, Pair.of(new Climate.ParameterPoint(temps2, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0),
             //       biomes.getHolderOrThrow(AetherBiomes.SKYROOT_FOREST)));
            //removes all climate parameters from the aether
            //noiseSource.parameters.values().remove(AetherBiomeBuilders.buildAetherBiomeSource(biomes.asLookup()));
            //((MultiNoiseBiomeSource) chunkGenerator.getBiomeSource()).parameters.values().remove(biomes.getHolderOrThrow(AetherBiomes.SKYROOT_GROVE));
            //((MultiNoiseBiomeSource) chunkGenerator.getBiomeSource()).parameters.values().remove(biomes.getHolderOrThrow(AetherBiomes.SKYROOT_MEADOW));
            //((MultiNoiseBiomeSource) chunkGenerator.getBiomeSource()).parameters.values().remove(biomes.getHolderOrThrow(AetherBiomes.SKYROOT_WOODLAND));

            noiseSource.possibleBiomes().add(biomes.getHolderOrThrow(DABiomes.AETHER_PLAINS));
            noiseSource.possibleBiomes().add(biomes.getHolderOrThrow(DABiomes.ROSEROOT_FOREST));

           // chunkGenerator.biomeSource.possibleBiomes().add(biomes.getHolderOrThrow(DABiomes.AETHER_PLAINS));
           // chunkGenerator.biomeSource.possibleBiomes().add(biomes.getHolderOrThrow(DABiomes.ROSEROOT_FOREST));

            //adds our own climate parameters to the aether

            //noiseSource = new MultiNoiseBiomeSource(new Climate.ParameterList(List.of(Pair.of(new Climate.ParameterPoint(temps1, fullRange, fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(DABiomes.AETHER_PLAINS)), Pair.of(new Climate.ParameterPoint(temps2, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_MEADOW)), Pair.of(new Climate.ParameterPoint(temps2, Climate.Parameter.span(0.0F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_FOREST)), Pair.of(new Climate.ParameterPoint(temps3, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_GROVE)), Pair.of(new Climate.ParameterPoint(temps3, Climate.Parameter.span(0.0F, 0.8F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_FOREST)), Pair.of(new Climate.ParameterPoint(temps3, Climate.Parameter.span(0.8F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_GROVE)), Pair.of(new Climate.ParameterPoint(temps4, Climate.Parameter.span(-1.0F, -0.1F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_GROVE)), Pair.of(new Climate.ParameterPoint(temps4, Climate.Parameter.span(-0.1F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_FOREST)), Pair.of(new Climate.ParameterPoint(temps5, Climate.Parameter.span(-1.0F, -0.6F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_MEADOW)), Pair.of(new Climate.ParameterPoint(temps5, Climate.Parameter.span(-0.6F, -0.3F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_GROVE)), Pair.of(new Climate.ParameterPoint(temps5, Climate.Parameter.span(-0.3F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_FOREST)), Pair.of(new Climate.ParameterPoint(temps6, Climate.Parameter.span(-1.0F, -0.1F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_MEADOW)), Pair.of(new Climate.ParameterPoint(temps6, Climate.Parameter.span(-0.1F, 0.8F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_WOODLAND)), Pair.of(new Climate.ParameterPoint(temps5, Climate.Parameter.span(0.8F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0L), biomes.getOrThrow(AetherBiomes.SKYROOT_FOREST)))));
            System.out.println(noiseSource.parameters.values());


            //chunkGenerator.biomeSource.possibleBiomes().removeAll(Collections.singleton(true));





            chunkGenerator.featuresPerStep = Suppliers
                    .memoize(() -> FeatureSorter.buildFeaturesPerStep(List.copyOf(chunkGenerator.biomeSource.possibleBiomes()),
                            biome -> chunkGenerator.generationSettingsGetter.apply(biome).features(), true));
        }

    }
}*/
