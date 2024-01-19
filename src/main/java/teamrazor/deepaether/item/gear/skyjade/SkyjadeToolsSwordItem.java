
package teamrazor.deepaether.item.gear.skyjade;

import com.aetherteam.aether.item.AetherCreativeTabs;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import teamrazor.deepaether.DeepAetherConfig;
import teamrazor.deepaether.init.DATiers;
import teamrazor.deepaether.tags.SkyjadeWeapon;

public class SkyjadeToolsSwordItem extends SwordItem implements SkyjadeWeapon {
	public SkyjadeToolsSwordItem() {
		super(DATiers.SKYJADE, 3, -3f, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES));
	}
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return this.increaseDamage(super.getAttributeModifiers(slot, stack), stack, slot);
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
