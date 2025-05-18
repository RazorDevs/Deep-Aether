package teamrazor.deepaether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import teamrazor.deepaether.block.SunrootHangerBlock;
import teamrazor.deepaether.init.DABlocks;


public class SunrootHangerDecorator extends TreeDecorator {
    public static final Codec<SunrootHangerDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(SunrootHangerDecorator::new, (vineDecorator) -> vineDecorator.probability).codec();

    private final float probability;

    @Override
    protected TreeDecoratorType<?> type() {
        return DADecoratorType.SUNROOT_HANGER.get();
    }

    public SunrootHangerDecorator(float probability) {
        this.probability = probability;
    }

    public void place(Context context) {
        RandomSource randomsource = context.random();
        context.leaves().forEach((blockPos) -> {
            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos = blockPos.below();
                if (context.isAir(blockpos)) {
                    addHangingVine(blockpos, context);
                }
            }
        });
    }

    private void addHangingVine(BlockPos blockPos, Context context) {
        for(int i = 0; context.isAir(blockPos.below(i)) && i < 5; i++) {
            if(i != 4 && context.isAir(blockPos.below(i+1))) {
                placeVine(blockPos.below(i), context, DABlocks.SUNROOT_HANGER.get().defaultBlockState().setValue(SunrootHangerBlock.BOTTOM, false));
            }
            else placeVine(blockPos.below(i), context, DABlocks.SUNROOT_HANGER.get().defaultBlockState().setValue(SunrootHangerBlock.BOTTOM, true));
        }

    }
    public void placeVine(BlockPos blockPos, Context context, BlockState state) {
        context.setBlock(blockPos, state);
    }
}

