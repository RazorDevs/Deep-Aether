package teamrazor.deepaether.world.features.tree.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.BiConsumer;

public class YagrootRootPlacer extends RootPlacer {
    public static final Codec<YagrootRootPlacer> CODEC = RecordCodecBuilder.create((p_225856_) -> {
        return rootPlacerParts(p_225856_).and(MangroveRootPlacement.CODEC.fieldOf("yagroot_root_placer").forGetter((p_225849_) -> {
            return p_225849_.yagrootRootPlacement;
        })).apply(p_225856_, YagrootRootPlacer::new);
    });
    private final MangroveRootPlacement yagrootRootPlacement;

    public YagrootRootPlacer(IntProvider p_225817_, BlockStateProvider p_225818_, Optional<AboveRootPlacement> p_225819_, MangroveRootPlacement p_225820_) {
        super(p_225817_, p_225818_, p_225819_);
        this.yagrootRootPlacement = p_225820_;

    }


    protected @NotNull RootPlacerType<?> type() {
        return DARootPlacers.YAGROOT_ROOT_PLACER.get();
    }

    @Override
    public boolean placeRoots(@NotNull LevelSimulatedReader level, @NotNull BiConsumer<BlockPos, BlockState> blockstate, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockPos pos1, @NotNull TreeConfiguration treeConfiguration) {
        int intA;
        int intB;
        int intC;
        int maxRootLength;

        //Max length of roots without moving down
        int maxFlatRootLength;
        Direction direction;

        for (int i = 1; i <= 4; i++) {

            intA = 0;
            intB = 0;
            intC = 0;
            maxRootLength = random.nextInt(8,15);
            maxFlatRootLength = random.nextInt(4,7);

            if(i==1) direction = Direction.NORTH;
            else if (i==2) direction = Direction.SOUTH;
            else if(i==3) direction = Direction.EAST;
            else direction = Direction.WEST;



            for (int j = 0; j < maxRootLength && intC < maxFlatRootLength; j++) {
                if (this.canPlaceRoot(level, pos.below(intB + 1).relative(direction,intA+1))) {
                    intB++;
                    intC = 0;
                }
                else if (this.canPlaceRoot(level, pos.below(intB).relative(direction,intA+2))) {
                    intA++;
                    intC++;
                }
                this.placeRoot(level, blockstate, random, pos.below(intB).relative(direction, intA+1), treeConfiguration);
            }
        }
        return true;
    }



    private boolean isEmpty(LevelSimulatedReader level, BlockPos pos) {
        return level.isStateAtPosition(pos, state -> state.getBlock() == Blocks.AIR || state.getBlock() == Blocks.CAVE_AIR);
    }
}