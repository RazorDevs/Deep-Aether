package teamrazor.deepaether.world.feature.tree.decorators;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;

public class DARootPlacers {
    public static final DeferredRegister<RootPlacerType<?>> ROOT_PLACERS = DeferredRegister.create(BuiltInRegistries.ROOT_PLACER_TYPE, DeepAether.MODID);
    public static final DeferredHolder<RootPlacerType<?>, RootPlacerType<YagrootRootPlacer>> YAGROOT_ROOT_PLACER = ROOT_PLACERS.register("yagroot_root_placer", () -> new RootPlacerType<>(YagrootRootPlacer.CODEC));
}
