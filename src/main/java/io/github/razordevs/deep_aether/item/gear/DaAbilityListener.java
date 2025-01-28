package io.github.razordevs.deep_aether.item.gear;

import com.aetherteam.aether.attachment.AetherDataAttachments;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.DeepAetherConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = DeepAether.MODID)
public class DaAbilityListener {
    @SubscribeEvent
    public static void onEntityFall(LivingFallEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (!event.isCanceled()) {
            event.setCanceled(DAEquipmentUtil.hasFullStratusSet(livingEntity));
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

    @SubscribeEvent
    public static void onMiningSpeed(PlayerEvent.BreakSpeed event) {
        if(DeepAetherConfig.SERVER.enable_skyjade_rework.get())
            return;

        Player player = event.getEntity();
        if (!event.isCanceled()) {
            event.setNewSpeed(DAEquipmentUtil.handleSkyjadeRingAbility(player, event.getNewSpeed()));
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if(DeepAetherConfig.SERVER.enable_skyjade_rework.get())
            return;

        Player player = event.getPlayer();
        LevelAccessor level = event.getLevel();
        BlockState state = event.getState();
        BlockPos pos = event.getPos();
        if (!event.isCanceled()) {
            DAEquipmentUtil.damageSkyjadeRing(player, level, state, pos);
        }
    }
}
