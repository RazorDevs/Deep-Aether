package teamrazor.deepaether.client.renderer.entity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import teamrazor.deepaether.entity.StormArrow;

@OnlyIn(Dist.CLIENT)
public class StormArrowRenderer extends ArrowRenderer<StormArrow> {
    public static final ResourceLocation SPECTRAL_ARROW_LOCATION = new ResourceLocation("textures/entity/projectiles/arrow.png");

    public StormArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(StormArrow pEntity) {
        return SPECTRAL_ARROW_LOCATION;
    }
}
