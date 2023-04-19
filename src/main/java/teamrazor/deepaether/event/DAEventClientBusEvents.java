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
import teamrazor.deepaether.client.renderer.DABoatRenderer;
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
    public static  void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(DAParticles.POISON_BUBBLES.get(),
                PoisonBubbles.Provider::new);
    }

    public static void registerCuriosRenderers() {
        CuriosRendererRegistry.register(DAItems.SKYJADE_GLOVES.get(), GlovesRenderer::new);
        CuriosRendererRegistry.register(DAItems.CLOUDIUM_GLOVES.get(), GlovesRenderer::new);

    }



}



