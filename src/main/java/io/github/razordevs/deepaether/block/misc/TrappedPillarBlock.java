package io.github.razordevs.deepaether.deepaether.block.misc;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.event.AetherEventDispatch;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class TrappedPillarBlock extends RotatedPillarBlock {
    private final Supplier<EntityType<?>> spawnableEntityTypeSupplier;
    private final Supplier<? extends BlockState> defaultStateSupplier;

    public TrappedPillarBlock(Supplier<EntityType<?>> spawnableEntityTypeSupplier, Supplier<? extends BlockState> defaultStateSupplier, BlockBehaviour.Properties properties) {
        super(properties);
        this.spawnableEntityTypeSupplier = spawnableEntityTypeSupplier;
        this.defaultStateSupplier = defaultStateSupplier;
    }

    public BlockState getFacadeBlock() {
        return (BlockState)this.defaultStateSupplier.get();
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player player) {
            if (AetherEventDispatch.onTriggerTrap(player, level, pos, state)) {
                level.setBlockAndUpdate(pos, (BlockState)this.defaultStateSupplier.get());
                if (level instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel)level;
                    float yRot = player.getYRot() * 0.017453292F;
                    Vec3 targetVec = new Vec3((double)pos.getX() + 0.5 - (double)(Mth.sin(yRot) * 3.0F), (double)(pos.getY() + 1), (double)pos.getZ() + 0.5 + (double)(Mth.cos(yRot) * 3.0F));
                    ClipContext context = new ClipContext(player.position(), targetVec, net.minecraft.world.level.ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player);
                    BlockHitResult hitResult = serverLevel.clip(context);
                    BlockPos spawnPos = hitResult.getBlockPos();
                    if (hitResult.getType() == HitResult.Type.BLOCK) {
                        spawnPos = spawnPos.relative(hitResult.getDirection());
                    }

                    ((EntityType)this.spawnableEntityTypeSupplier.get()).spawn(serverLevel, spawnPos, MobSpawnType.TRIGGERED);
                    serverLevel.playSound((Player)null, pos, (SoundEvent) AetherSoundEvents.BLOCK_DUNGEON_TRAP_TRIGGER.get(), SoundSource.BLOCKS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                }
            }
        }

    }
}
