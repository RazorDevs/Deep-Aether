package teamrazor.deepaether.world.feature.tree.decorators;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.rootplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

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


    protected RootPlacerType<?> type() {
        return DARootPlacers.YAGROOT_ROOT_PLACER.get();
    }

    @Override
    public boolean placeRoots(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockstate, RandomSource random, BlockPos pos, BlockPos pos1, TreeConfiguration treeConfiguration) {
        int a;
        int b;
        for (int i = 1; i <= 4; i++) {
            if (i == 1) {
                a = 0;
                b = 0;
                for (int j = 0; j < 10; j++) {
                    if (random.nextInt(7) != 5) {
                        if (this.isEmpty(level, pos.below(b + 1).north(a)))
                            b++;
                        else if (this.isEmpty(level, pos.below(b).north(a + 1))) {
                            a++;
                        }
                        this.placeRoot(level, blockstate, random, pos.below(b).north(a), treeConfiguration);
                    }
                }
            }
            if (i == 2) {
                a = 0;
                b = 0;
                for (int j = 0; j < 10; j++) {
                    if (random.nextInt(7) != 5) {
                        if (this.isEmpty(level, pos.below(b + 1).south(a)))
                            b++;
                        else if (this.isEmpty(level, pos.below(b).south(a + 1))) {
                            a++;
                        }
                        this.placeRoot(level, blockstate, random, pos.below(b).south(a), treeConfiguration);
                    }
                }
            }
            if (i == 3) {
                a = 0;
                b = 0;
                for (int j = 0; j < 10; j++) {
                    if (random.nextInt(7) != 5) {
                        if (this.isEmpty(level, pos.below(b + 1).west(a)))
                            b++;
                        else if (this.isEmpty(level, pos.below(b).west(a + 1))) {
                            a++;
                        }
                        this.placeRoot(level, blockstate, random, pos.below(b).west(a), treeConfiguration);
                    }
                }
            }
            if (i == 4) {
                a = 0;
                b = 0;
                for (int j = 0; j < 10; j++) {
                    if (random.nextInt(7) != 5) {
                        if (this.isEmpty(level, pos.below(b + 1).east(a)))
                            b++;
                        else if (this.isEmpty(level, pos.below(b).east(a + 1))) {
                            a++;
                        }
                        this.placeRoot(level, blockstate, random, pos.below(b).east(a), treeConfiguration);
                    }
                }
            }
        }
        return true;
    }


    private boolean isEmpty(LevelSimulatedReader level, BlockPos pos) {
        if (level.isStateAtPosition(pos, state -> state.getBlock() == Blocks.AIR || state.getBlock() == Blocks.CAVE_AIR))
            return true;
        else return false;
    }
}
/*
    protected void placeRoot(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> p_225875_, RandomSource random, BlockPos pos, TreeConfiguration treeConfiguration) {
        p_225875_.accept(pos, this.rootProvider.getState(random, pos));
        }
    }*/