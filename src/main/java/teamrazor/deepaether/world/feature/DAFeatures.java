package teamrazor.deepaether.world.feature;

import com.aetherteam.aether.world.configuration.AercloudConfiguration;
import com.aetherteam.aether.world.feature.AercloudFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.world.feature.features.AercloudCloudFeature;
import teamrazor.deepaether.world.feature.features.FallenTreeFeature;
import teamrazor.deepaether.world.feature.features.ModifiedAercloudFeature;
import teamrazor.deepaether.world.feature.features.configuration.AercloudCloudConfiguration;
import teamrazor.deepaether.world.feature.features.*;
import teamrazor.deepaether.world.feature.features.configuration.FallenTreeConfiguration;

public class DAFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, DeepAether.MODID);
    public static DeferredHolder<Feature<?>, Feature<FallenTreeConfiguration>> FALLEN_TREE = FEATURES.register("fallen_tree", () -> new FallenTreeFeature(FallenTreeConfiguration.CODEC));
    //public static RegistryObject<Feature<NoneFeatureConfiguration>> ROCKY_BUMPS = FEATURES.register("rocky_bumps", new RockyBumpsFeature(NoneFeatureConfiguration.CODEC));

    public static DeferredHolder<Feature<?>, Feature<AercloudCloudConfiguration>> AERCLOUD_CLOUD = FEATURES.register("aercloud_cloud", () -> new AercloudCloudFeature(AercloudCloudConfiguration.CODEC));
    public static DeferredHolder<Feature<?>, Feature<ConfiguredBoulder.Config>> CONFIGURED_BOULDER = FEATURES.register("configured_boulder", () -> new ConfiguredBoulder(ConfiguredBoulder.Config.CODEC));
    public static DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROCK_SPIKE = FEATURES.register("rock_spike", () -> new RockSpikeFeature(NoneFeatureConfiguration.CODEC));
    public static DeferredHolder<Feature<?>, Feature<ColumnFeatureConfiguration>> CLORITE_COLUMNS = FEATURES.register("clorite_columns", () -> new CloriteColumnsFeature(ColumnFeatureConfiguration.CODEC));
}

