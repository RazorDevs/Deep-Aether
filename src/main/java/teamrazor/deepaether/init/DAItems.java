package teamrazor.deepaether.init;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootBucketItem;
import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootPoisonBucketItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
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
import teamrazor.deepaether.item.gear.other.*;
import teamrazor.deepaether.item.gear.skyjade.*;
import teamrazor.deepaether.item.gear.stratus.*;
import teamrazor.deepaether.item.misc.*;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class DAItems {
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, DeepAetherMod.MODID);

	// SIGNS
	public static final RegistryObject<Item> ROSEROOT_SIGN = ITEMS.register("roseroot_sign", () -> new SignItem(new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS).stacksTo(16).tab(CreativeModeTab.TAB_MATERIALS), DABlocks.ROSEROOT_SIGN.get(), DABlocks.ROSEROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> YAGROOT_SIGN = ITEMS.register("yagroot_sign", () -> new SignItem(new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS).stacksTo(16), DABlocks.YAGROOT_SIGN.get(), DABlocks.YAGROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> CRUDEROOT_SIGN = ITEMS.register("cruderoot_sign", () -> new SignItem(new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS).stacksTo(16), DABlocks.CRUDEROOT_SIGN.get(), DABlocks.CRUDEROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> CONBERRY_SIGN = ITEMS.register("conberry_sign", () -> new SignItem(new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS).stacksTo(16), DABlocks.CONBERRY_SIGN.get(), DABlocks.CONBERRY_WALL_SIGN.get()));
	public static final RegistryObject<Item> SUNROOT_SIGN = ITEMS.register("sunroot_sign", () -> new SignItem(new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS).stacksTo(16), DABlocks.SUNROOT_SIGN.get(), DABlocks.SUNROOT_WALL_SIGN.get()));


	// EQUIPMENT

	public static final RegistryObject<Item> AFTERBURNER = ITEMS.register("afterburner", () -> new Afterburner((new Item.Properties().stacksTo(1).durability(200).tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).fireResistant())));
	public static final RegistryObject<Item> SKYJADE_SWORD = ITEMS.register("skyjade_sword", SkyjadeToolsSwordItem::new);
	public static final RegistryObject<Item> SKYJADE_PICKAXE = ITEMS.register("skyjade_pickaxe", () -> new SkyjadeToolsPickaxeItem(DATiers.SKYJADE, 1, -3f, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES)));
	public static final RegistryObject<Item> SKYJADE_AXE = ITEMS.register("skyjade_axe", () -> new SkyjadeToolsAxeItem(DATiers.SKYJADE, 1, -3f, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES)));
	public static final RegistryObject<Item> SKYJADE_SHOVEL = ITEMS.register("skyjade_shovel", () -> new SkyjadeToolsShovelItem(DATiers.SKYJADE, 1, -3f, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES)));
	public static final RegistryObject<Item> SKYJADE_HOE = ITEMS.register("skyjade_hoe", () -> new SkyjadeToolsHoeItem(DATiers.SKYJADE, 0, -3f, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES)));

	public static final RegistryObject<Item> SKYJADE_HELMET = ITEMS.register("skyjade_helmet", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, EquipmentSlot.HEAD, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES)));
	public static final RegistryObject<Item> SKYJADE_CHESTPLATE = ITEMS.register("skyjade_chestplate", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, EquipmentSlot.CHEST, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES)));
	public static final RegistryObject<Item> SKYJADE_LEGGINGS = ITEMS.register("skyjade_leggings", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, EquipmentSlot.LEGS, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES)));
	public static final RegistryObject<Item> SKYJADE_BOOTS = ITEMS.register("skyjade_boots", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, EquipmentSlot.FEET, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES)));
	public static final RegistryObject<Item> SKYJADE_GLOVES = ITEMS.register("skyjade_gloves", () -> new SkyjadeGlovesItem(0.5, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES).defaultDurability(75)));
	public static final RegistryObject<Item> SKYJADE_RING = ITEMS.register("skyjade_ring", () -> new SkyjadeRingItem(new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES).stacksTo(1).durability(30)));
	public static final RegistryObject<Item> SPOOKY_RING = ITEMS.register("spooky_ring", () -> new SpookyRing(AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().stacksTo(1).durability(500).tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES)));
	public static final RegistryObject<Item> SLIDER_EYE = ITEMS.register("slider_eye", () -> new SliderEye(AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().stacksTo(1).durability(500).rarity(AetherItems.AETHER_LOOT).fireResistant().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES)));
	public static final RegistryObject<Item> MEDAL_OF_HONOR = ITEMS.register("medal_of_honor", () -> new MedalOfHonor(new Item.Properties().stacksTo(1).rarity(AetherItems.AETHER_LOOT).fireResistant().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES)));
	public static final RegistryObject<Item> GRAVITITE_RING = ITEMS.register("gravitite_ring", () -> new RingItem(AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ZANITE_RING, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES).stacksTo(1).durability(50)));
	public static final RegistryObject<Item> STRATUS_HELMET = ITEMS.register("stratus_helmet", () -> new StratusAbility(DaArmorMaterials.STRATUS, EquipmentSlot.HEAD, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES)));
	public static final RegistryObject<Item> STRATUS_CHESTPLATE = ITEMS.register("stratus_chestplate", () -> new DaArmorItem(DaArmorMaterials.STRATUS, EquipmentSlot.CHEST, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES)));
	public static final RegistryObject<Item> STRATUS_LEGGINGS = ITEMS.register("stratus_leggings", () -> new DaArmorItem(DaArmorMaterials.STRATUS, EquipmentSlot.LEGS, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES)));
	public static final RegistryObject<Item> STRATUS_BOOTS = ITEMS.register("stratus_boots", () -> new DaArmorItem(DaArmorMaterials.STRATUS, EquipmentSlot.FEET, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES)));
	public static final RegistryObject<Item> STRATUS_GLOVES = ITEMS.register("stratus_gloves", () -> new DAGlovesItem(DaArmorMaterials.STRATUS, 1.0,"stratus_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES).defaultDurability(2031)));
	public static final RegistryObject<Item> STRATUS_RING = ITEMS.register("stratus_ring", () -> new RingItem(AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().tab(AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES).stacksTo(1).durability(100)));

	public static final RegistryObject<Item> STRATUS_SWORD = ITEMS.register("stratus_sword", () -> new StratusSwordItem(DATiers.STRATUS, 3, -2.4F, (new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES))));
	public static final RegistryObject<Item> STRATUS_SHOVEL = ITEMS.register("stratus_shovel", () -> new StratusShovelItem(DATiers.STRATUS, 1.5F, -3.0F, (new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES))));
	public static final RegistryObject<Item> STRATUS_PICKAXE = ITEMS.register("stratus_pickaxe", () -> new StratusPickaxeItem(DATiers.STRATUS, 1, -2.8F, (new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES))));
	public static final RegistryObject<Item> STRATUS_AXE = ITEMS.register("stratus_axe", () -> new StratusAxeItem(DATiers.STRATUS, 5.0F, -3.0F, (new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES))));
	public static final RegistryObject<Item> STRATUS_HOE = ITEMS.register("stratus_hoe", () -> new StratusHoeItem(DATiers.STRATUS, -4, 0.0F, (new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES))));
	// ORES
	public static final RegistryObject<Item> SKYJADE = ITEMS.register("skyjade", SkyjadeItem::new);
	public static final RegistryObject<Item> STRATUS_INGOT = ITEMS.register("stratus_ingot", () -> new Item(new Item.Properties().tab(AetherCreativeTabs.AETHER_INGREDIENTS)));

	// FOOD
	public static final RegistryObject<Item> RAW_QUAIL = ITEMS.register("raw_quail", () -> new Item(new Item.Properties().tab(AetherCreativeTabs.AETHER_FOOD_AND_DRINKS).food(Foods.CHICKEN)));
	public static final RegistryObject<Item> COOKED_QUAIL = ITEMS.register("cooked_quail", () -> new Item(new Item.Properties().tab(AetherCreativeTabs.AETHER_FOOD_AND_DRINKS).food(Foods.COOKED_CHICKEN)));
	public static final RegistryObject<Item> RAW_AERGLOW_FISH = ITEMS.register("raw_aerglow_fish", () -> new Item(new Item.Properties().tab(AetherCreativeTabs.AETHER_FOOD_AND_DRINKS).food(Foods.COD)));
	public static final RegistryObject<Item> COOKED_AERGLOW_FISH = ITEMS.register("cooked_aerglow_fish", () -> new Item(new Item.Properties().tab(AetherCreativeTabs.AETHER_FOOD_AND_DRINKS).food(Foods.COOKED_COD)));

	public static final RegistryObject<Item> SKYROOT_AERGLOW_FISH_BUCKET = ITEMS.register("skyroot_aerglow_fish_bucket", () ->  new DASkyrootBucketItem(DAEntities.AETHER_FISH, (new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES)).craftRemainder(AetherItems.SKYROOT_BUCKET.get()).stacksTo(1)));
	public static final RegistryObject<Item> AERGLOW_FISH_BUCKET = ITEMS.register("aerglow_fish_bucket", () -> new DABucketItem(DAEntities.AETHER_FISH, (new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES)).stacksTo(1)));



	//BOATS
	public static final RegistryObject<Item> ROSEROOT_BOAT = ITEMS.register("roseroot_boat", () -> new DABoatItem(false, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1), DABoatEntity.Type.ROSEROOT));
	public static final RegistryObject<Item> ROSEROOT_CHEST_BOAT = ITEMS.register("roseroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1),  DABoatEntity.Type.ROSEROOT));
	public static final RegistryObject<Item> YAGROOT_BOAT = ITEMS.register("yagroot_boat", () -> new DABoatItem(false, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1), DABoatEntity.Type.YAGROOT));
	public static final RegistryObject<Item> YAGROOT_CHEST_BOAT = ITEMS.register("yagroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1), DABoatEntity.Type.YAGROOT));

	public static final RegistryObject<Item> CRUDEROOT_BOAT = ITEMS.register("cruderoot_boat", () -> new DABoatItem(false, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1), DABoatEntity.Type.CRUDEROOT));
	public static final RegistryObject<Item> CRUDEROOT_CHEST_BOAT = ITEMS.register("cruderoot_chest_boat", () -> new DABoatItem(true, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1), DABoatEntity.Type.CRUDEROOT));

	public static final RegistryObject<Item> CONBERRY_BOAT = ITEMS.register("conberry_boat", () -> new DABoatItem(false, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1), DABoatEntity.Type.CONBERRY));
	public static final RegistryObject<Item> CONBERRY_CHEST_BOAT = ITEMS.register("conberry_chest_boat", () -> new DABoatItem(true, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1), DABoatEntity.Type.CONBERRY));
	public static final RegistryObject<Item> SUNROOT_BOAT = ITEMS.register("sunroot_boat", () -> new DABoatItem(false, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1), DABoatEntity.Type.SUNROOT));
	public static final RegistryObject<Item> SUNROOT_CHEST_BOAT = ITEMS.register("sunroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1), DABoatEntity.Type.SUNROOT));

	// MISC

	public static final RegistryObject<Item> SUN_CORE = ITEMS.register("sun_core", () -> new SunCore((new Item.Properties()).rarity(AetherItems.AETHER_LOOT).fireResistant().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES)));
	public static final RegistryObject<Item> AERWHALE_SADDLE = ITEMS.register("aerwhale_saddle", () -> new AerwhaleSaddle((new Item.Properties()).rarity(AetherItems.AETHER_LOOT).fireResistant().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES)));

	public static final RegistryObject<Item> BLUE_SQUASH_SLICE = ITEMS.register("blue_squash_slice", () -> new DASquashPieItem(new Item.Properties().food(Foods.GLOW_BERRIES).tab(AetherCreativeTabs.AETHER_FOOD_AND_DRINKS), new MobEffect[]{MobEffects.MOVEMENT_SPEED, MobEffects.NIGHT_VISION, MobEffects.DIG_SLOWDOWN}));
	public static final RegistryObject<Item> GREEN_SQUASH_SLICE = ITEMS.register("green_squash_slice", () -> new DASquashPieItem(new Item.Properties().food(Foods.GLOW_BERRIES).tab(AetherCreativeTabs.AETHER_FOOD_AND_DRINKS), new MobEffect[]{MobEffects.JUMP, MobEffects.LEVITATION, MobEffects.LUCK}));
	public static final RegistryObject<Item> PURPLE_SQUASH_SLICE = ITEMS.register("purple_squash_slice", () -> new DASquashPieItem(new Item.Properties().food(Foods.GLOW_BERRIES).tab(AetherCreativeTabs.AETHER_FOOD_AND_DRINKS), new MobEffect[]{MobEffects.LEVITATION, MobEffects.DAMAGE_RESISTANCE, AetherEffects.INEBRIATION.get()}));

	public static final RegistryObject<Item> VENOMITE_SPAWN_EGG = ITEMS.register("venomite_spawn_egg",
			() -> new ForgeSpawnEggItem(DAEntities.VENOMITE, 0,800080, new Item.Properties().tab(AetherCreativeTabs.AETHER_SPAWN_EGGS)));
	
	public static final RegistryObject<Item> AETHER_FISH_SPAWN_EGG = ITEMS.register("aether_fish_spawn_egg",
			() -> new ForgeSpawnEggItem(DAEntities.AETHER_FISH, 698060, 16776960, new Item.Properties().tab(AetherCreativeTabs.AETHER_SPAWN_EGGS)));

	public static final RegistryObject<Item> QUAIL_SPAWN_EGG = ITEMS.register("quail_spawn_egg",
			() -> new ForgeSpawnEggItem(DAEntities.QUAIL,6373632,16776960, new Item.Properties().tab(AetherCreativeTabs.AETHER_SPAWN_EGGS)));

	public static final RegistryObject<Item> QUAIL_EGG = ITEMS.register("quail_egg",
			() -> new QuailEggItem(new Item.Properties().tab(AetherCreativeTabs.AETHER_INGREDIENTS).stacksTo(16)));

	public static final RegistryObject<Item> MUSIC_DISC_NABOORU = ITEMS.register("music_disc_nabooru",
			() -> new RecordItem(7, DASounds.NABOORU, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1).rarity(Rarity.RARE), 7280));
	public static final RegistryObject<Item> MUSIC_DISC_A_MORNING_WISH = ITEMS.register("music_disc_a_morning_wish",
			() -> new RecordItem(7, DASounds.A_MORNING_WISH, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1).rarity(Rarity.RARE), 5660));

	public static final RegistryObject<Item> PLACEABLE_POISON_BUCKET = ITEMS.register("poison_bucket",
			() -> new SkyrootPoisonBucketItem(new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1)));

	public static final RegistryObject<Item> VIRULENT_QUICKSAND_BUCKET = ITEMS.register("virulent_quicksand_bucket",
			() -> new SolidBucketItem(DABlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1)));

	public static final RegistryObject<Item> SKYROOT_VIRULENT_QUICKSAND_BUCKET = ITEMS.register("skyroot_virulent_quicksand_bucket",
			() -> new SolidBucketItem(DABlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().tab(AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES).stacksTo(1)));

	public static final RegistryObject<Item> AERGLOW_BLOSSOM = ITEMS.register("aerglow_blossom", () -> new Item(new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS)));
	public static final RegistryObject<Item> GOLDEN_BERRIES = ITEMS.register("goldenleaf_berries",()-> new ItemNameBlockItem(DABlocks.GOLDEN_VINES.get(), (new Item.Properties().tab(AetherCreativeTabs.AETHER_FOOD_AND_DRINKS)).food(DAFoods.GOLDEN_BERRIES)));
	public static final RegistryObject<Item> GOLDEN_GRASS_SEEDS = ITEMS.register("golden_grass_seeds",()-> new Item(new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS)));
	public static final RegistryObject<Item> GOLDEN_SWET_BALL = ITEMS.register("golden_swet_ball",()-> new Item(new Item.Properties().tab(AetherCreativeTabs.AETHER_INGREDIENTS)));

	public static final RegistryObject<Item> SQUASH_SEEDS = ITEMS.register("squash_seeds",()-> new ItemNameBlockItem(DABlocks.SQUASH_STEM.get(), new Item.Properties().tab(AetherCreativeTabs.AETHER_BLOCKS)));

	public static final RegistryObject<Item> CHAOS_EMERALD = ITEMS.register("chaos_emerald", () -> new ChaosEmerald(new Item.Properties()));


	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}

	public static void setupBucketReplacements() {
		SkyrootBucketItem.REPLACEMENTS.put(DAItems.AERGLOW_FISH_BUCKET, DAItems.SKYROOT_AERGLOW_FISH_BUCKET);
	}
}
