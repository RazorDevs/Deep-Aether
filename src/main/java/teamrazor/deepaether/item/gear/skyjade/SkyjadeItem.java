
package teamrazor.deepaether.item.gear.skyjade;

import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;

public class SkyjadeItem extends Item {
	public SkyjadeItem() {
		super(new Properties().stacksTo(64).rarity(Rarity.COMMON).tab(AetherCreativeTabs.AETHER_INGREDIENTS));
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
