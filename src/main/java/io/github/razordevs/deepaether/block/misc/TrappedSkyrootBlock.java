package io.github.razordevs.deepaether.deepaether.block.misc;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.event.AetherEventDispatch;
import io.github.razordevs.deepaether.deepaether.init.DAEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class TrappedSkyrootBlock extends Block {
    public static final IntegerProperty TRAPPED_MOB_TYPE = IntegerProperty.create("trapped_mob_type", 0, 2);

    public TrappedSkyrootBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TRAPPED_MOB_TYPE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TRAPPED_MOB_TYPE);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player player && AetherEventDispatch.onTriggerTrap(player, level, pos, state)) {
            level.setBlockAndUpdate(pos, AetherBlocks.SKYROOT_PLANKS.get().defaultBlockState());
            if (level instanceof ServerLevel serverLevel) {
                float yRot = player.getYRot() * Mth.DEG_TO_RAD;
                Vec3 targetVec = new Vec3(pos.getX() + 0.5 - Mth.sin(yRot) * 3, pos.getY() + 1, pos.getZ() + 0.5 + Mth.cos(yRot) * 3);
                ClipContext context = new ClipContext(player.position(), targetVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player);
                BlockHitResult hitResult = serverLevel.clip(context);
                BlockPos spawnPos = hitResult.getBlockPos();
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    spawnPos = spawnPos.relative(hitResult.getDirection());
                }
                this.getEntity(state, level).spawn(serverLevel, spawnPos, MobSpawnType.TRIGGERED);
                serverLevel.playSound(null, pos, AetherSoundEvents.BLOCK_DUNGEON_TRAP_TRIGGER.get(), SoundSource.BLOCKS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    /**
     * Different Rooms can Spawn With Different Entities
     */
    private EntityType<?> getEntity(BlockState state, Level level) {
        EntityType<?> type = switch (state.getValue(TRAPPED_MOB_TYPE)) {
            case 1 -> AetherEntityTypes.AECHOR_PLANT.get();
            case 2 -> AetherEntityTypes.COCKATRICE.get();
            default -> DAEntities.BABY_ZEPHYR.get();
        };
        if(level.getRandom().nextBoolean())
            return type;
        else return DAEntities.BABY_ZEPHYR.get();
    }
}
