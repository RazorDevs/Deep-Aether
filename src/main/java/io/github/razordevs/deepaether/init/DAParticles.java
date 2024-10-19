package io.github.razordevs.deepaether.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;

public class DAParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, DeepAether.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> POISON_BUBBLES = PARTICLE_TYPES.register("poison_bubbles",
            () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MYTHICAL_PARTICLE = PARTICLE_TYPES.register("mythical_particle",
            () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ROSEROOT_LEAVES = PARTICLE_TYPES.register("roseroot_leaves",
            () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FLOWERING_ROSEROOT_LEAVES = PARTICLE_TYPES.register("flowering_roseroot_leaves",
            () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EOTS_EXPLOSION = PARTICLE_TYPES.register("eots_explosion",
            () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EOTS_PRE_FIGHT = PARTICLE_TYPES.register("eots_pre_fight",
            () -> new SimpleParticleType(true));
}
