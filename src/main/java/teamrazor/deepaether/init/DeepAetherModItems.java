package teamrazor.deepaether.init;


import net.minecraft.sounds.SoundEvents;
import com.gildedgames.aether.client.AetherSoundEvents;
import com.gildedgames.aether.item.accessories.ring.RingItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
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

public class DeepAetherModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, DeepAetherMod.MODID);


	// Block Items ------------------------------------------------------------------------------------


	// GRASS
	//public static final RegistryObject<Item> AERGLOW_GRASS_BLOCK = block(DeepAetherModBlocks.AERGLOW_GRASS_BLOCK, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	//public static final RegistryObject<Item> AERGLOW_GRASS_PATH = block(DeepAetherModBlocks.AERGLOW_GRASS_PATH, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> AETHER_MUD = block(DeepAetherModBlocks.AETHER_MUD, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> PACKED_AETHER_MUD = block(DeepAetherModBlocks.PACKED_AETHER_MUD, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> AETHER_MUD_BRICKS = block(DeepAetherModBlocks.AETHER_MUD_BRICKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> AETHER_MUD_BRICKS_SLAB = block(DeepAetherModBlocks.AETHER_MUD_BRICKS_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> AETHER_MUD_BRICKS_STAIRS = block(DeepAetherModBlocks.AETHER_MUD_BRICKS_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> AETHER_MOSS_BLOCK = block(DeepAetherModBlocks.AETHER_MOSS_BLOCK, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> AETHER_MOSS_CARPET = block(DeepAetherModBlocks.AETHER_MOSS_CARPET, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	// WOOD
	public static final RegistryObject<Item> ROSE_WOOD = block(DeepAetherModBlocks.ROSE_WOOD, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_LOG = block(DeepAetherModBlocks.ROSE_LOG, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_LEAVES = block(DeepAetherModBlocks.ROSE_LEAVES, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> FLOWERING_ROSE_LEAVES = block(DeepAetherModBlocks.FLOWERING_ROSE_LEAVES, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_PLANKS = block(DeepAetherModBlocks.ROSE_PLANKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_ROSE_WOOD = block(DeepAetherModBlocks.STRIPPED_ROSE_WOOD, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_ROSE_LOG = block(DeepAetherModBlocks.STRIPPED_ROSE_LOG, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_WALL = block(DeepAetherModBlocks.ROSE_WALL, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_ROSE_WALL = block(DeepAetherModBlocks.STRIPPED_ROSE_WALL, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_SLAB = block(DeepAetherModBlocks.ROSE_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_STAIRS = block(DeepAetherModBlocks.ROSE_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_FENCE = block(DeepAetherModBlocks.ROSE_FENCE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_FENCE_GATE = block(DeepAetherModBlocks.ROSE_FENCE_GATE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_PRESSURE_PLATE = block(DeepAetherModBlocks.ROSE_PRESSURE_PLATE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_BUTTON = block(DeepAetherModBlocks.ROSE_BUTTON, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_DOOR = doubleBlock(DeepAetherModBlocks.ROSE_DOOR, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_TRAPDOOR = block(DeepAetherModBlocks.ROSE_TRAPDOOR, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSEWOOD_SAPLING = block(DeepAetherModBlocks.ROSEWOOD_SAPLING, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_SIGN = REGISTRY.register("rose_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB), DeepAetherModBlocks.ROSE_SIGN.get(), DeepAetherModBlocks.ROSE_WALL_SIGN.get()));
	public static final RegistryObject<Item> ROSE_BOAT = REGISTRY.register("rose_boat", () -> new DeepAetherModBoatItem(false, DeepAetherModBoat.Type.ROSE, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));
	public static final RegistryObject<Item> ROSE_CHEST_BOAT = REGISTRY.register("rose_chest_boat", () -> new DeepAetherModBoatItem(true, DeepAetherModBoat.Type.ROSE, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));


	public static final RegistryObject<Item> YAGROOT_WOOD = block(DeepAetherModBlocks.YAGROOT_WOOD, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_LOG = block(DeepAetherModBlocks.YAGROOT_LOG, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_LEAVES = block(DeepAetherModBlocks.YAGROOT_LEAVES, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_PLANKS = block(DeepAetherModBlocks.YAGROOT_PLANKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_YAGROOT_WOOD = block(DeepAetherModBlocks.STRIPPED_YAGROOT_WOOD, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_YAGROOT_LOG = block(DeepAetherModBlocks.STRIPPED_YAGROOT_LOG, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_WALL = block(DeepAetherModBlocks.YAGROOT_WALL, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_YAGROOT_WALL = block(DeepAetherModBlocks.STRIPPED_YAGROOT_WALL, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_SLAB = block(DeepAetherModBlocks.YAGROOT_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_STAIRS = block(DeepAetherModBlocks.YAGROOT_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_FENCE = block(DeepAetherModBlocks.YAGROOT_FENCE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_FENCE_GATE = block(DeepAetherModBlocks.YAGROOT_FENCE_GATE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_PRESSURE_PLATE = block(DeepAetherModBlocks.YAGROOT_PRESSURE_PLATE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_BUTTON = block(DeepAetherModBlocks.YAGROOT_BUTTON, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_DOOR = doubleBlock(DeepAetherModBlocks.YAGROOT_DOOR, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_TRAPDOOR = block(DeepAetherModBlocks.YAGROOT_TRAPDOOR, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_SAPLING = block(DeepAetherModBlocks.YAGROOT_SAPLING, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_ROOTS = block(DeepAetherModBlocks.YAGROOT_ROOTS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> MUDDY_YAGROOT_ROOTS = block(DeepAetherModBlocks.MUDDY_YAGROOT_ROOTS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_VINE = block(DeepAetherModBlocks.YAGROOT_VINE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YAGROOT_SIGN = REGISTRY.register("yagroot_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB), DeepAetherModBlocks.YAGROOT_SIGN.get(), DeepAetherModBlocks.YAGROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> YAGROOT_BOAT = REGISTRY.register("yagroot_boat", () -> new DeepAetherModBoatItem(false, DeepAetherModBoat.Type.YAGROOT, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));
	public static final RegistryObject<Item> YAGROOT_CHEST_BOAT = REGISTRY.register("yagroot_chest_boat", () -> new DeepAetherModBoatItem(true, DeepAetherModBoat.Type.YAGROOT, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));

	public static final RegistryObject<Item> CRUDEROOT_WOOD = block(DeepAetherModBlocks.CRUDEROOT_WOOD, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_LOG = block(DeepAetherModBlocks.CRUDEROOT_LOG, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_LEAVES = block(DeepAetherModBlocks.CRUDEROOT_LEAVES, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_PLANKS = block(DeepAetherModBlocks.CRUDEROOT_PLANKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_CRUDEROOT_WOOD = block(DeepAetherModBlocks.STRIPPED_CRUDEROOT_WOOD, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_CRUDEROOT_LOG = block(DeepAetherModBlocks.STRIPPED_CRUDEROOT_LOG, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_WALL = block(DeepAetherModBlocks.CRUDEROOT_WALL, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_CRUDEROOT_WALL = block(DeepAetherModBlocks.STRIPPED_CRUDEROOT_WALL, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_SLAB = block(DeepAetherModBlocks.CRUDEROOT_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_STAIRS = block(DeepAetherModBlocks.CRUDEROOT_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_FENCE = block(DeepAetherModBlocks.CRUDEROOT_FENCE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_FENCE_GATE = block(DeepAetherModBlocks.CRUDEROOT_FENCE_GATE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_PRESSURE_PLATE = block(DeepAetherModBlocks.CRUDEROOT_PRESSURE_PLATE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_BUTTON = block(DeepAetherModBlocks.CRUDEROOT_BUTTON, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_DOOR = doubleBlock(DeepAetherModBlocks.CRUDEROOT_DOOR, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_TRAPDOOR = block(DeepAetherModBlocks.CRUDEROOT_TRAPDOOR, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_SAPLING = block(DeepAetherModBlocks.CRUDEROOT_SAPLING, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CRUDEROOT_SIGN = REGISTRY.register("cruderoot_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB), DeepAetherModBlocks.CRUDEROOT_SIGN.get(), DeepAetherModBlocks.CRUDEROOT_WALL_SIGN.get()));
	public static final RegistryObject<Item> CRUDEROOT_BOAT = REGISTRY.register("cruderoot_boat", () -> new DeepAetherModBoatItem(false, DeepAetherModBoat.Type.CRUDEROOT, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));
	public static final RegistryObject<Item> CRUDEROOT_CHEST_BOAT = REGISTRY.register("cruderoot_chest_boat", () -> new DeepAetherModBoatItem(true, DeepAetherModBoat.Type.CRUDEROOT, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));


	// ORES
	public static final RegistryObject<Item> SKYJADE_ORE = block(DeepAetherModBlocks.SKYJADE_ORE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> SKYJADE_BLOCK = block(DeepAetherModBlocks.SKYJADE_BLOCK, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> AGATE_ORE = block(DeepAetherModBlocks.AGATE_ORE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> HIGHSTONE_AGATE_ORE = block(DeepAetherModBlocks.HIGHSTONE_AGATE_ORE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> AGATE_BLOCK = block(DeepAetherModBlocks.AGATE_BLOCK, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> ADIBIUM_ORE = block(DeepAetherModBlocks.ADIBIUM_ORE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> HIGHSTONE_ADIBIUM_ORE = block(DeepAetherModBlocks.HIGHSTONE_ADIBIUM_ORE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ADIBIUM_BLOCK = block(DeepAetherModBlocks.ADIBIUM_BLOCK, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> CLOUDIUM_DEBRIS = block(DeepAetherModBlocks.CLOUDIUM_DEBRIS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CLOUDIUM_BLOCK = block(DeepAetherModBlocks.CLOUDIUM_BLOCK, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> ORATIE_ORE = block(DeepAetherModBlocks.ORATIE_ORE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> HIGHSTONE_ORATIE_ORE = block(DeepAetherModBlocks.HIGHSTONE_ORATIE_ORE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> RAW_ORATIE_BLOCK = block(DeepAetherModBlocks.RAW_ORATIE_BLOCK, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ORATIE_BLOCK = block(DeepAetherModBlocks.ORATIE_BLOCK, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> ASETERITE = block(DeepAetherModBlocks.ASETERITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_ASETERITE = block(DeepAetherModBlocks.POLISHED_ASETERITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_ASETERITE_BRICKS = block(DeepAetherModBlocks.POLISHED_ASETERITE_BRICKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ASETERITE_STAIRS = block(DeepAetherModBlocks.ASETERITE_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_ASETERITE_STAIRS = block(DeepAetherModBlocks.POLISHED_ASETERITE_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_ASETERITE_BRICKS_STAIRS = block(DeepAetherModBlocks.POLISHED_ASETERITE_BRICKS_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ASETERITE_SLAB = block(DeepAetherModBlocks.ASETERITE_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_ASETERITE_SLAB = block(DeepAetherModBlocks.POLISHED_ASETERITE_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_ASETERITE_BRICKS_SLAB = block(DeepAetherModBlocks.POLISHED_ASETERITE_BRICKS_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ASETERITE_WALL = block(DeepAetherModBlocks.ASETERITE_WALL, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> GREOTITE = block(DeepAetherModBlocks.GREOTITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_GREOTITE = block(DeepAetherModBlocks.POLISHED_GREOTITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_GREOTITE_BRICKS = block(DeepAetherModBlocks.POLISHED_GREOTITE_BRICKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> GREOTITE_SLAB = block(DeepAetherModBlocks.GREOTITE_SLAB,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_GREOTITE_SLAB = block(DeepAetherModBlocks.POLISHED_GREOTITE_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_GREOTITE_BRICKS_SLAB = block(DeepAetherModBlocks.POLISHED_GREOTITE_BRICKS_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> GREOTITE_STAIRS = block(DeepAetherModBlocks.GREOTITE_STAIRS,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_GREOTITE_BRICKS_STAIRS = block(DeepAetherModBlocks.POLISHED_GREOTITE_BRICKS_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_GREOTITE_STAIRS = block(DeepAetherModBlocks.POLISHED_GREOTITE_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> GREOTITE_WALL = block(DeepAetherModBlocks.GREOTITE_WALL,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> JARINITE = block(DeepAetherModBlocks.JARINITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_JARINITE = block(DeepAetherModBlocks.POLISHED_JARINITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_JARINITE_BRICKS = block(DeepAetherModBlocks.POLISHED_JARINITE_BRICKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> JARINITE_SLAB = block(DeepAetherModBlocks.JARINITE_SLAB,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_JARINITE_SLAB = block(DeepAetherModBlocks.POLISHED_JARINITE_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_JARINITE_BRICKS_SLAB = block(DeepAetherModBlocks.POLISHED_JARINITE_BRICKS_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> JARINITE_STAIRS = block(DeepAetherModBlocks.JARINITE_STAIRS,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_JARINITE_STAIRS = block(DeepAetherModBlocks.POLISHED_JARINITE_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_JARINITE_BRICKS_STAIRS = block(DeepAetherModBlocks.POLISHED_JARINITE_BRICKS_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> JARINITE_WALL = block(DeepAetherModBlocks.JARINITE_WALL,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> DARKERITE = block(DeepAetherModBlocks.DARKERITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_DARKERITE = block(DeepAetherModBlocks.POLISHED_DARKERITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_DARKERITE_BRICKS = block(DeepAetherModBlocks.POLISHED_DARKERITE_BRICKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> DARKERITE_SLAB = block(DeepAetherModBlocks.DARKERITE_SLAB,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_DARKERITE_SLAB = block(DeepAetherModBlocks.POLISHED_DARKERITE_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_DARKERITE_BRICKS_SLAB = block(DeepAetherModBlocks.POLISHED_DARKERITE_BRICKS_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> DARKERITE_STAIRS = block(DeepAetherModBlocks.DARKERITE_STAIRS,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_DARKERITE_STAIRS = block(DeepAetherModBlocks.POLISHED_DARKERITE_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_DARKERITE_BRICKS_STAIRS = block(DeepAetherModBlocks.POLISHED_DARKERITE_BRICKS_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> DARKERITE_WALL = block(DeepAetherModBlocks.DARKERITE_WALL,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> CLORITE = block(DeepAetherModBlocks.CLORITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_CLORITE = block(DeepAetherModBlocks.POLISHED_CLORITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_CLORITE_BRICKS = block(DeepAetherModBlocks.POLISHED_CLORITE_BRICKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CLORITE_SLAB = block(DeepAetherModBlocks.CLORITE_SLAB,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_CLORITE_SLAB = block(DeepAetherModBlocks.POLISHED_CLORITE_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_CLORITE_BRICKS_SLAB = block(DeepAetherModBlocks.POLISHED_CLORITE_BRICKS_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CLORITE_STAIRS = block(DeepAetherModBlocks.CLORITE_STAIRS,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_CLORITE_STAIRS = block(DeepAetherModBlocks.POLISHED_CLORITE_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_CLORITE_BRICKS_STAIRS = block(DeepAetherModBlocks.POLISHED_CLORITE_BRICKS_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> CLORITE_WALL = block(DeepAetherModBlocks.CLORITE_WALL,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> YALLESITE_SLAB = block(DeepAetherModBlocks.YALLESITE_SLAB,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_YALLESITE_SLAB = block(DeepAetherModBlocks.POLISHED_YALLESITE_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_YALLESITE_BRICKS_SLAB = block(DeepAetherModBlocks.POLISHED_YALLESITE_BRICKS_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YALLESITE_STAIRS = block(DeepAetherModBlocks.YALLESITE_STAIRS,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_YALLESITE_STAIRS = block(DeepAetherModBlocks.POLISHED_YALLESITE_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_YALLESITE_BRICKS_STAIRS = block(DeepAetherModBlocks.POLISHED_YALLESITE_BRICKS_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YALLESITE = block(DeepAetherModBlocks.YALLESITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_YALLESITE = block(DeepAetherModBlocks.POLISHED_YALLESITE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> POLISHED_YALLESITE_BRICKS = block(DeepAetherModBlocks.POLISHED_YALLESITE_BRICKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> YALLESITE_WALL = block(DeepAetherModBlocks.YALLESITE_WALL,DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Item> HOLYSTONE_BRICKS = block(DeepAetherModBlocks.HOLYSTONE_BRICKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> HOLYSTONE_BRICKS_SLAB = block(DeepAetherModBlocks.HOLYSTONE_BRICKS_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> HOLYSTONE_BRICKS_STAIRS = block(DeepAetherModBlocks.HOLYSTONE_BRICKS_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	// MISC
	public static final RegistryObject<Item> AERGLOW_PETAL_BLOCK = block(DeepAetherModBlocks.AERGLOW_PETAL_BLOCK, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> RADIANT_ORCHID = block(DeepAetherModBlocks.RADIANT_ORCHID, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> LAVENDER = block(DeepAetherModBlocks.LAVENDER, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);


	// Standalone Items ------------------------------------------------------------------------------------


	// EQUIPMENT
	public static final RegistryObject<Item> SKYJADE_TOOLS_SWORD = REGISTRY.register("skyjade_sword", () -> new SkyjadeToolsSwordItem());
	public static final RegistryObject<Item> SKYJADE_TOOLS_PICKAXE = REGISTRY.register("skyjade_pickaxe", () -> new SkyjadeToolsPickaxeItem(DeepAetherModTiers.SKYJADE, 1, -3f, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> SKYJADE_TOOLS_AXE = REGISTRY.register("skyjade_axe", () -> new SkyjadeToolsAxeItem(DeepAetherModTiers.SKYJADE, 1, -3f, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> SKYJADE_TOOLS_SHOVEL = REGISTRY.register("skyjade_shovel", () -> new SkyjadeToolsShovelItem(DeepAetherModTiers.SKYJADE, 1, -3f, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> SKYJADE_TOOLS_HOE = REGISTRY.register("skyjade_hoe", () -> new SkyjadeToolsHoeItem(DeepAetherModTiers.SKYJADE, 0, -3f, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	public static final RegistryObject<Item> SKYJADE_ARMOR_HELMET = REGISTRY.register("skyjade_armor_helmet", () -> new SkyjadeArmorItem.Helmet());
	public static final RegistryObject<Item> SKYJADE_ARMOR_CHESTPLATE = REGISTRY.register("skyjade_armor_chestplate", () -> new SkyjadeArmorItem.Chestplate());
	public static final RegistryObject<Item> SKYJADE_ARMOR_LEGGINGS = REGISTRY.register("skyjade_armor_leggings", () -> new SkyjadeArmorItem.Leggings());
	public static final RegistryObject<Item> SKYJADE_ARMOR_BOOTS = REGISTRY.register("skyjade_armor_boots", () -> new SkyjadeArmorItem.Boots());
	public static final RegistryObject<Item> SKYJADE_GLOVES = REGISTRY.register("skyjade_gloves", () -> new DeepAetherGlovesItem(1.5, "skyjade_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().defaultDurability(2031).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> SKYJADE_RING = REGISTRY.register("skyjade_ring", () -> new RingItem(AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ZANITE_RING, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1)));

	public static final RegistryObject<Item> CLOUDIUM_HELMET = REGISTRY.register("cloudium_helmet", () -> new CloudiumAbility(DeepAetherArmorMaterial.CLOUDIUM, EquipmentSlot.HEAD, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_CHESTPLATE = REGISTRY.register("cloudium_chestplate", () -> new ArmorItem(DeepAetherArmorMaterial.CLOUDIUM, EquipmentSlot.CHEST, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_LEGGING = REGISTRY.register("cloudium_leggings", () -> new ArmorItem(DeepAetherArmorMaterial.CLOUDIUM, EquipmentSlot.LEGS, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_BOOTS = REGISTRY.register("cloudium_boots", () -> new ArmorItem(DeepAetherArmorMaterial.CLOUDIUM, EquipmentSlot.FEET, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_GLOVES = REGISTRY.register("cloudium_gloves", () -> new DeepAetherGlovesItem(2.0, "cloudium_gloves", AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, new Item.Properties().defaultDurability(2031).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_RING = REGISTRY.register("cloudium_ring", () -> new CloudiumRing(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1)));

	// ORES
	public static final RegistryObject<Item> SKYJADE = REGISTRY.register("skyjade", () -> new SkyjadeItem());
	public static final RegistryObject<Item> ADIBIUM_GEMSTONE = REGISTRY.register("adibium_gemstone", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> AGATE_GEMSTONE = REGISTRY.register("agate_gemstone", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> CLOUDIUM_SCRAP = REGISTRY.register("cloudium_scrap", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> RAW_ORATIE = REGISTRY.register("raw_oratie", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));
	public static final RegistryObject<Item> ORATIE_INGOT = REGISTRY.register("oratie_ingot", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	// FOOD
	public static final RegistryObject<Item> RAW_QUAIL = REGISTRY.register("raw_quail", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).food(Foods.CHICKEN)));
	public static final RegistryObject<Item> COOKED_QUAIL = REGISTRY.register("cooked_quail", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).food(Foods.COOKED_CHICKEN)));
	public static final RegistryObject<Item> RAW_AERGLOW_FISH = REGISTRY.register("raw_aerglow_fish", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).food(Foods.COD)));
	public static final RegistryObject<Item> COOKED_AERGLOW_FISH = REGISTRY.register("cooked_aerglow_fish", () -> new Item(new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).food(Foods.COOKED_COD)));


	// MISC
	public static final RegistryObject<Item> AERGLOW_FISH_EGG = REGISTRY.register("aether_fish_spawn_egg",
			() -> new ForgeSpawnEggItem(DeepAetherModEntities.AETHER_FISH, 33323, 42424, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));
	public static final RegistryObject<Item> QUAIL_EGG = REGISTRY.register("quail_spawn_egg",
			() -> new ForgeSpawnEggItem(DeepAetherModEntities.QUAIL,24433,32114, new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB)));

	public static final RegistryObject<Item> MUSIC_DISC_NABOORU = REGISTRY.register("music_disc_nabooru", () -> new RecordItem(0, DeepAetherModSounds.NABOORU.get(),
			new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1).rarity(Rarity.RARE), 100));
	public static final RegistryObject<Item> MUSIC_DISC_A_MORNING_WISH = REGISTRY.register("music_disc_a_morning_wish", () -> new RecordItem(0, DeepAetherModSounds.A_MORNING_WISH.get(),
			new Item.Properties().tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB).stacksTo(1).rarity(Rarity.RARE), 100));

	public static final RegistryObject<Item> PLACEABLE_POISON_BUCKET = REGISTRY.register("poison_bucket",
			() -> new BucketItem(DeepAetherModFluids.POISON_FLUID, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	public static final RegistryObject<Item> VIRULENT_QUICKSAND_BUCKET = REGISTRY.register("virulent_quicksand_bucket",
			() -> new SolidBucketItem(DeepAetherModBlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	public static final RegistryObject<Item> SKYROOT_VIRULENT_QUICKSAND_BUCKET = REGISTRY.register("skyroot_virulent_quicksand_bucket",
			() -> new SolidBucketItem(DeepAetherModBlocks.VIRULENT_QUICKSAND.get(), SoundEvents.SAND_BREAK, new Item.Properties().stacksTo(1).tab(DeepAetherModTabs.TAB_DEEP_AETHER_ITEMS_TAB)));

	public static final RegistryObject<Item> AERGLOW_PETAL = REGISTRY.register("aerglow_petal", () -> new AerglowPetalItem());



	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}

	private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
