package teamrazor.deepaether.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.item.gear.EquipmentUtil;

@Mixin(PowderSnowBlock.class)
public class PowderedSnowMixin {

    @Inject(at = @At("HEAD"), method = "canEntityWalkOnPowderSnow", cancellable = true)
    private static void canEntityWalkOnPowderSnow(Entity pEntity, CallbackInfoReturnable<Boolean> cir) {
        if(pEntity instanceof LivingEntity entity) {
            if(EquipmentUtil.hasCloudNecklace(entity)) {
                cir.setReturnValue(true);
            }
        }
    }
}
