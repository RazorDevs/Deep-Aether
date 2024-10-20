package teamrazor.deepaether.event;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import com.aetherteam.aether.client.renderer.accessory.PendantRenderer;
import com.aetherteam.aether.inventory.menu.LoreBookMenu;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.CherryParticle;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
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
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.renderer.curios.SkyjadeGlovesRenderer;
import teamrazor.deepaether.init.*;
import teamrazor.deepaether.item.compat.lost_content.AddonItemModelPredicates;
import teamrazor.deepaether.networking.attachment.DAAttachments;
import teamrazor.deepaether.networking.attachment.DAPlayerAttachment;
import teamrazor.deepaether.particle.custom.*;
import teamrazor.deepaether.screen.CombinerScreen;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = DeepAether.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAClientModBusEvents {

    /**
     * See {@link com.legacy.lost_aether.client.LCEntityRendering}
     */
    //TODO: UPDATE WHEN LOST AETHER CONTENT HAS PORTED TO 1.21+ (unlikely)
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

        LoreBookMenu.addLoreEntryOverride(stack -> stack.is(DAItems.STORM_SWORD.get()) && stack.getHoverName().getString().equalsIgnoreCase("storm ruler"), "lore.item.deep_aether.storm_ruler");

        event.enqueueWork(() -> {
            Sheets.addWoodType(DAWoodTypes.ROSEROOT);
            Sheets.addWoodType(DAWoodTypes.CRUDEROOT);
            Sheets.addWoodType(DAWoodTypes.YAGROOT);
            Sheets.addWoodType(DAWoodTypes.CONBERRY);
            Sheets.addWoodType(DAWoodTypes.SUNROOT);

            if (ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT)) {
                AddonItemModelPredicates.init();
            }

            registerItemModelPredicates();

            clockRotation(DAItems.SUN_CLOCK.get());

            compassRotation(DAItems.BRONZE_COMPASS.get());
            compassRotation(DAItems.SILVER_COMPASS.get());
            compassRotation(DAItems.GOLD_COMPASS.get());
        });
    }

    private static void registerItemModelPredicates() {
        ItemProperties.register(DAItems.BLADE_OF_LUCK.get(),
                new ResourceLocation("sword_state"), (stack, world, entity, value) -> {
                     if(entity instanceof Player player) {
                         DAPlayerAttachment attachment = player.getData(DAAttachments.PLAYER);
                         if(attachment.changeBladeOfLuckState) {
                             if(player.swinging) {
                                 if (attachment.getOldBladeOfLuckDamage() <= 3)
                                     return 0.25F;
                                 else if (attachment.getOldBladeOfLuckDamage() <= 10)
                                     return 0.5F;
                                 else if (attachment.getOldBladeOfLuckDamage() <= 15)
                                     return 0.75F;
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

    public static void registerCuriosRenderers() {
        CuriosRendererRegistry.register(DAItems.SKYJADE_GLOVES.get(), SkyjadeGlovesRenderer::new);
        CuriosRendererRegistry.register(DAItems.STRATUS_GLOVES.get(), GlovesRenderer::new);
        CuriosRendererRegistry.register(DAItems.MEDAL_OF_HONOR.get(), PendantRenderer::new);
        CuriosRendererRegistry.register(DAItems.AERCLOUD_NECKLACE.get(), PendantRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ?
                        BiomeColors.getAverageGrassColor(pLevel, pState.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? pPos.below() : pPos) : GrassColor.getDefaultColor(),
                DABlocks.TALL_GLOWING_GRASS.get());
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ?
                BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.getDefaultColor(), DABlocks.GLOWING_VINE.get());
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null && pPos != null ?
                BiomeColors.getAverageGrassColor(pLevel, pPos) : FoliageColor.getDefaultColor(), DABlocks.AERCLOUD_GRASS_BLOCK.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((ItemStack pStack, int pTintIndex) -> 10021818, DABlocks.AERCLOUD_GRASS_BLOCK.get());
    }

    /**
     *  Method responsible for the needle texture rotation.
     */
    public static void compassRotation(Item item){
        ItemProperties.register(item, new ResourceLocation("angle"), new ItemPropertyFunction() {
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
                    StructurePos globalPos = getStructurePos(stack);
                    if (globalPos != null && clientLevel.dimension().location().equals(globalPos.dimensionLocation())) {
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

            public StructurePos getStructurePos(ItemStack stack) {
                if (stack.hasTag()) {
                    final CompoundTag tag = stack.getTag();
                    if (tag != null && tag.contains("deep_aether:structureFound") && tag.getBoolean("deep_aether:structureFound")) {
                        if (tag.contains("deep_aether:structureLocation")) {
                            final BlockPos structurePos = BlockPos.of(tag.getLong("deep_aether:structureLocation"));
                            return new StructurePos(structurePos, new ResourceLocation(Aether.MODID, "the_aether"));
                        }
                    }
                }
                return null;
            }
        });
    }

    /**
     *  Method responsible for the clock day/night cycle texture rotation.
     */
    public static void clockRotation(Item item){
        ItemProperties.register(item, new ResourceLocation("time"), new ClampedItemPropertyFunction() {
            private double rotation;
            private double rota;
            private long lastUpdateTick;

            public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
                Entity entity = livingEntity != null ? livingEntity : itemStack.getEntityRepresentation();
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (clientLevel == null && entity.level() instanceof ClientLevel) {
                        clientLevel = (ClientLevel) entity.level();
                    }

                    if (clientLevel == null) {
                        return 0.0F;
                    } else {
                        double d0;
                        if (clientLevel.dimensionType().natural()) {
                            d0 = clientLevel.getTimeOfDay(1.0F);
                        } else {
                            d0 = Math.random();
                        }

                        d0 = this.wobble(clientLevel, d0);
                        return (float)d0;
                    }
                }
            }

            private double wobble(Level level, double v) {
                if (level.getGameTime() != this.lastUpdateTick) {
                    this.lastUpdateTick = level.getGameTime();
                    double d0 = v - this.rotation;
                    d0 = Mth.positiveModulo(d0 + 0.5, 1.0) - 0.5;
                    this.rota += d0 * 0.1;
                    this.rota *= 0.9;
                    this.rotation = Mth.positiveModulo(this.rotation + this.rota, 1.0);
                }

                return this.rotation;
            }
        });
    }

    public record StructurePos(BlockPos pos, ResourceLocation dimensionLocation) {
    }
}