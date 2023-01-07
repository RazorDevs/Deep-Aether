package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.QuailEntity;

public class QuailModel extends DefaultedEntityGeoModel<QuailEntity> {
    public QuailModel() {
        super(new ResourceLocation(DeepAetherMod.MODID, "quail"), true);
    }
}