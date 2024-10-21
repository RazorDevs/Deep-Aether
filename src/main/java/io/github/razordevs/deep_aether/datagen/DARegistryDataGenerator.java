package io.github.razordevs.deep_aether.datagen;

import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.datagen.strucutre.DAStructures;
import io.github.razordevs.deep_aether.datagen.strucutre.DAStrucutreSets;
import io.github.razordevs.deep_aether.datagen.world.DAConfiguredFeatures;
import io.github.razordevs.deep_aether.datagen.world.DAPlacedFeatures;
import io.github.razordevs.deep_aether.world.structure.DAStructureProcessorLists;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
public class DARegistryDataGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, DAConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, DAPlacedFeatures::bootstrap)
            .add(Registries.PROCESSOR_LIST, DAStructureProcessorLists::bootstrap)
            .add(Registries.STRUCTURE, DAStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, DAStrucutreSets::bootstrap)
            .add(Registries.JUKEBOX_SONG, DAJukeboxSongs::bootstrap)
            .add(Registries.ENCHANTMENT, DAEnchantments::bootstrap);
    public DARegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BUILDER, Set.of("minecraft", DeepAether.MODID));
    }
}
