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
import teamrazor.deepaether.init.DeepAetherModEntities;
import teamrazor.deepaether.init.DeepAetherModParticles;
import teamrazor.deepaether.init.DeepAetherModWoodTypes;
import teamrazor.deepaether.particle.custom.PoisonBubbles;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DeepAetherModEventClientBusEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            Sheets.addWoodType(DeepAetherModWoodTypes.ROSEROOT);
            Sheets.addWoodType(DeepAetherModWoodTypes.CRUDEROOT);
            Sheets.addWoodType(DeepAetherModWoodTypes.YAGROOT);
        });
    }


    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(DeepAetherModEntities.ROSEROOT_BOAT.get(), (context) -> new RoserootBoatRenderer(context, false));
        event.registerEntityRenderer(DeepAetherModEntities.ROSEROOT_CHEST_BOAT.get(), (context) -> new RoserootBoatRenderer(context, true));
        event.registerEntityRenderer(DeepAetherModEntities.YAGROOT_BOAT.get(), (context) -> new YagrootBoatRenderer(context, false));
        event.registerEntityRenderer(DeepAetherModEntities.YAGROOT_CHEST_BOAT.get(), (context) -> new YagrootBoatRenderer(context, true));
        event.registerEntityRenderer(DeepAetherModEntities.CRUDEROOT_BOAT.get(), (context) -> new CruderootBoatRenderer(context, false));
        event.registerEntityRenderer(DeepAetherModEntities.CRUDEROOT_CHEST_BOAT.get(), (context) -> new CruderootBoatRenderer(context, true));
    }

    @SubscribeEvent
    public static  void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(DeepAetherModParticles.POISON_BUBBLES.get(),
                PoisonBubbles.Provider::new);
    }
}



