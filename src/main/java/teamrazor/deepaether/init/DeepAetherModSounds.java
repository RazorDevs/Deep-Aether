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

public class DeepAetherModSounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DeepAetherMod.MODID);

	public static final RegistryObject<SoundEvent> NABOORU = register("music_disc_nabooru");
	public static final RegistryObject<SoundEvent> A_MORNING_WISH = register( "music_disc_a_morning_wish");


	private static RegistryObject<SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DeepAetherMod.MODID, name)));
	}
}