package teamrazor.deepaether.client.renderer.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.boss.EOTSCloudModel;
import teamrazor.deepaether.entity.boss.EOTSCloud;

public class EOTSCloudRenderer extends GeoEntityRenderer<EOTSCloud> {

    private static final ResourceLocation EOTS_TEXTURE = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/eots/eots_cloud.png");

    public EOTSCloudRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EOTSCloudModel());
    }

    @Override
    public ResourceLocation getTextureLocation(EOTSCloud p_115826_) {
        return EOTS_TEXTURE;
    }

    @Override
    public void preRender(PoseStack poseStack, EOTSCloud animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
