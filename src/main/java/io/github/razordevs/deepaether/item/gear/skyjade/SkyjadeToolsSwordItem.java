
package io.github.razordevs.deepaether.deepaether.item.gear.skyjade;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import io.github.razordevs.deepaether.deepaether.block.misc.DisableSound;
import io.github.razordevs.deepaether.deepaether.init.DATiers;

public class SkyjadeToolsSwordItem extends SwordItem {
	public SkyjadeToolsSwordItem() {
		super(DATiers.SKYJADE, 3, -3f, new Properties());
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		((DisableSound) player.level().getBlockState(pos).getBlock()).deep_Aether$disableSound(true);
		return false;
	}
}
