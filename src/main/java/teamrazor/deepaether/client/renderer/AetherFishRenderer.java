package teamrazor.deepaether.client.renderer;

import teamrazor.deepaether.entity.AetherFishEntity;
import teamrazor.deepaether.client.model.Modelcustom_model;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class AetherFishRenderer extends MobRenderer<AetherFishEntity, Modelcustom_model<AetherFishEntity>> {
	public AetherFishRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcustom_model(context.bakeLayer(Modelcustom_model.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(AetherFishEntity entity) {
		return new ResourceLocation("deep_aether:textures/aertherfish.png");
	}
}
