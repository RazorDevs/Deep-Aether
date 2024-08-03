package teamrazor.deepaether.world.feature.features.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class DAHugeMushroomFeatureConfiguration implements FeatureConfiguration {
    public static final Codec<DAHugeMushroomFeatureConfiguration> CODEC = RecordCodecBuilder.create((configuration)
            -> configuration.group(
                    BlockStateProvider.CODEC.fieldOf("cap_provider").forGetter((featureConfiguration) -> featureConfiguration.capProvider),
                    BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter((featureConfiguration) -> featureConfiguration.stemProvider),
                    BlockStateProvider.CODEC.fieldOf("roots_provider").forGetter((featureConfiguration) -> featureConfiguration.rootsProvider),
                    Codec.INT.fieldOf("foliage_radius").orElse(2).forGetter((featureConfiguration) -> featureConfiguration.foliageRadius),
                    Codec.INT.fieldOf("base_height").orElse(4).forGetter((featureConfiguration) -> featureConfiguration.baseHeight),
                    Codec.INT.fieldOf("random_height").orElse(7).forGetter((featureConfiguration) -> featureConfiguration.randomHeight),
                    Codec.INT.fieldOf("trunk_radius").orElse(1).forGetter((featureConfiguration) -> featureConfiguration.trunkRadius))

            .apply(configuration, DAHugeMushroomFeatureConfiguration::new));
    public final BlockStateProvider capProvider;
    public final BlockStateProvider stemProvider;
    public final BlockStateProvider rootsProvider;
    public final int foliageRadius;
    public final int baseHeight;
    public final int randomHeight;
    public final int trunkRadius;

    public DAHugeMushroomFeatureConfiguration(BlockStateProvider cap, BlockStateProvider stem, BlockStateProvider rootsProvider, int radius, int baseHeight, int randomHeight, int trunkRadius) {
        this.capProvider = cap;
        this.stemProvider = stem;
        this.rootsProvider = rootsProvider;
        this.foliageRadius = radius;
        this.baseHeight = baseHeight;
        this.randomHeight = randomHeight;
        this.trunkRadius = trunkRadius;
    }
}