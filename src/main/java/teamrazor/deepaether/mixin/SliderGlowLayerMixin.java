package teamrazor.deepaether.mixin;

import com.aetherteam.aether.client.renderer.entity.layers.SliderGlowLayer;
import com.aetherteam.aether.entity.monster.dungeon.boss.Slider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.DeepAetherConfig;
import teamrazor.deepaether.DeepAetherMod;

@Mixin(SliderGlowLayer.class)
public abstract class SliderGlowLayerMixin {
    @Unique
    private static final RenderType HALLOWEEN_SLIDER_AWAKE_GLOW = RenderType.eyes(new ResourceLocation(DeepAetherMod.MODID, "textures/entity/slider/halloween_slider_awake_glow.png"));
    @Unique
    private static final RenderType HALLOWEEN_AWAKE_CRITICAL_GLOW = RenderType.eyes(new ResourceLocation(DeepAetherMod.MODID, "textures/entity/slider/halloween_slider_awake_critical_glow.png"));

    @Inject(at = @At("HEAD"), remap = false, method = "renderType(Lcom/aetherteam/aether/entity/monster/dungeon/boss/Slider;)Lnet/minecraft/client/renderer/RenderType;", cancellable = true)
    public void renderType(Slider slider, CallbackInfoReturnable<RenderType> cir) {
        if (((DeepAetherMod.IS_HALLOWEEN || DeepAetherConfig.CLIENT.always_enable_halloween_slider.get()) && (!DeepAetherConfig.CLIENT.never_enable_halloween_slider.get()))) {
            cir.setReturnValue(slider.isCritical() ? HALLOWEEN_AWAKE_CRITICAL_GLOW : HALLOWEEN_SLIDER_AWAKE_GLOW);
        }
    }
}
