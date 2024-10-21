package io.github.razordevs.deep_aether;


import com.aetherteam.aether.entity.AetherEntityTypes;
import com.google.common.reflect.Reflection;
import com.mojang.logging.LogUtils;
import io.github.razordevs.aeroblender.aether.AetherRuleCategory;
import io.github.razordevs.deep_aether.advancement.DAAdvancementTriggers;
import io.github.razordevs.deep_aether.block.behavior.DADispenseBehaviors;
import io.github.razordevs.deep_aether.block.behavior.DaCauldronInteraction;
import io.github.razordevs.deep_aether.datagen.*;
import io.github.razordevs.deep_aether.datagen.loot.DALootTableData;
import io.github.razordevs.deep_aether.datagen.loot.modifiers.DAGlobalLootModifiers;
import io.github.razordevs.deep_aether.datagen.loot.modifiers.DALootDataProvider;
import io.github.razordevs.deep_aether.datagen.tags.*;
import io.github.razordevs.deep_aether.datagen.DARegistryDataGenerator;
import io.github.razordevs.deep_aether.event.DAGeneralEvents;
import io.github.razordevs.deep_aether.fluids.DAFluidTypes;
import io.github.razordevs.deep_aether.init.*;
import io.github.razordevs.deep_aether.item.component.DADataComponentTypes;
import io.github.razordevs.deep_aether.item.gear.DAArmorMaterials;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.github.razordevs.deep_aether.networking.packet.DAPlayerSyncPacket;
import io.github.razordevs.deep_aether.networking.packet.MoaEffectSyncPacket;
import io.github.razordevs.deep_aether.recipe.DARecipeSerializers;
import io.github.razordevs.deep_aether.recipe.DARecipeTypes;
import io.github.razordevs.deep_aether.world.biomes.DARareRegion;
import io.github.razordevs.deep_aether.world.biomes.DARegion;
import io.github.razordevs.deep_aether.world.biomes.DASurfaceData;
import io.github.razordevs.deep_aether.world.feature.DAFeatures;
import io.github.razordevs.deep_aether.world.feature.tree.decorators.DADecoratorType;
import io.github.razordevs.deep_aether.world.feature.tree.decorators.DARootPlacers;
import io.github.razordevs.deep_aether.world.feature.tree.foliage.DAFoliagePlacers;
import io.github.razordevs.deep_aether.world.feature.tree.trunk.DaTrunkPlacerTypes;
import io.github.razordevs.deep_aether.world.placementmodifier.DAPlacementModifiers;
import io.github.razordevs.deep_aether.world.structure.DAStructurePieceTypes;
import io.github.razordevs.deep_aether.world.structure.DAStructureTypes;
import io.github.razordevs.deep_aether.world.structure.processor.DAStructureProcessor;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import java.nio.file.Path;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Mod("deep_aether")
public class DeepAether {

	//TODO: add it_lang translation
	//TODO: add se_lang translation
	//TODO: clean up code
	//TODO: Custom plants are solid
	//TODO: Some grass have rendering issues
	//TODO: Continue Sacred Lands Testing

	public static final Logger LOGGER = LogUtils.getLogger();

	public static final String MODID = "deep_aether";
	public static final String MOD_VERSION = "1.1-dev-4";
	public static final String LOST_AETHER_CONTENT = "lost_aether_content";
	public static final String AETHER_GENESIS = "aether_genesis";
	public static final String AETHER_REDUX = "aether_redux";
	public static final String ANCIENT_AETHER = "ancient_aether";
	public static final String EMISSIVITY = "aether_emissivity";
	public static final String PROTECT_YOUR_MOA = "aether_protect_your_moa";

	public static final Path DIRECTORY = FMLPaths.CONFIGDIR.get().resolve(MODID);

	static Calendar CALENDER = Calendar.getInstance();
	public static boolean IS_HALLOWEEN = ((CALENDER.get(Calendar.MONTH) == Calendar.OCTOBER && CALENDER.get(Calendar.DAY_OF_MONTH) > 20)
			|| (CALENDER.get(Calendar.MONTH) == Calendar.NOVEMBER) && CALENDER.get(Calendar.DAY_OF_MONTH) < 10);

	public static boolean IsHalloweenContentEnabled() {
		return IS_HALLOWEEN || DeepAetherConfig.COMMON.always_enable_halloween_content.get();
	}

	public DeepAether(ModContainer mod, IEventBus bus, Dist dist) {
		bus.addListener(this::dataSetup);
		bus.addListener(this::commonSetup);
		bus.addListener(this::registerPackets);
		bus.addListener(this::addAetherAdditionalResourcesPack);

		DABlocks.BLOCKS.register(bus);
		DAItems.ITEMS.register(bus);
		DAParticles.PARTICLE_TYPES.register(bus);
		DAEntities.ENTITY_TYPES.register(bus);
		DASounds.SOUNDS.register(bus);
		DAFluids.FLUIDS.register(bus);
		DAFluidTypes.FLUID_TYPES.register(bus);
		DADecoratorType.REGISTRY.register(bus);
		DABlockEntityTypes.BLOCK_ENTITY_TYPES.register(bus);
		DAFoliagePlacers.FOLIAGE_PLACERS.register(bus);
		DARootPlacers.ROOT_PLACERS.register(bus);
		DaTrunkPlacerTypes.TRUNK_PLACERS.register(bus);
		DAFeatures.FEATURES.register(bus);
		DAGlobalLootModifiers.LOOT_MODIFIERS.register(bus);
		DAMobEffects.EFFECTS.register(bus);
		DAPotions.POTIONS.register(bus);
		DARecipeTypes.RECIPE_TYPES.register(bus);
		DARecipeSerializers.RECIPE_SERIALIZERS.register(bus);
		DAMenuTypes.MENUS.register(bus);
		DAAdvancementTriggers.TRIGGERS.register(bus);
		DAAttachments.ATTACHMENTS.register(bus);
		DAPlacementModifiers.PLACEMENT_MODIFIERS.register(bus);
		DAStructureTypes.STRUCTURE_TYPES.register(bus);
		DAStructurePieceTypes.STRUCTURE_PIECE_TYPES.register(bus);
		DAArmorMaterials.ARMOR_MATERIALS.register(bus);
		DAStructureProcessor.STRUCTURE_PROCESSOR_TYPES.register(bus);
		DADataComponentTypes.DATA_COMPONENT_TYPES.register(bus);

		DABlocks.registerWoodTypes(); // Registered this early to avoid bugs with WoodTypes and signs.

		mod.registerConfig(ModConfig.Type.COMMON, DeepAetherConfig.COMMON_SPEC);
		mod.registerConfig(ModConfig.Type.CLIENT, DeepAetherConfig.CLIENT_SPEC);

		if (dist == Dist.CLIENT) {
			bus.addListener(DARecipeCategories::registerRecipeCategories);
		}

	}

	public void registerPackets(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0").optional();

		registrar.playBidirectional(DAPlayerSyncPacket.TYPE, DAPlayerSyncPacket.STREAM_CODEC, DAPlayerSyncPacket::execute);
		registrar.playBidirectional(MoaEffectSyncPacket.TYPE, MoaEffectSyncPacket.STREAM_CODEC, MoaEffectSyncPacket::execute);
	}

	public void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

		PackOutput packOutput = generator.getPackOutput();

		// Client Data
		generator.addProvider(event.includeClient(), new DABlockstateData(packOutput, fileHelper));
		generator.addProvider(event.includeClient(), new DAItemModelData(packOutput, fileHelper));

		// Server Data
		generator.addProvider(event.includeServer(), new DARegistryDataGenerator(packOutput, event.getLookupProvider()));
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		generator.addProvider(event.includeServer(), new DARecipeData(packOutput, lookupProvider));
		DABlockTagData blockTags = new DABlockTagData(packOutput, lookupProvider, fileHelper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new DAItemTagData(packOutput, lookupProvider, blockTags.contentsGetter(), fileHelper));
		generator.addProvider(event.includeServer(), new DABiomeTagData(packOutput, lookupProvider, fileHelper));
		generator.addProvider(event.includeServer(), new DAFluidTagData(packOutput, lookupProvider, fileHelper));
		generator.addProvider(event.includeServer(), new DAEntityTagData(packOutput, lookupProvider, fileHelper));
		generator.addProvider(event.includeServer(), new DALootDataProvider(packOutput, lookupProvider));
		generator.addProvider(event.includeClient(), new DADataMapData(packOutput, lookupProvider));
		generator.addProvider(event.includeServer(), DALootTableData.create(packOutput, lookupProvider));
	}

	public void commonSetup(FMLCommonSetupEvent event) {
		Reflection.initialize(DARecipeBookTypes.class);
        registerFlawlessBossDrops();
		registerFluidInteractions();

		event.enqueueWork(() -> {
            DaCauldronInteraction.bootStrap();
			DABlocks.registerPots();
			DABlocks.registerFlammability();
			DAItems.setupBucketReplacements();
			this.registerDispenserBehaviors();
			Regions.register(new DARegion(ResourceLocation.fromNamespaceAndPath(MODID, "deep_aether"), DeepAetherConfig.COMMON.deep_aether_biome_weight.get()));
			if(!DeepAetherConfig.COMMON.disable_storm_cloud_and_skyroot_rainforest_biomes.get())
				Regions.register(new DARareRegion(ResourceLocation.fromNamespaceAndPath(MODID, "rare"), DeepAetherConfig.COMMON.storm_cloud_biome_weight.get()));
			SurfaceRuleManager.addSurfaceRules(AetherRuleCategory.THE_AETHER, MODID, DASurfaceData.makeRules());
			//BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.WATER, DAItems.BIO_CRYSTAL.get(), DAPotions.REMEDY_POTION.get()));
		});
	}

	private void registerFlawlessBossDrops() {
		this.getFlawlessBossDrop(AetherEntityTypes.SLIDER.get(), DeepAetherConfig.COMMON.slider_flawless_boss_drop.get(), DAItems.SLIDER_EYE.get());
		this.getFlawlessBossDrop(AetherEntityTypes.VALKYRIE_QUEEN.get(), DeepAetherConfig.COMMON.valkyrie_queen_flawless_boss_drop.get(), DAItems.MEDAL_OF_HONOR.get());
		this.getFlawlessBossDrop(AetherEntityTypes.SUN_SPIRIT.get(), DeepAetherConfig.COMMON.sun_spirit_flawless_boss_drop.get(), DAItems.SUN_CORE.get());
		this.getFlawlessBossDrop(DAEntities.EOTS_CONTROLLER.get(), DeepAetherConfig.COMMON.eots_flawless_boss_drop.get(), DAItems.FLOATY_SCARF.get());

		if(ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT)){}
			//this.getFlawlessBossDrop(LCEntityTypes.AERWHALE_KING, DeepAetherConfig.COMMON.aerwhale_king_flawless_boss_drop.get(), DAItems.AERWHALE_SADDLE.get());
	}

	private void registerFluidInteractions(){
		// Poison + Water = Aersmog
		FluidInteractionRegistry.addInteraction(DAFluidTypes.POISON_FLUID_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
				NeoForgeMod.WATER_TYPE.value(), state -> DABlocks.AERSMOG.get().defaultBlockState()
		));

		// Poison + Lava = Crying Obsidian
		FluidInteractionRegistry.addInteraction(DAFluidTypes.POISON_FLUID_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
				NeoForgeMod.LAVA_TYPE.value(), state -> Blocks.CRYING_OBSIDIAN.defaultBlockState()
		));
	}

	private void getFlawlessBossDrop(EntityType<?> type, String string, Item fallBack) {
		if(string.equals("null")) {
			DAGeneralEvents.FLAWLESS_BOSS_DROP.put(type, null);
		}
		else {
			String[] SliderItemId = string.split(":");
			if (BuiltInRegistries.ITEM.containsKey(ResourceLocation.fromNamespaceAndPath(SliderItemId[0], SliderItemId[1])))
				DAGeneralEvents.FLAWLESS_BOSS_DROP.put(type, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(SliderItemId[0], SliderItemId[1])));
			else {
				DAGeneralEvents.FLAWLESS_BOSS_DROP.put(type, fallBack);
				LOGGER.info("Config value " + string + " is referring to a missing item! Resolving to default value");
			}
		}
	}

	private void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(Items.POTION, DADispenseBehaviors.WATER_BOTTLE_TO_AETHER_MUD_DISPENSE_BEHAVIOR);
		DispenserBlock.registerBehavior(DAItems.PLACEABLE_POISON_BUCKET.get(), DADispenseBehaviors.DEEP_AETHER_BUCKET_PICKUP_DISPENSE_BEHAVIOR);
		DispenserBlock.registerBehavior(DAItems.VIRULENT_QUICKSAND_BUCKET.get(), DADispenseBehaviors.DEEP_AETHER_BUCKET_PICKUP_DISPENSE_BEHAVIOR);
	}
	public void addAetherAdditionalResourcesPack(AddPackFindersEvent event) {
		if (event.getPackType() == PackType.CLIENT_RESOURCES) {
			setupCompatPack("overrides/deep_aether_additional_assets", "Deep Aether Additional Assets", event, PackType.CLIENT_RESOURCES, PackSource.BUILT_IN, false);

			if (ModList.get().isLoaded(EMISSIVITY))
				setupCompatPack("overrides/deep_aether_emissivity", "Deep Aether Emissivity", event, PackType.CLIENT_RESOURCES, PackSource.BUILT_IN, true);

			if (ModList.get().isLoaded(AETHER_GENESIS) || ModList.get().isLoaded(AETHER_REDUX))
				setupCompatPack("overrides/golden_swet_ball/DAGoldenSwetBallFixClient", "Deep Aether Golden Swet Ball Texture Fix", event, PackType.CLIENT_RESOURCES, PackSource.DEFAULT, true);
		}

		if (event.getPackType() == PackType.SERVER_DATA) {
			if (ModList.get().isLoaded(PROTECT_YOUR_MOA))
				setupCompatPack("compat_recipes/protect_your_moa_compat", "Deep Aether Protect Your Moa Compat", event);


			if (ModList.get().isLoaded(AETHER_GENESIS))
				setupCompatPack("overrides/golden_swet_ball/DAGoldenSwetBallAetherGenesisFixData", "Deep Aether Golden Swet Ball Aether Genesis Fix", event);
			else if (ModList.get().isLoaded(AETHER_REDUX))
				setupCompatPack("overrides/golden_swet_ball/DAGoldenSwetBallAetherReduxFixData", "Deep Aether Golden Swet Ball Aether Redux Fix", event);

			if (ModList.get().isLoaded(LOST_AETHER_CONTENT))
				setupCompatPack("compat_recipes/aether_lost_content_compat", "Lost Aether Content Compat", event);
			else setupCompatPack("compat_recipes/aether_lost_content_not_compat", "Deep Aether Aerwhale Saddle Recipe", event);

			if (ModList.get().isLoaded(AETHER_REDUX))
				setupCompatPack("compat_recipes/aether_redux_compat", "Aether Redux Compat", event);

			if (ModList.get().isLoaded(ANCIENT_AETHER))
				setupCompatPack("compat_recipes/ancient_aether_compat", "Ancient Aether Compat", event);

		}
	}
	private static void setupCompatPack(String location, String name, AddPackFindersEvent event) {
		setupCompatPack(location, name, event, PackType.SERVER_DATA, PackSource.SERVER, true);
	}
	private static void setupCompatPack(String location, String name, AddPackFindersEvent event, PackType type, PackSource source, boolean force) {
		Path resourcePath = ModList.get().getModFileById(DeepAether.MODID).getFile().findResource("packs/"+location);
		Pack pack = Pack.readMetaAndCreate(new PackLocationInfo("builtin/" +location, Component.literal(name), source, Optional.empty()),
                new PathPackResources.PathResourcesSupplier(resourcePath), type, new PackSelectionConfig(force, Pack.Position.TOP, false));
		event.addRepositorySource(consumer -> consumer.accept(pack));
	}
}
