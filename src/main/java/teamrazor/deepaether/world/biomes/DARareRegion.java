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

        Climate.Parameter tempWoodland = Climate.Parameter.span(-1.5F, -0.8F);
        Climate.Parameter tempYagroot = Climate.Parameter.span(-0.8F, -0.4F);
        Climate.Parameter tempAerglow = Climate.Parameter.span(-0.4F, 0.0F);
        Climate.Parameter tempDefault3 = Climate.Parameter.span(0.0F, 0.4F);
        Climate.Parameter tempDefault4 = Climate.Parameter.span(0.4F, 0.7F);
        Climate.Parameter tempDefault5 = Climate.Parameter.span(0.7F, 1.5F);

        addBiome(mapper, new Climate.ParameterPoint(tempDefault5, Climate.Parameter.span(0.0F, 1.0F), fullRange, fullRange, Climate.Parameter.span(0.0F, 1.5F), fullRange, 0),
                DABiomes.SKYROOT_RAINFOREST);
        addBiome(mapper, new Climate.ParameterPoint(tempDefault5, Climate.Parameter.span(0.0F, 1.0F), fullRange, fullRange, Climate.Parameter.span(-1.5F, 0.0F), fullRange, 0),
                DABiomes.STORM_CLOUD);
    }
}
