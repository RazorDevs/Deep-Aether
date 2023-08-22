package teamrazor.deepaether.world.biomes;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import teamrazor.deepaether.DeepAetherMod;

public class DABiomes {
    public static final ResourceKey<Biome> AERLAVENDER_FIELDS = createKey("aerlavender_fields");
    public static final ResourceKey<Biome> AERGLOW_FOREST = createKey("aerglow_forest");
    public static final ResourceKey<Biome> BLUE_AERGLOW_FOREST = createKey("blue_aerglow_forest");
    public static final ResourceKey<Biome> MYSTIC_AERGLOW_FOREST = createKey("mystic_aerglow_forest");
    public static final ResourceKey<Biome> YAGROOT_SWAMP = createKey("yagroot_swamp");
    public static final ResourceKey<Biome> GOLDEN_HEIGHTS = createKey("golden_heights");
    public static final ResourceKey<Biome> SACRED_LANDS = createKey("sacred_lands");
    public static final ResourceKey<Biome> STORMCLOUD_SEA = createKey("stormcloud_sea");
    public static final ResourceKey<Biome> STORMCLOUD_SEA_EDGE = createKey("stormcloud_sea_edge");


    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(DeepAetherMod.MODID, name));
    }
}
