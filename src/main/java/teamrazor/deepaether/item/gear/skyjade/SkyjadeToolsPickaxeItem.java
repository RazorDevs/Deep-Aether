
package teamrazor.deepaether.item.gear.skyjade;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import teamrazor.deepaether.DeepAetherConfig;

public class SkyjadeToolsPickaxeItem extends PickaxeItem implements SkyjadeTool {
	public SkyjadeToolsPickaxeItem(Tier tier, int i, float v, Properties properties) {
		super(tier, i, v, properties);
	}

	@Override
	public boolean isEnchantable(ItemStack itemStack) {
		return DeepAetherConfig.COMMON.skyjade_enchant.get();
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return DeepAetherConfig.COMMON.skyjade_enchant.get();
	}
}
