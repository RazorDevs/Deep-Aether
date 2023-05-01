package teamrazor.deepaether.item.gear;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.item.gear.skyjade.SkyjadeAccessory;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

public class EquipmentUtil {
    public static boolean hasFullCloudiumSet(LivingEntity entity) {
        return hasArmorSet(entity, DAItems.CLOUDIUM_HELMET.get(), DAItems.CLOUDIUM_CHESTPLATE.get(), DAItems.CLOUDIUM_LEGGINGS.get(), DAItems.CLOUDIUM_BOOTS.get(), DAItems.CLOUDIUM_GLOVES.get());
    }


    private static boolean hasArmorSet(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots, Item gloves) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                && entity.getItemBySlot(EquipmentSlot.CHEST).is(chestplate)
                && entity.getItemBySlot(EquipmentSlot.LEGS).is(leggings)
                && entity.getItemBySlot(EquipmentSlot.FEET).is(boots)
                && CuriosApi.getCuriosHelper().findFirstCurio(entity, gloves).isPresent();
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

    public static double handleCloudiumRingBoost(LivingEntity entity) {
        EquipmentUtil.damageRing(entity, (RingItem) DAItems.GRAVITIE_RING.get());
        EquipmentUtil.damageRing(entity, (RingItem) DAItems.CLOUDIUM_RING.get());
        double multiplier = 1;
        if(CuriosApi.getCuriosHelper().findFirstCurio(entity, DAItems.CLOUDIUM_RING.get()).isPresent())
            multiplier = multiplier+1.25*(EquipmentUtil.getCurios(entity, DAItems.CLOUDIUM_RING.get()).toArray().length);

        if(CuriosApi.getCuriosHelper().findFirstCurio(entity, DAItems.GRAVITIE_RING.get()).isPresent())
            multiplier = multiplier+1.15*(EquipmentUtil.getCurios(entity, DAItems.GRAVITIE_RING.get()).toArray().length);

        System.out.println(multiplier);
        return multiplier;

    }
    public static float handleSkyjadeRingAbility(LivingEntity entity, float speed) {
        float newSpeed = speed;
        List<SlotResult> slotResults = getCurios(entity, DAItems.SKYJADE_RING.get());
        for (SlotResult slotResult : slotResults) {
            if (slotResult != null) {
                newSpeed = SkyjadeAccessory.handleMiningSpeed(newSpeed, slotResult.stack());
            }
        }
        return newSpeed;
    }

    public static List<SlotResult> getCurios(LivingEntity entity, Item item) {
        return CuriosApi.getCuriosHelper().findCurios(entity, item);
    }
}
