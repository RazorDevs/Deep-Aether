package teamrazor.deepaether.world.structure.brass;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import teamrazor.deepaether.init.DABlocks;

import java.util.function.Function;

public abstract class AbstractBrassRoom extends BrassDungeonPiece {


    public AbstractBrassRoom(StructurePieceType type, StructureTemplateManager manager, String name, StructurePlaceSettings settings, BlockPos pos) {
        super(type, manager, name, settings, pos);
    }

    public AbstractBrassRoom(StructurePieceType type, CompoundTag tag, StructureTemplateManager manager, Function<ResourceLocation, StructurePlaceSettings> settingsFactory) {
        super(type, tag, manager, settingsFactory);
    }

    public static StructurePlaceSettings makeSettingsWithPivot(StructurePlaceSettings settings, Rotation rotation) {
        settings.setRotationPivot(new BlockPos(0,0,0).relative(Direction.EAST, 31));
        settings.setRotation(rotation);
        return settings;
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


    public static class AbstractBossRoom extends AbstractBrassRoom {

        public AbstractBossRoom(StructurePieceType type, StructureTemplateManager manager, String name, StructurePlaceSettings settings, BlockPos pos) {
            super(type, manager, name, settings, pos);
        }

        public AbstractBossRoom(StructurePieceType type, CompoundTag tag, StructureTemplateManager manager, Function<ResourceLocation, StructurePlaceSettings> settingsFactory) {
            super(type, tag, manager, settingsFactory);
        }

        @Override
        protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
            super.handleDataMarker(name, pos, level, random, box);
        }
    }
}