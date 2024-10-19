package io.github.razordevs.deep_aether.init;


import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.fluids.PoisonFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DAFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, DeepAether.MODID);

    public static final DeferredHolder<Fluid, FlowingFluid> POISON_FLUID = FLUIDS.register("poison_fluid", PoisonFluid.Source::new);

    public static final DeferredHolder<Fluid, FlowingFluid> POISON_FLOWING = FLUIDS.register("poison_flowing", PoisonFluid.Flowing::new);
}
