package teamrazor.deepaether.item.gear.skyjade;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DAConfig;
import teamrazor.deepaether.item.gear.DaArmorItem;

public class SkyjadeArmorItem extends DaArmorItem {
    public SkyjadeArmorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
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
