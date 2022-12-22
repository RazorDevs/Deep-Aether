/*

package teamrazor.deepaether.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class DeepAetherModTabs {
	public static CreativeModeTab TAB_DEEP_AETHER_BLOCKS_TAB;
	public static CreativeModeTab TAB_DEEP_AETHER_ITEMS_TAB;

	public static void load() {
		TAB_DEEP_AETHER_BLOCKS_TAB = new CreativeModeTab("tabdeep_aether_blocks_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(DeepAetherModBlocks.FLOWERING_ROSE_LEAVES.get());
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
*/