package teamrazor.deepaether.item.misc;

import com.aetherteam.aether.client.AetherSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import teamrazor.deepaether.init.DATiers;

public class FlamethrowerItem extends Item implements Vanishable {
    public FlamethrowerItem(Properties properties) {
        super(properties);
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

            if(!player.isCreative())
                itemStack.hurt(1, RandomSource.create(), null);
        }
        super.onUseTick(level, livingEntity, itemStack, i);
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        Player player = (Player) entity;

        if(count < stack.getUseDuration() - 20 && stack.getMaxDamage() > stack.getDamageValue()) {
            if(!player.isCreative())
                player.getCooldowns().addCooldown(this, 100);
        }
        super.onStopUsing(stack, entity, count);
    }

    public int getUseDuration(ItemStack itemStack) {
        return 60;
    }

    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }
}
