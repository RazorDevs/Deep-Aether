
package io.github.razordevs.deep_aether.item.gear.skyjade;

import io.github.razordevs.deep_aether.block.misc.DisableSound;
import io.github.razordevs.deep_aether.init.DATiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SkyjadeToolsSwordItem extends SwordItem {
	public SkyjadeToolsSwordItem() {
		super(DATiers.SKYJADE, new Properties().attributes(SwordItem.createAttributes(DATiers.SKYJADE, 3, -3f)));
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
		((DisableSound) player.level().getBlockState(pos).getBlock()).deep_Aether$disableSound(true);
		return super.canAttackBlock(state, level, pos, player);
	}
}
