package teamrazor.deepaether.item.gear;

import com.gildedgames.aether.Aether;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aether.MODID)
public class DeepAetherModArmorAbilityListener {
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
