package io.github.razordevs.deep_aether.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.List;

public class AntidoteItem extends Item {
    private final boolean enchanted;
    private final MobEffectInstance instance;

    public AntidoteItem(boolean enchanted, Properties properties, MobEffectInstance instance) {
        super(properties);
        this.enchanted = enchanted;
        this.instance = instance;
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return enchanted;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
        return 40;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41358_) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        PotionContents.addPotionTooltip(List.of(instance), tooltipComponents::add, 1.0F, context.level() == null ? 20.0F : context.tickRate());
    }
}
