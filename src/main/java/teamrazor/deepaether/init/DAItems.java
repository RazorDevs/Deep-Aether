package teamrazor.deepaether.init;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.aether.item.miscellaneous.AetherRecordItem;
import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootBucketItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.DABoatEntity;
import teamrazor.deepaether.item.gear.DAGlovesItem;
import teamrazor.deepaether.item.gear.DaArmorItem;
import teamrazor.deepaether.item.gear.DaArmorMaterials;
import teamrazor.deepaether.item.gear.cloudium.*;
import teamrazor.deepaether.item.gear.skyjade.*;
import teamrazor.deepaether.item.misc.*;
import teamrazor.deepaether.item.mods.lost_content.LCDAShieldItem;
import teamrazor.deepaether.item.mods.lost_content.SkyjadeShieldItem;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class DAItems {
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, DeepAetherMod.MODID);

	// SIGNS
	public static final RegistryObject<Item> ROSEROOT_SIGN = ITEMS.register("roseroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.ROSEROOT_SIGN.get(), DABlocks.ROSEROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> YAGROOT_SIGN = ITEMS.register("yagroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.YAGROOT_SIGN.get(), DABlocks.YAGROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> CRUDEROOT_SIGN = ITEMS.register("cruderoot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.CRUDEROOT_SIGN.get(), DABlocks.CRUDEROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> CONBERRY_SIGN = ITEMS.register("conberry_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.CONBERRY_SIGN.get(), DABlocks.CONBERRY_WALL_SIGN.get()));


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
	public static final RegistryObject<Item> CLOUDIUM_HELMET = ITEMS.register("cloudium_helmet", () -> new CloudiumAbility(DaArmorMaterials.CLOUDIUM, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final RegistryObject<Item> CLOUDIUM_CHESTPLATE = ITEMS.register("cloudium_chestplate", () -> new DaArmorItem(DaArmorMaterials.CLOUDIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final RegistryObject<Item> CLOUDIUM_LEGGINGS = ITEMS.register("cloudium_leggings", () -> new DaArmorItem(DaArmorMaterials.CLOUDIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final RegistryObject<Item> CLOUDIUM_BOOTS = ITEMS.register("cloudium_boots", () -> new DaArmorItem(DaArmorMaterials.CLOUDIUM, ArmorItem.Type.BOOTS, new Item.Properties()));
	public static final RegistryObject<Item> CLOUDIUM_GLOVES = ITEMS.register("cloudium_gloves", () -> new DAGlovesItem(1.0, "cloudium_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().defaultDurability(2031)));
	public static final RegistryObject<Item> CLOUDIUM_RING = ITEMS.register("cloudium_ring", () -> new RingItem(AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().stacksTo(1).durability(100)));

	public static final RegistryObject<Item> CLOUDIUM_SWORD = ITEMS.register("cloudium_sword", () -> new CloudiumSwordItem(DATiers.CLOUDIUM, 3, -2.4F, (new Item.Properties())));
	public static final RegistryObject<Item> CLOUDIUM_SHOVEL = ITEMS.register("cloudium_shovel", () -> new CloudiumShovelItem(DATiers.CLOUDIUM, 1.5F, -3.0F, (new Item.Properties())));
	public static final RegistryObject<Item> CLOUDIUM_PICKAXE = ITEMS.register("cloudium_pickaxe", () -> new CloudiumPickaxeItem(DATiers.CLOUDIUM, 1, -2.8F, (new Item.Properties())));
	public static final RegistryObject<Item> CLOUDIUM_AXE = ITEMS.register("cloudium_axe", () -> new CloudiumAxeItem(DATiers.CLOUDIUM, 5.0F, -3.0F, (new Item.Properties())));
	public static final RegistryObject<Item> CLOUDIUM_HOE = ITEMS.register("cloudium_hoe", () -> new CloudiumHoeItem(DATiers.CLOUDIUM, -4, 0.0F, (new Item.Properties())));
	// ORES
	public static final RegistryObject<Item> SKYJADE = ITEMS.register("skyjade", () -> new SkyjadeItem());
	public static final RegistryObject<Item> CLOUDIUM_INGOT = ITEMS.register("cloudium_ingot", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CLOUDIUM_SCRAP = ITEMS.register("cloudium_scrap", () -> new Item(new Item.Properties()));

	// FOOD
	public static final RegistryObject<Item> RAW_QUAIL = ITEMS.register("raw_quail", () -> new Item(new Item.Properties().food(Foods.CHICKEN)));
	public static final RegistryObject<Item> COOKED_QUAIL = ITEMS.register("cooked_quail", () -> new Item(new Item.Properties().food(Foods.COOKED_CHICKEN)));
	public static final RegistryObject<Item> RAW_AERGLOW_FISH = ITEMS.register("raw_aerglow_fish", () -> new Item(new Item.Properties().food(Foods.COD)));
	public static final RegistryObject<Item> COOKED_AERGLOW_FISH = ITEMS.register("cooked_aerglow_fish", () -> new Item(new Item.Properties().food(Foods.COOKED_COD)));

	public static final RegistryObject<Item> SKYROOT_AERGLOW_FISH_BUCKET = ITEMS.register("skyroot_aerglow_fish_bucket", () ->  new DASkyrootBucketItem(DAEntities.AETHER_FISH, (new Item.Properties()).craftRemainder(AetherItems.SKYROOT_BUCKET.get()).stacksTo(1)));
	public static final RegistryObject<Item> AERGLOW_FISH_BUCKET = ITEMS.register("aerglow_fish_bucket", () -> new DABucketItem(DAEntities.AETHER_FISH, (new Item.Properties()).stacksTo(1)));



	//BOATS
	public static final RegistryObject<Item> ROSEROOT_BOAT = ITEMS.register("roseroot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.ROSEROOT));
	public static final RegistryObject<Item> ROSEROOT_CHEST_BOAT = ITEMS.register("roseroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1),  DABoatEntity.Type.ROSEROOT));
	public static final RegistryObject<Item> YAGROOT_BOAT = ITEMS.register("yagroot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.YAGROOT));
	public static final RegistryObject<Item> YAGROOT_CHEST_BOAT = ITEMS.register("yagroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.YAGROOT));

	public static final RegistryObject<Item> CRUDEROOT_BOAT = ITEMS.register("cruderoot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.CRUDEROOT));
	public static final RegistryObject<Item> CRUDEROOT_CHEST_BOAT = ITEMS.register("cruderoot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.CRUDEROOT));

	public static final RegistryObject<Item> CONBERRY_BOAT = ITEMS.register("conberry_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.CONBERRY));
	public static final RegistryObject<Item> CONBERRY_CHEST_BOAT = ITEMS.register("conberry_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.CONBERRY));

	// MISC
	public static final RegistryObject<Item> AETHER_FISH_SPAWN_EGG = ITEMS.register("aether_fish_spawn_egg",
			() -> new ForgeSpawnEggItem(DAEntities.AETHER_FISH, 698060, 16776960, new Item.Properties()));

	public static final RegistryObject<Item> QUAIL_SPAWN_EGG = ITEMS.register("quail_spawn_egg",
			() -> new ForgeSpawnEggItem(DAEntities.QUAIL,6373632,16776960, new Item.Properties()));

	public static final RegistryObject<Item> QUAIL_EGG = ITEMS.register("quail_egg",
			() -> new QuailEggItem(new Item.Properties().stacksTo(16)));

	public static final RegistryObject<Item> MUSIC_DISC_NABOORU = ITEMS.register("music_disc_nabooru",
			() -> new AetherRecordItem(7, DASounds.NABOORU, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 7280));
	public static final RegistryObject<Item> MUSIC_DISC_A_MORNING_WISH = ITEMS.register("music_disc_a_morning_wish",
			() -> new AetherRecordItem(8, DASounds.A_MORNING_WISH, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 5660));

	public static final RegistryObject<Item> PLACEABLE_POISON_BUCKET = ITEMS.register("poison_bucket",
			() -> new DrinkableBucketItem(DAFluids.POISON_FLUID, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> VIRULENT_QUICKSAND_BUCKET = ITEMS.register("virulent_quicksand_bucket",
			() -> new SolidBucketItem(DABlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> SKYROOT_VIRULENT_QUICKSAND_BUCKET = ITEMS.register("skyroot_virulent_quicksand_bucket",
			() -> new SolidBucketItem(DABlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> AERGLOW_PETAL = ITEMS.register("aerglow_petal", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_BERRIES = ITEMS.register("goldenleaf_berries",()-> new ItemNameBlockItem(DABlocks.GOLDEN_VINES.get(), (new Item.Properties()).food(DAFoods.GOLDEN_BERRIES)));
	public static final RegistryObject<Item> GOLDEN_GRASS_SEEDS = ITEMS.register("golden_grass_seeds",()-> new Item(new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_SWET_BALL = ITEMS.register("golden_swet_ball",()-> new GoldenSwetBall(new Item.Properties()));


	//LOST CONTENT
	public static final DeferredRegister<Item> LOST_CONTENT =
			DeferredRegister.create(ForgeRegistries.ITEMS, DeepAetherMod.MODID);

	public static final RegistryObject<Item> SKYJADE_SHIELD = LOST_CONTENT.register("skyjade_shield", () -> new SkyjadeShieldItem(new Item.Properties().durability(672)));
	public static final RegistryObject<Item> CLOUDIUM_SHIELD = LOST_CONTENT.register("cloudium_shield", () -> new LCDAShieldItem(new Item.Properties().durability(1344)));

	//AETHER REDUX
	//USed in DABlocks

	public static final DeferredRegister<Item> AETHER_REDUX =
			DeferredRegister.create(ForgeRegistries.ITEMS, DeepAetherMod.MODID);

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}


	public static void setupBucketReplacements() {
		SkyrootBucketItem.REPLACEMENTS.put(DAItems.AERGLOW_FISH_BUCKET, DAItems.SKYROOT_AERGLOW_FISH_BUCKET);
	}
}
