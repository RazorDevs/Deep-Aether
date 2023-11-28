package teamrazor.deepaether;


import com.google.common.reflect.Reflection;
import com.mojang.logging.LogUtils;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.resource.PathPackResources;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import teamrazor.aeroblender.aether.AetherRuleCategory;
import teamrazor.deepaether.advancement.DAAdvancementTriggers;
import teamrazor.deepaether.block.Behaviors.DADispenseBehaviors;
import teamrazor.deepaether.block.Behaviors.DaCauldronInteraction;
import teamrazor.deepaether.fluids.DAFluidTypes;
import teamrazor.deepaether.init.*;
import teamrazor.deepaether.networking.DAPacketHandler;
import teamrazor.deepaether.recipe.DARecipe;
import teamrazor.deepaether.recipe.DARecipeSerializers;
import teamrazor.deepaether.world.biomes.DARegion;
import teamrazor.deepaether.world.biomes.DASurfaceData;
import teamrazor.deepaether.world.feature.DAFeatures;
import teamrazor.deepaether.world.feature.tree.decorators.DADecoratorType;
import teamrazor.deepaether.world.feature.tree.decorators.DARootPlacers;
import teamrazor.deepaether.world.feature.tree.foliage.DAFoliagePlacers;
import teamrazor.deepaether.world.feature.tree.trunk.DaTrunkPlacerTypes;
import teamrazor.deepaether.world.placementmodifier.DAPlacementModifiers;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import java.nio.file.Path;
import java.util.Calendar;
import java.util.stream.Collectors;

@Mod("deep_aether")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepAetherMod {

	//TODO: add it_lang translation

	public static final Logger LOGGER = LogUtils.getLogger();

	public static final String MODID = "deep_aether";
	public static final String LOST_AETHER_CONTENT = "lost_aether_content";
	public static final String AETHER_GENESIS = "aether_genesis";
	public static final String AETHER_REDUX = "aether_redux";
	public static final String ANCIENT_AETHER = "ancient_aether";

	private static final String PROTOCOL_VERSION = "1";

	static Calendar CALENDER = Calendar.getInstance();
	public static boolean IS_HALLOWEEN = ((CALENDER.get(Calendar.MONTH) == Calendar.OCTOBER && CALENDER.get(Calendar.DAY_OF_MONTH) > 20)
			|| (CALENDER.get(Calendar.MONTH) == Calendar.NOVEMBER) && CALENDER.get(Calendar.DAY_OF_MONTH) < 10);

	public static boolean IsHalloweenContentEnabled() {
		return IS_HALLOWEEN || DeepAetherConfig.COMMON.always_enable_halloween_content.get();
	}
	public static final Path DIRECTORY = FMLPaths.CONFIGDIR.get().resolve(MODID);

	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);


	public DeepAetherMod() {

		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::commonSetup);
		MinecraftForge.EVENT_BUS.register(this);

		GeckoLib.initialize();
		DABlocks.BLOCKS.register(bus);
		DAItems.ITEMS.register(bus);
		DAParticles.PARTICLE_TYPES.register(bus);
		DAEntities.ENTITY_TYPES.register(bus);
		DASounds.SOUNDS.register(bus);
		DAFluids.FLUIDS.register(bus);
		DAFluidTypes.FLUID_TYPES.register(bus);
		DADecoratorType.REGISTRY.register(bus);
		DABlockEntityTypes.BLOCK_ENTITY_TYPES.register(bus);
		DABlocks.registerWoodTypes();
		DAFoliagePlacers.FOLIAGE_PLACERS.register(bus);
		DARootPlacers.ROOT_PLACERS.register(bus);
		DaTrunkPlacerTypes.TRUNK_PLACERS.register(bus);
		DAFeatures.FEATURES.register(bus);
		DAMobEffects.EFFECTS.register(bus);
		DIRECTORY.toFile().mkdirs(); // Ensures the Deep Aether's config folder is generated.
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DeepAetherConfig.COMMON_SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, DeepAetherConfig.CLIENT_SPEC);
		DARecipe.RECIPE_TYPES.register(bus);
		DARecipeSerializers.RECIPE_SERIALIZERS.register(bus);
		DAPacketHandler.register();
	}

	public void commonSetup(FMLCommonSetupEvent event) {
		Reflection.initialize(DAPlacementModifiers.class);
		DAAdvancementTriggers.init();
		event.enqueueWork(() -> {
			DaCauldronInteraction.bootStrap();
			DABlocks.registerPots();
			DABlocks.registerFlammability();
			DAItems.setupBucketReplacements();
			this.registerDispenserBehaviors();
			this.registerCompostable();
		});

		event.enqueueWork(() ->
		{
			// Weights are kept intentionally low as we add minimal biomes
			Regions.register(new DARegion(new ResourceLocation(MODID, "deep_aether"), DeepAetherConfig.COMMON.deep_aether_biome_weight.get()));

			// Register our surface rules
			SurfaceRuleManager.addSurfaceRules(AetherRuleCategory.THE_AETHER, MODID, DASurfaceData.makeRules());
		});
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
		InterModComms.sendTo(MODID, "helloworld", () -> {
			LOGGER.info("Hello world from the MDK");
			return "Hello world";
		});
	}

	private void processIMC(final InterModProcessEvent event) {
		LOGGER.info("Got IMC {}", event.getIMCStream().
				map(m -> m.messageSupplier().get()).
				collect(Collectors.toList()));
	}

	private void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(Items.POTION, DADispenseBehaviors.WATER_BOTTLE_TO_AETHER_MUD_DISPENSE_BEHAVIOR);
		DispenserBlock.registerBehavior(DAItems.PLACEABLE_POISON_BUCKET.get(), DADispenseBehaviors.DEEP_AETHER_BUCKET_PICKUP_DISPENSE_BEHAVIOR);
		DispenserBlock.registerBehavior(DAItems.VIRULENT_QUICKSAND_BUCKET.get(), DADispenseBehaviors.DEEP_AETHER_BUCKET_PICKUP_DISPENSE_BEHAVIOR);
	}

	public void registerCompostable() {
		ComposterBlock.COMPOSTABLES.put(DABlocks.ROSEROOT_LEAVES.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.BLUE_ROSEROOT_LEAVES.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.FLOWERING_ROSEROOT_LEAVES.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.YAGROOT_LEAVES.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.CRUDEROOT_LEAVES.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.AETHER_MOSS_BLOCK.get().asItem(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.AETHER_MOSS_CARPET.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.ROSEROOT_SAPLING.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.BLUE_ROSEROOT_SAPLING.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.YAGROOT_SAPLING.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.CRUDEROOT_SAPLING.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.CONBERRY_LEAVES.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.CONBERRY_SAPLING.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.SUNROOT_LEAVES.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.SUNROOT_SAPLING.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.YAGROOT_ROOTS.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.YAGROOT_VINE.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.AERGLOW_BLOSSOM_BLOCK.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.MINI_GOLDEN_GRASS.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.SHORT_GOLDEN_GRASS.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.MEDIUM_GOLDEN_GRASS.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.TALL_GOLDEN_GRASS.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.RADIANT_ORCHID.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.AERLAVENDER.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.TALL_AERLAVENDER.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.AETHER_CATTAILS.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.TALL_AETHER_CATTAILS.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(DABlocks.GOLDEN_FLOWER.get().asItem(), 0.3F);

		ComposterBlock.COMPOSTABLES.put(DAItems.AERGLOW_BLOSSOM.get(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(DAItems.GOLDEN_BERRIES.get(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(DAItems.GOLDEN_GRASS_SEEDS.get(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(DAItems.SQUASH_SEEDS.get(), 0.1F);
	}


	@SubscribeEvent
	public static void addAetherAdditionalResourcesPack(AddPackFindersEvent event) {
		if (event.getPackType() == PackType.CLIENT_RESOURCES) {
			var resourcePath = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/deep_aether_additional_assets");

			PathPackResources pack = new PathPackResources(ModList.get().getModFileById(DeepAetherMod.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath);
			PackMetadataSection metadata = new PackMetadataSection(Component.literal("Deep Aether Additional Assets"), PackType.CLIENT_RESOURCES.getVersion(SharedConstants.getCurrentVersion()));
			event.addRepositorySource((packConsumer, packConstructor) ->
					packConsumer.accept(packConstructor.create(
							"builtin/deep_aether_additional_assets",
							Component.literal("Deep Aether Additional Assets"),
							false,
							() -> pack,
							metadata,
							Pack.Position.TOP,
							PackSource.BUILT_IN,
							false)
					));


			if (ModList.get().isLoaded("aether_genesis") || ModList.get().isLoaded("aether_redux")) {
				var resourcePath1 = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/golden_swet_ball/DAGoldenSwetBallFixClient");
				PathPackResources pack1 = new PathPackResources(ModList.get().getModFileById(DeepAetherMod.MODID).getFile().getFileName() + ":" + resourcePath1, resourcePath1);
				PackMetadataSection metadata1 = new PackMetadataSection(Component.literal("Deep Aether Golden Swet Ball Texture Fix"), PackType.CLIENT_RESOURCES.getVersion(SharedConstants.getCurrentVersion()));
				event.addRepositorySource((packConsumer, packConstructor) ->
						packConsumer.accept(packConstructor.create(
								"builtin/DAGoldenSwetBallFixClient",
								Component.literal("Deep Aether Golden Swet Ball Texture Fix"),
								false,
								() -> pack1,
								metadata1,
								Pack.Position.TOP,
								PackSource.DEFAULT,
								true)
						));
			}

		} else if (event.getPackType() == PackType.SERVER_DATA) {
			if (ModList.get().isLoaded("aether_genesis")) {
				var resourcePath = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/golden_swet_ball/DAGoldenSwetBallAetherGenesisFixData");
				PathPackResources pack = new PathPackResources(ModList.get().getModFileById(DeepAetherMod.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath);
				PackMetadataSection metadata = new PackMetadataSection(Component.literal("Deep Aether Golden Swet Ball Aether Genesis Fix"), PackType.SERVER_DATA.getVersion(SharedConstants.getCurrentVersion()));
				event.addRepositorySource((packConsumer, packConstructor) ->
						packConsumer.accept(packConstructor.create(
								"builtin/DAGoldenSwetBallAetherGenesisFix",
								Component.literal("Deep Aether Golden Swet Ball Aether Genesis Fix"),
								false,
								() -> pack,
								metadata,
								Pack.Position.TOP,
								PackSource.SERVER,
								true)
						));
			} else if (ModList.get().isLoaded("aether_redux")) {
				var resourcePath = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/golden_swet_ball/DAGoldenSwetBallAetherReduxFixData");
				PathPackResources pack = new PathPackResources(ModList.get().getModFileById(DeepAetherMod.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath);
				PackMetadataSection metadata = new PackMetadataSection(Component.literal("Deep Aether Golden Swet Ball Aether Redux Fix"), PackType.SERVER_DATA.getVersion(SharedConstants.getCurrentVersion()));
				event.addRepositorySource((packConsumer, packConstructor) ->
						packConsumer.accept(packConstructor.create(
								"builtin/DAGoldenSwetBallAetherReduxFix",
								Component.literal("Deep Aether Golden Swet Ball Aether Redux Fix"),
								false,
								() -> pack,
								metadata,
								Pack.Position.TOP,
								PackSource.SERVER,
								true)
						));
			}

			if (ModList.get().isLoaded(DeepAetherMod.LOST_AETHER_CONTENT)) {
				var resourcePath = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/compat_recipes/aether_lost_content_compat");
				PathPackResources pack = new PathPackResources(ModList.get().getModFileById(DeepAetherMod.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath);
				PackMetadataSection metadata = new PackMetadataSection(Component.literal("Lost Aether Content Compat"), PackType.SERVER_DATA.getVersion(SharedConstants.getCurrentVersion()));
				event.addRepositorySource((packConsumer, packConstructor) ->
						packConsumer.accept(packConstructor.create(
								"builtin/lost_aether_content_compat",
								Component.literal("Lost Aether Content Compat"),
								true,
								() -> pack,
								metadata,
								Pack.Position.TOP,
								PackSource.SERVER,
								true)
						));
			}

			if (ModList.get().isLoaded(DeepAetherMod.AETHER_REDUX) ) {
				var resourcePath = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/compat_recipes/aether_redux_compat");
				PathPackResources pack = new PathPackResources(ModList.get().getModFileById(DeepAetherMod.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath);
				PackMetadataSection metadata = new PackMetadataSection(Component.literal("Aether Redux Compat"), PackType.SERVER_DATA.getVersion(SharedConstants.getCurrentVersion()));
				event.addRepositorySource((packConsumer, packConstructor) ->
						packConsumer.accept(packConstructor.create(
								"builtin/aether_redux_compat",
								Component.literal("Aether Redux Compat"),
								true,
								() -> pack,
								metadata,
								Pack.Position.TOP,
								PackSource.SERVER,
								true)
						));
			}
		}
	}
}