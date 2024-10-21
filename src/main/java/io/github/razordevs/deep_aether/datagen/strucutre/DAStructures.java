package io.github.razordevs.deep_aether.datagen.strucutre;

import com.aetherteam.aether.data.resources.builders.AetherStructureBuilders;
import com.aetherteam.aether.data.resources.registries.AetherStructureProcessorLists;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.datagen.tags.DATags;
import io.github.razordevs.deep_aether.world.structure.DAStructureProcessorLists;
import io.github.razordevs.deep_aether.world.structure.brass.BrassDungeonStructure;
import io.github.razordevs.deep_aether.world.structure.brass.processor.BrassProcessorSettings;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DAStructures {
    //TODO: fix Sacred Lands Structures spawning at bottom Y

    public static final ResourceKey<Structure> BRASS_DUNGEON = createKey("brass_dungeon");

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, name));
    }

    public static void bootstrap(BootstrapContext<Structure> context) {
        Map<MobCategory, StructureSpawnOverride> mobSpawnsBox = Arrays.stream(MobCategory.values())
                .collect(Collectors.toMap((category) -> category, (category) -> new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, WeightedRandomList.create())));

        Map<MobCategory, StructureSpawnOverride> mobSpawnsPiece = Arrays.stream(MobCategory.values())
                .collect(Collectors.toMap((category) -> category, (category) -> new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create())));

        HolderGetter<StructureProcessorList> processors = context.lookup(Registries.PROCESSOR_LIST);

        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        context.register(BRASS_DUNGEON, new BrassDungeonStructure(AetherStructureBuilders.structure(
                biomes.getOrThrow(DATags.Biomes.HAS_BRASS_DUNGEON),
                mobSpawnsBox,
                GenerationStep.Decoration.SURFACE_STRUCTURES,
                TerrainAdjustment.NONE),
                 184, 5, new BrassProcessorSettings(processors.getOrThrow(DAStructureProcessorLists.BRASS_BOSS_ROOM), processors.getOrThrow(DAStructureProcessorLists.BRASS_ROOM))));
    }
}
