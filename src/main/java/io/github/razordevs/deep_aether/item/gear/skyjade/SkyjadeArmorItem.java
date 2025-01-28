package io.github.razordevs.deep_aether.item.gear.skyjade;

import io.github.razordevs.deep_aether.DeepAetherConfig;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class SkyjadeArmorItem extends ArmorItem {
    public SkyjadeArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return DeepAetherConfig.SERVER.skyjade_enchant.get() && !DeepAetherConfig.SERVER.enable_skyjade_rework.get();
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return DeepAetherConfig.SERVER.skyjade_enchant.get() && !DeepAetherConfig.SERVER.enable_skyjade_rework.get();
    }
}


