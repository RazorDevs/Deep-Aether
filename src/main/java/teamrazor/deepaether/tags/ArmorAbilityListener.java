package teamrazor.deepaether.tags;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.item.gear.cloudium.CloudiumArmor;

@Mod.EventBusSubscriber
public class ArmorAbilityListener {

    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity livingEntity = event.getEntity();
        CloudiumArmor.moreBoostedJump(livingEntity);
    }
}