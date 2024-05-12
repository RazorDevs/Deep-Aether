package teamrazor.deepaether.event;

import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import com.aetherteam.aether.client.renderer.accessory.PendantRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.CherryParticle;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.AerwhaleModelOverrideOverrideLCCompat;
import teamrazor.deepaether.init.*;
import teamrazor.deepaether.item.mods.lost_content.AddonItemModelPredicates;
import teamrazor.deepaether.particle.custom.MysticalParticle;
import teamrazor.deepaether.particle.custom.PoisonBubbles;
import teamrazor.deepaether.screen.CombinerScreen;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = DeepAether.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAClientModBusEvents {

    /**
     * See {@link com.legacy.lost_aether.client.LCEntityRendering}
     */
    //TODO: UPDATE WHEN LOST AETHER CONTENT HAS PORTED TO 1.20.4
    /*
    @SubscribeEvent(priority = EventPriority.HIGHEST) //We want to ensure our event is loaded before LC's event.
    public static void initPostLayers(final EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        if(ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT))
            event.registerLayerDefinition(AetherModelLayers.AERWHALE, AerwhaleModelOverrideOverrideLCCompat::createOverrideLayerButWithChest);
    }
     */
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        registerCuriosRenderers();
        ItemBlockRenderTypes.setRenderLayer(DAFluids.POISON_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DAFluids.POISON_FLOWING.get(), RenderType.translucent());

        MenuScreens.register(DAMenuTypes.COMBINER_MENU.get(), CombinerScreen::new);


        event.enqueueWork(() -> {
            Sheets.addWoodType(DAWoodTypes.ROSEROOT);
            Sheets.addWoodType(DAWoodTypes.CRUDEROOT);
            Sheets.addWoodType(DAWoodTypes.YAGROOT);
            Sheets.addWoodType(DAWoodTypes.CONBERRY);
            Sheets.addWoodType(DAWoodTypes.SUNROOT);

            if (ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT)) {
                AddonItemModelPredicates.init();
            }
        });
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(DAParticles.POISON_BUBBLES.get(),
                PoisonBubbles.Provider::new);

        Minecraft.getInstance().particleEngine.register(DAParticles.MYTHICAL_PARTICLE.get(),
                MysticalParticle.Provider::new);

        Minecraft.getInstance().particleEngine.register(DAParticles.ROSEROOT_LEAVES.get(), (spriteSet)
                -> (particleType, level, v, v1, v2, v3, v4, v5)
                -> new CherryParticle(level, v, v1, v2, spriteSet));

        Minecraft.getInstance().particleEngine.register(DAParticles.FLOWERING_ROSEROOT_LEAVES.get(), (spriteSet)
                -> (particleType, level, v, v1, v2, v3, v4, v5)
                -> new CherryParticle(level, v, v1, v2, spriteSet));
    }

    public static void registerCuriosRenderers() {
        CuriosRendererRegistry.register(DAItems.SKYJADE_GLOVES.get(), GlovesRenderer::new);
        CuriosRendererRegistry.register(DAItems.STRATUS_GLOVES.get(), GlovesRenderer::new);
        CuriosRendererRegistry.register(DAItems.MEDAL_OF_HONOR.get(), PendantRenderer::new);
    }
}