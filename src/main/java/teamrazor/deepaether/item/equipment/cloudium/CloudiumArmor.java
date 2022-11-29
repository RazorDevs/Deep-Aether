package teamrazor.deepaether.item.equipment.cloudium;

import com.gildedgames.aether.capability.player.AetherPlayer;

import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import teamrazor.deepaether.item.equipment.EquipmentUtil;

public interface CloudiumArmor {


    static void moreBoostedJump(LivingEntity entity) {
        if (EquipmentUtil.hasFullCloudiumSet(entity)) {
            if (entity instanceof Player player) {
                AetherPlayer.get(player).ifPresent(aetherPlayer -> {
                    if (aetherPlayer.isGravititeJumpActive()) {
                        player.push(0.0, 1.5, 0.0);
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
/*    static void dash(LivingEntity entity, double strength) {
        if (EquipmentUtil.hasFullCloudiumSet(entity)) {
            if (entity instanceof Player player) {
                AetherPlayer.get(player).ifPresent(aetherPlayer -> {
                    double x = player.getLookAngle().x * strength * 2;
                    double y = player.getLookAngle().y * strength;
                    double z = player.getLookAngle().z * strength * 2;
                    //a is going to be multiplied with x and z. y's max value is 2 or -2. The larges muliplyer should be 1 (looking forwatd). Defor we multiply y with 0.5
                    double a = y*0.5;
                    // absolute value of a
                    if (a < 0) {
                        a = a * -1;
                    }
                    //When a (y) is large (when you look up or down) the multiplier of x and z should be close to zero
                    a = 1 - a;
                    System.out.println("x: " + x + "y: " + y + "z: " + z);
                    if (CloudiumArmor.isCloudiumDashActive) {
                        player.push(x*a, y, z*a);
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                        }
                    }
                });
            }
        }
    }*/
}
