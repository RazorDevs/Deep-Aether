package teamrazor.deepaether.init;

import com.aetherteam.aether.client.renderer.entity.WhirlwindRenderer;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.model.AerglowFishModel;
import teamrazor.deepaether.client.renderer.*;
import teamrazor.deepaether.client.renderer.boss.EOTSCloudRenderer;
import teamrazor.deepaether.client.renderer.boss.EOTSRenderer;
import teamrazor.deepaether.client.renderer.boss.WindChargeRenderer;
import teamrazor.deepaether.client.renderer.swet.AercloudSwetRenderer;
import teamrazor.deepaether.entity.DABoatEntity;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DAEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		//MOBS

		event.registerEntityRenderer(DAEntities.AERGLOW_FISH.get(), AerglowFishRenderer::new);
		event.registerEntityRenderer(DAEntities.QUAIL.get(), QuailRenderer::new);
		event.registerEntityRenderer(DAEntities.STEER.get(), SteerRenderer::new);

		//BOSSES

		event.registerEntityRenderer(DAEntities.EOTS.get(), EOTSRenderer::new);
		event.registerEntityRenderer(DAEntities.EOTS_CLOUD.get(), EOTSCloudRenderer::new);
		event.registerEntityRenderer(DAEntities.EOTS_TORNADO.get(), WhirlwindRenderer::new);

		//MISC

		event.registerBlockEntityRenderer(DABlockEntityTypes.SIGN.get(), SignRenderer::new);

		event.registerEntityRenderer(DAEntities.BOAT.get(), context -> new DABoatRenderer<>(context, false));
		event.registerEntityRenderer(DAEntities.CHEST_BOAT.get(), context -> new DABoatRenderer<>(context, true));

		//PROJECTILES

		event.registerEntityRenderer(DAEntities.QUAIL_EGG.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(DAEntities.AERCLOUD_SWET.get(), AercloudSwetRenderer::new);

		event.registerEntityRenderer(DAEntities.WIND_CHARGE.get(), WindChargeRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(DAModelLayers.AERGLOW_FISH, AerglowFishModel::createBodyLayer);
		for (DABoatEntity.Type type : DABoatEntity.Type.values()) {
			event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, type.getModelLocation()), "main"), BoatModel::createBodyModel);
			event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
		}

	}
}