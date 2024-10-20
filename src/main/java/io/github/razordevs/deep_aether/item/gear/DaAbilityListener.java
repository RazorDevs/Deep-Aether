package io.github.razordevs.deep_aether.item.gear;

import com.aetherteam.aether.attachment.AetherDataAttachments;
import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;

@EventBusSubscriber(modid = DeepAether.MODID)
public class DaAbilityListener {
    @SubscribeEvent
    public static void onEntityFall(LivingFallEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (!event.isCanceled()) {
            event.setCanceled(fallCancellation(livingEntity));
        }
    }

    // Gravitite Ability check for Ring boost
    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity livingEntity = event.getEntity();

        if (com.aetherteam.aether.item.EquipmentUtil.hasFullGravititeSet(livingEntity)) {
            if (livingEntity instanceof Player player) {
                if (player.getData(AetherDataAttachments.AETHER_PLAYER).isGravititeJumpActive()) {
                    player.push(0.0, DAEquipmentUtil.handleStratusRingBoost(livingEntity) - 1.0, 0.0);
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                    }
                }
            }
        }
    }

    public static boolean fallCancellation(LivingEntity entity) {
        return DAEquipmentUtil.hasFullStratusSet(entity);
    }
}
