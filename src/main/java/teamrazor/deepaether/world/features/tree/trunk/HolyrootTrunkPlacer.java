package teamrazor.deepaether.world.features.tree.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class HolyrootTrunkPlacer extends TrunkPlacer {
    public static final Codec<HolyrootTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70261_)
            -> trunkPlacerParts(p_70261_).apply(p_70261_, HolyrootTrunkPlacer::new));

    public HolyrootTrunkPlacer(int p_70248_, int p_70249_, int p_70250_) {
        super(p_70248_, p_70249_, p_70250_);
    }

    protected TrunkPlacerType<?> type() {
        return DaTrunkPlacerTypes.HOLYROOT_TRUNK_PLACER.get();
    }

    List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> pos, RandomSource random, int height, BlockPos blockPos, TreeConfiguration config) {
        int minSmallTrunkHeight = random.nextInt(4,6);
        for(int i = 0; i < height; ++i) {
            this.placeLog(level, pos, random, blockPos.above(i), config);
        }

        list.add(new FoliagePlacer.FoliageAttachment(blockPos.above(height), 0, true));

        return list;
    }

    public void placeSmallTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> pos, RandomSource random, BlockPos blockPos, TreeConfiguration config, Direction direction) {
        int upwards = 0;
        int i;
        for(i = 1; i < random.nextInt(3,6); i++)  {
            if(i != 1 && random.nextBoolean())
                upwards++;
            this.placeLog(level, pos, random, blockPos.relative(direction, i).above(upwards), config);
        }
        list.add(new FoliagePlacer.FoliageAttachment(blockPos.relative(direction, i-1).above(upwards), 0, false));
    }
}