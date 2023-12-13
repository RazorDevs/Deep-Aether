package teamrazor.deepaether.event;

import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.CherryParticle;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DAFluids;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.init.DAParticles;
import teamrazor.deepaether.init.DAWoodTypes;
import teamrazor.deepaether.item.mods.lost_content.AddonItemModelPredicates;
import teamrazor.deepaether.particle.custom.MysticalParticle;
import teamrazor.deepaether.particle.custom.PoisonBubbles;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAClientModBusEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        registerCuriosRenderers();
        ItemBlockRenderTypes.setRenderLayer(DAFluids.POISON_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DAFluids.POISON_FLOWING.get(), RenderType.translucent());

        event.enqueueWork(() -> {
            Sheets.addWoodType(DAWoodTypes.ROSEROOT);
            Sheets.addWoodType(DAWoodTypes.CRUDEROOT);
            Sheets.addWoodType(DAWoodTypes.YAGROOT);
            Sheets.addWoodType(DAWoodTypes.CONBERRY);
            Sheets.addWoodType(DAWoodTypes.SUNROOT);

            if(ModList.get().isLoaded("lost_aether_content")) {
                AddonItemModelPredicates.init();
            }
        });
    }

    @SubscribeEvent
    public static  void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(DAParticles.POISON_BUBBLES.get(),
                PoisonBubbles.Provider::new);

        Minecraft.getInstance().particleEngine.register(DAParticles.MYTHICAL_PARTICLE.get(),
                MysticalParticle.Provider::new);

        Minecraft.getInstance().particleEngine.register(DAParticles.ROSEROOT_LEAVES.get(), (p_277215_)
                -> (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_)
                -> new CherryParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_));

        Minecraft.getInstance().particleEngine.register(DAParticles.FLOWERING_ROSEROOT_LEAVES.get(), (p_277215_)
                -> (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_)
                -> new CherryParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_));
    }

    public static void registerCuriosRenderers() {
        CuriosRendererRegistry.register(DAItems.SKYJADE_GLOVES.get(), GlovesRenderer::new);
        CuriosRendererRegistry.register(DAItems.STRATUS_GLOVES.get(), GlovesRenderer::new);
    }
}



