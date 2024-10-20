package io.github.razordevs.deep_aether.item.gear.stratus;


import com.aetherteam.aether.attachment.AetherDataAttachments;
import io.github.razordevs.deep_aether.DeepAetherConfig;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import io.github.razordevs.deep_aether.client.keys.DeepAetherKeys;
import io.github.razordevs.deep_aether.item.gear.DAEquipmentUtil;

public class StratusAbility extends ArmorItem {

    public static float coolDown = 0;

    public StratusAbility(Holder<ArmorMaterial> armorMaterial, ArmorItem.Type type, Properties properties) {
        super(armorMaterial, type, properties);
    }

    private static boolean isStratusDashActive(Player player) {
        return DAEquipmentUtil.hasFullStratusSet(player) && coolDown <= 0;
    }

    private static boolean hasBeenOnGround = true;

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        //TODO: CHECK FOR CORRECT SLOT ID
        if(entity instanceof Player player) {
            if (player.onGround()) {
                hasBeenOnGround = true;
            }

            if (coolDown >= 0)
                coolDown -= 0.02F;
            if (level.isClientSide() && DAEquipmentUtil.hasFullStratusSet(player)) {

                if (DeepAetherKeys.STRATUS_DASH_ABILITY.isDown()) {
                    dash(player);
                }
            }
        }
    }

    static void dash(LivingEntity entity) {
        float dashMultiplier;
        if (DAEquipmentUtil.hasFullStratusSet(entity) && hasBeenOnGround) {
            if (entity instanceof Player player) {
                double x = player.getLookAngle().x * 1.3 * 2;
                double y = player.getLookAngle().y * 1.3;
                double z = player.getLookAngle().z * 1.3 * 2;
                double a = Math.abs(y * 0.5);

                a = 1 - a;
                if (StratusAbility.isStratusDashActive(player)) {
                    hasBeenOnGround = false;
                    coolDown = (float) DeepAetherConfig.COMMON.stratus_dash_cooldown.get();
                    dashMultiplier = (float) DAEquipmentUtil.handleStratusRingBoost(player);
                    player.push(x * a * dashMultiplier, y * dashMultiplier, z * a * dashMultiplier);
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                    }
                }
            }
        }
    }
    public static void moreBoostedJump(LivingEntity entity) {
        if (DAEquipmentUtil.hasFullStratusSet(entity)) {
            if (entity instanceof Player player) {
                if(player.hasData(AetherDataAttachments.AETHER_PLAYER)) {
                    if (player.getData(AetherDataAttachments.AETHER_PLAYER).isGravititeJumpActive()) {
                        player.push(0.0, 1.3 * (float) DAEquipmentUtil.handleStratusRingBoost(player), 0.0);
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

