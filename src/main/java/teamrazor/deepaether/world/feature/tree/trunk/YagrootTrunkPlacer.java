package teamrazor.deepaether.world.feature.tree.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class YagrootTrunkPlacer extends TrunkPlacer {
    public static final Codec<YagrootTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70261_) -> {
        return trunkPlacerParts(p_70261_).apply(p_70261_, YagrootTrunkPlacer::new);
    });


    public YagrootTrunkPlacer(int p_161770_, int p_161771_, int p_161772_) {
        super(p_161770_, p_161771_, p_161772_);
    }

    protected TrunkPlacerType<?> type() {
        return DaTrunkPlacerTypes.YAGROOT_TRUNK_PLACER.get();
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> posState, RandomSource random, int int1, BlockPos pos, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        int i;

        //Places Main Trunk And Side Trunk
        for (i = 0; i < int1; i++) {
            this.placeLog(level, posState, random, pos.above(i), config);
            if (i < int1 / 4 || i > (int1 / 4)*3) {
                this.placeLog(level, posState, random, pos.above(i).east(), config);
                this.placeLog(level, posState, random, pos.above(i).west(), config);
                this.placeLog(level, posState, random, pos.above(i).north(), config);
                this.placeLog(level, posState, random, pos.above(i).south(), config);
            }
        }



        //places Top of The Trunk
        Direction direction;
        for(int z = 0; z < 4; z++) {
            if(z == 0) direction = Direction.NORTH;
            else if(z == 1) direction = Direction.SOUTH;
            else if(z == 2) direction = Direction.EAST;
            else direction = Direction.WEST;
            int y = 0;
            int a = random.nextInt(3, 5);
            for (int x = 0; (x/2)+1 <= a; x++) {
                y = Math.round(Math.round(Math.sqrt(Math.pow(a,2)-Math.pow(x-a,2))));
                System.out.println(y);
                if(z == 0 || z == 1)
                    this.placeLog(level, posState, random, pos.relative(direction, x).above(i+y-3), config, (state) -> state.trySetValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
                if(z == 2 || z == 3)
                    this.placeLog(level, posState, random, pos.relative(direction, x).above(i+y-3), config, (p_161826_) -> p_161826_.trySetValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
                list.add(new FoliagePlacer.FoliageAttachment(pos.relative(direction, x).above(i+y-3), 0, false));
            }
        }

        //Makes the tree not float in the air
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

        return list;

    }
}