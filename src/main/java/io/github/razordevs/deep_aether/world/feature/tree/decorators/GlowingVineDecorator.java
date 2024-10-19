package io.github.razordevs.deep_aether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;


public class GlowingVineDecorator extends TreeDecorator {
    public static final MapCodec<GlowingVineDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(GlowingVineDecorator::new, p_226037_ -> p_226037_.probability)
            .stable();
    private final float probability;

    @Override
    protected TreeDecoratorType<?> type() {
        return DADecoratorType.GLOWING_VINE.get();
    }

    public GlowingVineDecorator(float p_226031_) {
        this.probability = p_226031_;
    }

    @Override
    public void place(TreeDecorator.Context pContext) {
        RandomSource randomsource = pContext.random();
        pContext.leaves().forEach(p_226035_ -> {
            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos = p_226035_.west();
                if (pContext.isAir(blockpos)) {
                    addHangingVine(blockpos, VineBlock.EAST, pContext);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos1 = p_226035_.east();
                if (pContext.isAir(blockpos1)) {
                    addHangingVine(blockpos1, VineBlock.WEST, pContext);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos2 = p_226035_.north();
                if (pContext.isAir(blockpos2)) {
                    addHangingVine(blockpos2, VineBlock.SOUTH, pContext);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos3 = p_226035_.south();
                if (pContext.isAir(blockpos3)) {
                    addHangingVine(blockpos3, VineBlock.NORTH, pContext);
                }
            }
        });
    }

    private static void addHangingVine(BlockPos pPos, BooleanProperty pSideProperty, TreeDecorator.Context pContext) {
        Block vine = getRandomVine(pContext.random());

        placeVine(pPos, pSideProperty, pContext, vine);
        int i = 4;

        for(BlockPos blockpos = pPos.below(); pContext.isAir(blockpos) && i > 0; --i) {
            placeVine(blockpos, pSideProperty, pContext, vine);
            blockpos = blockpos.below();
        }
    }

    private static Block getRandomVine(RandomSource random) {
        if(random.nextInt(5)==1)
            return DABlocks.GLOWING_VINE.get();
        else return Blocks.VINE;
    }

    private static void placeVine(BlockPos pPos, BooleanProperty pSideProperty, TreeDecorator.Context context, Block block) {
        context.setBlock(pPos, block.defaultBlockState().setValue(pSideProperty, Boolean.valueOf(true)));
    }
}

