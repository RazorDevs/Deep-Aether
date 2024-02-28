package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.StemGrownBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.init.DABlocks;

import java.util.Random;
import java.util.function.Supplier;

public class AttachedSquashStemBlock extends AttachedStemBlock {

    public AttachedSquashStemBlock(Supplier<Item> itemSupplier, Properties properties) {
        super(DABlocks.BLUE_SQUASH.get(), itemSupplier, properties);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        StemGrownBlock fruit = randomizedSquash().get();
        return !blockState1.is(fruit) && direction == blockState.getValue(FACING) ? fruit.getStem().defaultBlockState().setValue(StemBlock.AGE, Integer.valueOf(7)) : super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);
    }

    private RegistryObject<SquashBlock> randomizedSquash(){
        return new Random().nextBoolean() ? DABlocks.BLUE_SQUASH : DABlocks.GREEN_SQUASH;
    }
}
