package teamrazor.deepaether.world.Gen;

import com.gildedgames.aether.data.resources.AetherBiomes;
import com.gildedgames.aether.data.resources.AetherDimensions;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import teamrazor.deepaether.init.DeepAetherModBiomes;
import terrablender.api.Region;
import terrablender.api.RegionType;
import static terrablender.api.ParameterUtils.*;

import java.util.List;
import java.util.function.Consumer;
/*

public class DeepAetherModRegion extends Region {
    public DeepAetherModRegion(ResourceLocation name, int weight)
    {
        super(name, RegionType.valueOf("the_aether"), weight);
    }


    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            // Simple example:
            // Replace the Vanilla desert with our hot_red biome
            builder.replaceBiome(AetherBiomes.SKYROOT_FOREST, DeepAetherModBiomes.AERGLOW_GROVE);

            // More complex example:
            // Replace specific parameter points for the frozen peaks with our cold_blue biome
            List<Climate.ParameterPoint> frozenPeaksPoints = new ParameterPointListBuilder()
                    .temperature(Temperature.ICY, Temperature.COOL, Temperature.NEUTRAL)
                    .humidity(Humidity.ARID, Humidity.DRY, Humidity.NEUTRAL, Humidity.WET, Humidity.HUMID)
                    .continentalness(Continentalness.span(Continentalness.COAST, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_0, Erosion.EROSION_1)
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.PEAK_VARIANT, Weirdness.HIGH_SLICE_VARIANT_DESCENDING)
                    .build();

            builder.replaceBiome(AetherBiomes.SKYROOT_FOREST, DeepAetherModBiomes.VIRULENT_FOREST);
        });
    }
}
*/