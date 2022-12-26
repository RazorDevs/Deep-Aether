package teamrazor.deepaether.datagen;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.data.resources.registries.AetherDimensions;
import com.gildedgames.aether.data.resources.registries.AetherNoiseSettings;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.world.feature.DeepAetherModConfiguredFeatures;
import teamrazor.deepaether.world.feature.DeepAetherModPlacedFeatures;
import teamrazor.deepaether.world.gen.biomes.DeepAetherModBiomes;


import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class DeepAetherModWorldGenData extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, DeepAetherModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, DeepAetherModPlacedFeatures::bootstrap)
            //.add(Registries.BIOME, DeepAetherModBiomes::bootstrap)
            .add(Registries.NOISE_SETTINGS, AetherNoiseSettings::bootstrap)
            .add(Registries.DIMENSION_TYPE, AetherDimensions::bootstrap);
            //.add(Registries.STRUCTURE, AetherStructures::bootstrap)
            //.add(Registries.STRUCTURE_SET, AetherStructureSets::bootstrap);

    public DeepAetherModWorldGenData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(DeepAetherMod.MODID));
    }

    public static HolderLookup.Provider createLookup() {
        return BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup());
    }
}
