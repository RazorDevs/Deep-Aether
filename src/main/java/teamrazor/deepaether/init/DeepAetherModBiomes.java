/*
package teamrazor.deepaether.init;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.common.registry.worldgen.AetherBiomes;
import com.gildedgames.aether.common.world.builders.AetherBiomeBuilders;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.world.DeepAetherModBiomeBuilders;

import java.util.function.Supplier;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepAetherModBiomes extends AetherBiomes {
    public static final DeferredRegister<Biome> REGISTRY = DeferredRegister.create(ForgeRegistries.BIOMES, DeepAetherMod.MODID);

    public static final RegistryObject<Biome> AERGLOW_GROVE = register(DeepAetherModBiomes.Keys.AERGLOW_GROVE, DeepAetherModBiomeBuilders::aerglowGroveBiome);

    private static RegistryObject<Biome> register(ResourceKey<Biome> biomeResourceKey, Supplier<Biome> biome) {
        return REGISTRY.register(biomeResourceKey.location().getPath(), biome);
    }

    public static class Keys extends AetherBiomes.Keys {
        public static  final ResourceKey<Biome> AERGLOW_GROVE = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(DeepAetherMod.MODID, "aerglow_grove"));
    }

}
*/