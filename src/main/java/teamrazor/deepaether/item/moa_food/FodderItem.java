package teamrazor.deepaether.item.moa_food;

import com.aetherteam.aether.entity.passive.Moa;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FodderItem extends Item {
    public FodderItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if(!(livingEntity instanceof Moa))
            return InteractionResult.FAIL;

        if(!player.isCreative())
            itemStack.shrink(1);

        if(applyMoaEffect(livingEntity, itemStack))
            return InteractionResult.SUCCESS;

        return InteractionResult.PASS;
    }

    @Override
    public boolean isEdible() {
        return false;
    }

    private boolean applyMoaEffect(LivingEntity livingEntity, ItemStack stack) {
        MobEffectInstance fodder = getMobEffect(stack);
        if(fodder == null) return false;

        if (livingEntity.addEffect(fodder)) {
            livingEntity.level().playLocalSound(livingEntity.blockPosition(), SoundEvents.PLAYER_BURP, SoundSource.AMBIENT, 1f, 0.2f, true);
            return true;
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        MobEffectInstance fodder = getMobEffect(stack);
        if(fodder != null)
            PotionUtils.addPotionTooltip(List.of(fodder), components, 1.0F);
        super.appendHoverText(stack, level, components, flag);
    }

    @Nullable
    public MobEffectInstance getMobEffect(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if(tag != null) {

            int amplifier;
            int time;
            String effect;

            if(tag.contains("amplifier")) {
                amplifier = tag.getInt("amplifier");
            }
            else amplifier = 0;
            if(tag.contains("time")) {
                time = tag.getInt("time");
            }
            else time = 14400;
            if(tag.contains("effect")) {
                effect = tag.getString("effect");
            }
            else return null;

            MobEffect mobEffect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(effect));
            if(mobEffect == null)
                return null;
            else {
                return new MobEffectInstance(mobEffect, time, amplifier);
            }
        }
        else return null;
    }
}
