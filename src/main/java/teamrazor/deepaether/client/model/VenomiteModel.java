package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.Venomite;

public class VenomiteModel extends DefaultedEntityGeoModel<Venomite> {
    public VenomiteModel() {
        super(new ResourceLocation(DeepAetherMod.MODID, "venomite"), false);
    }
}
