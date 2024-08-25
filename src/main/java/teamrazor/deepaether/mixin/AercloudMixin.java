package teamrazor.deepaether.mixin;

import com.aetherteam.aether.block.natural.AercloudBlock;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.item.gear.EquipmentUtil;

@Mixin(AercloudBlock.class)
public class AercloudMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/shapes/EntityCollisionContext;getEntity()Lnet/minecraft/world/entity/Entity;"), method = "getCollisionShape", cancellable = true)
    public void getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir, @Local EntityCollisionContext entityCollisionContext) {
        Entity entity = entityCollisionContext.getEntity();
        if (entity instanceof LivingEntity entity1) {
            if(EquipmentUtil.hasCloudNecklace(entity1))
                    cir.setReturnValue(Shapes.box(0.0, 0.0, 0.0, 1.0, 1.0, 1.0));
        }
    }
}
