package teamrazor.deepaether.datagen.loot;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.providers.AetherBlockLootSubProvider;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.loot.functions.DoubleDrops;
import com.aetherteam.aether.mixin.mixins.common.accessor.BlockLootAccessor;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.block.Behaviors.GoldenVines;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DABlockLoot extends AetherBlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(AetherBlocks.TREASURE_CHEST.get()).map(ItemLike::asItem).collect(Collectors.toSet());

    public DABlockLoot() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }


    @Override
    public void generate() {
        this.dropSelf(DABlocks.ROSEROOT_WOOD.get());
        this.dropSelf(DABlocks.ROTTEN_ROSEROOT_LOG.get());
        this.dropSelf(DABlocks.STRIPPED_ROSEROOT_WOOD.get());
        this.dropSelfDouble(DABlocks.ROSEROOT_LOG.get());
        this.dropSelf(DABlocks.STRIPPED_ROSEROOT_LOG.get());
        this.dropSelf(DABlocks.ROSEROOT_PLANKS.get());
        this.dropSelf(DABlocks.ROSEROOT_SLAB.get());
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
        this.add(DABlocks.ROSEROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.ROSEROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));

        this.add(DABlocks.FLOWERING_ROSEROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticksAndAerglowPetal(leaves, DABlocks.ROSEROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.add(DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticksAndAerglowPetal(leaves, DABlocks.BLUE_ROSEROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));

        this.dropOther(DABlocks.ROSEROOT_WALL_SIGN.get(), DABlocks.ROSEROOT_SIGN.get());
        this.dropSelf(DABlocks.ROSEROOT_SIGN.get());
        this.dropOther(DABlocks.ROSEROOT_WALL_HANGING_SIGN.get(), DABlocks.ROSEROOT_HANGING_SIGN.get());
        this.dropSelf(DABlocks.ROSEROOT_HANGING_SIGN.get());
        this.add(DABlocks.BLUE_ROSEROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.BLUE_ROSEROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));

        this.dropSelf(DABlocks.AERGLOW_BLOSSOM_BLOCK.get());

        this.dropSelf(DABlocks.SQUASH.get());
        this.dropNone(DABlocks.SQUASH_STEM.get());
        this.dropNone(DABlocks.ATTACHED_SQUASH_STEM.get());

        this.dropSelf(DABlocks.YAGROOT_WOOD.get());
        this.dropSelf(DABlocks.STRIPPED_YAGROOT_WOOD.get());
        this.dropSelfDouble(DABlocks.YAGROOT_LOG.get());
        this.dropSelf(DABlocks.STRIPPED_YAGROOT_LOG.get());
        this.dropSelf(DABlocks.YAGROOT_PLANKS.get());
        this.dropSelf(DABlocks.YAGROOT_SLAB.get());
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
        this.add(DABlocks.YAGROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.YAGROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.dropOther(DABlocks.YAGROOT_WALL_SIGN.get(), DABlocks.YAGROOT_SIGN.get());
        this.dropSelf(DABlocks.YAGROOT_SIGN.get());
        this.dropOther(DABlocks.YAGROOT_WALL_HANGING_SIGN.get(), DABlocks.YAGROOT_HANGING_SIGN.get());
        this.dropSelf(DABlocks.YAGROOT_HANGING_SIGN.get());

        this.dropSelfDouble(DABlocks.YAGROOT_ROOTS.get());
        this.dropSelf(DABlocks.MUDDY_YAGROOT_ROOTS.get());


        this.dropSelf(DABlocks.CRUDEROOT_WOOD.get());
        this.dropSelf(DABlocks.STRIPPED_CRUDEROOT_WOOD.get());
        this.dropSelfDouble(DABlocks.CRUDEROOT_LOG.get());
        this.dropSelf(DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        this.dropSelf(DABlocks.CRUDEROOT_PLANKS.get());
        this.dropSelf(DABlocks.CRUDEROOT_SLAB.get());
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
        this.add(DABlocks.CRUDEROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.CRUDEROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.dropOther(DABlocks.CRUDEROOT_WALL_SIGN.get(), DABlocks.CRUDEROOT_SIGN.get());
        this.dropSelf(DABlocks.CRUDEROOT_SIGN.get());
        this.dropOther(DABlocks.CRUDEROOT_WALL_HANGING_SIGN.get(), DABlocks.CRUDEROOT_HANGING_SIGN.get());
        this.dropSelf(DABlocks.CRUDEROOT_HANGING_SIGN.get());

        this.dropSelf(DABlocks.CONBERRY_WOOD.get());
        this.dropSelf(DABlocks.STRIPPED_CONBERRY_WOOD.get());
        this.dropSelfDouble(DABlocks.CONBERRY_LOG.get());
        this.dropSelfDouble(DABlocks.STRIPPED_CONBERRY_LOG.get());
        this.dropSelf(DABlocks.CONBERRY_PLANKS.get());
        this.dropSelf(DABlocks.CONBERRY_SLAB.get());
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
        this.add(DABlocks.CONBERRY_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.CONBERRY_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.dropOther(DABlocks.CONBERRY_WALL_SIGN.get(), DABlocks.CONBERRY_SIGN.get());
        this.dropSelf(DABlocks.CONBERRY_SIGN.get());
        this.dropOther(DABlocks.CONBERRY_WALL_HANGING_SIGN.get(), DABlocks.CONBERRY_HANGING_SIGN.get());
        this.dropSelf(DABlocks.CONBERRY_HANGING_SIGN.get());

        this.dropSelf(DABlocks.SUNROOT_WOOD.get());
        this.dropSelf(DABlocks.STRIPPED_SUNROOT_WOOD.get());
        this.dropSelfDouble(DABlocks.SUNROOT_LOG.get());
        this.dropSelfDouble(DABlocks.STRIPPED_SUNROOT_LOG.get());
        this.dropSelf(DABlocks.SUNROOT_PLANKS.get());
        this.dropSelf(DABlocks.SUNROOT_SLAB.get());
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
        this.add(DABlocks.SUNROOT_LEAVES.get(),
                (leaves) -> droppingWithChancesAndSkyrootSticks(leaves, DABlocks.SUNROOT_SAPLING.get(), BlockLootAccessor.aether$getNormalLeavesSaplingChances()));
        this.dropOther(DABlocks.SUNROOT_WALL_SIGN.get(), DABlocks.SUNROOT_SIGN.get());
        this.dropSelf(DABlocks.SUNROOT_SIGN.get());
        this.dropOther(DABlocks.SUNROOT_WALL_HANGING_SIGN.get(), DABlocks.SUNROOT_HANGING_SIGN.get());
        this.dropSelf(DABlocks.SUNROOT_HANGING_SIGN.get());

        this.dropSelfDouble(DABlocks.AETHER_MUD.get());
        this.dropSelf(DABlocks.PACKED_AETHER_MUD.get());
        this.dropSelf(DABlocks.AETHER_MUD_BRICKS.get());
        this.dropSelf(DABlocks.AETHER_MUD_BRICKS_SLAB.get());
        this.dropSelf(DABlocks.AETHER_MUD_BRICKS_STAIRS.get());
        this.dropSelf(DABlocks.AETHER_MUD_BRICKS_WALL.get());

        this.dropSelf(DABlocks.AETHER_MOSS_BLOCK.get());
        this.dropSelf(DABlocks.AETHER_MOSS_CARPET.get());

        //ORES
        this.add(DABlocks.SKYJADE_ORE.get(), (block) -> this.createOreDrop(block, DAItems.SKYJADE.get()));
        this.dropSelf(DABlocks.SKYJADE_BLOCK.get());
        this.dropSelf(DABlocks.STRATUS_BLOCK.get());


        //STONES


        this.dropSelf(DABlocks.COBBLED_ASETERITE.get());
        this.dropSelf(DABlocks.COBBLED_ASETERITE_SLAB.get());
        this.dropSelf(DABlocks.COBBLED_ASETERITE_STAIRS.get());
        this.dropSelf(DABlocks.COBBLED_ASETERITE_WALL.get());

        this.dropDoubleWithSilk(DABlocks.ASETERITE.get(), DABlocks.COBBLED_ASETERITE.get());
        this.dropSelf(DABlocks.ASETERITE_SLAB.get());
        this.dropSelf(DABlocks.ASETERITE_STAIRS.get());
        this.dropSelf(DABlocks.ASETERITE_WALL.get());

        this.dropSelf(DABlocks.POLISHED_ASETERITE.get());
        this.dropSelf(DABlocks.POLISHED_ASETERITE_SLAB.get());
        this.dropSelf(DABlocks.POLISHED_ASETERITE_STAIRS.get());

        this.dropSelf(DABlocks.ASETERITE_BRICKS.get());
        this.dropSelf(DABlocks.ASETERITE_BRICKS_SLAB.get());
        this.dropSelf(DABlocks.ASETERITE_BRICKS_STAIRS.get());
        this.dropSelf(DABlocks.ASETERITE_BRICKS_WALL.get());

        this.dropSelfDouble(DABlocks.RAW_CLORITE.get());
        this.dropSelf(DABlocks.RAW_CLORITE_SLAB.get());
        this.dropSelf(DABlocks.RAW_CLORITE_STAIRS.get());
        this.dropSelf(DABlocks.RAW_CLORITE_WALL.get());
        this.dropSelf(DABlocks.CLORITE.get());
        this.dropSelf(DABlocks.CLORITE_SLAB.get());
        this.dropSelf(DABlocks.CLORITE_STAIRS.get());
        this.dropSelf(DABlocks.CLORITE_WALL.get());
        this.dropSelf(DABlocks.POLISHED_CLORITE.get());
        this.dropSelf(DABlocks.POLISHED_CLORITE_SLAB.get());
        this.dropSelf(DABlocks.POLISHED_CLORITE_STAIRS.get());
        this.dropSelf(DABlocks.CLORITE_PILLAR.get());

        this.dropSelf(DABlocks.HOLYSTONE_TILES.get());
        this.dropSelf(DABlocks.HOLYSTONE_TILE_SLAB.get());
        this.dropSelf(DABlocks.HOLYSTONE_TILE_STAIRS.get());
        this.dropSelf(DABlocks.HOLYSTONE_TILE_WALL.get());

        this.dropSelf(DABlocks.MOSSY_HOLYSTONE_TILES.get());
        this.dropSelf(DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get());
        this.dropSelf(DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get());
        this.dropSelf(DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get());


        this.dropSelf(DABlocks.BIG_HOLYSTONE_BRICKS.get());
        this.dropSelf(DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get());
        this.dropSelf(DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get());
        this.dropSelf(DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get());

        this.dropSelf(DABlocks.HOLYSTONE_PILLAR.get());
        this.dropSelf(DABlocks.HOLYSTONE_PILLAR_UP.get());
        this.dropSelf(DABlocks.HOLYSTONE_PILLAR_DOWN.get());
        this.dropSelf(DABlocks.CHISELED_HOLYSTONE.get());

        //FLOWERS

        this.dropSelf(DABlocks.AERLAVENDER.get());
        this.dropSelf(DABlocks.AETHER_CATTAILS.get());
        this.dropSelf(DABlocks.TALL_AERLAVENDER.get());
        this.add(DABlocks.TALL_AETHER_CATTAILS.get(),
                (flower) -> createSinglePropConditionTable(DABlocks.TALL_AETHER_CATTAILS.get(), DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
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

        this.dropNone(DABlocks.VIRULENT_QUICKSAND.get());

        this.add(DABlocks.YAGROOT_VINE.get(), (vine) -> createYagrootVinesDrop(DABlocks.YAGROOT_VINE.get()));

        this.dropOther(DABlocks.GOLDEN_DIRT_PATH.get(), AetherBlocks.AETHER_DIRT.get());

        this.dropOther(DABlocks.POISON_CAULDRON.get(), Blocks.CAULDRON.asItem());
        this.dropSelfDouble(DABlocks.AERSMOG.get());
        this.dropSelf(DABlocks.STERLING_AERCLOUD.get());
        this.dropSelf(DABlocks.CHROMATIC_AERCLOUD.get());

        this.dropSelfDouble(DABlocks.RAIN_AERCLOUD.get());
        this.dropDoubleWithSilk(DABlocks.GOLDEN_GRASS_BLOCK.get(), AetherBlocks.AETHER_DIRT.get());
        this.dropSelfDouble(DABlocks.AETHER_COARSE_DIRT.get());

        this.add(DABlocks.MINI_GOLDEN_GRASS.get(), this::createGoldenGrassDrops);
        this.add(DABlocks.SHORT_GOLDEN_GRASS.get(), this::createGoldenGrassDrops);
        this.add(DABlocks.MEDIUM_GOLDEN_GRASS.get(), this::createGoldenGrassDrops);
        this.add(DABlocks.TALL_GOLDEN_GRASS.get(), (grass) -> this.createGoldenDoublePlantWithSeedDrops(grass, DABlocks.MEDIUM_GOLDEN_GRASS.get()));

        this.add(DABlocks.MINI_GOLDEN_GRASS.get(), this::createGoldenGrassDrops);

        this.dropNone(DABlocks.FEATHER_GRASS.get());
        this.dropNone(DABlocks.TALL_FEATHER_GRASS.get());

        this.dropSelf(DABlocks.GOLDEN_FLOWER.get());
        this.dropSelf(DABlocks.ENCHANTED_BLOSSOM.get());


        this.add(DABlocks.GOLDEN_VINES.get(), DABlockLoot::createGoldenVinesDrop);
        this.add(DABlocks.GOLDEN_VINES_PLANT.get(), DABlockLoot::createGoldenVinesDrop);

        this.dropSelf(DABlocks.LIGHTCAP_MUSHROOMS.get());


        //Lost content
        this.dropSelf(DABlocks.GILDED_HOLYSTONE_TILES.get());
        this.dropSelf(DABlocks.GILDED_HOLYSTONE_TILE_WALL.get());
        this.dropSelf(DABlocks.GILDED_HOLYSTONE_TILE_SLAB.get());
        this.dropSelf(DABlocks.GILDED_HOLYSTONE_TILE_STAIRS.get());

        this.dropSelf(DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());
        this.dropSelf(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get());
        this.dropSelf(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_SLAB.get());
        this.dropSelf(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_STAIRS.get());

        //Aether genesis
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

    protected static LootTable.Builder createYagrootVinesDrop(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(HAS_SHEARS).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block)));
    }

    protected LootTable.Builder createGoldenDoublePlantWithSeedDrops(Block block, Block p_248735_) {
        LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(p_248735_).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(block, LootItem.lootTableItem(Items.WHEAT_SEEDS)).when(LootItemRandomChanceCondition.randomChance(0.125F)));
        LootPoolEntryContainer.Builder<?> builder1 = LootItem.lootTableItem(p_248735_).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(block, LootItem.lootTableItem(DAItems.GOLDEN_GRASS_SEEDS.get())).when(LootItemRandomChanceCondition.randomChance(0.1F)));
        LootPoolEntryContainer.Builder<?> builder2 = LootItem.lootTableItem(p_248735_).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(block, LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get())).when(LootItemRandomChanceCondition.randomChance(0.01F)));

        return LootTable.lootTable()

                .withPool(LootPool.lootPool().add(builder).add(builder1).add(builder2).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0))))
                .withPool(LootPool.lootPool().add(builder).add(builder1).add(builder2).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
                        .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }


    protected LootTable.Builder createGoldenGrassDrops(Block block) {
        return createShearsDispatchTable(block, this.applyExplosionDecay(block,
                LootItem.lootTableItem(Items.WHEAT_SEEDS).when(LootItemRandomChanceCondition.randomChance(0.125F))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.1F))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)).add(LootItem.lootTableItem(DAItems.GOLDEN_GRASS_SEEDS.get())))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.01F))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)).add(LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get())));

    }


    public LootTable.Builder droppingWithChancesAndSkyrootSticksAndAerglowPetal(Block block, Block sapling, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(sapling)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(BlockLootAccessor.aether$hasShearsOrSilkTouch().invert())
                        .add(this.applyExplosionDecay(block,
                                LootItem.lootTableItem(AetherItems.SKYROOT_STICK.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))))
                        .add(this.applyExplosionDecay(block,
                                LootItem.lootTableItem(DAItems.AERGLOW_BLOSSOM.get()).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))))
                .apply(DoubleDrops.builder());
    }

    protected static final LootItemCondition.Builder HAS_NO_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)).invert();
    /*protected static LootTable.Builder deepAetherTallFlowerDrop(Block block, Block block1) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(HAS_SILK_TOUCH).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block))).withPool(LootPool.lootPool().when(HAS_NO_SILK_TOUCH).when(HAS_NO_SHEARS).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block1)));
    }

    public void createDeepAetherTallFlowerDrop(Block block, Block block1) {
        this.add(block, (result) -> this.deepAetherTallFlowerDrop(block, block1));
    }*/

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return DABlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
