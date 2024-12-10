package teamrazor.deepaether.item.dungeon.brass;

import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.client.multiplayer.ClientLevel;
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
import teamrazor.deepaether.networking.DeepAetherPlayer;

public class BladeOfLuckItem extends SwordItem {
    public BladeOfLuckItem(Tier tier, int a, float b, Properties properties) {
        super(tier, a, b, properties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (player.level().isClientSide() && player.getAttackStrengthScale(0) >= 1) {
            DeepAetherPlayer.get(player).ifPresent((daPlayer) -> {
                Player clientPlayer = daPlayer.getPlayer();
                SimpleParticleType particleType;
                if (daPlayer.getBladeOfLuckDamage() <= 3) {
                    particleType = DAParticles.CLOVER_UNLUCKY.get();
                    clientPlayer.level().playSound(clientPlayer, clientPlayer.getX(), clientPlayer.getY(), clientPlayer.getZ(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
                } else if (daPlayer.getBladeOfLuckDamage() <= 8) {
                    particleType = DAParticles.CLOVER.get();
                } else if (daPlayer.getBladeOfLuckDamage() <= 16) {
                    particleType = DAParticles.CLOVER_LUCKY.get();
                } else {
                    particleType = DAParticles.CLOVER_VERY_LUCKY.get();
                    clientPlayer.level().playSound(clientPlayer, clientPlayer.getX(), clientPlayer.getY(), clientPlayer.getZ(), SoundEvents.ARROW_HIT_PLAYER, SoundSource.PLAYERS, 1.0F, 1.0F);
                }


                RandomSource random = clientPlayer.getRandom();
                for (int i = 0; i < 10; i++) {
                    ((ClientLevel) clientPlayer.level()).addParticle(particleType, clientPlayer.getX() + random.nextFloat(), clientPlayer.getY() + random.nextFloat(), clientPlayer.getZ() + random.nextFloat(),
                            0, 0, 0);
                }
            });
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean flag = super.hurtEnemy(stack, target, attacker);

        if(flag && attacker instanceof Player player && !target.level().isClientSide() && player.getAttackStrengthScale(0) >= 1) {
            DeepAetherPlayer.get(player).ifPresent((daPlayer) -> {
            target.invulnerableTime = 0;
            target.hurt(player.level().damageSources().playerAttack(player), daPlayer.getBladeOfLuckDamage());

            daPlayer.setSynched(INBTSynchable.Direction.CLIENT,"setBladeOfLuckDamage", player.level().getRandom().nextInt(21));
        });
        }
        return flag;
    }
}
