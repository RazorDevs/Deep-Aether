package teamrazor.deepaether.client.renderer.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.boss.EOTSModel;
import teamrazor.deepaether.entity.boss.EOTSEntity;

public class EOTSRenderer extends GeoEntityRenderer<EOTSEntity> {

    private static final ResourceLocation EOTS_TEXTURE = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/eots/eots.png");

    public EOTSRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EOTSModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(EOTSEntity p_115826_) {
        return EOTS_TEXTURE;
    }

    @Override
    public void preRender(PoseStack poseStack, EOTSEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
