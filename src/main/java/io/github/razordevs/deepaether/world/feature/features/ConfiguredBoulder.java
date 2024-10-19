package io.github.razordevs.deepaether.deepaether.world.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ConfiguredBoulder extends Feature<ConfiguredBoulder.Config> {
    public ConfiguredBoulder(Codec<Config> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<Config> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();

        Config config;
        for(config = context.config(); origin.getY() > level.getMinBuildHeight() + 3; origin = origin.below()) {
            if (!level.isEmptyBlock(origin.below())) {
                BlockState blockstate = level.getBlockState(origin.below());
                if (isDirt(blockstate) || isStone(blockstate)) {
                    break;
                }
            }
        }

        if (origin.getY() <= level.getMinBuildHeight() + 3) {
            return false;
        } else {
            for(int l = 0; l < 3; ++l) {
                int i = rand.nextInt(2);
                int j = rand.nextInt(2);
                int k = rand.nextInt(2);
                float f = (float)(i + j + k) * 0.333F + 0.5F;

                for (BlockPos pos : BlockPos.betweenClosed(origin.offset(-i, -j, -k), origin.offset(i, j, k))) {
                    if (pos.distSqr(origin) <= (double) (f * f)) {
                        level.setBlock(pos, config.state.getState(rand, pos), 3);
                    }
                }

                origin = origin.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
            }

            return true;
        }
    }


    public record Config(BlockStateProvider state) implements FeatureConfiguration {
        public static final Codec<Config> CODEC = BlockStateProvider.CODEC.fieldOf("state").xmap(Config::new, (config) -> config.state).codec();
    }
}
