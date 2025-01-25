package io.github.razordevs.deep_aether.world.biomes;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.data.resources.AetherFeatureStates;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.zepalesque.unity.block.UnityBlocks;

public class DASurfaceData {

    public static SurfaceRules.RuleSource makeRules()
    {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.GOLDEN_HEIGHTS),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.2D),
                                        SurfaceRules.state(AetherFeatureStates.QUICKSOIL)))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.GOLDEN_HEIGHTS), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.state(DABlocks.GOLDEN_GRASS_BLOCK.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.GOLDEN_GROVE), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.state(DABlocks.GOLDEN_GRASS_BLOCK.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.YAGROOT_SWAMP),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.7D),
                                        SurfaceRules.state(DABlocks.VIRULENT_QUICKSAND.get().defaultBlockState())))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.YAGROOT_SWAMP),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(UnityBlocks.AETHER_MUD.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.YAGROOT_SWAMP),
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(UnityBlocks.AETHER_MUD.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true))))
                );
    }
}
