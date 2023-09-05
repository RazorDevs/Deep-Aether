package teamrazor.deepaether.world.features.tree.trunk;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DaTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, DeepAetherMod.MODID);

    public static final RegistryObject<TrunkPlacerType<TwinTrunkPlacer>> TWIN_TRUNK_PLACER = TRUNK_PLACERS.register("twin_trunk_placer", () -> new TrunkPlacerType<>(TwinTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<YagrootTrunkPlacer>> YAGROOT_TRUNK_PLACER = TRUNK_PLACERS.register("yagroot_trunk_placer", () -> new TrunkPlacerType<>(YagrootTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<HolyrootTrunkPlacer>> HOLYROOT_TRUNK_PLACER = TRUNK_PLACERS.register("holyroot_trunk_placer", () -> new TrunkPlacerType<>(HolyrootTrunkPlacer.CODEC));
}