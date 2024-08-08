package teamrazor.deepaether.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.EOTSModel;
import teamrazor.deepaether.entity.living.boss.eots.EOTSController;

public class EOTSRenderer extends MobRenderer<EOTSController, EOTSModel> {

    public EOTSRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EOTSModel(), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EOTSController instance) {
        return new ResourceLocation(DeepAether.MODID, "textures/entity/eots/eots.png");
    }
}