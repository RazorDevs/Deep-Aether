package teamrazor.deepaether.item.gear;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID)
public class DAArmorAbilityListener {
    @SubscribeEvent
    public static void onEntityFall(LivingFallEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (!event.isCanceled()) {
            event.setCanceled(fallCancellation(livingEntity));
        }
    }
    public static boolean fallCancellation(LivingEntity entity) {
        return EquipmentUtil.hasFullCloudiumSet(entity);
    }
}
