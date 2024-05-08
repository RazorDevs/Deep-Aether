package teamrazor.deepaether.world.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;

public class DAStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, DeepAether.MODID);

    public static final RegistryObject<StructureType<BrassDungeonStructure>> BRASS_DUNGEON = STRUCTURE_TYPES.register("brass_dungeon", () -> () -> BrassDungeonStructure.CODEC);
}
