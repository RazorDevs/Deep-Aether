
package teamrazor.deepaether.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.client.model.Modelcustom_model;
import teamrazor.deepaether.entity.AetherFishEntity;

public class AetherFishRenderer extends MobRenderer<AetherFishEntity, Modelcustom_model<AetherFishEntity>> {

	public AetherFishRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcustom_model(context.bakeLayer(Modelcustom_model.LAYER_LOCATION)), 0.5f);

	}

	@Override
	public ResourceLocation getTextureLocation(AetherFishEntity entity) {
		return new ResourceLocation("deep_aether:textures/aertherfish.png");
	}

}
