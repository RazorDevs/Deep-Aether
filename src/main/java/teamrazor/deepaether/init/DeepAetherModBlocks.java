
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package teamrazor.deepaether.init;

import teamrazor.deepaether.block.StrippedRoseWoodWallBlock;
import teamrazor.deepaether.block.StrippedRoseWoodLogBlock;
import teamrazor.deepaether.block.StrippedRoseWoodBlock;
import teamrazor.deepaether.block.SkyjadeOreBlock;
import teamrazor.deepaether.block.SkyjadeBlockBlock;
import teamrazor.deepaether.block.RoseWoodWallBlock;
import teamrazor.deepaether.block.RoseWoodTrapdoorBlock;
import teamrazor.deepaether.block.RoseWoodDoorBlock;
import teamrazor.deepaether.block.RoseWoodBlock;
import teamrazor.deepaether.block.RoseStairsBlock;
import teamrazor.deepaether.block.RoseSlabBlock;
import teamrazor.deepaether.block.RosePressurePlateBlock;
import teamrazor.deepaether.block.RosePlanksBlock;
import teamrazor.deepaether.block.RoseLogBlock;
import teamrazor.deepaether.block.RoseLeavesBlock;
import teamrazor.deepaether.block.RoseFenceGateBlock;
import teamrazor.deepaether.block.RoseFenceBlock;
import teamrazor.deepaether.block.RoseButtonBlock;
import teamrazor.deepaether.block.RadiantOrchidBlock;
import teamrazor.deepaether.block.FloweringRoseLeavesBlock;
import teamrazor.deepaether.block.AerglowPetalBlockBlock;
import teamrazor.deepaether.block.AerglowGrassPathBlock;
import teamrazor.deepaether.block.AerglowGrassBlockBlock;
import teamrazor.deepaether.DeepAetherMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

public class DeepAetherModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, DeepAetherMod.MODID);
	public static final RegistryObject<Block> AERGLOW_GRASS_BLOCK = REGISTRY.register("aerglow_grass_block", () -> new AerglowGrassBlockBlock());
	public static final RegistryObject<Block> AERGLOW_GRASS_PATH = REGISTRY.register("aerglow_grass_path", () -> new AerglowGrassPathBlock());
	public static final RegistryObject<Block> SKYJADE_ORE = REGISTRY.register("skyjade_ore", () -> new SkyjadeOreBlock());
	public static final RegistryObject<Block> SKYJADE_BLOCK = REGISTRY.register("skyjade_block", () -> new SkyjadeBlockBlock());
	public static final RegistryObject<Block> AERGLOW_PETAL_BLOCK = REGISTRY.register("aerglow_petal_block", () -> new AerglowPetalBlockBlock());
	public static final RegistryObject<Block> ROSE_WOOD = REGISTRY.register("rose_wood", () -> new RoseWoodBlock());
	public static final RegistryObject<Block> ROSE_LOG = REGISTRY.register("rose_log", () -> new RoseLogBlock());
	public static final RegistryObject<Block> ROSE_WOOD_WALL = REGISTRY.register("rose_wood_wall", () -> new RoseWoodWallBlock());
	public static final RegistryObject<Block> STRIPPED_ROSE_WOOD = REGISTRY.register("stripped_rose_wood", () -> new StrippedRoseWoodBlock());
	public static final RegistryObject<Block> STRIPPED_ROSE_WOOD_LOG = REGISTRY.register("stripped_rose_wood_log",
			() -> new StrippedRoseWoodLogBlock());
	public static final RegistryObject<Block> STRIPPED_ROSE_WOOD_WALL = REGISTRY.register("stripped_rose_wood_wall",
			() -> new StrippedRoseWoodWallBlock());
	public static final RegistryObject<Block> ROSE_PLANKS = REGISTRY.register("rose_planks", () -> new RosePlanksBlock());
	public static final RegistryObject<Block> ROSE_STAIRS = REGISTRY.register("rose_stairs", () -> new RoseStairsBlock());
	public static final RegistryObject<Block> ROSE_SLAB = REGISTRY.register("rose_slab", () -> new RoseSlabBlock());
	public static final RegistryObject<Block> ROSE_FENCE = REGISTRY.register("rose_fence", () -> new RoseFenceBlock());
	public static final RegistryObject<Block> ROSE_FENCE_GATE = REGISTRY.register("rose_fence_gate", () -> new RoseFenceGateBlock());
	public static final RegistryObject<Block> ROSE_PRESSURE_PLATE = REGISTRY.register("rose_pressure_plate", () -> new RosePressurePlateBlock());
	public static final RegistryObject<Block> ROSE_BUTTON = REGISTRY.register("rose_button", () -> new RoseButtonBlock());
	public static final RegistryObject<Block> ROSE_WOOD_DOOR = REGISTRY.register("rose_wood_door", () -> new RoseWoodDoorBlock());
	public static final RegistryObject<Block> ROSE_WOOD_TRAPDOOR = REGISTRY.register("rose_wood_trapdoor", () -> new RoseWoodTrapdoorBlock());
	public static final RegistryObject<Block> ROSE_LEAVES = REGISTRY.register("rose_leaves", () -> new RoseLeavesBlock());
	public static final RegistryObject<Block> FLOWERING_ROSE_LEAVES = REGISTRY.register("flowering_rose_leaves",
			() -> new FloweringRoseLeavesBlock());
	public static final RegistryObject<Block> RADIANT_ORCHID = REGISTRY.register("radiant_orchid", () -> new RadiantOrchidBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			AerglowGrassPathBlock.registerRenderLayer();
			AerglowPetalBlockBlock.registerRenderLayer();
			RoseWoodWallBlock.registerRenderLayer();
			StrippedRoseWoodWallBlock.registerRenderLayer();
			RoseFenceBlock.registerRenderLayer();
			RoseWoodDoorBlock.registerRenderLayer();
			RoseWoodTrapdoorBlock.registerRenderLayer();
			RoseLeavesBlock.registerRenderLayer();
			FloweringRoseLeavesBlock.registerRenderLayer();
			RadiantOrchidBlock.registerRenderLayer();
		}
	}
}
