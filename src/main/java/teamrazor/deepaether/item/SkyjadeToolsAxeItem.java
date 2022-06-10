
package teamrazor.deepaether.item;

import teamrazor.deepaether.init.DeepAetherModTabs;
import teamrazor.deepaether.init.DeepAetherModItems;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;

public class SkyjadeToolsAxeItem extends AxeItem {
	public SkyjadeToolsAxeItem() {
		super(new Tier() {
			public int getUses() {
				return 100;
			}

			public float getSpeed() {
				return 12f;
			}

			public float getAttackDamageBonus() {
				return 7f;
			}

			public int getLevel() {
				return 2;
			}

			public int getEnchantmentValue() {
				return 22;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(DeepAetherModItems.SKYJADE.get()));
			}
		}, 1, -3f, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB));
	}
}
