package teamrazor.deepaether.world.feature;

import com.aetherteam.aether.world.configuration.AercloudConfiguration;
import com.aetherteam.aether.world.configuration.AetherLakeConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.world.feature.features.AercloudCloudFeature;
import teamrazor.deepaether.world.feature.features.FallenTreeFeature;
import teamrazor.deepaether.world.feature.features.ModifiedAercloudFeature;
import teamrazor.deepaether.world.feature.features.PoisonLakeFeature;
import teamrazor.deepaether.world.feature.features.configuration.AercloudCloudConfiguration;
import teamrazor.deepaether.world.feature.features.configuration.FallenTreeConfiguration;

public class DAFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, DeepAether.MODID);
    public static final RegistryObject<Feature<AercloudConfiguration>> MODIFIED_AERCLOUD = FEATURES.register("aercloud", () -> new ModifiedAercloudFeature(AercloudConfiguration.CODEC));
    public static final RegistryObject<Feature<FallenTreeConfiguration>> FALLEN_TREE = FEATURES.register("fallen_tree", () -> new FallenTreeFeature(FallenTreeConfiguration.CODEC));
    public static final RegistryObject<Feature<AercloudCloudConfiguration>> AERCLOUD_CLOUD = FEATURES.register("aercloud_cloud", () -> new AercloudCloudFeature(AercloudCloudConfiguration.CODEC));
    public static final RegistryObject<Feature<AetherLakeConfiguration>> POISON_LAKE = FEATURES.register("poison_lake", () -> new PoisonLakeFeature(AetherLakeConfiguration.CODEC));
}

