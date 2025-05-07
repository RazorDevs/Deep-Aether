package teamrazor.deepaether.client.renderer;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.entity.StormArrow;

public class StormArrowRenderer extends ArrowRenderer<StormArrow> {
    public static final ResourceLocation SPECTRAL_ARROW_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/projectiles/arrow.png");

    public StormArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    public ResourceLocation getTextureLocation(StormArrow pEntity) {
        return SPECTRAL_ARROW_LOCATION;
    }
}
