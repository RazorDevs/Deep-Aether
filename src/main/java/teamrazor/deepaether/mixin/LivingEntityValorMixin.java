package teamrazor.deepaether.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.init.DAMobEffects;

@Mixin(value = LivingEntity.class)
public abstract class LivingEntityValorMixin {

    /**
     *  Adds a check to a LivingEntity for the Valkyrie Valor effect.
     *  Checks for the damage received from an undead mob and applies the equivalent of a Resistance II effect to it.
     *  Partial copy of the Resistance check code.
     */

    @Inject(at = @At("HEAD"), method = "getDamageAfterMagicAbsorb", cancellable = true)
    private void checkValkValorEffect(DamageSource damageSource, float damage, CallbackInfoReturnable<Float> cir) {
        LivingEntity undead = ((LivingEntity) damageSource.getEntity());
        if (((LivingEntity) (Object) this).hasEffect(DAMobEffects.VALKYRIE_VALOR.get()) && !damageSource.is(DamageTypeTags.BYPASSES_RESISTANCE) && undead.getMobType() == MobType.UNDEAD) {
            int j = 15;
            float f = damage * (float)j;
            float f1 = damage;
            damage = Math.max(f / 25.0F, 0.0F);
            float f2 = f1 - damage;
            if (f2 > 0.0F && f2 < 3.4028235E37F) {
                if (((LivingEntity) (Object) this) instanceof ServerPlayer) {
                    ((ServerPlayer)((LivingEntity) (Object) this)).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_RESISTED), Math.round(f2 * 10.0F));
                } else if (damageSource.getEntity() instanceof ServerPlayer) {
                    ((ServerPlayer) damageSource.getEntity()).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_DEALT_RESISTED), Math.round(f2 * 10.0F));
                }
            }
            cir.setReturnValue(damage);
        }
    }
}
