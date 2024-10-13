package teamrazor.deepaether.world.structure;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.world.structure.brass.BrassDungeonStructure;

public class DAStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_TYPE, DeepAether.MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<BrassDungeonStructure>> BRASS_DUNGEON = STRUCTURE_TYPES.register("brass_dungeon", () -> () -> BrassDungeonStructure.CODEC);
    public static final DeferredHolder<StructureType<?>, StructureType<DAJigsawStructure>> DA_JIGSAW = STRUCTURE_TYPES.register("deep_aether_jigsaw", () -> DAJigsawStructure.CODEC::codec);
}
