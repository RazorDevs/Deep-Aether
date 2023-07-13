package teamrazor.deepaether.world.features.tree.foliage;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DAFoliagePlacers {
        public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, DeepAetherMod.MODID);
        public static final RegistryObject<FoliagePlacerType<RoserootFoliagePlacer>> ROSEROOT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("roseroot_foliage_placer", () -> new FoliagePlacerType<>(RoserootFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<YagrootFoliagePlacer>> YAGROOT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("yagroot_foliage_placer", () -> new FoliagePlacerType<>(YagrootFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<SunrootFoliagePlacer>> SUNROOT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("sunroot_foliage_placer", () -> new FoliagePlacerType<>(SunrootFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<HolyrootFoliagePlacer>> HOLYROOT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("holyroot_foliage_placer", () -> new FoliagePlacerType<>(HolyrootFoliagePlacer.CODEC));
        public static final RegistryObject<FoliagePlacerType<OldFoliagePlacer>> OLD_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("old_foliage_placer", () -> new FoliagePlacerType<>(OldFoliagePlacer.CODEC));

}