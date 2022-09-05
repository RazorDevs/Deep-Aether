
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package teamrazor.deepaether.deepaether.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import teamrazor.deepaether.init.DeepAetherModBlocks;
import teamrazor.deepaether.init.DeepAetherModItems;

public class DeepAetherModTabs {
	public static CreativeModeTab TAB_DEEP_AETHER_BLOCKS_TAB;
	public static CreativeModeTab TAB_DEEP_AETHER_ITEMS_TAB;

	public static void load() {
		TAB_DEEP_AETHER_BLOCKS_TAB = new CreativeModeTab("tabdeep_aether_blocks_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(DeepAetherModBlocks.AERGLOW_GRASS_BLOCK.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
		TAB_DEEP_AETHER_ITEMS_TAB = new CreativeModeTab("tabdeep_aether_items_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(DeepAetherModItems.SKYJADE_TOOLS_SWORD.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
