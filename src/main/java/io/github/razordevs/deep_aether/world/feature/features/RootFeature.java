package io.github.razordevs.deep_aether.world.feature.features;

import com.aetherteam.aether.world.configuration.AercloudConfiguration;
import com.mojang.serialization.Codec;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class RootFeature extends Feature<AercloudConfiguration> {


    public RootFeature(Codec<AercloudConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<AercloudConfiguration> featurePlaceContext) {
        RandomSource random = featurePlaceContext.random();
        WorldGenLevel level = featurePlaceContext.level();
        BlockPos pos = featurePlaceContext.origin();

        int intA;
        int intB;
        int intC;
        int intD;
        int maxRootLength;

        //Max length of roots without moving down
        int maxFlatRootLength;
        Direction direction;
        if(level.isEmptyBlock(pos.below()) || !level.isEmptyBlock(pos))
            return false;

        this.setBlock(level, pos, DABlocks.AERCLOUD_ROOTS.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        for (int i = 1; i <= 2; i++) {
            intA = 0;
            intB = 0;
            intC = 0;
            intD = 0;
            maxRootLength = random.nextInt(15,30);
            maxFlatRootLength = 15;

            int offset = random.nextInt(3);
            maxRootLength -= offset;

            if(i==1) direction = Direction.SOUTH;
            else direction = Direction.EAST;
            pos = pos.relative(direction, offset);
            Direction opposite = this.getOpposite(direction);

            for (int j = 0; j < maxRootLength && intC < maxFlatRootLength; j++) {
                if (level.isEmptyBlock(pos.below(intB + 1).relative(direction,intA).relative(opposite, intD))) {
                    intB++;
                    intC = 0;
                }
                else if (level.isEmptyBlock(pos.below(intB).relative(direction,intA+1).relative(opposite, intD))) {
                    if(random.nextInt(7) == 1) {
                        level.isEmptyBlock(pos.below(intB - 1).relative(direction,intA+1).relative(opposite, intD+1));
                        intD++;
                    }
                    intA++;
                    intC++;
                }
                else if(level.isEmptyBlock(pos.below(intB - 1).relative(direction,intA+1).relative(opposite, intD))) {

                    intA++;
                    intB--;
                    this.setBlock(level, pos.below(intB).relative(direction, intA-1).relative(opposite, intD), DABlocks.AERCLOUD_ROOTS.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
                }
                this.setBlock(level, pos.below(intB).relative(direction, intA).relative(opposite, intD), DABlocks.AERCLOUD_ROOTS.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
            }
        }
        return true;
    }

    private Direction getOpposite(Direction direction) {
        if(direction == Direction.EAST)
            return Direction.SOUTH;
        else return Direction.EAST;
    }
}