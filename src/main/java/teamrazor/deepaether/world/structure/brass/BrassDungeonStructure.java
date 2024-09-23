package teamrazor.deepaether.world.structure.brass;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.world.structure.DAStructurePieceTypes;
import teamrazor.deepaether.world.structure.DAStructureTypes;

import java.util.Optional;

//TODO: ADD CLOUD BED
//TODO: ADD ENTRANCE
//TODO: ADD VARIATION

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

        //int terrainHeight = context.chunkGenerator().getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        int height = this.minY + random.nextInt(this.rangeY);
        //height = Math.max(terrainHeight, height);
        BlockPos blockpos = new BlockPos(chunkpos.getMiddleBlockX(), height, chunkpos.getMiddleBlockZ());

        return Optional.of(new GenerationStub(blockpos, piecesBuilder -> this.generatePieces(piecesBuilder, context, blockpos)));
    }



    @Override
    public StructureType<?> type() {
        return DAStructureTypes.BRASS_DUNGEON.get();
    }

    private void generatePieces(StructurePiecesBuilder builder, GenerationContext context, BlockPos elevatedPos) {
        RandomSource random = context.random();
        StructureTemplateManager templateManager = context.structureTemplateManager();

        Rotation rotation = Rotation.getRandom(random);
        this.createBossRoom(
                builder,
                elevatedPos,
                rotation,
                templateManager,
                true);

        rotation = rotation.getRotated(Rotation.CLOCKWISE_90);
        this.createBossRoom(
                builder,
                elevatedPos.relative(rotation.rotate(Direction.SOUTH), 1),
                rotation,
                templateManager,
                false
        );
        rotation = rotation.getRotated(Rotation.CLOCKWISE_90);
        this.createBossRoom(
                builder,
                elevatedPos.relative(rotation.rotate(Direction.SOUTH), 1).relative(rotation.rotate(Direction.EAST), 1),
                rotation,
                templateManager,
                false
        );

        rotation = rotation.getRotated(Rotation.CLOCKWISE_90);
        this.createBossRoom(
                builder,
                elevatedPos.relative(rotation.rotate(Direction.EAST), 1),
                rotation,
                templateManager,
                false
        );

        rotation = rotation.getRotated(Rotation.CLOCKWISE_90);
        builder.addPiece(new BrassBossRoom(
                templateManager,
                "door",
                elevatedPos.relative(rotation.rotate(Direction.EAST), 4),
                rotation));

    }

    private void createBossRoom(StructurePiecesBuilder builder, BlockPos pos, Rotation rotation, StructureTemplateManager templateManager, boolean parent) {
        if(parent) {
            builder.addPiece(new BrassBossRoom(
                    templateManager,
                    "room_part_0",
                    pos,
                    rotation));
        }
        else {
            builder.addPiece(new BrassRoom(
                    templateManager,
                    "room_part_0_no_boss",
                    pos,
                    rotation));
        }

        builder.addPiece(new BrassRoom(
                templateManager,
                "room_part_up",
                pos.above(32),
                rotation));
    }
}
