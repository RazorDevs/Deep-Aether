package io.github.razordevs.deepaether.deepaether.item.gear.stratus;


import com.aetherteam.aether.attachment.AetherDataAttachments;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamrazor.deepaether.DeepAetherConfig;
import io.github.razordevs.deepaether.deepaether.client.keys.DeepAetherKeys;
import io.github.razordevs.deepaether.deepaether.item.gear.DaArmorItem;
import io.github.razordevs.deepaether.deepaether.item.gear.EquipmentUtil;

import static com.aetherteam.aether.item.EquipmentUtil.findFirstCurio;


public class StratusAbility extends DaArmorItem {

    public static float coolDown = 0;

    public StratusAbility(ArmorMaterial armorMaterial, ArmorItem.Type type, Properties properties) {
        super(armorMaterial, type, properties);
    }

    private static boolean isStratusDashActive(Player player) {
        return EquipmentUtil.hasFullStratusSet(player) && coolDown <= 0;
    }

    private static boolean hasBeenOnGround = true;


    @SuppressWarnings("removal")
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(player.onGround()) {
            hasBeenOnGround = true;
        }

        if (coolDown >= 0)
            coolDown -= 0.02F;
        if (world.isClientSide() && EquipmentUtil.hasFullStratusSet(player)) {

            if (DeepAetherKeys.STRATUS_DASH_ABILITY.isDown()) {
                dash(player);
            }
        }
    }

    static void dash(LivingEntity entity) {
        float dashMultiplier;
        if (EquipmentUtil.hasFullStratusSet(entity) && hasBeenOnGround) {
            if (entity instanceof Player player) {
                double x = player.getLookAngle().x * 1.3 * 2;
                double y = player.getLookAngle().y * 1.3;
                double z = player.getLookAngle().z * 1.3 * 2;
                double a = y * 0.5;
                // absolute value of a
                if (a < 0) {
                    a = a * -1;
                }
                a = 1 - a;
                if (StratusAbility.isStratusDashActive(player)) {
                    hasBeenOnGround = false;
                    coolDown = (float) DeepAetherConfig.COMMON.stratus_dash_cooldown.get();
                    dashMultiplier = (float) EquipmentUtil.handleStratusRingBoost(player);
                    player.push(x * a * dashMultiplier, y * dashMultiplier, z * a * dashMultiplier);
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                    }
                }
            }
        }
    }
    public static void moreBoostedJump(LivingEntity entity) {
        if (EquipmentUtil.hasFullStratusSet(entity)) {
            if (entity instanceof Player player) {
                if(player.hasData(AetherDataAttachments.AETHER_PLAYER)) {
                    if (player.getData(AetherDataAttachments.AETHER_PLAYER).isGravititeJumpActive()) {
                        player.push(0.0, 1.3 * (float) EquipmentUtil.handleStratusRingBoost(player), 0.0);
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                        }
                    }
                }
            } else {
                entity.push(0.0, 1.3, 0.0);
            }
        }
    }
}

