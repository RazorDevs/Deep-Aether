package io.github.razordevs.deepaether.deepaether.item.dungeon.brass;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import io.github.razordevs.deepaether.deepaether.entity.StormArrow;

public class StormBowItem extends BowItem {
    public StormBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow customArrow(AbstractArrow arrow, ItemStack stack) {
        if(arrow.getOwner() instanceof LivingEntity)
            return new StormArrow((LivingEntity) arrow.getOwner(), arrow.level(), stack);
        else return super.customArrow(arrow, stack);
    }
}
