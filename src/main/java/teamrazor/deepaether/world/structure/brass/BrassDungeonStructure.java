package teamrazor.deepaether.world.structure.brass;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.world.structurepiece.LargeAercloudChunk;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import teamrazor.deepaether.world.structure.DAStructureTypes;

import java.util.*;

//TODO: ADD CLOUD BED
//TODO: ADD ENTRANCE

public class BrassDungeonStructure extends Structure {
    public static final Codec<BrassDungeonStructure> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            settingsCodec(builder),
            Codec.INT.fieldOf("minY").forGetter(o -> o.minY),
            Codec.INT.fieldOf("rangeY").forGetter(o -> o.rangeY)
    ).apply(builder, BrassDungeonStructure::new));

    private final int minY;
    private final int rangeY;

    public BrassDungeonStructure(StructureSettings settings, int minY, int rangeY) {
        super(settings);
        this.minY = minY;
        this.rangeY = rangeY;
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        RandomSource random = context.random();
        ChunkPos chunkpos = context.chunkPos();

        int height = this.minY + random.nextInt(this.rangeY);
        BlockPos blockpos = new BlockPos(chunkpos.getMiddleBlockX(), height, chunkpos.getMiddleBlockZ());

        return Optional.of(new GenerationStub(blockpos, piecesBuilder -> this.generatePieces(piecesBuilder, context, blockpos)));
    }

    @Override
    public StructureType<?> type() {
        return DAStructureTypes.BRASS_DUNGEON.get();
    }

    private String getRandomRoomType(RandomSource random) {
        int num = random.nextInt(77);

        if(num <= 20)
            return "empty_0";
        else if(num <=40)
            return "empty_1";
        else if(num <=60)
            return "empty_2";
        else if(num <=65)
            return "garden";
        else if(num <= 75)
            return "library";
        else return "infested";
    }


    private void generatePieces(StructurePiecesBuilder builder, GenerationContext context, BlockPos elevatedPos) {
        RandomSource random = context.random();
        StructureTemplateManager templateManager = context.structureTemplateManager();
        this.buildCloudBed(builder, random, elevatedPos.above().relative(Direction.NORTH, 34).west(10));

        Rotation rotation = Rotation.getRandom(random);
        this.createBossRoom(random,
                builder,
                elevatedPos,
                rotation,
                templateManager,
                true);

        rotation = rotation.getRotated(Rotation.CLOCKWISE_90);
        this.createBossRoom(random,
                builder,
                elevatedPos.relative(rotation.rotate(Direction.SOUTH), 1),
                rotation,
                templateManager,
                false
        );
        rotation = rotation.getRotated(Rotation.CLOCKWISE_90);
        this.createBossRoom(random,
                builder,
                elevatedPos.relative(rotation.rotate(Direction.SOUTH), 1).relative(rotation.rotate(Direction.EAST), 1),
                rotation,
                templateManager,
                false
        );

        rotation = rotation.getRotated(Rotation.CLOCKWISE_90);
        this.createBossRoom(random,
                builder,
                elevatedPos.relative(rotation.rotate(Direction.EAST), 1),
                rotation,
                templateManager,
                false
        );

        rotation = rotation.getRotated(Rotation.CLOCKWISE_90);

        builder.addPiece(new DefaultBrassRoom(
                templateManager,
                "door",
                elevatedPos.relative(rotation.rotate(Direction.EAST), 4),
                rotation));
    }

    private void createBossRoom(RandomSource random, StructurePiecesBuilder builder, BlockPos pos, Rotation rotation, StructureTemplateManager templateManager, boolean parent) {
        String room = this.getRandomRoomType(random);
        if(room.equals("garden")) {
            if(parent) builder.addPiece(new GardenBrassRoom.BossRoom(templateManager, "garden_boss", pos, rotation));
            else builder.addPiece(new GardenBrassRoom(templateManager, "garden", pos, rotation));

        }
        else if(room.equals("infested")) {
            if(parent) builder.addPiece(new InfestedBrassRoom.BossRoom(templateManager, "infested_boss", pos, rotation));
            else builder.addPiece(new InfestedBrassRoom(templateManager, "infested", pos, rotation));

        }
        else {
            if(parent) builder.addPiece(new DefaultBrassRoom.BossRoom(templateManager, room +"_boss", pos, rotation));
            else builder.addPiece(new DefaultBrassRoom(templateManager, room, pos, rotation));

        }

        //Roof
        builder.addPiece(new DefaultBrassRoom(
                templateManager,
                "room_part_up",
                pos.above(32),
                rotation));
    }

    private void buildCloudBed(StructurePiecesBuilder builder, RandomSource random, BlockPos origin) {
        int xBounds = 77;
        int zBounds = 77;
        BlockPos.MutableBlockPos offset = origin.mutable().move(0, -1, 0);

        Map<ChunkPos, Set<BlockPos>> chunks = new HashMap<>();
        Set<BlockPos> positions = new HashSet<>();
        for (int tries = 0; tries < 100; tries++) {
            int x = offset.getX() + random.nextInt(xBounds);
            int y = offset.getY();
            int z = offset.getZ() + random.nextInt(zBounds);
            int xTendency = random.nextInt(3) - 1;
            int zTendency = random.nextInt(3) - 1;

            for (int n = 0; n < 10; ++n) {
                x += random.nextInt(3) - 1 + xTendency;
                if (random.nextBoolean()) {
                    y += random.nextInt(3) - 1;
                }
                z += random.nextInt(3) - 1 + zTendency;

                for (int x1 = x; x1 < x + random.nextInt(4) + 3; ++x1) {
                    for (int y1 = y; y1 < y + random.nextInt(1) + 2; ++y1) {
                        for (int z1 = z; z1 < z + random.nextInt(4) + 3; ++z1) {
                            if (Math.abs(x1 - x) + Math.abs(y1 - y) + Math.abs(z1 - z) < 4 + random.nextInt(2)) {
                                BlockPos newPosition = new BlockPos(x1, y1, z1);
                                positions.add(newPosition);
                                chunks.computeIfAbsent(new ChunkPos(newPosition), (pos) -> new HashSet<>());
                            }
                        }
                    }
                }
            }
        }

        chunks.forEach(((chunkPos, blockPosSet) -> {
            blockPosSet.addAll(positions.stream().filter(pos -> (new ChunkPos(pos).equals(chunkPos))).toList());
            builder.addPiece(new LargeAercloudChunk(blockPosSet,
                    BlockStateProvider.simple(AetherBlocks.COLD_AERCLOUD.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)),
                    new BoundingBox(chunkPos.getMinBlockX(), origin.getY(), chunkPos.getMinBlockZ(), chunkPos.getMaxBlockX(), origin.getY(), chunkPos.getMaxBlockZ()),
                    Direction.NORTH));
        }));
    }
}
