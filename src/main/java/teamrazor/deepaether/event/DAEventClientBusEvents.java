package teamrazor.deepaether.event;

import com.gildedgames.aether.client.renderer.accessory.GlovesRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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
import teamrazor.deepaether.init.*;
import teamrazor.deepaether.particle.custom.PoisonBubbles;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAEventClientBusEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        registerCuriosRenderers();
        ItemBlockRenderTypes.setRenderLayer(DAFluids.POISON_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DAFluids.POISON_FLOWING.get(), RenderType.translucent());

        event.enqueueWork(() -> {
            Sheets.addWoodType(DAWoodTypes.ROSEROOT);
            Sheets.addWoodType(DAWoodTypes.CRUDEROOT);
            Sheets.addWoodType(DAWoodTypes.YAGROOT);
            Sheets.addWoodType(DAWoodTypes.AMBERROOT);
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
        event.registerEntityRenderer(DAEntities.AMBERROOT_BOAT.get(), (context) -> new CruderootBoatRenderer(context, false));
        event.registerEntityRenderer(DAEntities.AMBERROOT_CHEST_BOAT.get(), (context) -> new CruderootBoatRenderer(context, true));
    }

    @SubscribeEvent
    public static  void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(DAParticles.POISON_BUBBLES.get(),
                PoisonBubbles.Provider::new);
    }

    public static void registerCuriosRenderers() {
        CuriosRendererRegistry.register(DAItems.SKYJADE_GLOVES.get(), GlovesRenderer::new);
        CuriosRendererRegistry.register(DAItems.CLOUDIUM_GLOVES.get(), GlovesRenderer::new);

    }



}



