package teamrazor.deepaether.world.Gen;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import teamrazor.deepaether.world.feature.DeepAetherModPlacedFeatures;

import java.util.List;

public class DeepAetherModOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(DeepAetherModPlacedFeatures.ASETERITE_PLACED);
    }
}
