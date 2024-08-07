package teamrazor.deepaether.client.renderer;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.QuailModel;
import teamrazor.deepaether.entity.living.quail.Quail;
import teamrazor.deepaether.entity.living.quail.QuailVariants;

import java.util.Map;

public class QuailRenderer extends MobRenderer<Quail, QuailModel> {

    public QuailRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new QuailModel(renderManager.bakeLayer(DAModelLayers.QUAIL)), 0.3f);
    }

    public static final Map<QuailVariants, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(QuailVariants.class), (map) -> {
                map.put(QuailVariants.OLD_GREEN,
                        new ResourceLocation(DeepAether.MODID, "textures/entity/quail/quail_old_green.png"));
                map.put(QuailVariants.PINK,
                        new ResourceLocation(DeepAether.MODID, "textures/entity/quail/quail_pink.png"));
                map.put(QuailVariants.PURPLE,
                        new ResourceLocation(DeepAether.MODID, "textures/entity/quail/quail_purple.png"));
                map.put(QuailVariants.TROPICAL_BLUE,
                        new ResourceLocation(DeepAether.MODID, "textures/entity/quail/quail_tropical_blue.png"));
                map.put(QuailVariants.FADED_YELLOW,
                        new ResourceLocation(DeepAether.MODID, "textures/entity/quail/quail_faded_yellow.png"));
                map.put(QuailVariants.LIGHT_BLUE,
                        new ResourceLocation(DeepAether.MODID, "textures/entity/quail/quail_light_blue.png"));
                map.put(QuailVariants.COPPER,
                        new ResourceLocation(DeepAether.MODID, "textures/entity/quail/quail_copper.png"));

            });

    @Override
    protected float getBob(Quail pLivingBase, float pPartialTicks) {
        float f = Mth.lerp(pPartialTicks, pLivingBase.oFlap, pLivingBase.flap);
        float f1 = Mth.lerp(pPartialTicks, pLivingBase.oFlapSpeed, pLivingBase.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
    @Override
    @NotNull
    public ResourceLocation getTextureLocation(Quail instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }
}