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


public class SkyRegion extends Region {

    public SkyRegion(ResourceLocation name, int weight)
    {
        super(name, AetherRegionType.THE_AETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        Climate.Parameter fullRange = Climate.Parameter.span(-1.0F, 1.0F);

        this.addBiome(mapper, new Climate.ParameterPoint(Climate.Parameter.span(-0.9F, 0.9F), Climate.Parameter.span(-0.9F, 0.9F), Climate.Parameter.span(-0.9F, 0.9F), Climate.Parameter.span(-0.9F, 0.9F), Climate.Parameter.span(-1.0F, -0.9F), Climate.Parameter.span(-0.9F, 0.9F), 0),
                DABiomes.STORMCLOUD_SEA);

        this.addBiome(mapper, new Climate.ParameterPoint(fullRange, fullRange, fullRange, fullRange, Climate.Parameter.span(-1.0F, -0.9F), fullRange, 0),
                DABiomes.STORMCLOUD_SEA_EDGE);


    }
}
