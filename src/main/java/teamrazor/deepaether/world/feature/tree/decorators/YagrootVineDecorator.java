package teamrazor.deepaether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamrazor.deepaether.init.DABlocks;


public class YagrootVineDecorator extends TreeDecorator {
    //private static final Logger LOGGER = LogManager.getLogger();
    public static final Codec<YagrootVineDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(YagrootVineDecorator::new, (vineDecorator) -> vineDecorator.probability).codec();

    private final float probability;

    @Override
    protected TreeDecoratorType<?> type() {
        return DADecoratorType.YAGVINES.get();
    }

    public YagrootVineDecorator(float probability) {
        this.probability = probability;
    }

    public void place(TreeDecorator.Context context) {
        //LOGGER.debug("Placing Yagroot vines...");
        RandomSource randomsource = context.random();
        context.leaves().forEach((blockPos) -> {
            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos = blockPos.west();
                if (context.isAir(blockpos)) {
                    //LOGGER.debug("Adding hanging vine to the west of {}", blockpos);
                    addHangingVine(blockpos, VineBlock.EAST, context);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos1 = blockPos.east();
                if (context.isAir(blockpos1)) {
                    //LOGGER.debug("Adding hanging vine to the east of {}", blockpos1);
                    addHangingVine(blockpos1, VineBlock.WEST, context);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos2 = blockPos.north();
                if (context.isAir(blockpos2)) {
                    //LOGGER.debug("Adding hanging vine to the north of {}", blockpos2);
                    addHangingVine(blockpos2, VineBlock.SOUTH, context);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos3 = blockPos.south();
                if (context.isAir(blockpos3)) {
                    //LOGGER.debug("Adding hanging vine to the south of {}", blockpos3);
                    addHangingVine(blockpos3, VineBlock.NORTH, context);
                }
            }

        });
    }

    private void addHangingVine(BlockPos blockPos, BooleanProperty property, TreeDecorator.Context context) {
        //LOGGER.debug("Adding hanging vine to {}", blockPos);
        placeVine(blockPos, property, context);
        int i = 4;

        for(BlockPos blockpos = blockPos.below(); context.isAir(blockpos) && i > 0; --i) {
            //LOGGER.debug("Adding hanging vine to {}", blockPos);
            placeVine(blockpos, property, context);
            blockpos = blockpos.below();
        }

    }
    public void placeVine(BlockPos blockPos, BooleanProperty property, TreeDecorator.Context context) {
        context.setBlock(blockPos, DABlocks.YAGROOT_VINE.get().defaultBlockState().setValue(property, Boolean.valueOf(true)));
    }
}

