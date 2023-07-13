package teamrazor.deepaether.world.features.feature;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.data.resources.AetherFeatureStates;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import teamrazor.deepaether.world.features.configuration.CloudPillarConfiguration;

public class CloudPillarFeature  extends Feature<CloudPillarConfiguration> {
    public CloudPillarFeature(Codec<CloudPillarConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CloudPillarConfiguration> featurePlaceContext) {
        BlockPos blockpos = featurePlaceContext.origin();
        WorldGenLevel worldgenlevel = featurePlaceContext.level();
        RandomSource randomsource = featurePlaceContext.random();
        int maxLengt = randomsource.nextInt(15);
        int SecondaryDirectionChance;
        int MainDirection = randomsource.nextInt(0, 3);
        boolean SecondaryDirection = randomsource.nextBoolean();

        BlendInWithTerrain(blockpos, worldgenlevel, randomsource, 1);

        boolean canContinueBuilding = true;
        //IF SOUP
        for (int i = 0; i <= maxLengt; i++) {
            for (int ii = 0; ii <= 1; ii++) {
                if (canContinueBuilding) {
                    SecondaryDirectionChance = randomsource.nextInt(0, 4);
                    canContinueBuilding = placeCircle(blockpos, worldgenlevel, randomsource);
                    blockpos = blockpos.above(1);
                    if (MainDirection == 0) {
                        blockpos = blockpos.east(1);
                        if (SecondaryDirectionChance == 0) {
                            if (SecondaryDirection)
                                blockpos = blockpos.south(1);
                            else
                                blockpos = blockpos.north(1);
                        }
                    } else if (MainDirection == 1) {
                        blockpos = blockpos.west(1);
                        if (SecondaryDirectionChance == 0) {
                            if (SecondaryDirection)
                                blockpos = blockpos.south(1);
                            else
                                blockpos = blockpos.north(1);
                        }
                    } else if (MainDirection == 2) {
                        blockpos = blockpos.south(1);
                        if (SecondaryDirectionChance == 0) {
                            if (SecondaryDirection)
                                blockpos = blockpos.west(1);
                            else
                                blockpos = blockpos.east(1);
                        }
                    } else {
                        blockpos = blockpos.north(1);
                        if (SecondaryDirectionChance == 0) {
                            if (SecondaryDirection)
                                blockpos = blockpos.west(1);
                            else
                                blockpos = blockpos.east(1);
                        }
                    }
                } else BlendInWithTerrain(blockpos, worldgenlevel, randomsource, -1);
                canContinueBuilding = placeCircle(blockpos, worldgenlevel, randomsource);
                blockpos = blockpos.above(1);
            }
        }
        placeTopCircle(blockpos, worldgenlevel, randomsource);
        return true;
    }

    public boolean placeCircle(BlockPos blockPos, WorldGenLevel worldGenLevel, RandomSource randomSource) {
        int a;
        int stop = 0;
        for (int ii = 1; ii <= 5; ii++) {
            if (ii == 1 || ii == 5) {
                a = 3;
                for (int i = 1; i <= a; ) {
                    i++;
                    int airChance = randomSource.nextInt(0, 5);
                    if (worldGenLevel.isEmptyBlock(blockPos.east(i + 1).south(ii))) {
                        if (airChance > 0) {
                            this.setBlock(worldGenLevel, blockPos.east(i + 1).south(ii), AetherFeatureStates.COLD_AERCLOUD);
                        }
                    } else stop += 1;
                }
            } else {
                a = 5;
                for (int i = 1; i <= a; ) {
                    i++;
                    int airChance = randomSource.nextInt(0, 5);
                    if (worldGenLevel.isEmptyBlock(blockPos.east(i).south(ii))) {
                        if (airChance > 0) {
                            this.setBlock(worldGenLevel, blockPos.east(i).south(ii), AetherFeatureStates.COLD_AERCLOUD);
                        }
                    } else stop += 1;
                }
            }
        }
        if (stop >= 21)
            return false;
        else
            return true;
    }

    public void placeTopCircle(BlockPos blockPos, WorldGenLevel worldGenLevel, RandomSource randomSource) {
        for (int ii = 1; ii <= 3; ii++) {
            for (int i = 1; i <= 3; ) {
                i++;
                int airChance = randomSource.nextInt(0, 5);
                if (worldGenLevel.isEmptyBlock(blockPos.east(i + 1).south(ii))) {
                    if (airChance > 0) {
                        this.setBlock(worldGenLevel, blockPos.east(i + 1).south(ii + 1), AetherFeatureStates.COLD_AERCLOUD);
                    }
                }
            }
        }
    }

    public void BlendInWithTerrain(BlockPos blockPos, WorldGenLevel worldGenLevel, RandomSource randomSource, int ceilingOrFloor) {
        int a;
        for (int ii = 1; ii <= 5; ii++) {
            if (ii == 1 || ii == 5) {
                a = 5;
                for (int i = 1; i <= a; ) {
                    i++;
                    Block block = worldGenLevel.getBlockState(blockPos.east(i-2).south(ii)).getBlock();
                    if (worldGenLevel.getBlockState(blockPos.east(i-1).south(ii)).is(AetherTags.Blocks.AERCLOUDS)) {
                        if (randomSource.nextInt(0, 6) > 0)
                            this.setBlock(worldGenLevel, blockPos.east(i + 1).south(ii).below(ceilingOrFloor), AetherFeatureStates.COLD_AERCLOUD);
                        if (randomSource.nextInt(0, 6) > 0)
                            this.setBlock(worldGenLevel, blockPos.east(i + 1).south(ii).below(ceilingOrFloor * 2), AetherFeatureStates.COLD_AERCLOUD);

                    }
                }
            } else {
                a = 7;
                for (int i = 1; i <= a; ) {
                    i++;
                    if (worldGenLevel.getBlockState(blockPos.east(i-1).south(ii)).is(AetherTags.Blocks.AERCLOUDS)) {
                        if (randomSource.nextInt(0, 6) > 0)
                            this.setBlock(worldGenLevel, blockPos.east(i).south(ii).below(ceilingOrFloor), AetherFeatureStates.COLD_AERCLOUD);
                        if (randomSource.nextInt(0, 6) > 0)
                            this.setBlock(worldGenLevel, blockPos.east(i).south(ii).below(ceilingOrFloor * 2), AetherFeatureStates.COLD_AERCLOUD);
                    }
                }
            }
        }
    }
}
