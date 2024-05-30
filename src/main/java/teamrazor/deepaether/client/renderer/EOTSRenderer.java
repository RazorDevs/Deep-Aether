package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.EOTSModel;
import teamrazor.deepaether.entity.living.boss.eots.EOTS;

public class EOTSRenderer extends GeoEntityRenderer<EOTS> {

    public EOTSRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EOTSModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(EOTS instance) {
        return new ResourceLocation(DeepAether.MODID, "textures/entity/eots/eots.png");
    }

    @Override
    public void preRender(PoseStack poseStack, EOTS animatable,
                          BakedGeoModel model, MultiBufferSource bufferSource,
                          VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay,
                          float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}