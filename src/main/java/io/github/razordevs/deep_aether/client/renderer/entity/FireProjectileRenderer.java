package io.github.razordevs.deep_aether.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.github.razordevs.deep_aether.entity.living.projectile.FireProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class FireProjectileRenderer extends EntityRenderer<FireProjectile> {
    public static final ResourceLocation FIRE_PROJECTILE_TEXTURE = ResourceLocation.withDefaultNamespace("textures/item/fire_charge.png");
            //ResourceLocation.fromNamespaceAndPath(DeepAetherMod.MODID, "textures/item/sun_core.png");
    public FireProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0F;
    }

    /**
     * [VANILLA COPY] - {@link net.minecraft.client.renderer.entity.DragonFireballRenderer}.
     */
    @Override
    public void render(FireProjectile hammer, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.pushPose();
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(hammer)));
        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();
        vertex(consumer, matrix4f, poseStack, packedLight, 0.0F, 0.0F, 0.0F, 1.0F);
        vertex(consumer, matrix4f, poseStack, packedLight, 1.0F, 0.0F, 1.0F, 1.0F);
        vertex(consumer, matrix4f, poseStack, packedLight, 1.0F, 1.0F, 1.0F, 0.0F);
        vertex(consumer, matrix4f, poseStack, packedLight, 0.0F, 1.0F, 0.0F, 0.0F);
        poseStack.popPose();
        super.render(hammer, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f matrix, PoseStack normals, int packedLight, float offsetX, float offsetY, float textureX, float textureY) {
        consumer.addVertex(matrix, offsetX - 0.5F, offsetY - 0.25F, 0.0F).setColor(255, 255, 255, 255).setUv(textureX, textureY).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight, packedLight).setNormal(normals.last(), 0.0F, 1.0F, 0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(FireProjectile hammer) {
        return FIRE_PROJECTILE_TEXTURE;
    }
}
