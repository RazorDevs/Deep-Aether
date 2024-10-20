package io.github.razordevs.deep_aether.mixin.block;

import io.github.razordevs.deep_aether.item.gear.DAEquipmentUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderedSnowMixin {

    @Inject(at = @At("HEAD"), method = "canEntityWalkOnPowderSnow", cancellable = true)
    private static void canEntityWalkOnPowderSnow(Entity pEntity, CallbackInfoReturnable<Boolean> cir) {
        if(pEntity instanceof LivingEntity entity) {
            if(DAEquipmentUtil.hasCloudNecklace(entity)) {
                cir.setReturnValue(true);
            }
        }
    }
}
