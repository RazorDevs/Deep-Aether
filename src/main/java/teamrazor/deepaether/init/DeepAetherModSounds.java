package teamrazor.deepaether.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepAetherModSounds {

	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DeepAetherMod.MODID);

	public static final RegistryObject<SoundEvent> NABOORU = REGISTRY.register("music_disc_nabooru", () -> new SoundEvent(new ResourceLocation("deep_aether", "music_disc_nabooru")));
	public static final RegistryObject<SoundEvent> A_MORNING_WISH = REGISTRY.register("music_disc_a_morning_wish", () -> new SoundEvent(new ResourceLocation("deep_aether", "music_disc_a_morning_wish")));

	/*@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		for (Map.Entry<ResourceLocation, SoundEvent> sound : REGISTRY.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
	}*/
}