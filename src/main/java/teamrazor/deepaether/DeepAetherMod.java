package teamrazor.deepaether;

//import com.gildedgames.aether.data.generators.AetherDataGenerators;
import com.gildedgames.aether.data.generators.AetherDataGenerators;
import com.gildedgames.aether.data.generators.tags.AetherBlockTagData;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import teamrazor.deepaether.client.renderer.AetherFishRenderer;
import teamrazor.deepaether.client.renderer.QuailRenderer;
import teamrazor.deepaether.fluids.BaseFluidType;
import teamrazor.deepaether.fluids.DeepAetherModFluidTypes;
import teamrazor.deepaether.init.*;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import teamrazor.deepaether.tags.DeepAetherBiomeTagData;
import teamrazor.deepaether.tags.DeepAetherItemTagData;
import teamrazor.deepaether.tags.DeepAetherTags;
import teamrazor.deepaether.world.DeepAetherModBiomeBuilders;
import teamrazor.deepaether.world.DeepAetherModDataGenerators;
import teamrazor.deepaether.world.Gen.DeepAetherModBiomeModifiers;
import teamrazor.deepaether.world.feature.DeepAetherModPlacedFeatures;
import teamrazor.deepaether.world.feature.tree.decorators.DeepAetherDecoratorType;
import teamrazor.deepaether.world.feature.tree.decorators.FlowerBlobFoliagePlacer;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Mod("deep_aether")
public class DeepAetherMod {
	private static final Logger LOGGER = LogUtils.getLogger();

	public static final String MODID = "deep_aether";
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public DeepAetherMod() {
		DeepAetherModTabs.load();
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dataSetup);


		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();



		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		GeckoLib.initialize();
		DeepAetherModBlocks.REGISTRY.register(bus);
		DeepAetherModItems.REGISTRY.register(bus);
		DeepAetherModEntities.REGISTRY.register(bus);
		DeepAetherModSounds.REGISTRY.register(bus);
		//DeepAetherModBiomes.REGISTRY.register(bus);
		DeepAetherModFluids.register(bus);
		DeepAetherModFluidTypes.register(bus);
		DeepAetherDecoratorType.REGISTRY.register(bus);
		FlowerBlobFoliagePlacer.REGISTRY.register(bus);
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		DeepAetherModPlacedFeatures.register(eventBus);
		DeepAetherModBiomeModifiers.register(eventBus);
	}

	private void clientSetup(final FMLClientSetupEvent event) {
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		// some preinit code
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getName());

	}

	public void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		AetherBlockTagData blockTags = new AetherBlockTagData(generator, helper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new DeepAetherItemTagData(generator, blockTags, helper));
		// generator.addProvider(event.includeServer(), new DeepAetherBiomeTagData(generator, helper));
		//generator.addProvider(event.includeServer(), DeepAetherModDataGenerators.levelStem(generator, helper));
	}

	private void enqueueIMC(final InterModEnqueueEvent event)
	{
		// Some example code to dispatch IMC to another mod
		InterModComms.sendTo(MODID, "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
	}

	private void processIMC(final InterModProcessEvent event)
	{
		// Some example code to receive and process InterModComms from other mods
		LOGGER.info("Got IMC {}", event.getIMCStream().
				map(m->m.messageSupplier().get()).
				collect(Collectors.toList()));
	}

	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			EntityRenderers.register(DeepAetherModEntities.QUAIL.get(), QuailRenderer::new);
			EntityRenderers.register(DeepAetherModEntities.AETHER_FISH.get(), AetherFishRenderer::new);
		}
	}
}
