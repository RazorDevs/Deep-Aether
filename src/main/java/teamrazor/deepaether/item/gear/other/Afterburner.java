package teamrazor.deepaether.item.gear.other;

import com.aetherteam.aether.client.AetherSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import teamrazor.deepaether.entity.FireProjectile;
import teamrazor.deepaether.init.DATiers;

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
            level.playLocalSound(player.getX(), player.getY(), player.getZ(), (SoundEvent) AetherSoundEvents.ITEM_HAMMER_OF_KINGBDOGZ_SHOOT.get(), SoundSource.PLAYERS, 1.0F, 1.0F / (livingEntity.getRandom().nextFloat() * 0.4F + 0.8F), false);
        }
        super.onUseTick(level, livingEntity, itemStack, i);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        Player player = (Player) entity;

        if(entity.getTicksUsingItem() > 20 && stack.getMaxDamage() > stack.getDamageValue()) {
            if(!player.isCreative()) {
                player.getCooldowns().addCooldown(this, 100);
                stack.hurt(10, RandomSource.create(), null);
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int count) {
        if(entity instanceof Player player)
            if(count < stack.getUseDuration() - 20 && stack.getMaxDamage() > stack.getDamageValue()) {
                if(!player.isCreative()) {
                    player.getCooldowns().addCooldown(this, 100);
                    stack.hurt(10, RandomSource.create(), null);
                }
            }

        super.releaseUsing(stack, level, entity, count);
    }

    public int getUseDuration(ItemStack itemStack) {
        return 60;
    }

    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

}
