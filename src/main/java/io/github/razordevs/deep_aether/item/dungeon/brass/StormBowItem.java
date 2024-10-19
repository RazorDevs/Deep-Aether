package io.github.razordevs.deep_aether.item.dungeon.brass;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import io.github.razordevs.deep_aether.entity.StormArrow;

public class StormBowItem extends BowItem {
    public StormBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
        if(arrow.getOwner() instanceof LivingEntity)
            return new StormArrow((LivingEntity) arrow.getOwner(), arrow.level(), projectileStack, weaponStack);
        else return super.customArrow(arrow, projectileStack, weaponStack);
    }
}
