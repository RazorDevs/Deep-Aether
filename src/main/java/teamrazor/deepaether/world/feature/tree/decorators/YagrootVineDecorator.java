package teamrazor.deepaether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import teamrazor.deepaether.init.DABlocks;


public class YagrootVineDecorator extends TreeDecorator {

    public static final Codec<YagrootVineDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(YagrootVineDecorator::new, (p_226037_) -> {
        return p_226037_.probability;
    }).codec();

    private final float probability;

    @Override
    protected TreeDecoratorType<?> type() {
        return DADecoratorType.YAGVINES.get();
    }

    public YagrootVineDecorator(float p_226031_) {
        this.probability = p_226031_;
    }

    public void place(TreeDecorator.Context p_226039_) {
        RandomSource randomsource = p_226039_.random();
        p_226039_.leaves().forEach((p_226035_) -> {
            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos = p_226035_.west();
                if (p_226039_.isAir(blockpos)) {
                    addHangingVine(blockpos, VineBlock.EAST, p_226039_);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos1 = p_226035_.east();
                if (p_226039_.isAir(blockpos1)) {
                    addHangingVine(blockpos1, VineBlock.WEST, p_226039_);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos2 = p_226035_.north();
                if (p_226039_.isAir(blockpos2)) {
                    addHangingVine(blockpos2, VineBlock.SOUTH, p_226039_);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos3 = p_226035_.south();
                if (p_226039_.isAir(blockpos3)) {
                    addHangingVine(blockpos3, VineBlock.NORTH, p_226039_);
                }
            }

        });
    }

    private void addHangingVine(BlockPos p_226041_, BooleanProperty p_226042_, TreeDecorator.Context context) {
        placeVine(p_226041_, p_226042_, context);
        int i = 4;

        for(BlockPos blockpos = p_226041_.below(); context.isAir(blockpos) && i > 0; --i) {
            placeVine(blockpos, p_226042_, context);
            blockpos = blockpos.below();
        }

    }
    public void placeVine(BlockPos p_226065_, BooleanProperty p_226066_, TreeDecorator.Context context) {
        context.setBlock(p_226065_, DABlocks.YAGROOT_VINE.get().defaultBlockState().setValue(p_226066_, Boolean.valueOf(true)));
    }
}

