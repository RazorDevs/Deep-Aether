package teamrazor.deepaether.world.feature.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;


public class YagrootFoliagePlacer extends FoliagePlacer {
    public static final Codec<YagrootFoliagePlacer> CODEC = RecordCodecBuilder.create((p2) -> {
        return foliagePlacerParts(p2).and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((foliagePlacer) -> {
            return foliagePlacer.trunkHeight;
        })).apply(p2, YagrootFoliagePlacer::new);
    });
    private final IntProvider trunkHeight;

    public YagrootFoliagePlacer(IntProvider intProvider, IntProvider intProvider1, IntProvider intProvider2) {
        super(intProvider, intProvider1);
        this.trunkHeight = intProvider2;
    }

    protected FoliagePlacerType<?> type() {
        return DAFoliagePlacers.YAGROOT_FOLIAGE_PLACER.get();
    }




    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration configuration, int i1, FoliageAttachment foliageAttachment, int foliageMaxHeight, int i2, int i3) {
        this.placeTreeLeavesRows(random.nextInt(1,3), level,foliageSetter,random,configuration,foliageAttachment);
    }

    private void placeTreeLeavesRows(int size, LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration configuration, FoliageAttachment foliageAttachment) {
        int size2;
        if(size != 1)
            size2 = size-1;
        else size2 = size;

        this.placeLeavesRow(level,foliageSetter,random,configuration,foliageAttachment.pos(), size2, 1,false);
        this.placeLeavesRow(level,foliageSetter,random,configuration,foliageAttachment.pos(), size, 0,false);
        this.placeLeavesRow(level,foliageSetter,random,configuration,foliageAttachment.pos(), size2, -1,false);

    }


    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return Math.max(4, i - this.trunkHeight.sample(randomSource));
    }

    protected boolean shouldSkipLocation(RandomSource randomSource, int a, int b, int c, int d, boolean b1) {
        return a == d && c == d && d > 0;
    }
    @Override
    protected void placeLeavesRow(LevelSimulatedReader simulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration configuration, BlockPos blockPos, int i1, int i2, boolean b) {
        int i = b ? 1 : 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j = -i1; j <= i1 + i; ++j) {
            for (int k = -i1; k <= i1 + i; ++k) {
                if (!shouldSkipLocationSigned(randomSource, j, i2, k, i1, b)) {
                    blockpos$mutableblockpos.setWithOffset(blockPos, j, i2, k);
                    tryPlaceLeaf(simulatedReader, foliageSetter, randomSource, configuration, blockpos$mutableblockpos);
                }
            }
        }
    }
}
