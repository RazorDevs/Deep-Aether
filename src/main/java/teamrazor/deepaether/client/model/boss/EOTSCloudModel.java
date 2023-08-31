package teamrazor.deepaether.client.model.boss;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.boss.EOTSCloud;

public class EOTSCloudModel extends DefaultedEntityGeoModel<EOTSCloud> {
    public EOTSCloudModel() {
        super(new ResourceLocation(DeepAetherMod.MODID, "eots_cloud"), false);
    }
}
