package io.github.razordevs.deep_aether.world.feature.tree.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class SunrootTunkPlacer extends TrunkPlacer {
    public static final MapCodec<SunrootTunkPlacer> CODEC = RecordCodecBuilder.mapCodec((p_70261_) ->
            trunkPlacerParts(p_70261_).apply(p_70261_, SunrootTunkPlacer::new));

    public SunrootTunkPlacer(int p_161770_, int p_161771_, int p_161772_) {
        super(p_161770_, p_161771_, p_161772_);
    }

    protected TrunkPlacerType<?> type() {
        return DaTrunkPlacerTypes.SUNROOT_TRUNK_PLACER.get();
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> posState, RandomSource random, int int1, BlockPos pos, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        //PLaces Main Trunk
        setDirtAt(level, posState, random, pos.below(), config);
        int i;
        for(i = 0; i < int1; ++i) {
            this.placeLog(level, posState, random, pos.above(i), config);
        }
        list.add(new FoliagePlacer.FoliageAttachment(pos.above(i), 0, false));

        //Places extra foliage attachments
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos pos2;

        pos2 = pos.relative(direction, random.nextInt(2,4)).above(i+random.nextInt(0,3));
        this.placeLog(level, posState, random, pos2, config, (log) -> log.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis()));
        list.add(new FoliagePlacer.FoliageAttachment(pos2, 0, false));

        if(random.nextBoolean()) {
            pos2 = pos.relative(direction.getClockWise(), random.nextInt(2,4)).above(i+random.nextInt(0,3));
            this.placeLog(level, posState, random, pos2, config, (log) -> log.trySetValue(RotatedPillarBlock.AXIS, direction.getClockWise().getAxis()));
            list.add(new FoliagePlacer.FoliageAttachment(pos2, 0, false));
        }

        //Adds the bottom of the trunk
        if(this.isFree(level, pos.below()))
            this.placeLog(level,posState,random,pos.below(),config);
        if(this.isFree(level, pos.below().south()))
            this.placeLog(level,posState,random,pos.below().south(),config);
        if(this.isFree(level, pos.below().north()))
            this.placeLog(level,posState,random,pos.below().north(),config);
        if(this.isFree(level, pos.below().west()))
            this.placeLog(level,posState,random,pos.below().west(),config);
        if(this.isFree(level, pos.below().east()))
            this.placeLog(level,posState,random,pos.below().east(),config);

        if(this.isFree(level, pos.south()))
            this.placeLog(level,posState,random,pos.south(),config);
        if(this.isFree(level, pos.north()))
            this.placeLog(level,posState,random,pos.north(),config);
        if(this.isFree(level, pos.west()))
            this.placeLog(level,posState,random,pos.west(),config);
        if(this.isFree(level, pos.east()))
            this.placeLog(level,posState,random,pos.east(),config);

        return list;
    }
}