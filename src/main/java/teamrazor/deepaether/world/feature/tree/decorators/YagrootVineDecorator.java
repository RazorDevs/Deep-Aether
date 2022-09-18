package teamrazor.deepaether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import teamrazor.deepaether.block.DeepAetherVineBlock;
import teamrazor.deepaether.init.DeepAetherModBlocks;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class YagrootVineDecorator extends TreeDecorator {

    public static final Codec<YagrootVineDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(YagrootVineDecorator::new, (p_226037_) -> {
        return p_226037_.probability;
    }).codec();

    private final float probability;

    @Override
    protected TreeDecoratorType<?> type() {
        return DeepAetherDecoratorType.YAGVINES.get();
    }

    public YagrootVineDecorator(float p_226031_) {
        this.probability = p_226031_;
    }

    @Override
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

    private static void addHangingVine(BlockPos p_226041_, BooleanProperty p_226042_, TreeDecorator.Context p_226043_) {
        Context test = YagrootVineDecorator.Context.class.cast(p_226043_);
        test.placeVine(p_226041_, p_226042_);
        int i = 4;

        for(BlockPos blockpos = p_226041_.below(); p_226043_.isAir(blockpos) && i > 0; --i) {
            test.placeVine(blockpos, p_226042_);
            blockpos = blockpos.below();
        }
    }

    public static class Context {
        private final LevelSimulatedReader level;
        private final BiConsumer<BlockPos, BlockState> decorationSetter;
        private final RandomSource random;
        private final ObjectArrayList<BlockPos> logs;
        private final ObjectArrayList<BlockPos> leaves;
        private final ObjectArrayList<BlockPos> roots;

        public Context(LevelSimulatedReader p_226052_, BiConsumer<BlockPos, BlockState> p_226053_, RandomSource p_226054_, Set<BlockPos> p_226055_, Set<BlockPos> p_226056_, Set<BlockPos> p_226057_) {
            this.level = p_226052_;
            this.decorationSetter = p_226053_;
            this.random = p_226054_;
            this.roots = new ObjectArrayList<>(p_226057_);
            this.logs = new ObjectArrayList<>(p_226055_);
            this.leaves = new ObjectArrayList<>(p_226056_);
            this.logs.sort(Comparator.comparingInt(Vec3i::getY));
            this.leaves.sort(Comparator.comparingInt(Vec3i::getY));
            this.roots.sort(Comparator.comparingInt(Vec3i::getY));
        }

        public void placeVine(BlockPos p_226065_, BooleanProperty p_226066_) {
            this.setBlock(p_226065_, DeepAetherModBlocks.YAGROOT_VINE.get().defaultBlockState().setValue(p_226066_, Boolean.valueOf(true)));
        }

        public void setBlock(BlockPos p_226062_, BlockState p_226063_) {
            this.decorationSetter.accept(p_226062_, p_226063_);
        }

        public boolean isAir(BlockPos p_226060_) {
            return this.level.isStateAtPosition(p_226060_, BlockBehaviour.BlockStateBase::isAir);
        }

        public LevelSimulatedReader level() {
            return this.level;
        }

        public RandomSource random() {
            return this.random;
        }

        public ObjectArrayList<BlockPos> logs() {
            return this.logs;
        }

        public ObjectArrayList<BlockPos> leaves() {
            return this.leaves;
        }

        public ObjectArrayList<BlockPos> roots() {
            return this.roots;
        }
    }
}
