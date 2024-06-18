package teamrazor.deepaether.item.dungeon.brass;

import com.aetherteam.aether.item.EquipmentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DAItems;

@Mod.EventBusSubscriber(modid = DeepAether.MODID)
public class StormSwordItem extends SwordItem {
    public StormSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        DamageSource damageSource = event.getSource();
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
