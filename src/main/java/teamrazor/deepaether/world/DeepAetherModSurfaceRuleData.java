package teamrazor.deepaether.world;
/*

import com.gildedgames.aether.block.AetherBlocks;
import com.gildedgames.aether.data.resources.AetherBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import teamrazor.deepaether.init.DeepAetherModBiomes;

public class DeepAetherModSurfaceRuleData {

private static final SurfaceRules.RuleSource AETHER_DIRT = makeStateRule(AetherBlocks.AETHER_DIRT.get());
    private static final SurfaceRules.RuleSource AETHER_GRASS_BLOCK = makeStateRule(AetherBlocks.AETHER_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource AETHER_GRASS = makeStateRule(AetherBlocks.AETHER_GRASS_BLOCK.get());

    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, AETHER_GRASS_BLOCK), AETHER_DIRT);

        return SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.isBiome(DeepAetherModBiomes.AERGLOW_GROVE), AETHER_GRASS),
            SurfaceRules.ifTrue(SurfaceRules.isBiome(DeepAetherModBiomes.VIRULENT_FOREST), AETHER_GRASS),

            // Default to a grass and dirt surface
            SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}*/