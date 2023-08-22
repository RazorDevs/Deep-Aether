package teamrazor.deepaether.world.biomes.regions;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import teamrazor.aeroblender.aether.AetherRegionType;
import teamrazor.deepaether.world.biomes.DABiomes;
import terrablender.api.Region;

import java.util.function.Consumer;


public class DARegion extends Region {

    public DARegion(ResourceLocation name, int weight)
    {
        super(name, AetherRegionType.THE_AETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        Climate.Parameter fullRange = Climate.Parameter.span(-1.0F, 1.0F);

        //GOLDEN HEIGHTS AND YAGROOT SWAMP
        Climate.Parameter temps1 = Climate.Parameter.span(-1.0F, 0.0F);

        //AERGLOW FOREST AND SUB-VARIANTS
        Climate.Parameter temps2 = Climate.Parameter.span(0.0F, 0.4F);

        //AERLAVENDER FIELDS
        Climate.Parameter temps3 = Climate.Parameter.span(0.4F, 1.0F);


        this.addBiome(mapper, new Climate.ParameterPoint(temps1, Climate.Parameter.span(0.0F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.GOLDEN_HEIGHTS);

        this.addBiome(mapper, new Climate.ParameterPoint(temps1, Climate.Parameter.span(-0.33F, 0.33F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.SACRED_LANDS);

        this.addBiome(mapper, new Climate.ParameterPoint(temps1, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.YAGROOT_SWAMP);

        this.addBiome(mapper, new Climate.ParameterPoint(temps2, Climate.Parameter.span(-1.0F, 0.3F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERGLOW_FOREST);

        this.addBiome(mapper, new Climate.ParameterPoint(temps2, Climate.Parameter.span(0.3F, 0.8F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.MYSTIC_AERGLOW_FOREST);

        this.addBiome(mapper, new Climate.ParameterPoint(temps2, Climate.Parameter.span(0.8F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.BLUE_AERGLOW_FOREST);

        this.addBiome(mapper, new Climate.ParameterPoint(temps3, fullRange, fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERLAVENDER_FIELDS);
    }
}
