package teamrazor.deepaether.world.structure.brass;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import teamrazor.deepaether.world.structure.DAStructurePieceTypes;
import teamrazor.deepaether.world.structure.processor.BrassDungeonRoomProcessor;

public class GardenBrassRoom extends AbstractBrassRoom {
    public GardenBrassRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation) {
        super(DAStructurePieceTypes.GARDEN_BRASS_ROOM.get(), manager, name,
                makeSettingsWithPivot(makeSettings(), rotation), pos);
    }

    public GardenBrassRoom(StructurePieceSerializationContext context, CompoundTag tag) {
        super(DAStructurePieceTypes.GARDEN_BRASS_ROOM.get(), tag, context.structureTemplateManager(), resourceLocation
                -> makeSettings());
    }

    protected static StructurePlaceSettings makeSettings() {
        return new StructurePlaceSettings()
                .addProcessor(BrassDungeonPiece.LOCKED_NIMBUS_STONE)
                .addProcessor(BrassDungeonPiece.TRAPPED_NIMBUS_STONE)
                .addProcessor(BrassDungeonPiece.TRAPPED_SKYROOT_PLANKS_PLANT);
    }

    public static class BossRoom extends AbstractBossRoom {
        public BossRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation) {
            super(DAStructurePieceTypes.GARDEN_BRASS_BOSS_ROOM.get(), manager, name,
                    makeSettingsWithPivot(makeSettings(), rotation), pos);
        }

        public BossRoom(StructurePieceSerializationContext context, CompoundTag tag) {
            super(DAStructurePieceTypes.GARDEN_BRASS_BOSS_ROOM.get(), tag, context.structureTemplateManager(), resourceLocation
                    -> makeSettings());
        }

        protected static StructurePlaceSettings makeSettings() {
            return GardenBrassRoom.makeSettings()
                    .addProcessor(BrassDungeonRoomProcessor.INSTANCE)
                    .setFinalizeEntities(true);
        }
    }
}