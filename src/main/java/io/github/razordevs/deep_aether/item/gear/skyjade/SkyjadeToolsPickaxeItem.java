
package io.github.razordevs.deep_aether.item.gear.skyjade;

import io.github.razordevs.deep_aether.block.misc.DisableSound;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class SkyjadeToolsPickaxeItem extends PickaxeItem {

	public SkyjadeToolsPickaxeItem(Tier tier, int i, float v, Properties properties) {
		super(tier, i, v, properties);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		((DisableSound) player.level().getBlockState(pos).getBlock()).deep_Aether$disableSound(true);
		return false;
	}
}
