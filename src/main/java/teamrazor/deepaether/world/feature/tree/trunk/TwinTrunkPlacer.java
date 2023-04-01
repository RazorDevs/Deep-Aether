package teamrazor.deepaether.world.feature.tree.trunk;

import com.google.common.collect.ImmutableList;
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
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class TwinTrunkPlacer extends TrunkPlacer {
    public static final Codec<TwinTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70261_) -> {
        return trunkPlacerParts(p_70261_).apply(p_70261_, TwinTrunkPlacer::new);
    });



    public TwinTrunkPlacer(int p_161770_, int p_161771_, int p_161772_) {
        super(p_161770_, p_161771_, p_161772_);
    }

    protected TrunkPlacerType<?> type() {
        return DaTrunkPlacerTypes.TWIN_TRUNK_PLACER.get();
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> posState, RandomSource random, int p_226082_, BlockPos pos, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos.MutableBlockPos blockposState$mutableblockposState = pos.mutable();
        BlockPos pos1 = blockposState$mutableblockposState.below();
        setDirtAt(level, posState, random, pos1, config);
        setDirtAt(level, posState, random, pos1.mutable().move(direction.getOpposite()), config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        int i;
        int m = 1;
        int j = 0;
        this.placeLog(level, posState, random, pos1.above(1), config);
        this.placeLog(level, posState, random, pos1.above(1).mutable().move(direction.getOpposite(),1), config);
        for(i = 2; i < p_226082_; ++i) {
            if(random.nextInt(10) >= (i/2)+m) {
                m += 1;
                j += 1;
            }
            else m+=1;
            this.placeLog(level, posState, random, pos1.above(i).mutable().move(direction, j), config);
            this.placeLog(level, posState, random, pos1.above(i).mutable().move(direction.getOpposite(), j+1), config);
        }

        list.add(new FoliagePlacer.FoliageAttachment(pos1.above(i).mutable().move(direction, j), 0, false));
        list.add(new FoliagePlacer.FoliageAttachment(pos1.above(i).mutable().move(direction.getOpposite(), j+1), 0, false));
        return list;
    }
}
