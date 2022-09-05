package teamrazor.deepaether.deepaether.fluids;

import com.mojang.math.Vector3f;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.fluids.BaseFluidType;

public class DeepAetherModFluidTypes {
    public static final ResourceLocation POISON_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation POISON_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation POISON_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, DeepAetherMod.MODID);

    public static final RegistryObject<FluidType> POISON_FLUID_TYPE = register("poison_fluid", FluidType.Properties.create().density(15).viscosity(8));


    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return REGISTRY.register(name, () -> new BaseFluidType(POISON_STILL_RL, POISON_FLOWING_RL, POISON_OVERLAY_RL,
                0x7F00FF, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }
}