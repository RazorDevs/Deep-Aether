package io.github.razordevs.deep_aether.world.feature.tree.trunk;
import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DaTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, DeepAether.MODID);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<TwinTrunkPlacer>> TWIN_TRUNK_PLACER = TRUNK_PLACERS.register("twin_trunk_placer", () -> new TrunkPlacerType<>(TwinTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<YagrootTrunkPlacer>> YAGROOT_TRUNK_PLACER = TRUNK_PLACERS.register("yagroot_trunk_placer", () -> new TrunkPlacerType<>(YagrootTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<SunrootTunkPlacer>> SUNROOT_TRUNK_PLACER = TRUNK_PLACERS.register("sunroot_trunk_placer", () -> new TrunkPlacerType<>(SunrootTunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<ImprovedStraightTrunkPlacer>> IMPROVED_STRAIGHT_TRUNK_PLACER = TRUNK_PLACERS.register("improved_straight_trunk_placer", () -> new TrunkPlacerType<>(ImprovedStraightTrunkPlacer.CODEC));

}