
package teamrazor.deepaether.client.renderer;

public class AetherFishRenderer extends MobRenderer<AetherFishEntity, Modelcustom_model<AetherFishEntity>> {

	public AetherFishRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcustom_model(context.bakeLayer(Modelcustom_model.LAYER_LOCATION)), 0.5f);

	}

	@Override
	public ResourceLocation getTextureLocation(AetherFishEntity entity) {
		return new ResourceLocation("deep_aether:textures/aertherfish.png");
	}

}
