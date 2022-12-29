package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.AetherFishEntity;


public class AerglowFishModel extends DefaultedEntityGeoModel<AetherFishEntity> {
	public AerglowFishModel() {
		super(new ResourceLocation(DeepAetherMod.MODID, "aerglow_fish"));
	}
}