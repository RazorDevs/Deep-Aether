package io.github.razordevs.deep_aether.world.structure.processor;

import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.world.structure.brass.processor.BrassDungeonRoomProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DAStructureProcessor {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, DeepAether.MODID);

    public static final DeferredHolder<StructureProcessorType<?>, StructureProcessorType<BrassDungeonRoomProcessor>> BOSS_ROOM = STRUCTURE_PROCESSOR_TYPES.register("boss_room", () -> () -> BrassDungeonRoomProcessor.CODEC);
}
