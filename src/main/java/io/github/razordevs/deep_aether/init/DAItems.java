package io.github.razordevs.deep_aether.init;

import com.aetherteam.aether.data.resources.registries.AetherStructures;
import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.aether.item.components.AetherDataComponents;
import com.aetherteam.aether.item.components.DungeonKind;
import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootBucketItem;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.datagen.DAJukeboxSongs;
import io.github.razordevs.deep_aether.entity.DABoatEntity;
import io.github.razordevs.deep_aether.item.dungeon.brass.CloudCapeItem;
import io.github.razordevs.deep_aether.item.dungeon.brass.StormBowItem;
import io.github.razordevs.deep_aether.item.dungeon.brass.StormSwordItem;
import io.github.razordevs.deep_aether.item.gear.DAArmorMaterials;
import io.github.razordevs.deep_aether.item.gear.other.*;
import io.github.razordevs.deep_aether.item.gear.skyjade.*;
import io.github.razordevs.deep_aether.item.gear.stratus.*;
import io.github.razordevs.deep_aether.item.misc.*;
import io.github.razordevs.deep_aether.item.moa_food.FodderItem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
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

import java.util.function.Supplier;

public class DAItems {
	public static final DeferredRegister.Items ITEMS =
			DeferredRegister.createItems(DeepAether.MODID);

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
	public static final DeferredItem<Item> SKYJADE_TOOLS_PICKAXE = ITEMS.register("skyjade_pickaxe", () -> new SkyjadeToolsPickaxeItem(DATiers.SKYJADE, new Item.Properties().attributes(PickaxeItem.createAttributes(DATiers.SKYJADE, 1, -3f))));
	public static final DeferredItem<Item> SKYJADE_TOOLS_AXE = ITEMS.register("skyjade_axe", () -> new SkyjadeToolsAxeItem(DATiers.SKYJADE, new Item.Properties().attributes(AxeItem.createAttributes(DATiers.SKYJADE, 1, -3f))));
	public static final DeferredItem<Item> SKYJADE_TOOLS_SHOVEL = ITEMS.register("skyjade_shovel", () -> new SkyjadeToolsShovelItem(DATiers.SKYJADE, new Item.Properties().attributes(ShovelItem.createAttributes(DATiers.SKYJADE, 1, -3f))));
	public static final DeferredItem<Item> SKYJADE_TOOLS_HOE = ITEMS.register("skyjade_hoe", () -> new SkyjadeToolsHoeItem(DATiers.SKYJADE, new Item.Properties().attributes(HoeItem.createAttributes(DATiers.SKYJADE, 0, -3f))));

	public static final DeferredItem<Item> SKYJADE_HELMET = ITEMS.register("skyjade_helmet", () -> new SkyjadeHelmetItem(DAArmorMaterials.SKYJADE, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_CHESTPLATE = ITEMS.register("skyjade_chestplate", () -> new ArmorItem(DAArmorMaterials.SKYJADE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_LEGGINGS = ITEMS.register("skyjade_leggings", () -> new ArmorItem(DAArmorMaterials.SKYJADE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_BOOTS = ITEMS.register("skyjade_boots", () -> new ArmorItem(DAArmorMaterials.SKYJADE, ArmorItem.Type.BOOTS, new Item.Properties()));
	public static final DeferredItem<Item> SKYJADE_GLOVES = ITEMS.register("skyjade_gloves", () -> new SkyjadeGlovesItem(0.5, new Item.Properties().durability(75)));
	public static final DeferredItem<Item> SKYJADE_RING = ITEMS.register("skyjade_ring", () -> new SkyjadeRingItem(new Item.Properties().stacksTo(1).durability(30)));

	public static final DeferredItem<Item> GRAVITITE_RING = ITEMS.register("gravitite_ring", () -> new RingItem(DASounds.ITEM_ACCESSORY_EQUIP_GRAVITITE_RING, new Item.Properties().stacksTo(1).durability(50)));
	public static final DeferredItem<Item> STRATUS_HELMET = ITEMS.register("stratus_helmet", () -> new StratusAbility(DAArmorMaterials.STRATUS, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final DeferredItem<Item> STRATUS_CHESTPLATE = ITEMS.register("stratus_chestplate", () -> new ArmorItem(DAArmorMaterials.STRATUS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<Item> STRATUS_LEGGINGS = ITEMS.register("stratus_leggings", () -> new ArmorItem(DAArmorMaterials.STRATUS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final DeferredItem<Item> STRATUS_BOOTS = ITEMS.register("stratus_boots", () -> new ArmorItem(DAArmorMaterials.STRATUS, ArmorItem.Type.BOOTS, new Item.Properties()));
	public static final DeferredItem<Item> STRATUS_GLOVES = ITEMS.register("stratus_gloves", () -> new GlovesItem(DAArmorMaterials.STRATUS, 1.0, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "stratus_gloves"), DASounds.ITEM_ARMOR_EQUIP_STRATUS, new Item.Properties().durability(2031)));
	public static final DeferredItem<Item> STRATUS_RING = ITEMS.register("stratus_ring", () -> new RingItem(DASounds.ITEM_ACCESSORY_EQUIP_STRATUS_RING, new Item.Properties().stacksTo(1).durability(100)));
	public static final DeferredItem<Item> SPOOKY_RING = ITEMS.register("spooky_ring", () -> new SpookyRing(DASounds.ITEM_ACCESSORY_EQUIP_SPOOKY_RING, new Item.Properties().stacksTo(1).durability(500)));
	public static final DeferredItem<Item> SLIDER_EYE = ITEMS.register("slider_eye", () -> new SliderEye(DASounds.ITEM_ACCESSORY_EQUIP_SLIDER_EYE, new Item.Properties().stacksTo(1).durability(500).rarity(AetherItems.AETHER_LOOT).fireResistant()));
	public static final DeferredItem<Item> MEDAL_OF_HONOR = ITEMS.register("medal_of_honor", () -> new MedalOfHonor(new Item.Properties().stacksTo(1).rarity(AetherItems.AETHER_LOOT).fireResistant()));

	public static final DeferredItem<Item> STRATUS_SWORD = ITEMS.register("stratus_sword", () -> new StratusSwordItem(DATiers.STRATUS, (new Item.Properties().attributes(SwordItem.createAttributes(DATiers.STRATUS, 3, -2.4F)))));
	public static final DeferredItem<Item> STRATUS_SHOVEL = ITEMS.register("stratus_shovel", () -> new StratusShovelItem(DATiers.STRATUS, (new Item.Properties().attributes(ShovelItem.createAttributes(DATiers.STRATUS, 1.5F, -3.0F)))));
	public static final DeferredItem<Item> STRATUS_PICKAXE = ITEMS.register("stratus_pickaxe", () -> new StratusPickaxeItem(DATiers.STRATUS, (new Item.Properties().attributes(PickaxeItem.createAttributes(DATiers.STRATUS, 1, -2.8F)))));
	public static final DeferredItem<Item> STRATUS_AXE = ITEMS.register("stratus_axe", () -> new StratusAxeItem(DATiers.STRATUS, (new Item.Properties()).attributes(AxeItem.createAttributes(DATiers.STRATUS, 5.0F, -3.0F))));
	public static final DeferredItem<Item> STRATUS_HOE = ITEMS.register("stratus_hoe", () -> new StratusHoeItem(DATiers.STRATUS, (new Item.Properties().attributes(HoeItem.createAttributes(DATiers.STRATUS, -4, 0.0F)))));
	public static final DeferredItem<Item> STRATUS_SMITHING_TEMPLATE = ITEMS.register("stratus_smithing_template", () -> new SmithingTemplateItem(DAItems.STRATUS_UPGRADE_APPLIES_TO, DAItems.STRATUS_UPGRADE_INGREDIENTS, DAItems.STRATUS_UPGRADE, DAItems.STRATUS_UPGRADE_BASE_SLOT_DESCRIPTION, DAItems.STRATUS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, SmithingTemplateItem.createNetheriteUpgradeIconList(),  SmithingTemplateItem.createNetheriteUpgradeMaterialList()));

	public static final DeferredItem<Item> STORMFORGED_HELMET = ITEMS.register("stormforged_helmet", () -> new ArmorItem(DAArmorMaterials.STORMFORGED, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final DeferredItem<Item> STORMFORGED_CHESTPLATE = ITEMS.register("stormforged_chestplate", () -> new ArmorItem(DAArmorMaterials.STORMFORGED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<Item> STORMFORGED_LEGGINGS = ITEMS.register("stormforged_leggings", () -> new ArmorItem(DAArmorMaterials.STORMFORGED, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final DeferredItem<Item> STORMFORGED_BOOTS = ITEMS.register("stormforged_boots", () -> new ArmorItem(DAArmorMaterials.STORMFORGED, ArmorItem.Type.BOOTS, new Item.Properties()));
	public static final DeferredItem<Item> STORMFORGED_GLOVES = ITEMS.register("stormforged_gloves", () -> new GlovesItem(DAArmorMaterials.STORMFORGED, 0.75, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "stormforged_gloves"), DASounds.ITEM_ARMOR_EQUIP_STRATUS, new Item.Properties()));

	public static final DeferredItem<Item> AERCLOUD_NECKLACE = ITEMS.register("aercloud_necklace", () -> new PendantItem(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "aercloud_necklace"), DASounds.ITEM_ACCESSORY_EQUIP_MEDAL_OF_HONOR, new Item.Properties()));
	public static final DeferredItem<Item> CLOUD_CAPE = ITEMS.register("cloud_cape", () -> new CloudCapeItem(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "cloud_cape"), new Item.Properties()));

	// ORES
	public static final DeferredItem<Item> SKYJADE = ITEMS.register("skyjade", SkyjadeItem::new);
	public static final DeferredItem<Item> STRATUS_INGOT = ITEMS.register("stratus_ingot", () -> new Item(new Item.Properties()));

	// FOOD
	public static final DeferredItem<Item> RAW_QUAIL = ITEMS.register("raw_quail", () -> new Item(new Item.Properties().food(Foods.CHICKEN)));
	public static final DeferredItem<Item> COOKED_QUAIL = ITEMS.register("cooked_quail", () -> new Item(new Item.Properties().food(Foods.COOKED_CHICKEN)));
	public static final DeferredItem<Item> RAW_AERGLOW_FISH = ITEMS.register("raw_aerglow_fish", () -> new Item(new Item.Properties().food(Foods.COD)));
	public static final DeferredItem<Item> COOKED_AERGLOW_FISH = ITEMS.register("cooked_aerglow_fish", () -> new Item(new Item.Properties().food(Foods.COOKED_COD)));

	public static final DeferredItem<Item> BIO_CRYSTAL = ITEMS.register("bio_crystal", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> SKYROOT_AERGLOW_FISH_BUCKET = ITEMS.register("skyroot_aerglow_fish_bucket", () ->  new DASkyrootBucketItem(DAEntities.AETHER_FISH.get(), (new Item.Properties()).craftRemainder(AetherItems.SKYROOT_BUCKET.get()).stacksTo(1)));
	public static final DeferredItem<Item> AERGLOW_FISH_BUCKET = ITEMS.register("aerglow_fish_bucket", () -> new DABucketItem(DAEntities.AETHER_FISH.get(), (new Item.Properties()).stacksTo(1)));

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

	public static final DeferredItem<Item> BLUE_SQUASH_SLICE = ITEMS.register("blue_squash_slice", () -> new DASquashPieItem(new Item.Properties().food(Foods.GLOW_BERRIES), MobEffects.MOVEMENT_SPEED, MobEffects.NIGHT_VISION, MobEffects.DIG_SLOWDOWN));
	public static final DeferredItem<Item> GREEN_SQUASH_SLICE = ITEMS.register("green_squash_slice", () -> new DASquashPieItem(new Item.Properties().food(Foods.GLOW_BERRIES), MobEffects.JUMP, MobEffects.LEVITATION, MobEffects.LUCK));
	public static final DeferredItem<Item> PURPLE_SQUASH_SLICE = ITEMS.register("purple_squash_slice", () -> new DASquashPieItem(new Item.Properties().food(Foods.GLOW_BERRIES), MobEffects.LEVITATION, MobEffects.DAMAGE_RESISTANCE, AetherEffects.INEBRIATION));

	// MISC
	public static final DeferredItem<Item> SUN_CORE = ITEMS.register("sun_core", () -> new SunCore((new Item.Properties()).rarity(AetherItems.AETHER_LOOT).fireResistant()));
	public static final DeferredItem<Item> AERWHALE_SADDLE = ITEMS.register("aerwhale_saddle", () -> new AerwhaleSaddle((new Item.Properties().stacksTo(1)).rarity(AetherItems.AETHER_LOOT).fireResistant()));

	public static final DeferredItem<Item> FLOATY_SCARF = ITEMS.register("floaty_scarf", () -> new FloatyScarf("floaty_scarf", new Item.Properties()));

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
			() -> new Item( new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(DAJukeboxSongs.NABOORU)));
	public static final DeferredItem<Item> MUSIC_DISC_A_MORNING_WISH = ITEMS.register("music_disc_a_morning_wish",
			() -> new Item( new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(DAJukeboxSongs.A_MORNING_WISH)));

	public static final DeferredItem<Item> PLACEABLE_POISON_BUCKET = ITEMS.register("poison_bucket",
			() -> new DrinkableBucketItem(DAFluids.POISON_FLUID.get(), new Item.Properties().stacksTo(1)));

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
	public static final DeferredItem<Item> BRONZE_COMPASS = ITEMS.register("bronze_compass", ()-> new DungeonCompass(new Item.Properties(), AetherStructures.BRONZE_DUNGEON, "Bronze Dungeon"));
	public static final DeferredItem<Item> SILVER_COMPASS = ITEMS.register("silver_compass", ()-> new DungeonCompass(new Item.Properties(), AetherStructures.SILVER_DUNGEON, "Silver Dungeon"));
	public static final DeferredItem<Item> GOLD_COMPASS = ITEMS.register("gold_compass", ()-> new DungeonCompass(new Item.Properties(), AetherStructures.GOLD_DUNGEON, "Gold Dungeon"));

	public static final DeferredItem<Item> CHAOS_EMERALD = ITEMS.register("chaos_emerald", () -> new ChaosEmerald(new Item.Properties()));

	//BRASS LOOT
	public static final DeferredItem<Item> BRASS_DUNGEON_KEY = ITEMS.register("brass_dungeon_key", () -> new Item(new Item.Properties().stacksTo(1).rarity(AetherItems.AETHER_LOOT).fireResistant().component(AetherDataComponents.DUNGEON_KIND, new DungeonKind(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "brass")))));
	public static final DeferredItem<Item> STORM_BOW = ITEMS.register("storm_bow", () -> new StormBowItem(new Item.Properties().durability(384)));
	public static final DeferredItem<Item> STORM_SWORD = ITEMS.register("storm_sword", () -> new StormSwordItem(DATiers.STORM, new Item.Properties().durability(384)));

	//PROTECT YOUR MOA
	//public static final DeferredItem<?> SKYJADE_MOA_ARMOR = registerPYMItem();

	//LOST CONTENT
	/*
	public static final DeferredItem<Item> SKYJADE_SHIELD = registerLostContentItem("skyjade_shield", () -> new SkyjadeShieldItem(new Item.Properties().durability(672)));
	public static final DeferredItem<Item> STRATUS_SHIELD = ITEMS.register("stratus_shield", () -> new LCDAShieldItem(new Item.Properties().durability(1344)));
	*/

	public static void setupBucketReplacements() {
		SkyrootBucketItem.REPLACEMENTS.put(DAItems.AERGLOW_FISH_BUCKET, DAItems.SKYROOT_AERGLOW_FISH_BUCKET);
	}
	private static <T extends Item> DeferredItem<T> registerLostContentItem(String name, Supplier<T> item) {
		if(ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT)) {
			DeepAether.LOGGER.info("Deep Aether: Registering Aether Lost Content compat items");
		}
		return ITEMS.register(name, item);
	}

	/*
	private static DeferredItem<?>  registerPYMItem() {
		if(ModList.get().isLoaded(DeepAether.PROTECT_YOUR_MOA)) {
			DeepAether.LOGGER.info("Deep Aether: Registering Protect Your Moa compat items");
			return ITEMS.register("skyjade_moa_armor", ()-> new MoaArmorItem(7, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/entity/moa/armor/moa_armor_skyjade.png"), new Item.Properties().stacksTo(1)));
		}
		else return ITEMS.register("skyjade_moa_armor", () -> new Item(new Item.Properties()));
	}
	 */

	//For Stratus Template
	public static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
	public static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.LIGHT_PURPLE;
	public static final Component STRATUS_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "stratus_upgrade"))).withStyle(DAItems.TITLE_FORMAT);
	public static final Component STRATUS_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(DeepAether.MODID,"smithing_template.stratus_upgrade.applies_to"))).withStyle(DAItems.DESCRIPTION_FORMAT);
	public static final Component STRATUS_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(DeepAether.MODID,"smithing_template.stratus_upgrade.ingredients"))).withStyle(DAItems.DESCRIPTION_FORMAT);
	public static final Component STRATUS_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(DeepAether.MODID,"smithing_template.stratus_upgrade.base_slot_description")));
	public static final Component STRATUS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(DeepAether.MODID,"smithing_template.stratus_upgrade.additions_slot_description")));
}
