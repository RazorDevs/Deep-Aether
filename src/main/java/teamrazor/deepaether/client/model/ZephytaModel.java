package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.ZephytaGolem;
    public class ZephytaModel extends DefaultedEntityGeoModel<ZephytaGolem> {
        public ZephytaModel() {
            super(new ResourceLocation(DeepAetherMod.MODID, "zephyta_golem"), true);
        }
    }
