package io.github.razordevs.deep_aether.item.gear.other;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.item.gear.DAEquipmentUtil;
import io.github.razordevs.deep_aether.mixin.AetherSkyRenderEffectsAccessor;
import io.wispforest.accessories.api.slot.SlotReference;
import io.wispforest.accessories.api.slot.SlotType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Supplier;

public class SpookyRing extends RingItem {

    public SpookyRing(Holder<SoundEvent> ringSound, Properties properties) {
        super(ringSound, properties);
    }

    Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();

    private int calculateIncrease(SlotReference context) {
        Level level = context.entity().level();
        int a = (int) level.getDayTime();
        if (level.isNight() && a < 13000) {
            return Mth.abs(a - 18000) / 2000;
        } else return 0;
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        SpookyMoonConditions(reference);
        AttributeInstance damage = reference.entity().getAttribute(Attributes.ATTACK_DAMAGE);
        if(damage != null) {
            AttributeModifier attribute = damage.getModifier(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "spooky_bonus_damage"));
            if (attribute != null) {
                damage.removeModifier(attribute);
                attributes.put(Attributes.ATTACK_DAMAGE.value(), createSpookyRingAttribute(this.calculateIncrease(reference)));

            } else
                attributes.put(Attributes.ATTACK_DAMAGE.value(), createSpookyRingAttribute(this.calculateIncrease(reference)));
        }
    }

    private AttributeModifier createSpookyRingAttribute(double amount) {
        return new AttributeModifier(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "spooky_bonus_damage"), amount, AttributeModifier.Operation.ADD_VALUE);
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        super.onUnequip(stack, reference);
        SpookyMoonConditions(reference);
    }

    //Little easter egg
    public static void SpookyMoonConditions(SlotReference slotReference) {
        Level level = slotReference.entity().level();
        if (level.isClientSide()) {
            LevelRenderer.MOON_LOCATION = DAEquipmentUtil.hasTwoSpookyRings(slotReference.entity()) & level.getMoonPhase() == 0 && DeepAether.IS_HALLOWEEN ? ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/environment/spooky_moon_phases.png") : ResourceLocation.withDefaultNamespace("textures/environment/moon_phases.png");
            AetherSkyRenderEffectsAccessor.setMOON_LOCATION(DAEquipmentUtil.hasTwoSpookyRings(slotReference.entity()) && level.getMoonPhase() == 0 && DeepAether.IS_HALLOWEEN ? ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/environment/spooky_moon_phases.png") : ResourceLocation.withDefaultNamespace("textures/environment/moon_phases.png"));
        }
    }

    @Override
    public void getAttributesTooltip(ItemStack stack, SlotType type, List<Component> tooltips, TooltipContext tooltipContext, TooltipFlag tooltipType) {
        tooltips.add(Component.translatable("gui.deep_aether.spooky_ring").withStyle(ChatFormatting.DARK_RED));
    }
}
