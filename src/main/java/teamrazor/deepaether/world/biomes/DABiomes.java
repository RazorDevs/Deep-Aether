package teamrazor.deepaether.world.biomes;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import teamrazor.deepaether.DeepAetherMod;

import java.util.List;

public class DABiomes {
    public static final ResourceKey<Biome> AETHER_PLAINS = createKey("aether_plains");
    public static final ResourceKey<Biome> ROSEROOT_FOREST = createKey("roseroot_forest");

    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(DeepAetherMod.MODID, name));
    }

    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> vanillaConfiguredCarvers = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(AETHER_PLAINS, DABiomeBuilders.aetherPlainsBiome(placedFeatures, vanillaConfiguredCarvers));


    }
    public static BiomeSource buildDeepAetherBiomeSource(HolderGetter<Biome> biomes) {
        final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);
        final Climate.Parameter lowerTempRange = Climate.Parameter.span(-1.0F, 0.75F);
        final Climate.Parameter upperTempRange = Climate.Parameter.span(0.7F, 1.0F);
        return new MultiNoiseBiomeSource(new Climate.ParameterList<>(List.of(
                Pair.of(new Climate.ParameterPoint(FULL_RANGE, FULL_RANGE, FULL_RANGE, FULL_RANGE, FULL_RANGE, Climate.Parameter.span(1.0F, 2.0F), 0),
                        biomes.getOrThrow(DABiomes.AETHER_PLAINS)),
                Pair.of(new Climate.ParameterPoint(upperTempRange, FULL_RANGE, FULL_RANGE, FULL_RANGE, FULL_RANGE, FULL_RANGE, 0),
                        biomes.getOrThrow(DABiomes.AETHER_PLAINS))

                                )));
    }



}
