package teamrazor.deepaether.world.structure.brass;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.world.structure.DAStructurePieceTypes;

public class BrassRoom extends BrassDungeonPiece {

    public BrassRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation) {
        super(DAStructurePieceTypes.BRASS_ROOM.get(), manager, name,
                BrassBossRoom.makeSettingsWithPivot(makeSettings(), rotation), pos);
    }

    public BrassRoom(StructurePieceSerializationContext context, CompoundTag tag) {
        super(DAStructurePieceTypes.BRASS_ROOM.get(), tag, context.structureTemplateManager(), resourceLocation
                -> makeSettings());
    }

    private static StructurePlaceSettings makeSettings() {
        return new StructurePlaceSettings()
                .addProcessor(BrassDungeonPiece.LOCKED_NIMBUS_STONE)
                .addProcessor(BrassDungeonPiece.TRAPPED_NIMBUS_STONE)
                .addProcessor(BrassDungeonPiece.TRAPPED_SKYROOT_PLANKS_NORMAL);
    }


    @Override
    protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
        if (name.equals("Door Location")) {
            level.setBlock(pos, DABlocks.LOCKED_NIMBUS_STONE.get().defaultBlockState(), 2); //Fix later
        }
    }
}