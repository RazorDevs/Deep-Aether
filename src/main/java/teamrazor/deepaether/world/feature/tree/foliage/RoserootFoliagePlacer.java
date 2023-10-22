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



public class RoserootFoliagePlacer extends FoliagePlacer {
    public static final Codec<RoserootFoliagePlacer> CODEC = RecordCodecBuilder.create((p2) -> foliagePlacerParts(p2).and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((foliagePlacer) -> foliagePlacer.trunkHeight)).apply(p2, RoserootFoliagePlacer::new));
    private final IntProvider trunkHeight;

    public RoserootFoliagePlacer(IntProvider intProvider, IntProvider intProvider1, IntProvider intProvider2) {
        super(intProvider, intProvider1);
        this.trunkHeight = intProvider2;
    }

    protected FoliagePlacerType<?> type() {
        return DAFoliagePlacers.ROSEROOT_FOLIAGE_PLACER.get();
    }



    @Override
    protected void createFoliage(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, int i1, FoliagePlacer.FoliageAttachment foliageAttachment, int foliageMaxHeight, int i2, int i3) {
        BlockPos blockpos = foliageAttachment.pos();
        int i = randomSource.nextInt(2);
        int j = 1;
        int k = 0;

        for(int l = randomSource.nextInt(1, 4); l >= 0; --l) {
            this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.above(1+l), 0, 1, foliageAttachment.doubleTrunk());
        }
        int a1 = ((foliageMaxHeight/3)*2) + randomSource.nextInt(-1,1);
        int a2 = ((foliageMaxHeight/3)) + randomSource.nextInt(-1,1);


        for(int l = foliageMaxHeight; l >= 0; --l) {
            if (l == 0) {
                this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, 1, 1, foliageAttachment.doubleTrunk());
                blockpos = blockpos.below(1);
            }
            else if(l >= a1) {
                placeSmallCircle(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, foliageAttachment);
                blockpos = blockpos.below(1);
            }
            else if (l >= a2) {
                this.placeSquare(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos);
                blockpos = blockpos.below(1);
            }
            else {
                placeBiggerCircle(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, foliageAttachment);
                blockpos = blockpos.below(1);
            }
        }

    }
    public void placeSquare(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, BlockPos blockpos) {
        for (int i = 0; i < 3; ++i) {
            for (int ii = 0; ii < 3; ++ii) {
                tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(i-1).east(ii-1).above(2));
            }
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(0).east(2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(0).east(-2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(2).east(0).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-2).east(0).above(2));

            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(1).east(2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(1).east(-2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-1).east(2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-1).east(-2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(2).east(1).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-2).east(1).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(2).east(-1).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-2).east(-1).above(2));

        }
    }
    public void placeSmallCircle(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, BlockPos blockpos, FoliagePlacer.FoliageAttachment foliageAttachment) {
        this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, 1, 2, foliageAttachment.doubleTrunk());

        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-1).east(-1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(1).east(1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(0).east(2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(0).east(-2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(2).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-2).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-1).east(1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(1).east(-1).above(2));
    }
    public void placeBigCircle(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, BlockPos blockpos, FoliagePlacer.FoliageAttachment foliageAttachment) {
        this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, 2, 2, foliageAttachment.doubleTrunk());

        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(0).east(3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(0).east(-3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(3).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-3).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(2).east(2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-2).east(-2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(2).east(-2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-2).east(2).above(2));

    }
    public void placeBiggerCircle(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, BlockPos blockpos, FoliagePlacer.FoliageAttachment foliageAttachment) {
        this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, 2, 2, foliageAttachment.doubleTrunk());

        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(0).east(3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(0).east(-3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(3).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-3).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(2).east(2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-2).east(-2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(2).east(-2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-2).east(2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(1).east(3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-1).east(3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(1).east(-3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-1).east(-3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(3).east(1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(3).east(-1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-3).east(1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos.north(-3).east(-1).above(2));

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
