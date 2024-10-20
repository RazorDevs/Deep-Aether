package io.github.razordevs.deep_aether.item.gear;

import com.google.common.collect.ImmutableMap;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Map;

public class DAAbilityHooks {

    public static class ToolHooks {
        public static final Map<Block, Block> STRIPPABLES = (new ImmutableMap.Builder<Block, Block>())
                .put(DABlocks.ROSEROOT_LOG.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get())
                .put(DABlocks.ROSEROOT_WOOD.get(), DABlocks.STRIPPED_ROSEROOT_WOOD.get())
                .put(DABlocks.YAGROOT_LOG.get(), DABlocks.STRIPPED_YAGROOT_LOG.get())
                .put(DABlocks.YAGROOT_WOOD.get(), DABlocks.STRIPPED_YAGROOT_WOOD.get())
                .build();

        public static BlockState setupToolActions(BlockState old, ItemAbility action) {
            Block oldBlock = old.getBlock();
            if (action == ItemAbilities.AXE_STRIP) {
                if (STRIPPABLES.containsKey(oldBlock)) {
                    return STRIPPABLES.get(oldBlock).withPropertiesOf(old);
                }
            }
            return old;
        }
    }
}
