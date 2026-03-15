package io.github.razordevs.deep_aether.client.renderer.entity;

import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.entity.model.CrystalModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.entity.Tumblecloud;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class TumblecloudRenderer extends EntityRenderer<Tumblecloud> {
    public static final ResourceLocation TEXTURE = DeepAether.getResource("textures/entity/tumblecloud.png");
    private final CrystalModel<Tumblecloud> crystal;

    public TumblecloudRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.crystal = new CrystalModel<>(context.bakeLayer(AetherModelLayers.CLOUD_CRYSTAL));
    }

    @Override
    public void render(Tumblecloud crystal, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0, 0.4, 0.0);
        VertexConsumer iVertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(crystal)));
        float f = (float) crystal.tickCount + partialTicks;
        poseStack.mulPose(Axis.XP.rotationDegrees(f * 0.1F * 360.0F));
        this.crystal.crystal1.render(poseStack, iVertexBuilder, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.mulPose(Axis.YP.rotationDegrees(f * 0.1F * 360.0F));
        this.crystal.crystal2.render(poseStack, iVertexBuilder, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.mulPose(Axis.ZP.rotationDegrees(f * 0.1F * 360.0F));
        this.crystal.crystal3.render(poseStack, iVertexBuilder, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(crystal, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Tumblecloud tumblecloud) {
        return TEXTURE;
    }
}
