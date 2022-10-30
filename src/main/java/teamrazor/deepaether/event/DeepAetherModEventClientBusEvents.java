package teamrazor.deepaether.event;


import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.Sheets;

import net.minecraft.client.renderer.blockentity.SignRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.blockentity.DeepAetherModWoodTypes;
import teamrazor.deepaether.client.renderer.DeepAetherModBoatRenderer;
import teamrazor.deepaether.entity.DeepAetherModBoat;
import teamrazor.deepaether.init.DeepAetherModBlockEntityTypes;
import teamrazor.deepaether.init.DeepAetherModEntities;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DeepAetherModEventClientBusEvents {
    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {

        event.enqueueWork(() -> {
            Sheets.addWoodType(DeepAetherModWoodTypes.YAGROOT);
            Sheets.addWoodType(DeepAetherModWoodTypes.ROSE);
            Sheets.addWoodType(DeepAetherModWoodTypes.CRUDEROOT);
        });
    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(DeepAetherModBlockEntityTypes.SIGN.get(), SignRenderer::new);
        event.registerEntityRenderer(DeepAetherModEntities.BOAT.get(), (context) -> new DeepAetherModBoatRenderer(context, false));
        event.registerEntityRenderer(DeepAetherModEntities.CHEST_BOAT.get(), (context) -> new DeepAetherModBoatRenderer(context, true));
    }
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (DeepAetherModBoat.Type type : DeepAetherModBoat.Type.values()) {
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, type.getModelLocation()), "main"), () -> BoatModel.createBodyModel(false));
            event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, type.getChestModelLocation()), "main"), () -> BoatModel.createBodyModel(true));
        }
    }

}


