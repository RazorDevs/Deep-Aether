package io.github.razordevs.deep_aether.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.razordevs.deep_aether.client.model.AerglowFishModel;
import io.github.razordevs.deep_aether.client.renderer.DAModelLayers;
import io.github.razordevs.deep_aether.entity.living.AerglowFish;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import teamrazor.deepaether.DeepAether;

@OnlyIn(Dist.CLIENT)
public class AetherFishRenderer extends MobRenderer<AerglowFish, AerglowFishModel<AerglowFish>> {
	private static final ResourceLocation AERGLOW_FISH_LOCATION = ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/entity/aerglow_fish.png");

	public AetherFishRenderer(EntityRendererProvider.Context renderer) {
		super(renderer, new AerglowFishModel<>(renderer.bakeLayer(DAModelLayers.AERGLOW_FISH)), 0.4F);
	}

	public ResourceLocation getTextureLocation(AerglowFish fish) {
		return AERGLOW_FISH_LOCATION;
	}

	protected void setupRotations(AerglowFish fish, PoseStack pose, float p_115830_, float p_115831_, float p_115832_) {
		super.setupRotations(fish, pose, p_115830_, p_115831_, p_115832_);
		float f = 1.0F;
		float f1 = 1.0F;
		if (!fish.isInWater()) {
			f = 1.3F;
			f1 = 1.7F;
		}

		float f2 = f * 4.3F * Mth.sin(f1 * 0.6F * p_115830_);
		pose.mulPose(Axis.YP.rotationDegrees(f2));
		pose.translate(0.0F, 0.0F, -0.4F);
		if (!fish.isInWater()) {
			pose.translate(0.2F, 0.1F, 0.0F);
			pose.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}

	}
}
