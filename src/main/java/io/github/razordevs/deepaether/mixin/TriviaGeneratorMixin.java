package io.github.razordevs.deepaether.deepaether.mixin;

import com.aetherteam.aether.client.TriviaGenerator;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

/**
 * Used to make th trivia component pro-tip string blue if the rest of the trivia component is blue.
 * Used to make deep aether pro-tips blue to differ them from normal aether pro-tips
 */
@Mixin(value = TriviaGenerator.class, remap = false)
public abstract class TriviaGeneratorMixin {


    @Shadow @Nullable protected abstract Component getTriviaComponent();

    @Inject(at = @At("HEAD"), method = "getTriviaLine", cancellable = true, remap = false)
    private void getTriviaLine(CallbackInfoReturnable<Component> cir) {
        if(this.getTriviaComponent() != null) {
            if (this.getTriviaComponent().getContents().toString().contains("aether.pro_tips.line.deep_aether")) {
                cir.setReturnValue(Component.translatable("gui.deep_aether.pro_tip").append(Component.literal(" ").append(this.getTriviaComponent())));
            }
        }
    }
}
