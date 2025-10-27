package io.github.razordevs.deep_aether.event;

import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import com.aetherteam.aether.client.renderer.accessory.PendantRenderer;
import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.aether.inventory.menu.LoreBookMenu;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.DeepAetherConfig;
import io.github.razordevs.deep_aether.client.particle.*;
import io.github.razordevs.deep_aether.client.renderer.accessory.FloatyScarfRenderer;
import io.github.razordevs.deep_aether.client.renderer.accessory.SkyjadeGlovesRenderer;
import io.github.razordevs.deep_aether.client.renderer.accessory.WindShieldRenderer;
import io.github.razordevs.deep_aether.entity.living.GentleWind;
import io.github.razordevs.deep_aether.fluids.DAFluidTypes;
import io.github.razordevs.deep_aether.init.*;
import io.github.razordevs.deep_aether.item.component.DADataComponentTypes;
import io.github.razordevs.deep_aether.item.component.DungeonTracker;
import io.github.razordevs.deep_aether.item.component.MoaFodder;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.github.razordevs.deep_aether.networking.attachment.DAPlayerAttachment;
import io.github.razordevs.deep_aether.screen.CombinerScreen;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.CherryParticle;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import javax.annotation.Nullable;

@EventBusSubscriber(modid = DeepAether.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class DAClientModBusEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        registerAccessoriesRenderers();
        ItemBlockRenderTypes.setRenderLayer(DAFluids.POISON_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DAFluids.POISON_FLOWING.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(DABlocks.SKYJADE_CHAIN.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DABlocks.SKYJADE_LANTERN.get(), RenderType.translucent());

        LoreBookMenu.addLoreEntryOverride(registryAccess -> stack -> stack
                .is(DAItems.STORM_SWORD.get()) && stack.getHoverName().getString().equalsIgnoreCase("storm ruler"), "lore.item.deep_aether.storm_ruler");

        event.enqueueWork(() -> {
            Sheets.addWoodType(DAWoodTypes.ROSEROOT);
            Sheets.addWoodType(DAWoodTypes.CRUDEROOT);
            Sheets.addWoodType(DAWoodTypes.YAGROOT);
            Sheets.addWoodType(DAWoodTypes.CONBERRY);
            Sheets.addWoodType(DAWoodTypes.SUNROOT);

            registerItemModelPredicates();
            Moa.registerJumpOverlayTextureOverride(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "effect_extra_jumps"),
                    ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "hud/effect_feather"));
            Moa.registerJumpOverlayTextureOverride(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "test_effect"),
                            ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "hud/gravitite")
            );

            ItemProperties.register(DAItems.STORM_BOW.get(),
                    // The id of the property.
                    ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "enchanted"),
                    // A reference to a method that calculates the override value.
                    (stack, level, player, seed) -> stack.isEnchanted() ? 1 : 0
            );
        });
    }

    @SubscribeEvent
    public static void fluidInitializeClient(RegisterClientExtensionsEvent event){
        event.registerFluidType(new IClientFluidTypeExtensions() {
            public ResourceLocation getStillTexture() {
                return DAFluidTypes.POISON_STILL_RL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return DAFluidTypes.POISON_FLOWING_RL;
            }

            @Override
            public @NotNull ResourceLocation getOverlayTexture() {
                return DAFluidTypes.POISON_OVERLAY_RL;
            }

            @Override
            public int getTintColor() {
                return 0xffAB5AFD;
            }

            @Override
            public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level,
                                                    int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return new Vector3f(180f / 255f, 60f / 255f, 230f / 255f);
            }

            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick,
                                        float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart(0f);
                RenderSystem.setShaderFogEnd(7.5f); // distance when the fog starts
            }
        }, DAFluidTypes.POISON_FLUID_TYPE);
    }

    /**
     * Method responsible for the Sun Clock's rotation
     */
    public static void registerItemModelPredicates() {
        sunClock();
        compassRotation(DAItems.BRONZE_COMPASS.get());
        compassRotation(DAItems.SILVER_COMPASS.get());
        compassRotation(DAItems.GOLD_COMPASS.get());
        ItemProperties.register(DAItems.MOA_FODDER.asItem(), ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "color"), (stack, level, entity, state) -> {
                    MoaFodder fodder = stack.get(DADataComponentTypes.MOA_FODDER);
                    if (fodder != null) {
                        if(fodder.effect().is(DAMobEffects.MOA_BONUS_JUMPS)) {
                            return 0.1F;
                        }
                        else if(fodder.effect().is(MobEffects.FIRE_RESISTANCE)) {
                            return 0.2F;
                        }
                        else if(fodder.effect().is(MobEffects.JUMP)) {
                            return 0.3F;
                        }
                    }
                    return  0.0F;
                }

        );

        ItemProperties.register(DAItems.BLADE_OF_LUCK.get(),
                ResourceLocation.withDefaultNamespace("sword_state"), (stack, world, entity, value) -> {
                    if(entity instanceof Player player) {
                        DAPlayerAttachment attachment = player.getData(DAAttachments.PLAYER);
                        if(attachment.changeBladeOfLuckState) {
                            if(player.swinging) {
                                if (attachment.getOldBladeOfLuckDamage() <= 3)
                                    return 0.2F;
                                if (attachment.getOldBladeOfLuckDamage() <= 8)
                                    return 0.4F;
                                else if (attachment.getOldBladeOfLuckDamage() <= 12)
                                    return 0.6F;
                                else if (attachment.getOldBladeOfLuckDamage() <= 16)
                                    return 0.8F;
                                else return 1.0F;
                            }
                            else attachment.changeBladeOfLuckState = false;
                        }
                        else {
                            return 0.5F;
                        }
                    }
                    return 0.5F;
                });

        ItemProperties.register(DAItems.STORM_BOW.get(), ResourceLocation.withDefaultNamespace("pull"), (p_344163_, p_344164_, p_344165_, p_344166_) -> {
            if (p_344165_ == null) {
                return 0.0F;
            } else {
                return p_344165_.getUseItem() != p_344163_ ? 0.0F : (float)(p_344163_.getUseDuration(p_344165_) - p_344165_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(DAItems.STORM_BOW.get(), ResourceLocation.withDefaultNamespace("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F);
    }


    private static void sunClock() {
        ItemProperties.register(DAItems.SUN_CLOCK.get(), ResourceLocation.withDefaultNamespace("time"), new ClampedItemPropertyFunction() {
            private double rotation;
            private double rota;
            private long lastUpdateTick;

            @Override
            public float unclampedCall(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity p_entity, int seed) {
                Entity entity = (p_entity != null ? p_entity : stack.getEntityRepresentation());
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (level == null && entity.level() instanceof ClientLevel) {
                        level = (ClientLevel)entity.level();
                    }

                    if (level == null) {
                        return 0.0F;
                    } else {
                        double d0;
                        if (level.dimensionType().natural()) {
                            d0 = level.getTimeOfDay(1.0F);
                        } else {
                            d0 = Math.random();
                        }

                        d0 = this.wobble(level, d0);
                        return (float)d0;
                    }
                }
            }

            private double wobble(Level level, double rotation) {
                if (level.getGameTime() != this.lastUpdateTick) {
                    this.lastUpdateTick = level.getGameTime();
                    double d0 = rotation - this.rotation;
                    d0 = Mth.positiveModulo(d0 + 0.5, 1.0) - 0.5;
                    this.rota += d0 * 0.1;
                    this.rota *= 0.9;
                    this.rotation = Mth.positiveModulo(this.rotation + this.rota, 1.0);
                }

                return this.rotation;
            }
        });
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(DAMenuTypes.COMBINER_MENU.get(), CombinerScreen::new);
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(DAParticles.POISON_BUBBLES.get(), PoisonBubbles.Provider::new);
        event.registerSpriteSet(DAParticles.MYTHICAL_PARTICLE.get(), MysticalParticle.Provider::new);

        event.registerSpriteSet(DAParticles.CLOVER_VERY_LUCKY.get(), LuckParticle.Provider::new);
        event.registerSpriteSet(DAParticles.CLOVER_LUCKY.get(), LuckParticle.Provider::new);
        event.registerSpriteSet(DAParticles.CLOVER.get(), LuckParticle.Provider::new);
        event.registerSpriteSet(DAParticles.CLOVER_UNLUCKY.get(), LuckParticle.Provider::new);

        event.registerSpriteSet(DAParticles.ROSEROOT_LEAVES.get(), (spriteSet)
                -> (particleType, level, v, v1, v2, v3, v4, v5)
                -> new CherryParticle(level, v, v1, v2, spriteSet));

        event.registerSpriteSet(DAParticles.FLOWERING_ROSEROOT_LEAVES.get(), (spriteSet)
                -> (particleType, level, v, v1, v2, v3, v4, v5)
                -> new CherryParticle(level, v, v1, v2, spriteSet));

        event.registerSpriteSet(DAParticles.EOTS_EXPLOSION.get(), EOTSExplosionParticle.Provider::new);
        event.registerSpriteSet(DAParticles.EOTS_PRE_FIGHT.get(), EOTSPreFightParticle.Provider::new);
    }

    public static void registerAccessoriesRenderers() {
        AccessoriesRendererRegistry.registerRenderer(DAItems.SKYJADE_GLOVES.get(), SkyjadeGlovesRenderer::new);
        AccessoriesRendererRegistry.registerRenderer(DAItems.FLOATY_SCARF.get(), FloatyScarfRenderer::new);
        AccessoriesRendererRegistry.registerRenderer(DAItems.WIND_SHIELD.get(), WindShieldRenderer::new);
        AccessoriesRendererRegistry.registerRenderer(DAItems.STORMFORGED_GLOVES.get(), GlovesRenderer::new);
        AccessoriesRendererRegistry.registerRenderer(DAItems.STRATUS_GLOVES.get(), GlovesRenderer::new);
        AccessoriesRendererRegistry.registerRenderer(DAItems.MEDAL_OF_HONOR.get(), PendantRenderer::new);
        AccessoriesRendererRegistry.registerRenderer(DAItems.AERCLOUD_NECKLACE.get(), PendantRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ?
                        BiomeColors.getAverageGrassColor(pLevel, pState.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? pPos.below() : pPos) : GrassColor.getDefaultColor(),
                DABlocks.TALL_GLOWING_GRASS.get());
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ?
                BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.getDefaultColor(), DABlocks.GLOWING_VINE.get());
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ?
                BiomeColors.getAverageGrassColor(pLevel, pPos) : FoliageColor.getDefaultColor(), DABlocks.AERCLOUD_GRASS_BLOCK.get(), DABlocks.GLOWING_SPORES.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((ItemStack pStack, int pTintIndex) -> 10021818, DABlocks.AERCLOUD_GRASS_BLOCK.get());
        event.register((ItemStack stack, int tintindex) -> stack.has(DADataComponentTypes.FLOATY_SCARF) ?
                GentleWind.getFromColor(stack.get(DADataComponentTypes.FLOATY_SCARF).colors(), tintindex) : -1, DAItems.FLOATY_SCARF.get());
    }

    /**
     *  Method responsible for the needle texture rotation.
     */
    public static void compassRotation(Item item){
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("angle"), new ItemPropertyFunction() {
            @OnlyIn(Dist.CLIENT)
            private double rotation;
            @OnlyIn(Dist.CLIENT)
            private double rota;
            @OnlyIn(Dist.CLIENT)
            private long lastUpdateTick;

            @OnlyIn(Dist.CLIENT)
            public float call(ItemStack stack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingBaseIn, int seed) {
                if (livingBaseIn == null && !stack.isFramed()) {
                    return 0.0F;
                } else {
                    boolean livingExists = livingBaseIn != null;
                    Entity entity = livingExists ? livingBaseIn : stack.getFrame();
                    if (clientLevel == null && entity.level() instanceof ClientLevel) {
                        clientLevel = (ClientLevel) entity.level();
                    }

                    double d0;
                    GlobalPos globalPos = getStructurePos(stack);
                    if (globalPos != null && clientLevel.dimension().equals(globalPos.dimension())) {
                        double d1 = livingExists ? (double) entity.getYRot() : this.getFrameRotation((ItemFrame) entity);
                        d1 = Mth.positiveModulo(d1 / 360.0D, 1.0D);
                        double d2 = this.getSpawnToAngle(entity, globalPos.pos()) / (double) ((float) Math.PI * 2F);
                        d0 = 0.5D - (d1 - 0.25D - d2);
                    } else {
                        d0 = Math.random();
                    }

                    if (livingExists) {
                        d0 = this.wobble(clientLevel, d0);
                    }

                    return Mth.positiveModulo((float) d0, 1.0F);
                }
            }

            @OnlyIn(Dist.CLIENT)
            private double wobble(ClientLevel clientLevel, double p_185093_2_) {
                if (clientLevel.getGameTime() != this.lastUpdateTick) {
                    this.lastUpdateTick = clientLevel.getGameTime();
                    double d0 = p_185093_2_ - this.rotation;
                    d0 = Mth.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
                    this.rota += d0 * 0.1D;
                    this.rota *= 0.8D;
                    this.rotation = Mth.positiveModulo(this.rotation + this.rota, 1.0D);
                }

                return this.rotation;
            }

            @OnlyIn(Dist.CLIENT)
            private double getFrameRotation(ItemFrame itemFrame) {
                Direction direction = itemFrame.getDirection();
                int i = direction.getAxis().isVertical() ? 90 * direction.getAxisDirection().getStep() : 0;
                return Mth.wrapDegrees(180 + direction.get2DDataValue() * 90 + itemFrame.getRotation() * 45 + i);
            }

            @OnlyIn(Dist.CLIENT)
            private double getSpawnToAngle(Entity entityIn, @NotNull BlockPos pos) {
                return Math.atan2((double) pos.getZ() - entityIn.getZ(), (double) pos.getX() - entityIn.getX());
            }

            public GlobalPos getStructurePos(ItemStack stack) {
                if (stack.has(DADataComponentTypes.DUNGEON_TRACKER)) {
                    DungeonTracker tracker = stack.get(DADataComponentTypes.DUNGEON_TRACKER);
                    if (tracker != null && tracker.found()) {
                        if (tracker.target().isPresent()) {
                            return tracker.target().get();
                        }
                    }
                }
                return null;
            }
        });
    }

    public record StructurePos(BlockPos pos, ResourceLocation dimensionLocation) {
    }
}