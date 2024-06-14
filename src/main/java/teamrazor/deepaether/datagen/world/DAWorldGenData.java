package teamrazor.deepaether.datagen.world;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.datagen.strucutre.DAStructures;
import teamrazor.deepaether.datagen.strucutre.DAStrucutreSets;
import teamrazor.deepaether.world.feature.DAConfiguredFeatures;
import teamrazor.deepaether.world.feature.DAPlacedFeatures;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
public class DAWorldGenData extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, DAConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, DAPlacedFeatures::bootstrap)
            .add(Registries.STRUCTURE, DAStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, DAStrucutreSets::bootstrap);
    public DAWorldGenData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(DeepAether.MODID));
    }
}
