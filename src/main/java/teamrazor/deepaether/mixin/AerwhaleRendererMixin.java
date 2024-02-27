package teamrazor.deepaether.mixin;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.client.renderer.entity.AerwhaleRenderer;
import com.aetherteam.aether.client.renderer.entity.MultiModelRenderer;
import com.aetherteam.aether.client.renderer.entity.model.AerwhaleModel;
import com.aetherteam.aether.client.renderer.entity.model.ClassicAerwhaleModel;
import com.aetherteam.aether.entity.passive.Aerwhale;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamrazor.deepaether.client.model.AerwhaleModelLayer;
import teamrazor.deepaether.client.model.ClassicAerwhaleModelLayer;

@Mixin(AerwhaleRenderer.class)
public abstract class AerwhaleRendererMixin extends MobRenderer<Aerwhale, EntityModel<Aerwhale>>  {
    public AerwhaleRendererMixin(EntityRendererProvider.Context context, AerwhaleModel defaultModel, float shadowRadius) {
        super(context, defaultModel, shadowRadius);
    }

    @Inject(at = @At("TAIL"), method = "<init>", remap = false)
    private void AerwhaleRenderer(EntityRendererProvider.Context context, CallbackInfo ci) {
        if(AetherConfig.CLIENT.legacy_models.get())
            this.addLayer(new ClassicAerwhaleModelLayer(this));
        else if(AetherConfig.CLIENT.legacy_models.get())
            this.addLayer(new AerwhaleModelLayer(this));
    }
}