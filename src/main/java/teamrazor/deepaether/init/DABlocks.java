

package teamrazor.deepaether.init;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.construction.AetherDirtPathBlock;
import com.aetherteam.aether.block.natural.AetherDoubleDropBlock;
import com.aetherteam.aether.block.natural.AetherDoubleDropsLeaves;
import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.mixin.mixins.common.accessor.FireBlockAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
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
	public static final RegistryObject<Block> GOLDEN_GRASS_BLOCK = registerBlock("golden_heights_grass_block", () -> new GoldenGrassBlock(Block.Properties.of().mapColor(MapColor.GOLD).randomTicks().strength(0.2F).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> GOLDEN_DIRT_PATH = registerBlock("golden_heights_dirt_path", () -> new AetherDirtPathBlock(Block.Properties.of().mapColor(MapColor.GOLD).strength(0.2F).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> AETHER_COARSE_DIRT = registerBlock("aether_coarse_dirt", () -> new AetherCoarseDirtBlock(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT)));

	public static final RegistryObject<Block> MINI_GOLDEN_GRASS = registerBlock("mini_golden_grass", ()-> new GoldenGrassPlant(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<Block> SHORT_GOLDEN_GRASS = registerBlock("short_golden_grass", ()-> new GoldenGrassPlant(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<Block> MEDIUM_GOLDEN_GRASS = registerBlock("medium_golden_grass", ()-> new GoldenGrassPlant(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<Block> TALL_GOLDEN_GRASS = registerBlock("tall_golden_grass", ()-> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));

	public static final RegistryObject<Block> FEATHER_GRASS = registerBlock("feather_grass", ()-> new FeatherGrassPlant(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<Block> TALL_FEATHER_GRASS = registerBlock("tall_feather_grass", ()-> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));


	// WOOD
	public static final RegistryObject<Block> ROSEROOT_WOOD = registerBlock("roseroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> ROSEROOT_LOG = registerBlock("roseroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> ROTTEN_ROSEROOT_LOG = registerBlock("rotten_roseroot_log", () -> new EmptyLog(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> ROSEROOT_WALL = registerBlock(300, "roseroot_wall", () ->  new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_ROSEROOT_WOOD = registerBlock("stripped_roseroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_ROSEROOT_LOG = registerBlock("stripped_roseroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_ROSEROOT_WALL = registerBlock(300, "stripped_roseroot_wall", () -> new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> ROSEROOT_PLANKS = registerBlock(300, "roseroot_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
	public static final RegistryObject<Block> ROSEROOT_STAIRS = registerBlock("roseroot_stairs", () -> new StairBlock(() -> DABlocks.ROSEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> ROSEROOT_SLAB = registerBlock("roseroot_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
	public static final RegistryObject<Block> ROSEROOT_FENCE = registerBlock("roseroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
	public static final RegistryObject<Block> ROSEROOT_FENCE_GATE = registerBlock("roseroot_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), DAWoodTypes.ROSEROOT));
	public static final RegistryObject<Block> ROSEROOT_PRESSURE_PLATE = registerBlock("roseroot_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.OAK_PRESSURE_PLATE), DAWoodTypes.ROSEROOT_BLOCK_SET));
	public static final RegistryObject<Block> ROSEROOT_BUTTON = registerBlock("roseroot_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), DAWoodTypes.ROSEROOT_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> ROSEROOT_DOOR = registerBlock("roseroot_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), DAWoodTypes.ROSEROOT_BLOCK_SET));
	public static final RegistryObject<Block> ROSEROOT_TRAPDOOR = registerBlock("roseroot_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), DAWoodTypes.ROSEROOT_BLOCK_SET));
	public static final RegistryObject<Block> ROSEROOT_LEAVES = registerBlock("roseroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> FLOWERING_ROSEROOT_LEAVES = registerBlock("flowering_roseroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn(DABlocks::ocelotOrParrot).isSuffocating(DABlocks::never).isViewBlocking(DABlocks::never).lightLevel(s -> 5)));
	public static final RegistryObject<Block> ROSEROOT_SAPLING = registerBlock("roseroot_sapling", () -> new SaplingBlock( new RoserootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Block> BLUE_ROSEROOT_LEAVES = registerBlock("blue_roseroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> FLOWERING_BLUE_ROSEROOT_LEAVES = registerBlock("flowering_blue_roseroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(DABlocks.FLOWERING_ROSEROOT_LEAVES.get())));
	public static final RegistryObject<Block> BLUE_ROSEROOT_SAPLING = registerBlock("blue_roseroot_sapling", () -> new SaplingBlock( new BlueRoserootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Block> ROSEROOT_WALL_SIGN = BLOCKS.register("roseroot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DAWoodTypes.ROSEROOT));
	public static final RegistryObject<Block> ROSEROOT_SIGN = BLOCKS.register("roseroot_sign", () -> new DASignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DAWoodTypes.ROSEROOT));

	public static final RegistryObject<CeilingHangingSignBlock> ROSEROOT_HANGING_SIGN = BLOCKS.register("roseroot_hanging_sign", () -> new DACeilingHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), DAWoodTypes.ROSEROOT));
	public static final RegistryObject<Block> ROSEROOT_WALL_HANGING_SIGN = BLOCKS.register("roseroot_wall_hanging_sign", () -> new DAWallHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), DAWoodTypes.ROSEROOT));
	public static final RegistryObject<Block> LIGHTCAP_MUSHROOMS = registerBlock("lightcap_mushrooms", () -> new LightCapMushroomBlock(Block.Properties.copy(Blocks.BROWN_MUSHROOM), null));



	//YAGROOT
	public static final RegistryObject<Block> YAGROOT_WOOD = registerBlock("yagroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> YAGROOT_LOG = registerBlock("yagroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> YAGROOT_WALL = registerBlock(300,"yagroot_wall", () ->  new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_WOOD = registerBlock("stripped_yagroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_LOG = registerBlock("stripped_yagroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_YAGROOT_WALL = registerBlock(300,"stripped_yagroot_wall", () -> new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> YAGROOT_PLANKS = registerBlock(300, "yagroot_planks", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> YAGROOT_STAIRS = registerBlock("yagroot_stairs", () -> new StairBlock(() -> DABlocks.ROSEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> YAGROOT_SLAB = registerBlock("yagroot_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
	public static final RegistryObject<Block> YAGROOT_FENCE = registerBlock("yagroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2f, 3f)));
	public static final RegistryObject<Block> YAGROOT_FENCE_GATE = registerBlock("yagroot_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), DAWoodTypes.YAGROOT));
	public static final RegistryObject<Block> YAGROOT_PRESSURE_PLATE = registerBlock("yagroot_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.OAK_PRESSURE_PLATE), DAWoodTypes.YAGROOT_BLOCK_SET));
	public static final RegistryObject<Block> YAGROOT_BUTTON = registerBlock("yagroot_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), DAWoodTypes.YAGROOT_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> YAGROOT_DOOR = registerBlock("yagroot_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), DAWoodTypes.YAGROOT_BLOCK_SET));
	public static final RegistryObject<Block> YAGROOT_TRAPDOOR = registerBlock("yagroot_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), DAWoodTypes.YAGROOT_BLOCK_SET));
	public static final RegistryObject<Block> YAGROOT_LEAVES = registerBlock("yagroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> YAGROOT_SAPLING = registerBlock("yagroot_sapling", () -> new SaplingBlock( new YagrootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.MANGROVE_PROPAGULE)));
	public static final RegistryObject<Block> YAGROOT_ROOTS = registerBlock(300, "yagroot_roots", () -> new DADoubleDropRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(0.7F).randomTicks().sound(SoundType.MANGROVE_ROOTS).noOcclusion()));
	public static final RegistryObject<Block> MUDDY_YAGROOT_ROOTS = registerBlock("muddy_yagroot_roots", () -> new DADoubleDropRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(0.7F).sound(SoundType.MUDDY_MANGROVE_ROOTS)));

	public static final RegistryObject<Block> YAGROOT_WALL_SIGN = BLOCKS.register("yagroot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DAWoodTypes.YAGROOT));
	public static final RegistryObject<Block> YAGROOT_SIGN = BLOCKS.register("yagroot_sign", () -> new DASignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DAWoodTypes.YAGROOT));

	public static final RegistryObject<CeilingHangingSignBlock> YAGROOT_HANGING_SIGN = BLOCKS.register("yagroot_hanging_sign", () -> new DACeilingHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), DAWoodTypes.YAGROOT));
	public static final RegistryObject<Block> YAGROOT_WALL_HANGING_SIGN = BLOCKS.register("yagroot_wall_hanging_sign", () -> new DAWallHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), DAWoodTypes.YAGROOT));

	//CRUDEROOOT
	public static final RegistryObject<Block> CRUDEROOT_WOOD = registerBlock("cruderoot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> CRUDEROOT_LOG = registerBlock("cruderoot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> CRUDEROOT_WALL = registerBlock(300,"cruderoot_wall", () ->  new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_WOOD = registerBlock("stripped_cruderoot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_LOG = registerBlock("stripped_cruderoot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_CRUDEROOT_WALL = registerBlock(300,"stripped_cruderoot_wall", () -> new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> CRUDEROOT_PLANKS = registerBlock(300, "cruderoot_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
	public static final RegistryObject<Block> CRUDEROOT_STAIRS = registerBlock("cruderoot_stairs", () -> new StairBlock(() -> DABlocks.CRUDEROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> CRUDEROOT_SLAB = registerBlock("cruderoot_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
	public static final RegistryObject<Block> CRUDEROOT_FENCE = registerBlock("cruderoot_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
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

	public static final RegistryObject<CeilingHangingSignBlock> CRUDEROOT_HANGING_SIGN = BLOCKS.register("cruderoot_hanging_sign", () -> new DACeilingHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), DAWoodTypes.CRUDEROOT));
	public static final RegistryObject<Block> CRUDEROOT_WALL_HANGING_SIGN = BLOCKS.register("cruderoot_wall_hanging_sign", () -> new DAWallHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), DAWoodTypes.CRUDEROOT));

	//CONBERRY

	public static final RegistryObject<Block> CONBERRY_WOOD = registerBlock("conberry_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> CONBERRY_LOG = registerBlock("conberry_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> CONBERRY_WALL = registerBlock(300,"conberry_wall", () ->  new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_CONBERRY_WOOD = registerBlock("stripped_conberry_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_CONBERRY_LOG = registerBlock("stripped_conberry_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_CONBERRY_WALL = registerBlock(300,"stripped_conberry_wall", () -> new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> CONBERRY_PLANKS = registerBlock( 300,"conberry_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
	public static final RegistryObject<Block> CONBERRY_STAIRS = registerBlock("conberry_stairs", () -> new StairBlock(() -> DABlocks.CONBERRY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> CONBERRY_SLAB = registerBlock("conberry_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
	public static final RegistryObject<Block> CONBERRY_FENCE = registerBlock("conberry_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
	public static final RegistryObject<Block> CONBERRY_FENCE_GATE = registerBlock("conberry_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), DAWoodTypes.CONBERRY));
	public static final RegistryObject<Block> CONBERRY_PRESSURE_PLATE = registerBlock("conberry_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.OAK_PRESSURE_PLATE), DAWoodTypes.CONBERRY_BLOCK_SET));
	public static final RegistryObject<Block> CONBERRY_BUTTON = registerBlock("conberry_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), DAWoodTypes.CONBERRY_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> CONBERRY_DOOR = registerBlock("conberry_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), DAWoodTypes.CONBERRY_BLOCK_SET));
	public static final RegistryObject<Block> CONBERRY_TRAPDOOR = registerBlock("conberry_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), DAWoodTypes.CONBERRY_BLOCK_SET));
	public static final RegistryObject<Block> CONBERRY_LEAVES = registerBlock("conberry_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> CONBERRY_SAPLING = registerBlock("conberry_sapling", () -> new SaplingBlock( new ConberryTreeGrower(), BlockBehaviour.Properties.copy(Blocks.MANGROVE_PROPAGULE)));
	public static final RegistryObject<Block> CONBERRY_WALL_SIGN = BLOCKS.register("conberry_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DAWoodTypes.CONBERRY));
	public static final RegistryObject<Block> CONBERRY_SIGN = BLOCKS.register("conberry_sign", () -> new DASignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DAWoodTypes.CONBERRY));

	public static final RegistryObject<CeilingHangingSignBlock> CONBERRY_HANGING_SIGN = BLOCKS.register("conberry_hanging_sign", () -> new DACeilingHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), DAWoodTypes.CONBERRY));
	public static final RegistryObject<Block> CONBERRY_WALL_HANGING_SIGN = BLOCKS.register("conberry_wall_hanging_sign", () -> new DAWallHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), DAWoodTypes.CONBERRY));
	//SUNROOT

	public static final RegistryObject<Block> SUNROOT_WOOD = registerBlock("sunroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> SUNROOT_LOG = registerBlock("sunroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> SUNROOT_WALL = registerBlock(300,"sunroot_wall", () ->  new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> STRIPPED_SUNROOT_WOOD = registerBlock("stripped_sunroot_wood", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_SUNROOT_LOG = registerBlock("stripped_sunroot_log", () -> new DALogBlock(Block.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_SUNROOT_WALL = registerBlock(300,"stripped_sunroot_wall", () -> new DAWallBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> SUNROOT_PLANKS = registerBlock( 300,"sunroot_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
	public static final RegistryObject<Block> SUNROOT_STAIRS = registerBlock("sunroot_stairs", () -> new StairBlock(() -> DABlocks.SUNROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> SUNROOT_SLAB = registerBlock("sunroot_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
	public static final RegistryObject<Block> SUNROOT_FENCE = registerBlock("sunroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
	public static final RegistryObject<Block> SUNROOT_FENCE_GATE = registerBlock("sunroot_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE), DAWoodTypes.SUNROOT));
	public static final RegistryObject<Block> SUNROOT_PRESSURE_PLATE = registerBlock("sunroot_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.OAK_PRESSURE_PLATE), DAWoodTypes.SUNROOT_BLOCK_SET));
	public static final RegistryObject<Block> SUNROOT_BUTTON = registerBlock("sunroot_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), DAWoodTypes.SUNROOT_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> SUNROOT_DOOR = registerBlock("sunroot_door", () ->  new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR), DAWoodTypes.SUNROOT_BLOCK_SET));
	public static final RegistryObject<Block> SUNROOT_TRAPDOOR = registerBlock("sunroot_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR), DAWoodTypes.SUNROOT_BLOCK_SET));
	public static final RegistryObject<Block> SUNROOT_LEAVES = registerBlock("sunroot_leaves", () -> new AetherDoubleDropsLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> SUNROOT_SAPLING = registerBlock("sunroot_sapling", () -> new SaplingBlock( new SunrootTreeGrower(), BlockBehaviour.Properties.copy(Blocks.MANGROVE_PROPAGULE)));
	public static final RegistryObject<Block> SUNROOT_WALL_SIGN = BLOCKS.register("sunroot_wall_sign", () -> new DAWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), DAWoodTypes.SUNROOT));
	public static final RegistryObject<Block> SUNROOT_SIGN = BLOCKS.register("sunroot_sign", () -> new DASignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), DAWoodTypes.SUNROOT));

	public static final RegistryObject<CeilingHangingSignBlock> SUNROOT_HANGING_SIGN = BLOCKS.register("sunroot_hanging_sign", () -> new DACeilingHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), DAWoodTypes.SUNROOT));
	public static final RegistryObject<Block> SUNROOT_WALL_HANGING_SIGN = BLOCKS.register("sunroot_wall_hanging_sign", () -> new DAWallHangingSignBlock(
			BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), DAWoodTypes.SUNROOT));

	// ORES
	public static final RegistryObject<Block> SKYJADE_ORE = registerBlock("skyjade_ore", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final RegistryObject<Block> SKYJADE_BLOCK = registerBlock("skyjade_block", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final RegistryObject<Block> STRATUS_BLOCK = registerBlock("stratus_block", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(10f, 10f)));

	//STONE TYPES
	public static final RegistryObject<Block> ASETERITE = registerBlock("aseterite", () -> new AetherDoubleDropBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE = registerBlock("polished_aseterite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_STAIRS = registerBlock("polished_aseterite_stairs", () -> new StairBlock(() -> DABlocks.POLISHED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));
	public static final RegistryObject<Block> ASETERITE_STAIRS = registerBlock("aseterite_stairs", () -> new StairBlock(() -> DABlocks.ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
	public static final RegistryObject<Block> ASETERITE_SLAB = registerBlock("aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_ASETERITE_SLAB = registerBlock("polished_aseterite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> ASETERITE_WALL = registerBlock("aseterite_wall", () -> new WallBlock(BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> RAW_CLORITE = registerBlock("raw_clorite", () -> new AetherDoubleDropBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

	public static final RegistryObject<Block> CLORITE = registerBlock("clorite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE = registerBlock("polished_clorite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> RAW_CLORITE_SLAB = registerBlock("raw_clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

	public static final RegistryObject<Block> CLORITE_SLAB = registerBlock("clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> POLISHED_CLORITE_SLAB = registerBlock("polished_clorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
	public static final RegistryObject<Block> RAW_CLORITE_STAIRS = registerBlock("raw_clorite_stairs", () -> new StairBlock(() -> DABlocks.CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CLORITE_STAIRS = registerBlock("clorite_stairs", () -> new StairBlock(() -> DABlocks.CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_CLORITE_STAIRS = registerBlock("polished_clorite_stairs", () -> new StairBlock(() -> DABlocks.POLISHED_CLORITE.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CLORITE_WALL = registerBlock("clorite_wall", () -> new WallBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> RAW_CLORITE_WALL = registerBlock("raw_clorite_wall", () -> new WallBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CLORITE_PILLAR = registerBlock("clorite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> HOLYSTONE_TILES = registerBlock("holystone_tiles", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MOSSY_HOLYSTONE_TILES = registerBlock("mossy_holystone_tiles", () -> new Block(BlockBehaviour.Properties.copy(MOSSY_STONE_BRICKS)));

	public static final RegistryObject<Block> HOLYSTONE_TILE_STAIRS = registerBlock("holystone_tile_stairs", () -> new StairBlock(DABlocks.HOLYSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MOSSY_HOLYSTONE_TILE_STAIRS = registerBlock("mossy_holystone_tile_stairs", () -> new StairBlock(DABlocks.MOSSY_HOLYSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_STONE_BRICKS)));

	public static final RegistryObject<Block> HOLYSTONE_TILE_SLAB = registerBlock("holystone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MOSSY_HOLYSTONE_TILE_SLAB = registerBlock("mossy_holystone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_STONE_BRICKS)));

	public static final RegistryObject<Block> HOLYSTONE_TILE_WALL = registerBlock("holystone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MOSSY_HOLYSTONE_TILE_WALL = registerBlock("mossy_holystone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_STONE_BRICKS)));

	public static final RegistryObject<Block> BIG_HOLYSTONE_BRICKS = registerBlock("big_holystone_bricks", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CHISELED_HOLYSTONE = registerBlock("chiseled_holystone", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> HOLYSTONE_PILLAR = registerBlock("holystone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> HOLYSTONE_PILLAR_UP = registerBlock("holystone_pillar_up", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> HOLYSTONE_PILLAR_DOWN = registerBlock("holystone_pillar_down", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(1f, 10f).requiresCorrectToolForDrops()));


	//AERCLOUDS
	public static final RegistryObject<Block> AERSMOG = registerBlock("aersmog", () -> new AersmogBlock((Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.3F).sound(SoundType.WOOL).noOcclusion().dynamicShape().isRedstoneConductor(DABlocks::never).isSuffocating(DABlocks::never).isViewBlocking(DABlocks::never))));
	public static final RegistryObject<Block> CHROMATIC_AERCLOUD = registerBlock("chromatic_aercloud", () -> new ChromaticAercloudBlock(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.3F).sound(SoundType.WOOL).dynamicShape().isRedstoneConductor(DABlocks::never).isSuffocating(DABlocks::never).isViewBlocking(DABlocks::never).noCollission()));
	public static final RegistryObject<Block> STERLING_AERCLOUD = registerBlock("sterling_aercloud", () -> new SterlingAercloudBlock(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.3F).sound(SoundType.WOOL).noCollission().dynamicShape().isRedstoneConductor(DABlocks::never).isSuffocating(DABlocks::never).isViewBlocking(DABlocks::never)));
	public static final RegistryObject<Block> RAIN_AERCLOUD = registerBlock("rain_aercloud", () -> new RainAercloudBlock(BlockBehaviour.Properties.copy(AetherBlocks.COLD_AERCLOUD.get())));

	//MISC
	public static final RegistryObject<Block> RADIANT_ORCHID = registerBlock("radiant_orchid", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.of().noCollission().sound(SoundType.GRASS).instabreak().lightLevel(s -> 5)));
	public static final RegistryObject<Block> AERLAVENDER = registerBlock("aerlavender", () ->  new FlowerBlockLargeHitBox(MobEffects.JUMP, 6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> TALL_AERLAVENDER = registerBlock("tall_aerlavender", () ->  new FlowerBlockLargeHitBox(MobEffects.JUMP, 6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> AETHER_CATTAILS = registerBlock("aether_cattails", () ->  new FlowerBlock(AetherEffects.INEBRIATION, 6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> TALL_AETHER_CATTAILS = registerBlock("tall_aether_cattails", () ->  new TallFlowerBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> GOLDEN_FLOWER = registerBlock("golden_flower", () ->  new FlowerBlockLargeHitBox(MobEffects.GLOWING,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> ENCHANTED_BLOSSOM = registerBlock("enchanted_blossom", () ->  new FlowerBlock(MobEffects.GLOWING,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> SKY_TULIPS = registerBlock("sky_tulips", () ->  new FlowerBlock(MobEffects.HEALTH_BOOST,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> IASPOVE = registerBlock("iaspove", () ->  new FlowerBlock(MobEffects.MOVEMENT_SPEED,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> GOLDEN_ASPESS = registerBlock("golden_aspess", () ->  new FlowerBlockLargeHitBox(MobEffects.GLOWING,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> ECHAISY = registerBlock("echaisy", () ->  new FlowerBlock(MobEffects.DAMAGE_RESISTANCE,6, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));


	public static final RegistryObject<Block> AERGLOW_BLOSSOM_BLOCK = registerBlock("aerglow_blossom_block", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.MOSS).strength(1f, 10f).lightLevel(s -> 9)));
	public static final RegistryObject<Block> AETHER_MOSS_CARPET = registerBlock("aether_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET)));
	public static final RegistryObject<Block> AETHER_MOSS_BLOCK = registerBlock("aether_moss_block", () -> new DAMossBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)));

	public static final RegistryObject<Block> VIRULENT_QUICKSAND = registerBlock("virulent_quicksand", () -> new VirulentQuicksandBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));

	//MUD
	public static final RegistryObject<Block> AETHER_MUD = registerBlock("aether_mud", () -> new DoubleDropMudBlock(BlockBehaviour.Properties.copy(Blocks.MUD)));
	public static final RegistryObject<Block> PACKED_AETHER_MUD = registerBlock("packed_aether_mud", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PACKED_MUD)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS = registerBlock("aether_mud_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_SLAB = registerBlock("aether_mud_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICK_SLAB)));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_STAIRS = registerBlock("aether_mud_bricks_stairs", () -> new StairBlock(() -> DABlocks.AETHER_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.MUD_BRICKS).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AETHER_MUD_BRICKS_WALL = registerBlock("aether_mud_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.MUD_BRICKS).strength(2.0F, 6.0F).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block>  GOLDEN_VINES = BLOCKS.register("golden_vines", () -> new GoldenVinesBlock(BlockBehaviour.Properties.of().randomTicks().noCollission().lightLevel(GoldenVines.emission(1)).instabreak().sound(SoundType.CAVE_VINES)));
	public static final RegistryObject<Block>  GOLDEN_VINES_PLANT = BLOCKS.register("golden_vines_plant", () -> new GoldenVinesPlantBlock(BlockBehaviour.Properties.of().noCollission().lightLevel(GoldenVines.emission(1)).instabreak().sound(SoundType.CAVE_VINES)));

	public static final RegistryObject<LiquidBlock> POISON_BLOCK = BLOCKS.register("poison", () -> new PoisonBlock(DAFluids.POISON_FLUID, BlockBehaviour.Properties.of()
			.noCollission()
			.replaceable()
			.strength(100f)
			.noLootTable()));
	public static final RegistryObject<Block> POISON_CAULDRON = BLOCKS.register("poison_cauldron", () -> new PoisonCauldronBlock(BlockBehaviour.Properties.copy(CAULDRON)));



	//POTS
	public static final RegistryObject<FlowerPotBlock> POTTED_AERLAVENDER = BLOCKS.register("potted_aerlavender", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AERLAVENDER, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_TALL_AERLAVENDER = BLOCKS.register("potted_tall_aerlavender", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, TALL_AERLAVENDER, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_AETHER_CATTAILS = BLOCKS.register("potted_aether_cattails", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AETHER_CATTAILS, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_RADIANT_ORCHID = BLOCKS.register("potted_radiant_orchid", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RADIANT_ORCHID, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_ENCHANTED_BLOSSOM = BLOCKS.register("potted_enchanted_blossom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ENCHANTED_BLOSSOM, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_SKY_TULIPS = BLOCKS.register("potted_sky_tulips", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SKY_TULIPS, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_IASPOVE = BLOCKS.register("potted_iaspove", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, IASPOVE, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_GOLDEN_ASPESS = BLOCKS.register("potted_golden_aspess", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GOLDEN_ASPESS, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_ECHAISY = BLOCKS.register("potted_echaisy", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ECHAISY, Block.Properties.copy(Blocks.FLOWER_POT)));


	public static final RegistryObject<FlowerPotBlock> POTTED_ROSEROOT_SAPLING = BLOCKS.register("potted_roseroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ROSEROOT_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_BLUE_ROSEROOT_SAPLING = BLOCKS.register("potted_blue_roseroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_ROSEROOT_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_YAGROOT_SAPLING = BLOCKS.register("potted_yagroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, TALL_AERLAVENDER, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_CRUDEROOT_SAPLING = BLOCKS.register("potted_cruderoot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CRUDEROOT_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_CONBERRY_SAPLING = BLOCKS.register("potted_conberry_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CONBERRY_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));

	public static final RegistryObject<FlowerPotBlock> POTTED_SUNROOT_SAPLING = BLOCKS.register("potted_sunroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SUNROOT_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));

	//REDUX COMPATIBILITY

	public static final RegistryObject<Block> GILDED_HOLYSTONE_TILES = registerAetherReduxBlock("gilded_holystone_tiles", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> GILDED_HOLYSTONE_TILE_STAIRS = registerAetherReduxBlock("gilded_holystone_tile_stairs", () -> new StairBlock(DABlocks.GILDED_HOLYSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> GILDED_HOLYSTONE_TILE_SLAB = registerAetherReduxBlock("gilded_holystone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> GILDED_HOLYSTONE_TILE_WALL = registerAetherReduxBlock("gilded_holystone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> BLIGHTMOSS_HOLYSTONE_TILES = registerAetherReduxBlock("blightmoss_holystone_tiles", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> BLIGHTMOSS_HOLYSTONE_TILE_STAIRS = registerAetherReduxBlock("blightmoss_holystone_tile_stairs", () -> new StairBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> BLIGHTMOSS_HOLYSTONE_TILE_SLAB = registerAetherReduxBlock("blightmoss_holystone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> BLIGHTMOSS_HOLYSTONE_TILE_WALL = registerAetherReduxBlock("blightmoss_holystone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1f, 10f).requiresCorrectToolForDrops()));


	//GENESIS COMPATIBILITY

	public static final RegistryObject<WallBlock> ROSEROOT_LOG_WALL = registerAetherGenesisBlock("roseroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallBlock> STRIPPED_ROSEROOT_LOG_WALL = registerAetherGenesisBlock("stripped_roseroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallBlock> CRUDEROOT_LOG_WALL = registerAetherGenesisBlock("cruderoot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallBlock> STRIPPED_CRUDEROOT_LOG_WALL = registerAetherGenesisBlock("stripped_cruderoot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallBlock> YAGROOT_LOG_WALL = registerAetherGenesisBlock("yagroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallBlock> STRIPPED_YAGROOT_LOG_WALL = registerAetherGenesisBlock("stripped_yagroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallBlock> CONBERRY_LOG_WALL = registerAetherGenesisBlock("conberry_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallBlock> STRIPPED_CONBERRY_LOG_WALL = registerAetherGenesisBlock("stripped_conberry_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallBlock> SUNROOT_LOG_WALL = registerAetherGenesisBlock("sunroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallBlock> STRIPPED_SUNROOT_LOG_WALL = registerAetherGenesisBlock("stripped_sunroot_log_wall", () -> new DAWallBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));



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


	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		return DAItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItemDisabled(String name, RegistryObject<T> block) {
		return DAItems.ITEMS.register(name, () -> new DisabledBlockItem(block.get(), new Item.Properties()));
	}
	private static <T extends Block> RegistryObject<T> registerAetherReduxBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);

		if(ModList.get().isLoaded(DeepAetherMod.AETHER_REDUX)) {
			DeepAetherMod.LOGGER.info("Deep Aether: Registering Aether Redux compat blocks");
			registerBlockItem(name, toReturn);

		}
		else
			registerBlockItemDisabled(name, toReturn);

		return toReturn;

	}

	private static <T extends Block> RegistryObject<T> registerAetherGenesisBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);


		if(ModList.get().isLoaded(DeepAetherMod.AETHER_GENESIS)) {
			DeepAetherMod.LOGGER.info("Deep Aether: Registering Aether Genesis compat blocks");
			registerBlockItem(name, toReturn);
		}
		else
			registerBlockItemDisabled(name, toReturn);


		return  toReturn;
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
	private static boolean ocelotOrParrot(BlockState p_235441_0_, BlockGetter p_235441_1_, BlockPos p_235441_2_, EntityType<?> p_235441_3_) {
		return p_235441_3_ == EntityType.OCELOT || p_235441_3_ == EntityType.PARROT;
	}
}


