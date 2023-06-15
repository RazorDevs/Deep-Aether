package teamrazor.deepaether.world.biomes;

import atumblender.api.Region;
import atumblender.api.RegionType;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;


public class DARegion extends Region {

    public DARegion(ResourceLocation name, int weight)
    {
        super(name, RegionType.AETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        Climate.Parameter fullRange = Climate.Parameter.span(-1.0F, 1.0F);
        Climate.Parameter temps1 = Climate.Parameter.span(-1.0F, -0.8F);

        //GOLDEN HEIGHTS AND YAGROOT SWAMP
        Climate.Parameter temps2 = Climate.Parameter.span(-0.8F, 0.0F);

        //AERGLOW FOREST AND SUB-VARIANTS
        Climate.Parameter temps3 = Climate.Parameter.span(0.0F, 0.4F);

        //AERLAVENDER FIELDS
        Climate.Parameter temps4 = Climate.Parameter.span(0.4F, 0.93F);
        Climate.Parameter temps5 = Climate.Parameter.span(0.93F, 0.94F);
        Climate.Parameter temps6 = Climate.Parameter.span(0.94F, 1.0F);


        this.addBiome(mapper, new Climate.ParameterPoint(temps2, Climate.Parameter.span(0.0F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.GOLDEN_HEIGHTS);

        this.addBiome(mapper, new Climate.ParameterPoint(temps2, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.YAGROOT_SWAMP);

        this.addBiome(mapper, new Climate.ParameterPoint(temps3, Climate.Parameter.span(-1.0F, 0.3F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERGLOW_FOREST);

        this.addBiome(mapper, new Climate.ParameterPoint(temps3, Climate.Parameter.span(0.3F, 0.8F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.MYSTIC_AERGLOW_FOREST);

        this.addBiome(mapper, new Climate.ParameterPoint(temps3, Climate.Parameter.span(0.8F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.BLUE_AERGLOW_FOREST);

        this.addBiome(mapper, new Climate.ParameterPoint(temps4, fullRange, fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERLAVENDER_FIELDS);

        /*VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        // Overlap Vanilla's parameters with our own for our COLD_BLUE biome.
        // The parameters for this biome are chosen arbitrarily.
        new ParameterPointListBuilder()
                .temperature(Temperature.span(Temperature.COOL, Temperature.FROZEN))
                .humidity(Humidity.span(Humidity.ARID, Humidity.DRY))
                .continentalness(Continentalness.INLAND)
                .erosion(Erosion.EROSION_0, Erosion.EROSION_1)
                .depth(Depth.SURFACE, Depth.FLOOR)
                .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING)
                .build().forEach(point -> builder.add(point, TestBiomes.COLD_BLUE));

        // Add our points to the mapper
        builder.build().forEach(mapper::accept);*/
    }
}
