package teamrazor.deepaether.item.gear.stratus;


import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamrazor.deepaether.client.keys.DeepAetherKeys;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.item.gear.DaArmorItem;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import top.theillusivec4.curios.api.CuriosApi;


public class StratusAbility extends DaArmorItem {

    static float coolDown = 0;

    public StratusAbility(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    public static boolean hasFullStratusSet(LivingEntity entity) {
        return hasArmorSet(entity, DAItems.STRATUS_HELMET.get(), DAItems.STRATUS_CHESTPLATE.get(), DAItems.STRATUS_LEGGINGS.get(), DAItems.STRATUS_BOOTS.get(), DAItems.STRATUS_GLOVES.get());
    }


    private static boolean hasArmorSet(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots, Item gloves) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                && entity.getItemBySlot(EquipmentSlot.CHEST).is(chestplate)
                && entity.getItemBySlot(EquipmentSlot.LEGS).is(leggings)
                && entity.getItemBySlot(EquipmentSlot.FEET).is(boots)
                && CuriosApi.getCuriosHelper().findFirstCurio(entity, gloves).isPresent();
    }

    private static boolean isStratusDashActive(Player player) {
        return hasFullStratusSet(player) && coolDown <= 0;
    }

    private final double strength = 1.3;
    private static boolean hasBeenOnGround = true;


    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(player.isOnGround()) {
            hasBeenOnGround = true;
        }

        if (coolDown >= 0)
            coolDown -= 0.02;
        if (world.isClientSide() && hasFullStratusSet(player)) {

            if (DeepAetherKeys.STRATUS_DASH_ABILITY.isDown()) {
                dash(player, strength);
            }
        }
    }

    static void dash(LivingEntity entity, double strength) {
        float dashMultiplier;
        if (EquipmentUtil.hasFullStratusSet(entity) && hasBeenOnGround) {
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
                if (StratusAbility.isStratusDashActive(player)) {
                    hasBeenOnGround = false;
                    coolDown = 5;
                    dashMultiplier = (float) EquipmentUtil.handleStratusRingBoost(player);
                    player.push(x * a * dashMultiplier, y * dashMultiplier, z * a * dashMultiplier);
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                    }
                }
            }
        }
    }

}

