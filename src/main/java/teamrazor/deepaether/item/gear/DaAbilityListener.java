package teamrazor.deepaether.item.gear;

import com.aetherteam.aether.capability.player.AetherPlayer;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAether;

@Mod.EventBusSubscriber(modid = DeepAether.MODID)
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
                AetherPlayer.get(player).ifPresent(aetherPlayer -> {
                    if (aetherPlayer.isGravititeJumpActive()) {
                        player.push(0.0, EquipmentUtil.handleStratusRingBoost(livingEntity)-1.0, 0.0);
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                        }
                    }
                });
            }
        }
    }

    public static boolean fallCancellation(LivingEntity entity) {
        return EquipmentUtil.hasFullStratusSet(entity);
    }

    @SubscribeEvent
    public static void onMiningSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (!event.isCanceled()) {
            event.setNewSpeed(EquipmentUtil.handleSkyjadeRingAbility(player, event.getNewSpeed()));
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (!event.isCanceled()) {
            EquipmentUtil.damageRing(player, (RingItem) DAItems.SKYJADE_RING.get());
        }
    }
}
