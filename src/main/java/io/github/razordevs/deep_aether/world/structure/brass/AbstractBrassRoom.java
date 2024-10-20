package io.github.razordevs.deep_aether.world.structure.brass;

import com.aetherteam.aether.blockentity.TreasureChestBlockEntity;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.datagen.loot.DALoot;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.Function;

public abstract class AbstractBrassRoom extends BrassDungeonPiece {


    public AbstractBrassRoom(StructurePieceType type, StructureTemplateManager manager, String name, StructurePlaceSettings settings, BlockPos pos) {
        super(type, manager, name, settings, pos);
    }

    public AbstractBrassRoom(StructurePieceType type, CompoundTag tag, StructureTemplateManager manager, Function<ResourceLocation, StructurePlaceSettings> settingsFactory) {
        super(type, tag, manager, settingsFactory);
    }

    @Override
    protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
        switch (name) {
            case "Brass Chest" -> createChestLoot(level, pos, random, DALoot.BRASS_DUNGEON_LOOT);
            case "Library Chest" -> createChestLoot(level, pos, random, DALoot.BRASS_DUNGEON_LOOT); //Fix later
            case "Combinder Chest" -> createChestLoot(level, pos, random, DALoot.BRASS_DUNGEON_LOOT); //Fix later
            case "Infested Chest Up" -> {
                BlockPos chest = pos.above();
                BlockEntity entity = level.getBlockEntity(chest);

                if (entity instanceof RandomizableContainerBlockEntity container) {
                    container.setLootTable(DALoot.BRASS_DUNGEON_LOOT, random.nextLong());
                }

                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            }
        }
    }

    protected void createChestLoot(ServerLevelAccessor level, BlockPos pos, RandomSource random, ResourceKey<LootTable> lootTable) {
        BlockPos chest = pos.below();
        BlockEntity entity = level.getBlockEntity(chest);

        if (entity instanceof RandomizableContainerBlockEntity container) {
            container.setLootTable(lootTable, random.nextLong());
        }

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
    }

    public static StructurePlaceSettings makeSettingsWithPivot(StructurePlaceSettings settings, Rotation rotation) {
        settings.setRotationPivot(new BlockPos(0,0,0).relative(Direction.EAST, 31));
        settings.setRotation(rotation);
        return settings;
    }

    public static class AbstractBossRoom extends AbstractBrassRoom {

        public AbstractBossRoom(StructurePieceType type, StructureTemplateManager manager, String name, StructurePlaceSettings settings, BlockPos pos) {
            super(type, manager, name, settings, pos);
        }

        public AbstractBossRoom(StructurePieceType type, CompoundTag tag, StructureTemplateManager manager, Function<ResourceLocation, StructurePlaceSettings> settingsFactory) {
            super(type, tag, manager, settingsFactory);
        }

        @Override
        protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
            switch (name) {
                case "Brass Chest" -> createChestLoot(level, pos, random, DALoot.BRASS_DUNGEON_LOOT);
                case "Library Chest" -> createChestLoot(level, pos, random, DALoot.BRASS_DUNGEON_LOOT); //Fix later
                case "Combinder Chest" -> createChestLoot(level, pos, random, DALoot.BRASS_DUNGEON_LOOT); //Fix later
                case "Infested Chest Up" -> {
                    BlockPos chest = pos.above();
                    BlockEntity entity = level.getBlockEntity(chest);

                    if (entity instanceof RandomizableContainerBlockEntity container) {
                        container.setLootTable(DALoot.BRASS_DUNGEON_LOOT, random.nextLong());
                    }

                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                }
                case "Treasure Chest" -> {
                    BlockPos chest = pos.below();
                    BlockEntity entity = level.getBlockEntity(chest);

                    if (entity instanceof RandomizableContainerBlockEntity container) {
                        container.setLootTable(DALoot.BRASS_DUNGEON_REWARD, random.nextLong());
                    }
                    TreasureChestBlockEntity.setDungeonType(level, chest, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "brass"));

                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                }
            }
        }
    }
}
