package teamrazor.deepaether.item.gear.cloudium;

import com.gildedgames.aether.capability.player.AetherPlayer;
import com.gildedgames.aether.item.accessories.ring.RingItem;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

public interface CloudiumArmor {
    static void moreBoostedJump(LivingEntity entity) {
        if (EquipmentUtil.hasFullCloudiumSet(entity)) {
            if (entity instanceof Player player) {
                AetherPlayer.get(player).ifPresent(aetherPlayer -> {
                    if (aetherPlayer.isGravititeJumpActive()) {
                        player.push(0.0, 1.5 * (float) EquipmentUtil.HandleCloudiumRingBoost(player), 0.0);
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                        }
                    }
                });
            } else {
                entity.push(0.0, 1.5, 0.0);
            }
        }
    }
}
