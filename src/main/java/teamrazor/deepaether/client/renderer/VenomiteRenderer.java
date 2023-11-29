package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.VenomiteModel;
import teamrazor.deepaether.entity.Venomite;
import teamrazor.deepaether.entity.quail.Quail;

public class VenomiteRenderer extends GeoEntityRenderer<Venomite> {

    public VenomiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VenomiteModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(Venomite instance) {
        if (instance.isAngry()) return new ResourceLocation(DeepAetherMod.MODID, "textures/entity/venomite/venomite_angry.png");
        return new ResourceLocation(DeepAetherMod.MODID, "textures/entity/venomite/venomite.png");
    }

    @Override
    public RenderType getRenderType(Venomite animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityCutoutNoCull(texture);
    }
}