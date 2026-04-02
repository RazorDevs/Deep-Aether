package io.github.razordevs.deep_aether.world.feature.features;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import io.github.razordevs.deep_aether.init.DABlocks;
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

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class HolystoneColumnsFeature extends Feature<ColumnFeatureConfiguration> {

    private static final ImmutableList<Block> CANNOT_PLACE_ON = ImmutableList.of(
            Blocks.WATER, Blocks.BEDROCK, Blocks.CHEST, Blocks.SPAWNER,
            AetherBlocks.COLD_AERCLOUD.get(), AetherBlocks.GOLDEN_AERCLOUD.get(),
            AetherBlocks.BLUE_AERCLOUD.get(), DABlocks.STERLING_AERCLOUD.get()
    );

    private static final int CLUSTERED_REACH = 5;
    private static final int CLUSTERED_SIZE = 50;
    private static final int UNCLUSTERED_REACH = 8;
    private static final int UNCLUSTERED_SIZE = 15;

    public HolystoneColumnsFeature(Codec<ColumnFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ColumnFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        ColumnFeatureConfiguration config = context.config();

        if (!canPlaceAt(level, origin.mutable())) {
            return false;
        }

        int height = config.height().sample(random);
        boolean isClustered = random.nextFloat() < 0.9F;
        int maxReach = Math.min(height, isClustered ? CLUSTERED_REACH : UNCLUSTERED_REACH);
        int clusterSize = isClustered ? CLUSTERED_SIZE : UNCLUSTERED_SIZE;
        boolean placedAny = false;

        Map<BlockPos, Integer> highestBlocks = new HashMap<>();

        Iterable<BlockPos> randomPositions = BlockPos.randomBetweenClosed(
                random, clusterSize,
                origin.getX() - maxReach, origin.getY(), origin.getZ() - maxReach,
                origin.getX() + maxReach, origin.getY(), origin.getZ() + maxReach
        );

        for (BlockPos randomPos : randomPositions) {
            int distance = height - randomPos.distManhattan(origin);
            if (distance >= 0) {
                placedAny |= this.placeColumn(level, randomPos, distance, config.reach().sample(random), highestBlocks);
            }
        }

        for (Map.Entry<BlockPos, Integer> entry : highestBlocks.entrySet()) {
            BlockPos xzPos = entry.getKey();
            int highestY = entry.getValue();

            BlockPos holystonePos = new BlockPos(xzPos.getX(), highestY + 1, xzPos.getZ());

            if (isAirOrCloud(level, holystonePos) && random.nextFloat() > 0.75F) {
                this.setBlock(level, holystonePos, DABlocks.POINTED_HOLYSTONE.get().defaultBlockState());
            }
        }

        return placedAny;
    }

    private boolean placeColumn(LevelAccessor level, BlockPos pos, int distance, int reach, Map<BlockPos, Integer> highestBlocks) {
        boolean placedAny = false;

        Iterable<BlockPos> targetPositions = BlockPos.betweenClosed(
                pos.getX() - reach, pos.getY(), pos.getZ() - reach,
                pos.getX() + reach, pos.getY(), pos.getZ() + reach
        );

        for (BlockPos targetPos : targetPositions) {
            int manhattanDist = targetPos.distManhattan(pos);

            BlockPos startPos = isAirOrCloud(level, targetPos)
                    ? findSurface(level, targetPos.mutable(), distance)
                    : findAir(level, targetPos.mutable(), distance);

            if (startPos == null) {
                continue;
            }

            int columnHeight = distance - (manhattanDist / 2);
            BlockPos.MutableBlockPos mutablePos = startPos.mutable();

            for (; columnHeight >= 0; --columnHeight) {
                if (isAirOrCloud(level, mutablePos)) {
                    this.setBlock(level, mutablePos, AetherBlocks.HOLYSTONE.get().defaultBlockState());

                    // Update our map with the highest Y coordinate for this specific X,Z column
                    BlockPos xzPos = new BlockPos(mutablePos.getX(), 0, mutablePos.getZ());
                    int currentY = mutablePos.getY();
                    if (!highestBlocks.containsKey(xzPos) || highestBlocks.get(xzPos) < currentY) {
                        highestBlocks.put(xzPos, currentY);
                    }

                    mutablePos.move(Direction.UP);
                    placedAny = true;
                } else {
                    if (!level.getBlockState(mutablePos).is(AetherBlocks.HOLYSTONE.get())) {
                        break;
                    }
                    mutablePos.move(Direction.UP);
                }
            }
        }

        return placedAny;
    }

    @Nullable
    private static BlockPos findSurface(LevelAccessor level, BlockPos.MutableBlockPos pos, int distance) {
        while (pos.getY() > level.getMinBuildHeight() + 1 && distance > 0) {
            --distance;
            if (canPlaceAt(level, pos)) {
                return pos;
            }
            pos.move(Direction.DOWN);
        }
        return null;
    }

    private static boolean canPlaceAt(LevelAccessor level, BlockPos.MutableBlockPos pos) {
        if (!isAirOrCloud(level, pos)) {
            return false;
        }

        pos.move(Direction.DOWN);
        BlockState stateBelow = level.getBlockState(pos);
        pos.move(Direction.UP);

        return !stateBelow.isAir() && !CANNOT_PLACE_ON.contains(stateBelow.getBlock());
    }

    @Nullable
    private static BlockPos findAir(LevelAccessor level, BlockPos.MutableBlockPos pos, int distance) {
        while (pos.getY() < level.getMaxBuildHeight() && distance > 0) {
            --distance;
            BlockState state = level.getBlockState(pos);

            if (CANNOT_PLACE_ON.contains(state.getBlock())) {
                return null;
            }
            if (state.isAir()) {
                return pos;
            }
            pos.move(Direction.UP);
        }
        return null;
    }

    private static boolean isAirOrCloud(LevelAccessor level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.isAir() || state.is(AetherTags.Blocks.AERCLOUDS);
    }
}