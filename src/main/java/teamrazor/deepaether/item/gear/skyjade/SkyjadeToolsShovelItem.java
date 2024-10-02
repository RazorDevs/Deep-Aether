
package teamrazor.deepaether.item.gear.skyjade;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import teamrazor.deepaether.DeepAetherConfig;
import teamrazor.deepaether.block.misc.DisableSound;

public class SkyjadeToolsShovelItem extends ShovelItem {
	public SkyjadeToolsShovelItem(Tier tier, int i, float v, Properties properties) {
		super(tier, i, v, properties);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		((DisableSound) player.level().getBlockState(pos).getBlock()).deep_Aether$disableSound(true);
		return false;
	}
}
