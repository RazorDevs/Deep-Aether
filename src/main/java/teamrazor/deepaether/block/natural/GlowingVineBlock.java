package teamrazor.deepaether.block.natural;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.init.DAItems;

public class GlowingVineBlock extends VineBlock {
    public GlowingVineBlock(Properties p_57847_) {
        super(p_57847_);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof ShearsItem) {
            popResource(context.getLevel(), context.getClickedPos(), new ItemStack(DAItems.GLOWING_SPORES.get()));
            return Blocks.VINE.defaultBlockState().setValue(UP, state.getValue(UP))
                    .setValue(NORTH, state.getValue(NORTH))
                    .setValue(EAST, state.getValue(EAST))
                    .setValue(SOUTH, state.getValue(SOUTH))
                    .setValue(WEST, state.getValue(WEST));
        }
        else return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
