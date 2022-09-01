
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package teamrazor.deepaether.init;


import net.minecraft.core.Direction;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import teamrazor.deepaether.block.*;
import teamrazor.deepaether.DeepAetherMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

public class DeepAetherModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, DeepAetherMod.MODID);
	public static final RegistryObject<Block> GREOTITE_SLAB = REGISTRY.register("greotite_slab", () -> new GreotiteSlabBlock());
	public static final RegistryObject<Block> POLISHED_GREOTITE_SLAB = REGISTRY.register("polished_greotite_slab",
			() -> new PolishedGreotiteSlabBlock());
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_SLAB = REGISTRY.register("polished_greotite_bricks_slab",
			() -> new PolishedGreotiteBricksSlabBlock());
	public static final RegistryObject<Block> GREOTITE_STAIRS = REGISTRY.register("greotite_stairs", () -> new GreotiteStairsBlock());
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_STAIRS = REGISTRY.register("polished_greotite_bricks_stairs",
			() -> new PolishedGreotiteBricksStairsBlock());
	public static final RegistryObject<Block> POLISHED_GREOTITE_STAIRS = REGISTRY.register("polished_greotite_stairs",
			() -> new PolishedGreotiteStairsBlock());
	public static final RegistryObject<Block> GREOTITE_WALL = REGISTRY.register("greotite_wall", () -> new GreotiteWallBlock());
	public static final RegistryObject<Block> DARKERITE_SLAB = REGISTRY.register("darkerite_slab", () -> new DarkeriteSlabBlock());
	public static final RegistryObject<Block> POLISHED_DARKERITE_SLAB = REGISTRY.register("polished_darkerite_slab",
			() -> new PolishedDarkeriteSlabBlock());
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_SLAB = REGISTRY.register("polished_darkerite_bricks_slab",
			() -> new PolishedDarkeriteBricksSlabBlock());
	public static final RegistryObject<Block> DARKERITE_STAIRS = REGISTRY.register("darkerite_stairs", () -> new DarkeriteStairsBlock());
	public static final RegistryObject<Block> POLISHED_DARKERITE_STAIRS = REGISTRY.register("polished_darkerite_stairs",
			() -> new PolishedDarkeriteStairsBlock());
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_STAIRS = REGISTRY.register("polished_darkerite_bricks_stairs",
			() -> new PolishedDarkeriteBricksStairsBlock());
	public static final RegistryObject<Block> DARKERITE_WALL = REGISTRY.register("darkerite_wall", () -> new DarkeriteWallBlock());
	public static final RegistryObject<Block> ASETERITE_WALL = REGISTRY.register("aseterite_wall", () -> new AseteriteWallBlock());
	public static final RegistryObject<Block> JARINITE_SLAB = REGISTRY.register("jarinite_slab", () -> new JariniteSlabBlock());
	public static final RegistryObject<Block> POLISHED_JARINITE_SLAB = REGISTRY.register("polished_jarinite_slab",
			() -> new PolishedJariniteSlabBlock());
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_SLAB = REGISTRY.register("polished_jarinite_bricks_slab",
			() -> new PolishedJariniteBricksSlabBlock());
	public static final RegistryObject<Block> JARINITE_STAIRS = REGISTRY.register("jarinite_stairs", () -> new JariniteStairsBlock());
	public static final RegistryObject<Block> POLISHED_JARINITE_STAIRS = REGISTRY.register("polished_jarinite_stairs",
			() -> new PolishedJariniteStairsBlock());
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_STAIRS = REGISTRY.register("polished_jarinite_bricks_stairs",
			() -> new PolishedJariniteBricksStairsBlock());
	public static final RegistryObject<Block> JARINITE_WALL = REGISTRY.register("jarinite_wall", () -> new JariniteWallBlock());
	public static final RegistryObject<Block> CLORITE_SLAB = REGISTRY.register("clorite_slab", () -> new CloriteSlabBlock());
	public static final RegistryObject<Block> POLISHED_CLORITE_SLAB = REGISTRY.register("polished_clorite_slab",
			() -> new PolishedCloriteSlabBlock());
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_SLAB = REGISTRY.register("polished_clorite_bricks_slab",
			() -> new PolishedCloriteBricksSlabBlock());
	public static final RegistryObject<Block> CLORITE_STAIRS = REGISTRY.register("clorite_stairs", () -> new CloriteStairsBlock());
	public static final RegistryObject<Block> POLISHED_CLORITE_STAIRS = REGISTRY.register("polished_clorite_stairs",
			() -> new PolishedCloriteStairsBlock());
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_STAIRS = REGISTRY.register("polished_clorite_bricks_stairs",
			() -> new PolishedCloriteBricksStairsBlock());
	public static final RegistryObject<Block> CLORITE_WALL = REGISTRY.register("clorite_wall", () -> new CloriteWallBlock());
	public static final RegistryObject<Block> YALLESITE_SLAB = REGISTRY.register("yallesite_slab", () -> new YallesiteSlabBlock());
	public static final RegistryObject<Block> POLISHED_YALLESITE_SLAB = REGISTRY.register("polished_yallesite_slab",
			() -> new PolishedYallesiteSlabBlock());
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_SLAB = REGISTRY.register("polished_yallesite_bricks_slab",
			() -> new PolishedYallesiteBricksSlabBlock());
	public static final RegistryObject<Block> YALLESITE_STAIRS = REGISTRY.register("yallesite_stairs", () -> new YallesiteStairsBlock());
	public static final RegistryObject<Block> POLISHED_YALLESITE_STAIRS = REGISTRY.register("polished_yallesite_stairs",
			() -> new PolishedYallesiteStairsBlock());
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_STAIRS = REGISTRY.register("polished_yallesite_bricks_stairs",
			() -> new PolishedYallesiteBricksStairsBlock());


	public static final RegistryObject<Block> AERGLOW_GRASS_BLOCK = REGISTRY.register("aerglow_grass_block", () -> new AerglowGrassBlockBlock());
	public static final RegistryObject<Block> AERGLOW_GRASS_PATH = REGISTRY.register("aerglow_grass_path", () -> new AerglowGrassPathBlock());
	public static final RegistryObject<Block> SKYJADE_ORE = REGISTRY.register("skyjade_ore", () -> new SkyjadeOreBlock());
	public static final RegistryObject<Block> SKYJADE_BLOCK = REGISTRY.register("skyjade_block", () -> new SkyjadeBlockBlock());
	public static final RegistryObject<Block> AERGLOW_PETAL_BLOCK = REGISTRY.register("aerglow_petal_block", () -> new AerglowPetalBlockBlock());
	public static final RegistryObject<Block> ROSE_WOOD = REGISTRY.register("rose_wood", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> ROSE_LOG = REGISTRY.register("rose_log", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> ROSE_WOOD_WALL = REGISTRY.register("rose_wood_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_ROSE_WOOD = REGISTRY.register("stripped_rose_wood", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_ROSE_WOOD_LOG = REGISTRY.register("stripped_rose_wood_log", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_ROSE_WOOD_WALL = REGISTRY.register("stripped_rose_wood_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> ROSE_PLANKS = REGISTRY.register("rose_planks", () -> new RosePlanksBlock());
	public static final RegistryObject<Block> ROSE_STAIRS = REGISTRY.register("rose_stairs", () -> new RoseStairsBlock());
	public static final RegistryObject<Block> ROSE_SLAB = REGISTRY.register("rose_slab", () -> new RoseSlabBlock());
	public static final RegistryObject<Block> ROSE_FENCE = REGISTRY.register("rose_fence", () -> new RoseFenceBlock());
	public static final RegistryObject<Block> ROSE_FENCE_GATE = REGISTRY.register("rose_fence_gate", () -> new RoseFenceGateBlock());
	public static final RegistryObject<Block> ROSE_PRESSURE_PLATE = REGISTRY.register("rose_pressure_plate", () -> new RosePressurePlateBlock());
	public static final RegistryObject<Block> ROSE_BUTTON = REGISTRY.register("rose_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_BUTTON)));
	public static final RegistryObject<Block> ROSE_WOOD_DOOR = REGISTRY.register("rose_wood_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
	public static final RegistryObject<Block> ROSE_WOOD_TRAPDOOR = REGISTRY.register("rose_wood_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR)));
	public static final RegistryObject<Block> ROSE_LEAVES = REGISTRY.register("rose_leaves", () -> new RoseLeavesBlock());
	public static final RegistryObject<Block> FLOWERING_ROSE_LEAVES = REGISTRY.register("flowering_rose_leaves",
			() -> new FloweringRoseLeavesBlock());
	public static final RegistryObject<Block> RADIANT_ORCHID = REGISTRY.register("radiant_orchid", () -> new RadiantOrchidBlock());
	public static final RegistryObject<Block> ROSEWOOD_SAPLING = REGISTRY.register("rosewood_sapling", () -> new RoseWoodSapling());

	public static final RegistryObject<Block> ASETERITE = REGISTRY.register("aseterite", () -> new Aseterite());

	public static final RegistryObject<Block> POLISHED_ASETERITE = REGISTRY.register("polished_aseterite", () -> new Polished_Aseterite());
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS = REGISTRY.register("polished_aseterite_bricks", () -> new Polished_Aseterite_Bricks());
	public static final RegistryObject<Block> CLORITE = REGISTRY.register("clorite", () -> new Clorite());
	public static final RegistryObject<Block> POLISHED_CLORITE = REGISTRY.register("polished_clorite", () -> new Polished_Clorite());
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS = REGISTRY.register("polished_clorite_bricks", () -> new Polished_Clorite_Bricks());

	public static final RegistryObject<Block> DARKERITE = REGISTRY.register("darkerite", () -> new Darkerite());
	public static final RegistryObject<Block> POLISHED_DARKERITE = REGISTRY.register("polished_darkerite", () -> new Polished_Darkerite());
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS = REGISTRY.register("polished_darkerite_bricks", () -> new Polished_Darkerite_Bricks());
	public static final RegistryObject<Block> POLISHED_ASETERITE_STAIRS = REGISTRY.register("polished_aseterite_stairs", () -> new Polished_Aseterite_Stairs());
	public static final RegistryObject<Block> ASETERITE_STAIRS = REGISTRY.register("aseterite_stairs", () -> new Aseterite_Stairs());
	public static final RegistryObject<Block> ASETERITE_SLAB = REGISTRY.register("aseterite_slab", () -> new Aseterite_Slab());
	public static final RegistryObject<Block> POLISHED_ASETERITE_SLAB = REGISTRY.register("polished_aseterite_slab", () -> new Polished_Aseterite_Slab());
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_STAIRS = REGISTRY.register("polished_aseterite_bricks_stairs", () -> new Polished_Aseterite_Bricks_Stairs());

	public static final RegistryObject<Block> ASETERITE_PILLAR = REGISTRY.register("aseterite_pillar", () -> new Aseterite_Pillar());

	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_SLAB = REGISTRY.register("polished_aseterite_bricks_slab", () -> new Polished_Aseterite_Bricks_Slab());
	public static final RegistryObject<Block> GREOTITE = REGISTRY.register("greotite", () -> new Greotite());

	public static final RegistryObject<Block> POLISHED_GREOTITE = REGISTRY.register("polished_greotite", () -> new Polished_Greotite());
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS = REGISTRY.register("polished_greotite_bricks", () -> new Polished_Greotite_Bricks());

	public static final RegistryObject<Block> JARINITE = REGISTRY.register("jarinite", () -> new Jarinite());
	public static final RegistryObject<Block> POLISHED_JARINITE = REGISTRY.register("polished_jarinite", () -> new Polished_Jarinite());
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS = REGISTRY.register("polished_jarinite_bricks", () -> new Polished_Jarinite_Bricks());
	public static final RegistryObject<Block> YALLESITE = REGISTRY.register("yallesite", () -> new Yallesite());
	public static final RegistryObject<Block> POLISHED_YALLESITE = REGISTRY.register("polished_yallesite", () -> new Polished_Yallesite());
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS = REGISTRY.register("polished_yallesite_bricks", () -> new Polished_Yallesite_Bricks());


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
			RoseWoodSapling.registerRenderLayer();
			GreotiteWallBlock.registerRenderLayer();
			DarkeriteWallBlock.registerRenderLayer();
			AseteriteWallBlock.registerRenderLayer();
			JariniteWallBlock.registerRenderLayer();
			CloriteWallBlock.registerRenderLayer();
			CloriteWallBlock.registerRenderLayer();

		}
	}
}
