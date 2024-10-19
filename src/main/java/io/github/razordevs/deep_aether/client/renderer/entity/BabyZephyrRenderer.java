package io.github.razordevs.deep_aether.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.razordevs.deep_aether.client.model.BabyZephyrModel;
import io.github.razordevs.deep_aether.client.renderer.DAModelLayers;
import io.github.razordevs.deep_aether.entity.living.BabyZephyr;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import teamrazor.deepaether.DeepAether;

public class BabyZephyrRenderer extends MobRenderer<BabyZephyr, BabyZephyrModel> {
    public BabyZephyrRenderer(EntityRendererProvider.Context context) {
        super(context, new BabyZephyrModel(context.bakeLayer(DAModelLayers.BABY_ZEPHYR)), 0.5F);
    }

    @Override
    protected void scale(BabyZephyr zephyr, PoseStack poseStack, float partialTicks) {
        float f = Math.min(Mth.lerp(partialTicks, zephyr.getCloudScale(), zephyr.getCloudScale() + zephyr.getCloudScaleAdd()), 38.0F);
        float f1 = f / 38.0F;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }
        f1 = 1.0F / ((float) Math.pow(f1, 5) * 2.0F + 1.0F);
        float f2 = (8.0F + f1) / 2.0F;
        float f3 = (8.0F + 1.0F / f1) / 2.0F;

        poseStack.scale(f3, f2, f3);
        poseStack.translate(0.0, 0.5, 0.0);
    }

    @Override
    public ResourceLocation getTextureLocation(BabyZephyr pEntity) {
        return ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/entity/baby_zephyr.png");
    }
}
