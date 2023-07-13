package teamrazor.deepaether.world.features.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.material.Fluids;
import teamrazor.deepaether.init.DABlocks;


public class OldFoliagePlacer extends FoliagePlacer {
    public static final Codec<OldFoliagePlacer> CODEC = RecordCodecBuilder.create((p2) -> {
        return foliagePlacerParts(p2).and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((foliagePlacer) -> {
            return foliagePlacer.trunkHeight;
        })).apply(p2, OldFoliagePlacer::new);
    });
    private final IntProvider trunkHeight;

    public OldFoliagePlacer(IntProvider intProvider, IntProvider intProvider1, IntProvider intProvider2) {
        super(intProvider, intProvider1);
        this.trunkHeight = intProvider2;
    }

    protected FoliagePlacerType<?> type() {
        return DAFoliagePlacers.OLD_FOLIAGE_PLACER.get();
    }



    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration configuration, int i1, FoliageAttachment foliageAttachment, int foliageMaxHeight, int i2, int i3) {
        int MaxHeight = random.nextInt(5,8);
        BlockPos blockpos = foliageAttachment.pos().below(MaxHeight/2 + 1);
        int size;
        int y = Math.round(MaxHeight/2);
        for(int i = 0; i < MaxHeight; i++) {
            if(y-i == 0)
                size = y -1;
            else size = y-Math.abs(y-i);
            this.placeOldLeavesRow(level, foliageSetter, random, configuration, blockpos, size, i);
        }
    }


    private void placeOldLeavesRow(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration configuration, BlockPos pos, int size, int height) {
        this.placeEmptyLeavesRow(level, foliageSetter, random, configuration, pos, size+1, height, false);
        this.placeLeavesRow(level, foliageSetter, random, configuration, pos, size, height, false);

    }

    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return Math.max(4, i - this.trunkHeight.sample(randomSource));
    }

    protected boolean shouldSkipLocation(RandomSource randomSource, int a, int b, int c, int d, boolean b1) {
        return a == d && c == d && d > 0;
    }

    protected void placeEmptyLeavesRow(LevelSimulatedReader p_225629_, FoliageSetter p_272772_, RandomSource random, TreeConfiguration p_225632_, BlockPos p_225633_, int p_225634_, int p_225635_, boolean p_225636_) {
        int i = p_225636_ ? 1 : 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int j = -p_225634_; j <= p_225634_ + i; ++j) {
            for(int k = -p_225634_; k <= p_225634_ + i; ++k) {
                if (!this.shouldSkipLocationSigned(random, j, p_225635_, k, p_225634_, p_225636_)) {
                    blockpos$mutableblockpos.setWithOffset(p_225633_, j, p_225635_, k);
                    if(random.nextBoolean())
                        tryPlaceLeaf(p_225629_, p_272772_, random, p_225632_, blockpos$mutableblockpos);
                }
            }
        }
    }
}
