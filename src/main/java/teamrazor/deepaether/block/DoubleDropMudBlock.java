package teamrazor.deepaether.block;

import com.gildedgames.aether.block.AetherBlockStateProperties;
import com.gildedgames.aether.block.natural.AetherDoubleDropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DoubleDropMudBlock extends AetherDoubleDropBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public DoubleDropMudBlock(BlockBehaviour.Properties p_221545_) {
        super(p_221545_);
        this.registerDefaultState(this.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, false));
    }

    public VoxelShape getCollisionShape(BlockState p_221561_, BlockGetter p_221562_, BlockPos p_221563_, CollisionContext p_221564_) {
        return SHAPE;
    }

    public VoxelShape getBlockSupportShape(BlockState p_221566_, BlockGetter p_221567_, BlockPos p_221568_) {
        return Shapes.block();
    }

    public VoxelShape getVisualShape(BlockState p_221556_, BlockGetter p_221557_, BlockPos p_221558_, CollisionContext p_221559_) {
        return Shapes.block();
    }

    public boolean isPathfindable(BlockState p_221547_, BlockGetter p_221548_, BlockPos p_221549_, PathComputationType p_221550_) {
        return false;
    }

    public float getShadeBrightness(BlockState p_221552_, BlockGetter p_221553_, BlockPos p_221554_) {
        return 0.2F;
    }
}

