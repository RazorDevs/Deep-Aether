package teamrazor.deepaether.item.gear;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.NeoForgeMod;
import teamrazor.deepaether.init.DAItems;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;
import java.util.UUID;

public class EquipmentUtil {
    public static boolean hasFullStratusSet(LivingEntity entity) {
        return hasArmorSet(entity, DAItems.STRATUS_HELMET.get(), DAItems.STRATUS_CHESTPLATE.get(), DAItems.STRATUS_LEGGINGS.get(), DAItems.STRATUS_BOOTS.get(), DAItems.STRATUS_GLOVES.get());
    }

    public static boolean hasFullStormsteelSet(LivingEntity entity) {
        return hasArmorSet(entity, DAItems.STORMFORGED_HELMET.get(), DAItems.STORMFORGED_CHESTPLATE.get(), DAItems.STORMFORGED_LEGGINGS.get(), DAItems.STORMFORGED_BOOTS.get());
    }

    public static boolean hasFullSkyjadeSet(LivingEntity entity) {
        return hasArmorSet(entity, DAItems.SKYJADE_HELMET.get(), DAItems.SKYJADE_CHESTPLATE.get(), DAItems.SKYJADE_LEGGINGS.get(), DAItems.SKYJADE_BOOTS.get(), DAItems.SKYJADE_GLOVES.asItem());
    }

    protected static final UUID BASE_STEP_HEIGHT_UUID = UUID.fromString("9e6af263-1af0-4d81-b0d8-2bf97b0e8c18");
    protected static final UUID BASE_SPEED_UUID = UUID.fromString("30f9b053-cc36-4854-9224-d916b4a7aa88");

    public static void updateSkyjadeBehavior(Player player, boolean enabled) {
        AttributeInstance step = player.getAttribute(NeoForgeMod.STEP_HEIGHT.value());
        AttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if(enabled) {
            if(step != null && !step.hasModifier(new AttributeModifier(BASE_STEP_HEIGHT_UUID, "Step Height Bonus", 1.0, AttributeModifier.Operation.ADDITION)))
                step.addTransientModifier(new AttributeModifier(BASE_STEP_HEIGHT_UUID, "Step Height Bonus", 1.0, AttributeModifier.Operation.ADDITION));
            if(speed != null && speed.hasModifier(new AttributeModifier(BASE_SPEED_UUID, "Speed Bonus", 0.2F, AttributeModifier.Operation.ADDITION)))
                speed.addTransientModifier(new AttributeModifier(BASE_SPEED_UUID, "Speed Bonus", 0.2F, AttributeModifier.Operation.ADDITION));
        }
        else {
            if(step != null)
                step.removeModifier(BASE_STEP_HEIGHT_UUID);
            if(speed != null)
                speed.removeModifier(BASE_SPEED_UUID);
        }
    }


    private static boolean hasArmorSet(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots, Item gloves) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(helmet) &&
                entity.getItemBySlot(EquipmentSlot.CHEST).is(chestplate) &&
                entity.getItemBySlot(EquipmentSlot.LEGS).is(leggings) &&
                entity.getItemBySlot(EquipmentSlot.FEET).is(boots) &&
                (!(Boolean) AetherConfig.SERVER.require_gloves.get() || com.aetherteam.aether.item.EquipmentUtil.findFirstCurio(entity, gloves).isPresent());
    }

    private static boolean hasArmorSet(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                && entity.getItemBySlot(EquipmentSlot.CHEST).is(chestplate)
                && entity.getItemBySlot(EquipmentSlot.LEGS).is(leggings)
                && entity.getItemBySlot(EquipmentSlot.FEET).is(boots);
    }

    public static boolean hasTwoSpookyRings(SlotContext context) {
        return CuriosApi.getCuriosHelper().findCurios(context.entity(), DAItems.SPOOKY_RING.get()).size() == 2;
    }

    public static int getSkyjadeRingCount(SlotContext context) {
        return CuriosApi.getCuriosHelper().findCurios(context.entity(), DAItems.SKYJADE_RING.get()).size();
    }

    public static boolean hasCloudNecklace(LivingEntity entity) {
        return !CuriosApi.getCuriosHelper().findCurios(entity, DAItems.AERCLOUD_NECKLACE.get()).isEmpty();
    }

    public static void damageRing(LivingEntity entity, RingItem ring) {
        List<SlotResult> slotResults = getCurios(entity, ring);
        for (SlotResult slotResult : slotResults) {
            if (slotResult != null) {
                if (entity.getRandom().nextInt(3) == 0) {
                    slotResult.stack().hurtAndBreak(1, entity, wearer -> CuriosApi.getCuriosHelper().onBrokenCurio(slotResult.slotContext()));
                }
            }
        }
    }

    public static double handleStratusRingBoost(LivingEntity entity) {
        EquipmentUtil.damageRing(entity, (RingItem) DAItems.GRAVITITE_RING.get());
        EquipmentUtil.damageRing(entity, (RingItem) DAItems.STRATUS_RING.get());
        double multiplier = 1;
        if(CuriosApi.getCuriosHelper().findFirstCurio(entity, DAItems.STRATUS_RING.get()).isPresent())
            multiplier = multiplier+1.2*(EquipmentUtil.getCurios(entity, DAItems.STRATUS_RING.get()).toArray().length);

        if(CuriosApi.getCuriosHelper().findFirstCurio(entity, DAItems.GRAVITITE_RING.get()).isPresent())
            multiplier = multiplier+1.1*(EquipmentUtil.getCurios(entity, DAItems.GRAVITITE_RING.get()).toArray().length);

        return multiplier;
    }

    public static List<SlotResult> getCurios(LivingEntity entity, Item item) {
        return CuriosApi.getCuriosHelper().findCurios(entity, item);
    }
}
