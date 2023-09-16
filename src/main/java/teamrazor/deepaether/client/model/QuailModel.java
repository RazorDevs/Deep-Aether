package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.quail.Quail;

public class QuailModel extends AnimatedGeoModel<Quail> {
    private static final ResourceLocation modelResource = new ResourceLocation(DeepAetherMod.MODID, "geo/quail.geo.json");
    private static final ResourceLocation textureResource = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_copper.png");
    private static final ResourceLocation animationResource = new ResourceLocation(DeepAetherMod.MODID, "animations/entity/quail.animation.json");

    @Override
    public ResourceLocation getModelResource(Quail quail) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(Quail quail) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(Quail quail) {
        return animationResource;
    }
}