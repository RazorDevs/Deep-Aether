package teamrazor.deepaether.world.feature.tree.decorators;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DARootPlacers {

    public static final DeferredRegister<RootPlacerType<?>> ROOT_PLACERS = DeferredRegister.create(Registry.ROOT_PLACER_TYPE_REGISTRY, DeepAetherMod.MODID);
    public static final RegistryObject<RootPlacerType<YagrootRootPlacer>> YAGROOT_ROOT_PLACER = ROOT_PLACERS.register("yagroot_root_placer", () -> new RootPlacerType<>(YagrootRootPlacer.CODEC));
}
