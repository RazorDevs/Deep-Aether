
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package teamrazor.deepaether.init;
import com.gildedgames.aether.block.natural.AetherDoubleDropBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamrazor.deepaether.block.*;
import teamrazor.deepaether.DeepAetherMod;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.block.DeepAetherModLogBlock;
import teamrazor.deepaether.world.feature.tree.CruderootTreeGrower;
import teamrazor.deepaether.world.feature.tree.RosewoodTreeGrower;
import teamrazor.deepaether.world.feature.tree.YagrootTreeGrower;

import java.util.function.Supplier;

public class DeepAetherModBlocks {
	public static final DeferredRegister<Block> BLOCKS =
			DeferredRegister.create(ForgeRegistries.BLOCKS, DeepAetherMod.MODID);
	// GRASS - randomTicks() is needed for grass growth.
	//public static final RegistryObject<Block> AERGLOW_GRASS_BLOCK = registerBlock()("aerglow_grass_block", () -> new GrassBlock(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.GRAVEL).strength(1f, 10f).randomTicks()));
	//public static final RegistryObject<Block> AERGLOW_GRASS_PATH = registerBlock()("aerglow_grass_path", () -> new DirtPathBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(0.65F).sound(SoundType.GRAVEL)));

	// WOOD
	public static final RegistryObject<Block> ROSE_WOOD = registerBlock("rose_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> ROSE_LOG = registerBlock("rose_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> ROSE_WALL = registerBlock("rose_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_ROSE_WOOD = registerBlock("stripped_rose_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_ROSE_LOG = registerBlock("stripped_rose_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_ROSE_WALL = registerBlock("stripped_rose_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> ROSE_PLANKS = registerBlock("rose_planks", () -> new Block (BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> ROSE_STAIRS = registerBlock("rose_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.ROSE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> ROSE_SLAB = registerBlock("rose_slab", () -> new DeepAetherSlabBlock());
	public static final RegistryObject<Block> ROSE_FENCE = registerBlock("rose_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> ROSE_FENCE_GATE = registerBlock("rose_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN));
	public static final RegistryObject<Block> ROSE_PRESSURE_PLATE = registerBlock("rose_pressure_plate", () -> new DeepAetherPressurePlateBlock());
	public static final RegistryObject<Block> ROSE_BUTTON = registerBlock("rose_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON));
	public static final RegistryObject<Block> ROSE_DOOR = registerBlock("rose_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN));
	public static final RegistryObject<Block> ROSE_TRAPDOOR = registerBlock("rose_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN));
	public static final RegistryObject<Block> ROSE_LEAVES = registerBlock("rose_leaves", () -> new DeepAetherLeavesBlock());
	public static final RegistryObject<Block> FLOWERING_ROSE_LEAVES = registerBlock("flowering_rose_leaves", () -> new FloweringRoseLeavesBlock());
	public static final RegistryObject<Block> ROSEWOOD_SAPLING = registerBlock("rosewood_sapling", () -> new SaplingBlock( new RosewoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	//public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> ROSE_SIGN = HELPER.createSignBlock("rose", MaterialColor.COLOR_PINK);

	public static final RegistryObject<Block> YAGROOT_WOOD = registerBlock("yagroot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> YAGROOT_LOG = registerBlock("yagroot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> YAGROOT_WALL = registerBlock("yagroot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_WOOD = registerBlock("stripped_yagroot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_LOG = registerBlock("stripped_yagroot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_WALL = registerBlock("stripped_yagroot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> YAGROOT_PLANKS = registerBlock("yagroot_planks", () -> new Block (BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> YAGROOT_STAIRS = registerBlock("yagroot_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.ROSE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> YAGROOT_SLAB = registerBlock("yagroot_slab", () -> new DeepAetherSlabBlock());
	public static final RegistryObject<Block> YAGROOT_FENCE = registerBlock("yagroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> YAGROOT_FENCE_GATE = registerBlock("yagroot_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN));
	public static final RegistryObject<Block> YAGROOT_PRESSURE_PLATE = registerBlock("yagroot_pressure_plate", () -> new DeepAetherPressurePlateBlock());
	public static final RegistryObject<Block> YAGROOT_BUTTON = registerBlock("yagroot_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON));
	public static final RegistryObject<Block> YAGROOT_DOOR = registerBlock("yagroot_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN));
	public static final RegistryObject<Block> YAGROOT_TRAPDOOR = registerBlock("yagroot_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN));
	public static final RegistryObject<Block> YAGROOT_LEAVES = registerBlock("yagroot_leaves", () -> new DeepAetherModEffectBlock());
	public static final RegistryObject<Block> YAGROOT_SAPLING = registerBlock("yagroot_sapling", () -> new SaplingBlock( new YagrootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Block> YAGROOT_ROOTS = registerBlock("yagroot_roots", () -> new AetherDoubleDropBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_ROOTS)));
	public static final RegistryObject<Block> MUDDY_YAGROOT_ROOTS = registerBlock("muddy_yagroot_roots", () -> new AetherDoubleDropBlock(BlockBehaviour.Properties.copy(Blocks.MUDDY_MANGROVE_ROOTS)));

	//public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> YAGROOT_SIGN = HELPER.createSignBlock("yagroot", MaterialColor.TERRACOTTA_PINK);

	public static final RegistryObject<Block> CRUDEROOT_WOOD = registerBlock("cruderoot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> CRUDEROOT_LOG = registerBlock("cruderoot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> CRUDEROOT_WALL = registerBlock("cruderoot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_WOOD = registerBlock("stripped_cruderoot_wood", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_LOG = registerBlock("stripped_cruderoot_log", () -> new DeepAetherModLogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_WALL = registerBlock("stripped_cruderoot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> CRUDEROOT_PLANKS = registerBlock("cruderoot_planks", () -> new Block (BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> CRUDEROOT_STAIRS = registerBlock("cruderoot_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.CRUDEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> CRUDEROOT_SLAB = registerBlock("cruderoot_slab", () -> new DeepAetherSlabBlock());
	public static final RegistryObject<Block> CRUDEROOT_FENCE = registerBlock("cruderoot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> CRUDEROOT_FENCE_GATE = registerBlock("cruderoot_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN));
	public static final RegistryObject<Block> CRUDEROOT_PRESSURE_PLATE = registerBlock("cruderoot_pressure_plate", () -> new DeepAetherPressurePlateBlock());
	public static final RegistryObject<Block> CRUDEROOT_BUTTON = registerBlock("cruderoot_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON));
	public static final RegistryObject<Block> CRUDEROOT_DOOR = registerBlock("cruderoot_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN));
	public static final RegistryObject<Block> CRUDEROOT_TRAPDOOR = registerBlock("cruderoot_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN));
	public static final RegistryObject<Block> CRUDEROOT_LEAVES = registerBlock("cruderoot_leaves", () -> new DeepAetherLeavesBlock());
	public static final RegistryObject<Block> CRUDEROOT_SAPLING = registerBlock("cruderoot_sapling", () -> new SaplingBlock( new CruderootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	//public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> CRUDEROOT_SIGN = HELPER.createSignBlock("cruderoot", MaterialColor.COLOR_PINK);

	public static final RegistryObject<Block> YAGROOT_VINE = registerBlock("yagroot_vine", () -> new VineBlock(BlockBehaviour.Properties.copy(Blocks.VINE)));
	// ORES
	public static final RegistryObject<Block> SKYJADE_ORE = registerBlock("skyjade_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> SKYJADE_BLOCK = registerBlock("skyjade_block", () -> new Block (BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	public static final RegistryObject<Block> AGATE_ORE = registerBlock("agate_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> HIGHSTONE_AGATE_ORE = registerBlock("highstone_agate_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> AGATE_BLOCK = registerBlock("agate_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	public static final RegistryObject<Block> ADIBIUM_ORE = registerBlock("adibium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> HIGHSTONE_ADIBIUM_ORE = registerBlock("highstone_adibium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> ADIBIUM_BLOCK = registerBlock("adibium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	public static final RegistryObject<Block> CLOUDIUM_DEBRIS = registerBlock("cloudium_debris", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(10f, 10f)));
	public static final RegistryObject<Block> CLOUDIUM_BLOCK = registerBlock("cloudium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(10f, 10f)));

	public static final RegistryObject<Block> ORATIE_ORE = registerBlock("oratie_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> HIGHSTONE_ORATIE_ORE = registerBlock("highstone_oratie_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> RAW_ORATIE_BLOCK= registerBlock("raw_oratie_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));
	public static final RegistryObject<Block> ORATIE_BLOCK = registerBlock("oratie_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	public static final RegistryObject<Block> ASETERITE = registerBlock("aseterite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE = registerBlock("polished_aseterite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_STAIRS = registerBlock("polished_aseterite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ASETERITE_STAIRS = registerBlock("aseterite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ASETERITE_SLAB = registerBlock("aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_SLAB = registerBlock("polished_aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));

	public static final RegistryObject<Block> ASETERITE_WALL = registerBlock("aseterite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> GREOTITE = registerBlock("greotite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_GREOTITE = registerBlock("polished_greotite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> GREOTITE_SLAB = registerBlock("greotite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_SLAB = registerBlock("polished_greotite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> GREOTITE_STAIRS = registerBlock("greotite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.GREOTITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_GREOTITE_STAIRS = registerBlock("polished_greotite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_GREOTITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> GREOTITE_WALL = registerBlock("greotite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> JARINITE = registerBlock("jarinite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_JARINITE = registerBlock("polished_jarinite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> JARINITE_SLAB = registerBlock("jarinite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_JARINITE_SLAB = registerBlock("polished_jarinite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> JARINITE_STAIRS = registerBlock("jarinite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.JARINITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_JARINITE_STAIRS = registerBlock("polished_jarinite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_JARINITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> JARINITE_WALL = registerBlock("jarinite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> YALLESITE = registerBlock("yallesite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_YALLESITE = registerBlock("polished_yallesite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> YALLESITE_SLAB = registerBlock("yallesite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_SLAB = registerBlock("polished_yallesite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> YALLESITE_STAIRS = registerBlock("yallesite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.YALLESITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_YALLESITE_STAIRS = registerBlock("polished_yallesite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_YALLESITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> YALLESITE_WALL = registerBlock("yallesite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CLORITE = registerBlock("clorite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE = registerBlock("polished_clorite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> CLORITE_SLAB = registerBlock("clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE_SLAB = registerBlock("polished_clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> CLORITE_STAIRS = registerBlock("clorite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_CLORITE_STAIRS = registerBlock("polished_clorite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CLORITE_WALL = registerBlock("clorite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> DARKERITE = registerBlock("darkerite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_DARKERITE = registerBlock("polished_darkerite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> DARKERITE_SLAB = registerBlock("darkerite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_SLAB = registerBlock("polished_darkerite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> DARKERITE_STAIRS = registerBlock("darkerite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.DARKERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_DARKERITE_STAIRS = registerBlock("polished_darkerite_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_DARKERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DARKERITE_WALL = registerBlock("darkerite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> HOLYSTONE_BRICKS = registerBlock("holystone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> HOLYSTONE_BRICKS_STAIRS = registerBlock("holystone_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.HOLYSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> HOLYSTONE_BRICKS_SLAB = registerBlock("holystone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)));



	//MISC
	public static final RegistryObject<Block> RADIANT_ORCHID = registerBlock("radiant_orchid", () -> new RadiantOrchidBlock());
	public static final RegistryObject<Block> AERLAVENDER = registerBlock("aerlavender", () ->  new LavenderBlock(MobEffects.JUMP, 6, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> TALL_AERLAVENDER = registerBlock("tall_aerlavender", () ->  new FlowerBlock(MobEffects.JUMP, 6, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));


	public static final RegistryObject<Block> AERGLOW_PETAL_BLOCK = registerBlock("aerglow_petal_block", () -> new Block (BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.MOSS).strength(1f, 10f).lightLevel(s -> 9)));
	public static final RegistryObject<Block> AETHER_MOSS_CARPET = registerBlock("aether_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET)));
	public static final RegistryObject<Block> AETHER_MOSS_BLOCK = registerBlock("aether_moss_block", () -> new DeepAetherMossBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)));

	public static final RegistryObject<Block> VIRULENT_QUICKSAND = registerBlock("virulent_quicksand", () -> new VirulentQuicksandBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));

	//MUD
	public static final RegistryObject<Block> AETHER_MUD = registerBlock("aether_mud", () -> new MudBlock(BlockBehaviour.Properties.copy(Blocks.MUD)));
	public static final RegistryObject<Block> PACKED_AETHER_MUD = registerBlock("packed_aether_mud", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PACKED_MUD)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS = registerBlock("aether_mud_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_SLAB = registerBlock("aether_mud_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICK_SLAB)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_STAIRS = registerBlock("aether_mud_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.AETHER_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.MUD_BRICKS).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));

	public static final RegistryObject<LiquidBlock> POISON_BLOCK = BLOCKS.register("poison", () -> new PoisonBlock(DeepAetherModFluids.POISON_FLUID, BlockBehaviour.Properties.of(Material.LAVA)
			.noCollission()
			.strength(100f)
			.noLootTable()));
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		return DeepAetherModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}


	//QUARK SUPPORT
/*
	//POSTS
	public static final RegistryObject<Block> ROSE_POST = HELPER.createCompatFuelBlock("quark", "rose_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_LOG.get())), 300);
	public static final RegistryObject<Block> STRIPPED_ROSE_POST = HELPER.createCompatFuelBlock("quark", "stripped_rose_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_LOG.get())), 300);
	public static final RegistryObject<Block> YAGROOT_POST = HELPER.createCompatFuelBlock("quark", "yagroot_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_LOG.get())), 300);
	public static final RegistryObject<Block> STRIPPED_YAGROOT_POST = HELPER.createCompatFuelBlock("quark", "stripped_yagroot_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_LOG.get())), 300);
	public static final RegistryObject<Block> CRUDEROOT_POST = HELPER.createCompatFuelBlock("quark", "cruderoot_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_LOG.get())), 300);
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_POST = HELPER.createCompatFuelBlock("quark", "stripped_cruderoot_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_LOG.get())), 300);

	//SHELVES
	public static final RegistryObject<Block> ROSE_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "rose_bookshelf",() -> new BookshelfBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)), 300);
	public static final RegistryObject<Block> YAGROOT_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "yagroot_bookshelf",() -> new BookshelfBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)), 300);
	public static final RegistryObject<Block> CRUDEROOT_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "cruderoot_bookshelf",() -> new BookshelfBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)), 300);

	//HEDGES
	public static final RegistryObject<Block> ROSE_HEDGE = HELPER.createCompatFuelBlock("quark", "rose_hedge",() -> new HedgeBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_PLANKS.get())), 300);
	public static final RegistryObject<Block> YAGROOT_HEDGE = HELPER.createCompatFuelBlock("quark", "yagroot_hedge",() -> new HedgeBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.YAGROOT_PLANKS.get())), 300);
	public static final RegistryObject<Block> CRUDEROOT_HEDGE = HELPER.createCompatFuelBlock("quark", "cruderoot_hedge",() -> new HedgeBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())), 300);

	//CHESTS
	public static final RegistryObject<Block> ROSE_CHEST = HELPER.createCompatFuelBlock("quark", "rose_chest",() -> new BlueprintChestBlock("rose", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);
	public static final RegistryObject<Block> YAGROOT_CHEST = HELPER.createCompatFuelBlock("quark", "yagroot_chest",() -> new BlueprintChestBlock("yagroot", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);
	public static final RegistryObject<Block> CRUDEROOT_CHEST = HELPER.createCompatFuelBlock("quark", "cruderoot_chest", () -> new BlueprintChestBlock("cruderoot", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);

	//TRAPPED CHESTS
	public static final RegistryObject<Block> ROSE_TRAPPED_CHEST = HELPER.createCompatFuelBlock("quark", "rose_trapped_chest",() -> new BlueprintTrappedChestBlock("rose", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);
	public static final RegistryObject<Block> YAGROOT_TRAPPED_CHEST = HELPER.createCompatFuelBlock("quark", "yagroot_trapped_chest",() -> new BlueprintTrappedChestBlock("yagroot", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);
	public static final RegistryObject<Block> CRUDEROOT_TRAPPED_CHEST = HELPER.createCompatFuelBlock("quark", "cruderoot_trapped_chest", () -> new BlueprintTrappedChestBlock("cruderoot", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);

	//VERTICAL PLANKS
	public static final RegistryObject<Block> VERTICAL_YAGROOT_PLANKS = HELPER.createCompatFuelBlock("quark", "vertical_yagroot_planks",() -> new Block(BlockBehaviour.Properties.copy(DeepAetherModBlocks.YAGROOT_PLANKS.get())), 300);
	public static final RegistryObject<Block> VERTICAL_CRUDEROOT_PLANKS = HELPER.createCompatFuelBlock("quark", "vertical_cruderoot_planks",() -> new Block(BlockBehaviour.Properties.copy(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())), 300);
	public static final RegistryObject<Block> VERTICAL_PLANKS_PLANKS = HELPER.createCompatFuelBlock("quark", "vertical_rose_planks",() -> new Block(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_PLANKS.get())), 300);

	//VERTICAL SLABS
	public static final RegistryObject<Block> YAGROOT_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "yagroot_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.YAGROOT_PLANKS.get())), 150);
	public static final RegistryObject<Block> CRUDEROOT_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "cruderoot_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())), 150);
	public static final RegistryObject<Block> ROSE_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "rose_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ROSE_PLANKS.get())), 150);
	public static final RegistryObject<Block> ASETERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "aseterite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.ASETERITE.get())));
	public static final RegistryObject<Block> POLISHED_ASETERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_aseterite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_ASETERITE.get())));
	public static final RegistryObject<Block> JARINITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "jarinite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.JARINITE.get())));
	public static final RegistryObject<Block> POLISHED_JARINITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_jarinite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_JARINITE.get())));
	public static final RegistryObject<Block> DARKERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "darkerite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.DARKERITE.get())));
	public static final RegistryObject<Block> POLISHED_DARKERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_darkerite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_DARKERITE.get())));
	public static final RegistryObject<Block> CLORITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "clorite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.CLORITE.get())));
	public static final RegistryObject<Block> POLISHED_CLORITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_clorite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_CLORITE.get())));
	public static final RegistryObject<Block> YALLESITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "yallesite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.YALLESITE.get())));
	public static final RegistryObject<Block> POLISHED_YALLESITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_yallesite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_YALLESITE.get())));
	public static final RegistryObject<Block> GREOTITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "greotite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.GREOTITE.get())));
	public static final RegistryObject<Block> POLISHED_GREOTITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_greotite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_GREOTITE.get())));
	public static final RegistryObject<Block> HOLYSTONE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "holystone_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.HOLYSTONE_BRICKS.get())));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "aether_mud_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.AETHER_MUD_BRICKS.get())));

	//LADDERS
	public static final RegistryObject<Block> YAGROOT_LADDER = HELPER.createCompatBlock("quark", "yagroot_ladder",() -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)));
	public static final RegistryObject<Block> ROSE_LADDER = HELPER.createCompatBlock("quark", "rose_ladder",() -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)));
	public static final RegistryObject<Block> CRUDEROOT_LADDER = HELPER.createCompatBlock("quark", "cruderoot_ladder",() -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)));

	//LEAF CARPET
	public static final RegistryObject<Block> YAGROOT_LEAF_CARPET = HELPER.createCompatBlock("quark", "yagroot_leaf_carpet",() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> ROSE_LEAF_CARPET = HELPER.createCompatBlock("quark", "rose_leaf_carpet",() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> CRUDEROOT_LEAF_CARPET = HELPER.createCompatBlock("quark", "cruderoot_leaf_carpet",() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

	//DIRT BRICKS
	public static final RegistryObject<Block> AETHER_DIRT_BRICKS = HELPER.createCompatBlock("quark", "aether_dirt_bricks",() -> new Block(BlockBehaviour.Properties.of(Material.DIRT).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AETHER_DIRT_BRICKS_SLAB = HELPER.createCompatBlock("quark", "aether_dirt_bricks_slab",() -> new SlabBlock(BlockBehaviour.Properties.of(Material.DIRT).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AETHER_DIRT_BRICKS_STAIRS = HELPER.createCompatBlock("quark", "aether_dirt_bricks_stairs",() -> new StairBlock(DeepAetherModBlocks.AETHER_DIRT_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.DIRT).strength(0.5F).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> AETHER_DIRT_BRICKS_WALL = HELPER.createCompatBlock("quark", "aether_dirt_bricks_wall",() -> new WallBlock(BlockBehaviour.Properties.of(Material.DIRT).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AETHER_DIRT_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "aether_dirt_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.of(Material.DIRT).requiresCorrectToolForDrops()));

	//STONE TYPES

	//ASETERITE
	public static final RegistryObject<Block> ASETERITE_BRICKS = HELPER.createCompatBlock("quark","aseterite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> ASETERITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","aseterite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.ASETERITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ASETERITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","aseterite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> ASETERITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "aseterite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> ASETERITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","aseterite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS = HELPER.createCompatBlock("quark","polished_aseterite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_aseterite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_aseterite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_aseterite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_ASETERITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_aseterite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> ASETERITE_PILLAR = HELPER.createCompatBlock("quark","aseterite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_aseterite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_ASETERITE_BRICKS.get())));

	//CLORITE

	public static final RegistryObject<Block> CLORITE_BRICKS = HELPER.createCompatBlock("quark","clorite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> CLORITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","clorite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.CLORITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CLORITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","clorite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> CLORITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "clorite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> CLORITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","clorite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS = HELPER.createCompatBlock("quark","polished_clorite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_clorite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_clorite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_clorite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_CLORITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_clorite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> CLORITE_PILLAR = HELPER.createCompatBlock("quark","clorite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_clorite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_CLORITE_BRICKS.get())));

	//DARKERITE
	public static final RegistryObject<Block> DARKERITE_BRICKS = HELPER.createCompatBlock("quark","darkerite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> DARKERITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","darkerite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.DARKERITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DARKERITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","darkerite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> DARKERITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "darkerite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> DARKERITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","darkerite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS = HELPER.createCompatBlock("quark","polished_darkerite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_darkerite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_DARKERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_darkerite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_darkerite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_DARKERITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_darkerite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> DARKERITE_PILLAR = HELPER.createCompatBlock("quark","darkerite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_darkerite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_DARKERITE_BRICKS.get())));

	//YALLESITE
	public static final RegistryObject<Block> YALLESITE_BRICKS = HELPER.createCompatBlock("quark","yallesite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> YALLESITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","yallesite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.YALLESITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> YALLESITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","yallesite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> YALLESITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "yallesite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> YALLESITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","yallesite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS = HELPER.createCompatBlock("quark","polished_yallesite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_yallesite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_YALLESITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_yallesite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_yallesite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_YALLESITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_yallesite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> YALLESITE_PILLAR = HELPER.createCompatBlock("quark","yallesite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_yallesite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_YALLESITE_BRICKS.get())));

	//JARINITE
	public static final RegistryObject<Block> JARINITE_BRICKS = HELPER.createCompatBlock("quark","jarinite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> JARINITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","jarinite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.JARINITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> JARINITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","jarinite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> JARINITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "jarinite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> JARINITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","jarinite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS = HELPER.createCompatBlock("quark","polished_jarinite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_jarinite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_JARINITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_jarinite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_jarinite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_JARINITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_jarinite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> JARINITE_PILLAR = HELPER.createCompatBlock("quark","jarinite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_jarinite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_JARINITE_BRICKS.get())));

	//GREOTITE
	public static final RegistryObject<Block> GREOTITE_BRICKS = HELPER.createCompatBlock("quark","greotite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> GREOTITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","greotite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.GREOTITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> GREOTITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","greotite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> GREOTITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "greotite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> GREOTITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","greotite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS = HELPER.createCompatBlock("quark","polished_greotite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_greotite_bricks_stairs", () -> new StairBlock(() -> DeepAetherModBlocks.POLISHED_GREOTITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_greotite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_greotite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_GREOTITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_greotite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> GREOTITE_PILLAR = HELPER.createCompatBlock("quark","greotite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_greotite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DeepAetherModBlocks.POLISHED_GREOTITE_BRICKS.get())));
*/
}
