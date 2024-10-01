package teamrazor.deepaether.item.gear.skyjade;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.DeepAetherConfig;
import teamrazor.deepaether.init.DASounds;
import teamrazor.deepaether.item.gear.DaArmorMaterials;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class SkyjadeGlovesItem extends GlovesItem {
    public SkyjadeGlovesItem(double punchDamage, Item.Properties properties) {
        super(DaArmorMaterials.SKYJADE, punchDamage, new ResourceLocation(DeepAether.MODID,"skyjade_gloves"), DASounds.ITEM_ARMOR_EQUIP_SKYJADE, properties);
    }
}
