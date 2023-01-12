package teamrazor.deepaether.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DAParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, DeepAetherMod.MODID);

    public static final RegistryObject<SimpleParticleType> POISON_BUBBLES = PARTICLE_TYPES.register("poison_bubbles",
            () -> new SimpleParticleType(true));
}
