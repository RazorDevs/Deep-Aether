package io.github.razordevs.deep_aether.datagen.loot;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.providers.AetherBlockLootSubProvider;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.loot.functions.DoubleDrops;
import com.aetherteam.aether.mixin.mixins.common.accessor.BlockLootAccessor;
import io.github.razordevs.deep_aether.block.behavior.GoldenVines;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DABlockLoot extends AetherBlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(AetherBlocks.TREASURE_CHEST.get()).map(ItemLike::asItem).collect(Collectors.toSet());

    public DABlockLoot(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return DABlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
    }

    @Override
    public void generate() {

        this.dropSelf(DABlocks.HIGHSTONE.get());
        this.dropOther(DABlocks.TRAPPED_SKYROOT_PLANKS.get(), AetherBlocks.SKYROOT_PLANKS.get());
        this.dropOther(DABlocks.LOCKED_SKYROOT_PLANKS.get(), AetherBlocks.SKYROOT_PLANKS.get());

        //Roseroot woodset
        this.dropSelf(DABlocks.ROSEROOT_WOOD.get());
        this.dropSelf(DABlocks.ROTTEN_ROSEROOT_LOG.get());
        this.dropSelf(DABlocks.STRIPPED_ROSEROOT_WOOD.get());
        this.dropSelfDouble(DABlocks.ROSEROOT_LOG.get());
        this.dropSelf(DABlocks.STRIPPED_ROSEROOT_LOG.get());
        this.dropSelf(DABlocks.ROSEROOT_PLANKS.get());
        this.add(DABlocks.ROSEROOT_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.ROSEROOT_STAIRS.get());
        this.dropSelf(DABlocks.ROSEROOT_FENCE.get());
        this.dropSelf(DABlocks.ROSEROOT_FENCE_GATE.get());
        this.add(DABlocks.ROSEROOT_DOOR.get(), createDoorTable(DABlocks.ROSEROOT_DOOR.get()));
        this.dropSelf(DABlocks.ROSEROOT_TRAPDOOR.get());
        this.dropSelf(DABlocks.ROSEROOT_BUTTON.get());
        this.dropSelf(DABlocks.ROSEROOT_PRESSURE_PLATE.get());
        this.dropSelf(DABlocks.ROSEROOT_WALL.get());
        this.dropSelf(DABlocks.STRIPPED_ROSEROOT_WALL.get());
        this.dropSelf(DABlocks.ROSEROOT_SAPLING.get());
        this.dropPottedContents(DABlocks.POTTED_ROSEROOT_SAPLING.get());
        this.dropSelf(DABlocks.BLUE_ROSEROOT_SAPLING.get());
        this.dropPottedContents(DABlocks.POTTED_BLUE_ROSEROOT_SAPLING.get());
        this.add(DABlocks.ROSEROOT_LEAVES.get(), (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.ROSEROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.add(DABlocks.FLOWERING_ROSEROOT_LEAVES.get(), (leaves) -> droppingWithChancesAndSkyrootSticksAndAerglowPetal(leaves, DABlocks.ROSEROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.add(DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(), (leaves) -> droppingWithChancesAndSkyrootSticksAndAerglowPetal(leaves, DABlocks.BLUE_ROSEROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.dropOther(DABlocks.ROSEROOT_WALL_SIGN.get(), DABlocks.ROSEROOT_SIGN.get());
        this.dropSelf(DABlocks.ROSEROOT_SIGN.get());
        this.dropOther(DABlocks.ROSEROOT_WALL_HANGING_SIGN.get(), DABlocks.ROSEROOT_HANGING_SIGN.get());
        this.dropSelf(DABlocks.ROSEROOT_HANGING_SIGN.get());
        this.add(DABlocks.BLUE_ROSEROOT_LEAVES.get(), (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.BLUE_ROSEROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.dropSelf(DABlocks.AERGLOW_BLOSSOM_BLOCK.get());

        //Yagroot woodset
        this.dropSelf(DABlocks.YAGROOT_WOOD.get());
        this.dropSelf(DABlocks.STRIPPED_YAGROOT_WOOD.get());
        this.dropSelfDouble(DABlocks.YAGROOT_LOG.get());
        this.dropSelf(DABlocks.STRIPPED_YAGROOT_LOG.get());
        this.dropSelf(DABlocks.YAGROOT_PLANKS.get());
        this.add(DABlocks.YAGROOT_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.YAGROOT_STAIRS.get());
        this.dropSelf(DABlocks.YAGROOT_FENCE.get());
        this.dropSelf(DABlocks.YAGROOT_FENCE_GATE.get());
        this.add(DABlocks.YAGROOT_DOOR.get(), createDoorTable(DABlocks.YAGROOT_DOOR.get()));
        this.dropSelf(DABlocks.YAGROOT_TRAPDOOR.get());
        this.dropSelf(DABlocks.YAGROOT_BUTTON.get());
        this.dropSelf(DABlocks.YAGROOT_PRESSURE_PLATE.get());
        this.dropSelf(DABlocks.YAGROOT_WALL.get());
        this.dropSelf(DABlocks.STRIPPED_YAGROOT_WALL.get());
        this.dropSelf(DABlocks.YAGROOT_SAPLING.get());
        this.dropPottedContents(DABlocks.POTTED_YAGROOT_SAPLING.get());
        this.add(DABlocks.YAGROOT_LEAVES.get(), (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.YAGROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.dropOther(DABlocks.YAGROOT_WALL_SIGN.get(), DABlocks.YAGROOT_SIGN.get());
        this.dropSelf(DABlocks.YAGROOT_SIGN.get());
        this.dropOther(DABlocks.YAGROOT_WALL_HANGING_SIGN.get(), DABlocks.YAGROOT_HANGING_SIGN.get());
        this.dropSelf(DABlocks.YAGROOT_HANGING_SIGN.get());
        this.dropSelfDouble(DABlocks.YAGROOT_ROOTS.get());
        this.dropSelfDouble(DABlocks.AERCLOUD_ROOTS.get());
        this.dropSelf(DABlocks.MUDDY_YAGROOT_ROOTS.get());
        this.add(DABlocks.YAGROOT_VINE.get(), (vine) -> createVinesDrop(DABlocks.YAGROOT_VINE.get()));
        this.add(DABlocks.GLOWING_VINE.get(), (vine) -> createVinesDrop(Blocks.VINE));

        //Cruderoot woodset
        this.dropSelf(DABlocks.CRUDEROOT_WOOD.get());
        this.dropSelf(DABlocks.STRIPPED_CRUDEROOT_WOOD.get());
        this.dropSelfDouble(DABlocks.CRUDEROOT_LOG.get());
        this.dropSelf(DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        this.dropSelf(DABlocks.CRUDEROOT_PLANKS.get());
        this.add(DABlocks.CRUDEROOT_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.CRUDEROOT_STAIRS.get());
        this.dropSelf(DABlocks.CRUDEROOT_FENCE.get());
        this.dropSelf(DABlocks.CRUDEROOT_FENCE_GATE.get());
        this.add(DABlocks.CRUDEROOT_DOOR.get(), createDoorTable(DABlocks.CRUDEROOT_DOOR.get()));
        this.dropSelf(DABlocks.CRUDEROOT_TRAPDOOR.get());
        this.dropSelf(DABlocks.CRUDEROOT_BUTTON.get());
        this.dropSelf(DABlocks.CRUDEROOT_PRESSURE_PLATE.get());
        this.dropSelf(DABlocks.CRUDEROOT_WALL.get());
        this.dropSelf(DABlocks.STRIPPED_CRUDEROOT_WALL.get());
        this.dropSelf(DABlocks.CRUDEROOT_SAPLING.get());
        this.dropPottedContents(DABlocks.POTTED_CRUDEROOT_SAPLING.get());
        this.add(DABlocks.CRUDEROOT_LEAVES.get(), (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.CRUDEROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.dropOther(DABlocks.CRUDEROOT_WALL_SIGN.get(), DABlocks.CRUDEROOT_SIGN.get());
        this.dropSelf(DABlocks.CRUDEROOT_SIGN.get());
        this.dropOther(DABlocks.CRUDEROOT_WALL_HANGING_SIGN.get(), DABlocks.CRUDEROOT_HANGING_SIGN.get());
        this.dropSelf(DABlocks.CRUDEROOT_HANGING_SIGN.get());

        //Conberry woodset
        this.dropSelf(DABlocks.CONBERRY_WOOD.get());
        this.dropSelf(DABlocks.STRIPPED_CONBERRY_WOOD.get());
        this.dropSelfDouble(DABlocks.CONBERRY_LOG.get());
        this.dropSelfDouble(DABlocks.STRIPPED_CONBERRY_LOG.get());
        this.dropSelf(DABlocks.CONBERRY_PLANKS.get());
        this.add(DABlocks.CONBERRY_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.CONBERRY_STAIRS.get());
        this.dropSelf(DABlocks.CONBERRY_FENCE.get());
        this.dropSelf(DABlocks.CONBERRY_FENCE_GATE.get());
        this.add(DABlocks.CONBERRY_DOOR.get(), createDoorTable(DABlocks.CONBERRY_DOOR.get()));
        this.dropSelf(DABlocks.CONBERRY_TRAPDOOR.get());
        this.dropSelf(DABlocks.CONBERRY_BUTTON.get());
        this.dropSelf(DABlocks.CONBERRY_PRESSURE_PLATE.get());
        this.dropSelf(DABlocks.CONBERRY_WALL.get());
        this.dropSelf(DABlocks.STRIPPED_CONBERRY_WALL.get());
        this.dropSelf(DABlocks.CONBERRY_SAPLING.get());
        this.dropPottedContents(DABlocks.POTTED_CONBERRY_SAPLING.get());
        this.add(DABlocks.CONBERRY_LEAVES.get(), (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.CONBERRY_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.dropOther(DABlocks.CONBERRY_WALL_SIGN.get(), DABlocks.CONBERRY_SIGN.get());
        this.dropSelf(DABlocks.CONBERRY_SIGN.get());
        this.dropOther(DABlocks.CONBERRY_WALL_HANGING_SIGN.get(), DABlocks.CONBERRY_HANGING_SIGN.get());
        this.dropSelf(DABlocks.CONBERRY_HANGING_SIGN.get());

        //Sunroot woodet
        this.dropSelf(DABlocks.SUNROOT_WOOD.get());
        this.dropSelf(DABlocks.STRIPPED_SUNROOT_WOOD.get());
        this.dropSelfDouble(DABlocks.SUNROOT_LOG.get());
        this.dropSelfDouble(DABlocks.STRIPPED_SUNROOT_LOG.get());
        this.dropSelf(DABlocks.SUNROOT_PLANKS.get());
        this.add(DABlocks.SUNROOT_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.SUNROOT_STAIRS.get());
        this.dropSelf(DABlocks.SUNROOT_FENCE.get());
        this.dropSelf(DABlocks.SUNROOT_FENCE_GATE.get());
        this.add(DABlocks.SUNROOT_DOOR.get(), createDoorTable(DABlocks.SUNROOT_DOOR.get()));
        this.dropSelf(DABlocks.SUNROOT_TRAPDOOR.get());
        this.dropSelf(DABlocks.SUNROOT_BUTTON.get());
        this.dropSelf(DABlocks.SUNROOT_PRESSURE_PLATE.get());
        this.dropSelf(DABlocks.SUNROOT_WALL.get());
        this.dropSelf(DABlocks.STRIPPED_SUNROOT_WALL.get());
        this.dropSelf(DABlocks.SUNROOT_SAPLING.get());
        this.dropPottedContents(DABlocks.POTTED_SUNROOT_SAPLING.get());
        this.add(DABlocks.SUNROOT_LEAVES.get(), (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.SUNROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.dropOther(DABlocks.SUNROOT_WALL_SIGN.get(), DABlocks.SUNROOT_SIGN.get());
        this.dropSelf(DABlocks.SUNROOT_SIGN.get());
        this.dropOther(DABlocks.SUNROOT_WALL_HANGING_SIGN.get(), DABlocks.SUNROOT_HANGING_SIGN.get());
        this.dropSelf(DABlocks.SUNROOT_HANGING_SIGN.get());
        this.dropSelf(DABlocks.SUNROOT_HANGER.get());

        //Totems
        this.dropSelf(DABlocks.MOA_TOTEM.get());
        this.dropSelf(DABlocks.ZEPHYR_TOTEM.get());
        this.dropSelf(DABlocks.AERWHALE_TOTEM.get());

        //Ores
        this.add(DABlocks.SKYJADE_ORE.get(), (block) -> this.createOreDrop(block, DAItems.SKYJADE.get()));
        this.dropSelf(DABlocks.SKYJADE_BLOCK.get());
        this.dropSelf(DABlocks.STRATUS_BLOCK.get());


        //Stone types
        this.dropSelf(DABlocks.COBBLED_ASETERITE.get());
        this.add(DABlocks.COBBLED_ASETERITE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.COBBLED_ASETERITE_STAIRS.get());
        this.dropSelf(DABlocks.COBBLED_ASETERITE_WALL.get());

        this.dropDoubleWithSilk(DABlocks.ASETERITE.get(), DABlocks.COBBLED_ASETERITE.get());
        this.add(DABlocks.ASETERITE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.ASETERITE_STAIRS.get());
        this.dropSelf(DABlocks.ASETERITE_WALL.get());

        this.dropSelf(DABlocks.POLISHED_ASETERITE.get());
        this.add(DABlocks.POLISHED_ASETERITE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.POLISHED_ASETERITE_STAIRS.get());

        this.dropSelf(DABlocks.ASETERITE_BRICKS.get());
        this.add(DABlocks.ASETERITE_BRICKS_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.ASETERITE_BRICKS_STAIRS.get());
        this.dropSelf(DABlocks.ASETERITE_BRICKS_WALL.get());

        this.dropSelfDouble(DABlocks.RAW_CLORITE.get());
        this.add(DABlocks.RAW_CLORITE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.RAW_CLORITE_STAIRS.get());
        this.dropSelf(DABlocks.RAW_CLORITE_WALL.get());
        this.dropSelf(DABlocks.CLORITE.get());
        this.add(DABlocks.CLORITE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.CLORITE_STAIRS.get());
        this.dropSelf(DABlocks.CLORITE_WALL.get());
        this.dropSelf(DABlocks.POLISHED_CLORITE.get());
        this.add(DABlocks.POLISHED_CLORITE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.POLISHED_CLORITE_STAIRS.get());
        this.dropSelf(DABlocks.CLORITE_PILLAR.get());

        this.dropSelf(DABlocks.HOLYSTONE_TILES.get());
        this.add(DABlocks.HOLYSTONE_TILE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.HOLYSTONE_TILE_STAIRS.get());
        this.dropSelf(DABlocks.HOLYSTONE_TILE_WALL.get());

        this.dropSelf(DABlocks.MOSSY_HOLYSTONE_BRICKS.get());
        this.dropSelf(DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get());
        this.add(DABlocks.MOSSY_HOLYSTONE_BRICK_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.MOSSY_HOLYSTONE_BRICK_STAIRS.get());

        this.dropSelf(DABlocks.MOSSY_HOLYSTONE_TILES.get());
        this.dropSelf(DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get());
        this.add(DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get());


        this.dropSelf(DABlocks.BIG_HOLYSTONE_BRICKS.get());
        this.add(DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get());
        this.dropSelf(DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get());

        this.dropSelf(DABlocks.HOLYSTONE_PILLAR.get());
        this.dropSelf(DABlocks.HOLYSTONE_PILLAR_UP.get());
        this.dropSelf(DABlocks.HOLYSTONE_PILLAR_DOWN.get());
        this.dropSelf(DABlocks.CHISELED_HOLYSTONE.get());

        //Plants
        this.dropSelf(DABlocks.CLOUDBLOOM_CARPET.get());

        this.dropSelf(DABlocks.BLUE_SQUASH.get());
        this.dropSelf(DABlocks.GREEN_SQUASH.get());
        this.dropSelf(DABlocks.PURPLE_SQUASH.get());
        this.dropNone(DABlocks.SQUASH_STEM.get());
        this.dropNone(DABlocks.ATTACHED_SQUASH_STEM.get());

        this.add(DABlocks.MINI_GOLDEN_GRASS.get(), this::createGoldenGrassDrops);
        this.add(DABlocks.SHORT_GOLDEN_GRASS.get(), this::createGoldenGrassDrops);
        this.add(DABlocks.MEDIUM_GOLDEN_GRASS.get(), this::createGoldenGrassDrops);
        this.add(DABlocks.TALL_GOLDEN_GRASS.get(), (grass) -> this.createGoldenDoublePlantWithSeedDrops(grass, DABlocks.MEDIUM_GOLDEN_GRASS.get()));
        this.add(DABlocks.TALL_GLOWING_GRASS.get(), (flower) -> createSinglePropConditionTable(Blocks.TALL_GRASS, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.dropSelf(DABlocks.TALL_ALIEN_PLANT.get());

        this.add(DABlocks.FEATHER_GRASS.get(), this::createFeatherGrassDrops);
        this.add(DABlocks.TALL_FEATHER_GRASS.get(), this::createFeatherGrassDrops);

        this.add(DABlocks.GOLDEN_FLOWER.get(), (flower) -> createSinglePropConditionTable(DABlocks.GOLDEN_FLOWER.get(), DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(DABlocks.GLOWING_SPORES.get(), this.createPetalsDrops(DABlocks.GLOWING_SPORES.get()));
        this.dropSelf(DABlocks.ENCHANTED_BLOSSOM.get());

        this.add(DABlocks.GOLDEN_VINES.get(), DABlockLoot::createGoldenVinesDrop);
        this.add(DABlocks.GOLDEN_VINES_PLANT.get(), DABlockLoot::createGoldenVinesDrop);

        this.dropSelf(DABlocks.LIGHTCAP_MUSHROOMS.get());
        this.add(DABlocks.LIGHTCAP_MUSHROOM_BLOCK.get(), (block) -> this.createMushroomBlockDrop(block, DABlocks.LIGHTCAP_MUSHROOMS.get()));

        this.add(DABlocks.PINK_AERCLOUD_MUSHROOM_BLOCK.get(), (block) -> this.createMushroomBlockDrop(block, DABlocks.LIGHTCAP_MUSHROOMS.get()));
        this.add(DABlocks.BLUE_AERCLOUD_MUSHROOM_BLOCK.get(), (block) -> this.createMushroomBlockDrop(block, DABlocks.LIGHTCAP_MUSHROOMS.get()));
        this.dropSelf(DABlocks.AERCLOUD_ROOT_CARPET.get());
        //Flowers
        this.dropSelf(DABlocks.AERLAVENDER.get());
        this.dropSelf(DABlocks.AETHER_CATTAILS.get());
        this.dropSelf(DABlocks.TALL_AERLAVENDER.get());
        this.add(DABlocks.TALL_AETHER_CATTAILS.get(), (flower) -> createSinglePropConditionTable(DABlocks.TALL_AETHER_CATTAILS.get(), DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.dropSelf(DABlocks.RADIANT_ORCHID.get());
        this.dropSelf(DABlocks.SKY_TULIPS.get());
        this.dropSelf(DABlocks.IASPOVE.get());
        this.dropSelf(DABlocks.GOLDEN_ASPESS.get());
        this.dropSelf(DABlocks.ECHAISY.get());

        this.dropPottedContents(DABlocks.POTTED_AERLAVENDER.get());
        this.dropPottedContents(DABlocks.POTTED_TALL_AERLAVENDER.get());
        this.dropPottedContents(DABlocks.POTTED_AETHER_CATTAILS.get());
        this.dropPottedContents(DABlocks.POTTED_RADIANT_ORCHID.get());
        this.dropPottedContents(DABlocks.POTTED_ENCHANTED_BLOSSOM.get());
        this.dropPottedContents(DABlocks.POTTED_SKY_TULIPS.get());
        this.dropPottedContents(DABlocks.POTTED_IASPOVE.get());
        this.dropPottedContents(DABlocks.POTTED_GOLDEN_ASPESS.get());
        this.dropPottedContents(DABlocks.POTTED_ECHAISY.get());

        //Brass Dungeon
        this.dropSelf(DABlocks.NIMBUS_STONE.get());
        this.dropSelf(DABlocks.LIGHT_NIMBUS_STONE.get());
        this.dropSelf(DABlocks.NIMBUS_STAIRS.get());
        this.dropSelf(DABlocks.NIMBUS_SLAB.get());
        this.dropSelf(DABlocks.NIMBUS_WALL.get());
        this.dropNone(DABlocks.LOCKED_NIMBUS_STONE.get());
        this.dropNone(DABlocks.LOCKED_LIGHT_NIMBUS_STONE.get());
        this.dropNone(DABlocks.TRAPPED_NIMBUS_STONE.get());
        this.dropNone(DABlocks.TRAPPED_LIGHT_NIMBUS_STONE.get());
        this.dropNone(DABlocks.BOSS_DOORWAY_NIMBUS_STONE.get());
        this.dropNone(DABlocks.BOSS_DOORWAY_LIGHT_NIMBUS_STONE.get());
        this.dropNone(DABlocks.TREASURE_DOORWAY_NIMBUS_STONE.get());
        this.dropNone(DABlocks.TREASURE_DOORWAY_LIGHT_NIMBUS_STONE.get());
        this.dropSelf(DABlocks.NIMBUS_PILLAR.get());
        this.dropSelf(DABlocks.LIGHT_NIMBUS_PILLAR.get());
        this.dropNone(DABlocks.LOCKED_NIMBUS_PILLAR.get());
        this.dropNone(DABlocks.LOCKED_LIGHT_NIMBUS_PILLAR.get());
        this.dropNone(DABlocks.TRAPPED_NIMBUS_PILLAR.get());
        this.dropNone(DABlocks.TRAPPED_LIGHT_NIMBUS_PILLAR.get());
        this.dropNone(DABlocks.BOSS_DOORWAY_NIMBUS_PILLAR.get());
        this.dropNone(DABlocks.BOSS_DOORWAY_LIGHT_NIMBUS_PILLAR.get());
        this.dropNone(DABlocks.TREASURE_DOORWAY_NIMBUS_PILLAR.get());
        this.dropNone(DABlocks.TREASURE_DOORWAY_LIGHT_NIMBUS_PILLAR.get());

        //Misc
        this.dropNone(DABlocks.VIRULENT_QUICKSAND.get());
        this.dropSelf(DABlocks.SKYJADE_CHAIN.get());
        this.dropSelf(DABlocks.SKYJADE_LANTERN.get());

        this.dropOther(DABlocks.GOLDEN_DIRT_PATH.get(), AetherBlocks.AETHER_DIRT.get());

        this.dropOther(DABlocks.POISON_CAULDRON.get(), Blocks.CAULDRON.asItem());
        this.dropSelfDouble(DABlocks.AERSMOG.get());
        this.dropSelf(DABlocks.STERLING_AERCLOUD.get());
        this.dropSelf(DABlocks.CHROMATIC_AERCLOUD.get());

        this.dropSelfDouble(DABlocks.RAIN_AERCLOUD.get());
        this.dropDoubleWithSilk(DABlocks.GOLDEN_GRASS_BLOCK.get(), AetherBlocks.AETHER_DIRT.get());
        this.dropDoubleWithSilk(DABlocks.AERCLOUD_GRASS_BLOCK.get(), AetherBlocks.COLD_AERCLOUD.get());
        this.dropSelf(DABlocks.BLUE_AERCLOUD_MUSHROOMS.get());
        this.dropSelf(DABlocks.PINK_AERCLOUD_MUSHROOMS.get());
        this.dropSelf(DABlocks.COMBINER.get());


        //Aether redux compat
        this.dropSelf(DABlocks.GILDED_HOLYSTONE_BRICKS.get());
        this.dropSelf(DABlocks.GILDED_HOLYSTONE_BRICK_WALL.get());
        this.add(DABlocks.GILDED_HOLYSTONE_BRICK_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.GILDED_HOLYSTONE_BRICK_STAIRS.get());

        this.dropSelf(DABlocks.GILDED_HOLYSTONE_TILES.get());
        this.dropSelf(DABlocks.GILDED_HOLYSTONE_TILE_WALL.get());
        this.add(DABlocks.GILDED_HOLYSTONE_TILE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.GILDED_HOLYSTONE_TILE_STAIRS.get());

        this.dropSelf(DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get());
        this.dropSelf(DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_WALL.get());
        this.add(DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_STAIRS.get());

        this.dropSelf(DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());
        this.dropSelf(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get());
        this.add(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_STAIRS.get());

        //Aether genesis compat
        this.dropSelf(DABlocks.ROSEROOT_LOG_WALL.get());
        this.dropSelf(DABlocks.STRIPPED_ROSEROOT_LOG_WALL.get());
        this.dropSelf(DABlocks.CRUDEROOT_LOG_WALL.get());
        this.dropSelf(DABlocks.STRIPPED_CRUDEROOT_LOG_WALL.get());
        this.dropSelf(DABlocks.YAGROOT_LOG_WALL.get());
        this.dropSelf(DABlocks.STRIPPED_YAGROOT_LOG_WALL.get());
        this.dropSelf(DABlocks.CONBERRY_LOG_WALL.get());
        this.dropSelf(DABlocks.STRIPPED_CONBERRY_LOG_WALL.get());
        this.dropSelf(DABlocks.SUNROOT_LOG_WALL.get());
        this.dropSelf(DABlocks.STRIPPED_SUNROOT_LOG_WALL.get());

    }

    protected static LootTable.Builder createGoldenVinesDrop(Block p_251070_) {
        return LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(DAItems.GOLDEN_BERRIES.get())).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_251070_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GoldenVines.BERRIES, true))));
    }

    protected static LootTable.Builder createVinesDrop(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(HAS_SHEARS).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block)));
    }

    protected LootTable.Builder createGoldenDoublePlantWithSeedDrops(Block block, Block p_248735_) {
        LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(p_248735_).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(block, LootItem.lootTableItem(Items.WHEAT_SEEDS)).when(LootItemRandomChanceCondition.randomChance(0.125F)));
        LootPoolEntryContainer.Builder<?> builder1 = LootItem.lootTableItem(p_248735_).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(block, LootItem.lootTableItem(DAItems.GOLDEN_GRASS_SEEDS.get())).when(LootItemRandomChanceCondition.randomChance(0.1F)));
        LootPoolEntryContainer.Builder<?> builder2 = LootItem.lootTableItem(p_248735_).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(block, LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get())).when(LootItemRandomChanceCondition.randomChance(0.01F)));

        return LootTable.lootTable()

                .withPool(LootPool.lootPool().add(builder).add(builder1).add(builder2).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))), new BlockPos(0, 1, 0))))
                .withPool(LootPool.lootPool().add(builder).add(builder1).add(builder2).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
                        .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))), new BlockPos(0, -1, 0))));
    }




    protected LootTable.Builder createGoldenGrassDrops(Block block) {
        return createShearsDispatchTable(block, this.applyExplosionDecay(block,
                LootItem.lootTableItem(Items.WHEAT_SEEDS).when(LootItemRandomChanceCondition.randomChance(0.125F))
                        .apply(ApplyBonusCount.addUniformBonusCount(this.registries.holderOrThrow(Enchantments.FORTUNE), 2))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.1F))
                        .apply(ApplyBonusCount.addUniformBonusCount(this.registries.holderOrThrow(Enchantments.FORTUNE), 2)).add(LootItem.lootTableItem(DAItems.GOLDEN_GRASS_SEEDS.get())))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.01F))
                        .apply(ApplyBonusCount.addUniformBonusCount(this.registries.holderOrThrow(Enchantments.FORTUNE), 2)).add(LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get())));

    }

    protected LootTable.Builder createFeatherGrassDrops(Block block) {
        return createShearsDispatchTable(block, this.applyExplosionDecay(block,
                LootItem.lootTableItem(DAItems.CLOUDBLOOM_BOUQUET).when(LootItemRandomChanceCondition.randomChance(0.5F))
                        .apply(ApplyBonusCount.addUniformBonusCount(this.registries.holderOrThrow(Enchantments.FORTUNE), 2))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.1F)));
    }


    public LootTable.Builder droppingWithChancesAndSkyrootSticksAndAerglowPetal(Block block, Block sapling, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(sapling)).when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.holderOrThrow(Enchantments.FORTUNE), chances)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(HAS_SHEARS.or(this.hasSilkTouch()).invert())
                        .add(this.applyExplosionDecay(block,
                                LootItem.lootTableItem(AetherItems.SKYROOT_STICK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.holderOrThrow(Enchantments.FORTUNE), 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))))
                        .add(this.applyExplosionDecay(block,
                                LootItem.lootTableItem(DAItems.AERGLOW_BLOSSOM.get()).apply(ApplyBonusCount.addOreBonusCount(this.registries.holderOrThrow(Enchantments.FORTUNE))))))
                .apply(DoubleDrops.builder());
    }
}
