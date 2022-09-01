/*package teamrazor.deepaether.world;


import com.gildedgames.aether.block.AetherBlocks;
import com.gildedgames.aether.data.resources.builders.AetherBiomeBuilders;
import com.gildedgames.aether.data.resources.builders.AetherFeatureBuilders;
import com.gildedgames.aether.world.feature.AetherFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class DeepAetherModBiomeBuilders extends AetherBiomeBuilders {



    public static Biome aerglowGroveBiome() {
        return makeDefaultBiome(new BiomeGenerationSettings.Builder()
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(AetherFeatureBuilders.treeBlendDensity(2)))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(new PlacedFeature(Holder.hackyErase(AetherFeatures.ConfiguredFeatures.SKYROOT_TREE_CONFIGURED_FEATURE), List.of(
                        CountOnEveryLayerPlacement.of(1),
                        AetherFeatureBuilders.copyBlockSurvivability(AetherBlocks.SKYROOT_SAPLING.get())))))
        );
    }

    public static Biome virulentForestBiome() {
        return makeDefaultBiome(new BiomeGenerationSettings.Builder()
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(new PlacedFeature(Holder.hackyErase(AetherFeatures.ConfiguredFeatures.SKYROOT_TREE_CONFIGURED_FEATURE), List.of(
                        CountOnEveryLayerPlacement.of(1),
                        AetherFeatureBuilders.copyBlockSurvivability(AetherBlocks.SKYROOT_SAPLING.get())))))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(AetherFeatureBuilders.treeBlendDensity(3)))
        );
    }

}
*/