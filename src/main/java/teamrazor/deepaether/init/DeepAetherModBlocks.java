
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package teamrazor.deepaether.init;


import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.HedgeBlock;
import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
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
import teamrazor.deepaether.world.feature.tree.CruderootTreeGrower;
import teamrazor.deepaether.world.feature.tree.RosewoodTreeGrower;
import teamrazor.deepaether.world.feature.tree.YagrootTreeGrower;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class DeepAetherModBlocks {


	public static final BlockSubRegistryHelper HELPER = DeepAetherMod.REGISTRY_HELPER.getBlockSubHelper();


	// GRASS - randomTicks() is needed for grass growth.
	//public static final RegistryObject<Block> AERGLOW_GRASS_BLOCK = HELPER.createBlock()("aerglow_grass_block", () -> new GrassBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.GRAVEL).strength(1f, 10f).randomTicks()));
	//public static final RegistryObject<Block> AERGLOW_GRASS_PATH = HELPER.createBlock()("aerglow_grass_path", () -> new DirtPathBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(0.65F).sound(SoundType.GRAVEL)));

	// WOOD
	public static final RegistryObject<Block> ROSE_WOOD = HELPER.createBlock("rose_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_LOG = HELPER.createBlock("rose_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_WALL = HELPER.createBlock("rose_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> STRIPPED_ROSE_WOOD = HELPER.createBlock("stripped_rose_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> STRIPPED_ROSE_LOG = HELPER.createBlock("stripped_rose_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> STRIPPED_ROSE_WALL = HELPER.createBlock("stripped_rose_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_PLANKS = HELPER.createBlock("rose_planks", () -> new Block (BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_STAIRS = HELPER.createBlock("rose_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.ROSE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_SLAB = HELPER.createBlock("rose_slab", () -> new DeepAetherSlabBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_FENCE = HELPER.createBlock("rose_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_FENCE_GATE = HELPER.createBlock("rose_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_PRESSURE_PLATE = HELPER.createBlock("rose_pressure_plate", () -> new DeepAetherPressurePlateBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_BUTTON = HELPER.createBlock("rose_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_BUTTON)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_DOOR = HELPER.createBlock("rose_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_TRAPDOOR = HELPER.createBlock("rose_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_LEAVES = HELPER.createBlock("rose_leaves", () -> new DeepAetherLeavesBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> FLOWERING_ROSE_LEAVES = HELPER.createBlock("flowering_rose_leaves", () -> new FloweringRoseLeavesBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSEWOOD_SAPLING = HELPER.createBlock("rosewood_sapling", () -> new SaplingBlock( new RosewoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> ROSE_SIGN = HELPER.createSignBlock("rose", MaterialColor.COLOR_PINK);

	public static final RegistryObject<Block> YAGROOT_WOOD = HELPER.createBlock("yagroot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_LOG = HELPER.createBlock("yagroot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_WALL = HELPER.createBlock("yagroot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> STRIPPED_YAGROOT_WOOD = HELPER.createBlock("stripped_yagroot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> STRIPPED_YAGROOT_LOG = HELPER.createBlock("stripped_yagroot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> STRIPPED_YAGROOT_WALL = HELPER.createBlock("stripped_yagroot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_PLANKS = HELPER.createBlock("yagroot_planks", () -> new Block (BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_STAIRS = HELPER.createBlock("yagroot_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.ROSE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_SLAB = HELPER.createBlock("yagroot_slab", () -> new DeepAetherSlabBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_FENCE = HELPER.createBlock("yagroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_FENCE_GATE = HELPER.createBlock("yagroot_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_PRESSURE_PLATE = HELPER.createBlock("yagroot_pressure_plate", () -> new DeepAetherPressurePlateBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_BUTTON = HELPER.createBlock("yagroot_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_BUTTON)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_DOOR = HELPER.createBlock("yagroot_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_TRAPDOOR = HELPER.createBlock("yagroot_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_LEAVES = HELPER.createBlock("yagroot_leaves", () -> new DeepAetherModEffectBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_SAPLING = HELPER.createBlock("yagroot_sapling", () -> new SaplingBlock( new YagrootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YAGROOT_ROOTS = HELPER.createBlock("yagroot_roots", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MANGROVE_ROOTS)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> MUDDY_YAGROOT_ROOTS = HELPER.createBlock("muddy_yagroot_roots", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUDDY_MANGROVE_ROOTS)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	//public static final RegistryObject<Block> YAGROOT_WALL_SIGN = HELPER.createBlockNoItem("yagroot_wall_sign", () -> new DeepAetherModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DeepAetherModWoodTypes.YAGROOT));
	//public static final RegistryObject<Block> YAGROOT_SIGN = HELPER.createBlockNoItem("yagroot_sign", () -> new DeepAetherModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DeepAetherModWoodTypes.YAGROOT));

	public static final RegistryObject<Block> CRUDEROOT_WOOD = HELPER.createBlock("cruderoot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_LOG = HELPER.createBlock("cruderoot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_WALL = HELPER.createBlock("cruderoot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_WOOD = HELPER.createBlock("stripped_cruderoot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_LOG = HELPER.createBlock("stripped_cruderoot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_WALL = HELPER.createBlock("stripped_cruderoot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_PLANKS = HELPER.createBlock("cruderoot_planks", () -> new Block (BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_STAIRS = HELPER.createBlock("cruderoot_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.CRUDEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_SLAB = HELPER.createBlock("cruderoot_slab", () -> new DeepAetherSlabBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_FENCE = HELPER.createBlock("cruderoot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_FENCE_GATE = HELPER.createBlock("cruderoot_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_PRESSURE_PLATE = HELPER.createBlock("cruderoot_pressure_plate", () -> new DeepAetherPressurePlateBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_BUTTON = HELPER.createBlock("cruderoot_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_BUTTON)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_DOOR = HELPER.createBlock("cruderoot_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_TRAPDOOR = HELPER.createBlock("cruderoot_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_LEAVES = HELPER.createBlock("cruderoot_leaves", () -> new DeepAetherLeavesBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_SAPLING = HELPER.createBlock("cruderoot_sapling", () -> new SaplingBlock( new CruderootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	//public static final RegistryObject<Block> CRUDEROOT_WALL_SIGN = HELPER.createBlockNoItem("cruderoot_wall_sign", () -> new DeepAetherModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DeepAetherModWoodTypes.CRUDEROOT));
	//public static final RegistryObject<Block> CRUDEROOT_SIGN = HELPER.createBlockNoItem("cruderoot_sign", () -> new DeepAetherModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DeepAetherModWoodTypes.CRUDEROOT));
	public static final RegistryObject<Block> YAGROOT_VINE = HELPER.createBlock("yagroot_vine", () -> new VineBlock(BlockBehaviour.Properties.copy(Blocks.VINE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	// ORES
	public static final RegistryObject<Block> SKYJADE_ORE = HELPER.createBlock("skyjade_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> SKYJADE_BLOCK = HELPER.createBlock("skyjade_block", () -> new Block (BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> AGATE_ORE = HELPER.createBlock("agate_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> HIGHSTONE_AGATE_ORE = HELPER.createBlock("highstone_agate_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> AGATE_BLOCK = HELPER.createBlock("agate_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> ADIBIUM_ORE = HELPER.createBlock("adibium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> HIGHSTONE_ADIBIUM_ORE = HELPER.createBlock("highstone_adibium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ADIBIUM_BLOCK = HELPER.createBlock("adibium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> CLOUDIUM_DEBRIS = HELPER.createBlock("cloudium_debris", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(10f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CLOUDIUM_BLOCK = HELPER.createBlock("cloudium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(10f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> ORATIE_ORE = HELPER.createBlock("oratie_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> HIGHSTONE_ORATIE_ORE = HELPER.createBlock("highstone_oratie_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> RAW_ORATIE_BLOCK= HELPER.createBlock("raw_oratie_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ORATIE_BLOCK = HELPER.createBlock("oratie_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> ASETERITE = HELPER.createBlock("aseterite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_ASETERITE = HELPER.createBlock("polished_aseterite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS = HELPER.createBlock("polished_aseterite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_ASETERITE_STAIRS = HELPER.createBlock("polished_aseterite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ASETERITE_STAIRS = HELPER.createBlock("aseterite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ASETERITE_SLAB = HELPER.createBlock("aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_ASETERITE_SLAB = HELPER.createBlock("polished_aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_STAIRS = HELPER.createBlock("polished_aseterite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_SLAB = HELPER.createBlock("polished_aseterite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	//public static final RegistryObject<Block> ASETERITE_PILLAR = HELPER.createBlock()("aseterite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> ASETERITE_WALL = HELPER.createBlock("aseterite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> GREOTITE = HELPER.createBlock("greotite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_GREOTITE = HELPER.createBlock("polished_greotite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS = HELPER.createBlock("polished_greotite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> GREOTITE_SLAB = HELPER.createBlock("greotite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_GREOTITE_SLAB = HELPER.createBlock("polished_greotite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_SLAB = HELPER.createBlock("polished_greotite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> GREOTITE_STAIRS = HELPER.createBlock("greotite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.GREOTITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_STAIRS = HELPER.createBlock("polished_greotite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_GREOTITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_GREOTITE_STAIRS = HELPER.createBlock("polished_greotite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_GREOTITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> GREOTITE_WALL = HELPER.createBlock("greotite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> JARINITE = HELPER.createBlock("jarinite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_JARINITE = HELPER.createBlock("polished_jarinite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS = HELPER.createBlock("polished_jarinite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> JARINITE_SLAB = HELPER.createBlock("jarinite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_JARINITE_SLAB = HELPER.createBlock("polished_jarinite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_SLAB = HELPER.createBlock("polished_jarinite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> JARINITE_STAIRS = HELPER.createBlock("jarinite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.JARINITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_JARINITE_STAIRS = HELPER.createBlock("polished_jarinite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_JARINITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_STAIRS = HELPER.createBlock("polished_jarinite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_JARINITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> JARINITE_WALL = HELPER.createBlock("jarinite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> YALLESITE = HELPER.createBlock("yallesite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_YALLESITE = HELPER.createBlock("polished_yallesite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS = HELPER.createBlock("polished_yallesite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YALLESITE_SLAB = HELPER.createBlock("yallesite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_YALLESITE_SLAB = HELPER.createBlock("polished_yallesite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_SLAB = HELPER.createBlock("polished_yallesite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YALLESITE_STAIRS = HELPER.createBlock("yallesite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.YALLESITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_YALLESITE_STAIRS = HELPER.createBlock("polished_yallesite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_YALLESITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_STAIRS = HELPER.createBlock("polished_yallesite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_YALLESITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> YALLESITE_WALL = HELPER.createBlock("yallesite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> CLORITE = HELPER.createBlock("clorite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_CLORITE = HELPER.createBlock("polished_clorite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS = HELPER.createBlock("polished_clorite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CLORITE_SLAB = HELPER.createBlock("clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_CLORITE_SLAB = HELPER.createBlock("polished_clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_SLAB = HELPER.createBlock("polished_clorite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CLORITE_STAIRS = HELPER.createBlock("clorite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_CLORITE_STAIRS = HELPER.createBlock("polished_clorite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_STAIRS = HELPER.createBlock("polished_clorite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CLORITE_WALL = HELPER.createBlock("clorite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> DARKERITE = HELPER.createBlock("darkerite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_DARKERITE = HELPER.createBlock("polished_darkerite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS = HELPER.createBlock("polished_darkerite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> DARKERITE_SLAB = HELPER.createBlock("darkerite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_DARKERITE_SLAB = HELPER.createBlock("polished_darkerite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_SLAB = HELPER.createBlock("polished_darkerite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> DARKERITE_STAIRS = HELPER.createBlock("darkerite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.DARKERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_DARKERITE_STAIRS = HELPER.createBlock("polished_darkerite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_DARKERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_STAIRS = HELPER.createBlock("polished_darkerite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_DARKERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> DARKERITE_WALL = HELPER.createBlock("darkerite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> HOLYSTONE_BRICKS = HELPER.createBlock("holystone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> HOLYSTONE_BRICKS_STAIRS = HELPER.createBlock("holystone_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.HOLYSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> HOLYSTONE_BRICKS_SLAB = HELPER.createBlock("holystone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);



	// MISC
	public static final RegistryObject<Block> RADIANT_ORCHID = HELPER.createBlock("radiant_orchid", () -> new RadiantOrchidBlock(), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> LAVENDER = HELPER.createBlock("lavender", () ->  new FlowerBlock(MobEffects.JUMP, 6, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	
	public static final RegistryObject<Block> AERGLOW_PETAL_BLOCK = HELPER.createBlock("aerglow_petal_block", () -> new Block (BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.MOSS).strength(1f, 10f).lightLevel(s -> 9)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> AETHER_MOSS_CARPET = HELPER.createBlock("aether_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> AETHER_MOSS_BLOCK = HELPER.createBlock("aether_moss_block", () -> new DeepAetherMossBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> VIRULENT_QUICKSAND = HELPER.createBlock("virulent_quicksand", () -> new VirulentQuicksandBlock(BlockBehaviour.Properties.copy(Blocks.SAND)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	//MUD
	public static final RegistryObject<Block> AETHER_MUD = HELPER.createBlock("aether_mud", () -> new MudBlock(BlockBehaviour.Properties.copy(Blocks.MUD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> PACKED_AETHER_MUD = HELPER.createBlock("packed_aether_mud", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PACKED_MUD)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> AETHER_MUD_BRICKS = HELPER.createBlock("aether_mud_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_SLAB = HELPER.createBlock("aether_mud_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICK_SLAB)), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_STAIRS = HELPER.createBlock("aether_mud_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.AETHER_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.MUD_BRICKS).strength(5f).requiresCorrectToolForDrops()), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<LiquidBlock> POISON_BLOCK = DeepAetherModBlocks.HELPER.createBlockNoItem("poison", () -> new PoisonBlock(DeepAetherModFluids.POISON_FLUID, BlockBehaviour.Properties.of(Material.LAVA)
			.noCollission()
			.strength(100f)
			.noLootTable()));
	//QUARK SUPPORT

	//HEDGES
	public static final RegistryObject<Block> YAGROOT_HEDGE = HELPER.createCompatFuelBlock("quark", "yagroot_hedge",() -> new HedgeBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.YAGROOT_PLANKS.get())), 300, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_HEDGE = HELPER.createCompatFuelBlock("quark", "cruderoot_hedge",() -> new HedgeBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())), 300, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_HEDGE = HELPER.createCompatFuelBlock("quark", "rose_hedge",() -> new HedgeBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_PLANKS.get())), 300, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	//VERTICAL PLANKS
	public static final RegistryObject<Block> VERTICAL_YAGROOT_PLANKS = HELPER.createCompatFuelBlock("quark", "vertical_yagroot_planks",() -> new Block(BlockBehaviour.Properties.copy(DeepAetherModBlocks.YAGROOT_PLANKS.get())), 300, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> VERTICAL_CRUDEROOT_PLANKS = HELPER.createCompatFuelBlock("quark", "vertical_cruderoot_planks",() -> new Block(BlockBehaviour.Properties.copy(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())), 300, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> VERTICAL_PLANKS_PLANKS = HELPER.createCompatFuelBlock("quark", "vertical_rose_planks",() -> new Block(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_PLANKS.get())), 300, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	//VERTICAL SLABS
	public static final RegistryObject<Block> YAGROOT_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "yagroot_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.YAGROOT_PLANKS.get())), 150, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> CRUDEROOT_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "cruderoot_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())), 150, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> ROSE_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "rose_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_PLANKS.get())), 150, DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> ASETERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "aseterite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ASETERITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_ASETERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_aseterite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_ASETERITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_aseterite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_ASETERITE_BRICKS.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> JARINITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "jarinite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.JARINITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_JARINITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_jarinite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_JARINITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_jarinite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_JARINITE_BRICKS.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> DARKERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "darkerite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.DARKERITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_DARKERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_darkerite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_DARKERITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_darkerite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_DARKERITE_BRICKS.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> CLORITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "clorite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.CLORITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_CLORITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_clorite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_CLORITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_clorite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_CLORITE_BRICKS.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> YALLESITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "yallesite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.YALLESITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_YALLESITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_yallesite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_YALLESITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_yallesite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_YALLESITE_BRICKS.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> GREOTITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "greotite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.GREOTITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_GREOTITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_greotite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_GREOTITE.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_greotite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_GREOTITE_BRICKS.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);

	public static final RegistryObject<Block> HOLYSTONE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "holystone_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.HOLYSTONE_BRICKS.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "aether_mud_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.AETHER_MUD_BRICKS.get())), DeepAetherModTabs.TAB_DEEP_AETHER_BLOCKS_TAB);


/*	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
		}
	}*/
}
