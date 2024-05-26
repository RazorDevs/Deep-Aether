package teamrazor.deepaether.world.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.QuartPos;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import teamrazor.deepaether.world.biomes.DABiomes;
import teamrazor.deepaether.world.feature.features.configuration.AercloudCloudConfiguration;
import teamrazor.deepaether.world.feature.features.configuration.FallenTreeConfiguration;
import teamrazor.deepaether.world.feature.tree.trunk.TrunkUtils;

import javax.annotation.Nullable;
import java.util.List;

public class AercloudCloudFeature extends Feature<AercloudCloudConfiguration> {

    public static final PerlinSimplexNoise bottom_noise = new PerlinSimplexNoise(new XoroshiroRandomSource(42), List.of(0,1, 1));
    //public static final PerlinSimplexNoise top_noise = new PerlinSimplexNoise(new XoroshiroRandomSource(12), List.of(1,1,1, 2, 3, 4, 5,5, 5,7,8));
    //public static final PerlinSimplexNoise top_noise = new PerlinSimplexNoise(new XoroshiroRandomSource(12), List.of(1,3,3,7));

    public static final int lowestY  = 175;
    public AercloudCloudFeature(Codec<AercloudCloudConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<AercloudCloudConfiguration> context) {
        WorldGenLevel reader = context.level();
        BlockPos pos = context.origin();
        AercloudCloudConfiguration config = context.config();
        BlockState block = config.block().getState(context.random(), pos);
        place(reader, pos, block);

        return true;
    }

    public void place(WorldGenLevel reader, BlockPos pos, BlockState block) {
        boolean goAgainstX = !reader.getBiome(pos.relative(Direction.Axis.X, 16)).is(DABiomes.STORM_CLOUD);
        boolean goAgainstNegativeX = !reader.getBiome(pos.relative(Direction.Axis.X, -16)).is(DABiomes.STORM_CLOUD);
        boolean goAgainstZ = !reader.getBiome(pos.relative(Direction.Axis.Z, 16)).is(DABiomes.STORM_CLOUD);
        boolean goAgainstNegativeZ = !reader.getBiome(pos.relative(Direction.Axis.Z, -16)).is(DABiomes.STORM_CLOUD);

        boolean goAgainstXAndZ = ((!reader.getBiome(pos.relative(Direction.Axis.X, 16).relative(Direction.Axis.Z, 16)).is(DABiomes.STORM_CLOUD)) && !goAgainstX && !goAgainstZ);
        boolean goAgainstXAndNegativeZ = (!reader.getBiome(pos.relative(Direction.Axis.X, 16).relative(Direction.Axis.Z, -16)).is(DABiomes.STORM_CLOUD) && !goAgainstX && !goAgainstNegativeZ);
        boolean goAgainstNegativeXAndZ = (!reader.getBiome(pos.relative(Direction.Axis.X, -16).relative(Direction.Axis.Z, 16)).is(DABiomes.STORM_CLOUD) && !goAgainstNegativeX && !goAgainstZ);
        boolean goAgainstNegativeXAndNegativeZ = (!reader.getBiome(pos.relative(Direction.Axis.X, -16).relative(Direction.Axis.Z, -16)).is(DABiomes.STORM_CLOUD) && !goAgainstNegativeX && !goAgainstNegativeZ);

        int chunkX = pos.getX() - (pos.getX() % 16);
        int chunkZ = pos.getZ() - (pos.getZ() % 16);

        //Fills a chunk with blocks with a noise-based terrain
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int xCoord = chunkX + x;
                int zCoord = chunkZ + z;

                double bottomNoiseValue = bottom_noise.getValue(xCoord * 0.02D, zCoord * 0.02D, false);
                double bottom = Math.abs(Mth.lerp(bottomNoiseValue, 5, 2));
                double originalBottom = bottom;

                double topNoiseValue = bottom_noise.getValue(xCoord * 0.005D, zCoord * 0.005D, false);
                //double top = Mth.lerp(topNoiseValue*edgeMultiplier,12,25) + 5*edgeMultiplier;
                double top = Mth.lerp(topNoiseValue, 2, 12) + 3;

                final double decreaseMultiplier = 1.5D;
                final double decreaseMultiplierCorner = 4D;

                //SOUP
                if (goAgainstX) {
                    if (x > 8) {
                        bottom += (x - 8D) / decreaseMultiplier;
                        top -= (x - 8D) / decreaseMultiplier;
                    }
                }
                if (goAgainstNegativeX) {
                    if (x <= 8) {
                        bottom += (8D - x) / decreaseMultiplier;
                        top -= (8D - x) / decreaseMultiplier;
                    }
                }
                if (goAgainstZ) {
                    if (z > 8) {
                        bottom += (z - 8D) / decreaseMultiplier;
                        top -= (z - 8D) / decreaseMultiplier;
                    }
                }
                if (goAgainstNegativeZ) {
                    if (z < 8) {
                        bottom += (8D - z) / decreaseMultiplier;
                        top -= (8D - z) / decreaseMultiplier;
                    }
                } else if (goAgainstXAndZ) {
                    if (x > 12) {
                        bottom += (x - 12D) / decreaseMultiplierCorner;
                        top -= (x - 12D) / decreaseMultiplierCorner;
                    }
                    if (z > 12) {
                        bottom += (z - 12D) / decreaseMultiplierCorner;
                        top -= (z - 12D) / decreaseMultiplierCorner;
                    }
                } else if (goAgainstXAndNegativeZ) {
                    if (x > 12) {
                        bottom += (x - 12D) / decreaseMultiplierCorner;
                        top -= (x - 12D) / decreaseMultiplierCorner;
                    }
                    if (z < 4) {
                        bottom += (4D - z) / decreaseMultiplierCorner;
                        top -= (4D - z) / decreaseMultiplierCorner;
                    }
                } else if (goAgainstNegativeXAndZ) {
                    if (x < 4) {
                        bottom += (4D - x) / decreaseMultiplierCorner;
                        top -= (4D - x) / decreaseMultiplierCorner;
                    }
                    if (z > 12) {
                        bottom += (z - 12D) / decreaseMultiplierCorner;
                        top -= (z - 12D) / decreaseMultiplierCorner;
                    }
                } else if (goAgainstNegativeXAndNegativeZ) {
                    if (x < 4) {
                        bottom += (4D - x) / decreaseMultiplierCorner;
                        top -= (4D - x) / decreaseMultiplierCorner;
                    }
                    if (z < 4) {
                        bottom += (4D - z) / decreaseMultiplierCorner;
                        top -= (4D - z) / decreaseMultiplierCorner;
                    }
                }

                for (int y = Math.round(Math.round(lowestY + bottom)); y < lowestY + top + originalBottom; y++) {
                    this.setBlock(reader, pos.relative(Direction.Axis.X, x).relative(Direction.Axis.Z, z).atY(y), block);
                }
            }
        }
    }
}