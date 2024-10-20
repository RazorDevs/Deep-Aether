package io.github.razordevs.deep_aether.item.gear.other;

import io.github.razordevs.deep_aether.entity.living.projectile.FireProjectile;
import io.github.razordevs.deep_aether.init.DASounds;
import io.github.razordevs.deep_aether.init.DATiers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class Afterburner extends TieredItem {
    public Afterburner(Properties properties) {
        super(DATiers.FIRE, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack heldStack = player.getItemInHand(interactionHand);
        if (!level.isClientSide()) {
            player.startUsingItem(interactionHand);
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.success(heldStack);
        }
        return InteractionResultHolder.fail(heldStack);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int i) {
        Player player = (Player) livingEntity;
        if(player.getTicksUsingItem() > 20 && itemStack.getMaxDamage() > itemStack.getDamageValue()) {

            FireProjectile fireProjectile = new FireProjectile(player, level);
            fireProjectile.shoot(player.getXRot(), player.getYRot(), 1.0F, 11.0F);

            level.addFreshEntity(fireProjectile);
            level.playLocalSound(player.getX(), player.getY(), player.getZ(), DASounds.ITEM_AFTERBURNER_FIRES.get(), SoundSource.PLAYERS, 1.0F, 1.0F / (livingEntity.getRandom().nextFloat() * 0.4F + 0.8F), false);
        }
        super.onUseTick(level, livingEntity, itemStack, i);
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        if(entity instanceof Player player)
            if(count < stack.getUseDuration(entity) - 20 && stack.getMaxDamage() > stack.getDamageValue()) {
                if(!player.isCreative()) {
                    player.getCooldowns().addCooldown(this, 100);
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(InteractionHand.MAIN_HAND));
                }
            }
        super.onStopUsing(stack, entity, count);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 60;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

}
