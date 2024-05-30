package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.WindflyModel;
import teamrazor.deepaether.entity.living.Windfly;

public class WindflyRenderer extends GeoEntityRenderer<Windfly> {

    public WindflyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WindflyModel());
        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(Windfly instance) {
        return new ResourceLocation(DeepAether.MODID, "textures/entity/windfly/windfly.png");
    }

    @Override
    public void preRender(PoseStack poseStack, Windfly animatable,
                          BakedGeoModel model, MultiBufferSource bufferSource,
                          VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay,
                          float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}