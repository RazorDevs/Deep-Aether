package teamrazor.deepaether.mixin;

import com.aetherteam.aether.client.renderer.entity.AerwhaleRenderer;
import com.aetherteam.aether.client.renderer.entity.model.AerwhaleModel;
import com.aetherteam.aether.entity.passive.Aerwhale;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamrazor.deepaether.client.model.AerwhaleModelLayer;

@Mixin(AerwhaleRenderer.class)
public abstract class AerwhaleRendererMixin extends MobRenderer<Aerwhale, AerwhaleModel> {


    public AerwhaleRendererMixin(EntityRendererProvider.Context context, AerwhaleModel defaultModel, float shadowRadius) {
        super(context, defaultModel, shadowRadius);
    }

    @Inject(at = @At("TAIL"), method = "<init>", remap = false)
    private void AerwhaleRenderer(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new AerwhaleModelLayer(this));
    }


}