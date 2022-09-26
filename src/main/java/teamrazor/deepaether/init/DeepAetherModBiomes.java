package teamrazor.deepaether.init;


import com.gildedgames.aether.Aether;
import com.gildedgames.aether.data.resources.AetherBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.world.DeepAetherModBiomeBuilders;

import java.util.HashMap;
import java.util.Map;


public class DeepAetherModBiomes {
    public static final DeferredRegister REGISTRY = DeferredRegister.create(ForgeRegistries.BIOMES, DeepAetherMod.MODID);

    public static final RegistryObject<Biome> AERGLOW_GROVE = REGISTRY.register("aerglow_grove",() -> DeepAetherModBiomeBuilders.aerglowGroveBiome());
    public static final RegistryObject<Biome> VIRULENT_FOREST = REGISTRY.register("virulent_forest", () -> DeepAetherModBiomeBuilders.virulentForestBiome());

    /*public static ResourceKey<Biome> register(String name, Biome biome) {
        ResourceLocation location = new ResourceLocation(DeepAetherMod.MODID, name);
        REGISTRY.putIfAbsent(location, biome);
        return ResourceKey.create(Registry.BIOME_REGISTRY, location);
    }*/
}