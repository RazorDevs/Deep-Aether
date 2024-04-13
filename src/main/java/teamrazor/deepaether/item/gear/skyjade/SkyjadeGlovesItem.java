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
import teamrazor.deepaether.DeepAetherConfig;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DASounds;
import teamrazor.deepaether.item.gear.DaArmorMaterials;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class SkyjadeGlovesItem extends GlovesItem {
    public SkyjadeGlovesItem(double punchDamage, Item.Properties properties) {
        super(DaArmorMaterials.SKYJADE, punchDamage, new ResourceLocation(DeepAetherMod.MODID,"skyjade_gloves"), DASounds.ITEM_ARMOR_EQUIP_SKYJADE, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
        attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Gloves Damage Bonus", calculateIncrease(stack), AttributeModifier.Operation.ADDITION));
        return attributes;
    }
    private float calculateIncrease(ItemStack stack) {
        int maxDurability = stack.getMaxDamage();
        int currentDurability = maxDurability - stack.getDamageValue();
        if (currentDurability >= maxDurability - (int) (maxDurability / 4.0)) {
            return 1F;

        }
        else if (currentDurability >= maxDurability - (int) (maxDurability / 3.0)) {
            return 0.75F;
        }

        else if (currentDurability >= maxDurability - (int) (maxDurability / 1.5)) {
            return 0.5F;
        }
        else {
            return 0.25F;
        }
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
