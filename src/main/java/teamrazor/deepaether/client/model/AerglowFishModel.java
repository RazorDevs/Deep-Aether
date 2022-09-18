package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.AetherFishEntity;
import teamrazor.deepaether.entity.QuailEntity;

public class AerglowFishModel extends AnimatedGeoModel<AetherFishEntity> {
	@Override
	public ResourceLocation getModelResource(AetherFishEntity object) {
		return new ResourceLocation(DeepAetherMod.MODID, "geo/aerglow_fish.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AetherFishEntity object) {
		return new ResourceLocation(DeepAetherMod.MODID, "textures/entities/aetherfish.png");
	}

	@Override
	public ResourceLocation getAnimationResource(AetherFishEntity animatable) {
		return new ResourceLocation(DeepAetherMod.MODID, "animations/aerglow_fish.anim.json");
	}
}