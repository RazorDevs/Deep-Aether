package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.VenomiteBubbleModel;
import teamrazor.deepaether.entity.VenomiteBubble;

@OnlyIn(Dist.CLIENT)
public class VenomiteBubbleRenderer extends EntityRenderer<VenomiteBubble> {
	private static final ResourceLocation LOCATION = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/venomite/venomite_bubble.png");

	private final VenomiteBubbleModel<VenomiteBubble> bubble;

	public VenomiteBubbleRenderer(EntityRendererProvider.Context renderer) {
		super(renderer);
		this.bubble = new VenomiteBubbleModel<>(renderer.bakeLayer(DAModelLayers.VENOMITE_BUBBLE));
	}

	@Override
	public ResourceLocation getTextureLocation(VenomiteBubble venomiteBubble) {
		return LOCATION;
	}


	@Override
	public void render(VenomiteBubble venomiteBubble, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		poseStack.pushPose();
		VertexConsumer iVertexBuilder = bufferSource.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(venomiteBubble)));
		poseStack.mulPose(Axis.XP.rotationDegrees(180f));
		poseStack.translate(0,-1.53,0);
		this.bubble.renderToBuffer(poseStack, iVertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();

		super.render(venomiteBubble, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
	}
}
