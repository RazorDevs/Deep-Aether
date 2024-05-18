package teamrazor.deepaether.world.feature.features;

import com.aetherteam.aether.AetherTags;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import teamrazor.deepaether.init.DABlocks;

import javax.annotation.Nullable;
import java.util.Iterator;

public class CloriteColumnsFeature extends Feature<ColumnFeatureConfiguration> {
    private static final ImmutableList<Block> CANNOT_PLACE_ON;
    private static final int CLUSTERED_REACH = 5;
    private static final int CLUSTERED_SIZE = 50;
    private static final int UNCLUSTERED_REACH = 8;
    private static final int UNCLUSTERED_SIZE = 15;

    public CloriteColumnsFeature(Codec<ColumnFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean place(FeaturePlaceContext<ColumnFeatureConfiguration> pContext) {
        int i = pContext.chunkGenerator().getSeaLevel();
        BlockPos blockpos = pContext.origin();
        WorldGenLevel worldgenlevel = pContext.level();
        RandomSource randomsource = pContext.random();
        ColumnFeatureConfiguration columnfeatureconfiguration = (ColumnFeatureConfiguration)pContext.config();
        if (!canPlaceAt(worldgenlevel, i, blockpos.mutable())) {
            return false;
        } else {
            int j = columnfeatureconfiguration.height().sample(randomsource);
            boolean flag = randomsource.nextFloat() < 0.9F;
            int k = Math.min(j, flag ? 5 : 8);
            int l = flag ? 50 : 15;
            boolean flag1 = false;
            Iterator var12 = BlockPos.randomBetweenClosed(randomsource, l, blockpos.getX() - k, blockpos.getY(), blockpos.getZ() - k, blockpos.getX() + k, blockpos.getY(), blockpos.getZ() + k).iterator();

            while(var12.hasNext()) {
                BlockPos blockpos1 = (BlockPos)var12.next();
                int i1 = j - blockpos1.distManhattan(blockpos);
                if (i1 >= 0) {
                    flag1 |= this.placeColumn(worldgenlevel, i, blockpos1, i1, columnfeatureconfiguration.reach().sample(randomsource));
                }
            }

            return flag1;
        }
    }

    private boolean placeColumn(LevelAccessor pLevel, int pSeaLevel, BlockPos pPos, int pDistance, int pReach) {
        boolean flag = false;
        Iterator var7 = BlockPos.betweenClosed(pPos.getX() - pReach, pPos.getY(), pPos.getZ() - pReach, pPos.getX() + pReach, pPos.getY(), pPos.getZ() + pReach).iterator();

        while(true) {
            int i;
            BlockPos blockpos1;
            do {
                if (!var7.hasNext()) {
                    return flag;
                }

                BlockPos blockpos = (BlockPos)var7.next();
                i = blockpos.distManhattan(pPos);
                blockpos1 = isAirOrCloud(pLevel, blockpos) ? findSurface(pLevel, pSeaLevel, blockpos.mutable(), i) : findAir(pLevel, blockpos.mutable(), i);
            } while(blockpos1 == null);

            int j = pDistance - i / 2;

            for(BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos1.mutable(); j >= 0; --j) {
                if (isAirOrCloud(pLevel, blockpos$mutableblockpos)) {
                    this.setBlock(pLevel, blockpos$mutableblockpos, DABlocks.CLORITE.get().defaultBlockState());
                    blockpos$mutableblockpos.move(Direction.UP);
                    flag = true;
                } else {
                    if (!pLevel.getBlockState(blockpos$mutableblockpos).is(DABlocks.CLORITE.get())) {
                        break;
                    }

                    blockpos$mutableblockpos.move(Direction.UP);
                }
            }
        }
    }

    @Nullable
    private static BlockPos findSurface(LevelAccessor pLevel, int pSeaLevel, BlockPos.MutableBlockPos pPos, int pDistance) {
        while(pPos.getY() > pLevel.getMinBuildHeight() + 1 && pDistance > 0) {
            --pDistance;
            if (canPlaceAt(pLevel, pSeaLevel, pPos)) {
                return pPos;
            }

            pPos.move(Direction.DOWN);
        }

        return null;
    }

    private static boolean canPlaceAt(LevelAccessor pLevel, int pSeaLevel, BlockPos.MutableBlockPos pPos) {
        if (!isAirOrCloud(pLevel, pPos)) {
            return false;
        } else {
            BlockState blockstate = pLevel.getBlockState(pPos.move(Direction.DOWN));
            pPos.move(Direction.UP);
            return !blockstate.isAir() && !CANNOT_PLACE_ON.contains(blockstate.getBlock());
        }
    }

    @Nullable
    private static BlockPos findAir(LevelAccessor pLevel, BlockPos.MutableBlockPos pPos, int pDistance) {
        while(pPos.getY() < pLevel.getMaxBuildHeight() && pDistance > 0) {
            --pDistance;
            BlockState blockstate = pLevel.getBlockState(pPos);
            if (CANNOT_PLACE_ON.contains(blockstate.getBlock())) {
                return null;
            }

            if (blockstate.isAir()) {
                return pPos;
            }

            pPos.move(Direction.UP);
        }

        return null;
    }

    private static boolean isAirOrCloud(LevelAccessor pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return blockstate.isAir() || blockstate.is(AetherTags.Blocks.AERCLOUDS);
    }

    static {
        CANNOT_PLACE_ON = ImmutableList.of(Blocks.WATER, Blocks.BEDROCK, Blocks.CHEST, Blocks.SPAWNER);
    }
}
