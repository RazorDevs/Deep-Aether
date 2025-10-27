package io.github.razordevs.deep_aether.item.misc;

import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootRemedyBucketItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class IronRemedyBucketItem extends SkyrootRemedyBucketItem {
    public IronRemedyBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        if (!level.isClientSide()) {
            user.addEffect(new MobEffectInstance(AetherEffects.REMEDY, 200, 0, false, false, true));
        }
        this.consume(this, stack, user);
        return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
    }

}
