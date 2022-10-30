package teamrazor.deepaether.item.accessory;


import com.gildedgames.aether.item.accessories.AccessoryItem;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.item.ItemStack;

import top.theillusivec4.curios.api.SlotContext;





public class CloudiumRing extends AccessoryItem {
    public CloudiumRing(Properties properties) {
        super(properties);

    }




    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (!livingEntity.isOnGround()) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 5, 2));
        }
    }
}