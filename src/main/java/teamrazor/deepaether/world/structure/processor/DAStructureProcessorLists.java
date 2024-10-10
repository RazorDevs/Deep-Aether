package teamrazor.deepaether.world.structure.processor;

import com.aetherteam.aether.world.processor.BossRoomProcessor;
import com.aetherteam.aether.world.processor.DoubleDropsProcessor;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.AxisAlignedLinearPosTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.world.structure.brass.BrassDungeonPiece;

import java.util.List;

public class DAStructureProcessorLists {
    public static final ResourceKey<StructureProcessorList> BRASS_ROOM = createKey("brass_room");
    public static final ResourceKey<StructureProcessorList> GARDEN_BRASS_ROOM = createKey("garden_brass_room");
    public static final ResourceKey<StructureProcessorList> INFESTED_BRASS_ROOM = createKey("infested_brass_room");


    private static ResourceKey<StructureProcessorList> createKey(String name) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, new ResourceLocation(DeepAether.MODID, name));
    }

    private static final AxisAlignedLinearPosTest ON_FLOOR = new AxisAlignedLinearPosTest(1.0F, 0.0F, 0, 1, Direction.Axis.Y);

    public static void bootstrap(BootstapContext<StructureProcessorList> context) {
        register(context, BRASS_ROOM, List.of(
                BrassDungeonPiece.LOCKED_NIMBUS_STONE,
                BrassDungeonPiece.TRAPPED_NIMBUS_STONE,
                BrassDungeonPiece.TRAPPED_SKYROOT_PLANKS_NORMAL,
                DoubleDropsProcessor.INSTANCE
        ));
        register(context, GARDEN_BRASS_ROOM, List.of(
                BrassDungeonPiece.LOCKED_NIMBUS_STONE,
                BrassDungeonPiece.TRAPPED_NIMBUS_STONE,
                BrassDungeonPiece.TRAPPED_SKYROOT_PLANKS_NORMAL,
                BossRoomProcessor.INSTANCE
        ));
        register(context, INFESTED_BRASS_ROOM, List.of(
                BrassDungeonPiece.LOCKED_NIMBUS_STONE,
                BrassDungeonPiece.TRAPPED_NIMBUS_STONE,
                BrassDungeonPiece.TRAPPED_SKYROOT_PLANKS_NORMAL,
                DoubleDropsProcessor.INSTANCE
        ));
    }

    private static void register(BootstapContext<StructureProcessorList> context, ResourceKey<StructureProcessorList> key, List<StructureProcessor> processors) {
        context.register(key, new StructureProcessorList(processors));
    }
}
