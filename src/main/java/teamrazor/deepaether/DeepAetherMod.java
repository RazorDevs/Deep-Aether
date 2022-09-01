package teamrazor.deepaether;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import org.slf4j.Logger;
import teamrazor.deepaether.init.*;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import teamrazor.deepaether.world.Gen.DeepAetherModBiomeModifiers;
import teamrazor.deepaether.world.feature.DeepAetherModPlacedFeatures;

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

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();



		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		DeepAetherModBlocks.REGISTRY.register(bus);
		DeepAetherModItems.REGISTRY.register(bus);
		//DeepAetherModEntities.REGISTRY.register(bus);
		//DeepAetherModBiomes.REGISTRY.register(bus);
		//DeepAetherModFluids.REGISTRY.register(bus);
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
//don't know how to fix
/*	@SubscribeEvent
	public void registerBucket(RegistryEvent.Register<Item> event) {
		//event.getRegistry().register(DeepAetherModItems.SKYROOT_POISON_BUCKET.get());
		LOGGER.info("BUCKET SHOULD BE OVERWRITTEN.");
	}*/

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
			BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}
}
