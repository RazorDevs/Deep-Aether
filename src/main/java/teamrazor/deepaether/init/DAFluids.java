package teamrazor.deepaether.init;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.fluids.PoisonFluid;

public class DAFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, DeepAether.MODID);

    public static final DeferredHolder<Fluid, FlowingFluid> POISON_FLUID = FLUIDS.register("poison_fluid", PoisonFluid.Source::new);

    public static final DeferredHolder<Fluid, FlowingFluid> POISON_FLOWING = FLUIDS.register("poison_flowing", PoisonFluid.Flowing::new);
}
