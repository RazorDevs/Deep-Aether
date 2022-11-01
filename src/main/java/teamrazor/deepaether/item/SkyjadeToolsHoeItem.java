
package teamrazor.deepaether.item;

import teamrazor.deepaether.init.DeepAetherModTabs;
import teamrazor.deepaether.init.DeepAetherModItems;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.HoeItem;
import teamrazor.deepaether.tags.SkyjadeTool;

public class SkyjadeToolsHoeItem extends HoeItem implements SkyjadeTool {
	public SkyjadeToolsHoeItem(Tier tier, int i, float v, Properties properties) {
		super(tier, i, v, properties);
	}
}
