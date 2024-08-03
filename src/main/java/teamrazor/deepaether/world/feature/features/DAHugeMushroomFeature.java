package teamrazor.deepaether.world.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import teamrazor.deepaether.world.feature.features.configuration.DAHugeMushroomFeatureConfiguration;

public class DAHugeMushroomFeature extends Feature<DAHugeMushroomFeatureConfiguration> {
    public DAHugeMushroomFeature(Codec<DAHugeMushroomFeatureConfiguration> p_65975_) {
        super(p_65975_);
    }


    @Override
    public boolean place(FeaturePlaceContext<DAHugeMushroomFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        RandomSource randomsource = pContext.random();
        DAHugeMushroomFeatureConfiguration config = pContext.config();
        int height = this.getTreeHeight(randomsource, config.baseHeight, config.randomHeight);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        if (!this.isValidPosition(worldgenlevel, blockpos, height, blockpos$mutableblockpos, config)) {
            return false;
        } else {
            this.makeCap(worldgenlevel, randomsource, blockpos, height, blockpos$mutableblockpos, config);
            this.placeTrunk(worldgenlevel, randomsource, blockpos, config, height, config.trunkRadius, blockpos$mutableblockpos);
            this.placeRoots(worldgenlevel, config.rootsProvider, randomsource, blockpos);
            return true;
        }
    }

    public void placeRoots(WorldGenLevel level, BlockStateProvider blockstate, RandomSource random, BlockPos pos) {
        int intA;
        int intB;
        int intC;
        int maxRootLength;

        //Max length of roots without moving down
        int maxFlatRootLength;
        Direction direction;

        for (int i = 1; i <= 4; i++) {

            intA = 0;
            intB = 0;
            intC = 0;
            maxRootLength = random.nextInt(8,15);
            maxFlatRootLength = random.nextInt(4,7);

            if(i==1) direction = Direction.NORTH;
            else if (i==2) direction = Direction.SOUTH;
            else if(i==3) direction = Direction.EAST;
            else direction = Direction.WEST;



            for (int j = 0; j < maxRootLength && intC < maxFlatRootLength; j++) {
                if (level.isEmptyBlock(pos.below(intB + 1).relative(direction,intA))) {
                    intB++;
                    intC = 0;
                }
                else if (level.isEmptyBlock(pos.below(intB).relative(direction,intA+1))) {
                    intA++;
                    intC++;
                }
                this.setBlock(level, pos.below(intB).relative(direction, intA), blockstate.getState(random, pos));
            }
        }
    }


    /**
     * [Modified] {@link net.minecraft.world.level.levelgen.feature.HugeRedMushroomFeature#makeCap(LevelAccessor, RandomSource, BlockPos, int, BlockPos.MutableBlockPos, HugeMushroomFeatureConfiguration)}
     */
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, DAHugeMushroomFeatureConfiguration config) {
        int a = config.trunkRadius % 2 == 0 ? -1 : 0;
        for(int y = height - 3; y <= height; ++y) {
            int j = y < height ? config.foliageRadius : config.foliageRadius -1;
            int k = config.foliageRadius - 2 + a;

            int j2 = j-a;

            for(int x = -j; x <= j2; ++x) {
                for(int z = -j; z <= j2; ++z) {
                    boolean flag = x == -j;
                    boolean flag1 = x == j2;
                    boolean flag2 = z == -j;
                    boolean flag3 = z == j2;
                    boolean flag4 = flag || flag1;
                    boolean flag5 = flag2 || flag3;
                    if (y >= height || flag4 != flag5) {
                        mutablePos.setWithOffset(pos, x, y, z);
                        if (!level.getBlockState(mutablePos).isSolidRender(level, mutablePos)) {
                            BlockState blockstate = config.capProvider.getState(random, pos);
                            if (blockstate.hasProperty(HugeMushroomBlock.WEST) && blockstate.hasProperty(HugeMushroomBlock.EAST) && blockstate.hasProperty(HugeMushroomBlock.NORTH) && blockstate.hasProperty(HugeMushroomBlock.SOUTH) && blockstate.hasProperty(HugeMushroomBlock.UP)) {
                                blockstate = blockstate.setValue(HugeMushroomBlock.UP, y >= height - 1).setValue(HugeMushroomBlock.WEST, x < -k).setValue(HugeMushroomBlock.EAST, x > k).setValue(HugeMushroomBlock.NORTH, z < -k).setValue(HugeMushroomBlock.SOUTH, z > k);
                            }

                            this.setBlock(level, mutablePos, blockstate);
                        }
                    }
                }
            }
        }

    }
    /**
     * [Code Copy] {@link net.minecraft.world.level.levelgen.feature.HugeRedMushroomFeature#getTreeRadiusForHeight(int, int, int, int)} (LevelAccessor, RandomSource, BlockPos, int, BlockPos.MutableBlockPos, HugeMushroomFeatureConfiguration)}
     */
    protected int getTreeRadiusForHeight(int radius, int height) {
        int i = 0;
        if (height < -1 && height >= -1 - 3) {
            i = radius;
        } else if (height == -1) {
            i = radius;
        }

        return i;
    }

    /**
     * [Modified] {@link net.minecraft.world.level.levelgen.feature.HugeRedMushroomFeature#placeTrunk(LevelAccessor, RandomSource, BlockPos, HugeMushroomFeatureConfiguration, int, BlockPos.MutableBlockPos)}
     */
    protected void placeTrunk(LevelAccessor pLevel, RandomSource pRandom, BlockPos pPos, DAHugeMushroomFeatureConfiguration pConfig, int pMaxHeight, int radius, BlockPos.MutableBlockPos pMutablePos) {
        for(int iii = 0; iii < radius; ++iii) {
            for (int ii = 0; ii < radius; ++ii) {
                for (int i = 0; i < pMaxHeight; ++i) {
                    pMutablePos.set(pPos).move(Direction.UP, i).move(Direction.NORTH, ii - (radius/2)).move(Direction.EAST, iii);
                    if (!pLevel.getBlockState(pMutablePos).isSolidRender(pLevel, pMutablePos)) {
                        this.setBlock(pLevel, pMutablePos, pConfig.stemProvider.getState(pRandom, pPos));
                    }
                }
            }
        }
    }

    protected boolean isValidPosition(LevelAccessor pLevel, BlockPos pPos, int pMaxHeight, BlockPos.MutableBlockPos pMutablePos, DAHugeMushroomFeatureConfiguration pConfig) {
        int i = pPos.getY();
        if (i >= pLevel.getMinBuildHeight() + 1 && i + pMaxHeight + 1 < pLevel.getMaxBuildHeight()) {
            BlockState blockstate = pLevel.getBlockState(pPos.below());
            if (!isDirt(blockstate) && !blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
                return false;
            } else {
                for(int j = 0; j <= pMaxHeight; ++j) {
                    int k = this.getTreeRadiusForHeight(pConfig.foliageRadius, j);

                    for(int l = -k; l <= k; ++l) {
                        for(int i1 = -k; i1 <= k; ++i1) {
                            BlockState blockstate1 = pLevel.getBlockState(pMutablePos.setWithOffset(pPos, l, j, i1));
                            if (!blockstate1.isAir() && !blockstate1.is(BlockTags.LEAVES)) {
                                return false;
                            }
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    protected int getTreeHeight(RandomSource pRandom, int baseHeight, int randomHeight) {
        return pRandom.nextInt(randomHeight) + baseHeight;
    }
}
