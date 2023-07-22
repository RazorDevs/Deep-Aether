package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.Steer;

public class SteerModel extends DefaultedEntityGeoModel<Steer> {
    public SteerModel() {
        super(new ResourceLocation(DeepAetherMod.MODID, "steer"), true);
    }
}
