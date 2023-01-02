package teamrazor.deepaether.world.feature.tree.foliage;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DeepAetherModFoliagePlacers {
        public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, DeepAetherMod.MODID);

        public static final RegistryObject<FoliagePlacerType<RoserootFoliagePlacer>> ROSEROOT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("roseroot_foliage_placer", () -> new FoliagePlacerType<>(RoserootFoliagePlacer.CODEC));
    }