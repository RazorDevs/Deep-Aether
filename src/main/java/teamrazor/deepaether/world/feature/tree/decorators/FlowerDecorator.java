package teamrazor.deepaether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlowerDecorator extends TreeDecorator {

    private final float probability;

    public FlowerDecorator(float p_69958_) {
        this.probability = p_69958_;
    }

    protected TreeDecoratorType<?> type() {
        return TreeDecoratorType.ATTACHED_TO_LEAVES;
    }

    public void place(TreeDecorator.Context p_226019_) {
        RandomSource randomsource = p_226019_.random();
        if (!(randomsource.nextFloat() >= this.probability)) {
            List<BlockPos> list = p_226019_.leaves();
            List<BlockPos> list1 = p_226019_.logs();
            int i = !list.isEmpty() ? Math.max(list.get(0).getY() - 1, list1.get(0).getY() + 1) : Math.min(list1.get(0).getY() + 2 + randomsource.nextInt(3), list1.get(list1.size() - 1).getY());
        }
    }

}
