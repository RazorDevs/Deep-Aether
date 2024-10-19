package io.github.razordevs.deepaether.deepaether.world.feature.features.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record FallenTreeConfiguration(int min, int max, BlockStateProvider block, BlockStateProvider decorators) implements FeatureConfiguration {
    public static final Codec<FallenTreeConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                    Codec.INT.fieldOf("min").forGetter(FallenTreeConfiguration::min),
                    Codec.INT.fieldOf("max").forGetter(FallenTreeConfiguration::max),

                    BlockStateProvider.CODEC.fieldOf("block").forGetter(FallenTreeConfiguration::block),
                    BlockStateProvider.CODEC.fieldOf("decorators").forGetter(FallenTreeConfiguration::decorators))
            .apply(instance, FallenTreeConfiguration::new));

    public FallenTreeConfiguration(int min, int max, BlockStateProvider block, BlockStateProvider decorators) {
        this.min = min;
        this.max = max;
        this.block = block;
        this.decorators = decorators;
    }

    public int min() {
        return this.min;
    }

    public int max() {
        return this.max;
    }

    public BlockStateProvider decorators() {
        return this.decorators;
    }

    public BlockStateProvider block() {
        return this.block;
    }
}

