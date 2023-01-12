package teamrazor.deepaether.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.renderer.boat.CruderootBoatRenderer;
import teamrazor.deepaether.client.renderer.boat.RoserootBoatRenderer;
import teamrazor.deepaether.client.renderer.boat.YagrootBoatRenderer;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DAParticles;
import teamrazor.deepaether.init.DAWoodTypes;
import teamrazor.deepaether.particle.custom.PoisonBubbles;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DAEventClientBusEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            Sheets.addWoodType(DAWoodTypes.ROSEROOT);
            Sheets.addWoodType(DAWoodTypes.CRUDEROOT);
            Sheets.addWoodType(DAWoodTypes.YAGROOT);
        });
    }


    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(DAEntities.ROSEROOT_BOAT.get(), (context) -> new RoserootBoatRenderer(context, false));
        event.registerEntityRenderer(DAEntities.ROSEROOT_CHEST_BOAT.get(), (context) -> new RoserootBoatRenderer(context, true));
        event.registerEntityRenderer(DAEntities.YAGROOT_BOAT.get(), (context) -> new YagrootBoatRenderer(context, false));
        event.registerEntityRenderer(DAEntities.YAGROOT_CHEST_BOAT.get(), (context) -> new YagrootBoatRenderer(context, true));
        event.registerEntityRenderer(DAEntities.CRUDEROOT_BOAT.get(), (context) -> new CruderootBoatRenderer(context, false));
        event.registerEntityRenderer(DAEntities.CRUDEROOT_CHEST_BOAT.get(), (context) -> new CruderootBoatRenderer(context, true));
    }

    @SubscribeEvent
    public static  void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(DAParticles.POISON_BUBBLES.get(),
                PoisonBubbles.Provider::new);
    }
}



