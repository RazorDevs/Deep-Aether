package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MudBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import teamrazor.deepaether.init.DeepAetherModBlocks;

import javax.annotation.Nullable;

public class AetherMudBricks extends MudBlock {
    public AetherMudBricks(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return false;
    }


    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof BottleItem) {
            if(state.is(DeepAetherModBlocks.ROSE_LOG.get())) {
                return DeepAetherModBlocks.STRIPPED_ROSE_LOG.get().defaultBlockState();
            }
            if(state.is(DeepAetherModBlocks.ROSE_WOOD.get())) {
                return DeepAetherModBlocks.STRIPPED_ROSE_LOG.get().defaultBlockState();
            }
            if(state.is(DeepAetherModBlocks.YAGROOT_LOG.get())) {
                return DeepAetherModBlocks.STRIPPED_YAGROOT_LOG.get().defaultBlockState();
            }
            if(state.is(DeepAetherModBlocks.YAGROOT_WOOD.get())) {
                return DeepAetherModBlocks.STRIPPED_YAGROOT_WOOD.get().defaultBlockState();
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
