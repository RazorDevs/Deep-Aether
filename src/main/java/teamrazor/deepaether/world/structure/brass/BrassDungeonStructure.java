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
import teamrazor.deepaether.world.structure.DAStructureTypes;

import java.util.Optional;

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
        int x = chunkpos.getMiddleBlockX();
        int z = chunkpos.getMiddleBlockZ();

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
        BlockPos bossPos = elevatedPos.offset(this.getBossRoomOffset(templateManager, rotation.rotate(Direction.SOUTH)));
        BrassBossRoom bossRoom = new BrassBossRoom(
                templateManager,
                "boss_room",
                bossPos,
                rotation
        );
        builder.addPiece(bossRoom);
    }

    private Vec3i getBossRoomOffset(StructureTemplateManager templateManager, Direction direction) {
        StructureTemplate template = templateManager.getOrCreate(new ResourceLocation(DeepAether.MODID, "brass_dungeon/boss_room"));
        Vec3i size = template.getSize();
        Vec3i offset = new Vec3i(size.getX() / -2, size.getY() / -2, (size.getZ()) / -2);
        return offset.relative(direction, -1);
    }
}
