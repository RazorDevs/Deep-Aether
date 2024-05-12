package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.registries.DeferredBlock;
import teamrazor.deepaether.init.DABlocks;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class SquashStemBlock extends StemBlock {

    private final ResourceKey<Block> attachedStem;

    public SquashStemBlock(ResourceKey<Block> fruit, ResourceKey<Block> stem, ResourceKey<Item> seed, Properties properties) {
        super(fruit, stem, seed, properties);
        this.attachedStem = stem;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        if (!level.isAreaLoaded(blockPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (level.getRawBrightness(blockPos, 0) >= 9) {
            float f = CropBlock.getGrowthSpeed(this, level, blockPos);
            if (net.neoforged.neoforge.common.CommonHooks.onCropsGrowPre(level, blockPos, blockState, randomSource.nextInt((int)(25.0F / f) + 1) == 0)) {
                int i = blockState.getValue(AGE);
                if (i < 7) {
                    level.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(i + 1)), 2);
                } else {
                    Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
                    BlockPos blockpos = blockPos.relative(direction);
                    BlockState blockstate = level.getBlockState(blockpos.below());
                    if (level.isEmptyBlock(blockpos) && (blockstate.is(Blocks.FARMLAND) || blockstate.is(BlockTags.DIRT))) { // TODO 1.20.3 PORTING: reimplement canSustainPlant check
                        Registry<Block> registry = level.registryAccess().registryOrThrow(Registries.BLOCK);
                        Optional<Block> optional = registry.getOptional(this.randomizedSquash().getKey());
                        Optional<Block> optional1 = registry.getOptional(this.attachedStem);
                        if (optional.isPresent() && optional1.isPresent()) {
                            level.setBlockAndUpdate(blockpos, optional.get().defaultBlockState());
                            level.setBlockAndUpdate(blockPos, optional1.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction));
                        }
                    }
                }
                net.neoforged.neoforge.common.CommonHooks.onCropsGrowPost(level, blockPos, blockState);
            }
        }
    }

    private DeferredBlock<Block> randomizedSquash(){
        return new Random().nextBoolean() ? DABlocks.BLUE_SQUASH : DABlocks.GREEN_SQUASH;
    }
}
