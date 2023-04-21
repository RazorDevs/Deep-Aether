

package teamrazor.deepaether.init;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.construction.AetherDirtPathBlock;
import com.aetherteam.aether.block.natural.AercloudBlock;
import com.aetherteam.aether.block.natural.AetherDoubleDropBlock;
import com.aetherteam.aether.block.natural.AetherDoubleDropsLeaves;
import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.mixin.mixins.common.accessor.FireBlockAccessor;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.block.*;
import teamrazor.deepaether.block.Behaviors.GoldenVines;
import teamrazor.deepaether.world.feature.tree.*;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.CAULDRON;
import static net.minecraft.world.level.block.Blocks.MOSSY_STONE_BRICKS;

public class DABlocks {
	public static final DeferredRegister<Block> BLOCKS =
			DeferredRegister.create(ForgeRegistries.BLOCKS, DeepAetherMod.MODID);

	//GRASS
	public static final RegistryObject<Block> GOLDEN_HEIGHTS_GRASS_BLOCK = registerBlock("golden_heights_grass_block", () -> new GoldenGrassBlock(Block.Properties.of(Material.GRASS, MaterialColor.WARPED_WART_BLOCK).randomTicks().strength(0.2F).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> GOLDEN_HEIGHTS_DIRT_PATH = registerBlock("golden_heights_dirt_path", () -> new AetherDirtPathBlock(Block.Properties.of(Material.DIRT, MaterialColor.TERRACOTTA_CYAN).strength(0.2F).sound(SoundType.GRASS)));

	public static final RegistryObject<Block> MINI_GOLDEN_GRASS = registerBlock("mini_golden_grass", ()-> new BushBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)));
	public static final RegistryObject<Block> SHORT_GOLDEN_GRASS = registerBlock("short_golden_grass", ()-> new BushBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)));
	public static final RegistryObject<Block> MEDIUM_GOLDEN_GRASS = registerBlock("medium_golden_grass", ()-> new BushBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)));
	public static final RegistryObject<Block> TALL_GOLDEN_GRASS = registerBlock("tall_golden_grass", ()-> new DoublePlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));


	// WOOD
	public static final RegistryObject<Block> ROSEROOT_WOOD = registerBlock("roseroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> ROSEROOT_LOG = registerBlock("roseroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> ROSEROOT_WALL = registerBlock(300, "roseroot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_ROSEROOT_WOOD = registerBlock("stripped_roseroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_ROSEROOT_LOG = registerBlock("stripped_roseroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_ROSEROOT_WALL = registerBlock("stripped_roseroot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> ROSEROOT_PLANKS = registerBlock(300, "roseroot_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> ROSEROOT_STAIRS = registerBlock("roseroot_stairs", () -> new StairBlock(() -> DABlocks.ROSEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> ROSEROOT_SLAB = registerBlock("roseroot_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
	public static final RegistryObject<Block> ROSEROOT_FENCE = registerBlock("roseroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> ROSEROOT_FENCE_GATE = registerBlock("roseroot_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), DAWoodTypes.ROSEROOT));
	public static final RegistryObject<Block> ROSEROOT_PRESSURE_PLATE = registerBlock("roseroot_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.OAK_PRESSURE_PLATE), DAWoodTypes.ROSEROOT_BLOCK_SET));
	public static final RegistryObject<Block> ROSEROOT_BUTTON = registerBlock("roseroot_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), DAWoodTypes.ROSEROOT_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> ROSEROOT_DOOR = registerBlock("roseroot_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), DAWoodTypes.ROSEROOT_BLOCK_SET));
	public static final RegistryObject<Block> ROSEROOT_TRAPDOOR = registerBlock("roseroot_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), DAWoodTypes.ROSEROOT_BLOCK_SET));
	public static final RegistryObject<Block> ROSEROOT_LEAVES = registerBlock("roseroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> FLOWERING_ROSEROOT_LEAVES = registerBlock("flowering_roseroot_leaves", () -> new FloweringRoseLeavesBlock());
	public static final RegistryObject<Block> ROSEROOT_SAPLING = registerBlock("roseroot_sapling", () -> new SaplingBlock( new RosewoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Block> BLUE_ROSEROOT_LEAVES = registerBlock("blue_roseroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> FLOWERING_BLUE_ROSEROOT_LEAVES = registerBlock("flowering_blue_roseroot_leaves", () -> new FloweringRoseLeavesBlock());
	public static final RegistryObject<Block> BLUE_ROSEROOT_SAPLING = registerBlock("blue_roseroot_sapling", () -> new SaplingBlock( new BlueRosewoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Block> ROSEROOT_WALL_SIGN = BLOCKS.register("roseroot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DAWoodTypes.ROSEROOT));
	public static final RegistryObject<Block> ROSEROOT_SIGN = BLOCKS.register("roseroot_sign", () -> new DASignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DAWoodTypes.ROSEROOT));



	//YAGROOT
	public static final RegistryObject<Block> YAGROOT_WOOD = registerBlock("yagroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> YAGROOT_LOG = registerBlock("yagroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> YAGROOT_WALL = registerBlock(300,"yagroot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_WOOD = registerBlock("stripped_yagroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_LOG = registerBlock("stripped_yagroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_WALL = registerBlock(300,"stripped_yagroot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> YAGROOT_PLANKS = registerBlock(300, "yagroot_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> YAGROOT_STAIRS = registerBlock("yagroot_stairs", () -> new StairBlock(() -> DABlocks.ROSEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> YAGROOT_SLAB = registerBlock("yagroot_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
	public static final RegistryObject<Block> YAGROOT_FENCE = registerBlock("yagroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> YAGROOT_FENCE_GATE = registerBlock("yagroot_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), DAWoodTypes.YAGROOT));
	public static final RegistryObject<Block> YAGROOT_PRESSURE_PLATE = registerBlock("yagroot_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.OAK_PRESSURE_PLATE), DAWoodTypes.YAGROOT_BLOCK_SET));
	public static final RegistryObject<Block> YAGROOT_BUTTON = registerBlock("yagroot_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), DAWoodTypes.YAGROOT_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> YAGROOT_DOOR = registerBlock("yagroot_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), DAWoodTypes.YAGROOT_BLOCK_SET));
	public static final RegistryObject<Block> YAGROOT_TRAPDOOR = registerBlock("yagroot_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), DAWoodTypes.YAGROOT_BLOCK_SET));
	public static final RegistryObject<Block> YAGROOT_LEAVES = registerBlock("yagroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> YAGROOT_SAPLING = registerBlock("yagroot_sapling", () -> new SaplingBlock( new YagrootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.MANGROVE_PROPAGULE)));
	public static final RegistryObject<Block> YAGROOT_ROOTS = registerBlock("yagroot_roots", () -> new DADoubleDropRotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(0.7F).randomTicks().sound(SoundType.MANGROVE_ROOTS).noOcclusion()));
	public static final RegistryObject<Block> MUDDY_YAGROOT_ROOTS = registerBlock("muddy_yagroot_roots", () -> new DADoubleDropRotatedPillarBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.PODZOL).strength(0.7F).sound(SoundType.MUDDY_MANGROVE_ROOTS)));

	public static final RegistryObject<Block> YAGROOT_WALL_SIGN = BLOCKS.register("yagroot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DAWoodTypes.YAGROOT));
	public static final RegistryObject<Block> YAGROOT_SIGN = BLOCKS.register("yagroot_sign", () -> new DASignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DAWoodTypes.YAGROOT));


	//CRUDEROOOT
	public static final RegistryObject<Block> CRUDEROOT_WOOD = registerBlock("cruderoot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> CRUDEROOT_LOG = registerBlock("cruderoot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> CRUDEROOT_WALL = registerBlock(300,"cruderoot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_WOOD = registerBlock("stripped_cruderoot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_LOG = registerBlock("stripped_cruderoot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_WALL = registerBlock(300,"stripped_cruderoot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> CRUDEROOT_PLANKS = registerBlock(300, "cruderoot_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> CRUDEROOT_STAIRS = registerBlock("cruderoot_stairs", () -> new StairBlock(() -> DABlocks.CRUDEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> CRUDEROOT_SLAB = registerBlock("cruderoot_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
	public static final RegistryObject<Block> CRUDEROOT_FENCE = registerBlock("cruderoot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> CRUDEROOT_FENCE_GATE = registerBlock("cruderoot_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), DAWoodTypes.CRUDEROOT));
	public static final RegistryObject<Block> CRUDEROOT_PRESSURE_PLATE = registerBlock("cruderoot_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.OAK_PRESSURE_PLATE), DAWoodTypes.CRUDEROOT_BLOCK_SET));
	public static final RegistryObject<Block> CRUDEROOT_BUTTON = registerBlock("cruderoot_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), DAWoodTypes.CRUDEROOT_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> CRUDEROOT_DOOR = registerBlock("cruderoot_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), DAWoodTypes.CRUDEROOT_BLOCK_SET));
	public static final RegistryObject<Block> CRUDEROOT_TRAPDOOR = registerBlock("cruderoot_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), DAWoodTypes.CRUDEROOT_BLOCK_SET));
	public static final RegistryObject<Block> CRUDEROOT_LEAVES = registerBlock("cruderoot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> CRUDEROOT_SAPLING = registerBlock("cruderoot_sapling", () -> new SaplingBlock( new CruderootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.MANGROVE_PROPAGULE)));
	public static final RegistryObject<Block> CRUDEROOT_WALL_SIGN = BLOCKS.register("cruderoot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DAWoodTypes.CRUDEROOT));
	public static final RegistryObject<Block> CRUDEROOT_SIGN = BLOCKS.register("cruderoot_sign", () -> new DASignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DAWoodTypes.CRUDEROOT));
	public static final RegistryObject<Block> YAGROOT_VINE = registerBlock("yagroot_vine", () -> new VineBlock(BlockBehaviour.Properties.copy(Blocks.VINE)));

	//AMBERROOT

	public static final RegistryObject<Block> AMBERROOT_WOOD = registerBlock("amberroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> AMBERROOT_LOG = registerBlock("amberroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> AMBERROOT_WALL = registerBlock(300,"amberroot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_AMBERROOT_WOOD = registerBlock("stripped_amberroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_AMBERROOT_LOG = registerBlock("stripped_amberroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_AMBERROOT_WALL = registerBlock(300,"stripped_amberroot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> AMBERROOT_PLANKS = registerBlock( 300,"amberroot_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> AMBERROOT_STAIRS = registerBlock("amberroot_stairs", () -> new StairBlock(() -> DABlocks.AMBERROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> AMBERROOT_SLAB = registerBlock("amberroot_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
	public static final RegistryObject<Block> AMBERROOT_FENCE = registerBlock("amberroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> AMBERROOT_FENCE_GATE = registerBlock("amberroot_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), DAWoodTypes.AMBERROOT));
	public static final RegistryObject<Block> AMBERROOT_PRESSURE_PLATE = registerBlock("amberroot_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.OAK_PRESSURE_PLATE), DAWoodTypes.AMBERROOT_BLOCK_SET));
	public static final RegistryObject<Block> AMBERROOT_BUTTON = registerBlock("amberroot_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), DAWoodTypes.AMBERROOT_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> AMBERROOT_DOOR = registerBlock("amberroot_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), DAWoodTypes.AMBERROOT_BLOCK_SET));
	public static final RegistryObject<Block> AMBERROOT_TRAPDOOR = registerBlock("amberroot_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), DAWoodTypes.AMBERROOT_BLOCK_SET));
	public static final RegistryObject<Block> AMBERROOT_LEAVES = registerBlock("amberroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> AMBERROOT_SAPLING = registerBlock("amberroot_sapling", () -> new SaplingBlock( new AmberrootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.MANGROVE_PROPAGULE)));
	public static final RegistryObject<Block> AMBERROOT_WALL_SIGN = BLOCKS.register("amberroot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DAWoodTypes.AMBERROOT));
	public static final RegistryObject<Block> AMBERROOT_SIGN = BLOCKS.register("amberroot_sign", () -> new DASignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DAWoodTypes.AMBERROOT));

	//SKYROOT WALLS
	public static final RegistryObject<Block> STRIPPED_SKYROOT_WALL = registerBlock(300,"stripped_skyroot_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> SKYROOT_WALL = registerBlock(300,"skyroot_wall", () ->  new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));


	// ORES
	public static final RegistryObject<Block> SKYJADE_ORE = registerBlock("skyjade_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	public static final RegistryObject<Block> SKYJADE_BLOCK = registerBlock("skyjade_block", () -> new Block (BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	//public static final RegistryObject<Block> AGATE_ORE = registerBlock("agate_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	//public static final RegistryObject<Block> HIGHSTONE_AGATE_ORE = registerBlock("highstone_agate_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	//public static final RegistryObject<Block> AGATE_BLOCK = registerBlock("agate_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	//public static final RegistryObject<Block> ADIBIUM_ORE = registerBlock("adibium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	//public static final RegistryObject<Block> HIGHSTONE_ADIBIUM_ORE = registerBlock("highstone_adibium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	//public static final RegistryObject<Block> ADIBIUM_BLOCK = registerBlock("adibium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	public static final RegistryObject<Block> CLOUDIUM_DEBRIS = registerBlock("cloudium_debris", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(10f, 10f)));
	public static final RegistryObject<Block> CLOUDIUM_BLOCK = registerBlock("cloudium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(10f, 10f)));

	//public static final RegistryObject<Block> ORATIE_ORE = registerBlock("oratie_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	//public static final RegistryObject<Block> HIGHSTONE_ORATIE_ORE = registerBlock("highstone_oratie_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f)));
	//public static final RegistryObject<Block> RAW_ORATIE_BLOCK= registerBlock("raw_oratie_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));
	//public static final RegistryObject<Block> ORATIE_BLOCK = registerBlock("oratie_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f)));

	public static final RegistryObject<Block> ASETERITE = registerBlock("aseterite", () -> new AetherDoubleDropBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE = registerBlock("polished_aseterite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_STAIRS = registerBlock("polished_aseterite_stairs", () -> new StairBlock(() -> DABlocks.POLISHED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ASETERITE_STAIRS = registerBlock("aseterite_stairs", () -> new StairBlock(() -> DABlocks.ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ASETERITE_SLAB = registerBlock("aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_SLAB = registerBlock("polished_aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> ASETERITE_WALL = registerBlock("aseterite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CLORITE = registerBlock("clorite", () -> new AetherDoubleDropBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE = registerBlock("polished_clorite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> CLORITE_SLAB = registerBlock("clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE_SLAB = registerBlock("polished_clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> CLORITE_STAIRS = registerBlock("clorite_stairs", () -> new StairBlock(() -> DABlocks.CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_CLORITE_STAIRS = registerBlock("polished_clorite_stairs", () -> new StairBlock(() -> DABlocks.POLISHED_CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CLORITE_WALL = registerBlock("clorite_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CLORITE_PILLAR = registerBlock("clorite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> HOLYSTONE_TILES = registerBlock("holystone_tiles", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MOSSY_HOLYSTONE_TILES = registerBlock("mossy_holystone_tiles", () -> new Block(BlockBehaviour.Properties.copy(MOSSY_STONE_BRICKS)));

	public static final RegistryObject<Block> HOLYSTONE_TILE_STAIRS = registerBlock("holystone_tile_stairs", () -> new StairBlock(DABlocks.HOLYSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MOSSY_HOLYSTONE_TILE_STAIRS = registerBlock("mossy_holystone_tile_stairs", () -> new StairBlock(DABlocks.MOSSY_HOLYSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_STONE_BRICKS)));

	public static final RegistryObject<Block> HOLYSTONE_TILE_SLAB = registerBlock("holystone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MOSSY_HOLYSTONE_TILE_SLAB = registerBlock("mossy_holystone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_STONE_BRICKS)));

	public static final RegistryObject<Block> HOLYSTONE_TILE_WALL = registerBlock("holystone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MOSSY_HOLYSTONE_TILE_WALL = registerBlock("mossy_holystone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_STONE_BRICKS)));


	//MISC
	public static final RegistryObject<Block> RAIN_AERCLOUD = registerBlock("rain_aercloud", () -> new AercloudBlock(BlockBehaviour.Properties.copy(AetherBlocks.COLD_AERCLOUD.get())));
	public static final RegistryObject<Block> RADIANT_ORCHID = registerBlock("radiant_orchid", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.of(Material.PLANT).noCollission().sound(SoundType.GRASS).instabreak().lightLevel(s -> 5)));
	public static final RegistryObject<Block> AERLAVENDER = registerBlock("aerlavender", () ->  new FlowerBlock(MobEffects.JUMP, 6, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> TALL_AERLAVENDER = registerBlock("tall_aerlavender", () ->  new FlowerBlock(MobEffects.JUMP, 6, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> AETHER_CATTAILS = registerBlock("aether_cattails", () ->  new FlowerBlock(AetherEffects.INEBRIATION, 6, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> TALL_AETHER_CATTAILS = registerBlock("tall_aether_cattails", () ->  new TallFlowerBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> GOLDEN_FLOWER = registerBlock("golden_flower", () ->  new FlowerBlock(MobEffects.GLOWING,6, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));


	public static final RegistryObject<Block> AERGLOW_PETAL_BLOCK = registerBlock("aerglow_petal_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.MOSS).strength(1f, 10f).lightLevel(s -> 9)));
	public static final RegistryObject<Block> AETHER_MOSS_CARPET = registerBlock("aether_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET)));
	public static final RegistryObject<Block> AETHER_MOSS_BLOCK = registerBlock("aether_moss_block", () -> new DAMossBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)));

	public static final RegistryObject<Block> VIRULENT_QUICKSAND = registerBlock("virulent_quicksand", () -> new VirulentQuicksandBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));

	//MUD
	public static final RegistryObject<Block> AETHER_MUD = registerBlock("aether_mud", () -> new DoubleDropMudBlock(BlockBehaviour.Properties.copy(Blocks.MUD)));
	public static final RegistryObject<Block> PACKED_AETHER_MUD = registerBlock("packed_aether_mud", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PACKED_MUD)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS = registerBlock("aether_mud_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_SLAB = registerBlock("aether_mud_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICK_SLAB)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_STAIRS = registerBlock("aether_mud_bricks_stairs", () -> new StairBlock(() -> DABlocks.AETHER_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.MUD_BRICKS).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));


	public static final RegistryObject<Block>  GOLDEN_VINES = BLOCKS.register("golden_vines", () -> new GoldenVinesBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().lightLevel(GoldenVines.emission(1)).instabreak().sound(SoundType.CAVE_VINES)));
	public static final RegistryObject<Block>  GOLDEN_VINES_PLANT = BLOCKS.register("golden_vines_plant", () -> new GoldenVinesPlantBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().lightLevel(GoldenVines.emission(1)).instabreak().sound(SoundType.CAVE_VINES)));

	public static final RegistryObject<LiquidBlock> POISON_BLOCK = BLOCKS.register("poison", () -> new PoisonBlock(DAFluids.POISON_FLUID, BlockBehaviour.Properties.of(Material.LAVA)
			.noCollission()
			.strength(100f)
			.noLootTable()));
	public static final RegistryObject<Block> POISON_CAULDRON = BLOCKS.register("poison_cauldron", () -> new PoisonCauldronBlock(BlockBehaviour.Properties.copy(CAULDRON)));


	//POTS
	public static final RegistryObject<FlowerPotBlock> POTTED_AERLAVENDER = BLOCKS.register("potted_aerlavender", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AERLAVENDER, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_TALL_AERLAVENDER = BLOCKS.register("potted_tall_aerlavender", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, TALL_AERLAVENDER, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_AETHER_CATTAILS = BLOCKS.register("potted_aether_cattails", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AETHER_CATTAILS, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_RADIANT_ORCHID = BLOCKS.register("potted_radiant_orchid", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RADIANT_ORCHID, Block.Properties.copy(Blocks.FLOWER_POT)));

	public static final RegistryObject<FlowerPotBlock> POTTED_ROSEROOT_SAPLING = BLOCKS.register("potted_roseroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ROSEROOT_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_BLUE_ROSEROOT_SAPLING = BLOCKS.register("potted_blue_roseroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_ROSEROOT_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_YAGROOT_SAPLING = BLOCKS.register("potted_yagroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, TALL_AERLAVENDER, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_CRUDEROOT_SAPLING = BLOCKS.register("potted_cruderoot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CRUDEROOT_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_AMBERROOT_SAPLING = BLOCKS.register("potted_amberroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AMBERROOT_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static void registerPots() {
		FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;
		pot.addPlant(DABlocks.AERLAVENDER.getId(), DABlocks.POTTED_AERLAVENDER);
		pot.addPlant(DABlocks.TALL_AERLAVENDER.getId(), DABlocks.POTTED_TALL_AERLAVENDER);
		pot.addPlant(DABlocks.AETHER_CATTAILS.getId(), DABlocks.POTTED_AETHER_CATTAILS);
		pot.addPlant(DABlocks.RADIANT_ORCHID.getId(), DABlocks.POTTED_RADIANT_ORCHID);

		pot.addPlant(DABlocks.ROSEROOT_SAPLING.getId(), DABlocks.POTTED_ROSEROOT_SAPLING);
		pot.addPlant(DABlocks.BLUE_ROSEROOT_SAPLING.getId(), DABlocks.POTTED_ROSEROOT_SAPLING);
		pot.addPlant(DABlocks.YAGROOT_SAPLING.getId(), DABlocks.POTTED_YAGROOT_SAPLING);
		pot.addPlant(DABlocks.CRUDEROOT_SAPLING.getId(), DABlocks.POTTED_CRUDEROOT_SAPLING);
		pot.addPlant(DABlocks.AMBERROOT_SAPLING.getId(), DABlocks.POTTED_AMBERROOT_SAPLING);

	}


	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		return DAItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static <T extends Block> RegistryObject<T> registerBlock(int burnTime, String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBurnableBlockItem(burnTime, name, toReturn);
		return toReturn;
	}
	private static <T extends Block> RegistryObject<Item> registerBurnableBlockItem(int burnTime, String name, RegistryObject<T> block) {
		return DAItems.ITEMS.register(name, () -> new BurnableBlockItem(burnTime, block.get(), new Item.Properties()));
	}


	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}

	public static void registerWoodTypes() {
		WoodType.register(DAWoodTypes.ROSEROOT);
		WoodType.register(DAWoodTypes.CRUDEROOT);
		WoodType.register(DAWoodTypes.YAGROOT);
		WoodType.register(DAWoodTypes.AMBERROOT);
	}
	public static void registerFlammability() {
		FireBlockAccessor fireBlockAccessor = (FireBlockAccessor) Blocks.FIRE;
		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_LEAVES.get(), 30, 60);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_LEAVES.get(), 30, 60);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_LEAVES.get(), 30, 60);
		fireBlockAccessor.callSetFlammable(DABlocks.AMBERROOT_LEAVES.get(), 30, 60);

		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_ROSEROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_CRUDEROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_YAGROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.AMBERROOT_LOG.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_AMBERROOT_LOG.get(), 5, 5);

		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_WOOD.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_WOOD.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_WOOD.get(), 5, 5);
		fireBlockAccessor.callSetFlammable(DABlocks.AMBERROOT_WOOD.get(), 5, 5);


		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_PLANKS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_PLANKS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_PLANKS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.AMBERROOT_PLANKS.get(), 5, 20);

		fireBlockAccessor.callSetFlammable(DABlocks.AERLAVENDER.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.TALL_AERLAVENDER.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.AETHER_CATTAILS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.TALL_AETHER_CATTAILS.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.RADIANT_ORCHID.get(), 60, 100);
		fireBlockAccessor.callSetFlammable(DABlocks.GOLDEN_FLOWER.get(), 60, 100);
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

		fireBlockAccessor.callSetFlammable(DABlocks.AMBERROOT_FENCE_GATE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.AMBERROOT_FENCE.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.AMBERROOT_STAIRS.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.AMBERROOT_SLAB.get(), 5, 20);

		fireBlockAccessor.callSetFlammable(DABlocks.CRUDEROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_CRUDEROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.ROSEROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_ROSEROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.YAGROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_YAGROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.AMBERROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_AMBERROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.SKYROOT_WALL.get(), 5, 20);
		fireBlockAccessor.callSetFlammable(DABlocks.STRIPPED_SKYROOT_WALL.get(), 5, 20);
	}



		//QUARK SUPPORT
/*
	//POSTS
	public static final RegistryObject<Block> ROSEROOT_POST = HELPER.createCompatFuelBlock("quark", "roseroot_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DABlocks.ROSEROOT_LOG.get())), 300);
	public static final RegistryObject<Block> STRIPPED_ROSEROOT_POST = HELPER.createCompatFuelBlock("quark", "stripped_roseroot_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DABlocks.ROSEROOT_LOG.get())), 300);
	public static final RegistryObject<Block> YAGROOT_POST = HELPER.createCompatFuelBlock("quark", "yagroot_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DABlocks.ROSEROOT_LOG.get())), 300);
	public static final RegistryObject<Block> STRIPPED_YAGROOT_POST = HELPER.createCompatFuelBlock("quark", "stripped_yagroot_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DABlocks.ROSEROOT_LOG.get())), 300);
	public static final RegistryObject<Block> CRUDEROOT_POST = HELPER.createCompatFuelBlock("quark", "cruderoot_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DABlocks.ROSEROOT_LOG.get())), 300);
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_POST = HELPER.createCompatFuelBlock("quark", "stripped_cruderoot_post",() -> new WoodPostBlock(BlockBehaviour.Properties.copy(DABlocks.ROSEROOT_LOG.get())), 300);

	//SHELVES
	public static final RegistryObject<Block> ROSEROOT_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "roseroot_bookshelf",() -> new BookshelfBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)), 300);
	public static final RegistryObject<Block> YAGROOT_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "yagroot_bookshelf",() -> new BookshelfBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)), 300);
	public static final RegistryObject<Block> CRUDEROOT_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "cruderoot_bookshelf",() -> new BookshelfBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD)), 300);

	//HEDGES
	public static final RegistryObject<Block> ROSEROOT_HEDGE = HELPER.createCompatFuelBlock("quark", "roseroot_hedge",() -> new HedgeBlock(BlockBehaviour.Properties.copy(DABlocks.ROSEROOT_PLANKS.get())), 300);
	public static final RegistryObject<Block> YAGROOT_HEDGE = HELPER.createCompatFuelBlock("quark", "yagroot_hedge",() -> new HedgeBlock(BlockBehaviour.Properties.copy(DABlocks.YAGROOT_PLANKS.get())), 300);
	public static final RegistryObject<Block> CRUDEROOT_HEDGE = HELPER.createCompatFuelBlock("quark", "cruderoot_hedge",() -> new HedgeBlock(BlockBehaviour.Properties.copy(DABlocks.CRUDEROOT_PLANKS.get())), 300);

	//CHESTS
	public static final RegistryObject<Block> ROSEROOT_CHEST = HELPER.createCompatFuelBlock("quark", "roseroot_chest",() -> new BlueprintChestBlock("roseroot", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);
	public static final RegistryObject<Block> YAGROOT_CHEST = HELPER.createCompatFuelBlock("quark", "yagroot_chest",() -> new BlueprintChestBlock("yagroot", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);
	public static final RegistryObject<Block> CRUDEROOT_CHEST = HELPER.createCompatFuelBlock("quark", "cruderoot_chest", () -> new BlueprintChestBlock("cruderoot", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);

	//TRAPPED CHESTS
	public static final RegistryObject<Block> ROSEROOT_TRAPPED_CHEST = HELPER.createCompatFuelBlock("quark", "roseroot_trapped_chest",() -> new BlueprintTrappedChestBlock("roseroot", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);
	public static final RegistryObject<Block> YAGROOT_TRAPPED_CHEST = HELPER.createCompatFuelBlock("quark", "yagroot_trapped_chest",() -> new BlueprintTrappedChestBlock("yagroot", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);
	public static final RegistryObject<Block> CRUDEROOT_TRAPPED_CHEST = HELPER.createCompatFuelBlock("quark", "cruderoot_trapped_chest", () -> new BlueprintTrappedChestBlock("cruderoot", BlockBehaviour.Properties.copy(Blocks.CHEST)), 300);

	//VERTICAL PLANKS
	public static final RegistryObject<Block> VERTICAL_YAGROOT_PLANKS = HELPER.createCompatFuelBlock("quark", "vertical_yagroot_planks",() -> new Block(BlockBehaviour.Properties.copy(DABlocks.YAGROOT_PLANKS.get())), 300);
	public static final RegistryObject<Block> VERTICAL_CRUDEROOT_PLANKS = HELPER.createCompatFuelBlock("quark", "vertical_cruderoot_planks",() -> new Block(BlockBehaviour.Properties.copy(DABlocks.CRUDEROOT_PLANKS.get())), 300);
	public static final RegistryObject<Block> VERTICAL_PLANKS_PLANKS = HELPER.createCompatFuelBlock("quark", "vertical_roseroot_planks",() -> new Block(BlockBehaviour.Properties.copy(DABlocks.ROSEROOT_PLANKS.get())), 300);

	//VERTICAL SLABS
	public static final RegistryObject<Block> YAGROOT_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "yagroot_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.YAGROOT_PLANKS.get())), 150);
	public static final RegistryObject<Block> CRUDEROOT_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "cruderoot_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.CRUDEROOT_PLANKS.get())), 150);
	public static final RegistryObject<Block> ROSEROOT_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "roseroot_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.ROSEROOT_PLANKS.get())), 150);
	public static final RegistryObject<Block> ASETERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "aseterite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.ASETERITE.get())));
	public static final RegistryObject<Block> POLISHED_ASETERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_aseterite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_ASETERITE.get())));
	public static final RegistryObject<Block> JARINITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "jarinite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.JARINITE.get())));
	public static final RegistryObject<Block> POLISHED_JARINITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_jarinite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_JARINITE.get())));
	public static final RegistryObject<Block> DARKERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "darkerite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.DARKERITE.get())));
	public static final RegistryObject<Block> POLISHED_DARKERITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_darkerite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_DARKERITE.get())));
	public static final RegistryObject<Block> CLORITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "clorite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.CLORITE.get())));
	public static final RegistryObject<Block> POLISHED_CLORITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_clorite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_CLORITE.get())));
	public static final RegistryObject<Block> YALLESITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "yallesite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.YALLESITE.get())));
	public static final RegistryObject<Block> POLISHED_YALLESITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_yallesite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_YALLESITE.get())));
	public static final RegistryObject<Block> GREOTITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "greotite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.GREOTITE.get())));
	public static final RegistryObject<Block> POLISHED_GREOTITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_greotite_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_GREOTITE.get())));
	public static final RegistryObject<Block> HOLYSTONE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "holystone_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.HOLYSTONE_BRICKS.get())));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "aether_mud_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.AETHER_MUD_BRICKS.get())));

	//LADDERS
	public static final RegistryObject<Block> YAGROOT_LADDER = HELPER.createCompatBlock("quark", "yagroot_ladder",() -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)));
	public static final RegistryObject<Block> ROSEROOT_LADDER = HELPER.createCompatBlock("quark", "roseroot_ladder",() -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)));
	public static final RegistryObject<Block> CRUDEROOT_LADDER = HELPER.createCompatBlock("quark", "cruderoot_ladder",() -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER)));

	//LEAF CARPET
	public static final RegistryObject<Block> YAGROOT_LEAF_CARPET = HELPER.createCompatBlock("quark", "yagroot_leaf_carpet",() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> ROSEROOT_LEAF_CARPET = HELPER.createCompatBlock("quark", "roseroot_leaf_carpet",() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> CRUDEROOT_LEAF_CARPET = HELPER.createCompatBlock("quark", "cruderoot_leaf_carpet",() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

	//DIRT BRICKS
	public static final RegistryObject<Block> AETHER_DIRT_BRICKS = HELPER.createCompatBlock("quark", "aether_dirt_bricks",() -> new Block(BlockBehaviour.Properties.of(Material.DIRT).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AETHER_DIRT_BRICKS_SLAB = HELPER.createCompatBlock("quark", "aether_dirt_bricks_slab",() -> new SlabBlock(BlockBehaviour.Properties.of(Material.DIRT).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AETHER_DIRT_BRICKS_STAIRS = HELPER.createCompatBlock("quark", "aether_dirt_bricks_stairs",() -> new StairBlock(DABlocks.AETHER_DIRT_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.DIRT).strength(0.5F).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> AETHER_DIRT_BRICKS_WALL = HELPER.createCompatBlock("quark", "aether_dirt_bricks_wall",() -> new WallBlock(BlockBehaviour.Properties.of(Material.DIRT).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AETHER_DIRT_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "aether_dirt_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.of(Material.DIRT).requiresCorrectToolForDrops()));

	//STONE TYPES

	//ASETERITE
	public static final RegistryObject<Block> ASETERITE_BRICKS = HELPER.createCompatBlock("quark","aseterite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> ASETERITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","aseterite_bricks_stairs", () -> new StairBlock(() -> DABlocks.ASETERITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ASETERITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","aseterite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> ASETERITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "aseterite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> ASETERITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","aseterite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS = HELPER.createCompatBlock("quark","polished_aseterite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_aseterite_bricks_stairs", () -> new StairBlock(() -> DABlocks.POLISHED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_aseterite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_aseterite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_ASETERITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_aseterite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> ASETERITE_PILLAR = HELPER.createCompatBlock("quark","aseterite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_aseterite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_ASETERITE_BRICKS.get())));

	//CLORITE

	public static final RegistryObject<Block> CLORITE_BRICKS = HELPER.createCompatBlock("quark","clorite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> CLORITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","clorite_bricks_stairs", () -> new StairBlock(() -> DABlocks.CLORITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CLORITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","clorite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> CLORITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "clorite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> CLORITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","clorite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS = HELPER.createCompatBlock("quark","polished_clorite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_clorite_bricks_stairs", () -> new StairBlock(() -> DABlocks.POLISHED_CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_clorite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_clorite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_CLORITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_clorite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> CLORITE_PILLAR = HELPER.createCompatBlock("quark","clorite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_CLORITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_clorite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_CLORITE_BRICKS.get())));

	//DARKERITE
	public static final RegistryObject<Block> DARKERITE_BRICKS = HELPER.createCompatBlock("quark","darkerite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> DARKERITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","darkerite_bricks_stairs", () -> new StairBlock(() -> DABlocks.DARKERITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DARKERITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","darkerite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> DARKERITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "darkerite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> DARKERITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","darkerite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS = HELPER.createCompatBlock("quark","polished_darkerite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_darkerite_bricks_stairs", () -> new StairBlock(() -> DABlocks.POLISHED_DARKERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_darkerite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_darkerite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_DARKERITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_darkerite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> DARKERITE_PILLAR = HELPER.createCompatBlock("quark","darkerite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_DARKERITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_darkerite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_DARKERITE_BRICKS.get())));

	//YALLESITE
	public static final RegistryObject<Block> YALLESITE_BRICKS = HELPER.createCompatBlock("quark","yallesite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> YALLESITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","yallesite_bricks_stairs", () -> new StairBlock(() -> DABlocks.YALLESITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> YALLESITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","yallesite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> YALLESITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "yallesite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> YALLESITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","yallesite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS = HELPER.createCompatBlock("quark","polished_yallesite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_yallesite_bricks_stairs", () -> new StairBlock(() -> DABlocks.POLISHED_YALLESITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_yallesite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_yallesite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_YALLESITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_yallesite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> YALLESITE_PILLAR = HELPER.createCompatBlock("quark","yallesite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_YALLESITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_yallesite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_YALLESITE_BRICKS.get())));

	//JARINITE
	public static final RegistryObject<Block> JARINITE_BRICKS = HELPER.createCompatBlock("quark","jarinite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> JARINITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","jarinite_bricks_stairs", () -> new StairBlock(() -> DABlocks.JARINITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> JARINITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","jarinite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> JARINITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "jarinite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> JARINITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","jarinite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS = HELPER.createCompatBlock("quark","polished_jarinite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_jarinite_bricks_stairs", () -> new StairBlock(() -> DABlocks.POLISHED_JARINITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_jarinite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_jarinite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_JARINITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_jarinite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> JARINITE_PILLAR = HELPER.createCompatBlock("quark","jarinite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_JARINITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_jarinite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_JARINITE_BRICKS.get())));

	//GREOTITE
	public static final RegistryObject<Block> GREOTITE_BRICKS = HELPER.createCompatBlock("quark","greotite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> GREOTITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","greotite_bricks_stairs", () -> new StairBlock(() -> DABlocks.GREOTITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> GREOTITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","greotite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> GREOTITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "greotite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));
	public static final RegistryObject<Block> GREOTITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark","greotite_bricks_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS = HELPER.createCompatBlock("quark","polished_greotite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_STAIRS = HELPER.createCompatBlock("quark","polished_greotite_bricks_stairs", () -> new StairBlock(() -> DABlocks.POLISHED_GREOTITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_SLAB = HELPER.createCompatBlock("quark","polished_greotite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_WALL = HELPER.createCompatBlock("quark", "polished_greotite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE_WALL)));

	public static final RegistryObject<Block> CHISELED_GREOTITE_BRICKS = HELPER.createCompatBlock("quark","chiseled_greotite_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> GREOTITE_PILLAR = HELPER.createCompatBlock("quark","greotite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> POLISHED_GREOTITE_BRICKS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_greotite_bricks_vertical_slab",() -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(DABlocks.POLISHED_GREOTITE_BRICKS.get())));
*/
}


