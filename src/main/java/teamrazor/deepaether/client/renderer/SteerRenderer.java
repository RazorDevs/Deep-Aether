package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.SteerModel;
import teamrazor.deepaether.entity.Steer;

public class SteerRenderer extends GeoEntityRenderer<Steer> {

    private static final ResourceLocation STEER_TEXTURE = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/steer.png");

    public SteerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SteerModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(Steer p_115826_) {
        return STEER_TEXTURE;
    }

    @Override
    public void preRender(PoseStack poseStack, Steer animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}

