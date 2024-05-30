package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.living.Windfly;

public class WindflyModel extends DefaultedEntityGeoModel<Windfly> {
    public WindflyModel() {
        super(new ResourceLocation(DeepAether.MODID, "windfly"), false);
    }
}
