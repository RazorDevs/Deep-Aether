package teamrazor.deepaether.block;

import com.gildedgames.aether.block.AetherBlockStateProperties;
import com.gildedgames.aether.block.natural.AetherLogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.init.DeepAetherModBlocks;

public class DeepAetherModLogBlock extends AetherLogBlock {
    public DeepAetherModLogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, false));
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(DeepAetherModBlocks.ROSE_LOG.get())) {
                return DeepAetherModBlocks.STRIPPED_ROSE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DeepAetherModBlocks.ROSE_WOOD.get())) {
                return DeepAetherModBlocks.STRIPPED_ROSE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DeepAetherModBlocks.YAGROOT_LOG.get())) {
                return DeepAetherModBlocks.STRIPPED_YAGROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DeepAetherModBlocks.YAGROOT_WOOD.get())) {
                return DeepAetherModBlocks.STRIPPED_YAGROOT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DeepAetherModBlocks.CRUDEROOT_LOG.get())) {
                return DeepAetherModBlocks.STRIPPED_CRUDEROOT_LOG.get().defaultBlockState();
            }
            if(state.is(DeepAetherModBlocks.CRUDEROOT_WOOD.get())) {
                return DeepAetherModBlocks.CRUDEROOT_WOOD.get().defaultBlockState();
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}