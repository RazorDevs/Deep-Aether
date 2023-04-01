package teamrazor.deepaether.item.gear.cloudium;


import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamrazor.deepaether.client.keys.DeepAetherKeys;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.item.gear.DaArmorItem;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import top.theillusivec4.curios.api.CuriosApi;


public class CloudiumAbility extends DaArmorItem {

    static float coolDown = 0;

    public CloudiumAbility(ArmorMaterial p_40386_, ArmorItem.Type p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    public static boolean hasFullCloudiumSet(LivingEntity entity) {
        return hasArmorSet(entity, DAItems.CLOUDIUM_HELMET.get(), DAItems.CLOUDIUM_CHESTPLATE.get(), DAItems.CLOUDIUM_LEGGINGS.get(), DAItems.CLOUDIUM_BOOTS.get(), DAItems.CLOUDIUM_GLOVES.get());
    }


    private static boolean hasArmorSet(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots, Item gloves) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                && entity.getItemBySlot(EquipmentSlot.CHEST).is(chestplate)
                && entity.getItemBySlot(EquipmentSlot.LEGS).is(leggings)
                && entity.getItemBySlot(EquipmentSlot.FEET).is(boots)
                && CuriosApi.getCuriosHelper().findFirstCurio(entity, gloves).isPresent();
    }

    private static boolean isCloudiumDashActive(Player player) {
        return hasFullCloudiumSet(player) && coolDown <= 0;
    }

    double strength = 1.5;

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (coolDown >= 0)
            coolDown -= 0.02;
        if (!world.isClientSide() && hasFullCloudiumSet(player)) {

            if (DeepAetherKeys.CLOUDIUM_DASH_ABILITY.isDown()) {
                dash(player, strength);
            }
        }
    }

    static void dash(LivingEntity entity, double strength) {
        float dashMultiplier = 1.0F;
        if (EquipmentUtil.hasFullCloudiumSet(entity)) {
            if (entity instanceof Player player) {

                double x = player.getLookAngle().x * strength * 2;
                double y = player.getLookAngle().y * strength;
                double z = player.getLookAngle().z * strength * 2;
                double a = y * 0.5;
                // absolute value of a
                if (a < 0) {
                    a = a * -1;
                }
                a = 1 - a;
                if (CloudiumAbility.isCloudiumDashActive(player)) {
                    coolDown = 5;
                    dashMultiplier = (float) EquipmentUtil.handleCloudiumRingBoost(player);
                    player.push(x * a * dashMultiplier, y * dashMultiplier, z * a * dashMultiplier);
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                    }
                }
            }
        }
    }

}

