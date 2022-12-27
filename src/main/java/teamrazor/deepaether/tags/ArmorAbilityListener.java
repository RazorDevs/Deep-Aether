package teamrazor.deepaether.tags;


import com.gildedgames.aether.Aether;
import com.gildedgames.aether.event.hooks.AbilityHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.item.gear.cloudium.CloudiumArmor;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID)
public class ArmorAbilityListener {

    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity livingEntity = event.getEntity();
        CloudiumArmor.moreBoostedJump(livingEntity);
    }

    @SubscribeEvent
    public static void onEntityFall(LivingFallEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (!event.isCanceled()) {
            event.setCanceled(AbilityHooks.ArmorHooks.fallCancellation(livingEntity));
        }
    }
}