package io.github.razordevs.deep_aether.block.natural;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.natural.AetherDoubleDropBlock;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class AetherCoarseDirtBlock extends AetherDoubleDropBlock {
    public AetherCoarseDirtBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof HoeItem) {
            return AetherBlocks.AETHER_DIRT.get().defaultBlockState();
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}