package io.github.razordevs.deep_aether.datagen.world;

import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.datagen.DAEnchantments;
import io.github.razordevs.deep_aether.datagen.DAJukeboxSongs;
import io.github.razordevs.deep_aether.datagen.strucutre.DAStructures;
import io.github.razordevs.deep_aether.datagen.strucutre.DAStrucutreSets;
import io.github.razordevs.deep_aether.world.structure.DAStructureProcessorLists;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
public class DAWorldGenData extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, DAConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, DAPlacedFeatures::bootstrap)
            .add(Registries.PROCESSOR_LIST, DAStructureProcessorLists::bootstrap)
            .add(Registries.STRUCTURE, DAStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, DAStrucutreSets::bootstrap)
            .add(Registries.JUKEBOX_SONG, DAJukeboxSongs::bootstrap)
            .add(Registries.ENCHANTMENT, DAEnchantments::bootstrap);
    public DAWorldGenData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(DeepAether.MODID));
    }
}
