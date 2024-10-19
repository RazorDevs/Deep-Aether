package io.github.razordevs.deepaether.deepaether.item.misc;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class AntidoteItem extends Item {
    private boolean enchanted;

    public AntidoteItem(boolean enchanted, Properties properties) {
        super(properties);
        this.enchanted = enchanted;
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return enchanted;
    }

    public int getUseDuration(ItemStack p_41360_) {
        return 40;
    }

    public UseAnim getUseAnimation(ItemStack p_41358_) {
        return UseAnim.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
