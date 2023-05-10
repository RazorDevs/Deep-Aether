package teamrazor.deepaether.block;

import com.aetherteam.aether.block.natural.AercloudBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static com.aetherteam.aether.block.AetherBlockStateProperties.DOUBLE_DROPS;

public class RedAercloudBlock extends AercloudBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    protected static final VoxelShape COLLISION_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 0.01, 16.0);
    protected static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0.0, 0.0, 0.0, 1.0, 0.9, 1.0);
    protected static final VoxelShape POWERED_COLLISION_SHAPE = Shapes.box(0.0, 0.0, 0.0, 0, 0, 0);


    public RedAercloudBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(POWERED, false)
                .setValue(DOUBLE_DROPS, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {

        if (!level.hasNeighborSignal(pos)) {
            entity.resetFallDistance();
            if (entity.getDeltaMovement().y < 0.0) {
                entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0, 0.005, 1.0));
            }
        }
    }

    private boolean checkIfPowered(LevelReader level, BlockPos pos)
    {
        boolean check1 = level.getDirectSignal(pos, Direction.DOWN) > 0 ||  level.getDirectSignal(pos, Direction.UP) > 0;
        boolean check2 =  level.getDirectSignal(pos, Direction.NORTH) > 0 ||  level.getDirectSignal(pos, Direction.SOUTH) > 0;
        boolean check3 =  level.getDirectSignal(pos, Direction.EAST) > 0 ||  level.getDirectSignal(pos, Direction.WEST) > 0;

        return check1 || check2 || check3;
    }


    @Override
    public int getSignal(BlockState blockState, BlockGetter level, BlockPos pos, Direction direction) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public boolean isSignalSource(BlockState blockState) {
        return true;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean b) {
        if (level.hasNeighborSignal(pos)){
            state.setValue(POWERED, true);
        }
        super.neighborChanged(state, level, pos, block, pos1, b);
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        if (checkIfPowered(level, pos)){
            state.setValue(POWERED, true);
        }
        super.onNeighborChange(state, level, pos, neighbor);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (!state.getValue(POWERED)) {
            if (context instanceof EntityCollisionContext entityCollisionContext) {
                Entity entity = entityCollisionContext.getEntity();
                if (entity != null) {
                    if (entity.fallDistance > 2.5F && (!(entity instanceof LivingEntity livingEntity) || !livingEntity.isFallFlying())) {
                        return FALLING_COLLISION_SHAPE; // Alternate shape when falling fast enough.
                    }
                }
            }
            return this.getDefaultCollisionShape(state, level, pos, context); // Default shape.
        }else{
            return POWERED_COLLISION_SHAPE;
        }
    }

    protected VoxelShape getDefaultCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55828_) {
        p_55828_.add(DOUBLE_DROPS, POWERED);
    }

}
