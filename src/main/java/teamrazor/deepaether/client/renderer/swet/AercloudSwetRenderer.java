package teamrazor.deepaether.client.renderer.swet;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.entity.SwetRenderer;
import com.aetherteam.aether.client.renderer.entity.layers.SwetOuterLayer;
import com.aetherteam.aether.entity.monster.Swet;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import teamrazor.deepaether.DeepAetherMod;

import javax.annotation.Nonnull;

public class AercloudSwetRenderer extends SwetRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/swet/aercloud_swet.png");

public AercloudSwetRenderer(EntityRendererProvider.Context context) {
        super(context);
        }


    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull Swet swet) {
        return TEXTURE;
    }
}
