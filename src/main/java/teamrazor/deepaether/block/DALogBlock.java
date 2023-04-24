package teamrazor.deepaether.block;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.natural.AetherLogBlock;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.init.DABlocks;

public class DALogBlock extends AetherLogBlock {
    public DALogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, false));
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(DABlocks.ROSEROOT_LOG.get())) {
                return DABlocks.STRIPPED_ROSEROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.ROSEROOT_WOOD.get())) {
                return DABlocks.STRIPPED_ROSEROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.YAGROOT_LOG.get())) {
                return DABlocks.STRIPPED_YAGROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.YAGROOT_WOOD.get())) {
                return DABlocks.STRIPPED_YAGROOT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.CRUDEROOT_LOG.get())) {
                return DABlocks.STRIPPED_CRUDEROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.CRUDEROOT_WOOD.get())) {
                return DABlocks.CRUDEROOT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.AMBERROOT_LOG.get())) {
                return DABlocks.STRIPPED_AMBERROOT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(DABlocks.AMBERROOT_WOOD.get())) {
                return DABlocks.STRIPPED_AMBERROOT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}