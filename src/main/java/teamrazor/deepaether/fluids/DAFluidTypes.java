package teamrazor.deepaether.fluids;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;
import teamrazor.deepaether.DeepAetherMod;

public class DAFluidTypes {
    public static final ResourceLocation POISON_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation POISON_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation POISON_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, DeepAetherMod.MODID);

    public static final RegistryObject<FluidType> POISON_FLUID_TYPE = register("poison_fluid", FluidType.Properties.create()
            .descriptionId("block.deep_aether.poison")
            .canSwim(false));
    
    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(POISON_STILL_RL, POISON_FLOWING_RL, POISON_OVERLAY_RL,
                0xffAB5AFD, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }
}