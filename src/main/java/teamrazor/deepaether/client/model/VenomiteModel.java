package teamrazor.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.Venomite;

public class VenomiteModel extends AnimatedGeoModel<Venomite> {
    private static final ResourceLocation modelResource = new ResourceLocation(DeepAetherMod.MODID, "geo/entity/venomite.geo.json");
    private static final ResourceLocation textureResource = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/venomite/venomite.png");
    private static final ResourceLocation animationResource = new ResourceLocation(DeepAetherMod.MODID, "animations/entity/venomite.animation.json");

    @Override
    public ResourceLocation getModelResource(Venomite venomite) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(Venomite venomite) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(Venomite venomite) {
        return animationResource;
    }
}
