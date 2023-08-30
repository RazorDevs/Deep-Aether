package teamrazor.deepaether.client.renderer.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.WindCharge;

@OnlyIn(Dist.CLIENT)
public class WindChargeRenderer extends EntityRenderer<WindCharge> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/eots/wind_charge.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(TEXTURE_LOCATION);

    public WindChargeRenderer(EntityRendererProvider.Context p_173962_) {
        super(p_173962_);
    }

    protected int getBlockLightLevel(WindCharge p_114087_, BlockPos p_114088_) {
        return 15;
    }

    public void render(WindCharge p_114080_, float p_114081_, float p_114082_, PoseStack p_114083_, MultiBufferSource p_114084_, int p_114085_) {
        p_114083_.pushPose();
        p_114083_.scale(1.0F, 1.0F, 1.0F);
        p_114083_.mulPose(this.entityRenderDispatcher.cameraOrientation());
        p_114083_.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose posestack$pose = p_114083_.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        VertexConsumer vertexconsumer = p_114084_.getBuffer(RENDER_TYPE);
        vertex(vertexconsumer, matrix4f, matrix3f, p_114085_, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, p_114085_, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, p_114085_, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, matrix3f, p_114085_, 0.0F, 1, 0, 0);
        p_114083_.popPose();
        super.render(p_114080_, p_114081_, p_114082_, p_114083_, p_114084_, p_114085_);
    }

    private static void vertex(VertexConsumer p_254095_, Matrix4f p_254477_, Matrix3f p_253948_, int p_253829_, float p_253995_, int p_254031_, int p_253641_, int p_254243_) {
        p_254095_.vertex(p_254477_, p_253995_ - 0.5F, (float)p_254031_ - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float)p_253641_, (float)p_254243_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_253829_).normal(p_253948_, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(WindCharge p_114482_) {
        return TEXTURE_LOCATION;
    }
}
