package io.github.razordevs.deep_aether.item.gear;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.item.EquipmentUtil;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DAItems;
import io.wispforest.accessories.api.AccessoriesAPI;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class DAEquipmentUtil {
    public static boolean hasFullStratusSet(LivingEntity entity) {
        return hasArmorSet(entity, DAItems.STRATUS_HELMET.get(), DAItems.STRATUS_CHESTPLATE.get(), DAItems.STRATUS_LEGGINGS.get(), DAItems.STRATUS_BOOTS.get(), DAItems.STRATUS_GLOVES.get());
    }

    public static boolean hasFullStormsteelSet(LivingEntity entity) {
        return hasArmorSet(entity, DAItems.STORMFORGED_HELMET.get(), DAItems.STORMFORGED_CHESTPLATE.get(), DAItems.STORMFORGED_LEGGINGS.get(), DAItems.STORMFORGED_BOOTS.get());
    }

    public static boolean hasFullSkyjadeSet(LivingEntity entity) {
        return hasArmorSet(entity, DAItems.SKYJADE_HELMET.get(), DAItems.SKYJADE_CHESTPLATE.get(), DAItems.SKYJADE_LEGGINGS.get(), DAItems.SKYJADE_BOOTS.get(), DAItems.SKYJADE_GLOVES.asItem());
    }

    protected static final AttributeModifier STEP_HEIGHT_BONUS = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "Step Height Bonus"), 1.0, AttributeModifier.Operation.ADD_VALUE);
    protected static final AttributeModifier SPEED_BONUS = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "Speed Bonus"), 0.2, AttributeModifier.Operation.ADD_VALUE);

    public static void updateSkyjadeBehavior(Player player, boolean enabled) {
        AttributeInstance step = player.getAttribute(Attributes.STEP_HEIGHT);
        AttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if(enabled) {
            if(step != null && !step.hasModifier(STEP_HEIGHT_BONUS.id()))
                step.addTransientModifier(STEP_HEIGHT_BONUS);
            if(speed != null && speed.hasModifier(SPEED_BONUS.id()))
                speed.addTransientModifier(SPEED_BONUS);
        }
        else {
            if(step != null)
                step.removeModifier(STEP_HEIGHT_BONUS.id());
            if(speed != null)
                speed.removeModifier(SPEED_BONUS.id());
        }
    }

    private static boolean hasArmorSet(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots, Item gloves) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(helmet) &&
                entity.getItemBySlot(EquipmentSlot.CHEST).is(chestplate) &&
                entity.getItemBySlot(EquipmentSlot.LEGS).is(leggings) &&
                entity.getItemBySlot(EquipmentSlot.FEET).is(boots) &&
                (!AetherConfig.SERVER.require_gloves.get() || EquipmentUtil.findFirstAccessory(entity, gloves).isPresent());
    }

    private static boolean hasArmorSet(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                && entity.getItemBySlot(EquipmentSlot.CHEST).is(chestplate)
                && entity.getItemBySlot(EquipmentSlot.LEGS).is(leggings)
                && entity.getItemBySlot(EquipmentSlot.FEET).is(boots);
    }

    public static boolean hasTwoSpookyRings(LivingEntity entity) {
        return EquipmentUtil.getAccessories(entity, DAItems.SPOOKY_RING.get()).size() == 2;
    }


    public static int getSkyjadeRingCount(LivingEntity entity) {
        return EquipmentUtil.getAccessories(entity, DAItems.SKYJADE_RING.get()).size();
    }

    public static boolean hasCloudNecklace(LivingEntity entity) {
        return EquipmentUtil.findFirstAccessory(entity, DAItems.AERCLOUD_NECKLACE.get()).isPresent();
    }

    public static void damageRing(LivingEntity entity, RingItem ring) {
        List<SlotEntryReference> slotResults = EquipmentUtil.getAccessories(entity, ring);
        for (SlotEntryReference slotResult : slotResults) {
            if (slotResult != null) {
                if (entity.level() instanceof ServerLevel serverLevel) {
                    slotResult.stack().hurtAndBreak(1, serverLevel, entity, (item) -> AccessoriesAPI.breakStack(slotResult.reference()));
                }
            }
        }
    }



    public static double handleStratusRingBoost(LivingEntity entity) {
        DAEquipmentUtil.damageRing(entity, (RingItem) DAItems.GRAVITITE_RING.get());
        DAEquipmentUtil.damageRing(entity, (RingItem) DAItems.STRATUS_RING.get());
        double multiplier = 1;
        List<SlotEntryReference> items = EquipmentUtil.getAccessories(entity, DAItems.STRATUS_RING.get());

        if(!items.isEmpty())
            multiplier = multiplier+(1.2*items.size());

        items = EquipmentUtil.getAccessories(entity, DAItems.GRAVITITE_RING.get());
        if(!items.isEmpty())
            multiplier = multiplier+(1.1*items.size());

        return multiplier;
    }
}
