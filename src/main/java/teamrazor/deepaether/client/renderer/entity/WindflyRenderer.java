package teamrazor.deepaether.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.WindflyModel;
import teamrazor.deepaether.client.renderer.DAModelLayers;
import teamrazor.deepaether.entity.living.Windfly;

public class WindflyRenderer extends MobRenderer<Windfly, WindflyModel> {

    public WindflyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WindflyModel(renderManager.bakeLayer(DAModelLayers.WINDFLY)), 0.5f);
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(@NotNull Windfly instance) {
        return new ResourceLocation(DeepAether.MODID, "textures/entity/windfly/windfly.png");
    }
}