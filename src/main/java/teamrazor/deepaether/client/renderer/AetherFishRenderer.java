package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.AerglowFishModel;
import teamrazor.deepaether.entity.AetherFishEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import javax.annotation.Nullable;

public class AetherFishRenderer extends GeoEntityRenderer<AetherFishEntity> {

		public AetherFishRenderer(EntityRendererProvider.Context renderManager) {
			super(renderManager, new AerglowFishModel());
			this.shadowRadius = 0.3f;
		}

		@Override
		public ResourceLocation getTextureLocation(AetherFishEntity instance) {
			return new ResourceLocation(DeepAetherMod.MODID, "textures/entity/aerglow_fish.png");
		}
}
