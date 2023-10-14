package teamrazor.deepaether.item.gear.stratus;

import com.aetherteam.aether.capability.player.AetherPlayer;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import teamrazor.deepaether.item.gear.EquipmentUtil;

public interface StratusArmor {

    // Stratus Armor Ability
    static void moreBoostedJump(LivingEntity entity) {
        if (EquipmentUtil.hasFullStratusSet(entity)) {
            if (entity instanceof Player player) {
                AetherPlayer.get(player).ifPresent(aetherPlayer -> {
                    if (aetherPlayer.isGravititeJumpActive()) {
                        player.push(0.0, 1.3 * (float) EquipmentUtil.handleStratusRingBoost(player), 0.0);
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                        }
                    }
                });
            } else {
                entity.push(0.0, 1.3, 0.0);
            }
        }
    }
}
