package teamrazor.deepaether.world.biomes;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import teamrazor.deepaether.DeepAether;

public class DABiomes {
    public static final ResourceKey<Biome> AERLAVENDER_FIELDS = createKey("aerlavender_fields");
    public static final ResourceKey<Biome> AERGLOW_FOREST = createKey("aerglow_forest");

    public static final ResourceKey<Biome> BLUE_AERGLOW_FOREST = createKey("blue_aerglow_forest");

    public static final ResourceKey<Biome> MYSTIC_AERGLOW_FOREST = createKey("mystic_aerglow_forest");
    public static final ResourceKey<Biome> YAGROOT_SWAMP = createKey("yagroot_swamp");
    public static final ResourceKey<Biome> GOLDEN_HEIGHTS = createKey("golden_heights");
    public static final ResourceKey<Biome> GOLDEN_GROVE = createKey("golden_grove");
    public static final ResourceKey<Biome> SACRED_LANDS = createKey("sacred_lands");
    public static final ResourceKey<Biome> STORM_CLOUD = createKey("storm_cloud");
    public static final ResourceKey<Biome> SKYROOT_RAINFOREST = createKey("skyroot_rainforest");

    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(DeepAether.MODID, name));
    }
}
