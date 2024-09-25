package teamrazor.deepaether.client.renderer.entity;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.client.renderer.entity.CloudCrystalRenderer;
import com.aetherteam.aether.client.renderer.entity.IceCrystalRenderer;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.living.projectile.WindCrystal;

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
