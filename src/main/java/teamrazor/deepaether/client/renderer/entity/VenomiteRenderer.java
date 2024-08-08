package teamrazor.deepaether.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.VenomiteModel;
import teamrazor.deepaether.client.renderer.DAModelLayers;
import teamrazor.deepaether.entity.living.Venomite;

public class VenomiteRenderer extends MobRenderer<Venomite, VenomiteModel> {

    public VenomiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new VenomiteModel(renderManager.bakeLayer(DAModelLayers.VENOMITE)), 0.5f);
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(Venomite instance) {
        if (instance.isAngry()) return new ResourceLocation(DeepAether.MODID, "textures/entity/venomite/venomite_angry.png");
        return new ResourceLocation(DeepAether.MODID, "textures/entity/venomite/venomite.png");
    }
}