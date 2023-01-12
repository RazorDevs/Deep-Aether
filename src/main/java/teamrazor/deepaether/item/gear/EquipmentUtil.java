package teamrazor.deepaether.item.gear;

import teamrazor.deepaether.init.DAItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

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
}
