package io.github.razordevs.deep_aether.init;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.construction.AetherDirtPathBlock;
import com.aetherteam.aether.block.dungeon.DoorwayBlock;
import com.aetherteam.aether.block.dungeon.TrappedBlock;
import com.aetherteam.aether.block.dungeon.TreasureDoorwayBlock;
import com.aetherteam.aether.block.natural.AercloudBlock;
import com.aetherteam.aether.block.natural.AetherDoubleDropBlock;
import com.aetherteam.aether.block.natural.AetherDoubleDropsLeaves;
import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.mixin.mixins.common.accessor.FireBlockAccessor;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.block.behavior.GoldenVines;
import io.github.razordevs.deep_aether.block.building.*;
import io.github.razordevs.deep_aether.block.misc.*;
import io.github.razordevs.deep_aether.block.natural.*;
import io.github.razordevs.deep_aether.block.utility.CombinerBlock;
import io.github.razordevs.deep_aether.block.utility.PoisonCauldronBlock;
import io.github.razordevs.deep_aether.datagen.registry.DAConfiguredFeatures;
import io.github.razordevs.deep_aether.world.feature.tree.DATreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.CAULDRON;
import static net.minecraft.world.level.block.Blocks.MOSSY_STONE_BRICKS;

@SuppressWarnings({"unused", "SameParameterValue"})
public class DABlocks {

	public static final DeferredRegister.Blocks BLOCKS =
			DeferredRegister.createBlocks(DeepAether.MODID);

	public static final DeferredBlock<Block> HIGHSTONE = registerBlock("highstone", () -> new AetherDoubleDropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

	//GRASS
	public static final DeferredBlock<Block> GOLDEN_GRASS_BLOCK = registerBlock("golden_heights_grass_block", () -> new GoldenGrassBlock(Block.Properties.of().mapColor(MapColor.GOLD).randomTicks().strength(0.2F).sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> GOLDEN_DIRT_PATH = registerBlock("golden_heights_dirt_path", () -> new AetherDirtPathBlock(Block.Properties.of().mapColor(MapColor.GOLD).strength(0.2F).sound(SoundType.GRASS)));

	public static final DeferredBlock<Block> MINI_GOLDEN_GRASS = registerBlock("mini_golden_grass", ()-> new GoldenGrassPlant(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)));
	public static final DeferredBlock<Block> SHORT_GOLDEN_GRASS = registerBlock("short_golden_grass", ()-> new GoldenGrassPlant(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)));
	public static final DeferredBlock<Block> MEDIUM_GOLDEN_GRASS = registerBlock("medium_golden_grass", ()-> new GoldenGrassPlant(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)));
	public static final DeferredBlock<Block> TALL_GOLDEN_GRASS = registerBlock("tall_golden_grass", ()-> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));

	public static final DeferredBlock<Block> FEATHER_GRASS = registerBlock("feather_grass", ()-> new FeatherGrassPlant(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)));
	public static final DeferredBlock<Block> TALL_FEATHER_GRASS = registerBlock("tall_feather_grass", ()-> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));

	// WOOD
	public static final DeferredBlock<Block> ROSEROOT_WOOD = registerBlock("roseroot_wood", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> ROSEROOT_LOG = registerBlock("roseroot_log", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> ROTTEN_ROSEROOT_LOG = registerBlock("rotten_roseroot_log", () -> new EmptyLog(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> ROSEROOT_WALL = registerBlock(300, "roseroot_wall", () ->  new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final DeferredBlock<Block> STRIPPED_ROSEROOT_WOOD = registerBlock("stripped_roseroot_wood", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> STRIPPED_ROSEROOT_LOG = registerBlock("stripped_roseroot_log", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> STRIPPED_ROSEROOT_WALL = registerBlock(300, "stripped_roseroot_wall", () -> new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final DeferredBlock<Block> ROSEROOT_PLANKS = registerBlock(300, "roseroot_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
	public static final DeferredBlock<Block> ROSEROOT_STAIRS = registerBlock("roseroot_stairs", () -> new StairBlock(DABlocks.ROSEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<SlabBlock> ROSEROOT_SLAB = registerBlock("roseroot_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
	public static final DeferredBlock<Block> ROSEROOT_FENCE = registerBlock("roseroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
	public static final DeferredBlock<Block> ROSEROOT_FENCE_GATE = registerBlock("roseroot_fence_gate", () -> new FenceGateBlock(DAWoodTypes.ROSEROOT, Block.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
	public static final DeferredBlock<Block> ROSEROOT_PRESSURE_PLATE = registerBlock("roseroot_pressure_plate", () -> new PressurePlateBlock(DAWoodTypes.ROSEROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
	public static final DeferredBlock<Block> ROSEROOT_BUTTON = registerBlock("roseroot_button", () -> new ButtonBlock(DAWoodTypes.ROSEROOT_BLOCK_SET, 30, Block.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
	public static final DeferredBlock<Block> ROSEROOT_DOOR = registerBlock("roseroot_door", () ->  new DoorBlock(DAWoodTypes.ROSEROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_DOOR)));
	public static final DeferredBlock<Block> ROSEROOT_TRAPDOOR = registerBlock("roseroot_trapdoor", () -> new TrapDoorBlock(DAWoodTypes.ROSEROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
	public static final DeferredBlock<Block> ROSEROOT_LEAVES = registerBlock("roseroot_leaves", () -> new RoserootLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final DeferredBlock<Block> FLOWERING_ROSEROOT_LEAVES = registerBlock("flowering_roseroot_leaves", () -> new FloweringRoserootLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn(DABlocks::ocelotOrParrot).isSuffocating(DABlocks::never).isViewBlocking(DABlocks::never).lightLevel(s -> 5)));
	public static final DeferredBlock<Block> ROSEROOT_SAPLING = registerBlock("roseroot_sapling", () -> new SaplingBlock(DATreeGrower.ROSEROOT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
	public static final DeferredBlock<Block> BLUE_ROSEROOT_LEAVES = registerBlock("blue_roseroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final DeferredBlock<Block> FLOWERING_BLUE_ROSEROOT_LEAVES = registerBlock("flowering_blue_roseroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.ofFullCopy(DABlocks.FLOWERING_ROSEROOT_LEAVES.get())));
	public static final DeferredBlock<Block> BLUE_ROSEROOT_SAPLING = registerBlock("blue_roseroot_sapling", () -> new SaplingBlock(DATreeGrower.BLUE_ROSEROOT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
	public static final DeferredBlock<Block> ROSEROOT_WALL_SIGN = BLOCKS.register("roseroot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), DAWoodTypes.ROSEROOT));
	public static final DeferredBlock<Block> ROSEROOT_SIGN = BLOCKS.register("roseroot_sign", () -> new DASignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), DAWoodTypes.ROSEROOT));

	public static final DeferredBlock<CeilingHangingSignBlock> ROSEROOT_HANGING_SIGN = BLOCKS.register("roseroot_hanging_sign", () -> new DACeilingHangingSignBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), DAWoodTypes.ROSEROOT));
	public static final DeferredBlock<Block> ROSEROOT_WALL_HANGING_SIGN = BLOCKS.register("roseroot_wall_hanging_sign", () -> new DAWallHangingSignBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), DAWoodTypes.ROSEROOT));
	public static final DeferredBlock<Block> LIGHTCAP_MUSHROOMS = registerBlock("lightcap_mushrooms", () -> new LightCapMushroomBlock(Block.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM), DAConfiguredFeatures.HUGE_LIGHTCAP_MUSHROOM));
	public static final DeferredBlock<Block> LIGHTCAP_MUSHROOM_BLOCK = registerBlock("lightcap_mushroom_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava()));

	//YAGROOT
	public static final DeferredBlock<Block> YAGROOT_WOOD = registerBlock("yagroot_wood", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> YAGROOT_LOG = registerBlock("yagroot_log", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> YAGROOT_WALL = registerBlock(300,"yagroot_wall", () ->  new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final DeferredBlock<Block> STRIPPED_YAGROOT_WOOD = registerBlock("stripped_yagroot_wood", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> STRIPPED_YAGROOT_LOG = registerBlock("stripped_yagroot_log", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> STRIPPED_YAGROOT_WALL = registerBlock(300,"stripped_yagroot_wall", () -> new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final DeferredBlock<Block> YAGROOT_PLANKS = registerBlock(300, "yagroot_planks", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2f, 3f).instrument(NoteBlockInstrument.BASS)));
	public static final DeferredBlock<Block> YAGROOT_STAIRS = registerBlock("yagroot_stairs", () -> new StairBlock(DABlocks.YAGROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> YAGROOT_SLAB = registerBlock("yagroot_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
	public static final DeferredBlock<Block> YAGROOT_FENCE = registerBlock("yagroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2f, 3f)));
	public static final DeferredBlock<Block> YAGROOT_FENCE_GATE = registerBlock("yagroot_fence_gate", () -> new FenceGateBlock(DAWoodTypes.YAGROOT, Block.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
	public static final DeferredBlock<Block> YAGROOT_PRESSURE_PLATE = registerBlock("yagroot_pressure_plate", () -> new PressurePlateBlock(DAWoodTypes.YAGROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
	public static final DeferredBlock<Block> YAGROOT_BUTTON = registerBlock("yagroot_button", () -> new ButtonBlock(DAWoodTypes.YAGROOT_BLOCK_SET, 30, Block.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
	public static final DeferredBlock<Block> YAGROOT_DOOR = registerBlock("yagroot_door", () ->  new DoorBlock(DAWoodTypes.YAGROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_DOOR)));
	public static final DeferredBlock<Block> YAGROOT_TRAPDOOR = registerBlock("yagroot_trapdoor", () -> new TrapDoorBlock(DAWoodTypes.YAGROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
	public static final DeferredBlock<Block> YAGROOT_LEAVES = registerBlock("yagroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final DeferredBlock<Block> YAGROOT_SAPLING = registerBlock("yagroot_sapling", () -> new SaplingBlock(DATreeGrower.YAGROOT, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE)));
	public static final DeferredBlock<Block> YAGROOT_ROOTS = registerBlock(300, "yagroot_roots", () -> new YagrootRootBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(0.7F).sound(SoundType.MANGROVE_ROOTS).noOcclusion()));
	public static final DeferredBlock<Block> MUDDY_YAGROOT_ROOTS = registerBlock("muddy_yagroot_roots", () -> new DADoubleDropRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(0.7F).sound(SoundType.MUDDY_MANGROVE_ROOTS)));
	public static final DeferredBlock<Block> YAGROOT_WALL_SIGN = BLOCKS.register("yagroot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), DAWoodTypes.YAGROOT));
	public static final DeferredBlock<Block> YAGROOT_SIGN = BLOCKS.register("yagroot_sign", () -> new DASignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), DAWoodTypes.YAGROOT));

	public static final DeferredBlock<Block> YAGROOT_VINE = registerBlock("yagroot_vine", () -> new YagrootVineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE)));

	public static final DeferredBlock<CeilingHangingSignBlock> YAGROOT_HANGING_SIGN = BLOCKS.register("yagroot_hanging_sign", () -> new DACeilingHangingSignBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), DAWoodTypes.YAGROOT));
	public static final DeferredBlock<Block> YAGROOT_WALL_HANGING_SIGN = BLOCKS.register("yagroot_wall_hanging_sign", () -> new DAWallHangingSignBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), DAWoodTypes.YAGROOT));

	//CRUDEROOT
	public static final DeferredBlock<Block> CRUDEROOT_WOOD = registerBlock("cruderoot_wood", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> CRUDEROOT_LOG = registerBlock("cruderoot_log", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> CRUDEROOT_WALL = registerBlock(300,"cruderoot_wall", () ->  new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final DeferredBlock<Block> STRIPPED_CRUDEROOT_WOOD = registerBlock("stripped_cruderoot_wood", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> STRIPPED_CRUDEROOT_LOG = registerBlock("stripped_cruderoot_log", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> STRIPPED_CRUDEROOT_WALL = registerBlock(300,"stripped_cruderoot_wall", () -> new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final DeferredBlock<Block> CRUDEROOT_PLANKS = registerBlock(300, "cruderoot_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
	public static final DeferredBlock<Block> CRUDEROOT_STAIRS = registerBlock("cruderoot_stairs", () -> new StairBlock(DABlocks.CRUDEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> CRUDEROOT_SLAB = registerBlock("cruderoot_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
	public static final DeferredBlock<Block> CRUDEROOT_FENCE = registerBlock("cruderoot_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
	public static final DeferredBlock<Block> CRUDEROOT_FENCE_GATE = registerBlock("cruderoot_fence_gate", () -> new FenceGateBlock(DAWoodTypes.CRUDEROOT, Block.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
	public static final DeferredBlock<Block> CRUDEROOT_PRESSURE_PLATE = registerBlock("cruderoot_pressure_plate", () -> new PressurePlateBlock(DAWoodTypes.CRUDEROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
	public static final DeferredBlock<Block> CRUDEROOT_BUTTON = registerBlock("cruderoot_button", () -> new ButtonBlock(DAWoodTypes.CRUDEROOT_BLOCK_SET, 30, Block.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
	public static final DeferredBlock<Block> CRUDEROOT_DOOR = registerBlock("cruderoot_door", () ->  new DoorBlock(DAWoodTypes.CRUDEROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_DOOR)));
	public static final DeferredBlock<Block> CRUDEROOT_TRAPDOOR = registerBlock("cruderoot_trapdoor", () -> new TrapDoorBlock(DAWoodTypes.CRUDEROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
	public static final DeferredBlock<Block> CRUDEROOT_LEAVES = registerBlock("cruderoot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final DeferredBlock<Block> CRUDEROOT_SAPLING = registerBlock("cruderoot_sapling", () -> new SaplingBlock(DATreeGrower.CRUDEROOT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
	public static final DeferredBlock<Block> CRUDEROOT_WALL_SIGN = BLOCKS.register("cruderoot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), DAWoodTypes.CRUDEROOT));
	public static final DeferredBlock<Block> CRUDEROOT_SIGN = BLOCKS.register("cruderoot_sign", () -> new DASignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), DAWoodTypes.CRUDEROOT));
	public static final DeferredBlock<CeilingHangingSignBlock> CRUDEROOT_HANGING_SIGN = BLOCKS.register("cruderoot_hanging_sign", () -> new DACeilingHangingSignBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), DAWoodTypes.CRUDEROOT));
	public static final DeferredBlock<Block> CRUDEROOT_WALL_HANGING_SIGN = BLOCKS.register("cruderoot_wall_hanging_sign", () -> new DAWallHangingSignBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), DAWoodTypes.CRUDEROOT));

	//CONBERRY
	public static final DeferredBlock<Block> CONBERRY_WOOD = registerBlock("conberry_wood", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> CONBERRY_LOG = registerBlock("conberry_log", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> CONBERRY_WALL = registerBlock(300,"conberry_wall", () ->  new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final DeferredBlock<Block> STRIPPED_CONBERRY_WOOD = registerBlock("stripped_conberry_wood", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> STRIPPED_CONBERRY_LOG = registerBlock("stripped_conberry_log", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> STRIPPED_CONBERRY_WALL = registerBlock(300,"stripped_conberry_wall", () -> new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final DeferredBlock<Block> CONBERRY_PLANKS = registerBlock( 300,"conberry_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
	public static final DeferredBlock<Block> CONBERRY_STAIRS = registerBlock("conberry_stairs", () -> new StairBlock(DABlocks.CONBERRY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> CONBERRY_SLAB = registerBlock("conberry_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
	public static final DeferredBlock<Block> CONBERRY_FENCE = registerBlock("conberry_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
	public static final DeferredBlock<Block> CONBERRY_FENCE_GATE = registerBlock("conberry_fence_gate", () -> new FenceGateBlock(DAWoodTypes.CONBERRY, Block.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
	public static final DeferredBlock<Block> CONBERRY_PRESSURE_PLATE = registerBlock("conberry_pressure_plate", () -> new PressurePlateBlock(DAWoodTypes.CONBERRY_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
	public static final DeferredBlock<Block> CONBERRY_BUTTON = registerBlock("conberry_button", () -> new ButtonBlock(DAWoodTypes.CONBERRY_BLOCK_SET, 30, Block.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
	public static final DeferredBlock<Block> CONBERRY_DOOR = registerBlock("conberry_door", () ->  new DoorBlock(DAWoodTypes.CONBERRY_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_DOOR)));
	public static final DeferredBlock<Block> CONBERRY_TRAPDOOR = registerBlock("conberry_trapdoor", () -> new TrapDoorBlock(DAWoodTypes.CONBERRY_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
	public static final DeferredBlock<Block> CONBERRY_LEAVES = registerBlock("conberry_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final DeferredBlock<Block> CONBERRY_SAPLING = registerBlock("conberry_sapling", () -> new SaplingBlock(DATreeGrower.CONBERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE)));
	public static final DeferredBlock<Block> CONBERRY_WALL_SIGN = BLOCKS.register("conberry_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), DAWoodTypes.CONBERRY));
	public static final DeferredBlock<Block> CONBERRY_SIGN = BLOCKS.register("conberry_sign", () -> new DASignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), DAWoodTypes.CONBERRY));

	public static final DeferredBlock<CeilingHangingSignBlock> CONBERRY_HANGING_SIGN = BLOCKS.register("conberry_hanging_sign", () -> new DACeilingHangingSignBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), DAWoodTypes.CONBERRY));
	public static final DeferredBlock<Block> CONBERRY_WALL_HANGING_SIGN = BLOCKS.register("conberry_wall_hanging_sign", () -> new DAWallHangingSignBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), DAWoodTypes.CONBERRY));

	//SUNROOT
	public static final DeferredBlock<Block> SUNROOT_WOOD = registerBlock("sunroot_wood", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> SUNROOT_LOG = registerBlock("sunroot_log", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> SUNROOT_WALL = registerBlock(300,"sunroot_wall", () ->  new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final DeferredBlock<Block> STRIPPED_SUNROOT_WOOD = registerBlock("stripped_sunroot_wood", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> STRIPPED_SUNROOT_LOG = registerBlock("stripped_sunroot_log", () -> new DALogBlock(Block.Properties.ofFullCopy(Blocks.OAK_LOG)));
	public static final DeferredBlock<Block> STRIPPED_SUNROOT_WALL = registerBlock(300,"stripped_sunroot_wall", () -> new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final DeferredBlock<Block> SUNROOT_PLANKS = registerBlock( 300,"sunroot_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
	public static final DeferredBlock<Block> SUNROOT_STAIRS = registerBlock("sunroot_stairs", () -> new StairBlock(DABlocks.SUNROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
	public static final DeferredBlock<Block> SUNROOT_SLAB = registerBlock("sunroot_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
	public static final DeferredBlock<Block> SUNROOT_FENCE = registerBlock("sunroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
	public static final DeferredBlock<Block> SUNROOT_FENCE_GATE = registerBlock("sunroot_fence_gate", () -> new FenceGateBlock(DAWoodTypes.SUNROOT, Block.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
	public static final DeferredBlock<Block> SUNROOT_PRESSURE_PLATE = registerBlock("sunroot_pressure_plate", () -> new PressurePlateBlock(DAWoodTypes.SUNROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
	public static final DeferredBlock<Block> SUNROOT_BUTTON = registerBlock("sunroot_button", () -> new ButtonBlock(DAWoodTypes.SUNROOT_BLOCK_SET, 30, Block.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
	public static final DeferredBlock<Block> SUNROOT_DOOR = registerBlock("sunroot_door", () ->  new DoorBlock(DAWoodTypes.SUNROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_DOOR)));
	public static final DeferredBlock<Block> SUNROOT_TRAPDOOR = registerBlock("sunroot_trapdoor", () -> new TrapDoorBlock(DAWoodTypes.SUNROOT_BLOCK_SET, Block.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
	public static final DeferredBlock<Block> SUNROOT_LEAVES = registerBlock("sunroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final DeferredBlock<Block> SUNROOT_SAPLING = registerBlock("sunroot_sapling", () -> new SaplingBlock(DATreeGrower.SUNROOT, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PROPAGULE)));
	public static final DeferredBlock<Block> SUNROOT_WALL_SIGN = BLOCKS.register("sunroot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), DAWoodTypes.SUNROOT));
	public static final DeferredBlock<Block> SUNROOT_SIGN = BLOCKS.register("sunroot_sign", () -> new DASignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), DAWoodTypes.SUNROOT));

	public static final DeferredBlock<CeilingHangingSignBlock> SUNROOT_HANGING_SIGN = BLOCKS.register("sunroot_hanging_sign", () -> new DACeilingHangingSignBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), DAWoodTypes.SUNROOT));
	public static final DeferredBlock<Block> SUNROOT_WALL_HANGING_SIGN = BLOCKS.register("sunroot_wall_hanging_sign", () -> new DAWallHangingSignBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), DAWoodTypes.SUNROOT));

	// ORES
	public static final DeferredBlock<Block> SKYJADE_ORE = registerBlock("skyjade_ore", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final DeferredBlock<Block> SKYJADE_BLOCK = registerBlock("skyjade_block", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final DeferredBlock<Block> STRATUS_BLOCK = registerBlock("stratus_block", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(10f, 10f)));

	//STONE TYPES
	public static final DeferredBlock<Block> COBBLED_ASETERITE = registerBlock("cobbled_aseterite", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<Block> COBBLED_ASETERITE_STAIRS = registerBlock("cobbled_aseterite_stairs", () -> new StairBlock(DABlocks.COBBLED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE)));
	public static final DeferredBlock<Block> COBBLED_ASETERITE_SLAB = registerBlock("cobbled_aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<Block> COBBLED_ASETERITE_WALL = registerBlock("cobbled_aseterite_wall", () -> new WallBlock(BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> ASETERITE = registerBlock("aseterite", () -> new AetherDoubleDropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<Block> ASETERITE_STAIRS = registerBlock("aseterite_stairs", () -> new StairBlock(DABlocks.ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE)));
	public static final DeferredBlock<Block> ASETERITE_SLAB = registerBlock("aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<Block> ASETERITE_WALL = registerBlock("aseterite_wall", () -> new WallBlock(BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> POLISHED_ASETERITE = registerBlock("polished_aseterite", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE)));
	public static final DeferredBlock<Block> POLISHED_ASETERITE_STAIRS = registerBlock("polished_aseterite_stairs", () -> new StairBlock(DABlocks.POLISHED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE)));

	public static final DeferredBlock<Block> POLISHED_ASETERITE_SLAB = registerBlock("polished_aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE)));

	public static final DeferredBlock<Block> ASETERITE_BRICKS= registerBlock("aseterite_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
	public static final DeferredBlock<Block> ASETERITE_BRICKS_STAIRS = registerBlock("aseterite_bricks_stairs", () -> new StairBlock(DABlocks.ASETERITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
	public static final DeferredBlock<Block> ASETERITE_BRICKS_SLAB = registerBlock("aseterite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
	public static final DeferredBlock<Block> ASETERITE_BRICKS_WALL = registerBlock("aseterite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> RAW_CLORITE = registerBlock("raw_clorite", () -> new AetherDoubleDropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

	public static final DeferredBlock<Block> CLORITE = registerBlock("clorite", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<Block> POLISHED_CLORITE = registerBlock("polished_clorite", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE)));
	public static final DeferredBlock<Block> RAW_CLORITE_SLAB = registerBlock("raw_clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

	public static final DeferredBlock<Block> CLORITE_SLAB = registerBlock("clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<Block> POLISHED_CLORITE_SLAB = registerBlock("polished_clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE)));
	public static final DeferredBlock<Block> RAW_CLORITE_STAIRS = registerBlock("raw_clorite_stairs", () -> new StairBlock(DABlocks.CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> CLORITE_STAIRS = registerBlock("clorite_stairs", () -> new StairBlock(DABlocks.CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> POLISHED_CLORITE_STAIRS = registerBlock("polished_clorite_stairs", () -> new StairBlock(DABlocks.POLISHED_CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CLORITE_WALL = registerBlock("clorite_wall", () -> new WallBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> RAW_CLORITE_WALL = registerBlock("raw_clorite_wall", () -> new WallBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> CLORITE_PILLAR = registerBlock("clorite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> HOLYSTONE_TILES = registerBlock("holystone_tiles", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> MOSSY_HOLYSTONE_BRICKS = registerBlock("mossy_holystone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOSSY_STONE_BRICKS)));
	public static final DeferredBlock<Block> MOSSY_HOLYSTONE_TILES = registerBlock("mossy_holystone_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOSSY_STONE_BRICKS)));

	public static final DeferredBlock<Block> HOLYSTONE_TILE_STAIRS = registerBlock("holystone_tile_stairs", () -> new StairBlock(DABlocks.HOLYSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> MOSSY_HOLYSTONE_BRICK_STAIRS = registerBlock("mossy_holystone_brick_stairs", () -> new StairBlock(DABlocks.MOSSY_HOLYSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STONE_BRICKS)));
	public static final DeferredBlock<Block> MOSSY_HOLYSTONE_TILE_STAIRS = registerBlock("mossy_holystone_tile_stairs", () -> new StairBlock(DABlocks.MOSSY_HOLYSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STONE_BRICKS)));

	public static final DeferredBlock<Block> HOLYSTONE_TILE_SLAB = registerBlock("holystone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> MOSSY_HOLYSTONE_BRICK_SLAB = registerBlock("mossy_holystone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STONE_BRICKS)));
	public static final DeferredBlock<Block> MOSSY_HOLYSTONE_TILE_SLAB = registerBlock("mossy_holystone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STONE_BRICKS)));

	public static final DeferredBlock<Block> HOLYSTONE_TILE_WALL = registerBlock("holystone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> MOSSY_HOLYSTONE_BRICK_WALL = registerBlock("mossy_holystone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STONE_BRICKS)));
	public static final DeferredBlock<Block> MOSSY_HOLYSTONE_TILE_WALL = registerBlock("mossy_holystone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STONE_BRICKS)));

	public static final DeferredBlock<Block> BIG_HOLYSTONE_BRICKS = registerBlock("big_holystone_bricks", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> BIG_HOLYSTONE_BRICKS_SLAB = registerBlock("big_holystone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(DABlocks.BIG_HOLYSTONE_BRICKS.get())));
	public static final DeferredBlock<Block> BIG_HOLYSTONE_BRICKS_STAIRS = registerBlock("big_holystone_bricks_stairs", () -> new StairBlock(BIG_HOLYSTONE_BRICKS.get().defaultBlockState(),  BlockBehaviour.Properties.ofFullCopy(DABlocks.BIG_HOLYSTONE_BRICKS.get())));
 	public static final DeferredBlock<Block> BIG_HOLYSTONE_BRICKS_WALL = registerBlock("big_holystone_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(DABlocks.BIG_HOLYSTONE_BRICKS.get())));


	public static final DeferredBlock<Block> CHISELED_HOLYSTONE = registerBlock("chiseled_holystone", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> HOLYSTONE_PILLAR = registerBlock("holystone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> HOLYSTONE_PILLAR_UP = registerBlock("holystone_pillar_up", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> HOLYSTONE_PILLAR_DOWN = registerBlock("holystone_pillar_down", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(1f, 10f).requiresCorrectToolForDrops()));


	//AERCLOUDS
	public static final DeferredBlock<Block> AERSMOG = registerBlock("aersmog", () -> new AersmogBlock((Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.3F).sound(SoundType.WOOL).noOcclusion().dynamicShape().isRedstoneConductor(DABlocks::never).isSuffocating(DABlocks::never).isViewBlocking(DABlocks::never).forceSolidOn())));
	public static final DeferredBlock<Block> CHROMATIC_AERCLOUD = registerBlock("chromatic_aercloud", () -> new ChromaticAercloudBlock(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.3F).sound(SoundType.WOOL).dynamicShape().isRedstoneConductor(DABlocks::never).isSuffocating(DABlocks::never).isViewBlocking(DABlocks::never)));
	public static final DeferredBlock<Block> STERLING_AERCLOUD = registerBlock("sterling_aercloud", () -> new SterlingAercloudBlock(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.3F).sound(SoundType.WOOL).dynamicShape().isRedstoneConductor(DABlocks::never).isSuffocating(DABlocks::never).isViewBlocking(DABlocks::never)));

	//PLANTS
	public static final DeferredBlock<Block> RADIANT_ORCHID = registerBlock("radiant_orchid", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.of().noCollission().sound(SoundType.GRASS).instabreak().lightLevel(s -> 8)));
	public static final DeferredBlock<Block> AERLAVENDER = registerBlock("aerlavender", () ->  new FlowerBlockLargeHitBox(MobEffects.JUMP, 6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> TALL_AERLAVENDER = registerBlock("tall_aerlavender", () ->  new FlowerBlockLargeHitBox(MobEffects.JUMP, 6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> AETHER_CATTAILS = registerBlock("aether_cattails", () ->  new FlowerBlock(AetherEffects.INEBRIATION, 6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> TALL_AETHER_CATTAILS = registerBlock("tall_aether_cattails", () ->  new TallFlowerBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> GOLDEN_FLOWER = registerBlock("golden_flower", () ->  new TallFlowerBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> ENCHANTED_BLOSSOM = registerBlock("enchanted_blossom", () ->  new FlowerBlock(MobEffects.GLOWING,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> SKY_TULIPS = registerBlock("sky_tulips", () ->  new FlowerBlock(MobEffects.HEALTH_BOOST,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> IASPOVE = registerBlock("iaspove", () ->  new FlowerBlock(MobEffects.MOVEMENT_SPEED,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> GOLDEN_ASPESS = registerBlock("golden_aspess", () ->  new FlowerBlockLargeHitBox(MobEffects.GLOWING,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> ECHAISY = registerBlock("echaisy", () ->  new FlowerBlock(MobEffects.DAMAGE_RESISTANCE,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> GOLDEN_VINES = BLOCKS.register("golden_vines", () -> new GoldenVinesBlock(BlockBehaviour.Properties.of().randomTicks().noCollission().lightLevel(GoldenVines.emission(1)).instabreak().sound(SoundType.CAVE_VINES)));
	public static final DeferredBlock<Block> GOLDEN_VINES_PLANT = BLOCKS.register("golden_vines_plant", () -> new GoldenVinesPlantBlock(BlockBehaviour.Properties.of().noCollission().lightLevel(GoldenVines.emission(1)).instabreak().sound(SoundType.CAVE_VINES)));
	public static final DeferredBlock<Block> SUNROOT_HANGER = registerBlock("sunroot_hanger", () -> new SunrootHangerBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.CAVE_VINES)));
	public static final DeferredBlock<Block> GLOWING_SPORES = registerBlock("glowing_spores", () -> new GlowingSporesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().sound(SoundType.PINK_PETALS).pushReaction(PushReaction.DESTROY).lightLevel(s -> 7)));

	public static final DeferredBlock<Block> BLUE_SQUASH = registerBlock("blue_squash", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> GREEN_SQUASH = registerBlock("green_squash", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> PURPLE_SQUASH = registerBlock("purple_squash", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> GLOWING_VINE = BLOCKS.register("glowing_vine", () -> new VineBlock(BlockBehaviour.Properties.of()
			.mapColor(MapColor.PLANT)
			.replaceable()
			.noCollission()
			.randomTicks()
			.strength(0.2F)
			.sound(SoundType.VINE)
			.ignitedByLava()
			.lightLevel(s -> 7)
			.pushReaction(PushReaction.DESTROY)));

	public static final DeferredBlock<Block> TALL_GLOWING_GRASS = BLOCKS.register("tall_glowing_grass", ()-> new GlowingGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));

	public static final DeferredBlock<Block> SQUASH_STEM = BLOCKS.register("squash_stem", () -> new SquashStemBlock(BlockReferences.BLUE_SQUASH, BlockReferences.ATTACHED_SQUASH_STEM, ItemReferences.SQUASH_SEEDS,
			BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> ATTACHED_SQUASH_STEM = BLOCKS.register("attached_squash_stem", () -> new AttachedStemBlock(BlockReferences.SQUASH_STEM, BlockReferences.BLUE_SQUASH, ItemReferences.SQUASH_SEEDS,
			BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));


	// BRASS DUNGEON

	public static final DeferredBlock<Block> NIMBUS_STONE = registerBlock("nimbus_stone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LIGHT_NIMBUS_STONE = registerBlock("light_nimbus_stone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 6.0F).requiresCorrectToolForDrops().lightLevel((light) -> 11)));
	public static final DeferredBlock<Block> LOCKED_NIMBUS_STONE = registerBlock("locked_nimbus_stone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(-1.0F, 3600000.0F)));
	public static final DeferredBlock<Block> LOCKED_LIGHT_NIMBUS_STONE = registerBlock("locked_light_nimbus_stone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(-1.0F, 3600000.0F).lightLevel((light) -> 11)));
	public static final DeferredBlock<Block> TRAPPED_NIMBUS_STONE = registerTrapped("trapped_nimbus_stone", () -> new TrappedBlock(DAEntities.BABY_ZEPHYR::get, () -> LOCKED_NIMBUS_STONE.get().defaultBlockState(), Block.Properties.ofFullCopy(LOCKED_NIMBUS_STONE.get())));
	public static final DeferredBlock<Block> TRAPPED_LIGHT_NIMBUS_STONE = registerTrapped("trapped_light_nimbus_stone", () -> new TrappedBlock(DAEntities.BABY_ZEPHYR::get, () -> LOCKED_NIMBUS_STONE.get().defaultBlockState(), Block.Properties.ofFullCopy(LOCKED_NIMBUS_STONE.get()).lightLevel((light) -> 11)));
	public static final DeferredBlock<Block> BOSS_DOORWAY_NIMBUS_STONE = registerBlock("boss_doorway_nimbus_stone", () -> new DoorwayBlock(DAEntities.EOTS_CONTROLLER::get, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(-1.0F, 3600000.0F).forceSolidOn()));
	public static final DeferredBlock<Block> BOSS_DOORWAY_LIGHT_NIMBUS_STONE = registerBlock("boss_doorway_light_nimbus_stone", () -> new DoorwayBlock(DAEntities.EOTS_CONTROLLER::get, BlockBehaviour.Properties.ofFullCopy(BOSS_DOORWAY_NIMBUS_STONE.get()).lightLevel((light) -> 11)));
	public static final DeferredBlock<Block> TREASURE_DOORWAY_NIMBUS_STONE = registerBlock("treasure_doorway_nimbus_stone", () -> new TreasureDoorwayBlock(BlockBehaviour.Properties.ofFullCopy(LOCKED_NIMBUS_STONE.get())));
	public static final DeferredBlock<Block> TREASURE_DOORWAY_LIGHT_NIMBUS_STONE = registerBlock("treasure_doorway_light_nimbus_stone", () -> new TreasureDoorwayBlock(BlockBehaviour.Properties.ofFullCopy(LOCKED_LIGHT_NIMBUS_STONE.get()).lightLevel((light) -> 11)));

	public static final DeferredBlock<Block> LOCKED_SKYROOT_PLANKS = registerTrapped("locked_skyroot_planks", () -> new Block(Block.Properties.ofFullCopy(AetherBlocks.SKYROOT_PLANKS.get()).strength(-1.0F, 3600000.0F)));
	public static final DeferredBlock<Block> TRAPPED_SKYROOT_PLANKS = registerTrapped("trapped_skyroot_planks", () -> new TrappedSkyrootBlock(Block.Properties.ofFullCopy(DABlocks.LOCKED_SKYROOT_PLANKS.get())));

	public static final DeferredBlock<StairBlock> NIMBUS_STAIRS = registerBlock("nimbus_stairs",
			() -> new StairBlock(NIMBUS_STONE.get().defaultBlockState(), Block.Properties.ofFullCopy(DABlocks.NIMBUS_STONE.get())));
	public static final DeferredBlock<SlabBlock> NIMBUS_SLAB = registerBlock("nimbus_slab",
			() -> new SlabBlock(Block.Properties.ofFullCopy(DABlocks.NIMBUS_STONE.get()).strength(0.5F, 6.0F)));
	public static final DeferredBlock<WallBlock> NIMBUS_WALL = registerBlock("nimbus_wall",
			() -> new WallBlock(Block.Properties.ofFullCopy(DABlocks.NIMBUS_STONE.get()).forceSolidOn()));

	public static final DeferredBlock<Block> NIMBUS_PILLAR = registerBlock("nimbus_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 6.0F).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LIGHT_NIMBUS_PILLAR = registerBlock("light_nimbus_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 6.0F).requiresCorrectToolForDrops().lightLevel((light) -> 11)));
	public static final DeferredBlock<Block> LOCKED_NIMBUS_PILLAR = registerBlock("locked_nimbus_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(-1.0F, 3600000.0F)));
	public static final DeferredBlock<Block> LOCKED_LIGHT_NIMBUS_PILLAR = registerBlock("locked_light_nimbus_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(-1.0F, 3600000.0F).lightLevel((light) -> 11)));
	public static final DeferredBlock<Block> TRAPPED_NIMBUS_PILLAR = registerTrapped("trapped_nimbus_pillar", () -> new TrappedPillarBlock(AetherEntityTypes.VALKYRIE::get, () -> LOCKED_NIMBUS_PILLAR.get().defaultBlockState(), Block.Properties.ofFullCopy(LOCKED_NIMBUS_PILLAR.get())));
	public static final DeferredBlock<Block> TRAPPED_LIGHT_NIMBUS_PILLAR = registerTrapped("trapped_light_nimbus_pillar", () -> new TrappedPillarBlock(AetherEntityTypes.VALKYRIE::get, () -> LOCKED_NIMBUS_PILLAR.get().defaultBlockState(), Block.Properties.ofFullCopy(LOCKED_NIMBUS_PILLAR.get()).lightLevel((light) -> 11)));
	public static final DeferredBlock<Block> BOSS_DOORWAY_NIMBUS_PILLAR = registerBlock("boss_doorway_nimbus_pillar", () -> new DoorwayPillarBlock(AetherEntityTypes.VALKYRIE_QUEEN::get, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(-1.0F, 3600000.0F).forceSolidOn().lightLevel((light) -> 11)));
	public static final DeferredBlock<Block> BOSS_DOORWAY_LIGHT_NIMBUS_PILLAR = registerBlock("boss_doorway_light_nimbus_pillar", () -> new DoorwayPillarBlock(AetherEntityTypes.VALKYRIE_QUEEN::get, BlockBehaviour.Properties.ofFullCopy(BOSS_DOORWAY_NIMBUS_PILLAR.get())));
	public static final DeferredBlock<Block> TREASURE_DOORWAY_NIMBUS_PILLAR = registerBlock("treasure_doorway_nimbus_pillar", () -> new TreasureDoorwayBlock(BlockBehaviour.Properties.ofFullCopy(LOCKED_NIMBUS_PILLAR.get())));
	public static final DeferredBlock<Block> TREASURE_DOORWAY_LIGHT_NIMBUS_PILLAR = registerBlock("treasure_doorway_light_nimbus_pillar", () -> new TreasureDoorwayBlock(BlockBehaviour.Properties.ofFullCopy(LOCKED_LIGHT_NIMBUS_PILLAR.get()).lightLevel((light) -> 11)));

	//MISC
	public static final DeferredBlock<Block> AERGLOW_BLOSSOM_BLOCK = registerBlock("aerglow_blossom_block", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.MOSS).strength(1f, 10f).lightLevel(s -> 9)));
	public static final DeferredBlock<Block> CLOUDBLOOM_CARPET = registerBlock("cloudbloom_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET)));

	public static final DeferredBlock<Block> VIRULENT_QUICKSAND = BLOCKS.register("virulent_quicksand", () -> new VirulentQuicksandBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)));
	public static final DeferredBlock<Block> SKYJADE_CHAIN = registerBlock("skyjade_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
	public static final DeferredBlock<Block> SKYJADE_LANTERN = registerBlock("skyjade_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));

	// POISON

	public static final DeferredBlock<LiquidBlock> POISON_BLOCK = BLOCKS.register("poison", () -> new PoisonBlock(DAFluids.POISON_FLUID.get(), BlockBehaviour.Properties.of()
			.noCollission()
			.replaceable()
			.strength(100f)
			.noLootTable()));
	public static final DeferredBlock<Block> POISON_CAULDRON = BLOCKS.register("poison_cauldron", () -> new PoisonCauldronBlock(BlockBehaviour.Properties.ofFullCopy(CAULDRON)));

	public static final DeferredBlock<Block> COMBINER = registerBlock("combiner", () -> new CombinerBlock((Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASEDRUM).strength(2.5F))));

	//POTS

	public static final DeferredBlock<FlowerPotBlock> POTTED_AERLAVENDER = BLOCKS.register("potted_aerlavender", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AERLAVENDER, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_TALL_AERLAVENDER = BLOCKS.register("potted_tall_aerlavender", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, TALL_AERLAVENDER, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_AETHER_CATTAILS = BLOCKS.register("potted_aether_cattails", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AETHER_CATTAILS, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_RADIANT_ORCHID = BLOCKS.register("potted_radiant_orchid", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RADIANT_ORCHID, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_ENCHANTED_BLOSSOM = BLOCKS.register("potted_enchanted_blossom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ENCHANTED_BLOSSOM, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_SKY_TULIPS = BLOCKS.register("potted_sky_tulips", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SKY_TULIPS, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_IASPOVE = BLOCKS.register("potted_iaspove", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, IASPOVE, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_GOLDEN_ASPESS = BLOCKS.register("potted_golden_aspess", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GOLDEN_ASPESS, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_ECHAISY = BLOCKS.register("potted_echaisy", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ECHAISY, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_ROSEROOT_SAPLING = BLOCKS.register("potted_roseroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ROSEROOT_SAPLING, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_BLUE_ROSEROOT_SAPLING = BLOCKS.register("potted_blue_roseroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_ROSEROOT_SAPLING, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_YAGROOT_SAPLING = BLOCKS.register("potted_yagroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, TALL_AERLAVENDER, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_CRUDEROOT_SAPLING = BLOCKS.register("potted_cruderoot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CRUDEROOT_SAPLING, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_CONBERRY_SAPLING = BLOCKS.register("potted_conberry_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CONBERRY_SAPLING, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_SUNROOT_SAPLING = BLOCKS.register("potted_sunroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SUNROOT_SAPLING, Block.Properties.ofFullCopy(Blocks.FLOWER_POT)));

	//REDUX COMPATIBILITY

	public static final DeferredBlock<Block> GILDED_HOLYSTONE_BRICKS = registerAetherReduxBlockWithLogging("gilded_holystone_bricks", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> GILDED_HOLYSTONE_BRICK_STAIRS = registerAetherReduxBlock("gilded_holystone_brick_stairs", () -> new StairBlock(DABlocks.GILDED_HOLYSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> GILDED_HOLYSTONE_BRICK_SLAB = registerAetherReduxBlock("gilded_holystone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> GILDED_HOLYSTONE_BRICK_WALL = registerAetherReduxBlock("gilded_holystone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> BLIGHTMOSS_HOLYSTONE_BRICKS = registerAetherReduxBlock("blightmoss_holystone_bricks", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> BLIGHTMOSS_HOLYSTONE_BRICK_STAIRS = registerAetherReduxBlock("blightmoss_holystone_brick_stairs", () -> new StairBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> BLIGHTMOSS_HOLYSTONE_BRICK_SLAB = registerAetherReduxBlock("blightmoss_holystone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> BLIGHTMOSS_HOLYSTONE_BRICK_WALL = registerAetherReduxBlock("blightmoss_holystone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> GILDED_HOLYSTONE_TILES = registerAetherReduxBlock("gilded_holystone_tiles", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> GILDED_HOLYSTONE_TILE_STAIRS = registerAetherReduxBlock("gilded_holystone_tile_stairs", () -> new StairBlock(DABlocks.GILDED_HOLYSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> GILDED_HOLYSTONE_TILE_SLAB = registerAetherReduxBlock("gilded_holystone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> GILDED_HOLYSTONE_TILE_WALL = registerAetherReduxBlock("gilded_holystone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> BLIGHTMOSS_HOLYSTONE_TILES = registerAetherReduxBlock("blightmoss_holystone_tiles", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> BLIGHTMOSS_HOLYSTONE_TILE_STAIRS = registerAetherReduxBlock("blightmoss_holystone_tile_stairs", () -> new StairBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> BLIGHTMOSS_HOLYSTONE_TILE_SLAB = registerAetherReduxBlock("blightmoss_holystone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> BLIGHTMOSS_HOLYSTONE_TILE_WALL = registerAetherReduxBlock("blightmoss_holystone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));

	//GENESIS COMPATIBILITY

	public static final DeferredBlock<WallBlock> ROSEROOT_LOG_WALL = registerAetherGenesisBlockWithLogging("roseroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallBlock> STRIPPED_ROSEROOT_LOG_WALL = registerAetherGenesisBlock("stripped_roseroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallBlock> CRUDEROOT_LOG_WALL = registerAetherGenesisBlock("cruderoot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallBlock> STRIPPED_CRUDEROOT_LOG_WALL = registerAetherGenesisBlock("stripped_cruderoot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallBlock> YAGROOT_LOG_WALL = registerAetherGenesisBlock("yagroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallBlock> STRIPPED_YAGROOT_LOG_WALL = registerAetherGenesisBlock("stripped_yagroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallBlock> CONBERRY_LOG_WALL = registerAetherGenesisBlock("conberry_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallBlock> STRIPPED_CONBERRY_LOG_WALL = registerAetherGenesisBlock("stripped_conberry_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallBlock> SUNROOT_LOG_WALL = registerAetherGenesisBlock("sunroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<WallBlock> STRIPPED_SUNROOT_LOG_WALL = registerAetherGenesisBlock("stripped_sunroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));

	//Deep Aether 1.2/Experimental
	public static final DeferredBlock<Block> AERCLOUD_ROOT_CARPET = registerDisabledBlock("aercloud_root_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET).isViewBlocking(DABlocks::never).noOcclusion()));
	public static final DeferredBlock<Block> TALL_ALIEN_PLANT = registerDisabledBlock("tall_alien_plant", ()-> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));

	public static final DeferredBlock<Block> PINK_AERCLOUD_MUSHROOM_BLOCK = registerDisabledBlock("pink_aercloud_mushroom_block", () -> new HalfTransperentHugeMushroomBlock((Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.3F).sound(SoundType.WOOL).noOcclusion().dynamicShape().isRedstoneConductor(DABlocks::never).isSuffocating(DABlocks::never).isViewBlocking(DABlocks::never).forceSolidOn())));
	public static final DeferredBlock<Block> PINK_AERCLOUD_MUSHROOMS = registerDisabledBlock("pink_aercloud_mushrooms", () -> new AercloudMushroomBlock(DAConfiguredFeatures.HUGE_PINK_AERCLOUD_MUSHROOM, Block.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM).noOcclusion()));
	public static final DeferredBlock<Block> BLUE_AERCLOUD_MUSHROOM_BLOCK = registerDisabledBlock("blue_aercloud_mushroom_block", () -> new HalfTransperentHugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava()));
	public static final DeferredBlock<Block> BLUE_AERCLOUD_MUSHROOMS = registerDisabledBlock("blue_aercloud_mushrooms", () -> new AercloudMushroomBlock(DAConfiguredFeatures.HUGE_BLUE_AERCLOUD_MUSHROOM, Block.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM).noOcclusion()));

	public static final DeferredBlock<Block> MOA_TOTEM = registerDisabledBlock("moa_totem", () -> new TotemBlock(Block.Properties.of().noOcclusion()));
	public static final DeferredBlock<Block> ZEPHYR_TOTEM = registerDisabledBlock("zephyr_totem", () -> new TotemBlock(Block.Properties.of().noOcclusion()));
	public static final DeferredBlock<Block> AERWHALE_TOTEM = registerDisabledBlock("aerwhale_totem", () -> new TotemBlock(Block.Properties.of().noOcclusion()));

	public static final DeferredBlock<Block> RAIN_AERCLOUD = registerDisabledBlock("rain_aercloud", () -> new AercloudBlock(BlockBehaviour.Properties.ofFullCopy(AetherBlocks.COLD_AERCLOUD.get()).forceSolidOn()));

	public static final DeferredBlock<Block> AERCLOUD_GRASS_BLOCK = registerDisabledBlock("aercloud_grass_block", () -> new AercloudGrassBlock(Block.Properties.of()
			.mapColor(MapColor.COLOR_PINK).randomTicks().strength(0.2F).sound(SoundType.GRASS).forceSolidOn()));
	public static final DeferredBlock<Block> AERCLOUD_ROOTS = registerDisabledBlock("aercloud_roots", () -> new AetherDoubleDropsLeaves (
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {

		@Override
		public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
			return true;
		}

		@Override
		public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
			return 1;
		}
	});

	public static void registerPots() {
		FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;
		pot.addPlant(DABlocks.AERLAVENDER.getId(), DABlocks.POTTED_AERLAVENDER);
		pot.addPlant(DABlocks.TALL_AERLAVENDER.getId(), DABlocks.POTTED_TALL_AERLAVENDER);
		pot.addPlant(DABlocks.AETHER_CATTAILS.getId(), DABlocks.POTTED_AETHER_CATTAILS);
		pot.addPlant(DABlocks.RADIANT_ORCHID.getId(), DABlocks.POTTED_RADIANT_ORCHID);
		pot.addPlant(DABlocks.ENCHANTED_BLOSSOM.getId(), DABlocks.POTTED_ENCHANTED_BLOSSOM);
		pot.addPlant(DABlocks.SKY_TULIPS.getId(), DABlocks.POTTED_SKY_TULIPS);
		pot.addPlant(DABlocks.IASPOVE.getId(), DABlocks.POTTED_IASPOVE);
		pot.addPlant(DABlocks.GOLDEN_ASPESS.getId(), DABlocks.POTTED_GOLDEN_ASPESS);
		pot.addPlant(DABlocks.ECHAISY.getId(), DABlocks.POTTED_ECHAISY);

		pot.addPlant(DABlocks.ROSEROOT_SAPLING.getId(), DABlocks.POTTED_ROSEROOT_SAPLING);
		pot.addPlant(DABlocks.BLUE_ROSEROOT_SAPLING.getId(), DABlocks.POTTED_BLUE_ROSEROOT_SAPLING);
		pot.addPlant(DABlocks.YAGROOT_SAPLING.getId(), DABlocks.POTTED_YAGROOT_SAPLING);
		pot.addPlant(DABlocks.CRUDEROOT_SAPLING.getId(), DABlocks.POTTED_CRUDEROOT_SAPLING);
		pot.addPlant(DABlocks.CONBERRY_SAPLING.getId(), DABlocks.POTTED_CONBERRY_SAPLING);
		pot.addPlant(DABlocks.SUNROOT_SAPLING.getId(), DABlocks.POTTED_SUNROOT_SAPLING);

	}


	private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
		DAItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static <T extends Block> void registerBlockItemDisabled(String name, DeferredBlock<T> block) {
		DAItems.ITEMS.register(name, () -> new DisabledBlockItem(block.get(), new Item.Properties()));
	}
	private static <T extends Block> DeferredBlock<T> registerAetherReduxBlock(String name, Supplier<T> block) {
		return registerCompatBlock(DeepAether.AETHER_REDUX, name, block);
	}

	private static <T extends Block> DeferredBlock<T> registerAetherReduxBlockWithLogging(String name, Supplier<T> block) {
		if(ModList.get().isLoaded(DeepAether.AETHER_REDUX))
			DeepAether.LOGGER.info("Deep Aether: Registering Aether Redux compat blocks");
		return registerCompatBlock(DeepAether.AETHER_REDUX, name, block);
	}

	private static <T extends Block> DeferredBlock<T> registerAetherGenesisBlock(String name, Supplier<T> block) {
		return registerCompatBlock(DeepAether.AETHER_GENESIS, name, block);
	}
	private static <T extends Block> DeferredBlock<T> registerAetherGenesisBlockWithLogging(String name, Supplier<T> block) {
		if(ModList.get().isLoaded(DeepAether.AETHER_GENESIS))
			DeepAether.LOGGER.info("Deep Aether: Registering Aether Genesis compat blocks");
		return registerAetherGenesisBlock(name, block);
	}

	private static <T extends Block> DeferredBlock<T> registerCompatBlock(String modid, String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = BLOCKS.register(name, block);
		if(ModList.get().isLoaded(modid)) {
			registerBlockItem(name, toReturn);
		}
		else registerBlockItemDisabled(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> DeferredBlock<T> registerDisabledBlock(String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = BLOCKS.register(name, block);
		registerBlockItemDisabled(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> DeferredBlock<T> registerBlock(int burnTime, String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = BLOCKS.register(name, block);
		registerBurnableBlockItem(burnTime, name, toReturn);
		return toReturn;
	}
	private static <T extends Block> void registerBurnableBlockItem(int burnTime, String name, DeferredBlock<T> block) {
		DAItems.ITEMS.register(name, () -> new BurnableBlockItem(burnTime, block.get(), new Item.Properties()));
	}

	@SuppressWarnings("unchecked")
	private static <B extends Block> DeferredBlock<B> registerTrapped(String name, Supplier<? extends Block> block) {
		return (DeferredBlock<B>) registerBlock(name, block);
	}

	public static void registerWoodTypes() {
		WoodType.register(DAWoodTypes.ROSEROOT);
		WoodType.register(DAWoodTypes.CRUDEROOT);
		WoodType.register(DAWoodTypes.YAGROOT);
		WoodType.register(DAWoodTypes.CONBERRY);
		WoodType.register(DAWoodTypes.SUNROOT);
	}

	public static void registerFlammability() {
		FireBlockAccessor fireBlockAccessor = (FireBlockAccessor) Blocks.FIRE;
		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_LEAVES.get(), 30, 60);
		fireBlockAccessor.callSetFlammable(DABlocks.BLUE_ROSEROOT_LEAVES.get(), 30, 60);
		fireBlockAccessor.callSetFlammable(DABlocks.FLOWERING_ROSEROOT_LEAVES.get(), 30, 60);
		fireBlockAccessor.callSetFlammable(DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(), 30, 60);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_LEAVES.get(), 30, 60);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_LEAVES.get(), 30, 60);
		fireBlockAccessor.callSetFlammable(DABlocks.CONBERRY_LEAVES.get(), 30, 60);
		fireBlockAccessor.callSetFlammable(DABlocks.SUNROOT_LEAVES.get(), 30, 60);

		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.ROTTEN_ROSEROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_ROSEROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_CRUDEROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_YAGROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.CONBERRY_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_CONBERRY_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.SUNROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_SUNROOT_LOG.get(), 5, 5);

		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_WOOD.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_WOOD.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_WOOD.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.CONBERRY_WOOD.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.SUNROOT_WOOD.get(), 5, 5);


		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_PLANKS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_PLANKS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_PLANKS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.CONBERRY_PLANKS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.SUNROOT_PLANKS.get(), 5, 20);

		fireBlockAccessor.callSetFlammable(DABlocks.AERLAVENDER.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.TALL_AERLAVENDER.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.AETHER_CATTAILS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.TALL_AETHER_CATTAILS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.RADIANT_ORCHID.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.GOLDEN_FLOWER.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.ENCHANTED_BLOSSOM.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.SKY_TULIPS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.IASPOVE.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.GOLDEN_ASPESS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.ECHAISY.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.FEATHER_GRASS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.TALL_FEATHER_GRASS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.MINI_GOLDEN_GRASS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.SHORT_GOLDEN_GRASS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.MEDIUM_GOLDEN_GRASS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.TALL_GOLDEN_GRASS.get(), 60, 100);

		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_FENCE_GATE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_FENCE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_STAIRS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_SLAB.get(), 5, 20);

		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_FENCE_GATE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_FENCE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_STAIRS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_SLAB.get(), 5, 20);

		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_FENCE_GATE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_FENCE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_STAIRS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_SLAB.get(), 5, 20);

		fireBlockAccessor.callSetFlammable(DABlocks.CONBERRY_FENCE_GATE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.CONBERRY_FENCE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.CONBERRY_STAIRS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.CONBERRY_SLAB.get(), 5, 20);

		fireBlockAccessor.callSetFlammable(DABlocks.SUNROOT_FENCE_GATE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.SUNROOT_FENCE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.SUNROOT_STAIRS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.SUNROOT_SLAB.get(), 5, 20);

		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_CRUDEROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_ROSEROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_YAGROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.CONBERRY_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_CONBERRY_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.SUNROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_SUNROOT_WALL.get(), 5, 20);
	}


	private static boolean never(BlockState p_test_1_, BlockGetter p_test_2_, BlockPos p_test_3_) {
		return false;
	}

	private static boolean always(BlockState p_test_1_, BlockGetter p_test_2_, BlockPos p_test_3_) {
		return true;
	}

	private static <A> boolean never(BlockState p_test_1_, BlockGetter p_test_2_, BlockPos p_test_3_, A p_test_4_) {
		return false;
	}
	private static boolean ocelotOrParrot(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
		return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
	}

	public static class BlockReferences {
		public static final ResourceKey<Block> BLUE_SQUASH = createKey("blue_squash");
		public static final ResourceKey<Block> GREEN_SQUASH = createKey("green_squash");
		public static final ResourceKey<Block> PURPLE_SQUASH = createKey("purple_squash");
		public static final ResourceKey<Block> SQUASH_STEM = createKey("squash_stem");
		public static final ResourceKey<Block> ATTACHED_SQUASH_STEM = createKey("attached_squash_stem");

		private static ResourceKey<Block> createKey(String pId) {
			return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, pId));
		}
	}

	public static class ItemReferences {
		public static final ResourceKey<Item> SQUASH_SEEDS = createKey("squash_seeds");

		private static ResourceKey<Item> createKey(String pId) {
			return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, pId));
		}
	}
}


