package teamrazor.deepaether.world.biomes;

import com.aetherteam.aether.data.resources.registries.AetherBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import teamrazor.aeroblender.aether.AetherRegionType;
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

        Climate.Parameter tempDefault1 = Climate.Parameter.span(-1.0F, -0.8F);
        Climate.Parameter tempGold = Climate.Parameter.span(-0.8F, -0.4F);
        Climate.Parameter tempDefault2 = Climate.Parameter.span(-0.4F, 0.0F);
        Climate.Parameter tempDefault3 = Climate.Parameter.span(0.0F, 0.4F);
        Climate.Parameter tempDefault4 = Climate.Parameter.span(0.4F, 0.8F);
        Climate.Parameter tempDefault5 = Climate.Parameter.span(0.8F, 1.0F);


        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault1, fullRange, fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.YAGROOT_SWAMP);
        // Row 2
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault2, Climate.Parameter.span(-1.0F, -0.6F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.BLUE_AERGLOW_FOREST);
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault2, Climate.Parameter.span(-0.6F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERGLOW_FOREST);
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault2, Climate.Parameter.span(0.0F, 0.7F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_FOREST);
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault2, Climate.Parameter.span(0.7F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERLAVENDER_FIELDS);
        // Row 3
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault3, Climate.Parameter.span(-1.0F, -0.3F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.GOLDEN_HEIGHTS);
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault3, Climate.Parameter.span(-0.3F, 0.5F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERLAVENDER_FIELDS);
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault3, Climate.Parameter.span(0.5F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_GROVE);

        // Row 4
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault4, Climate.Parameter.span(-1.0F, -0.3F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERGLOW_FOREST);
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault4, Climate.Parameter.span(-0.3F, 0.1F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.MYSTIC_AERGLOW_FOREST);
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault4, Climate.Parameter.span(0.1F, 0.4F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.BLUE_AERGLOW_FOREST);
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault4, Climate.Parameter.span(0.1F, 0.4F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_WOODLAND);
        // Row 5
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault5, Climate.Parameter.span(-1.0F, 0.5F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.YAGROOT_SWAMP);
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault5, Climate.Parameter.span(0.5F, 0.7F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.AERGLOW_FOREST);
        this.addBiome(mapper, new Climate.ParameterPoint(tempDefault5, Climate.Parameter.span(0.7F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.BLUE_AERGLOW_FOREST);

        this.addBiome(mapper, new Climate.ParameterPoint(tempGold, Climate.Parameter.span(-1.0F, -0.6F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.GOLDEN_HEIGHTS);
        this.addBiome(mapper, new Climate.ParameterPoint(tempGold, Climate.Parameter.span(-0.6F, 0.2F), fullRange, fullRange, fullRange, fullRange, 0),
                DABiomes.MYSTIC_AERGLOW_FOREST);
        this.addBiome(mapper, new Climate.ParameterPoint(tempGold, Climate.Parameter.span(0.2F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_MEADOW);
    }
}
