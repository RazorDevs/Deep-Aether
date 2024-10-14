package teamrazor.deepaether.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import teamrazor.deepaether.datagen.tags.DATags;

/*
    This mixin is used to override the behaviour of fluid interactions with lava.
    This is because with the existing fluid interaction system, lava's interactions
    are not taken into account and are hardcoded.
 */
@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin extends FlowingFluid {
    @ModifyReturnValue(method = "canBeReplacedWith", at = @At("RETURN"))
    private boolean deep_Aether$lavaReplaceOverride(boolean original, @Local(argsOnly = true) Fluid fluid, @Local(argsOnly = true) Direction direction){
        return original || (direction == Direction.DOWN && fluid.is(DATags.Fluids.POISON));
    }
}
