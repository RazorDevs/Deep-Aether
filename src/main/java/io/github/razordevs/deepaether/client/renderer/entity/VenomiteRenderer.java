package io.github.razordevs.deepaether.deepaether.client.renderer.entity;

import io.github.razordevs.deepaether.deepaether.entity.living.Venomite;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.DeepAether;
import io.github.razordevs.deepaether.deepaether.client.model.VenomiteModel;
import io.github.razordevs.deepaether.deepaether.client.renderer.DAModelLayers;

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