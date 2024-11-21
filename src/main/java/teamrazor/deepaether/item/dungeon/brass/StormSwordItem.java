package teamrazor.deepaether.item.dungeon.brass;

import com.aetherteam.aether.item.EquipmentUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DAItems;

@Mod.EventBusSubscriber(modid = DeepAether.MODID)
public class StormSwordItem extends SwordItem {

    public StormSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        DamageSource damageSource = event.getEntity().getLastDamageSource();
        if(damageSource == null)
            return;
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
