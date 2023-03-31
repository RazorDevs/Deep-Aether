package teamrazor.deepaether.world.feature.tree.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class TwinTrunkPlacer extends TrunkPlacer {
    public static final Codec<TwinTrunkPlacer> CODEC = RecordCodecBuilder.create((p_161786_) -> {
        return trunkPlacerParts(p_161786_).and(p_161786_.group(ExtraCodecs.POSITIVE_INT.optionalFieldOf("min_height_for_leaves", 1).forGetter((p_161788_) -> {
            return p_161788_.minHeightForLeaves;
        }), IntProvider.codec(1, 64).fieldOf("bend_length").forGetter((p_161784_) -> {
            return p_161784_.bendLength;
        }))).apply(p_161786_, TwinTrunkPlacer::new);
    });
    private final int minHeightForLeaves;
    private final IntProvider bendLength;

    public TwinTrunkPlacer(int p_161770_, int p_161771_, int p_161772_, int p_161773_, IntProvider p_161774_) {
        super(p_161770_, p_161771_, p_161772_);
        this.minHeightForLeaves = p_161773_;
        this.bendLength = p_161774_;
    }

    protected TrunkPlacerType<?> type() {
        return DaTrunkPlacerTypes.TWIN_TRUNK_PLACER.get();
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> posState, RandomSource random, int p_226082_, BlockPos pos, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int i = p_226082_ - 1;
        BlockPos.MutableBlockPos blockposState$mutableblockposState = pos.mutable();
        BlockPos blockposState = blockposState$mutableblockposState.below();
        setDirtAt(level, posState, random, blockposState, config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        for(int j = 0; j <= i; ++j) {
            if (j + 1 >= i + random.nextInt(2)) {
                blockposState$mutableblockposState.move(direction);
            }

            if (TreeFeature.validTreePos(level, blockposState$mutableblockposState)) {
                this.placeLog(level, posState, random, blockposState$mutableblockposState, config);
            }

            if (j >= this.minHeightForLeaves) {
                list.add(new FoliagePlacer.FoliageAttachment(blockposState$mutableblockposState.immutable(), 0, false));
            }

            blockposState$mutableblockposState.move(Direction.UP);
        }

        int l = this.bendLength.sample(random);

        for(int k = 0; k <= l; ++k) {
            if (TreeFeature.validTreePos(level, blockposState$mutableblockposState)) {
                this.placeLog(level, posState, random, blockposState$mutableblockposState, config);
            }

            list.add(new FoliagePlacer.FoliageAttachment(blockposState$mutableblockposState.immutable(), 0, false));
            blockposState$mutableblockposState.move(direction);
        }

        direction = direction.getOpposite();
        i = p_226082_ - 1;
        blockposState$mutableblockposState = pos.mutable();
        blockposState = blockposState$mutableblockposState.below();
        setDirtAt(level, posState, random, blockposState, config);


        for(int j = 0; j <= i; ++j) {
            if (j + 1 >= i + random.nextInt(2)) {
                blockposState$mutableblockposState.move(direction);
            }

            if (TreeFeature.validTreePos(level, blockposState$mutableblockposState)) {
                this.placeLog(level, posState, random, blockposState$mutableblockposState, config);
            }

            if (j >= this.minHeightForLeaves) {
                list.add(new FoliagePlacer.FoliageAttachment(blockposState$mutableblockposState.immutable(), 0, false));
            }

            blockposState$mutableblockposState.move(Direction.UP);
        }

        l = this.bendLength.sample(random);

        for(int k = 0; k <= l; ++k) {
            if (TreeFeature.validTreePos(level, blockposState$mutableblockposState)) {
                this.placeLog(level, posState, random, blockposState$mutableblockposState, config);
            }

            list.add(new FoliagePlacer.FoliageAttachment(blockposState$mutableblockposState.immutable(), 0, false));
            blockposState$mutableblockposState.move(direction);
        }

        return list;
    }
}
