package teamrazor.deepaether.block.building;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.init.DABlocks;

public class DAWallBlock extends WallBlock {
    public DAWallBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(DABlocks.ROSEROOT_WALL.get())) {
                return DABlocks.STRIPPED_ROSEROOT_WALL.get().defaultBlockState();
            }
            if(state.is(DABlocks.ROSEROOT_LOG_WALL.get())) {
                return DABlocks.STRIPPED_ROSEROOT_LOG_WALL.get().defaultBlockState();
            }
            if(state.is(DABlocks.YAGROOT_WALL.get())) {
                return DABlocks.STRIPPED_YAGROOT_WALL.get().defaultBlockState();
            }
            if(state.is(DABlocks.YAGROOT_LOG_WALL.get())) {
                return DABlocks.STRIPPED_YAGROOT_LOG_WALL.get().defaultBlockState();
            }
            if(state.is(DABlocks.CRUDEROOT_WALL.get())) {
                return DABlocks.STRIPPED_CRUDEROOT_WALL.get().defaultBlockState();
            }
            if(state.is(DABlocks.CRUDEROOT_LOG_WALL.get())) {
                return DABlocks.STRIPPED_CRUDEROOT_LOG_WALL.get().defaultBlockState();
            }
            if(state.is(DABlocks.CONBERRY_WALL.get())) {
                return DABlocks.STRIPPED_CONBERRY_WALL.get().defaultBlockState();
            }
            if(state.is(DABlocks.CONBERRY_LOG_WALL.get())) {
                return DABlocks.STRIPPED_CONBERRY_LOG_WALL.get().defaultBlockState();
            }
            if(state.is(DABlocks.SUNROOT_WALL.get())) {
                return DABlocks.STRIPPED_SUNROOT_WALL.get().defaultBlockState();
            }
            if(state.is(DABlocks.SUNROOT_LOG_WALL.get())) {
                return DABlocks.STRIPPED_SUNROOT_LOG_WALL.get().defaultBlockState();
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
