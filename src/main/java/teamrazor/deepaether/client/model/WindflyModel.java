package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.Windfly;

public class WindflyModel extends DefaultedEntityGeoModel<Windfly> {
    public WindflyModel() {
        super(new ResourceLocation(DeepAetherMod.MODID, "windfly"), false);
    }
}
