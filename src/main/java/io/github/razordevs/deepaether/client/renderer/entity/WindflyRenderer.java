package io.github.razordevs.deepaether.deepaether.client.renderer.entity;

import io.github.razordevs.deepaether.deepaether.entity.living.Windfly;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.DeepAether;
import io.github.razordevs.deepaether.deepaether.client.model.WindflyModel;
import io.github.razordevs.deepaether.deepaether.client.renderer.DAModelLayers;

public class WindflyRenderer extends MobRenderer<Windfly, WindflyModel> {

    public WindflyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WindflyModel(renderManager.bakeLayer(DAModelLayers.WINDFLY)), 0.5f);
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(@NotNull Windfly instance) {
        return new ResourceLocation(DeepAether.MODID, "textures/entity/windfly/windfly.png");
    }
}