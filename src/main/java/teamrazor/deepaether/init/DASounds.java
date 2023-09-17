package teamrazor.deepaether.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DASounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DeepAetherMod.MODID);

	public static final RegistryObject<SoundEvent> NABOORU = register("item.music_disc.nabooru");
	public static final RegistryObject<SoundEvent> A_MORNING_WISH = register( "item.music_disc.a_morning_wish");

	public static final RegistryObject<SoundEvent> QUAIL_DEATH = register( "entity.quail.death");

	public static final RegistryObject<SoundEvent> QUAIL_HURT = register( "entity.quail.hurt");
	public static final RegistryObject<SoundEvent> QUAIL_AMBIENT = register( "entity.quail.ambient");

	private static RegistryObject<SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(DeepAetherMod.MODID, name)));
	}
}