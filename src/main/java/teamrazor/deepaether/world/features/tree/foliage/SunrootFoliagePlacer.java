package teamrazor.deepaether.world.features.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import teamrazor.deepaether.world.features.tree.foliage.DAFoliagePlacers;

//This code was written by the Aether Team and not by Team Razor
public class SunrootFoliagePlacer extends FoliagePlacer {
        public static final Codec<SunrootFoliagePlacer> CODEC = RecordCodecBuilder.create((codec) -> foliagePlacerParts(codec)
                .and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((placer) -> placer.trunkHeight))
                .apply(codec, SunrootFoliagePlacer::new));
        private final IntProvider trunkHeight;

        public SunrootFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
                super(radius, offset);
                this.trunkHeight = height;
        }

        @Override
        protected FoliagePlacerType<?> type() {
                return DAFoliagePlacers.SUNROOT_FOLIAGE_PLACER.get();
        }

        @Override
        protected void createFoliage(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration configuration, int maxTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
                BlockPos blockpos = attachment.pos();
                int a;
                int ii = 0;
                int parts = random.nextInt(3,5);
                for (int i = maxTreeHeight; i > 0; i--) {


                        a = (ii - (ii % (maxTreeHeight/parts)  /  (maxTreeHeight/parts)+1));



                        if(a%2 == 1)
                                this.placeLeavesRow(level, setter, random, configuration, blockpos.above(i-offset), (int) (a/2+0.5), 0, attachment.doubleTrunk());
                        else this.placeLeavesSquare(level, setter, random, configuration, blockpos.above(i-offset), a+1, 0);

                        ii++;
                }
        }

        public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
                return Math.max(4, i - this.trunkHeight.sample(randomSource));
        }

        protected void placeLeavesSquare(LevelSimulatedReader level, FoliagePlacer.FoliageSetter setter, RandomSource random, TreeConfiguration config, BlockPos pos, int size, int height) {

                for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                                tryPlaceLeaf(level, setter, random, config, pos.above(height).east((i/2)+1).north((j/2)+1));
                        }
                }

        }

        protected boolean shouldSkipLocation(RandomSource randomSource, int a, int b, int c, int d, boolean b1) {
                return a == d && c == d && d > 0;
        }
}
