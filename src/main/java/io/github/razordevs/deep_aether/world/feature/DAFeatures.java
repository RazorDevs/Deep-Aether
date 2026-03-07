package io.github.razordevs.deep_aether.world.feature;

import com.aetherteam.aether.world.configuration.AercloudConfiguration;
import com.aetherteam.aether.world.configuration.AetherLakeConfiguration;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.world.feature.features.AercloudCloudFeature;
import io.github.razordevs.deep_aether.world.feature.features.DAHugeMushroomFeature;
import io.github.razordevs.deep_aether.world.feature.features.FallenTreeFeature;
import io.github.razordevs.deep_aether.world.feature.features.HolystoneColumnsFeature;
import io.github.razordevs.deep_aether.world.feature.features.PoisonLakeFeature;
import io.github.razordevs.deep_aether.world.feature.features.RainAercloudCloudFeature;
import io.github.razordevs.deep_aether.world.feature.features.RootFeature;
import io.github.razordevs.deep_aether.world.feature.features.TotemFeature;
import io.github.razordevs.deep_aether.world.feature.features.configuration.AercloudCloudConfiguration;
import io.github.razordevs.deep_aether.world.feature.features.configuration.DAHugeMushroomFeatureConfiguration;
import io.github.razordevs.deep_aether.world.feature.features.configuration.FallenTreeConfiguration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DAFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, DeepAether.MODID);
    public static final DeferredHolder<Feature<?>, Feature<FallenTreeConfiguration>> FALLEN_TREE = FEATURES.register("fallen_tree", () -> new FallenTreeFeature(FallenTreeConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> TOTEM = FEATURES.register("totem", () -> new TotemFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<AercloudCloudConfiguration>> AERCLOUD_CLOUD = FEATURES.register("aercloud_cloud", () -> new AercloudCloudFeature(AercloudCloudConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<AercloudCloudConfiguration>> RAIN_AERCLOUD_CLOUD = FEATURES.register("rain_aercloud_cloud", () -> new RainAercloudCloudFeature(AercloudCloudConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, RootFeature> AERCLOUD_ROOTS = FEATURES.register("aercloud_roots", () -> new RootFeature(AercloudConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<ColumnFeatureConfiguration>> HOLYSTONE_COLUMN = FEATURES.register("holystone_column", () -> new HolystoneColumnsFeature(ColumnFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<DAHugeMushroomFeatureConfiguration>> IMPROVED_MUSHROOM_FEATURE = FEATURES.register("improved_mushroom_feature", () -> new DAHugeMushroomFeature(DAHugeMushroomFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<AetherLakeConfiguration>> POISON_LAKE = FEATURES.register("poison_lake", () -> new PoisonLakeFeature(AetherLakeConfiguration.CODEC));
}

