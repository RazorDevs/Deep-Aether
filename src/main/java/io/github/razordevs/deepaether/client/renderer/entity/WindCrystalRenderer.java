package io.github.razordevs.deepaether.deepaether.client.renderer.entity;

import com.aetherteam.aether.client.renderer.entity.IceCrystalRenderer;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.DeepAether;

public class WindCrystalRenderer<T extends AbstractCrystal>  extends IceCrystalRenderer<T> {
    private static final ResourceLocation WIND_CRYSTAL_TEXTURE = new ResourceLocation(DeepAether.MODID, "textures/entity/projectile/wind_ball.png");
    public WindCrystalRenderer(EntityRendererProvider.Context context) {
        super(context);
    }


    @Override
    public ResourceLocation getTextureLocation(T crystal) {
        return WIND_CRYSTAL_TEXTURE;
    }
}
