
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package teamrazor.deepaether.init;


import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
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
import teamrazor.deepaether.block.DeepAetherModLogBlock;

import teamrazor.deepaether.blockentity.DeepAetherModStandingSignBlock;
import teamrazor.deepaether.blockentity.DeepAetherModWallSignBlock;
import teamrazor.deepaether.blockentity.DeepAetherModWoodTypes;
import teamrazor.deepaether.world.feature.tree.CruderootTreeGrower;
import teamrazor.deepaether.world.feature.tree.RosewoodTreeGrower;
import teamrazor.deepaether.world.feature.tree.YagrootTreeGrower;

public class DeepAetherModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, DeepAetherMod.MODID);

	// GRASS - randomTicks() is needed for grass growth.
	//public static final RegistryObject<Block> AERGLOW_GRASS_BLOCK = REGISTRY.register("aerglow_grass_block", () -> new GrassBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.GRAVEL).strength(1f, 10f).randomTicks()));
	//public static final RegistryObject<Block> AERGLOW_GRASS_PATH = REGISTRY.register("aerglow_grass_path", () -> new DirtPathBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(0.65F).sound(SoundType.GRAVEL)));

	// WOOD
	public static final RegistryObject<Block> ROSE_WOOD = REGISTRY.register("rose_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> ROSE_LOG = REGISTRY.register("rose_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> ROSE_WALL = REGISTRY.register("rose_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_ROSE_WOOD = REGISTRY.register("stripped_rose_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_ROSE_LOG = REGISTRY.register("stripped_rose_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_ROSE_WALL = REGISTRY.register("stripped_rose_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> ROSE_PLANKS = REGISTRY.register("rose_planks", () -> new Block (BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> ROSE_STAIRS = REGISTRY.register("rose_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.ROSE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> ROSE_SLAB = REGISTRY.register("rose_slab", () -> new DeepAetherSlabBlock());
	public static final RegistryObject<Block> ROSE_FENCE = REGISTRY.register("rose_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> ROSE_FENCE_GATE = REGISTRY.register("rose_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> ROSE_PRESSURE_PLATE = REGISTRY.register("rose_pressure_plate", () -> new DeepAetherPressurePlateBlock());
	public static final RegistryObject<Block> ROSE_BUTTON = REGISTRY.register("rose_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_BUTTON)));
	public static final RegistryObject<Block> ROSE_DOOR = REGISTRY.register("rose_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
	public static final RegistryObject<Block> ROSE_TRAPDOOR = REGISTRY.register("rose_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR)));
	public static final RegistryObject<Block> ROSE_LEAVES = REGISTRY.register("rose_leaves", () -> new DeepAetherLeavesBlock());
	public static final RegistryObject<Block> FLOWERING_ROSE_LEAVES = REGISTRY.register("flowering_rose_leaves", () -> new FloweringRoseLeavesBlock());
	public static final RegistryObject<Block> ROSEWOOD_SAPLING = REGISTRY.register("rosewood_sapling", () -> new SaplingBlock( new RosewoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Block> ROSE_WALL_SIGN = REGISTRY.register("rose_wall_sign", () -> new DeepAetherModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DeepAetherModWoodTypes.ROSE));
	public static final RegistryObject<Block> ROSE_SIGN = REGISTRY.register("rose_sign", () -> new DeepAetherModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DeepAetherModWoodTypes.ROSE));

	public static final RegistryObject<Block> YAGROOT_WOOD = REGISTRY.register("yagroot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> YAGROOT_LOG = REGISTRY.register("yagroot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> YAGROOT_WALL = REGISTRY.register("yagroot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_WOOD = REGISTRY.register("stripped_yagroot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_LOG = REGISTRY.register("stripped_yagroot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_WALL = REGISTRY.register("stripped_yagroot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> YAGROOT_PLANKS = REGISTRY.register("yagroot_planks", () -> new Block (BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> YAGROOT_STAIRS = REGISTRY.register("yagroot_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.ROSE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> YAGROOT_SLAB = REGISTRY.register("yagroot_slab", () -> new DeepAetherSlabBlock());
	public static final RegistryObject<Block> YAGROOT_FENCE = REGISTRY.register("yagroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> YAGROOT_FENCE_GATE = REGISTRY.register("yagroot_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> YAGROOT_PRESSURE_PLATE = REGISTRY.register("yagroot_pressure_plate", () -> new DeepAetherPressurePlateBlock());
	public static final RegistryObject<Block> YAGROOT_BUTTON = REGISTRY.register("yagroot_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_BUTTON)));
	public static final RegistryObject<Block> YAGROOT_DOOR = REGISTRY.register("yagroot_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
	public static final RegistryObject<Block> YAGROOT_TRAPDOOR = REGISTRY.register("yagroot_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR)));
	public static final RegistryObject<Block> YAGROOT_LEAVES = REGISTRY.register("yagroot_leaves", () -> new DeepAetherModEffectBlock());
	public static final RegistryObject<Block> YAGROOT_SAPLING = REGISTRY.register("yagroot_sapling", () -> new SaplingBlock( new YagrootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Block> YAGROOT_ROOTS = REGISTRY.register("yagroot_roots", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MANGROVE_ROOTS)));
	public static final RegistryObject<Block> MUDDY_YAGROOT_ROOTS = REGISTRY.register("muddy_yagroot_roots", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUDDY_MANGROVE_ROOTS)));

	public static final RegistryObject<Block> YAGROOT_WALL_SIGN = REGISTRY.register("yagroot_wall_sign", () -> new DeepAetherModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DeepAetherModWoodTypes.YAGROOT));
	public static final RegistryObject<Block> YAGROOT_SIGN = REGISTRY.register("yagroot_sign", () -> new DeepAetherModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DeepAetherModWoodTypes.YAGROOT));
	public static final RegistryObject<Block> CRUDEROOT_WOOD = REGISTRY.register("cruderoot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> CRUDEROOT_LOG = REGISTRY.register("cruderoot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> CRUDEROOT_WALL = REGISTRY.register("cruderoot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_WOOD = REGISTRY.register("stripped_cruderoot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_LOG = REGISTRY.register("stripped_cruderoot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_WALL = REGISTRY.register("stripped_cruderoot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> CRUDEROOT_PLANKS = REGISTRY.register("cruderoot_planks", () -> new Block (BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> CRUDEROOT_STAIRS = REGISTRY.register("cruderoot_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.CRUDEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> CRUDEROOT_SLAB = REGISTRY.register("cruderoot_slab", () -> new DeepAetherSlabBlock());
	public static final RegistryObject<Block> CRUDEROOT_FENCE = REGISTRY.register("cruderoot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> CRUDEROOT_FENCE_GATE = REGISTRY.register("cruderoot_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> CRUDEROOT_PRESSURE_PLATE = REGISTRY.register("cruderoot_pressure_plate", () -> new DeepAetherPressurePlateBlock());
	public static final RegistryObject<Block> CRUDEROOT_BUTTON = REGISTRY.register("cruderoot_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_BUTTON)));
	public static final RegistryObject<Block> CRUDEROOT_DOOR = REGISTRY.register("cruderoot_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
	public static final RegistryObject<Block> CRUDEROOT_TRAPDOOR = REGISTRY.register("cruderoot_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR)));
	public static final RegistryObject<Block> CRUDEROOT_LEAVES = REGISTRY.register("cruderoot_leaves", () -> new DeepAetherLeavesBlock());
	public static final RegistryObject<Block> CRUDEROOT_SAPLING = REGISTRY.register("cruderoot_sapling", () -> new SaplingBlock( new CruderootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Block> CRUDEROOT_WALL_SIGN = REGISTRY.register("cruderoot_wall_sign", () -> new DeepAetherModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DeepAetherModWoodTypes.CRUDEROOT));
	public static final RegistryObject<Block> CRUDEROOT_SIGN = REGISTRY.register("cruderoot_sign", () -> new DeepAetherModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DeepAetherModWoodTypes.CRUDEROOT));
	public static final RegistryObject<Block> YAGROOT_VINE = REGISTRY.register("yagroot_vine", () -> new VineBlock(BlockBehaviour.Properties.copy(Blocks.VINE)));
	// ORES
	public static final RegistryObject<Block> SKYJADE_ORE = REGISTRY.register("skyjade_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> SKYJADE_BLOCK = REGISTRY.register("skyjade_block", () -> new Block (BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	public static final RegistryObject<Block> AGATE_ORE = REGISTRY.register("agate_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> HIGHSTONE_AGATE_ORE = REGISTRY.register("highstone_agate_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> AGATE_BLOCK = REGISTRY.register("agate_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	public static final RegistryObject<Block> ADIBIUM_ORE = REGISTRY.register("adibium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> HIGHSTONE_ADIBIUM_ORE = REGISTRY.register("highstone_adibium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> ADIBIUM_BLOCK = REGISTRY.register("adibium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	public static final RegistryObject<Block> CLOUDIUM_DEBRIS = REGISTRY.register("cloudium_debris", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(10f, 10f)));
	public static final RegistryObject<Block> CLOUDIUM_BLOCK = REGISTRY.register("cloudium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(10f, 10f)));

	public static final RegistryObject<Block> ORATIE_ORE = REGISTRY.register("oratie_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> HIGHSTONE_ORATIE_ORE = REGISTRY.register("highstone_oratie_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> RAW_ORATIE_BLOCK= REGISTRY.register("raw_oratie_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));
	public static final RegistryObject<Block> ORATIE_BLOCK = REGISTRY.register("oratie_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	public static final RegistryObject<Block> ASETERITE = REGISTRY.register("aseterite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE = REGISTRY.register("polished_aseterite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS = REGISTRY.register("polished_aseterite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_STAIRS = REGISTRY.register("polished_aseterite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
			.sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ASETERITE_STAIRS = REGISTRY.register("aseterite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
			.sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ASETERITE_SLAB = REGISTRY.register("aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_SLAB = REGISTRY.register("polished_aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_STAIRS = REGISTRY.register("polished_aseterite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_ASETERITE.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_SLAB = REGISTRY.register("polished_aseterite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	//public static final RegistryObject<Block> ASETERITE_PILLAR = REGISTRY.register("aseterite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> ASETERITE_WALL = REGISTRY.register("aseterite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE)
			.strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> GREOTITE = REGISTRY.register("greotite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_GREOTITE = REGISTRY.register("polished_greotite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS = REGISTRY.register("polished_greotite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> GREOTITE_SLAB = REGISTRY.register("greotite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_SLAB = REGISTRY.register("polished_greotite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_SLAB = REGISTRY.register("polished_greotite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> GREOTITE_STAIRS = REGISTRY.register("greotite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.GREOTITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
			.sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_STAIRS = REGISTRY.register("polished_greotite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_GREOTITE.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_GREOTITE_STAIRS = REGISTRY.register("polished_greotite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_GREOTITE.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> GREOTITE_WALL = REGISTRY.register("greotite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE)
			.strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> JARINITE = REGISTRY.register("jarinite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_JARINITE = REGISTRY.register("polished_jarinite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS = REGISTRY.register("polished_jarinite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> JARINITE_SLAB = REGISTRY.register("jarinite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_JARINITE_SLAB = REGISTRY.register("polished_jarinite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_SLAB = REGISTRY.register("polished_jarinite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> JARINITE_STAIRS = REGISTRY.register("jarinite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.JARINITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
			.sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_JARINITE_STAIRS = REGISTRY.register("polished_jarinite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_JARINITE.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_STAIRS = REGISTRY.register("polished_jarinite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_JARINITE.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> JARINITE_WALL = REGISTRY.register("jarinite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE)
			.strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> YALLESITE = REGISTRY.register("yallesite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_YALLESITE = REGISTRY.register("polished_yallesite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS = REGISTRY.register("polished_yallesite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> YALLESITE_SLAB = REGISTRY.register("yallesite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_SLAB = REGISTRY.register("polished_yallesite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_SLAB = REGISTRY.register("polished_yallesite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> YALLESITE_STAIRS = REGISTRY.register("yallesite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.YALLESITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_YALLESITE_STAIRS = REGISTRY.register("polished_yallesite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_YALLESITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_STAIRS = REGISTRY.register("polished_yallesite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_YALLESITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> YALLESITE_WALL = REGISTRY.register("yallesite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CLORITE = REGISTRY.register("clorite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE = REGISTRY.register("polished_clorite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS = REGISTRY.register("polished_clorite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> CLORITE_SLAB = REGISTRY.register("clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE_SLAB = REGISTRY.register("polished_clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_SLAB = REGISTRY.register("polished_clorite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> CLORITE_STAIRS = REGISTRY.register("clorite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
			.sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_CLORITE_STAIRS = REGISTRY.register("polished_clorite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_CLORITE.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_STAIRS = REGISTRY.register("polished_clorite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_CLORITE.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CLORITE_WALL = REGISTRY.register("clorite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE)
			.strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> DARKERITE = REGISTRY.register("darkerite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_DARKERITE = REGISTRY.register("polished_darkerite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS = REGISTRY.register("polished_darkerite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> DARKERITE_SLAB = REGISTRY.register("darkerite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_SLAB = REGISTRY.register("polished_darkerite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_SLAB = REGISTRY.register("polished_darkerite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> DARKERITE_STAIRS = REGISTRY.register("darkerite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.DARKERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
			.sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_DARKERITE_STAIRS = REGISTRY.register("polished_darkerite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_DARKERITE.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_STAIRS = REGISTRY.register("polished_darkerite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_DARKERITE.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DARKERITE_WALL = REGISTRY.register("darkerite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE)
			.strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> HOLYSTONE_BRICKS = REGISTRY.register("holystone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> HOLYSTONE_BRICKS_STAIRS = REGISTRY.register("holystone_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.HOLYSTONE_BRICKS.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> HOLYSTONE_BRICKS_SLAB = REGISTRY.register("holystone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)));



	// MISC
	public static final RegistryObject<Block> RADIANT_ORCHID = REGISTRY.register("radiant_orchid", () -> new RadiantOrchidBlock());
	public static final RegistryObject<Block> LAVENDER = REGISTRY.register("lavender", () ->  new FlowerBlock(MobEffects.JUMP, 6, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));

	
	public static final RegistryObject<Block> AERGLOW_PETAL_BLOCK = REGISTRY.register("aerglow_petal_block", () -> new Block (BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.MOSS).strength(1f, 10f).lightLevel(s -> 9)));
	public static final RegistryObject<Block> AETHER_MOSS_CARPET = REGISTRY.register("aether_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET)));
	public static final RegistryObject<Block> AETHER_MOSS_BLOCK = REGISTRY.register("aether_moss_block", () -> new DeepAetherMossBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)));

	public static final RegistryObject<Block> VIRULENT_QUICKSAND = REGISTRY.register("virulent_quicksand", () -> new VirulentQuicksandBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));

	//MUD
	public static final RegistryObject<Block> AETHER_MUD = REGISTRY.register("aether_mud", () -> new MudBlock(BlockBehaviour.Properties.copy(Blocks.MUD)));
	public static final RegistryObject<Block> PACKED_AETHER_MUD = REGISTRY.register("packed_aether_mud", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PACKED_MUD)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS = REGISTRY.register("aether_mud_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_SLAB = REGISTRY.register("aether_mud_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICK_SLAB)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_STAIRS = REGISTRY.register("aether_mud_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.AETHER_MUD_BRICKS.get().defaultBlockState(),
			BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.MUD_BRICKS).strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<LiquidBlock> POISON_BLOCK = DeepAetherModBlocks.REGISTRY.register("poison", () -> new PoisonBlock(DeepAetherModFluids.POISON_FLUID, BlockBehaviour.Properties.of(Material.LAVA)
			.noCollission()
			.strength(100f)
			.noLootTable()));


	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
		}
	}
}
