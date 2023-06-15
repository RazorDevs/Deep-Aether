package teamrazor.deepaether.world.biomes;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.data.resources.AetherFeatureStates;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import teamrazor.deepaether.init.DABlocks;

public class DASurfaceData {

    public static SurfaceRules.RuleSource makeRules()
    {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.GOLDEN_HEIGHTS), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.state(DABlocks.GOLDEN_GRASS_BLOCK.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.GOLDEN_HEIGHTS),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.2D),
                                        SurfaceRules.state(AetherFeatureStates.QUICKSOIL)))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.YAGROOT_SWAMP),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(DABlocks.AETHER_MUD.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.YAGROOT_SWAMP),
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(DABlocks.AETHER_MUD.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true))))
                );
    }


    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
