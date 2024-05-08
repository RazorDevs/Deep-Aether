package teamrazor.deepaether.world.feature.tree;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;
import teamrazor.deepaether.world.feature.DAConfiguredFeatures;

import java.util.Optional;

public final class DATreeGrower {
    public static final TreeGrower BLUE_ROSEROOT = new TreeGrower(
            "blue_roseroot",
            0.25F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(DAConfiguredFeatures.BLUE_ROSEROOT_TREE_SMALL),
            Optional.of(DAConfiguredFeatures.BLUE_ROSEROOT_TREE_LARGE),
            Optional.empty(),
            Optional.empty()
    );
    public static final TreeGrower ROSEROOT = new TreeGrower(
            "roseroot",
            0.25F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(DAConfiguredFeatures.ROSEROOT_TREE_SMALL),
            Optional.of(DAConfiguredFeatures.ROSEROOT_TREE_SMALL),
            Optional.empty(),
            Optional.empty()
    );
    public static final TreeGrower CONBERRY = new TreeGrower(
            "conberry",
            Optional.empty(),
            Optional.of(DAConfiguredFeatures.CONBERRY_TREE),
            Optional.empty());

    public static final TreeGrower YAGROOT = new TreeGrower(
            "yagroot",
            Optional.empty(),
            Optional.of(DAConfiguredFeatures.YAGROOT_TREE_CONFIGURATION),
            Optional.empty());

    public static final TreeGrower CRUDEROOT = new TreeGrower(
            "cruderoot",
            Optional.empty(),
            Optional.of(DAConfiguredFeatures.CRUDEROOT_TREE_CONFIGURATION),
            Optional.empty());

    public static final TreeGrower SUNROOT = new TreeGrower(
            "sunroot",
            Optional.empty(),
            Optional.of(DAConfiguredFeatures.SUNROOT_TREE),
            Optional.empty());
}
