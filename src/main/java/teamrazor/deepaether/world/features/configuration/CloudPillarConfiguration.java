package teamrazor.deepaether.world.features.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CloudPillarConfiguration(BlockStateProvider blockstate) implements FeatureConfiguration {
    public static final Codec<CloudPillarConfiguration> CODEC = RecordCodecBuilder.create((p_190962_) -> p_190962_.group(BlockStateProvider.CODEC.fieldOf("blockstate").forGetter(CloudPillarConfiguration::blockstate)).apply(p_190962_, CloudPillarConfiguration::new));

}
