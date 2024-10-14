package teamrazor.deepaether.mixin.entity;

import com.aetherteam.aether.client.renderer.entity.layers.SliderGlowLayer;
import com.aetherteam.aether.entity.monster.dungeon.boss.Slider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.DeepAetherConfig;
/**
 * Used to change the slider glow layer texture during Halloween.
 * Also see {@link SliderRendererMixin}
 */
@Mixin(SliderGlowLayer.class)
public abstract class SliderGlowLayerMixin {
    @Unique
    private static final RenderType HALLOWEEN_SLIDER_AWAKE_GLOW = RenderType.eyes(new ResourceLocation(DeepAether.MODID, "textures/entity/slider/halloween_slider_awake_glow.png"));
    @Unique
    private static final RenderType HALLOWEEN_AWAKE_CRITICAL_GLOW = RenderType.eyes(new ResourceLocation(DeepAether.MODID, "textures/entity/slider/halloween_slider_awake_critical_glow.png"));

    @Inject(at = @At("HEAD"), remap = false, method = "renderType(Lcom/aetherteam/aether/entity/monster/dungeon/boss/Slider;)Lnet/minecraft/client/renderer/RenderType;", cancellable = true)
    public void renderType(Slider slider, CallbackInfoReturnable<RenderType> cir) {
        if (((DeepAether.IS_HALLOWEEN || DeepAetherConfig.CLIENT.always_enable_halloween_slider.get()) && (!DeepAetherConfig.CLIENT.never_enable_halloween_slider.get()))) {
            cir.setReturnValue(slider.isCritical() ? HALLOWEEN_AWAKE_CRITICAL_GLOW : HALLOWEEN_SLIDER_AWAKE_GLOW);
        }
    }
}
