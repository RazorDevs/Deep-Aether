package io.github.razordevs.deep_aether.world.biomes;

import com.aetherteam.aether.data.resources.registries.AetherBiomes;
import com.mojang.datafixers.util.Pair;
import io.github.razordevs.aeroblender.aether.AetherRegionType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
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

        Climate.Parameter tempMushroomCloud = Climate.Parameter.span(0.7F, 1.0F);
        Climate.Parameter tempSkyroot = Climate.Parameter.span(0.0F, 0.7F);
        //Climate.Parameter tempStorm = Climate.Parameter.span(-0.5F, 0.7F);
        Climate.Parameter tempCloud = Climate.Parameter.span(-0.8F, 0.0F);
        Climate.Parameter tempSkyroot2 = Climate.Parameter.span(-1.0F, -0.8F);

        //Mushroom + Normal cloud
        addBiome(mapper, new Climate.ParameterPoint(tempMushroomCloud, fullRange, fullRange, fullRange, Climate.Parameter.span(0.0F, 1.5F), fullRange, 0),
                DABiomes.SKYROOT_RAINFOREST);
        addBiome(mapper, new Climate.ParameterPoint(tempMushroomCloud, Climate.Parameter.span(0.2F, 1.0F), fullRange, fullRange, Climate.Parameter.span(-1.5F, 0.0F), fullRange, 0),
                DABiomes.CLOUD);
        addBiome(mapper, new Climate.ParameterPoint(tempMushroomCloud, Climate.Parameter.span(-1.0F, 0.2F), fullRange, fullRange, Climate.Parameter.span(-1.5F, 0.0F), fullRange, 0),
                DABiomes.CLOUD); //Overgrown cloud goes here

        //Skyroot
        this.addBiome(mapper, new Climate.ParameterPoint(tempSkyroot, Climate.Parameter.span(-1.0F, 0.0F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_MEADOW);
        this.addBiome(mapper, new Climate.ParameterPoint(tempSkyroot, Climate.Parameter.span(0.0F, 1.0F), fullRange, fullRange, fullRange, fullRange, 0),
                AetherBiomes.SKYROOT_FOREST);

        //Normal cloud
        addBiome(mapper, new Climate.ParameterPoint(tempCloud, fullRange, fullRange, fullRange, Climate.Parameter.span(0.0F, 1.5F), fullRange, 0),
                DABiomes.SKYROOT_RAINFOREST);
        addBiome(mapper, new Climate.ParameterPoint(tempCloud, fullRange, fullRange, fullRange, Climate.Parameter.span(-1.5F, 0.0F), fullRange, 0),
                DABiomes.CLOUD);

        //Skyroot
        addBiome(mapper, new Climate.ParameterPoint(tempSkyroot2, fullRange, fullRange, fullRange, Climate.Parameter.span(0.0F, 1.5F), fullRange, 0),
                AetherBiomes.SKYROOT_MEADOW);
    }
}
