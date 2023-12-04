package teamrazor.deepaether.item.misc;

import com.aetherteam.aether.client.AetherSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SimpleFoiledItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class FlamethrowerItem extends SimpleFoiledItem {
    public FlamethrowerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack heldStack = player.getItemInHand(interactionHand);
        if (!level.isClientSide()) {
            player.startUsingItem(interactionHand);
            return InteractionResultHolder.consume(heldStack);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.success(heldStack);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int i) {
        Player player = (Player) livingEntity;
        if(player.getTicksUsingItem() > 20) {
            FireProjectile hammerProjectile = new FireProjectile(player, level);
            hammerProjectile.shoot(player.getXRot(), player.getYRot(), 0.5F, 11.0F);
            level.addFreshEntity(hammerProjectile);
            itemStack.setDamageValue(getDamage(itemStack) - 1);
            level.playLocalSound(player.getX(), player.getY(), player.getZ(), (SoundEvent) AetherSoundEvents.ITEM_HAMMER_OF_KINGBDOGZ_SHOOT.get(), SoundSource.PLAYERS, 1.0F, 1.0F / (livingEntity.getRandom().nextFloat() * 0.4F + 0.8F), false);
        }
        super.onUseTick(level, livingEntity, itemStack, i);
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        Player player = (Player) entity;
        if(count < stack.getUseDuration() - 20)
            player.getCooldowns().addCooldown(this, 100);
        super.onStopUsing(stack, entity, count);
    }

    public int getUseDuration(ItemStack itemStack) {
        return 80;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 200;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

    @Override
    public boolean canBeDepleted() {
        return true;
    }
}
