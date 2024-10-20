package teamrazor.deepaether.item.dungeon.brass;

import com.aetherteam.nitrogen.attachment.INBTSynchable;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import teamrazor.deepaether.init.DAParticles;
import teamrazor.deepaether.networking.attachment.DAAttachments;
import teamrazor.deepaether.networking.attachment.DAPlayerAttachment;

public class BladeOfLuckItem extends SwordItem {
    public BladeOfLuckItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(player.level().isClientSide()) {
            DAPlayerAttachment attachment = player.getData(DAAttachments.PLAYER);

            SimpleParticleType particleType;
            if (attachment.getBladeOfLuckDamage() <= 3) {
                particleType = DAParticles.CLOVER_UNLUCKY.get();
                player.level().playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS);
            }
            else if(attachment.getBladeOfLuckDamage() <= 10) {
                particleType = DAParticles.CLOVER.get();
            }
            else if (attachment.getOldBladeOfLuckDamage() <= 15) {
                particleType = DAParticles.CLOVER_LUCKY.get();
            }
            else{
                particleType = DAParticles.CLOVER_VERY_LUCKY.get();
                player.level().playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_HIT_PLAYER, SoundSource.PLAYERS);
            }

            RandomSource random = player.getRandom();
            for (int i = 0; i < 10; i++) {
                player.level().addParticle(particleType, entity.getX() + random.nextFloat(), entity.getY() + random.nextFloat(), entity.getZ() + random.nextFloat(),
                        0,0,0);
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean flag = super.hurtEnemy(stack, target, attacker);

        if(flag && attacker instanceof Player player && !target.level().isClientSide() && player.getAttackStrengthScale(0) >= 1) {
            DAPlayerAttachment attachment = player.getData(DAAttachments.PLAYER);
            target.invulnerableTime = 0;
            System.out.println(attachment.getBladeOfLuckDamage());
            target.hurt(player.level().damageSources().playerAttack(player), attachment.getBladeOfLuckDamage());

            attachment.setSynched(player.getId(), INBTSynchable.Direction.CLIENT,"setBladeOfLuckDamage", player.level().getRandom().nextInt(21));
        }

        return flag;
    }
}
