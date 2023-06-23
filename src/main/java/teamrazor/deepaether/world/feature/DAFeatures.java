package teamrazor.deepaether.world.feature;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.world.configuration.AercloudConfiguration;
import com.aetherteam.aether.world.configuration.AetherLakeConfiguration;
import com.aetherteam.aether.world.configuration.ShelfConfiguration;
import com.aetherteam.aether.world.feature.AercloudFeature;
import com.aetherteam.aether.world.feature.AetherLakeFeature;
import com.aetherteam.aether.world.feature.CrystalIslandFeature;
import com.aetherteam.aether.world.feature.ShelfFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DAFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, DeepAetherMod.MODID);
    public static RegistryObject<Feature<AercloudConfiguration>> MODIFIED_AERCLOUD = FEATURES.register("aercloud", () -> new ModifiedAercloudFeature(AercloudConfiguration.CODEC));
}

