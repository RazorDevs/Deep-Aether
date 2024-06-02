package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Phantom;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.AerglowFishModel;
import teamrazor.deepaether.client.model.EOTSSegmentModel;
import teamrazor.deepaether.entity.living.boss.eots.EOTSSegment;


@OnlyIn(Dist.CLIENT)
public class EOTSSegmentRenderer extends MobRenderer<EOTSSegment, EOTSSegmentModel> {
	private static final ResourceLocation EOTS_SEGMENT_LOCATION = new ResourceLocation(DeepAether.MODID, "textures/entity/eots/eots_segment.png");

	public EOTSSegmentRenderer(EntityRendererProvider.Context renderer) {
		super(renderer, new EOTSSegmentModel(renderer.bakeLayer(DAModelLayers.EOTS_SEGMENT)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EOTSSegment segment) {
		return EOTS_SEGMENT_LOCATION;
	}

	@Override
	protected void setupRotations(EOTSSegment pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
		super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
		pPoseStack.mulPose(Axis.XP.rotationDegrees(pEntityLiving.getXRot()));
	}
}
