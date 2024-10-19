package io.github.razordevs.deep_aether.item.gear;


import com.aetherteam.aether.event.hooks.AbilityHooks;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.item.gear.stratus.StratusAbility;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;

@EventBusSubscriber(modid = DeepAether.MODID)
public class ArmorAbilityListener {

    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity livingEntity = event.getEntity();
        StratusAbility.moreBoostedJump(livingEntity);
    }

    @SubscribeEvent
    public static void onEntityFall(LivingFallEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (!event.isCanceled()) {
            event.setCanceled(AbilityHooks.ArmorHooks.fallCancellation(livingEntity));
        }
    }
}