package teamrazor.deepaether.world.features.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import teamrazor.deepaether.world.features.tree.foliage.DAFoliagePlacers;

//This code was written by the Aether Team and not by Team Razor
public class SunrootFoliagePlacer extends FoliagePlacer {
    public static final Codec<SunrootFoliagePlacer> CODEC = RecordCodecBuilder.create((codec) -> foliagePlacerParts(codec)
        .and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((placer) -> placer.trunkHeight))
        .apply(codec, SunrootFoliagePlacer::new));
private final IntProvider trunkHeight;

public SunrootFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.trunkHeight = height;
        }

@Override
protected FoliagePlacerType<?> type() {
        return DAFoliagePlacers.SUNROOT_FOLIAGE_PLACER.get();
        }

@Override
protected void createFoliage(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration configuration, int maxTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockpos = attachment.pos();
        this.placeLeavesRow(level, setter, random, configuration, blockpos, attachment.radiusOffset(), 0, attachment.doubleTrunk());
        }

@Override
public int foliageHeight(RandomSource random, int height, TreeConfiguration configuration) {
        int value = height - this.trunkHeight.sample(random);
        return Math.max(3, value);
        }

@Override
protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return localX + localX + localZ + localZ > range + 2;
        }
}