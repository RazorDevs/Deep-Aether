
package io.github.razordevs.deep_aether.item.gear.skyjade;

import io.github.razordevs.deep_aether.block.misc.DisableSound;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SkyjadeToolsAxeItem extends AxeItem {
	public SkyjadeToolsAxeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
		((DisableSound) player.level().getBlockState(pos).getBlock()).deep_Aether$disableSound(true);
		return super.canAttackBlock(state, level, pos, player);
	}
}
