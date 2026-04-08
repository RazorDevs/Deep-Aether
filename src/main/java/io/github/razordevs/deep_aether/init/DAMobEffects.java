package io.github.razordevs.deep_aether.init;

import com.aetherteam.aether.entity.ai.attribute.AetherAttributes;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.effects.MobEffectAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DAMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, DeepAether.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> MOA_BONUS_JUMPS = EFFECTS.register("moa_bonus_jumps" , () -> new MobEffectAccessor(MobEffectCategory.BENEFICIAL, 5031241)
            .addAttributeModifier(AetherAttributes.MOA_MAX_JUMPS, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "effect_extra_jumps"), 1.0, AttributeModifier.Operation.ADD_VALUE));

    public static final DeferredHolder<MobEffect, MobEffect> VALKYRIE_VALOR = EFFECTS.register("valkyrie_valor", () -> new MobEffectAccessor(MobEffectCategory.BENEFICIAL, 25525551));
}
