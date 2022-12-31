package teamrazor.deepaether.world.feature.tree.foliage;

import com.gildedgames.aether.world.foliageplacer.AetherFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;


import java.util.function.BiConsumer;



public class RoserootFoliagePlacer extends FoliagePlacer {
    public static final Codec<RoserootFoliagePlacer> CODEC = RecordCodecBuilder.create((p_68735_) -> {
        return foliagePlacerParts(p_68735_).and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((p_161553_) -> {
            return p_161553_.trunkHeight;
        })).apply(p_68735_, RoserootFoliagePlacer::new);
    });
    private final IntProvider trunkHeight;

    public RoserootFoliagePlacer(IntProvider p_161539_, IntProvider p_161540_, IntProvider p_161541_) {
        super(p_161539_, p_161540_);
        this.trunkHeight = p_161541_;
    }

    protected FoliagePlacerType<?> type() {
        return DeepAetherModFoliagePlacers.ROSEROOT_FOLIAGE_PLACER.get();
    }


    protected void createFoliage(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, TreeConfiguration treeConfiguration, int p_225748_, FoliagePlacer.FoliageAttachment foliageAttachment, int foliageMaxHeight, int p_225751_, int p_225752_) {
        BlockPos blockpos = foliageAttachment.pos();
        int i = randomSource.nextInt(2);
        int j = 1;
        int k = 0;

        for(int l = randomSource.nextInt(1, 4); l >= 0; --l) {
            this.placeLeavesRow(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.above(1+l), 0, 1, foliageAttachment.doubleTrunk());
        }
        int a1 = ((foliageMaxHeight/3)*2) + randomSource.nextInt(-1,1);
        int a2 = ((foliageMaxHeight/3)) + randomSource.nextInt(-1,1);


        for(int l = foliageMaxHeight; l >= 0; --l) {
            if (l == 0) {
                this.placeLeavesRow(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos, 1, 1, foliageAttachment.doubleTrunk());
                blockpos = blockpos.below(1);
            }
            else if(l >= a1) {
                placeSmallCircle(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos, foliageAttachment);
                blockpos = blockpos.below(1);
            }
            else if (l >= a2) {
                this.placeSquare(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos);
                blockpos = blockpos.below(1);
            }
            else {
                placeBiggerCircle(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos, foliageAttachment);
                blockpos = blockpos.below(1);
            }
        }

    }
    public void placeSquare(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, TreeConfiguration treeConfiguration, BlockPos blockpos) {
        for (int i = 0; i < 3; ++i) {
            for (int ii = 0; ii < 3; ++ii) {
                tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(i-1).east(ii-1).above(2));
            }
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(0).east(2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(0).east(-2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(2).east(0).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-2).east(0).above(2));

            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(1).east(2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(1).east(-2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-1).east(2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-1).east(-2).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(2).east(1).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-2).east(1).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(2).east(-1).above(2));
            if(randomSource.nextInt(4) == 1)tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-2).east(-1).above(2));

        }
    }
    public void placeSmallCircle(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, TreeConfiguration treeConfiguration, BlockPos blockpos, FoliagePlacer.FoliageAttachment foliageAttachment) {
        this.placeLeavesRow(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos, 1, 2, foliageAttachment.doubleTrunk());

        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-1).east(-1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(1).east(1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(0).east(2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(0).east(-2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(2).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-2).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-1).east(1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(1).east(-1).above(2));
    }
    public void placeBigCircle(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, TreeConfiguration treeConfiguration, BlockPos blockpos, FoliagePlacer.FoliageAttachment foliageAttachment) {
        this.placeLeavesRow(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos, 2, 2, foliageAttachment.doubleTrunk());

        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(0).east(3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(0).east(-3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(3).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-3).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(2).east(2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-2).east(-2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(2).east(-2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-2).east(2).above(2));

    }
    public void placeBiggerCircle(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, TreeConfiguration treeConfiguration, BlockPos blockpos, FoliagePlacer.FoliageAttachment foliageAttachment) {
        this.placeLeavesRow(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos, 2, 2, foliageAttachment.doubleTrunk());

        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(0).east(3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(0).east(-3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(3).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-3).east(0).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(2).east(2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-2).east(-2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(2).east(-2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-2).east(2).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(1).east(3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-1).east(3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(1).east(-3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-1).east(-3).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(3).east(1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(3).east(-1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-3).east(1).above(2));
        if(randomSource.nextBoolean())tryPlaceLeaf(levelSimulatedReader, biConsumer, randomSource, treeConfiguration, blockpos.north(-3).east(-1).above(2));

    }

    public int foliageHeight(RandomSource randomSource, int p_225741_, TreeConfiguration treeConfiguration) {
        return Math.max(4, p_225741_ - this.trunkHeight.sample(randomSource));
    }

    protected boolean shouldSkipLocation(RandomSource randomSource, int a, int b, int c, int d, boolean p_225738_) {
        return a == d && c == d && d > 0;
    }
}