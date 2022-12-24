package teamrazor.deepaether.world.gen.biomes;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import teamrazor.deepaether.DeepAetherMod;

public class DeepAetherModBiomes {
    public static final ResourceKey<Biome> AETHER_PLAINS = createKey("aether_plains");

    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(DeepAetherMod.MODID, name));
    }

    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> vanillaConfiguredCarvers = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(AETHER_PLAINS, DeepAetherModBiomeBuilders.aetherPlainsBiome(placedFeatures, vanillaConfiguredCarvers));

    }
}
