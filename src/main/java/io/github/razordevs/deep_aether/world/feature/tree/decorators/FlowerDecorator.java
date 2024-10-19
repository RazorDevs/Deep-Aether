package io.github.razordevs.deep_aether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FlowerDecorator extends TreeDecorator {

    public static final MapCodec<FlowerDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(FlowerDecorator::new, (decorator) -> decorator.probability).stable();

    private static final Direction WORLDGEN_FACING = Direction.SOUTH;
    private static final Direction[] SPAWN_DIRECTIONS = Direction.Plane.HORIZONTAL.stream().filter((direction) -> direction != WORLDGEN_FACING.getOpposite()).toArray(Direction[]::new);
    private final float probability;

    public FlowerDecorator(float v) {
        this.probability = v;
    }

    @NotNull
    protected TreeDecoratorType<?> type() {
        return DADecoratorType.FLOWERS.get();
    }

    public void place(Context context) {
        RandomSource randomsource = context.random();
        if (!(randomsource.nextFloat() >= this.probability)) {
            List<BlockPos> list = context.leaves();
            List<BlockPos> list1 = context.logs();
            int i = !list.isEmpty() ? Math.max(list.get(0).getY() - 1, list1.get(0).getY() + randomsource.nextInt(3)) : Math.min(list1.get(0).getY() + 1 + randomsource.nextInt(3), list1.get(list1.size() - 1).getY());
            List<BlockPos> list2 = list1.stream().filter((blockPos) -> blockPos.getY() == i).flatMap((posStream) -> Stream.of(SPAWN_DIRECTIONS).map(posStream::relative)).collect(Collectors.toList());
            if (!list2.isEmpty()) {
                Collections.shuffle(list2);
                Optional<BlockPos> optional = list2.stream().filter((air) -> context.isAir(air) && context.isAir(air.relative(WORLDGEN_FACING))).findFirst();
                optional.ifPresent(blockPos -> context.setBlock(blockPos, DABlocks.FLOWERING_ROSEROOT_LEAVES.get().defaultBlockState()));
            }
        }
    }
}
