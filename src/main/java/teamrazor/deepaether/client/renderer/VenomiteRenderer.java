package teamrazor.deepaether.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.VenomiteModel;
import teamrazor.deepaether.entity.Venomite;

public class VenomiteRenderer extends MobRenderer<Venomite, VenomiteModel> {
    public VenomiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VenomiteModel(renderManager.bakeLayer(DAModelLayers.VENOMITE)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Venomite instance) {
        if (instance.isAngry()) return new ResourceLocation(DeepAether.MODID, "textures/entity/venomite/venomite_angry.png");
        return new ResourceLocation(DeepAether.MODID, "textures/entity/venomite/venomite.png");
    }
}