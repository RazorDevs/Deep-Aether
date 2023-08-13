package teamrazor.deepaether.mixin;


import com.aetherteam.aether.block.natural.AercloudBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.Cancellable;
import teamrazor.deepaether.entity.swet.AercloudSwet;

@Mixin(AercloudBlock.class)
public class AercloudBlockMixin {

    @Unique
    private static final VoxelShape NO_COLLISION_SHAPE = Shapes.box(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);


    @Inject(at = @At("HEAD"), method = "entityInside", remap = false, cancellable = true)
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        if(entity instanceof AercloudSwet swet) {
            if(swet.isVehicle()) {
                swet.setDeltaMovement(0, 2, 0);
            }
            else ci.cancel();
        }
    }
    @Inject(at = @At("HEAD"), method = "getCollisionShape", remap = false, cancellable = true)
    public void getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (context instanceof EntityCollisionContext entityCollisionContext) {
            Entity entity = entityCollisionContext.getEntity();
            if (entity instanceof AercloudSwet swet) {
                if(swet.isVehicle()) {
                    cir.setReturnValue(NO_COLLISION_SHAPE);
                    }
                }
            }
        }
    }
