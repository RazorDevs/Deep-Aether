package io.github.razordevs.deepaether.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import io.github.razordevs.deepaether.deepaether.effects.MoaBonusJumpEffect;
import io.github.razordevs.deepaether.deepaether.effects.ValkyrieValorEffect;

public class DAMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, DeepAether.MODID);
    public static final DeferredHolder<MobEffect, MoaBonusJumpEffect> MOA_BONUS_JUMPS = EFFECTS.register("moa_bonus_jumps", MoaBonusJumpEffect::new);
    public static final DeferredHolder<MobEffect, ValkyrieValorEffect> VALKYRIE_VALOR = EFFECTS.register("valkyrie_valor", ValkyrieValorEffect::new);
}
