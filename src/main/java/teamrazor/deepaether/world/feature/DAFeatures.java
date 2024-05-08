package teamrazor.deepaether.world.feature;

import com.aetherteam.aether.world.configuration.AercloudConfiguration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.world.feature.features.FallenTreeFeature;
import teamrazor.deepaether.world.feature.features.ModifiedAercloudFeature;
import teamrazor.deepaether.world.feature.features.configuration.FallenTreeConfiguration;

public class DAFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, DeepAether.MODID);
    public static DeferredHolder<Feature<?>, Feature<AercloudConfiguration>> MODIFIED_AERCLOUD = FEATURES.register("aercloud", () -> new ModifiedAercloudFeature(AercloudConfiguration.CODEC));
    public static DeferredHolder<Feature<?>, Feature<FallenTreeConfiguration>> FALLEN_TREE = FEATURES.register("fallen_tree", () -> new FallenTreeFeature(FallenTreeConfiguration.CODEC));
    //public static RegistryObject<Feature<NoneFeatureConfiguration>> ROCKY_BUMPS = FEATURES.register("rocky_bumps", new RockyBumpsFeature(NoneFeatureConfiguration.CODEC));
}

