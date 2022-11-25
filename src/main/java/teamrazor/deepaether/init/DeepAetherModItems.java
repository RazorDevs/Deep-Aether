package teamrazor.deepaether.init;


import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.sounds.SoundEvents;
import com.gildedgames.aether.client.AetherSoundEvents;
import com.gildedgames.aether.item.accessories.ring.RingItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.entity.DeepAetherModBoat;
import teamrazor.deepaether.item.*;
import teamrazor.deepaether.DeepAetherMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.level.block.Block;
import teamrazor.deepaether.item.DeepAetherArmorMaterial;
import teamrazor.deepaether.item.abilities.CloudiumAbility;
import teamrazor.deepaether.item.accessory.CloudiumRing;
import teamrazor.deepaether.item.accessory.DeepAetherGlovesItem;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class DeepAetherModItems {
	public static final ItemSubRegistryHelper HELPER = DeepAetherMod.REGISTRY_HELPER.getItemSubHelper();

	// BOATS

	public static final RegistryObject<Item> ROSE_BOAT = HELPER.createItem("rose_boat", () -> new DeepAetherModBoatItem(false, DeepAetherModBoat.Type.ROSE, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));
	public static final RegistryObject<Item> ROSE_CHEST_BOAT = HELPER.createItem("rose_chest_boat", () -> new DeepAetherModBoatItem(true, DeepAetherModBoat.Type.ROSE, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));

	public static final RegistryObject<Item> YAGROOT_BOAT = HELPER.createItem("yagroot_boat", () -> new DeepAetherModBoatItem(false, DeepAetherModBoat.Type.YAGROOT, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));
	public static final RegistryObject<Item> YAGROOT_CHEST_BOAT = HELPER.createItem("yagroot_chest_boat", () -> new DeepAetherModBoatItem(true, DeepAetherModBoat.Type.YAGROOT, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));
	public static final RegistryObject<Item> CRUDEROOT_BOAT = HELPER.createItem("cruderoot_boat", () -> new DeepAetherModBoatItem(false, DeepAetherModBoat.Type.CRUDEROOT, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));
	public static final RegistryObject<Item> CRUDEROOT_CHEST_BOAT = HELPER.createItem("cruderoot_chest_boat", () -> new DeepAetherModBoatItem(true, DeepAetherModBoat.Type.CRUDEROOT, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));

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
	public static final RegistryObject<Item> SKYJADE_GLOVES = HELPER.createItem("skyjade_gloves", () -> new DeepAetherGlovesItem(1.5, "skyjade_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().defaultDurability(2031).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> SKYJADE_RING = HELPER.createItem("skyjade_ring", () -> new RingItem(AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ZANITE_RING, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1)));

	public static final RegistryObject<Item> CLOUDIUM_HELMET = HELPER.createItem("cloudium_helmet", () -> new CloudiumAbility(DeepAetherArmorMaterial.CLOUDIUM, EquipmentSlot.HEAD, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_CHESTPLATE = HELPER.createItem("cloudium_chestplate", () -> new ArmorItem(DeepAetherArmorMaterial.CLOUDIUM, EquipmentSlot.CHEST, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_LEGGING = HELPER.createItem("cloudium_leggings", () -> new ArmorItem(DeepAetherArmorMaterial.CLOUDIUM, EquipmentSlot.LEGS, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_BOOTS = HELPER.createItem("cloudium_boots", () -> new ArmorItem(DeepAetherArmorMaterial.CLOUDIUM, EquipmentSlot.FEET, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_GLOVES = HELPER.createItem("cloudium_gloves", () -> new DeepAetherGlovesItem(2.0, "cloudium_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().defaultDurability(2031).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_RING = HELPER.createItem("cloudium_ring", () -> new CloudiumRing(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1)));

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
