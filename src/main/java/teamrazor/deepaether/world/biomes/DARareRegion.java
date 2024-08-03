package teamrazor.deepaether.world.biomes;

import com.aetherteam.aether.data.resources.registries.AetherBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import teamrazor.aeroblender.aether.AetherRegionType;
import teamrazor.deepaether.DeepAetherConfig;
import terrablender.api.Region;

import java.util.function.Consumer;


public class DARareRegion extends Region {

    public DARareRegion(ResourceLocation name, int weight)
    {
        super(name, AetherRegionType.THE_AETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        Climate.Parameter fullRange = Climate.Parameter.span(-1.5F, 1.5F);

        Climate.Parameter tempDefault4 = Climate.Parameter.span(0.5F, 0.7F);
        Climate.Parameter tempDefault5 = Climate.Parameter.span(0.7F, 1.5F);

        Climate.Parameter tempDefault45 = Climate.Parameter.span(0.5F, 1.5F);

        addBiome(mapper, new Climate.ParameterPoint(tempDefault45, Climate.Parameter.span(-1.0F, 1.0F), fullRange, fullRange, Climate.Parameter.span(0.0F, 1.5F), fullRange, 0),
                DABiomes.SKYROOT_RAINFOREST);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault5, Climate.Parameter.span(-0.3F, 1.0F), fullRange, fullRange, Climate.Parameter.span(-1.5F, 0.0F), fullRange, 0),
                DABiomes.CLOUD);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault4, fullRange, fullRange, fullRange, Climate.Parameter.span(-1.5F, 0.0F), fullRange, 0),
                DABiomes.STORM_CLOUD);
    }
}
