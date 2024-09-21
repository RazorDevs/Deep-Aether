package teamrazor.deepaether.world.structure.brass;

import com.aetherteam.aether.blockentity.TreasureChestBlockEntity;
import com.aetherteam.aether.world.processor.BossRoomProcessor;
import com.aetherteam.aether.world.structurepiece.AetherTemplateStructurePiece;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.datagen.loot.DALoot;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.world.structure.DAStructurePieceTypes;
import teamrazor.deepaether.world.structure.processor.BrassDungeonRoomProcessor;

import java.util.Objects;

public class BrassBossRoom extends BrassDungeonPiece {

    public BrassBossRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation) {
        super(DAStructurePieceTypes.BRASS_BOSS_ROOM.get(), manager, name,
                makeSettingsWithPivot(makeSettings(), rotation), pos);
    }

    public BrassBossRoom(StructurePieceSerializationContext context, CompoundTag tag) {
        super(DAStructurePieceTypes.BRASS_BOSS_ROOM.get(), tag, context.structureTemplateManager(), resourceLocation
                -> makeSettings());
    }

    public static StructurePlaceSettings makeSettingsWithPivot(StructurePlaceSettings settings, Rotation rotation) {
        settings.setRotationPivot(new BlockPos(0,0,0).relative(Direction.EAST, 31));
        settings.setRotation(rotation);
        return settings;
    }

    private static StructurePlaceSettings makeSettings() {
        return new StructurePlaceSettings()
                .addProcessor(BrassDungeonPiece.LOCKED_NIMBUS_STONE)
                .addProcessor(BrassDungeonPiece.NIMBUS_STONE)
                .addProcessor(BrassDungeonRoomProcessor.INSTANCE)
                .setFinalizeEntities(true);
    }

    @Override
    protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
        if (name.equals("Treasure Chest")) {
            BlockPos chest = pos.below();
            BlockEntity entity = level.getBlockEntity(chest);

            if (entity instanceof RandomizableContainerBlockEntity container) {
                container.setLootTable(DALoot.BRASS_DUNGEON_REWARD, random.nextLong());
            }
            TreasureChestBlockEntity.setDungeonType(level, chest, new ResourceLocation(DeepAether.MODID, "brass"));

            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
        if (name.equals("Door Location")) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2); //Fix later
        }
    }
}