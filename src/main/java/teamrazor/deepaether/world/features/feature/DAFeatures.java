package teamrazor.deepaether.world.features.feature;

import com.aetherteam.aether.world.configuration.AercloudConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.world.features.configuration.CloudPillarConfiguration;

public class DAFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, DeepAetherMod.MODID);
    public static RegistryObject<Feature<AercloudConfiguration>> MODIFIED_AERCLOUD = FEATURES.register("modified_aercloud", () -> new ModifiedAercloudFeature(AercloudConfiguration.CODEC));
    public static RegistryObject<Feature<CloudPillarConfiguration>> AERCLOUD_PILLAR = FEATURES.register("aercloud_pillar", () -> new CloudPillarFeature(CloudPillarConfiguration.CODEC));

}

