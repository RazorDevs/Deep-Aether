package teamrazor.deepaether.world.Gen;


import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import teamrazor.deepaether.world.feature.DeepAetherModPlacedFeatures;

import java.util.List;
import java.util.Set;

    public class ModTreeGeneration {
        public static void generateTrees(final BiomeLoadingEvent event) {
            ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

            //tree temporarily generate in plains
            if(types.contains(Biome.BiomeCategory.NETHER)) {
                List<Holder<PlacedFeature>> base =
                        event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

                base.add(DeepAetherModPlacedFeatures.ROSEWOOD_PLACED);
            }
        }
}
