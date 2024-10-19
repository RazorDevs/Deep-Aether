package io.github.razordevs.deep_aether.world.biomes;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class DABiomes {
    public static final ResourceKey<Biome> AERLAVENDER_FIELDS = createKey("aerlavender_fields");
    public static final ResourceKey<Biome> AERGLOW_FOREST = createKey("aerglow_forest");

    public static final ResourceKey<Biome> BLUE_AERGLOW_FOREST = createKey("blue_aerglow_forest");

    public static final ResourceKey<Biome> MYSTIC_AERGLOW_FOREST = createKey("mystic_aerglow_forest");
    public static final ResourceKey<Biome> YAGROOT_SWAMP = createKey("yagroot_swamp");
    public static final ResourceKey<Biome> GOLDEN_HEIGHTS = createKey("golden_heights");
    public static final ResourceKey<Biome> GOLDEN_GROVE = createKey("golden_grove");
    public static final ResourceKey<Biome> SACRED_LANDS = createKey("sacred_lands");
    public static final ResourceKey<Biome> CLOUD = createKey("cloud");
    public static final ResourceKey<Biome> STORM_CLOUD = createKey("storm_cloud");
    public static final ResourceKey<Biome> OVERGROWN_CLOUD = createKey("overgrown_cloud");
    public static final ResourceKey<Biome> SKYROOT_RAINFOREST = createKey("skyroot_rainforest");

    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, name));
    }
}
