package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.living.quail.Quail;

public class QuailModel extends DefaultedEntityGeoModel<Quail> {
    public QuailModel() {
        super(new ResourceLocation(DeepAether.MODID, "quail"), true);
    }
}