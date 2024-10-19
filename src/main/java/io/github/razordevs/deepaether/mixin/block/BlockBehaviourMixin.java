package io.github.razordevs.deepaether.deepaether.mixin.block;

import io.github.razordevs.deepaether.deepaether.block.misc.DisableSound;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockBehaviourMixin implements DisableSound {

    @Unique
    boolean deep_Aether$disableSound = false;

    @Override
    public void deep_Aether$disableSound(boolean disableSound) {
        this.deep_Aether$disableSound = disableSound;
    }

    @Inject(at = @At("HEAD"), method = "getSoundType", cancellable = true)
    private void getSoundType(BlockState pState, CallbackInfoReturnable<SoundType> cir) {
        if(deep_Aether$disableSound) {
            deep_Aether$disableSound = false;
            cir.setReturnValue(SoundType.EMPTY);
        }
    }
}
