
package teamrazor.deepaether.item;

import teamrazor.deepaether.init.DeepAetherModTabs;

import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class SkyjadeItem extends Item {
	public SkyjadeItem() {
		super(new Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(64).rarity(Rarity.COMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
