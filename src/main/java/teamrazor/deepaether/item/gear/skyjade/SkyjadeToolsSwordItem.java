
package teamrazor.deepaether.item.gear.skyjade;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import teamrazor.deepaether.DAConfig;
import teamrazor.deepaether.init.DATiers;
import teamrazor.deepaether.tags.SkyjadeWeapon;

public class SkyjadeToolsSwordItem extends SwordItem implements SkyjadeWeapon {
	public SkyjadeToolsSwordItem() {
		super(DATiers.SKYJADE, 3, -3f, new Properties());
	}
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return this.increaseDamage(super.getAttributeModifiers(slot, stack), stack, slot);
	}

	@Override
	public boolean isEnchantable(ItemStack itemStack) {
		return DAConfig.COMMON.skyjade_enchant.get();
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return DAConfig.COMMON.skyjade_enchant.get();
	}
}
