package io.github.razordevs.deep_aether.world.structure;

import com.aetherteam.aether.world.processor.*;
import com.aetherteam.aether.world.structurepiece.bronzedungeon.BronzeDungeonPiece;
import com.aetherteam.aether.world.structurepiece.golddungeon.GoldDungeonPiece;
import com.aetherteam.aether.world.structurepiece.silverdungeon.SilverDungeonPiece;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.world.structure.brass.BrassDungeonPiece;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.AxisAlignedLinearPosTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;

public class DAStructureProcessorLists {
    public static final ResourceKey<StructureProcessorList> BRASS_ROOM = createKey("bronze_room");

    public static final ResourceKey<StructureProcessorList> BRASS_BOSS_ROOM = createKey("bronze_boss_room");

    private static ResourceKey<StructureProcessorList> createKey(String name) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, name));
    }

    public static void bootstrap(BootstrapContext<StructureProcessorList> context) {
        register(context, BRASS_ROOM, List.of(BrassDungeonPiece.TRAPPED_SKYROOT_PLANKS_NORMAL, BrassDungeonPiece.LOCKED_NIMBUS_STONE, BrassDungeonPiece.TRAPPED_NIMBUS_STONE, DoubleDropsProcessor.INSTANCE));
        register(context, BRASS_BOSS_ROOM, List.of(BrassDungeonPiece.TRAPPED_SKYROOT_PLANKS_NORMAL, BrassDungeonPiece.LOCKED_NIMBUS_STONE, BrassDungeonPiece.TRAPPED_NIMBUS_STONE, BossRoomProcessor.INSTANCE));
    }

    private static void register(BootstrapContext<StructureProcessorList> context, ResourceKey<StructureProcessorList> key, List<StructureProcessor> processors) {
        context.register(key, new StructureProcessorList(processors));
    }
}
