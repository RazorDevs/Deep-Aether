package teamrazor.deepaether.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.EOTSSegmentModel;
import teamrazor.deepaether.client.renderer.DAModelLayers;
import teamrazor.deepaether.entity.living.boss.eots.EOTSSegment;


@OnlyIn(Dist.CLIENT)
public class EOTSSegmentRenderer extends MobRenderer<EOTSSegment, EOTSSegmentModel> {
	private static final ResourceLocation EOTS_SEGMENT_LOCATION = new ResourceLocation(DeepAether.MODID, "textures/entity/eots/eots_segment.png");
	private static final ResourceLocation EOTS_SEGMENT_CONTROLLING_LOCATION = new ResourceLocation(DeepAether.MODID, "textures/entity/eots/eots_segment_controlling.png");

	public EOTSSegmentRenderer(EntityRendererProvider.Context renderer) {
		super(renderer, new EOTSSegmentModel(renderer.bakeLayer(DAModelLayers.EOTS_SEGMENT)), 0F);
	}

	@Override
	public ResourceLocation getTextureLocation(EOTSSegment segment) {
		return segment.isControllingSegment() ? EOTS_SEGMENT_CONTROLLING_LOCATION : EOTS_SEGMENT_LOCATION;
	}

	@Override
	public void render(EOTSSegment eots, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
		/*pPoseStack.pushPose();
		if(!eots.isControllingSegment()) {
			int sub = 0;
			sub = subTreeSearch(eots, sub) / 10;
			pPoseStack.scale(1.2f - sub, 1.2f - sub, 1.2f - sub);
		}else {
			pPoseStack.scale(1.2f, 1.2f, 1.2f);
		}
		pPoseStack.popPose();*/

		pPoseStack.scale(1.2f, 1.2f, 1.2f);
		super.render(eots, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
	}


	@Override
	protected float getBob(EOTSSegment pLivingBase, float pPartialTick) {
		return pPartialTick;
	}

	/*
	private static int subTreeSearch(EOTSSegment eots, int sub){
		if(!eots.isControllingSegment() && eots.getParent() != null){
			sub++;
			sub = subTreeSearch((EOTSSegment) eots.getServer().getLevel(eots.level().dimension()).getEntity(UUID.fromString(eots.getEntityData().get(eots.PARENT_DATA))), sub);
		}
		return sub;
	}
*/

	@Override
	protected void setupRotations(EOTSSegment pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
		super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
		pPoseStack.mulPose(Axis.XP.rotationDegrees(pEntityLiving.getXRot()));
	}

}
