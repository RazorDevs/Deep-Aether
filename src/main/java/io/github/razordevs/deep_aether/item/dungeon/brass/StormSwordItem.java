package io.github.razordevs.deep_aether.item.dungeon.brass;

import com.aetherteam.aether.item.EquipmentUtil;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = DeepAether.MODID)
public class StormSwordItem extends SwordItem {
    public StormSwordItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        DamageSource damageSource = event.getEntity().getLastDamageSource();
        if (canPerformAbility(damageSource)) {
            if(damageSource.getEntity() != null) {
                LivingEntity target = event.getEntity();
                Entity attacker = damageSource.getEntity();

                target.knockback(1.5F,
                        Mth.sin(attacker.getYRot() * (float) (Math.PI / 180.0)),
                        -Mth.cos(attacker.getYRot() * (float) (Math.PI / 180.0))
                );
            }
        }
    }
    private static boolean canPerformAbility(DamageSource source) {
        if (source.getDirectEntity() instanceof LivingEntity attacker) {
            if (EquipmentUtil.isFullStrength(attacker)) {
                return attacker.getMainHandItem().is(DAItems.STORM_SWORD.get());
            }
        }
        return false;
    }
}
