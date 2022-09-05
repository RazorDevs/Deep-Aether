package teamrazor.deepaether.deepaether.world.Gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.world.Gen.DeepAetherModEntityBiomeModifier;
import teamrazor.deepaether.world.Gen.DeepAetherModOreBiomeModifier;
import teamrazor.deepaether.world.Gen.DeepAetherModVegetalBiomeModifier;


public class DeepAetherModBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, DeepAetherMod.MODID);


    public static RegistryObject<Codec<DeepAetherModVegetalBiomeModifier>> VEGETAL_MODIFIER = BIOME_MODIFIERS.register("vegetal", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(DeepAetherModVegetalBiomeModifier::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(DeepAetherModVegetalBiomeModifier::feature)
            ).apply(builder, DeepAetherModVegetalBiomeModifier::new)));

    public static RegistryObject<Codec<DeepAetherModOreBiomeModifier>> ORE_MODIFIER = BIOME_MODIFIERS.register("ores", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(DeepAetherModOreBiomeModifier::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(DeepAetherModOreBiomeModifier::feature)
            ).apply(builder, DeepAetherModOreBiomeModifier::new)));

    public static RegistryObject<Codec<DeepAetherModEntityBiomeModifier>> ENTITY_MODIFIER = BIOME_MODIFIERS.register("entities", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(DeepAetherModEntityBiomeModifier::biomes),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("entity").forGetter(DeepAetherModEntityBiomeModifier::spawnerData)
            ).apply(builder, DeepAetherModEntityBiomeModifier::new)));


    public static void register(IEventBus eventBus) {
        BIOME_MODIFIERS.register(eventBus);
    }
}
