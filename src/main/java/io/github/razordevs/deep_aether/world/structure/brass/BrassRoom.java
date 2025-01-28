package io.github.razordevs.deep_aether.world.structure.brass;

import com.aetherteam.aether.block.AetherBlocks;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.world.structure.DAStructurePieceTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class BrassRoom extends BrassDungeonPiece {
    public BrassRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation, Holder<StructureProcessorList> processors) {
        super(DAStructurePieceTypes.BRASS_ROOM.get(), manager, name,
                makeSettingsWithPivot(makeSettings(), rotation), pos, processors);
    }

    public BrassRoom(StructurePieceSerializationContext context, CompoundTag tag) {
        super(DAStructurePieceTypes.BRASS_ROOM.get(), context.registryAccess(), tag, context.structureTemplateManager(), resourceLocation
                -> makeSettings());
    }

    protected static StructurePlaceSettings makeSettings() {
        return new StructurePlaceSettings();
    }

    @Override
    protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
        switch (name) {
            case "Aerlavender Flowers" -> {
                Block block = getRandomAerlavenderFlower(random);
                level.setBlock(pos.below(), AetherBlocks.AETHER_GRASS_BLOCK.get().defaultBlockState(), 2);
                level.setBlock(pos, block.defaultBlockState(), 2);
                if (block instanceof DoublePlantBlock) {
                    level.setBlock(pos.above(), block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 2);
                }
            }
            case "Golden Flowers" -> {
                Block block = getRandomGoldenFlower(random);
                level.setBlock(pos.below(), DABlocks.GOLDEN_GRASS_BLOCK.get().defaultBlockState(), 2);
                level.setBlock(pos, block.defaultBlockState(), 2);
                if (block instanceof DoublePlantBlock) {
                    level.setBlock(pos.above(), block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 2);
                }
            }
            case "Squash" -> {
                Block block = getRandomSquash(random);
                level.setBlock(pos, block.defaultBlockState(), 2);
            }
        }
    }

    public static Block getRandomAerlavenderFlower(RandomSource random) {
        return switch (random.nextInt(4)) {
            case 0 -> DABlocks.TALL_AERLAVENDER.get();
            case 1 -> DABlocks.FEATHER_GRASS.get();
            case 2 -> DABlocks.TALL_FEATHER_GRASS.get();
            default -> DABlocks.AERLAVENDER.get();
        };
    }
    public static Block getRandomGoldenFlower(RandomSource random) {
        return switch (random.nextInt(4)) {
            case 0 -> DABlocks.GOLDEN_ASPESS.get();
            case 1 -> DABlocks.MINI_GOLDEN_GRASS.get();
            case 2 -> DABlocks.MEDIUM_GOLDEN_GRASS.get();
            default -> DABlocks.GOLDEN_FLOWER.get();
        };
    }
    public static Block getRandomSquash(RandomSource random) {
        return switch (random.nextInt(3)) {
            case 0 -> DABlocks.GREEN_SQUASH.get();
            case 1 -> DABlocks.BLUE_SQUASH.get();
            default -> DABlocks.PURPLE_SQUASH.get();
        };
    }

    public static StructurePlaceSettings makeSettingsWithPivot(StructurePlaceSettings settings, Rotation rotation) {
        settings.setRotationPivot(new BlockPos(0,0,0).relative(Direction.EAST, 31));
        settings.setRotation(rotation);
        return settings;
    }

    public static class BossRoom extends BrassDungeonPiece {
        public BossRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation, Holder<StructureProcessorList> processors) {
            super(DAStructurePieceTypes.BRASS_BOSS_ROOM.get(), manager, name,
                    BrassRoom.makeSettingsWithPivot(makeSettings(), rotation), pos, processors);
        }

        public BossRoom(StructurePieceSerializationContext context, CompoundTag tag) {
            super(DAStructurePieceTypes.BRASS_BOSS_ROOM.get(), context.registryAccess(), tag, context.structureTemplateManager(), resourceLocation
                    -> makeSettings());
        }

        protected static StructurePlaceSettings makeSettings() {
            return new StructurePlaceSettings().setFinalizeEntities(true);
        }

        @Override
        protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
            switch (name) {
                case "Aerlavender Flowers" -> {
                    Block block = getRandomAerlavenderFlower(random);
                    level.setBlock(pos.below(), AetherBlocks.AETHER_GRASS_BLOCK.get().defaultBlockState(), 2);
                    level.setBlock(pos, block.defaultBlockState(), 2);
                    if (block instanceof DoublePlantBlock) {
                        level.setBlock(pos.above(), block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 2);
                    }
                }
                case "Golden Flowers" -> {
                    Block block = getRandomGoldenFlower(random);
                    level.setBlock(pos.below(), DABlocks.GOLDEN_GRASS_BLOCK.get().defaultBlockState(), 2);
                    level.setBlock(pos, block.defaultBlockState(), 2);
                    if (block instanceof DoublePlantBlock) {
                        level.setBlock(pos.above(), block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 2);
                    }
                }
                case "Squash" -> {
                    Block block = getRandomSquash(random);
                    level.setBlock(pos, block.defaultBlockState(), 2);
                }
            }
        }
    }
}