package teamrazor.deepaether.mixin;

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
import teamrazor.deepaether.DeepAetherMod;

@Mixin(value = SliderRenderer.class, remap = false)
public abstract class SliderMixin extends MobRenderer<Slider, SliderModel> {

    @Unique
    private static final ResourceLocation SLIDER_ASLEEP_TEXTURE = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/mobs/slider/slider_asleep.png");
    @Unique
    private static final ResourceLocation SLIDER_ASLEEP_CRITICAL_TEXTURE = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/mobs/slider/slider_asleep_critical.png");
    @Unique
    private static final ResourceLocation SLIDER_AWAKE_TEXTURE = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/mobs/slider/slider_awake.png");
    @Unique
    private static final ResourceLocation SLIDER_AWAKE_CRITICAL_TEXTURE = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/mobs/slider/slider_awake_critical.png");


    public SliderMixin(EntityRendererProvider.Context p_174304_, SliderModel p_174305_, float p_174306_) {
        super(p_174304_, p_174305_, p_174306_);
    }


    @Inject(at = @At("HEAD"), method = "getTextureLocation(Lcom/aetherteam/aether/entity/monster/dungeon/boss/Slider;)Lnet/minecraft/resources/ResourceLocation;", cancellable = true)
    public void getTextureLocation(Slider slider, CallbackInfoReturnable<ResourceLocation> cir) {
        if(DeepAetherMod.IS_HALLOWEN) {
            if (!slider.isAwake()) {
                cir.setReturnValue(!slider.isCritical() ? SLIDER_ASLEEP_TEXTURE : SLIDER_ASLEEP_CRITICAL_TEXTURE);
            } else {
                cir.setReturnValue(!slider.isCritical() ? SLIDER_AWAKE_TEXTURE : SLIDER_AWAKE_CRITICAL_TEXTURE);
            }
        }
    }
}
