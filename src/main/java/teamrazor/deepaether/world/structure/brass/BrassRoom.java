package teamrazor.deepaether.world.structure.brass;

import com.aetherteam.aether.blockentity.TreasureChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.world.structure.DAStructurePieceTypes;

public class BrassRoom extends BrassDungeonPiece {

    public BrassRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation, int flag) {
        super(DAStructurePieceTypes.BRASS_ROOM.get(), manager, name,
                BrassBossRoom.makeSettingsWithPivot(makeSettings(flag), rotation), pos);
    }

    public BrassRoom(StructurePieceSerializationContext context, CompoundTag tag) {
        super(DAStructurePieceTypes.BRASS_ROOM.get(), tag, context.structureTemplateManager(), resourceLocation
                -> makeSettings(0));
    }

    protected static StructurePlaceSettings makeSettings(int flag) {
        System.out.println(flag);
        return switch (flag) {
            case (1) -> new StructurePlaceSettings()
                    .addProcessor(BrassDungeonPiece.LOCKED_NIMBUS_STONE)
                    .addProcessor(BrassDungeonPiece.TRAPPED_NIMBUS_STONE)
                    .addProcessor(BrassDungeonPiece.TRAPPED_SKYROOT_PLANKS_PLANT);
            case (2) -> new StructurePlaceSettings()
                    .addProcessor(BrassDungeonPiece.LOCKED_NIMBUS_STONE)
                    .addProcessor(BrassDungeonPiece.TRAPPED_NIMBUS_STONE)
                    .addProcessor(BrassDungeonPiece.TRAPPED_SKYROOT_PLANKS_COCKATRICE);
            default -> new StructurePlaceSettings()
                    .addProcessor(BrassDungeonPiece.LOCKED_NIMBUS_STONE)
                    .addProcessor(BrassDungeonPiece.TRAPPED_NIMBUS_STONE)
                    .addProcessor(BrassDungeonPiece.TRAPPED_SKYROOT_PLANKS_NORMAL);
        };
    }


    @Override
    protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
        switch (name) {
            case "Brass Chest" -> level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2); //Fix later
            case "Library Chest" -> level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2); //Fix later
            case "Combinder Chest" -> level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2); //Fix later
            case "Treasure Chest" -> {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                level.setBlock(pos.below(), Blocks.AIR.defaultBlockState(), 2);
            }
        }
    }
}