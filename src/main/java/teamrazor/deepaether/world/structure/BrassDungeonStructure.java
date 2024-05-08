package teamrazor.deepaether.world.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

import java.util.Optional;

public class BrassDungeonStructure extends Structure {
    public static final Codec<BrassDungeonStructure> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            settingsCodec(builder),
            Codec.INT.fieldOf("maxY").forGetter(o -> o.maxY),
            Codec.INT.fieldOf("belowTerrain").forGetter(o -> o.belowTerrain),
            Codec.INT.fieldOf("aboveTerrain").forGetter(o -> o.aboveTerrain),
            Codec.INT.fieldOf("minY").forGetter(o -> o.minY),
            Codec.INT.fieldOf("rangeY").forGetter(o -> o.rangeY)
    ).apply(builder, BrassDungeonStructure::new));

    private final int maxY;
    private final int belowTerrain;
    private final int aboveTerrain;
    private final int minY;
    private final int rangeY;

    protected BrassDungeonStructure(StructureSettings settings, int maxY, int belowTerrain, int aboveTerrain, int minY, int rangeY) {
        super(settings);
        this.maxY = maxY;
        this.belowTerrain = belowTerrain;
        this.aboveTerrain = aboveTerrain;
        this.minY = minY;
        this.rangeY = rangeY;
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext generationContext) {
        return Optional.empty();
    }

    @Override
    public StructureType<?> type() {
        return null;
    }
}
