package io.github.razordevs.deepaether.deepaether.world.feature.tree.foliage;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;

public class DAFoliagePlacers {
        public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(BuiltInRegistries.FOLIAGE_PLACER_TYPE, DeepAether.MODID);
        public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<RoserootFoliagePlacer>> ROSEROOT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("roseroot_foliage_placer", () -> new FoliagePlacerType<>(RoserootFoliagePlacer.CODEC));
        public static final DeferredHolder<FoliagePlacerType<?>,FoliagePlacerType<YagrootFoliagePlacer>> YAGROOT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("yagroot_foliage_placer", () -> new FoliagePlacerType<>(YagrootFoliagePlacer.CODEC));
}