package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.quail.Quail;

public class QuailModel extends DefaultedEntityGeoModel<Quail> {
    public QuailModel() {
        super(new ResourceLocation(DeepAetherMod.MODID, "quail"), true);
    }
}