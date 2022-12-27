package teamrazor.deepaether.init;

import net.minecraft.sounds.SoundEvents;
import com.gildedgames.aether.client.AetherSoundEvents;
import com.gildedgames.aether.item.accessories.ring.RingItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamrazor.deepaether.item.*;
import teamrazor.deepaether.DeepAetherMod;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.item.DeepAetherModBoatItem;
import teamrazor.deepaether.item.gear.cloudium.*;

import teamrazor.deepaether.item.gear.DeepAetherGlovesItem;
import teamrazor.deepaether.item.gear.skyjade.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class DeepAetherModItems {
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, DeepAetherMod.MODID);

	// SIGNS
	public static final RegistryObject<Item> ROSEROOT_SIGN = ITEMS.register("roseroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DeepAetherModBlocks.ROSEROOT_SIGN.get(), DeepAetherModBlocks.ROSEROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> YAGROOT_SIGN = ITEMS.register("yagroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DeepAetherModBlocks.YAGROOT_SIGN.get(), DeepAetherModBlocks.YAGROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> CRUDEROOT_SIGN = ITEMS.register("cruderoot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DeepAetherModBlocks.CRUDEROOT_SIGN.get(), DeepAetherModBlocks.CRUDEROOT_WALL_SIGN.get()));


	// EQUIPMENT
	public static final RegistryObject<Item> SKYJADE_TOOLS_SWORD = ITEMS.register("skyjade_sword", () -> new SkyjadeToolsSwordItem());
	public static final RegistryObject<Item> SKYJADE_TOOLS_PICKAXE = ITEMS.register("skyjade_pickaxe", () -> new SkyjadeToolsPickaxeItem(DeepAetherModTiers.SKYJADE, 1, -3f, new Item.Properties()));
	public static final RegistryObject<Item> SKYJADE_TOOLS_AXE = ITEMS.register("skyjade_axe", () -> new SkyjadeToolsAxeItem(DeepAetherModTiers.SKYJADE, 1, -3f, new Item.Properties()));
	public static final RegistryObject<Item> SKYJADE_TOOLS_SHOVEL = ITEMS.register("skyjade_shovel", () -> new SkyjadeToolsShovelItem(DeepAetherModTiers.SKYJADE, 1, -3f, new Item.Properties()));
	public static final RegistryObject<Item> SKYJADE_TOOLS_HOE = ITEMS.register("skyjade_hoe", () -> new SkyjadeToolsHoeItem(DeepAetherModTiers.SKYJADE, 0, -3f, new Item.Properties()));

	public static final RegistryObject<Item> SKYJADE_ARMOR_HELMET = ITEMS.register("skyjade_armor_helmet", () -> new SkyjadeArmorItem.Helmet());
	public static final RegistryObject<Item> SKYJADE_ARMOR_CHESTPLATE = ITEMS.register("skyjade_armor_chestplate", () -> new SkyjadeArmorItem.Chestplate());
	public static final RegistryObject<Item> SKYJADE_ARMOR_LEGGINGS = ITEMS.register("skyjade_armor_leggings", () -> new SkyjadeArmorItem.Leggings());
	public static final RegistryObject<Item> SKYJADE_ARMOR_BOOTS = ITEMS.register("skyjade_armor_boots", () -> new SkyjadeArmorItem.Boots());
	public static final RegistryObject<Item> SKYJADE_GLOVES = ITEMS.register("skyjade_gloves", () -> new DeepAetherGlovesItem(0.75, "skyjade_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().defaultDurability(2031)));
	public static final RegistryObject<Item> SKYJADE_RING = ITEMS.register("skyjade_ring", () -> new RingItem(AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ZANITE_RING, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> CLOUDIUM_HELMET = ITEMS.register("cloudium_helmet", () -> new CloudiumAbility(CloudiumArmorItem.CLOUDIUM, EquipmentSlot.HEAD, new Item.Properties()));
	public static final RegistryObject<Item> CLOUDIUM_CHESTPLATE = ITEMS.register("cloudium_chestplate", () -> new ArmorItem(CloudiumArmorItem.CLOUDIUM, EquipmentSlot.CHEST, new Item.Properties()));
	public static final RegistryObject<Item> CLOUDIUM_LEGGINGS = ITEMS.register("cloudium_leggings", () -> new ArmorItem(CloudiumArmorItem.CLOUDIUM, EquipmentSlot.LEGS, new Item.Properties()));
	public static final RegistryObject<Item> CLOUDIUM_BOOTS = ITEMS.register("cloudium_boots", () -> new ArmorItem(CloudiumArmorItem.CLOUDIUM, EquipmentSlot.FEET, new Item.Properties()));
	public static final RegistryObject<Item> CLOUDIUM_GLOVES = ITEMS.register("cloudium_gloves", () -> new DeepAetherGlovesItem(1.0, "cloudium_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().defaultDurability(2031)));
	public static final RegistryObject<Item> CLOUDIUM_RING = ITEMS.register("cloudium_ring", () -> new CloudiumRing(new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> CLOUDIUM_SWORD = ITEMS.register("cloudium_sword", () -> new CloudiumSwordItem(Tiers.NETHERITE, 3, -2.4F, (new Item.Properties())));
	public static final RegistryObject<Item> CLOUDIUM_SHOVEL = ITEMS.register("cloudium_shovel", () -> new CloudiumShovelItem(Tiers.NETHERITE, 1.5F, -3.0F, (new Item.Properties())));
	public static final RegistryObject<Item> CLOUDIUM_PICKAXE = ITEMS.register("cloudium_pickaxe", () -> new CloudiumPickaxeItem(Tiers.NETHERITE, 1, -2.8F, (new Item.Properties())));
	public static final RegistryObject<Item> CLOUDIUM_AXE = ITEMS.register("cloudium_axe", () -> new CloudiumAxeItem(Tiers.NETHERITE, 5.0F, -3.0F, (new Item.Properties())));
	public static final RegistryObject<Item> CLOUDIUM_HOE = ITEMS.register("cloudium_hoe", () -> new CloudiumHoeItem(Tiers.NETHERITE, -4, 0.0F, (new Item.Properties())));
	// ORES
	public static final RegistryObject<Item> SKYJADE = ITEMS.register("skyjade", () -> new SkyjadeItem());
	public static final RegistryObject<Item> ADIBIUM_GEMSTONE = ITEMS.register("adibium_gemstone", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> AGATE_GEMSTONE = ITEMS.register("agate_gemstone", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CLOUDIUM_INGOT = ITEMS.register("cloudium_ingot", () -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> CLOUDIUM_SCRAP = ITEMS.register("cloudium_scrap", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> RAW_ORATIE = ITEMS.register("raw_oratie", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ORATIE_INGOT = ITEMS.register("oratie_ingot", () -> new Item(new Item.Properties()));

	// FOOD
	public static final RegistryObject<Item> RAW_QUAIL = ITEMS.register("raw_quail", () -> new Item(new Item.Properties().food(Foods.CHICKEN)));
	public static final RegistryObject<Item> COOKED_QUAIL = ITEMS.register("cooked_quail", () -> new Item(new Item.Properties().food(Foods.COOKED_CHICKEN)));
	public static final RegistryObject<Item> RAW_AERGLOW_FISH = ITEMS.register("raw_aerglow_fish", () -> new Item(new Item.Properties().food(Foods.COD)));
	public static final RegistryObject<Item> COOKED_AERGLOW_FISH = ITEMS.register("cooked_aerglow_fish", () -> new Item(new Item.Properties().food(Foods.COOKED_COD)));

	//BOATS
	public static final RegistryObject<Item> ROSEROOT_BOAT = ITEMS.register("roseroot_boat", () -> new DeepAetherModBoatItem(false, new Item.Properties().stacksTo(1), DeepAetherModWoodTypes.ROSEROOT));
	public static final RegistryObject<Item> ROSEROOT_CHEST_BOAT = ITEMS.register("roseroot_chest_boat", () -> new DeepAetherModBoatItem(true, new Item.Properties().stacksTo(1), DeepAetherModWoodTypes.ROSEROOT));
	public static final RegistryObject<Item> YAGROOT_BOAT = ITEMS.register("yagroot_boat", () -> new DeepAetherModBoatItem(false, new Item.Properties().stacksTo(1), DeepAetherModWoodTypes.YAGROOT));
	public static final RegistryObject<Item> YAGROOT_CHEST_BOAT = ITEMS.register("yagroot_chest_boat", () -> new DeepAetherModBoatItem(true, new Item.Properties().stacksTo(1), DeepAetherModWoodTypes.YAGROOT));

	public static final RegistryObject<Item> CRUDEROOT_BOAT = ITEMS.register("cruderoot_boat", () -> new DeepAetherModBoatItem(false, new Item.Properties().stacksTo(1), DeepAetherModWoodTypes.CRUDEROOT));
	public static final RegistryObject<Item> CRUDEROOT_CHEST_BOAT = ITEMS.register("cruderoot_chest_boat", () -> new DeepAetherModBoatItem(true, new Item.Properties().stacksTo(1), DeepAetherModWoodTypes.CRUDEROOT));

	// MISC
	/*public static final RegistryObject<Item> AERGLOW_FISH_EGG = ITEMS.register("aether_fish_spawn_egg",
			() -> new ForgeSpawnEggItem(DeepAetherModEntities.AETHER_FISH, 33323, 42424, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));
	public static final RegistryObject<Item> QUAIL_EGG = ITEMS.register("quail_spawn_egg",
			() -> new ForgeSpawnEggItem(DeepAetherModEntities.QUAIL,24433,32114, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));*/

	public static final RegistryObject<Item> MUSIC_DISC_NABOORU = ITEMS.register("music_disc_nabooru", () -> new RecordItem(0, DeepAetherModSounds.NABOORU.get(),
			new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 100));
	public static final RegistryObject<Item> MUSIC_DISC_A_MORNING_WISH = ITEMS.register("music_disc_a_morning_wish", () -> new RecordItem(0, DeepAetherModSounds.A_MORNING_WISH.get(),
			new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 100));

	public static final RegistryObject<Item> PLACEABLE_POISON_BUCKET = ITEMS.register("poison_bucket",
			() -> new BucketItem(DeepAetherModFluids.POISON_FLUID, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> VIRULENT_QUICKSAND_BUCKET = ITEMS.register("virulent_quicksand_bucket",
			() -> new SolidBucketItem(DeepAetherModBlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> SKYROOT_VIRULENT_QUICKSAND_BUCKET = ITEMS.register("skyroot_virulent_quicksand_bucket",
			() -> new SolidBucketItem(DeepAetherModBlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> AERGLOW_PETAL = ITEMS.register("aerglow_petal", () -> new AerglowPetalItem());

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
