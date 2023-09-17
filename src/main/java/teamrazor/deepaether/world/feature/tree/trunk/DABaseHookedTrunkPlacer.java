package teamrazor.deepaether.world.feature.tree.trunk;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class DABaseHookedTrunkPlacer extends TrunkPlacer {

    //This code was written by the Aether Team and not by Team Razor
    public DABaseHookedTrunkPlacer(int height, int heightRandA, int heightRandB) {
        super(height, heightRandA, heightRandB);
    }

    public List<FoliagePlacer.FoliageAttachment> placeVerticalTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int height, BlockPos pos, BlockStateProvider trunkProvider) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        int radius = 0;
        int cutoff = height % 2 == 1 ? height : height - 1;
        for (int i = 0; i < height + 1; ++i) {
            if (i < height) {
                this.placeTrunk(level, blockSetter, random, pos.above(i), trunkProvider);
            }
            if (i > 1 && i < cutoff) {
                radius = (i % 2) + 1;
            }
            if (i > cutoff) {
                radius = 1;
            }
            if (i >= height) {
                radius = 0;
            }
            list.add(new FoliagePlacer.FoliageAttachment(pos.above(i), radius, false));
        }
        return list;
    }

    public List<FoliagePlacer.FoliageAttachment> placeBranches(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int height, BlockPos pos, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        int bound = 3;
        float f = 0;
        for (int i = random.nextInt(2) + 3; i < height - 2; i += random.nextInt(2) + 1) {
            int j = (int) Mth.cos(f);
            int k = (int) Mth.sin(f);

            int offset = random.nextInt(bound) + 2;
            for (int l = 0; l < offset; l++) {
                BlockPos blockpos = pos.offset(j * l, i, k * l);
                this.placeBranch(level, blockSetter, random, blockpos, config);
                list.add(new FoliagePlacer.FoliageAttachment(blockpos.above(), 0, false));
                list.add(new FoliagePlacer.FoliageAttachment(blockpos, 1, false));
                list.add(new FoliagePlacer.FoliageAttachment(blockpos.below(), 0, false));
            }
            for (int l = 1; l < 4; l++) {
                if (l == 2) {
                    offset += 1;
                }
                if (l <= 2) {
                    list.add(new FoliagePlacer.FoliageAttachment(pos.offset(j * (offset - 1) - k, i + l, k * (offset - 1) - j), 0, false));
                    list.add(new FoliagePlacer.FoliageAttachment(pos.offset(j * (offset - 1) + k, i + l, k * (offset - 1) + j), 0, false));
                }
                BlockPos blockpos = pos.offset(j * offset, i + l, k * offset);
                this.placeBranch(level, blockSetter, random, blockpos, config);
                list.add(new FoliagePlacer.FoliageAttachment(blockpos, 1, false));
                list.add(new FoliagePlacer.FoliageAttachment(blockpos.above(), 0, false));
            }
            f += 0.25F * ((float) Math.PI * 2.0F);
            if (bound > 1 && f % 0.5 == 0) bound -= random.nextInt(2);
        }
        return list;
    }

    protected boolean placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, BlockStateProvider trunkProvider) {
        return this.placeTrunk(level, blockSetter, random, pos, Function.identity(), trunkProvider);
    }

    protected boolean placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, Function<BlockState, BlockState> propertySetter, BlockStateProvider trunkProvider) {
        if (this.validTreePos(level, pos)) {
            blockSetter.accept(pos, propertySetter.apply(trunkProvider.getState(random, pos)));
            return true;
        } else {
            return false;
        }
    }

    protected boolean placeBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config) {
        return this.placeBranch(level, blockSetter, random, pos, config, Function.identity());
    }

    protected boolean placeBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config, Function<BlockState, BlockState> propertySetter) {
        if (this.validBranchPos(level, pos)) {
            blockSetter.accept(pos, propertySetter.apply(config.trunkProvider.getState(random, pos)));
            return true;
        } else {
            return false;
        }
    }

    protected boolean validBranchPos(LevelSimulatedReader level, BlockPos pos) {
        return TreeFeature.isAirOrLeaves(level, pos) || this.isTrunk(level, pos);
    }

    public abstract boolean isTrunk(LevelSimulatedReader level, BlockPos pos);
}