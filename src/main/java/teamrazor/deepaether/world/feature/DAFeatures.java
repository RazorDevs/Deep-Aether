package teamrazor.deepaether.world.feature;

import com.aetherteam.aether.world.configuration.AercloudConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.world.feature.features.FallenTreeFeature;
import teamrazor.deepaether.world.feature.features.RockyBumpsFeature;
import teamrazor.deepaether.world.feature.features.ModifiedAercloudFeature;
import teamrazor.deepaether.world.feature.features.configuration.FallenTreeConfiguration;

public class DAFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, DeepAetherMod.MODID);
    public static RegistryObject<Feature<AercloudConfiguration>> MODIFIED_AERCLOUD = FEATURES.register("aercloud", () -> new ModifiedAercloudFeature(AercloudConfiguration.CODEC));
    public static RegistryObject<Feature<FallenTreeConfiguration>> FALLEN_TREE = FEATURES.register("fallen_tree", () -> new FallenTreeFeature(FallenTreeConfiguration.CODEC));
    //public static RegistryObject<Feature<NoneFeatureConfiguration>> ROCKY_BUMPS = FEATURES.register("rocky_bumps", new RockyBumpsFeature(NoneFeatureConfiguration.CODEC));
}

