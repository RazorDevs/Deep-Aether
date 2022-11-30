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
import teamrazor.deepaether.init.DeepAetherModItems;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import top.theillusivec4.curios.api.CuriosApi;


public class CloudiumAbility extends ArmorItem {

    static float coolDown = 0;

    public CloudiumAbility(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    public static boolean hasFullCloudiumSet(LivingEntity entity) {
        return hasArmorSet(entity, (Item) DeepAetherModItems.CLOUDIUM_HELMET.get(), (Item) DeepAetherModItems.CLOUDIUM_CHESTPLATE.get(), (Item) DeepAetherModItems.CLOUDIUM_LEGGINGS.get(), (Item) DeepAetherModItems.CLOUDIUM_BOOTS.get(), (Item) DeepAetherModItems.CLOUDIUM_GLOVES.get());
    }

    private static boolean hasArmorSet(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots, Item gloves) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                && entity.getItemBySlot(EquipmentSlot.CHEST).is(chestplate)
                && entity.getItemBySlot(EquipmentSlot.LEGS).is(leggings)
                && entity.getItemBySlot(EquipmentSlot.FEET).is(boots)
                && CuriosApi.getCuriosHelper().findFirstCurio(entity, gloves).isPresent();
    }
    private static boolean isCloudiumDashActive(Player player) {
        if(hasFullCloudiumSet(player) == true && coolDown <= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    double strength = 1;
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(coolDown >= 0)
            coolDown -= 0.02;
        if (!world.isClientSide()) {
            if (hasFullCloudiumSet(player) && DeepAetherKeys.CLOUDIUM_DASH_ABILITY.isDown()) {
                dash(player, strength);
            }
        }
    }

    static void dash(LivingEntity entity, double strength) {
        if (EquipmentUtil.hasFullCloudiumSet(entity)) {
            if (entity instanceof Player player) {

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
                    if (CloudiumAbility.isCloudiumDashActive(player)) {
                        coolDown = 5;
                        player.push(x*a, y, z*a);
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                        }
                    }
                }
            }
        }
    }



