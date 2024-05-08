package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.Venomite;

public class VenomiteModel extends DefaultedEntityGeoModel<Venomite> {
    public VenomiteModel() {
        super(new ResourceLocation(DeepAether.MODID, "venomite"), false);
    }
}
