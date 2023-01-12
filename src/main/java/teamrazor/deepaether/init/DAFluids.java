package teamrazor.deepaether.init;


import net.minecraft.world.level.material.*;

import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.fluids.DAFluidTypes;

public class DAFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, DeepAetherMod.MODID);

    public static final RegistryObject<FlowingFluid> POISON_FLUID = FLUIDS.register("poison_fluid", () -> new ForgeFlowingFluid.Source(DAFluids.POISON_PROPERTIES));

    public static final RegistryObject<FlowingFluid> POISON_FLOWING = FLUIDS.register("poison_flowing", () -> new ForgeFlowingFluid.Flowing(DAFluids.POISON_PROPERTIES));

    public static final ForgeFlowingFluid.Properties POISON_PROPERTIES = new ForgeFlowingFluid.Properties(DAFluidTypes.POISON_FLUID_TYPE, POISON_FLUID, POISON_FLOWING)
            .slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(DABlocks.POISON_BLOCK).bucket(DAItems.PLACEABLE_POISON_BUCKET);
}
