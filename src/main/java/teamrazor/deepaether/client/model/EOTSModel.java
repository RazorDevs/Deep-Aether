package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAether;

import teamrazor.deepaether.entity.living.boss.eots.EOTSController;

public class EOTSModel extends DefaultedEntityGeoModel<EOTSController> {
    public EOTSModel() {
        super(new ResourceLocation(DeepAether.MODID, "eots"), false);
    }
}
