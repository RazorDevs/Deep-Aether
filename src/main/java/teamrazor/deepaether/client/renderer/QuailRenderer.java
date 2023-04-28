package teamrazor.deepaether.client.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.QuailModel;
import teamrazor.deepaether.entity.quail.QuailEntity;
import teamrazor.deepaether.entity.quail.QuailVariants;

import java.util.Map;

public class QuailRenderer extends GeoEntityRenderer<QuailEntity> {

    public QuailRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new QuailModel());
        this.shadowRadius = 0.3f;
    }


    public static final Map<QuailVariants, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(QuailVariants.class), (p_114874_) -> {
                p_114874_.put(QuailVariants.OLD_GREEN,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_old_green.png"));
                p_114874_.put(QuailVariants.PINK,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_pink.png"));
                p_114874_.put(QuailVariants.PURPLE,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_purple.png"));
                p_114874_.put(QuailVariants.TROPICAL_BLUE,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_tropical_blue.png"));
                p_114874_.put(QuailVariants.FADED_YELLOW,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_faded_yellow.png"));
                p_114874_.put(QuailVariants.LIGHT_BLUE,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_light_blue.png"));
                p_114874_.put(QuailVariants.COPPER,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_copper.png"));

            });
    @Override
    public ResourceLocation getTextureLocation(QuailEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }
    @Override
    public void preRender(PoseStack poseStack, QuailEntity animatable,
                          BakedGeoModel model, MultiBufferSource bufferSource,
                          VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay,
                          float red, float green, float blue, float alpha) {

        if (animatable.isBaby()){
            poseStack.scale(0.3f, 0.3f, 0.3f);
        }else{
            poseStack.scale(0.5f, 0.5f,0.5f);
        }
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}