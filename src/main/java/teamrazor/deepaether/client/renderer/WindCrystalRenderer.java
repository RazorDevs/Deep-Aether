package teamrazor.deepaether.client.renderer;

import com.aetherteam.aether.client.renderer.entity.IceCrystalRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.WindCrystal;

public class WindCrystalRenderer extends IceCrystalRenderer<WindCrystal> {
    private static final ResourceLocation WIND_CRYSTAL_TEXTURE = DeepAether.getResource( "textures/entity/projectile/wind_ball.png");
    public WindCrystalRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(WindCrystal crystal) {
        return WIND_CRYSTAL_TEXTURE;
    }

    @Override
    public void render(WindCrystal crystal, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(crystal.isFriendly())
            poseStack.scale(0.4F, 0.4F, 0.4F);
        super.render(crystal, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}