package teamrazor.deepaether.world.feature;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.core.Holder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamrazor.deepaether.DeepAetherMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
public class DeepAetherModPlacedFeatures {
        public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
                DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, DeepAetherMod.MODID);

        public static final RegistryObject<PlacedFeature> ROSEWOOD_PLACED = PLACED_FEATURES.register("rosewood_placed",
                () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.ROSEWOOD_SPAWN, VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(3, 0.1f, 2))));
        public static final RegistryObject<PlacedFeature> ASETERITE_PLACED = PLACED_FEATURES.register("aseterite_placed",  () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                DeepAetherModConfiguredFeatures.ASETERITE, DeepAetherModOrePlacement.commonOrePlacement(80, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
        public static void register(IEventBus eventBus) {
                PLACED_FEATURES.register(eventBus);

        }
}
