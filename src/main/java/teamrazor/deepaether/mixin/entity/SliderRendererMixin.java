package teamrazor.deepaether.mixin.entity;

import com.aetherteam.aether.client.renderer.entity.SliderRenderer;
import com.aetherteam.aether.client.renderer.entity.model.SliderModel;
import com.aetherteam.aether.entity.monster.dungeon.boss.Slider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.DeepAetherConfig;

/**
 * Used to change the slider texture during Halloween.
 * Also see {@link SliderGlowLayerMixin}
 */
@Mixin(value = SliderRenderer.class, remap = false)
public abstract class SliderRendererMixin extends MobRenderer<Slider, SliderModel> {

    @Unique
    private static final ResourceLocation HALLOWEEN_SLIDER_ASLEEP_TEXTURE = new ResourceLocation(DeepAether.MODID, "textures/entity/slider/halloween_slider_asleep.png");
    @Unique
    private static final ResourceLocation HALLOWEEN_SLIDER_ASLEEP_CRITICAL_TEXTURE = new ResourceLocation(DeepAether.MODID, "textures/entity/slider/halloween_slider_asleep_critical.png");
    @Unique
    private static final ResourceLocation HALLOWEEN_SLIDER_AWAKE_TEXTURE = new ResourceLocation(DeepAether.MODID, "textures/entity/slider/halloween_slider_awake.png");
    @Unique
    private static final ResourceLocation HALLOWEEN_SLIDER_AWAKE_CRITICAL_TEXTURE = new ResourceLocation(DeepAether.MODID, "textures/entity/slider/halloween_slider_awake_critical.png");


    public SliderRendererMixin(EntityRendererProvider.Context context, SliderModel sliderModel, float v) {
        super(context, sliderModel, v);
    }

    @Inject(at = @At("HEAD"), method = "getTextureLocation(Lcom/aetherteam/aether/entity/monster/dungeon/boss/Slider;)Lnet/minecraft/resources/ResourceLocation;", cancellable = true)
    public void getTextureLocation(Slider slider, CallbackInfoReturnable<ResourceLocation> cir) {
        if (((DeepAether.IS_HALLOWEEN || DeepAetherConfig.CLIENT.always_enable_halloween_slider.get()) && (!DeepAetherConfig.CLIENT.never_enable_halloween_slider.get()))) {
            if (!slider.isAwake()) {
                if (!slider.isCritical())
                    cir.setReturnValue(HALLOWEEN_SLIDER_ASLEEP_TEXTURE);
                else cir.setReturnValue(HALLOWEEN_SLIDER_ASLEEP_CRITICAL_TEXTURE);
            }

            else {
                if (!slider.isCritical())
                    cir.setReturnValue(HALLOWEEN_SLIDER_AWAKE_TEXTURE);
                else cir.setReturnValue(HALLOWEEN_SLIDER_AWAKE_CRITICAL_TEXTURE);
            }
        }
    }
}
