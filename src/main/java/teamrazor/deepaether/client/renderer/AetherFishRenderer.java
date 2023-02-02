package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.SalmonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.AerglowFishModel;
import teamrazor.deepaether.entity.AetherFishEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import teamrazor.deepaether.init.DAModels;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class AetherFishRenderer extends MobRenderer<AetherFishEntity, AerglowFishModel<AetherFishEntity>> {
	private static final ResourceLocation AERGLOW_FISH_LOCATION = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/aerglow_fish.png");

	public AetherFishRenderer(EntityRendererProvider.Context p_174364_) {
		super(p_174364_, new AerglowFishModel<>(p_174364_.bakeLayer(DeepAetherModelLayers.AERGLOW_FISH)), 0.4F);
	}

	public ResourceLocation getTextureLocation(AetherFishEntity p_115826_) {
		return AERGLOW_FISH_LOCATION;
	}

	protected void setupRotations(AetherFishEntity p_115828_, PoseStack p_115829_, float p_115830_, float p_115831_, float p_115832_) {
		super.setupRotations(p_115828_, p_115829_, p_115830_, p_115831_, p_115832_);
		float f = 1.0F;
		float f1 = 1.0F;
		if (!p_115828_.isInWater()) {
			f = 1.3F;
			f1 = 1.7F;
		}

		float f2 = f * 4.3F * Mth.sin(f1 * 0.6F * p_115830_);
		p_115829_.mulPose(Axis.YP.rotationDegrees(f2));
		p_115829_.translate(0.0F, 0.0F, -0.4F);
		if (!p_115828_.isInWater()) {
			p_115829_.translate(0.2F, 0.1F, 0.0F);
			p_115829_.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}

	}
}
