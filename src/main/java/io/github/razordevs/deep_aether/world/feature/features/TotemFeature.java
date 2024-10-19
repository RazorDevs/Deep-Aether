package io.github.razordevs.deep_aether.world.feature.features;

import com.aetherteam.aether.block.AetherBlocks;
import com.mojang.serialization.Codec;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class TotemFeature extends Feature<NoneFeatureConfiguration> {

    private static int excludedValue = 0;

    public TotemFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel reader = context.level();
        RandomSource rand = context.random();
        BlockPos pos = context.origin();

        //Length of the fallen log
        int height = rand.nextInt(2, 4);

        //Chooses a random direction
        Direction direction = getRandomDirectionYExcluded(rand);

        //Checks if the feature can be placed first.
        if(!canPlace(reader, pos) || canPlace(reader, pos.below()))
            return false;


        for (int i = 0; i < height; i++) {
            if(canPlace(reader, pos)) {
                //Places the blocks
                this.setBlock(reader, pos, getRandomBlock(rand, false).defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
                pos = pos.above();
            }
        }

        return true;
    }

    public boolean canPlace(LevelReader reader, BlockPos pos) {
        BlockState state = reader.getBlockState(pos);
        BlockState below = reader.getBlockState(pos.below());
        return (reader.isEmptyBlock(pos) || state.is(BlockTags.LEAVES) || state.canBeReplaced() || !state.isCollisionShapeFullBlock(reader, pos)) && below.is(AetherBlocks.AETHER_GRASS_BLOCK);
    }

    public Block getRandomBlock(RandomSource random, boolean log){
        return switch(randomNonRepeatedInteger(random, 4)){
            case 0 -> DABlocks.MOA_TOTEM.get();
            case 1 -> DABlocks.ZEPHYR_TOTEM.get();
            case 2 -> DABlocks.AERWHALE_TOTEM.get();
            default -> log ? AetherBlocks.SKYROOT_LOG.get() : DABlocks.ZEPHYR_TOTEM.get();
        };
    }

    public static Direction getRandomDirectionYExcluded(RandomSource random) {
        return switch(random.nextInt(4)){
            case 0 -> Direction.NORTH;
            case 1 -> Direction.SOUTH;
            case 2 -> Direction.EAST;
            default -> Direction.WEST;
        };
    }

    /**
     * Returns a randomized integer different from the previously picked value.
     * <p>
     * After a new value is selected, the old one will be able to be picked, and
     * the current will not be available at the next call.
     */
    private int randomNonRepeatedInteger(RandomSource random, int bound){
        int choice;
        do {
            choice = random.nextInt(bound);
        } while(choice == excludedValue);
        excludedValue = choice;
        return choice;
    }
}
