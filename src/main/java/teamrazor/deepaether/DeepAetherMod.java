package teamrazor.deepaether;


import com.google.common.reflect.Reflection;
import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
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
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;
import teamrazor.aeroblender.aether.AetherRuleCategory;
import teamrazor.deepaether.advancement.DAAdvancementTriggers;
import teamrazor.deepaether.block.Behaviors.DADispenseBehaviors;
import teamrazor.deepaether.block.Behaviors.DaCauldronInteraction;
import teamrazor.deepaether.datagen.DABlockstateData;
import teamrazor.deepaether.datagen.DAItemModelData;
import teamrazor.deepaether.datagen.DARecipeData;
import teamrazor.deepaether.datagen.DAWorldGenData;
import teamrazor.deepaether.datagen.loot.DALootTableData;
import teamrazor.deepaether.datagen.loot.modifiers.DAGlobalLootModifiers;
import teamrazor.deepaether.datagen.loot.modifiers.DALootDataProvider;
import teamrazor.deepaether.datagen.tags.DABiomeTagData;
import teamrazor.deepaether.datagen.tags.DABlockTagData;
import teamrazor.deepaether.datagen.tags.DAEntityTagData;
import teamrazor.deepaether.datagen.tags.DAItemTagData;
import teamrazor.deepaether.fluids.DAFluidTypes;
import teamrazor.deepaether.init.*;
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
import java.util.concurrent.CompletableFuture;
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

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dataSetup);



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
		DAGlobalLootModifiers.LOOT_MODIFIERS.register(bus);
		DAMobEffects.EFFECTS.register(bus);
		DIRECTORY.toFile().mkdirs(); // Ensures the Deep Aether's config folder is generated.
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DeepAetherConfig.COMMON_SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, DeepAetherConfig.CLIENT_SPEC);
		DARecipe.RECIPE_TYPES.register(bus);
		DARecipeSerializers.RECIPE_SERIALIZERS.register(bus);

	}

	public void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		PackOutput packOutput = generator.getPackOutput();

		// Client Data
		generator.addProvider(event.includeClient(), new DABlockstateData(packOutput, fileHelper));
		generator.addProvider(event.includeClient(), new DAItemModelData(packOutput, fileHelper));

		// Server Data
		generator.addProvider(event.includeServer(), new DAWorldGenData(packOutput, lookupProvider));
		generator.addProvider(event.includeServer(), new DARecipeData(packOutput));
		generator.addProvider(event.includeServer(), DALootTableData.create(packOutput));
		DABlockTagData blockTags = new DABlockTagData(packOutput, lookupProvider, fileHelper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new DAItemTagData(packOutput, lookupProvider, blockTags.contentsGetter(), fileHelper));
		generator.addProvider(event.includeServer(), new DABiomeTagData(packOutput, lookupProvider, fileHelper));
		generator.addProvider(event.includeServer(), new DAEntityTagData(packOutput, lookupProvider, fileHelper));
		generator.addProvider(event.includeServer(), new DALootDataProvider(packOutput));
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

	private void enqueueIMC(final InterModEnqueueEvent event)
	{
		InterModComms.sendTo(MODID, "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
	}

	private void processIMC(final InterModProcessEvent event)
	{
		LOGGER.info("Got IMC {}", event.getIMCStream().
				map(m->m.messageSupplier().get()).
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
			var pack = Pack.readMetaAndCreate("builtin/deep_aether_additional_assets", Component.literal("Deep Aether Additional Assets"), false,
					path -> new PathPackResources(path, resourcePath, true), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
			event.addRepositorySource(consumer -> consumer.accept(pack));


			if(ModList.get().isLoaded(DeepAetherMod.AETHER_GENESIS) || ModList.get().isLoaded("aether_redux")) {
				var resourcePath1 = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/golden_swet_ball/DAGoldenSwetBallFixClient");
				var pack1 = Pack.readMetaAndCreate("builtin/DAGoldenSwetBallFixClient", Component.literal("Deep Aether Golden Swet Ball Texture Fix"), true,
						path -> new PathPackResources(path, resourcePath1, true), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.DEFAULT);
				event.addRepositorySource(consumer -> consumer.accept(pack1));

			}
		}
		if(ModList.get().isLoaded(DeepAetherMod.AETHER_GENESIS) && event.getPackType() == PackType.SERVER_DATA) {
			if (event.getPackType() == PackType.SERVER_DATA) {
				var resourcePath = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/golden_swet_ball/DAGoldenSwetBallAetherGenesisFixData");
				var pack = Pack.readMetaAndCreate("builtin/DAGoldenSwetBallAetherGenesisFix", Component.literal("Deep Aether Golden Swet Ball Aether Genesis Fix"), true,
						path -> new PathPackResources(path, resourcePath, true), PackType.SERVER_DATA, Pack.Position.TOP, PackSource.SERVER);
				event.addRepositorySource(consumer -> consumer.accept(pack));
			}
		}

		else if(ModList.get().isLoaded(DeepAetherMod.AETHER_REDUX) && event.getPackType() == PackType.SERVER_DATA) {
			if (event.getPackType() == PackType.SERVER_DATA) {
				var resourcePath = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/golden_swet_ball/DAGoldenSwetBallAetherReduxFixData");
				var pack = Pack.readMetaAndCreate("builtin/DAGoldenSwetBallAetherReduxFix", Component.literal("Deep Aether Golden Swet Ball Aether Redux Fix"), true,
						path -> new PathPackResources(path, resourcePath, true), PackType.SERVER_DATA, Pack.Position.TOP, PackSource.SERVER);

				event.addRepositorySource(consumer -> consumer.accept(pack));
			}
		}

		if(ModList.get().isLoaded(DeepAetherMod.LOST_AETHER_CONTENT) && event.getPackType() == PackType.SERVER_DATA) {
			if (event.getPackType() == PackType.SERVER_DATA) {
				var resourcePath = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/compat_recipes/aether_lost_content_compat");
				var pack = Pack.readMetaAndCreate("builtin/lost_aether_content_compat", Component.literal("Lost Aether Content Compat"), true,
						path -> new PathPackResources(path, resourcePath, true), PackType.SERVER_DATA, Pack.Position.TOP, PackSource.SERVER);

				event.addRepositorySource(consumer -> consumer.accept(pack));
			}
		}

		if(ModList.get().isLoaded(DeepAetherMod.AETHER_REDUX) && event.getPackType() == PackType.SERVER_DATA) {
			if (event.getPackType() == PackType.SERVER_DATA) {
				var resourcePath = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/compat_recipes/aether_redux_compat");
				var pack = Pack.readMetaAndCreate("builtin/aether_redux_compat", Component.literal("Aether Redux Compat"), true,
						path -> new PathPackResources(path, resourcePath, true), PackType.SERVER_DATA, Pack.Position.TOP, PackSource.SERVER);

				event.addRepositorySource(consumer -> consumer.accept(pack));
			}
		}

		if(ModList.get().isLoaded(DeepAetherMod.ANCIENT_AETHER) && event.getPackType() == PackType.SERVER_DATA) {
			if (event.getPackType() == PackType.SERVER_DATA) {
				var resourcePath = ModList.get().getModFileById(DeepAetherMod.MODID).getFile().findResource("packs/compat_recipes/ancient_aether_compat");
				var pack = Pack.readMetaAndCreate("builtin/aether_redux_compat", Component.literal("Ancient Aether Compat"), true,
						path -> new PathPackResources(path, resourcePath, true), PackType.SERVER_DATA, Pack.Position.TOP, PackSource.SERVER);

				event.addRepositorySource(consumer -> consumer.accept(pack));
			}
		}
	}
}
