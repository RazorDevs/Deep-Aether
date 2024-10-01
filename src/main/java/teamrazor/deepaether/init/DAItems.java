package teamrazor.deepaether.init;

import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.item.accessories.cape.CapeItem;
import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.aether.item.miscellaneous.DungeonKeyItem;
import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootBucketItem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.DABoatEntity;
import teamrazor.deepaether.item.compat.lost_content.LCDAShieldItem;
import teamrazor.deepaether.item.compat.lost_content.SkyjadeShieldItem;
import teamrazor.deepaether.item.dungeon.brass.CloudCapeItem;
import teamrazor.deepaether.item.dungeon.brass.StormBowItem;
import teamrazor.deepaether.item.dungeon.brass.StormSwordItem;
import teamrazor.deepaether.item.gear.DaArmorItem;
import teamrazor.deepaether.item.gear.DaArmorMaterials;
import teamrazor.deepaether.item.gear.other.*;
import teamrazor.deepaether.item.gear.skyjade.*;
import teamrazor.deepaether.item.gear.stratus.*;
import teamrazor.deepaether.item.misc.*;
import teamrazor.deepaether.item.moa_food.FodderItem;

import java.util.function.Supplier;

public class DAItems {
	public static final DeferredRegister.Items ITEMS =
			DeferredRegister.createItems(DeepAether.MODID);

	public static final Rarity AETHER_LOOT = Rarity.create("aether.loot", ChatFormatting.AQUA);

	// SIGNS
	public static final DeferredItem<Item> ROSEROOT_SIGN = ITEMS.register("roseroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.ROSEROOT_SIGN.get(), DABlocks.ROSEROOT_WALL_SIGN.get()));
	public static final DeferredItem<Item> YAGROOT_SIGN = ITEMS.register("yagroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.YAGROOT_SIGN.get(), DABlocks.YAGROOT_WALL_SIGN.get()));
	public static final DeferredItem<Item> CRUDEROOT_SIGN = ITEMS.register("cruderoot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.CRUDEROOT_SIGN.get(), DABlocks.CRUDEROOT_WALL_SIGN.get()));
	public static final DeferredItem<Item> CONBERRY_SIGN = ITEMS.register("conberry_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.CONBERRY_SIGN.get(), DABlocks.CONBERRY_WALL_SIGN.get()));
	public static final DeferredItem<Item> SUNROOT_SIGN = ITEMS.register("sunroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16), DABlocks.SUNROOT_SIGN.get(), DABlocks.SUNROOT_WALL_SIGN.get()));

	public static final DeferredItem<Item> ROSEROOT_HANGING_SIGN = ITEMS.register("roseroot_hanging_sign", () -> new HangingSignItem(DABlocks.ROSEROOT_HANGING_SIGN.get(), DABlocks.ROSEROOT_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> YAGROOT_HANGING_SIGN = ITEMS.register("yagroot_hanging_sign", () -> new HangingSignItem(DABlocks.YAGROOT_HANGING_SIGN.get(), DABlocks.YAGROOT_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> CRUDEROOT_HANGING_SIGN = ITEMS.register("cruderoot_hanging_sign", () -> new HangingSignItem(DABlocks.CRUDEROOT_HANGING_SIGN.get(), DABlocks.CRUDEROOT_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> CONBERRY_HANGING_SIGN = ITEMS.register("conberry_hanging_sign", () -> new HangingSignItem(DABlocks.CONBERRY_HANGING_SIGN.get(), DABlocks.CONBERRY_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
	public static final DeferredItem<Item> SUNROOT_HANGING_SIGN = ITEMS.register("sunroot_hanging_sign", () -> new HangingSignItem(DABlocks.SUNROOT_HANGING_SIGN.get(), DABlocks.SUNROOT_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

	// EQUIPMENT
	public static final DeferredItem<Item> AFTERBURNER = ITEMS.register("afterburner", () -> new Afterburner((new Item.Properties().stacksTo(1).durability(200).fireResistant())));

	public static final DeferredItem<Item> SKYJADE_TOOLS_SWORD = ITEMS.register("skyjade_sword", SkyjadeToolsSwordItem::new);
	public static final DeferredItem<Item> SKYJADE_TOOLS_PICKAXE = ITEMS.register("skyjade_pickaxe", () -> new SkyjadeToolsPickaxeItem(DATiers.SKYJADE, 1, -3f, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_TOOLS_AXE = ITEMS.register("skyjade_axe", () -> new SkyjadeToolsAxeItem(DATiers.SKYJADE, 1, -3f, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_TOOLS_SHOVEL = ITEMS.register("skyjade_shovel", () -> new SkyjadeToolsShovelItem(DATiers.SKYJADE, 1, -3f, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_TOOLS_HOE = ITEMS.register("skyjade_hoe", () -> new SkyjadeToolsHoeItem(DATiers.SKYJADE, 0, -3f, new Item.Properties()));

	public static final DeferredItem<Item> SKYJADE_HELMET = ITEMS.register("skyjade_helmet", () -> new SkyjadeHelmetItem(DaArmorMaterials.SKYJADE, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_CHESTPLATE = ITEMS.register("skyjade_chestplate", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_LEGGINGS = ITEMS.register("skyjade_leggings", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_BOOTS = ITEMS.register("skyjade_boots", () -> new SkyjadeArmorItem(DaArmorMaterials.SKYJADE, ArmorItem.Type.BOOTS, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_GLOVES = ITEMS.register("skyjade_gloves", () -> new SkyjadeGlovesItem(0.5, new Item.Properties().defaultDurability(75)));
	public static final DeferredItem<Item> SKYJADE_RING = ITEMS.register("skyjade_ring", () -> new SkyjadeRingItem(new Item.Properties().stacksTo(1).durability(30)));

	public static final DeferredItem<Item> GRAVITITE_RING = ITEMS.register("gravitite_ring", () -> new RingItem(DASounds.ITEM_ACCESSORY_EQUIP_GRAVITITE_RING, new Item.Properties().stacksTo(1).durability(50)));
	public static final DeferredItem<Item> STRATUS_HELMET = ITEMS.register("stratus_helmet", () -> new StratusAbility(DaArmorMaterials.STRATUS, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final DeferredItem<Item> STRATUS_CHESTPLATE = ITEMS.register("stratus_chestplate", () -> new DaArmorItem(DaArmorMaterials.STRATUS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<Item> STRATUS_LEGGINGS = ITEMS.register("stratus_leggings", () -> new DaArmorItem(DaArmorMaterials.STRATUS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final DeferredItem<Item> STRATUS_BOOTS = ITEMS.register("stratus_boots", () -> new DaArmorItem(DaArmorMaterials.STRATUS, ArmorItem.Type.BOOTS, new Item.Properties()));
	public static final DeferredItem<Item> STRATUS_GLOVES = ITEMS.register("stratus_gloves", () -> new GlovesItem(DaArmorMaterials.STRATUS, 1.0, new ResourceLocation(DeepAether.MODID, "stratus_gloves"), DASounds.ITEM_ARMOR_EQUIP_STRATUS, new Item.Properties().defaultDurability(2031)));
	public static final DeferredItem<Item> STRATUS_RING = ITEMS.register("stratus_ring", () -> new RingItem(DASounds.ITEM_ACCESSORY_EQUIP_STRATUS_RING, new Item.Properties().stacksTo(1).durability(100)));
	public static final DeferredItem<Item> SPOOKY_RING = ITEMS.register("spooky_ring", () -> new SpookyRing(DASounds.ITEM_ACCESSORY_EQUIP_SPOOKY_RING, new Item.Properties().stacksTo(1).durability(500)));
	public static final DeferredItem<Item> SLIDER_EYE = ITEMS.register("slider_eye", () -> new SliderEye(DASounds.ITEM_ACCESSORY_EQUIP_SLIDER_EYE, new Item.Properties().stacksTo(1).durability(500).rarity(AetherItems.AETHER_LOOT).fireResistant()));
	public static final DeferredItem<Item> MEDAL_OF_HONOR = ITEMS.register("medal_of_honor", () -> new MedalOfHonor(new Item.Properties().stacksTo(1).rarity(AetherItems.AETHER_LOOT).fireResistant()));

	public static final DeferredItem<Item> STRATUS_SWORD = ITEMS.register("stratus_sword", () -> new StratusSwordItem(DATiers.STRATUS, 3, -2.4F, (new Item.Properties())));
	public static final DeferredItem<Item> STRATUS_SHOVEL = ITEMS.register("stratus_shovel", () -> new StratusShovelItem(DATiers.STRATUS, 1.5F, -3.0F, (new Item.Properties())));
	public static final DeferredItem<Item> STRATUS_PICKAXE = ITEMS.register("stratus_pickaxe", () -> new StratusPickaxeItem(DATiers.STRATUS, 1, -2.8F, (new Item.Properties())));
	public static final DeferredItem<Item> STRATUS_AXE = ITEMS.register("stratus_axe", () -> new StratusAxeItem(DATiers.STRATUS, 5.0F, -3.0F, (new Item.Properties())));
	public static final DeferredItem<Item> STRATUS_HOE = ITEMS.register("stratus_hoe", () -> new StratusHoeItem(DATiers.STRATUS, -4, 0.0F, (new Item.Properties())));
	public static final DeferredItem<Item> STRATUS_SMITHING_TEMPLATE = ITEMS.register("stratus_smithing_template", () -> new SmithingTemplateItem(DAItems.STRATUS_UPGRADE_APPLIES_TO, DAItems.STRATUS_UPGRADE_INGREDIENTS, DAItems.STRATUS_UPGRADE, DAItems.STRATUS_UPGRADE_BASE_SLOT_DESCRIPTION, DAItems.STRATUS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, SmithingTemplateItem.createNetheriteUpgradeIconList(),  SmithingTemplateItem.createNetheriteUpgradeMaterialList()));

	public static final DeferredItem<Item> STORMFORGED_HELMET = ITEMS.register("stormforged_helmet", () -> new StratusAbility(DaArmorMaterials.STORMFORGED, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final DeferredItem<Item> STORMFORGED_CHESTPLATE = ITEMS.register("stormforged_chestplate", () -> new DaArmorItem(DaArmorMaterials.STORMFORGED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<Item> STORMFORGED_LEGGINGS = ITEMS.register("stormforged_leggings", () -> new DaArmorItem(DaArmorMaterials.STORMFORGED, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final DeferredItem<Item> STORMFORGED_BOOTS = ITEMS.register("stormforged_boots", () -> new DaArmorItem(DaArmorMaterials.STORMFORGED, ArmorItem.Type.BOOTS, new Item.Properties()));
	public static final DeferredItem<Item> STORMFORGED_GLOVES = ITEMS.register("stormforged_gloves", () -> new GlovesItem(DaArmorMaterials.STORMFORGED, 0.75, new ResourceLocation(DeepAether.MODID, "stormforged_gloves"), DASounds.ITEM_ARMOR_EQUIP_STRATUS, new Item.Properties()));

	public static final DeferredItem<Item> AERCLOUD_NECKLACE = ITEMS.register("aercloud_necklace", () -> new PendantItem(new ResourceLocation(DeepAether.MODID, "aercloud_necklace"), DASounds.ITEM_ACCESSORY_EQUIP_MEDAL_OF_HONOR, new Item.Properties()));
	public static final DeferredItem<Item> CLOUD_CAPE = ITEMS.register("cloud_cape", () -> new CloudCapeItem(new ResourceLocation(DeepAether.MODID, "cloud_cape"), new Item.Properties()));

	// ORES
	public static final DeferredItem<Item> SKYJADE = ITEMS.register("skyjade", SkyjadeItem::new);
	public static final DeferredItem<Item> STRATUS_INGOT = ITEMS.register("stratus_ingot", () -> new Item(new Item.Properties()));

	// FOOD
	public static final DeferredItem<Item> RAW_QUAIL = ITEMS.register("raw_quail", () -> new Item(new Item.Properties().food(Foods.CHICKEN)));
	public static final DeferredItem<Item> COOKED_QUAIL = ITEMS.register("cooked_quail", () -> new Item(new Item.Properties().food(Foods.COOKED_CHICKEN)));
	public static final DeferredItem<Item> RAW_AERGLOW_FISH = ITEMS.register("raw_aerglow_fish", () -> new Item(new Item.Properties().food(Foods.COD)));
	public static final DeferredItem<Item> COOKED_AERGLOW_FISH = ITEMS.register("cooked_aerglow_fish", () -> new Item(new Item.Properties().food(Foods.COOKED_COD)));

	public static final DeferredItem<Item> BIO_CRYSTAL = ITEMS.register("bio_crystal", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> SKYROOT_AERGLOW_FISH_BUCKET = ITEMS.register("skyroot_aerglow_fish_bucket", () ->  new DASkyrootBucketItem(DAEntities.AETHER_FISH, (new Item.Properties()).craftRemainder(AetherItems.SKYROOT_BUCKET.get()).stacksTo(1)));
	public static final DeferredItem<Item> AERGLOW_FISH_BUCKET = ITEMS.register("aerglow_fish_bucket", () -> new DABucketItem(DAEntities.AETHER_FISH, (new Item.Properties()).stacksTo(1)));

	// MOA FODDER
	public static final DeferredItem<Item> JUMP_FODDER = ITEMS.register("jump_fodder", () -> new FodderItem(new Item.Properties(), new MobEffectInstance(MobEffects.JUMP, 200, 1, true, true, true)));
	public static final DeferredItem<Item> LEVITATION_FODDER = ITEMS.register("levitation_fodder", () -> new FodderItem(new Item.Properties(), new MobEffectInstance(MobEffects.LEVITATION, 200, 1, true, true, true)));
	public static final DeferredItem<Item> FIRE_RES_FODDER = ITEMS.register("fire_res_fodder", () -> new FodderItem(new Item.Properties(), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 1, true, true, true)));


	// BOATS
	public static final DeferredItem<Item> ROSEROOT_BOAT = ITEMS.register("roseroot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.ROSEROOT));
	public static final DeferredItem<Item> ROSEROOT_CHEST_BOAT = ITEMS.register("roseroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1),  DABoatEntity.Type.ROSEROOT));
	public static final DeferredItem<Item> YAGROOT_BOAT = ITEMS.register("yagroot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.YAGROOT));
	public static final DeferredItem<Item> YAGROOT_CHEST_BOAT = ITEMS.register("yagroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.YAGROOT));

	public static final DeferredItem<Item> CRUDEROOT_BOAT = ITEMS.register("cruderoot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.CRUDEROOT));
	public static final DeferredItem<Item> CRUDEROOT_CHEST_BOAT = ITEMS.register("cruderoot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.CRUDEROOT));

	public static final DeferredItem<Item> CONBERRY_BOAT = ITEMS.register("conberry_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.CONBERRY));
	public static final DeferredItem<Item> CONBERRY_CHEST_BOAT = ITEMS.register("conberry_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.CONBERRY));
	public static final DeferredItem<Item> SUNROOT_BOAT = ITEMS.register("sunroot_boat", () -> new DABoatItem(false, new Item.Properties().stacksTo(1), DABoatEntity.Type.SUNROOT));
	public static final DeferredItem<Item> SUNROOT_CHEST_BOAT = ITEMS.register("sunroot_chest_boat", () -> new DABoatItem(true, new Item.Properties().stacksTo(1), DABoatEntity.Type.SUNROOT));

	public static final DeferredItem<Item> BLUE_SQUASH_SLICE = ITEMS.register("blue_squash_slice", () -> new DASquashPieItem(new Item.Properties().food(Foods.GLOW_BERRIES), new MobEffect[]{MobEffects.MOVEMENT_SPEED, MobEffects.NIGHT_VISION, MobEffects.DIG_SLOWDOWN}));
	public static final DeferredItem<Item> GREEN_SQUASH_SLICE = ITEMS.register("green_squash_slice", () -> new DASquashPieItem(new Item.Properties().food(Foods.GLOW_BERRIES), new MobEffect[]{MobEffects.JUMP, MobEffects.LEVITATION, MobEffects.LUCK}));
	public static final DeferredItem<Item> PURPLE_SQUASH_SLICE = ITEMS.register("purple_squash_slice", () -> new DASquashPieItem(new Item.Properties().food(Foods.GLOW_BERRIES), new MobEffect[]{MobEffects.LEVITATION, MobEffects.DAMAGE_RESISTANCE, AetherEffects.INEBRIATION.get()}));

	// MISC
	public static final DeferredItem<Item> SUN_CORE = ITEMS.register("sun_core", () -> new SunCore((new Item.Properties()).rarity(AetherItems.AETHER_LOOT).fireResistant()));
	public static final DeferredItem<Item> AERWHALE_SADDLE = ITEMS.register("aerwhale_saddle", () -> new AerwhaleSaddle((new Item.Properties().stacksTo(1)).rarity(AetherItems.AETHER_LOOT).fireResistant()));

	public static final DeferredItem<Item> AETHER_FISH_SPAWN_EGG = ITEMS.register("aether_fish_spawn_egg",
			() -> new DeferredSpawnEggItem(DAEntities.AETHER_FISH, 698060, 16776960, new Item.Properties()));

	public static final DeferredItem<Item> QUAIL_SPAWN_EGG = ITEMS.register("quail_spawn_egg",
			() -> new DeferredSpawnEggItem(DAEntities.QUAIL,6373632,16776960, new Item.Properties()));

	public static final DeferredItem<Item> VENOMITE_SPAWN_EGG = ITEMS.register("venomite_spawn_egg",
			() -> new DeferredSpawnEggItem(DAEntities.VENOMITE,0, 800080, new Item.Properties()));

	public static final DeferredItem<Item> WINDFLY_SPAWN_EGG = ITEMS.register("windfly_spawn_egg",
			() -> new DeferredSpawnEggItem(DAEntities.WINDFLY,10000, 500080, new Item.Properties()));


	public static final DeferredItem<Item> QUAIL_EGG = ITEMS.register("quail_egg",
			() -> new QuailEggItem(new Item.Properties().stacksTo(16)));

	public static final DeferredItem<Item> MUSIC_DISC_NABOORU = ITEMS.register("music_disc_nabooru",
			() -> new RecordItem(7, DASounds.NABOORU, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 7280));
	public static final DeferredItem<Item> MUSIC_DISC_A_MORNING_WISH = ITEMS.register("music_disc_a_morning_wish",
			() -> new RecordItem(7, DASounds.A_MORNING_WISH, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 5660));

	public static final DeferredItem<Item> PLACEABLE_POISON_BUCKET = ITEMS.register("poison_bucket",
			() -> new DrinkableBucketItem(DAFluids.POISON_FLUID, new Item.Properties().stacksTo(1)));

	public static final DeferredItem<Item> VIRULENT_QUICKSAND_BUCKET = ITEMS.register("virulent_quicksand_bucket",
			() -> new SolidBucketItem(DABlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1)));

	public static final DeferredItem<Item> SKYROOT_VIRULENT_QUICKSAND_BUCKET = ITEMS.register("skyroot_virulent_quicksand_bucket",
			() -> new SolidBucketItem(DABlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1)));

	public static final DeferredItem<Item> AERGLOW_BLOSSOM = ITEMS.register("aerglow_blossom", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> GOLDEN_BERRIES = ITEMS.register("goldenleaf_berries",()-> new ItemNameBlockItem(DABlocks.GOLDEN_VINES.get(), (new Item.Properties()).food(DAFoods.GOLDEN_BERRIES)));
	public static final DeferredItem<Item> FROZEN_GOLDEN_BERRIES = ITEMS.register("frozen_goldenleaf_berries",()-> new Item(new Item.Properties()));
	public static final DeferredItem<Item> ANTIDOTE = ITEMS.register("antidote",()-> new AntidoteItem(false, new Item.Properties().stacksTo(16).food(DAFoods.ANTIDOTE)));
	public static final DeferredItem<Item> ENCHANTED_ANTIDOTE = ITEMS.register("enchanted_antidote",()-> new AntidoteItem(true, new Item.Properties().stacksTo(16).food(DAFoods.ENCHANTED_ANTIDOTE)));

	public static final DeferredItem<Item> GOLDEN_GRASS_SEEDS = ITEMS.register("golden_grass_seeds",()-> new Item(new Item.Properties()));
	public static final DeferredItem<Item> GOLDEN_SWET_BALL = ITEMS.register("golden_swet_ball",()-> new GoldenSwetBallItem(new Item.Properties()));

	public static final DeferredItem<Item> SQUASH_SEEDS = ITEMS.register("squash_seeds",()-> new ItemNameBlockItem(DABlocks.SQUASH_STEM.get(), new Item.Properties()));
	public static final DeferredItem<Item> GLOWING_SPORES = ITEMS.register("glowing_spores", ()-> new GlowingSporeItem(new Item.Properties()));

	public static final DeferredItem<Item> SUN_CLOCK = ITEMS.register("sun_clock", ()-> new SunClock(new Item.Properties()));
	public static final DeferredItem<Item> BRONZE_COMPASS = ITEMS.register("bronze_compass", ()-> new DungeonCompass(new Item.Properties(), "bronze_dungeon", "Bronze Dungeon"));
	public static final DeferredItem<Item> SILVER_COMPASS = ITEMS.register("silver_compass", ()-> new DungeonCompass(new Item.Properties(), "silver_dungeon", "Silver Dungeon"));
	public static final DeferredItem<Item> GOLD_COMPASS = ITEMS.register("gold_compass", ()-> new DungeonCompass(new Item.Properties(), "gold_dungeon", "Gold Dungeon"));

	public static final DeferredItem<Item> CHAOS_EMERALD = ITEMS.register("chaos_emerald", () -> new ChaosEmerald(new Item.Properties()));

	//BRASS LOOT
	public static final DeferredItem<Item> BRASS_DUNGEON_KEY = ITEMS.register("brass_dungeon_key", () -> new DungeonKeyItem(new ResourceLocation(DeepAether.MODID, "brass"), new Item.Properties().stacksTo(1).rarity(AETHER_LOOT).fireResistant()));
	public static final DeferredItem<Item> STORM_BOW = ITEMS.register("storm_bow", () -> new StormBowItem(new Item.Properties().durability(384)));
	public static final DeferredItem<Item> STORM_SWORD = ITEMS.register("storm_sword", () -> new StormSwordItem(DATiers.STORM, 3, -2.4F, new Item.Properties().durability(384)));

	//PROTECT YOUR MOA
	public static final DeferredItem<?> SKYJADE_MOA_ARMOR = registerPYMItem();

	//LOST CONTENT
	public static final DeferredItem<Item> SKYJADE_SHIELD = registerLostContentItem("skyjade_shield", () -> new SkyjadeShieldItem(new Item.Properties().durability(672)));
	public static final DeferredItem<Item> STRATUS_SHIELD = ITEMS.register("stratus_shield", () -> new LCDAShieldItem(new Item.Properties().durability(1344)));

	public static void setupBucketReplacements() {
		SkyrootBucketItem.REPLACEMENTS.put(DAItems.AERGLOW_FISH_BUCKET, DAItems.SKYROOT_AERGLOW_FISH_BUCKET);
	}
	private static <T extends Item> DeferredItem<T> registerLostContentItem(String name, Supplier<T> item) {
		if(ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT)) {
			DeepAether.LOGGER.info("Deep Aether: Registering Aether Lost Content compat items");
		}
		return ITEMS.register(name, item);
	}

	private static DeferredItem<?>  registerPYMItem() {
		if(ModList.get().isLoaded(DeepAether.PROTECT_YOUR_MOA)) {
			DeepAether.LOGGER.info("Deep Aether: Registering Protect Your Moa compat items");
			return ITEMS.register("skyjade_moa_armor", ()-> new com.aetherteam.protect_your_moa.item.combat.MoaArmorItem(7, new ResourceLocation(DeepAether.MODID, "textures/entity/moa/armor/moa_armor_skyjade.png"), new Item.Properties().stacksTo(1)));
		}
		else return ITEMS.register("skyjade_moa_armor", () -> new Item(new Item.Properties()));
	}

	//For Stratus Template
	public static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
	public static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.LIGHT_PURPLE;
	public static final Component STRATUS_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(DeepAether.MODID, "stratus_upgrade"))).withStyle(DAItems.TITLE_FORMAT);
	public static final Component STRATUS_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(DeepAether.MODID,"smithing_template.stratus_upgrade.applies_to"))).withStyle(DAItems.DESCRIPTION_FORMAT);
	public static final Component STRATUS_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(DeepAether.MODID,"smithing_template.stratus_upgrade.ingredients"))).withStyle(DAItems.DESCRIPTION_FORMAT);
	public static final Component STRATUS_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(DeepAether.MODID,"smithing_template.stratus_upgrade.base_slot_description")));
	public static final Component STRATUS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(DeepAether.MODID,"smithing_template.stratus_upgrade.additions_slot_description")));
}
