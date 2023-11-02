package teamrazor.deepaether.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.VenomiteModel;
import teamrazor.deepaether.entity.Venomite;

public class VenomiteRenderer extends GeoEntityRenderer<Venomite> {

    public VenomiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VenomiteModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(Venomite instance) {
        if (instance.isAngry()) return new ResourceLocation(DeepAetherMod.MODID, "textures/entity/venomite/venomite_angry.png");
        return new ResourceLocation(DeepAetherMod.MODID, "textures/entity/venomite/venomite.png");
    }
}