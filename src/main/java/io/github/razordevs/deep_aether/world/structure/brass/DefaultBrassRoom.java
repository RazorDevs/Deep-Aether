package io.github.razordevs.deep_aether.world.structure.brass;

import io.github.razordevs.deep_aether.world.structure.DAStructurePieceTypes;
import io.github.razordevs.deep_aether.world.structure.processor.BrassDungeonRoomProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class DefaultBrassRoom extends AbstractBrassRoom {
    public DefaultBrassRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation) {
        super(DAStructurePieceTypes.BRASS_ROOM.get(), manager, name,
                makeSettingsWithPivot(makeSettings(), rotation), pos);
    }

    public DefaultBrassRoom(StructurePieceSerializationContext context, CompoundTag tag) {
        super(DAStructurePieceTypes.BRASS_ROOM.get(), tag, context.structureTemplateManager(), resourceLocation
                -> makeSettings());
    }

    protected static StructurePlaceSettings makeSettings() {
        return new StructurePlaceSettings()
                .addProcessor(LOCKED_NIMBUS_STONE)
                .addProcessor(TRAPPED_NIMBUS_STONE)
                .addProcessor(TRAPPED_SKYROOT_PLANKS_NORMAL);
    }

    public static class BossRoom extends AbstractBossRoom {
        public BossRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation) {
            super(DAStructurePieceTypes.BRASS_BOSS_ROOM.get(), manager, name,
                    makeSettingsWithPivot(makeSettings(), rotation), pos);
        }

        public BossRoom(StructurePieceSerializationContext context, CompoundTag tag) {
            super(DAStructurePieceTypes.BRASS_BOSS_ROOM.get(), tag, context.structureTemplateManager(), resourceLocation
                    -> makeSettings());
        }

        protected static StructurePlaceSettings makeSettings() {
            return DefaultBrassRoom.makeSettings()
                    .addProcessor(BrassDungeonRoomProcessor.INSTANCE)
                    .setFinalizeEntities(true);
        }
    }
}