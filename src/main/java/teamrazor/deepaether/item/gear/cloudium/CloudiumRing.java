package teamrazor.deepaether.item.gear.cloudium;


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
    float pTime;
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        pTime += 0.02;
        LivingEntity livingEntity = slotContext.entity();
        if (!livingEntity.isOnGround()) {
            if(pTime >= 1.5)
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 4, 2, false, false));
        }
        else {
            pTime = 0;
        }
    }
}