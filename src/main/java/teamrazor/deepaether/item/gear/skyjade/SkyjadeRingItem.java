package teamrazor.deepaether.item.gear.skyjade;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForgeMod;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.init.DASounds;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;


public class SkyjadeRingItem extends RingItem {
    public SkyjadeRingItem(Properties properties) {
        super(DASounds.ITEM_ACCESSORY_EQUIP_SKYJADE_RING, properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack repairItem, ItemStack repairMaterial) {
        return repairMaterial.is(DAItems.SKYJADE.get());
    }

    private static final UUID STEP_HEIGHT_0_UUID = UUID.fromString("12b603c5-f864-4747-9060-5332de7cbc44");
    private static final UUID STEP_HEIGHT_1_UUID = UUID.fromString("b6622801-8cd2-4983-8f74-e65110311040");

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        AttributeInstance stepHeight = livingEntity.getAttribute(NeoForgeMod.STEP_HEIGHT.value());
        if (stepHeight != null) {
            if (!stepHeight.hasModifier(this.getStepHeightModifier(EquipmentUtil.getSkyjadeRingCount(slotContext))) && !livingEntity.isShiftKeyDown()) {
                stepHeight.addTransientModifier(this.getStepHeightModifier(EquipmentUtil.getSkyjadeRingCount(slotContext)));
            }
        }
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        AttributeInstance stepHeight = livingEntity.getAttribute(NeoForgeMod.STEP_HEIGHT.value());
        if (stepHeight != null) {
            if (stepHeight.hasModifier(this.getStepHeightModifier(EquipmentUtil.getSkyjadeRingCount(slotContext)-1))) {
                stepHeight.removeModifier(this.getStepHeightModifier(EquipmentUtil.getSkyjadeRingCount(slotContext)-1).getId());
            }
        }
    }

    public AttributeModifier getStepHeightModifier(int count) {
        if(count == 0)
            return new AttributeModifier(STEP_HEIGHT_0_UUID, "Step height increase", 1.0, AttributeModifier.Operation.ADDITION);
        else return new AttributeModifier(STEP_HEIGHT_1_UUID, "Step height increase 1", 1.0, AttributeModifier.Operation.ADDITION);
    }
}
