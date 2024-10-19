package io.github.razordevs.deepaether.deepaether.item.gear.skyjade;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.DeepAetherConfig;
import io.github.razordevs.deepaether.deepaether.init.DASounds;
import io.github.razordevs.deepaether.deepaether.item.gear.DaArmorMaterials;
import top.theillusivec4.curios.api.SlotContext;

public class SkyjadeGlovesItem extends GlovesItem {
    public SkyjadeGlovesItem(double punchDamage, Item.Properties properties) {
        super(DaArmorMaterials.SKYJADE, punchDamage, new ResourceLocation(DeepAether.MODID,"skyjade_gloves"), DASounds.ITEM_ARMOR_EQUIP_SKYJADE, properties);
    }
}
