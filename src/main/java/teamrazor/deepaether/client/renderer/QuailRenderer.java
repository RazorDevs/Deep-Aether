package teamrazor.deepaether.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.client.model.QuailModel;
import teamrazor.deepaether.entity.QuailEntity;

public class QuailRenderer extends MobRenderer<QuailEntity, QuailModel<QuailEntity>> {
        public QuailRenderer(EntityRendererProvider.Context context) {
            super(context, new QuailModel(context.bakeLayer(QuailModel.LAYER_LOCATION)), 0.3f);
        }

        @Override
        public ResourceLocation getTextureLocation(QuailEntity entity) {
            return new ResourceLocation("deep_aether:textures/quail.png");
        }
    }