package teamrazor.deepaether.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;

public class DASounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DeepAether.MODID);

	public static final RegistryObject<SoundEvent> NABOORU = register("item.music_disc.nabooru");
	public static final RegistryObject<SoundEvent> A_MORNING_WISH = register("item.music_disc.a_morning_wish");

	public static final RegistryObject<SoundEvent> CYCLONE = register("item.music_disc.cyclone");
	public static final RegistryObject<SoundEvent> ABOVE_THE_RAIN = register("item.music_disc.above_the_rain");

	public static final RegistryObject<SoundEvent> ATTA = register("item.music_disc.atta");
	public static final RegistryObject<SoundEvent> FAENT = register("item.music_disc.faent");
	public static final RegistryObject<SoundEvent> HIMININN = register("item.music_disc.himininn");

	public static final RegistryObject<SoundEvent> LOCUS_FOR_WINDS = register("item.music_disc.locus_for_winds");


	public static final RegistryObject<SoundEvent> QUAIL_DEATH = register("entity.quail.death");
	public static final RegistryObject<SoundEvent> QUAIL_HURT = register("entity.quail.hurt");
	public static final RegistryObject<SoundEvent> QUAIL_AMBIENT = register("entity.quail.ambient");

	public static final RegistryObject<SoundEvent> VENOMITE_DEATH = register("entity.venomite.death");
	public static final RegistryObject<SoundEvent> VENOMITE_HURT = register("entity.venomite.hurt");
	public static final RegistryObject<SoundEvent> VENOMITE_AMBIENT = register("entity.venomite.ambient");

	public static final RegistryObject<SoundEvent> WINDFLY_HURT = register("entity.windfly.hurt");
	public static final RegistryObject<SoundEvent> WINDFLY_AMBIENT = register("entity.windfly.ambient");

	public static final RegistryObject<SoundEvent> EOTS_DEATH = register("entity.eots.death");
	public static final RegistryObject<SoundEvent> EOTS_HURT = register("entity.eots.hurt");
	public static final RegistryObject<SoundEvent> EOTS_AMBIENT = register("entity.eots.ambient");
	public static final RegistryObject<SoundEvent> EOTS_BLOWING = register("entity.eots.blowing");
	public static final RegistryObject<SoundEvent> EOTS_SHOOT = register("entity.eots.shoot");
	public static final RegistryObject<SoundEvent> MUSIC_BOSS_EOTS = register("music.boss.eots");

	public static final RegistryObject<SoundEvent> ITEM_ARMOR_EQUIP_SKYJADE = register("item.armor.equip_skyjade");
	public static final RegistryObject<SoundEvent> ITEM_ARMOR_EQUIP_STRATUS = register("item.armor.equip_stratus");
	public static final RegistryObject<SoundEvent> ITEM_ACCESSORY_EQUIP_SKYJADE_RING = register("item.accessory.equip_skyjade_ring");
	public static final RegistryObject<SoundEvent> ITEM_ACCESSORY_EQUIP_GRAVITITE_RING = register("item.accessory.equip_gravitite_ring");
	public static final RegistryObject<SoundEvent> ITEM_ACCESSORY_EQUIP_STRATUS_RING = register("item.accessory.equip_stratus_ring");
	public static final RegistryObject<SoundEvent> ITEM_ACCESSORY_EQUIP_SPOOKY_RING = register("item.accessory.equip_spooky_ring");
	public static final RegistryObject<SoundEvent> ITEM_ACCESSORY_EQUIP_SLIDER_EYE = register("item.accessory.equip_slider_eye");
	public static final RegistryObject<SoundEvent> ITEM_ACCESSORY_ABILITY_SLIDER_EYE = register("item.accessory.ability_slider_eye");
	public static final RegistryObject<SoundEvent> ITEM_ACCESSORY_EQUIP_MEDAL_OF_HONOR = register("item.accessory.equip_medal_of_honor");
	public static final RegistryObject<SoundEvent> ITEM_AFTERBURNER_FIRES = register("item.tool.afterburner_fires");
	public static final RegistryObject<SoundEvent> ITEM_ARMOR_EQUIP_STORMFORGED = register("item.armor.equip_stormforged");
	public static final RegistryObject<SoundEvent> EQUIP_FLOATY_SCARF = register("item.armor.equip_floaty_scarf");

	public static final RegistryObject<SoundEvent> DEEP_AETHER_MUSIC = register("music.deep_aether");

	private static RegistryObject<SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(DeepAether.getResource( name)));
	}
}