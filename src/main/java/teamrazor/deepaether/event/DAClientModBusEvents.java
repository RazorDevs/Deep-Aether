package teamrazor.deepaether.event;

import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import com.aetherteam.aether.client.renderer.accessory.PendantRenderer;
import com.aetherteam.aether.inventory.menu.LoreBookMenu;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.CherryParticle;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.AerwhaleModelOverrideOverrideLCCompat;
import teamrazor.deepaether.client.renderer.WindShieldRenderer;
import teamrazor.deepaether.client.renderer.FloatyScarfRenderer;
import teamrazor.deepaether.init.*;
import teamrazor.deepaether.item.moa_food.FodderItem;
import teamrazor.deepaether.item.mods.lost_content.AddonItemModelPredicates;
import teamrazor.deepaether.networking.DeepAetherPlayer;
import teamrazor.deepaether.particle.custom.EOTSPreFightParticle;
import teamrazor.deepaether.particle.custom.LuckParticle;
import teamrazor.deepaether.particle.custom.MysticalParticle;
import teamrazor.deepaether.particle.custom.PoisonBubbles;
import teamrazor.deepaether.screen.CombinerScreen;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = DeepAether.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAClientModBusEvents {

    /**
     * See {@link com.legacy.lost_aether.client.LCEntityRendering}
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST) //We want to ensure our event is loaded before LC's event.
    public static void initPostLayers(final EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        if(ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT))
            event.registerLayerDefinition(AetherModelLayers.AERWHALE, AerwhaleModelOverrideOverrideLCCompat::createOverrideLayerButWithChest);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        registerCuriosRenderers();
        ItemBlockRenderTypes.setRenderLayer(DAFluids.POISON_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DAFluids.POISON_FLOWING.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(DABlocks.SKYJADE_CHAIN.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DABlocks.SKYJADE_LANTERN.get(), RenderType.translucent());

        LoreBookMenu.addLoreEntryOverride(stack -> stack.is
                (DAItems.STORM_SWORD.get()) && stack.getHoverName().getString().equalsIgnoreCase("storm ruler"), "lore.item.deep_aether.storm_ruler");

        event.enqueueWork(() -> {
            Sheets.addWoodType(DAWoodTypes.ROSEROOT);
            Sheets.addWoodType(DAWoodTypes.CRUDEROOT);
            Sheets.addWoodType(DAWoodTypes.YAGROOT);
            Sheets.addWoodType(DAWoodTypes.CONBERRY);
            Sheets.addWoodType(DAWoodTypes.SUNROOT);

            MenuScreens.register(DAMenuTypes.COMBINER_MENU.get(), CombinerScreen::new);

            registerItemModelPredicates();
            if (ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT)) {
                AddonItemModelPredicates.init();
            }
        });
    }

    static float returnState = 1.0F;

    private static void registerItemModelPredicates() {
        ItemProperties.register(DAItems.MOA_FODDER.get(), new ResourceLocation(DeepAether.MODID, "color"), (stack, level, entity, state) -> {
            MobEffectInstance instance = ((FodderItem) stack.getItem()).getMobEffect(stack);
                    if (instance != null) {
                        if(instance.getEffect().equals(DAMobEffects.MOA_BONUS_JUMPS.get())) {
                            return 0.1F;
                        }
                        else if(instance.getEffect().equals(MobEffects.FIRE_RESISTANCE)) {
                            return 0.2F;
                        }
                        else if(instance.getEffect().equals(MobEffects.JUMP)) {
                            return 0.3F;
                        }
                    }
                    return  0.0F;
                }

        );


        ItemProperties.register(DAItems.STORM_BOW.get(), new ResourceLocation("pull"), (p_344163_, p_344164_, p_344165_, p_344166_) -> {
            if (p_344165_ == null) {
                return 0.0F;
            } else {
                return p_344165_.getUseItem() != p_344163_ ? 0.0F : (float)(p_344163_.getUseDuration() - p_344165_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(DAItems.STORM_BOW.get(), new ResourceLocation("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F);

        ItemProperties.register(DAItems.BLADE_OF_LUCK.get(),
                new ResourceLocation("sword_state"), (stack, world, entity, value) -> {
                    if(entity instanceof Player player) {
                        DeepAetherPlayer.get(player).ifPresent((daPlayer) -> {
                            if (daPlayer.getChangeBladeOfLuckState()) {
                                if (player.swinging) {
                                    if (daPlayer.getOldBladeOfLuckDamage() <= 3)
                                        returnState = 0.2F;
                                    if (daPlayer.getOldBladeOfLuckDamage() <= 8)
                                        returnState = 0.4F;
                                    else if (daPlayer.getOldBladeOfLuckDamage() <= 12)
                                        returnState = 0.6F;
                                    else if (daPlayer.getOldBladeOfLuckDamage() <= 16)
                                        returnState = 0.8F;
                                    else returnState = 1.0F;
                                } else daPlayer.setChangeBladeOfLuckState(false);
                            } else {
                                returnState = 0.5F;
                            }
                        });
                        return returnState;
                    }
                    return 0.5F;
                });
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(DAParticles.POISON_BUBBLES.get(),
                PoisonBubbles.Provider::new);

        event.registerSpriteSet(DAParticles.MYTHICAL_PARTICLE.get(),
                MysticalParticle.Provider::new);

        event.registerSpriteSet(DAParticles.ROSEROOT_LEAVES.get(), (spriteSet)
                -> (particleType, level, v, v1, v2, v3, v4, v5)
                -> new CherryParticle(level, v, v1, v2, spriteSet));

        event.registerSpriteSet(DAParticles.FLOWERING_ROSEROOT_LEAVES.get(), (spriteSet)
                -> (particleType, level, v, v1, v2, v3, v4, v5)
                -> new CherryParticle(level, v, v1, v2, spriteSet));


        //event.registerSpriteSet(DAParticles.EOTS_EXPLOSION.get(), EOTSExplosionParticle.Provider::new);
        event.registerSpriteSet(DAParticles.EOTS_PRE_FIGHT.get(), EOTSPreFightParticle.Provider::new);

        event.registerSpriteSet(DAParticles.CLOVER_VERY_LUCKY.get(), LuckParticle.Provider::new);
        event.registerSpriteSet(DAParticles.CLOVER_LUCKY.get(), LuckParticle.Provider::new);
        event.registerSpriteSet(DAParticles.CLOVER.get(), LuckParticle.Provider::new);
        event.registerSpriteSet(DAParticles.CLOVER_UNLUCKY.get(), LuckParticle.Provider::new);
    }

    public static void registerCuriosRenderers() {
        CuriosRendererRegistry.register(DAItems.SKYJADE_GLOVES.get(), GlovesRenderer::new);
        CuriosRendererRegistry.register(DAItems.STORMFORGED_GLOVES.get(), GlovesRenderer::new);
        CuriosRendererRegistry.register(DAItems.STRATUS_GLOVES.get(), GlovesRenderer::new);
        CuriosRendererRegistry.register(DAItems.MEDAL_OF_HONOR.get(), PendantRenderer::new);
        CuriosRendererRegistry.register(DAItems.WIND_SHIELD.get(), WindShieldRenderer::new);
        CuriosRendererRegistry.register(DAItems.FLOATY_SCARF.get(), FloatyScarfRenderer::new);
    }
}