package teamrazor.deepaether.item.misc;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DASquashPieItem extends Item {
    MobEffect[] effects;
    public DASquashPieItem(Properties properties, MobEffect[] effects) {
        super(properties);
        effects = effects;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity player) {
        player.addEffect(new MobEffectInstance(effects[level.random.nextInt(effects.length)], 300));

        return super.finishUsingItem(stack, level, player);
    }
}
