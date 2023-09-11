package teamrazor.deepaether.init;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootBucketItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.DABoatEntity;
import teamrazor.deepaether.item.gear.DAGlovesItem;
import teamrazor.deepaether.item.gear.DaArmorItem;
import teamrazor.deepaether.item.gear.DaArmorMaterials;
import teamrazor.deepaether.item.gear.skyjade.*;
import teamrazor.deepaether.item.gear.stratus.*;
import teamrazor.deepaether.item.misc.*;
import teamrazor.deepaether.item.mods.lost_content.LCDAShieldItem;
import teamrazor.deepaether.item.mods.lost_content.SkyjadeShieldItem;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class DAItems {
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, DeepAetherMod.MODID);

	// SIGNS
	public static final RegistryObject<Item> ROSEROOT_SIGN = ITEMS.register("roseroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.ROSEROOT_SIGN.get(), DABlocks.ROSEROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> YAGROOT_SIGN = ITEMS.register("yagroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.YAGROOT_SIGN.get(), DABlocks.YAGROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> CRUDEROOT_SIGN = ITEMS.register("cruderoot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.CRUDEROOT_SIGN.get(), DABlocks.CRUDEROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> CONBERRY_SIGN = ITEMS.register("conberry_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.CONBERRY_SIGN.get(), DABlocks.CONBERRY_WALL_SIGN.get()));
	public static final RegistryObject<Item> HOLYROOT_SIGN = ITEMS.register("holyroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.HOLYROOT_SIGN.get(), DABlocks.HOLYROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> SUNROOT_SIGN = ITEMS.register("sunroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.SUNROOT_SIGN.get(), DABlocks.SUNROOT_WALL_SIGN.get()));


	// EQUIPMENT
	public static final RegistryObject<Item> SKYJADE_TOOLS_SWORD = ITEMS.register("skyjade_sword", () -> new SkyjadeToolsSwordItem());
	public static final RegistryObject<Item> SKYJADE_TOOLS_PICKAXE = ITEMS.register("skyjade_pickaxe", () -> new SkyjadeToolsPickaxeItem(DATiers.SKYJADE, 1, -3f, new Item.Properties()));
	public static final RegistryObject<Item> SKYJADE_TOOLS_AXE = ITEMS.register("skyjade_axe", () -> new SkyjadeToolsAxeItem(DATiers.SKYJADE, 1, -3f, new Item.Properties()));
	public static final RegistryObject<Item> SKYJADE_TOOLS_SHOVEL = ITEMS.register("skyjade_shovel", () -> new SkyjadeToolsShovelItem(DATiers.SKYJADE, 1, -3f, new Item.Properties()));
	public static final RegistryObject<Item> SKYJADE_TOOLS_HOE = ITEMS.register("skyjade_hoe", () -> new SkyjadeToolsHoeItem(DATiers.SKYJADE, 0, -3f, new Item.Properties()));

	public static final RegistryObject<Item> SKYJADE_ARMOR_HELMET = ITEMS.register("skyjade_helmet", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final RegistryObject<Item> SKYJADE_ARMOR_CHESTPLATE = ITEMS.register("skyjade_chestplate", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final RegistryObject<Item> SKYJADE_ARMOR_LEGGINGS = ITEMS.register("skyjade_leggings", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final RegistryObject<Item> SKYJADE_ARMOR_BOOTS = ITEMS.register("skyjade_boots", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, ArmorItem.Type.BOOTS, new Item.Properties()));
	public static final RegistryObject<Item> SKYJADE_GLOVES = ITEMS.register("skyjade_gloves", () -> new SkyjadeGlovesItem(0.5, new Item.Properties().defaultDurability(75)));
	public static final RegistryObject<Item> SKYJADE_RING = ITEMS.register("skyjade_ring", () -> new SkyjadeRingItem(new Item.Properties().stacksTo(1).durability(30)));

	public static final RegistryObject<Item> GRAVITIE_RING = ITEMS.register("gravitite_ring", () -> new RingItem(AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ZANITE_RING, new Item.Properties().stacksTo(1).durability(50)));
	public static final RegistryObject<Item> STRATUS_HELMET = ITEMS.register("stratus_helmet", () -> new StratusAbility(DaArmorMaterials.STRATUS, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final RegistryObject<Item> STRATUS_CHESTPLATE = ITEMS.register("stratus_chestplate", () -> new DaArmorItem(DaArmorMaterials.STRATUS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final RegistryObject<Item> STRATUS_LEGGINGS = ITEMS.register("stratus_leggings", () -> new DaArmorItem(DaArmorMaterials.STRATUS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final RegistryObject<Item> STRATUS_BOOTS = ITEMS.register("stratus_boots", () -> new DaArmorItem(DaArmorMaterials.STRATUS, ArmorItem.Type.BOOTS, new Item.Properties()));
	public static final RegistryObject<Item> STRATUS_GLOVES = ITEMS.register("stratus_gloves", () -> new DAGlovesItem(1.0, "stratus_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().defaultDurability(2031)));
	public static final RegistryObject<Item> STRATUS_RING = ITEMS.register("stratus_ring", () -> new RingItem(AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().stacksTo(1).durability(100)));

	public static final RegistryObject<Item> STRATUS_SWORD = ITEMS.register("stratus_sword", () -> new StratusSwordItem(DATiers.STRATUS, 3, -2.4F, (new Item.Properties())));
	public static final RegistryObject<Item> STRATUS_SHOVEL = ITEMS.register("stratus_shovel", () -> new StratusShovelItem(DATiers.STRATUS, 1.5F, -3.0F, (new Item.Properties())));
	public static final RegistryObject<Item> STRATUS_PICKAXE = ITEMS.register("stratus_pickaxe", () -> new StratusPickaxeItem(DATiers.STRATUS, 1, -2.8F, (new Item.Properties())));
	public static final RegistryObject<Item> STRATUS_AXE = ITEMS.register("stratus_axe", () -> new StratusAxeItem(DATiers.STRATUS, 5.0F, -3.0F, (new Item.Properties())));
	public static final RegistryObject<Item> STRATUS_HOE = ITEMS.register("stratus_hoe", () -> new StratusHoeItem(DATiers.STRATUS, -4, 0.0F, (new Item.Properties())));
	// ORES
	public static final RegistryObject<Item> SKYJADE = ITEMS.register("skyjade", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> STRATUS_INGOT = ITEMS.register("stratus_ingot", () -> new Item(new Item.Properties()));

	// FOOD
	public static final RegistryObject<Item> RAW_QUAIL = ITEMS.register("raw_quail", () -> new Item(new Item.Properties().food(Foods.CHICKEN)));
	public static final RegistryObject<Item> COOKED_QUAIL = ITEMS.register("cooked_quail", () -> new Item(new Item.Properties().food(Foods.COOKED_CHICKEN)));
	public static final RegistryObject<Item> RAW_AERGLOW_FISH = ITEMS.register("raw_aerglow_fish", () -> new Item(new Item.Properties().food(Foods.COD)));
	public static final RegistryObject<Item> COOKED_AERGLOW_FISH = ITEMS.register("cooked_aerglow_fish", () -> new Item(new Item.Properties().food(Foods.COOKED_COD)));

	public static final RegistryObject<Item> SKYROOT_AERGLOW_FISH_BUCKET = ITEMS.register("skyroot_aerglow_fish_bucket", () ->  new DASkyrootBucketItem(DAEntities.AERGLOW_FISH, (new Item.Properties()).craftRemainder(AetherItems.SKYROOT_BUCKET.get()).stacksTo(1)));
	public static final RegistryObject<Item> AERGLOW_FISH_BUCKET = ITEMS.register("aerglow_fish_bucket", () -> new DABucketItem(DAEntities.AERGLOW_FISH, (new Item.Properties()).stacksTo(1)));



	//BOATS
	public static final RegistryObject<Item> ROSEROOT_BOAT = ITEMS.register("roseroot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.ROSEROOT));
	public static final RegistryObject<Item> ROSEROOT_CHEST_BOAT = ITEMS.register("roseroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1),  DABoatEntity.Type.ROSEROOT));
	public static final RegistryObject<Item> YAGROOT_BOAT = ITEMS.register("yagroot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.YAGROOT));
	public static final RegistryObject<Item> YAGROOT_CHEST_BOAT = ITEMS.register("yagroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.YAGROOT));

	public static final RegistryObject<Item> CRUDEROOT_BOAT = ITEMS.register("cruderoot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.CRUDEROOT));
	public static final RegistryObject<Item> CRUDEROOT_CHEST_BOAT = ITEMS.register("cruderoot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.CRUDEROOT));

	public static final RegistryObject<Item> CONBERRY_BOAT = ITEMS.register("conberry_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.CONBERRY));
	public static final RegistryObject<Item> CONBERRY_CHEST_BOAT = ITEMS.register("conberry_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.CONBERRY));
	public static final RegistryObject<Item> HOLYROOT_BOAT = ITEMS.register("holyroot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.HOLYROOT));
	public static final RegistryObject<Item> HOLYROOT_CHEST_BOAT = ITEMS.register("holyroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.HOLYROOT));
	public static final RegistryObject<Item> SUNROOT_BOAT = ITEMS.register("sunroot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.SUNROOT));
	public static final RegistryObject<Item> SUNROOT_CHEST_BOAT = ITEMS.register("sunroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.SUNROOT));

	// MISC
	public static final RegistryObject<Item> AERGLOW_FISH_SPAWN_EGG = ITEMS.register("aerglow_fish_spawn_egg",
			() -> new ForgeSpawnEggItem(DAEntities.AERGLOW_FISH, 698060, 16776960, new Item.Properties()));

	public static final RegistryObject<Item> QUAIL_SPAWN_EGG = ITEMS.register("quail_spawn_egg",
			() -> new ForgeSpawnEggItem(DAEntities.QUAIL,6373632,16776960, new Item.Properties()));

	public static final RegistryObject<Item> QUAIL_EGG = ITEMS.register("quail_egg",
			() -> new QuailEggItem(new Item.Properties().stacksTo(16)));

	public static final RegistryObject<Item> MUSIC_DISC_NABOORU = ITEMS.register("music_disc_nabooru",
			() -> new RecordItem(7, DASounds.NABOORU, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 7280));
	public static final RegistryObject<Item> MUSIC_DISC_A_MORNING_WISH = ITEMS.register("music_disc_a_morning_wish",
			() -> new RecordItem(8, DASounds.A_MORNING_WISH, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 5660));

	public static final RegistryObject<Item> PLACEABLE_POISON_BUCKET = ITEMS.register("poison_bucket",
			() -> new DrinkableBucketItem(DAFluids.POISON_FLUID, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> VIRULENT_QUICKSAND_BUCKET = ITEMS.register("virulent_quicksand_bucket",
			() -> new SolidBucketItem(DABlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> SKYROOT_VIRULENT_QUICKSAND_BUCKET = ITEMS.register("skyroot_virulent_quicksand_bucket",
			() -> new SolidBucketItem(DABlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> AERGLOW_PETAL = ITEMS.register("aerglow_petal", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_BERRIES = ITEMS.register("goldenleaf_berries",()-> new ItemNameBlockItem(DABlocks.GOLDEN_VINES.get(), (new Item.Properties()).food(DAFoods.GOLDEN_BERRIES)));
	public static final RegistryObject<Item> GOLDEN_GRASS_SEEDS = ITEMS.register("golden_grass_seeds",()-> new Item(new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_SWET_BALL = ITEMS.register("golden_swet_ball",()-> new Item(new Item.Properties()));

	public static final RegistryObject<Item> ADIBIUM_GEMSTONE = ITEMS.register("adibium_gemstone", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> AGATE_GEMSTONE = ITEMS.register("agate_gemstone", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> RAW_PURPITE = ITEMS.register("raw_purpite", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> PURPITE_INGOT = ITEMS.register("purpite_ingot", () -> new Item(new Item.Properties()));

	//LOST CONTENT

	public static final RegistryObject<Item> SKYJADE_SHIELD = registerLostContentItem("skyjade_shield", () -> new SkyjadeShieldItem(new Item.Properties().durability(672)));
	public static final RegistryObject<Item> STRATUS_SHIELD = registerLostContentItem("stratus_shield", () -> new LCDAShieldItem(new Item.Properties().durability(1344)));

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}


	public static void setupBucketReplacements() {
		SkyrootBucketItem.REPLACEMENTS.put(DAItems.AERGLOW_FISH_BUCKET, DAItems.SKYROOT_AERGLOW_FISH_BUCKET);
	}

	private static <T extends Item> RegistryObject<T> registerLostContentItem(String name, Supplier<T> item) {
		if(ModList.get().isLoaded(DeepAetherMod.LOST_AETHER_CONTENT)) {
			return ITEMS.register(name, item);
		}
		return null;
	}
}
