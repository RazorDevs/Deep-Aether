
package io.github.razordevs.deep_aether.item.gear.skyjade;

import io.github.razordevs.deep_aether.DeepAetherConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SkyjadeToolsHoeItem extends HoeItem implements SkyjadeTool{
	public SkyjadeToolsHoeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
		this.disableSound(player, pos);
		return super.canAttackBlock(state, level, pos, player);
	}

	@Override
	public boolean isEnchantable(ItemStack itemStack) {
		return DeepAetherConfig.SERVER.skyjade_enchant.get() && !DeepAetherConfig.SERVER.enable_skyjade_rework.get();
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return DeepAetherConfig.SERVER.skyjade_enchant.get() && !DeepAetherConfig.SERVER.enable_skyjade_rework.get();
	}
}
