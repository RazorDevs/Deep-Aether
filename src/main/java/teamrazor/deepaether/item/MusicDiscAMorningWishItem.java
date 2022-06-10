
package teamrazor.deepaether.item;

import teamrazor.deepaether.init.DeepAetherModTabs;
import teamrazor.deepaether.init.DeepAetherModSounds;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

public class MusicDiscAMorningWishItem extends RecordItem {
	public MusicDiscAMorningWishItem() {
		super(0, DeepAetherModSounds.REGISTRY.get(new ResourceLocation("deep_aether:music_disc_a_morning_wish")),
				new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1).rarity(Rarity.RARE));
	}
}
