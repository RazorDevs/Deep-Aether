package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.init.DABlocks;

import java.util.Random;
import java.util.function.Supplier;

public class SquashStemBlock extends StemBlock {

    public SquashStemBlock(Supplier<Item> itemSupplier, Properties properties) {
        super((StemGrownBlock) DABlocks.BLUE_SQUASH.get(), itemSupplier, properties);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        StemGrownBlock fruit = randomizedSquash().get();
        if (!level.isAreaLoaded(blockPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (level.getRawBrightness(blockPos, 0) >= 9) {
            float f = CropBlock.getGrowthSpeed(this, level, blockPos);
            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, blockPos, blockState, randomSource.nextInt((int)(25.0F / f) + 1) == 0)) {
                int i = blockState.getValue(AGE);
                if (i < 7) {
                    level.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(i + 1)), 2);
                } else {
                    Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
                    BlockPos blockpos = blockPos.relative(direction);
                    BlockState blockstate = level.getBlockState(blockpos.below());
                    if (level.isEmptyBlock(blockpos) && (blockstate.canSustainPlant(level, blockpos.below(), Direction.UP, fruit) || blockstate.is(Blocks.FARMLAND) || blockstate.is(BlockTags.DIRT))) {
                        level.setBlockAndUpdate(blockpos, fruit.defaultBlockState());
                        level.setBlockAndUpdate(blockPos, fruit.getAttachedStem().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
                    }
                }
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, blockPos, blockState);
            }
        }
    }

    private RegistryObject<SquashBlock> randomizedSquash(){
        return new Random().nextBoolean() ? DABlocks.BLUE_SQUASH : DABlocks.GREEN_SQUASH;
    }
}
