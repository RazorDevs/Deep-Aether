package teamrazor.deepaether.world.features.feature;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.data.resources.AetherFeatureStates;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import teamrazor.deepaether.world.features.configuration.CloudPillarConfiguration;

import java.util.ArrayList;
import java.util.List;

public class CloudPillarFeature  extends Feature<CloudPillarConfiguration> {

    public CloudPillarFeature(Codec<CloudPillarConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CloudPillarConfiguration> featurePlaceContext) {
        BlockPos blockpos = featurePlaceContext.origin();
        WorldGenLevel worldgenlevel = featurePlaceContext.level();
        RandomSource randomsource = featurePlaceContext.random();

        BlockState state = featurePlaceContext.config().blockstate().getState(randomsource, blockpos);

        Direction MainDirection = this.getRandom(randomsource);
        boolean SecondaryDirection = randomsource.nextBoolean();

        //IF SOUP
        for (int i = 0; i <= randomsource.nextInt(15); i++) {
            for (int ii = 0; ii <= 1; ii++) {

                blockpos = blockpos.above(1);

                blockpos = blockpos.relative(MainDirection);
                if (randomsource.nextInt(0, 4) == 0) {
                    if (SecondaryDirection)
                        blockpos = blockpos.relative(MainDirection.getClockWise());
                    else
                        blockpos = blockpos.relative(MainDirection.getCounterClockWise());
                }
                placeCircle(blockpos, worldgenlevel, randomsource, state);
            }
        }
        placeTopCircle(blockpos, worldgenlevel, randomsource, state);
        return true;
    }

    public void placeCircle(BlockPos blockPos, WorldGenLevel worldGenLevel, RandomSource randomSource, BlockState state) {
        int a;
        for (int ii = 1; ii <= 5; ii++) {
            if (ii == 1 || ii == 5)
                a = 3;
            else a = 5;

            for (int i = 1; i <= a; ) {
                i++;
                if (randomSource.nextInt(0, 5) > 0) {
                    this.setBlock(worldGenLevel, blockPos.east(i + 1).south(ii), state);
                }
            }
        }

    }





    public Direction getRandom(RandomSource random) {
        int a = random.nextInt(4);
        if(a == 0) return Direction.NORTH;
        else if(a == 1) return Direction.SOUTH;
        else if(a == 2) return Direction.WEST;
        else return Direction.EAST;
    }
    public void placeTopCircle(BlockPos blockPos, WorldGenLevel worldGenLevel, RandomSource randomSource, BlockState state) {
        for (int ii = 1; ii <= 3; ii++) {
            for (int i = 1; i <= 3; ) {
                i++;
                if (randomSource.nextInt(0, 5) > 0) {
                    this.setBlock(worldGenLevel, blockPos.east(i + 1).south(ii + 1), state);
                }
            }
        }
    }
}
