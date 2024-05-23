package teamrazor.deepaether.world.feature.features;

import com.aetherteam.aether.block.AetherBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import teamrazor.deepaether.init.DABlocks;

public class RockSpikeFeature extends Feature<NoneFeatureConfiguration> {
    public RockSpikeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos blockpos = context.origin();
        RandomSource randomsource = context.random();

        WorldGenLevel worldgenlevel;
        for(worldgenlevel = context.level(); worldgenlevel.isEmptyBlock(blockpos) && blockpos.getY() > worldgenlevel.getMinBuildHeight() + 2; blockpos = blockpos.below()) {
        }

        if (!worldgenlevel.getBlockState(blockpos).is(DABlocks.HIGHSTONE.get())) {
            return false;
        } else {
            blockpos = blockpos.above(randomsource.nextInt(4));
            int i = randomsource.nextInt(4) + 7;
            int j = i / 4 + randomsource.nextInt(2);
            if (j > 1 && randomsource.nextInt(60) == 0) {
                blockpos = blockpos.above(10 + randomsource.nextInt(30));
            }

            int k;
            int l;
            for(k = 0; k < i; ++k) {
                float f = (1.0F - (float)k / (float)i) * (float)j;
                l = Mth.ceil(f);

                for(int i1 = -l; i1 <= l; ++i1) {
                    float f1 = (float)Mth.abs(i1) - 0.25F;

                    for(int j1 = -l; j1 <= l; ++j1) {
                        float f2 = (float)Mth.abs(j1) - 0.25F;
                        if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(randomsource.nextFloat() > 0.75F))) {
                            BlockState blockstate = worldgenlevel.getBlockState(blockpos.offset(i1, k, j1));
                            if (blockstate.isAir() || isDirt(blockstate) || blockstate.is(DABlocks.RAW_CLORITE.get()) || blockstate.is(AetherBlocks.HOLYSTONE.get()) || blockstate.is(DABlocks.HIGHSTONE.get())) {
                                this.setBlock(worldgenlevel, blockpos.offset(i1, k, j1), AetherBlocks.HOLYSTONE.get().defaultBlockState());
                            }

                            if (k != 0 && l > 1) {
                                blockstate = worldgenlevel.getBlockState(blockpos.offset(i1, -k, j1));
                                if (blockstate.isAir() || isDirt(blockstate) || blockstate.is(DABlocks.RAW_CLORITE.get()) || blockstate.is(AetherBlocks.HOLYSTONE.get()) || blockstate.is(DABlocks.HIGHSTONE.get())) {
                                    this.setBlock(worldgenlevel, blockpos.offset(i1, -k, j1), AetherBlocks.HOLYSTONE.get().defaultBlockState());
                                }
                            }
                        }
                    }
                }
            }

            k = j - 1;
            if (k < 0) {
                k = 0;
            } else if (k > 1) {
                k = 1;
            }

            for(int l1 = -k; l1 <= k; ++l1) {
                for(l = -k; l <= k; ++l) {
                    BlockPos blockpos1 = blockpos.offset(l1, -1, l);
                    int j2 = 50;
                    if (Math.abs(l1) == 1 && Math.abs(l) == 1) {
                        j2 = randomsource.nextInt(5);
                    }

                    while(blockpos1.getY() > 50) {
                        BlockState blockstate1 = worldgenlevel.getBlockState(blockpos1);
                        if (!blockstate1.isAir() && !isDirt(blockstate1) && !blockstate1.is(DABlocks.RAW_CLORITE.get()) || !blockstate1.is(AetherBlocks.HOLYSTONE.get()) || !blockstate1.is(DABlocks.HIGHSTONE.get())) {
                            break;
                        }

                        this.setBlock(worldgenlevel, blockpos1, AetherBlocks.HOLYSTONE.get().defaultBlockState());
                        blockpos1 = blockpos1.below();
                        --j2;
                        if (j2 <= 0) {
                            blockpos1 = blockpos1.below(randomsource.nextInt(5) + 1);
                            j2 = randomsource.nextInt(5);
                        }
                    }
                }
            }

            return true;
        }
    }
}

