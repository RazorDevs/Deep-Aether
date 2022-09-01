package teamrazor.deepaether.init;

/*
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.*;

import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.block.PoisonBlock;

public class DeepAetherModFluids {
    public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(ForgeRegistries.FLUIDS, DeepAetherMod.MODID);

    public static final ResourceLocation POISON_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation POISON_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation POISON_OVERLAY_RL = new ResourceLocation("block/water_overlay");


    public static final RegistryObject<FlowingFluid> POISON_FLUID = REGISTRY.register("poison_fluid", () -> new ForgeFlowingFluid.Source(DeepAetherModFluids.POISON_PROPERTIES));

    public static final RegistryObject<FlowingFluid> POISON_FLOWING = REGISTRY.register("poison_flowing", () -> new ForgeFlowingFluid.Flowing(DeepAetherModFluids.POISON_PROPERTIES));


    public static final ForgeFlowingFluid.Properties POISON_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> POISON_FLUID.get(), () -> POISON_FLOWING.get(), FluidAttributes.builder(POISON_STILL_RL, POISON_FLOWING_RL)
            .density(15)
            .luminosity(1)
            .viscosity(8)
            .overlay(POISON_OVERLAY_RL)
            .color(0x7F00FF))
            .slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> DeepAetherModFluids.POISON_BLOCK.get()).bucket(() -> DeepAetherModItems.PLACEABLE_POISON_BUCKET.get());

    public static final RegistryObject<LiquidBlock> POISON_BLOCK = DeepAetherModBlocks.REGISTRY.register("poison", () -> new PoisonBlock(() -> DeepAetherModFluids.POISON_FLUID.get(), BlockBehaviour.Properties.of(Material.LAVA)
            .noCollission()
            .strength(100f)
            .noLootTable()));
}
*/