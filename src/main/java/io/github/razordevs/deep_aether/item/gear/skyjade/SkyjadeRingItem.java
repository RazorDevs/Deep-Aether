package io.github.razordevs.deep_aether.item.gear.skyjade;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DAItems;
import io.github.razordevs.deep_aether.init.DASounds;
import io.github.razordevs.deep_aether.item.gear.DAEquipmentUtil;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class SkyjadeRingItem extends RingItem {
    public SkyjadeRingItem(Properties properties) {
        super(DASounds.ITEM_ACCESSORY_EQUIP_SKYJADE_RING, properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack repairItem, ItemStack repairMaterial) {
        return repairMaterial.is(DAItems.SKYJADE.get());
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        LivingEntity livingEntity = reference.entity();
        AttributeInstance stepHeight = livingEntity.getAttribute(Attributes.STEP_HEIGHT);
        if (stepHeight != null) {
            int count = DAEquipmentUtil.getSkyjadeRingCount(livingEntity);
            for (int i = 1; i <= count; i++) {
                if (!stepHeight.hasModifier(this.getStepHeightModifierLocation(i))) {
                    stepHeight.addTransientModifier(this.getStepHeightModifier(i));
                }
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        LivingEntity livingEntity = reference.entity();
        AttributeInstance stepHeight = livingEntity.getAttribute(Attributes.STEP_HEIGHT);
        if (stepHeight != null) {
            if (stepHeight.hasModifier(this.getStepHeightModifierLocation(DAEquipmentUtil.getSkyjadeRingCount(livingEntity)+1))) {
                stepHeight.removeModifier(this.getStepHeightModifierLocation(DAEquipmentUtil.getSkyjadeRingCount(livingEntity)+1));
            }
        }
    }

    public AttributeModifier getStepHeightModifier(int count) {
        if(count == 1)
            return new AttributeModifier(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "Step height increase"), 0.5, AttributeModifier.Operation.ADD_VALUE);
        else return new AttributeModifier(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "Step height increase 1"), 0.5, AttributeModifier.Operation.ADD_VALUE);
    }

    public ResourceLocation getStepHeightModifierLocation(int count) {
        if(count == 1)
            return ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "Step height increase");
        else return ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "Step height increase 1");
    }
}
