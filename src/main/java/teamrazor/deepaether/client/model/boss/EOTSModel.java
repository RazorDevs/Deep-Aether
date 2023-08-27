package teamrazor.deepaether.client.model.boss;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.boss.EOTSEntity;

public class EOTSModel extends DefaultedEntityGeoModel<EOTSEntity> {
    public EOTSModel() {
       super(new ResourceLocation(DeepAetherMod.MODID, "eots"), false);
    }
}
