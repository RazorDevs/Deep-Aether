package io.github.razordevs.deepaether.deepaether.datagen.strucutre;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import teamrazor.deepaether.DeepAether;

import java.util.List;

public class DAStrucutreSets {
    public static final ResourceKey<StructureSet> BRASS_DUNGEON = createKey("brass_dungeon");

    private static ResourceKey<StructureSet> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(DeepAether.MODID, name));
    }

    /**
     * Warning for "deprecation" is suppressed because using {@link StructurePlacement.ExclusionZone} is necessary.
     */
    @SuppressWarnings("deprecation")
    public static void bootstrap(BootstapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        context.register(BRASS_DUNGEON, new StructureSet(List.of(
                StructureSet.entry(structures.getOrThrow(DAStructures.BRASS_DUNGEON), 1)),
                new RandomSpreadStructurePlacement(36, 24, RandomSpreadType.LINEAR, 4325807)));
    }
}
