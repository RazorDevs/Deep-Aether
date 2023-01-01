package teamrazor.deepaether.item.gear;

import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import teamrazor.deepaether.init.DeepAetherModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

public class EquipmentUtil {
    public static boolean hasFullCloudiumSet(LivingEntity entity) {
        return hasArmorSet(entity, DeepAetherModItems.CLOUDIUM_HELMET.get(), DeepAetherModItems.CLOUDIUM_CHESTPLATE.get(), DeepAetherModItems.CLOUDIUM_LEGGINGS.get(), DeepAetherModItems.CLOUDIUM_BOOTS.get(), DeepAetherModItems.CLOUDIUM_GLOVES.get());
    }


    private static boolean hasArmorSet(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots, Item gloves) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                && entity.getItemBySlot(EquipmentSlot.CHEST).is(chestplate)
                && entity.getItemBySlot(EquipmentSlot.LEGS).is(leggings)
                && entity.getItemBySlot(EquipmentSlot.FEET).is(boots)
                && CuriosApi.getCuriosHelper().findFirstCurio(entity, gloves).isPresent();
    }
}
