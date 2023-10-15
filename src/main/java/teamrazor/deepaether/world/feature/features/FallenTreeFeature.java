package teamrazor.deepaether.world.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import teamrazor.deepaether.world.feature.features.configuration.FallenTreeConfiguration;

public class FallenTreeFeature extends Feature<FallenTreeConfiguration> {
    public FallenTreeFeature(Codec<FallenTreeConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<FallenTreeConfiguration> context) {
        WorldGenLevel reader = context.level();
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        FallenTreeConfiguration config = context.config();
        BlockState block = config.block().getState(context.random(), pos);

        //Length of the fallen log
        int length = rand.nextInt(config.min(), config.max());

        //Gap between the log and the stump
        int gap = rand.nextInt(2,4);
        final int MAX_DEPTH = 4;

        //Chooses a random position
        Direction direction = Direction.getRandom(rand);

        //Checks if the feature can be placed first.
        if(!canPLace(reader, pos))
            return false;

        //Adds the gap
        pos = pos.relative(direction, gap);


        //First block after the gap should have a block below it.
        if(canPLace(reader, pos.below()))
            return false;

            //The log has a chance to follow the terrain downwards
        boolean follow_terrain = rand.nextBoolean();

        //Giant Check:
        if(follow_terrain) {
            BlockPos tempPos = pos;

            for (int i = 0; i < length; i++) {

                //The log can fall down a maximum of three blocks per block
                if(canPLace(reader, tempPos.relative(direction, i).below()) && canPLace(reader, tempPos.relative(direction, i))) {
                    boolean f = false;
                    for (int ii = 1; ii < MAX_DEPTH; ii++) {
                        if (!canPLace(reader, tempPos.relative(direction, i).below(ii))) {
                            tempPos = tempPos.below(ii - 1);
                            f = true;
                        }
                    }
                    if(!f)
                        return false;
                }
                else if (!canPLace(reader, tempPos.relative(direction, i)))
                    return false;
            }
        }
        else {
            int posWithoutBlockBelow = 0;


            for (int i = 0; i < length; i++) {
                if (!canPLace(reader, pos.relative(direction, i)))
                    return false;

                if (canPLace(reader, pos.relative(direction, i).below()))
                    posWithoutBlockBelow++;
                else posWithoutBlockBelow = 0;

                if(posWithoutBlockBelow > 2)
                    return false;
            }
        }

        //Places the log after the check:
        this.setBlock(reader, pos.relative(direction.getOpposite(), gap), block);

        if(follow_terrain) {
            for (int i = 0; i < length; i++) {
                if(canPLace(reader, pos.relative(direction, i).below())) {

                    //The log can fall down a maximum of tree blocks per block
                    for (int ii = 1; ii < MAX_DEPTH; ii++) {
                        if (!canPLace(reader, pos.relative(direction, i).below(ii))) {
                            pos = pos.below(ii - 1);
                        }
                    }
                }
                this.setBlock(reader, pos.relative(direction, i), block.setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
                addDecorators(reader, pos, config.decorators().getState(context.random(), pos), context.random(), direction);
            }
        }
        else {
            for (int i = 0; i < length; i++) {
                this.setBlock(reader, pos.relative(direction, i), block.setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
                addDecorators(reader, pos, config.decorators().getState(context.random(), pos), context.random(), direction);
            }
        }

        return true;
    }

    public boolean canPLace(LevelReader reader, BlockPos pos) {
        BlockState state = reader.getBlockState(pos);
        if(reader.isEmptyBlock(pos) || state.is(BlockTags.LEAVES) || state.canBeReplaced() || !state.isCollisionShapeFullBlock(reader, pos))
            return true;
        else return false;
    }
    public void addDecorators(WorldGenLevel reader, BlockPos pos, BlockState block, RandomSource random, Direction direction) {
        if(random.nextInt(7) == 1) {
            if (canPLace(reader, pos.above()))
                this.setBlock(reader, pos.above(), block);
        }

        if(random.nextInt(7) == 1) {
            if (canPLace(reader, pos.relative(direction.getClockWise())) && !canPLace(reader, pos.relative(direction.getClockWise()).below()))
                this.setBlock(reader, pos.relative(direction.getClockWise()), block);
        }

        if(random.nextInt(7) == 1) {
            if (canPLace(reader, pos.relative(direction.getCounterClockWise())) && !canPLace(reader, pos.relative(direction.getCounterClockWise()).below()))
                this.setBlock(reader, pos.relative(direction.getClockWise()), block);
        }
    }
}