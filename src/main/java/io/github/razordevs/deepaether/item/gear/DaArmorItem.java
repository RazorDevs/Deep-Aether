package io.github.razordevs.deepaether.deepaether.item.gear;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAether;

import javax.annotation.Nullable;

public class DaArmorItem extends ArmorItem {
    public DaArmorItem(ArmorMaterial material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return String.format("%s:textures/models/armor/%s_layer_%s.png", DeepAether.MODID, this.getMaterial().getName(), slot == EquipmentSlot.LEGS ? 2 : 1);
    }
}
