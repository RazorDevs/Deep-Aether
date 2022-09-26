package teamrazor.deepaether.item.abilities;


import com.google.common.collect.ImmutableMap;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamrazor.deepaether.init.DeepAetherModItems;
import teamrazor.deepaether.item.DeepAetherArmorMaterial;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Map;


public class CloudiumAbility extends ArmorItem {
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(DeepAetherArmorMaterial.CLOUDIUM,
                            new MobEffectInstance(MobEffects.JUMP, 200, 20)).build();

    public static boolean hasFullCloudiumSet(LivingEntity entity) {
        return hasArmorSet(entity, (Item) DeepAetherModItems.CLOUDIUM_HELMET.get(), (Item)DeepAetherModItems.CLOUDIUM_CHESTPLATE.get(), (Item)DeepAetherModItems.CLOUDIUM_LEGGING.get(), (Item)DeepAetherModItems.CLOUDIUM_BOOTS.get(), (Item)DeepAetherModItems.CLOUDIUM_GLOVES.get());
    }

    private static boolean hasArmorSet(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots, Item gloves) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                && entity.getItemBySlot(EquipmentSlot.CHEST).is(chestplate)
                && entity.getItemBySlot(EquipmentSlot.LEGS).is(leggings)
                && entity.getItemBySlot(EquipmentSlot.FEET).is(boots)
                && CuriosApi.getCuriosHelper().findFirstCurio(entity, gloves).isPresent();
    }
    public CloudiumAbility(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
            if(hasFullCloudiumSet(player)) {
                evaluateArmorEffects(player);
            }
        }
    }
    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasFullCloudiumSet(player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                            MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());
        if (hasFullCloudiumSet(player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));

            //if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
            //    player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});

            }
        }
    }