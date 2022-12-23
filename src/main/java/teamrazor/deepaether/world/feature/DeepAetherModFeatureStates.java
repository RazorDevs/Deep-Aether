package teamrazor.deepaether.world.feature;

import com.gildedgames.aether.block.AetherBlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.init.DeepAetherModBlocks;

public class DeepAetherModFeatureStates {
    public static final BlockState ROSE_LOG = DeepAetherModBlocks.ROSE_LOG.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState ROSE_LEAVES = DeepAetherModBlocks.ROSE_LEAVES.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState YAGROOT_LOG = DeepAetherModBlocks.YAGROOT_LOG.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState YAGROOT_LEAVES = DeepAetherModBlocks.YAGROOT_LEAVES.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState CRUDEROOT_LOG = DeepAetherModBlocks.CRUDEROOT_LOG.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState CRUDEROOT_LEAVES = DeepAetherModBlocks.CRUDEROOT_LEAVES.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);
    public static final BlockState FLOWERING_ROSE_LEAVES = DeepAetherModBlocks.FLOWERING_ROSE_LEAVES.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true);

}
