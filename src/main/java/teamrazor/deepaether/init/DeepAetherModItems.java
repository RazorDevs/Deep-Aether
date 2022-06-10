
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package teamrazor.deepaether.init;

import teamrazor.deepaether.item.SkyjadeToolsSwordItem;
import teamrazor.deepaether.item.SkyjadeToolsShovelItem;
import teamrazor.deepaether.item.SkyjadeToolsPickaxeItem;
import teamrazor.deepaether.item.SkyjadeToolsHoeItem;
import teamrazor.deepaether.item.SkyjadeToolsAxeItem;
import teamrazor.deepaether.item.SkyjadeItem;
import teamrazor.deepaether.item.SkyjadeArmorItem;
import teamrazor.deepaether.item.AerglowPetalItem;
import teamrazor.deepaether.DeepAetherMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

public class DeepAetherModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, DeepAetherMod.MODID);
	public static final RegistryObject<Item> AERGLOW_GRASS_BLOCK = block(DeepAetherModBlocks.AERGLOW_GRASS_BLOCK,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> AERGLOW_GRASS_PATH = block(DeepAetherModBlocks.AERGLOW_GRASS_PATH,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> SKYJADE_ORE = block(DeepAetherModBlocks.SKYJADE_ORE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> SKYJADE_BLOCK = block(DeepAetherModBlocks.SKYJADE_BLOCK, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> AERGLOW_PETAL_BLOCK = block(DeepAetherModBlocks.AERGLOW_PETAL_BLOCK,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_WOOD = block(DeepAetherModBlocks.ROSE_WOOD, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_LOG = block(DeepAetherModBlocks.ROSE_LOG, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_WOOD_WALL = block(DeepAetherModBlocks.ROSE_WOOD_WALL, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_ROSE_WOOD = block(DeepAetherModBlocks.STRIPPED_ROSE_WOOD,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_ROSE_WOOD_LOG = block(DeepAetherModBlocks.STRIPPED_ROSE_WOOD_LOG,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> STRIPPED_ROSE_WOOD_WALL = block(DeepAetherModBlocks.STRIPPED_ROSE_WOOD_WALL,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_PLANKS = block(DeepAetherModBlocks.ROSE_PLANKS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_STAIRS = block(DeepAetherModBlocks.ROSE_STAIRS, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_SLAB = block(DeepAetherModBlocks.ROSE_SLAB, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_FENCE = block(DeepAetherModBlocks.ROSE_FENCE, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_FENCE_GATE = block(DeepAetherModBlocks.ROSE_FENCE_GATE,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_PRESSURE_PLATE = block(DeepAetherModBlocks.ROSE_PRESSURE_PLATE,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_BUTTON = block(DeepAetherModBlocks.ROSE_BUTTON, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_WOOD_DOOR = doubleBlock(DeepAetherModBlocks.ROSE_WOOD_DOOR,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_WOOD_TRAPDOOR = block(DeepAetherModBlocks.ROSE_WOOD_TRAPDOOR,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> ROSE_LEAVES = block(DeepAetherModBlocks.ROSE_LEAVES, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> FLOWERING_ROSE_LEAVES = block(DeepAetherModBlocks.FLOWERING_ROSE_LEAVES,
			DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> RADIANT_ORCHID = block(DeepAetherModBlocks.RADIANT_ORCHID, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Item> AERGLOW_PETAL = REGISTRY.register("aerglow_petal", () -> new AerglowPetalItem());
	public static final RegistryObject<Item> SKYJADE = REGISTRY.register("skyjade", () -> new SkyjadeItem());
	public static final RegistryObject<Item> SKYJADE_TOOLS_SWORD = REGISTRY.register("skyjade_tools_sword", () -> new SkyjadeToolsSwordItem());
	public static final RegistryObject<Item> SKYJADE_TOOLS_PICKAXE = REGISTRY.register("skyjade_tools_pickaxe", () -> new SkyjadeToolsPickaxeItem());
	public static final RegistryObject<Item> SKYJADE_TOOLS_AXE = REGISTRY.register("skyjade_tools_axe", () -> new SkyjadeToolsAxeItem());
	public static final RegistryObject<Item> SKYJADE_TOOLS_SHOVEL = REGISTRY.register("skyjade_tools_shovel", () -> new SkyjadeToolsShovelItem());
	public static final RegistryObject<Item> SKYJADE_TOOLS_HOE = REGISTRY.register("skyjade_tools_hoe", () -> new SkyjadeToolsHoeItem());
	public static final RegistryObject<Item> SKYJADE_ARMOR_HELMET = REGISTRY.register("skyjade_armor_helmet", () -> new SkyjadeArmorItem.Helmet());
	public static final RegistryObject<Item> SKYJADE_ARMOR_CHESTPLATE = REGISTRY.register("skyjade_armor_chestplate",
			() -> new SkyjadeArmorItem.Chestplate());
	public static final RegistryObject<Item> SKYJADE_ARMOR_LEGGINGS = REGISTRY.register("skyjade_armor_leggings",
			() -> new SkyjadeArmorItem.Leggings());
	public static final RegistryObject<Item> SKYJADE_ARMOR_BOOTS = REGISTRY.register("skyjade_armor_boots", () -> new SkyjadeArmorItem.Boots());

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}

	private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
