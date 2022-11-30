package teamrazor.deepaether.init;


import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.sounds.SoundEvents;
import com.gildedgames.aether.client.AetherSoundEvents;
import com.gildedgames.aether.item.accessories.ring.RingItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.item.*;
import teamrazor.deepaether.DeepAetherMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.Block;
import teamrazor.deepaether.item.gear.cloudium.*;

import teamrazor.deepaether.item.gear.DeepAetherGlovesItem;
import teamrazor.deepaether.item.gear.skyjade.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class DeepAetherModItems {
	public static final ItemSubRegistryHelper HELPER = DeepAetherMod.REGISTRY_HELPER.getItemSubHelper();

	// BOATS
	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> ROSE_BOAT = HELPER.createBoatAndChestBoatItem("rose", DeepAetherModBlocks.ROSE_PLANKS);
	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> YAGROOT_BOAT = HELPER.createBoatAndChestBoatItem("yagroot", DeepAetherModBlocks.YAGROOT_PLANKS);
	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> CRUDEROOT_BOAT = HELPER.createBoatAndChestBoatItem("cruderoot", DeepAetherModBlocks.CRUDEROOT_PLANKS);

	// EQUIPMENT
	public static final RegistryObject<Item> SKYJADE_TOOLS_SWORD = HELPER.createItem("skyjade_sword", () -> new SkyjadeToolsSwordItem());
	public static final RegistryObject<Item> SKYJADE_TOOLS_PICKAXE = HELPER.createItem("skyjade_pickaxe", () -> new SkyjadeToolsPickaxeItem(DeepAetherModTiers.SKYJADE, 1, -3f, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> SKYJADE_TOOLS_AXE = HELPER.createItem("skyjade_axe", () -> new SkyjadeToolsAxeItem(DeepAetherModTiers.SKYJADE, 1, -3f, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> SKYJADE_TOOLS_SHOVEL = HELPER.createItem("skyjade_shovel", () -> new SkyjadeToolsShovelItem(DeepAetherModTiers.SKYJADE, 1, -3f, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> SKYJADE_TOOLS_HOE = HELPER.createItem("skyjade_hoe", () -> new SkyjadeToolsHoeItem(DeepAetherModTiers.SKYJADE, 0, -3f, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	public static final RegistryObject<Item> SKYJADE_ARMOR_HELMET = HELPER.createItem("skyjade_armor_helmet", () -> new SkyjadeArmorItem.Helmet());
	public static final RegistryObject<Item> SKYJADE_ARMOR_CHESTPLATE = HELPER.createItem("skyjade_armor_chestplate", () -> new SkyjadeArmorItem.Chestplate());
	public static final RegistryObject<Item> SKYJADE_ARMOR_LEGGINGS = HELPER.createItem("skyjade_armor_leggings", () -> new SkyjadeArmorItem.Leggings());
	public static final RegistryObject<Item> SKYJADE_ARMOR_BOOTS = HELPER.createItem("skyjade_armor_boots", () -> new SkyjadeArmorItem.Boots());
	public static final RegistryObject<Item> SKYJADE_GLOVES = HELPER.createItem("skyjade_gloves", () -> new DeepAetherGlovesItem(0.75, "skyjade_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().defaultDurability(2031).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> SKYJADE_RING = HELPER.createItem("skyjade_ring", () -> new RingItem(AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ZANITE_RING, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1)));

	public static final RegistryObject<Item> CLOUDIUM_HELMET = HELPER.createItem("cloudium_helmet", () -> new CloudiumAbility(CloudiumArmorItem.CLOUDIUM, EquipmentSlot.HEAD, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_CHESTPLATE = HELPER.createItem("cloudium_chestplate", () -> new ArmorItem(CloudiumArmorItem.CLOUDIUM, EquipmentSlot.CHEST, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_LEGGINGS = HELPER.createItem("cloudium_leggings", () -> new ArmorItem(CloudiumArmorItem.CLOUDIUM, EquipmentSlot.LEGS, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_BOOTS = HELPER.createItem("cloudium_boots", () -> new ArmorItem(CloudiumArmorItem.CLOUDIUM, EquipmentSlot.FEET, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_GLOVES = HELPER.createItem("cloudium_gloves", () -> new DeepAetherGlovesItem(1.0, "cloudium_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().defaultDurability(2031).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_RING = HELPER.createItem("cloudium_ring", () -> new CloudiumRing(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1)));

	public static final RegistryObject<Item> CLOUDIUM_SWORD = HELPER.createItem("cloudium_sword", () -> new CloudiumSwordItem(Tiers.NETHERITE, 3, -2.4F, (new Item.Properties()).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_SHOVEL = HELPER.createItem("cloudium_shovel", () -> new CloudiumShovelItem(Tiers.NETHERITE, 1.5F, -3.0F, (new Item.Properties()).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_PICKAXE = HELPER.createItem("cloudium_pickaxe", () -> new CloudiumPickaxeItem(Tiers.NETHERITE, 1, -2.8F, (new Item.Properties()).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_AXE = HELPER.createItem("cloudium_axe", () -> new CloudiumAxeItem(Tiers.NETHERITE, 5.0F, -3.0F, (new Item.Properties()).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_HOE = HELPER.createItem("cloudium_hoe", () -> new CloudiumHoeItem(Tiers.NETHERITE, -4, 0.0F, (new Item.Properties()).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	// ORES
	public static final RegistryObject<Item> SKYJADE = HELPER.createItem("skyjade", () -> new SkyjadeItem());
	public static final RegistryObject<Item> ADIBIUM_GEMSTONE = HELPER.createItem("adibium_gemstone", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> AGATE_GEMSTONE = HELPER.createItem("agate_gemstone", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_INGOT = HELPER.createItem("cloudium_ingot", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	public static final RegistryObject<Item> CLOUDIUM_SCRAP = HELPER.createItem("cloudium_scrap", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> RAW_ORATIE = HELPER.createItem("raw_oratie", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> ORATIE_INGOT = HELPER.createItem("oratie_ingot", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	// FOOD
	public static final RegistryObject<Item> RAW_QUAIL = HELPER.createItem("raw_quail", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).food(Foods.CHICKEN)));
	public static final RegistryObject<Item> COOKED_QUAIL = HELPER.createItem("cooked_quail", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).food(Foods.COOKED_CHICKEN)));
	public static final RegistryObject<Item> RAW_AERGLOW_FISH = HELPER.createItem("raw_aerglow_fish", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).food(Foods.COD)));
	public static final RegistryObject<Item> COOKED_AERGLOW_FISH = HELPER.createItem("cooked_aerglow_fish", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).food(Foods.COOKED_COD)));


	// MISC
	public static final RegistryObject<Item> AERGLOW_FISH_EGG = HELPER.createItem("aether_fish_spawn_egg",
			() -> new ForgeSpawnEggItem(DeepAetherModEntities.AETHER_FISH, 33323, 42424, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));
	public static final RegistryObject<Item> QUAIL_EGG = HELPER.createItem("quail_spawn_egg",
			() -> new ForgeSpawnEggItem(DeepAetherModEntities.QUAIL,24433,32114, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));

	public static final RegistryObject<Item> MUSIC_DISC_NABOORU = HELPER.createItem("music_disc_nabooru", () -> new RecordItem(0, DeepAetherModSounds.NABOORU.get(),
			new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1).rarity(Rarity.RARE), 100));
	public static final RegistryObject<Item> MUSIC_DISC_A_MORNING_WISH = HELPER.createItem("music_disc_a_morning_wish", () -> new RecordItem(0, DeepAetherModSounds.A_MORNING_WISH.get(),
			new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1).rarity(Rarity.RARE), 100));

	public static final RegistryObject<Item> PLACEABLE_POISON_BUCKET = HELPER.createItem("poison_bucket",
			() -> new BucketItem(DeepAetherModFluids.POISON_FLUID, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	public static final RegistryObject<Item> VIRULENT_QUICKSAND_BUCKET = HELPER.createItem("virulent_quicksand_bucket",
			() -> new SolidBucketItem(DeepAetherModBlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	public static final RegistryObject<Item> SKYROOT_VIRULENT_QUICKSAND_BUCKET = HELPER.createItem("skyroot_virulent_quicksand_bucket",
			() -> new SolidBucketItem(DeepAetherModBlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	public static final RegistryObject<Item> AERGLOW_PETAL = HELPER.createItem("aerglow_petal", () -> new AerglowPetalItem());

	//SUPPORT


	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return HELPER.createItem(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}

	private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block, CreativeModeTab tab) {
		return HELPER.createItem(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
