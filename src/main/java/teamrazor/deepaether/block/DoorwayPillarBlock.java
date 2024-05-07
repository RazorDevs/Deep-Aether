package teamrazor.deepaether.block;

import com.aetherteam.aether.client.particle.AetherParticleTypes;
import com.aetherteam.aether.entity.EntityUtil;
import com.aetherteam.aether.entity.ai.AetherBlockPathTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DoorwayPillarBlock extends Block {
    public static final BooleanProperty INVISIBLE = BooleanProperty.create("invisible");
    public static final VoxelShape INVISIBLE_SHAPE = Block.box(5.0, 5.0, 5.0, 11.0, 11.0, 11.0);
    private final Supplier<EntityType<?>> blockedEntityTypeSupplier;

    public DoorwayPillarBlock(Supplier<EntityType<?>> blockedEntityTypeSupplier, BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)this.getStateDefinition().any()).setValue(INVISIBLE, false));
        this.blockedEntityTypeSupplier = blockedEntityTypeSupplier;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{INVISIBLE});
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.isCreative()) {
            BlockState newState = (BlockState)state.cycle(INVISIBLE);
            level.setBlock(pos, newState, 3);
            return InteractionResult.SUCCESS;
        } else {
            return super.use(state, level, pos, player, hand, hit);
        }
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        boolean flag = super.canBeReplaced(state, context);
        if (!flag) {
            Level level = context.getLevel();
            BlockPos pos = context.getClickedPos();

            for(int i = 0; i < 2; ++i) {
                EntityUtil.spawnRemovalParticles(level, pos);
            }
        }

        return flag;
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.gameMode != null && minecraft.gameMode.getPlayerMode() == GameType.CREATIVE && minecraft.player != null && minecraft.level != null) {
            ItemStack itemStack = minecraft.player.getMainHandItem();
            Item item = itemStack.getItem();
            if (item instanceof BlockItem) {
                BlockItem blockItem = (BlockItem)item;
                if (blockItem.getBlock() == this && (Boolean)state.getValue(INVISIBLE)) {
                    minecraft.level.addParticle((ParticleOptions) AetherParticleTypes.BOSS_DOORWAY_BLOCK.get(), (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 0.0, 0.0, 0.0);
                }
            }
        }

    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if ((Boolean)state.getValue(INVISIBLE)) {
            if (context instanceof EntityCollisionContext) {
                EntityCollisionContext entity = (EntityCollisionContext)context;
                Entity var7 = entity.getEntity();
                if (var7 instanceof Player) {
                    Player player = (Player)var7;
                    if (player.isCreative()) {
                        return INVISIBLE_SHAPE;
                    }
                }
            }

            return Shapes.empty();
        } else {
            return super.getShape(state, level, pos, context);
        }
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entity) {
            if (entity.getEntity() != null && this.blockedEntityTypeSupplier.get() != null && entity.getEntity().getType() == this.blockedEntityTypeSupplier.get()) {
                return Shapes.block();
            }
        }

        return (Boolean)state.getValue(INVISIBLE) ? Shapes.empty() : super.getCollisionShape(state, level, pos, context);
    }

    public RenderShape getRenderShape(BlockState state) {
        return (Boolean)state.getValue(INVISIBLE) ? RenderShape.INVISIBLE : super.getRenderShape(state);
    }

    public @Nullable BlockPathTypes getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
        return AetherBlockPathTypes.BOSS_DOORWAY;
    }
}
