package io.github.razordevs.deep_aether.item.gear.skyjade;

import io.github.razordevs.deep_aether.DeepAetherConfig;
import io.github.razordevs.deep_aether.block.misc.DisableSound;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface SkyjadeTool {
    default float decreaseSpeed(ItemStack stack, float speed) {
        return (float) (speed / (2.0 * ((double) stack.getDamageValue()) / ((double) stack.getMaxDamage()) + 0.5));
    }

    default void disableSound(Player player, BlockPos pos) {
        if(DeepAetherConfig.SERVER.enable_skyjade_rework.get())
            ((DisableSound) player.level().getBlockState(pos).getBlock()).deep_Aether$disableSound(true);
    }
}
