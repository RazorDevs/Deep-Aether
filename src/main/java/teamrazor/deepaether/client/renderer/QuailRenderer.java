package teamrazor.deepaether.client.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.QuailModel;
import teamrazor.deepaether.entity.quail.Quail;
import teamrazor.deepaether.entity.quail.QuailVariants;

import java.util.Map;

public class QuailRenderer extends GeoEntityRenderer<Quail> {

    public QuailRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new QuailModel());
        this.shadowRadius = 0.3f;
    }

    public static final Map<QuailVariants, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(QuailVariants.class), (map) -> {
                map.put(QuailVariants.OLD_GREEN,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_old_green.png"));
                map.put(QuailVariants.PINK,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_pink.png"));
                map.put(QuailVariants.PURPLE,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_purple.png"));
                map.put(QuailVariants.TROPICAL_BLUE,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_tropical_blue.png"));
                map.put(QuailVariants.FADED_YELLOW,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_faded_yellow.png"));
                map.put(QuailVariants.LIGHT_BLUE,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_light_blue.png"));
                map.put(QuailVariants.COPPER,
                        new ResourceLocation(DeepAetherMod.MODID, "textures/entity/quail/quail_copper.png"));

            });
    @Override
    public ResourceLocation getTextureLocation(Quail instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public void render(Quail animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (animatable.isBaby()){
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }else{
            poseStack.scale(0.9f, 0.9f,0.9f);
        }
        if (animatable.isSitting()){
            poseStack.translate(0.0, -0.35, 0.0);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}