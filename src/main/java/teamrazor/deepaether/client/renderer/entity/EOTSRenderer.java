package teamrazor.deepaether.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.EOTSModel;
import teamrazor.deepaether.client.renderer.DAModelLayers;
import teamrazor.deepaether.entity.living.boss.eots.EOTSController;

public class EOTSRenderer extends MobRenderer<EOTSController, EOTSModel> {

    public EOTSRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EOTSModel(renderManager.bakeLayer(DAModelLayers.EOTS_CONTROLLER)), 0f);
    }

    @Override
    public void render(EOTSController pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(1.3f, 1.3f, 1.3f);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EOTSController instance) {
        return new ResourceLocation(DeepAether.MODID, "textures/entity/eots/eots_controller.png");
    }
}