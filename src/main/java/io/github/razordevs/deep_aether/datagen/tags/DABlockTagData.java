package io.github.razordevs.deep_aether.datagen.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.zepalesque.unity.block.UnityBlocks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class DABlockTagData extends BlockTagsProvider {

    public DABlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, DeepAether.MODID, helper);
    }
    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        IntrinsicTagAppender<Block> aether_block_tag = this.tag(AetherTags.Blocks.TREATED_AS_AETHER_BLOCK);
        Collection<DeferredHolder<Block, ? extends Block>> blocks = DABlocks.BLOCKS.getEntries();


        for (DeferredHolder<Block, ? extends Block> block : blocks) {
            aether_block_tag.add(block.get());
        }

        tag(AetherTags.Blocks.AETHER_ANIMALS_SPAWNABLE_ON).add(
                DABlocks.GOLDEN_GRASS_BLOCK.get(),
                DABlocks.AERCLOUD_GRASS_BLOCK.get()
        );
        tag(DATags.Blocks.CAN_GOLDEN_VINES_SURVIVE_ON).add(
                AetherBlocks.QUICKSOIL.get(),
                Blocks.SAND
        );
        tag(BlockTags.BEACON_BASE_BLOCKS).add(
                DABlocks.SKYJADE_BLOCK.get(),
                DABlocks.STRATUS_BLOCK.get()
        );

        tag(DATags.Blocks.ROSEROOT_LOGS).add(
                DABlocks.ROSEROOT_LOG.get(),
                DABlocks.ROSEROOT_WOOD.get(),
                DABlocks.STRIPPED_ROSEROOT_LOG.get(),
                DABlocks.STRIPPED_ROSEROOT_WOOD.get());

        tag(DATags.Blocks.CRUDEROOT_LOGS).add(
                DABlocks.CRUDEROOT_LOG.get(),
                DABlocks.CRUDEROOT_WOOD.get(),
                DABlocks.STRIPPED_CRUDEROOT_LOG.get(),
                DABlocks.STRIPPED_CRUDEROOT_WOOD.get());

        tag(DATags.Blocks.YAGROOT_LOGS).add(
                DABlocks.YAGROOT_LOG.get(),
                DABlocks.YAGROOT_WOOD.get(),
                DABlocks.STRIPPED_YAGROOT_LOG.get(),
                DABlocks.STRIPPED_YAGROOT_WOOD.get());

        tag(DATags.Blocks.CONBERRY_LOGS).add(
                DABlocks.CONBERRY_LOG.get(),
                DABlocks.CONBERRY_WOOD.get(),
                DABlocks.STRIPPED_CONBERRY_LOG.get(),
                DABlocks.STRIPPED_CONBERRY_WOOD.get());

        tag(DATags.Blocks.SUNROOT_LOGS).add(
                DABlocks.SUNROOT_LOG.get(),
                DABlocks.SUNROOT_WOOD.get(),
                DABlocks.STRIPPED_SUNROOT_LOG.get(),
                DABlocks.STRIPPED_SUNROOT_WOOD.get());

        tag(BlockTags.LOGS).add(
                DABlocks.ROSEROOT_LOG.get(),
                DABlocks.ROSEROOT_WOOD.get(),
                DABlocks.STRIPPED_ROSEROOT_LOG.get(),
                DABlocks.STRIPPED_ROSEROOT_WOOD.get(),
                DABlocks.YAGROOT_LOG.get(),
                DABlocks.YAGROOT_WOOD.get(),
                DABlocks.STRIPPED_YAGROOT_LOG.get(),
                DABlocks.STRIPPED_YAGROOT_WOOD.get(),
                DABlocks.CRUDEROOT_LOG.get(),
                DABlocks.CRUDEROOT_WOOD.get(),
                DABlocks.STRIPPED_CRUDEROOT_LOG.get(),
                DABlocks.STRIPPED_CRUDEROOT_WOOD.get(),
                DABlocks.CONBERRY_LOG.get(),
                DABlocks.CONBERRY_WOOD.get(),
                DABlocks.STRIPPED_CONBERRY_LOG.get(),
                DABlocks.STRIPPED_CONBERRY_WOOD.get(),
                DABlocks.SUNROOT_LOG.get(),
                DABlocks.SUNROOT_WOOD.get(),
                DABlocks.STRIPPED_SUNROOT_LOG.get(),
                DABlocks.STRIPPED_SUNROOT_WOOD.get()
        );
        tag(BlockTags.LOGS_THAT_BURN).add(
                DABlocks.ROSEROOT_LOG.get(),
                DABlocks.ROSEROOT_WOOD.get(),
                DABlocks.STRIPPED_ROSEROOT_LOG.get(),
                DABlocks.STRIPPED_ROSEROOT_WOOD.get(),
                DABlocks.YAGROOT_LOG.get(),
                DABlocks.YAGROOT_WOOD.get(),
                DABlocks.STRIPPED_YAGROOT_LOG.get(),
                DABlocks.STRIPPED_YAGROOT_WOOD.get(),
                DABlocks.CRUDEROOT_LOG.get(),
                DABlocks.CRUDEROOT_WOOD.get(),
                DABlocks.STRIPPED_CRUDEROOT_LOG.get(),
                DABlocks.STRIPPED_CRUDEROOT_WOOD.get(),
                DABlocks.CONBERRY_LOG.get(),
                DABlocks.CONBERRY_WOOD.get(),
                DABlocks.STRIPPED_CONBERRY_LOG.get(),
                DABlocks.STRIPPED_CONBERRY_WOOD.get(),
                DABlocks.SUNROOT_LOG.get(),
                DABlocks.SUNROOT_WOOD.get(),
                DABlocks.STRIPPED_SUNROOT_LOG.get(),
                DABlocks.STRIPPED_SUNROOT_WOOD.get()
        );


        tag(DATags.Blocks.NIMBUS_BLOCKS).add(
                DABlocks.NIMBUS_STONE.get(),
                DABlocks.LIGHT_NIMBUS_STONE.get(),
                DABlocks.LOCKED_NIMBUS_STONE.get(),
                DABlocks.LOCKED_LIGHT_NIMBUS_STONE.get(),
                DABlocks.TRAPPED_NIMBUS_STONE.get(),
                DABlocks.TRAPPED_LIGHT_NIMBUS_STONE.get(),
                DABlocks.BOSS_DOORWAY_NIMBUS_STONE.get(),
                DABlocks.BOSS_DOORWAY_LIGHT_NIMBUS_STONE.get(),
                DABlocks.TREASURE_DOORWAY_NIMBUS_STONE.get(),
                DABlocks.TREASURE_DOORWAY_LIGHT_NIMBUS_STONE.get(),
                DABlocks.NIMBUS_STAIRS.get(),
                DABlocks.NIMBUS_SLAB.get(),
                DABlocks.NIMBUS_WALL.get(),
                DABlocks.NIMBUS_PILLAR.get(),
                DABlocks.LIGHT_NIMBUS_PILLAR.get(),
                DABlocks.LOCKED_NIMBUS_PILLAR.get(),
                DABlocks.LOCKED_LIGHT_NIMBUS_PILLAR.get(),
                DABlocks.TRAPPED_NIMBUS_PILLAR.get(),
                DABlocks.TRAPPED_LIGHT_NIMBUS_PILLAR.get(),
                DABlocks.BOSS_DOORWAY_NIMBUS_PILLAR.get(),
                DABlocks.BOSS_DOORWAY_LIGHT_NIMBUS_PILLAR.get(),
                DABlocks.TREASURE_DOORWAY_NIMBUS_PILLAR.get(),
                DABlocks.TREASURE_DOORWAY_LIGHT_NIMBUS_PILLAR.get()
        );

        tag(BlockTags.ALL_SIGNS).add(
                DABlocks.ROSEROOT_SIGN.get(),
                DABlocks.YAGROOT_SIGN.get(),
                DABlocks.CRUDEROOT_SIGN.get(),
                DABlocks.CONBERRY_SIGN.get(),
                DABlocks.SUNROOT_SIGN.get(),
                DABlocks.ROSEROOT_WALL_SIGN.get(),
                DABlocks.YAGROOT_WALL_SIGN.get(),
                DABlocks.CRUDEROOT_WALL_SIGN.get(),
                DABlocks.CONBERRY_WALL_SIGN.get(),
                DABlocks.SUNROOT_WALL_SIGN.get()
        );

        tag(BlockTags.WALL_SIGNS).add(
                DABlocks.ROSEROOT_WALL_SIGN.get(),
                DABlocks.YAGROOT_WALL_SIGN.get(),
                DABlocks.CRUDEROOT_WALL_SIGN.get(),
                DABlocks.CONBERRY_WALL_SIGN.get(),
                DABlocks.SUNROOT_WALL_SIGN.get()
        );

        tag(BlockTags.STANDING_SIGNS).add(
                DABlocks.ROSEROOT_SIGN.get(),
                DABlocks.YAGROOT_SIGN.get(),
                DABlocks.CRUDEROOT_SIGN.get(),
                DABlocks.CONBERRY_SIGN.get(),
                DABlocks.SUNROOT_SIGN.get()
        );

        tag(BlockTags.CEILING_HANGING_SIGNS).add(
                DABlocks.ROSEROOT_HANGING_SIGN.get(),
                DABlocks.YAGROOT_HANGING_SIGN.get(),
                DABlocks.CRUDEROOT_HANGING_SIGN.get(),
                DABlocks.CONBERRY_HANGING_SIGN.get(),
                DABlocks.SUNROOT_HANGING_SIGN.get()
        );

        tag(BlockTags.WALL_HANGING_SIGNS).add(
                DABlocks.ROSEROOT_WALL_HANGING_SIGN.get(),
                DABlocks.YAGROOT_WALL_HANGING_SIGN.get(),
                DABlocks.CRUDEROOT_WALL_HANGING_SIGN.get(),
                DABlocks.CONBERRY_WALL_HANGING_SIGN.get(),
                DABlocks.SUNROOT_WALL_HANGING_SIGN.get()
        );

        tag(BlockTags.ALL_HANGING_SIGNS).add(
                DABlocks.ROSEROOT_WALL_HANGING_SIGN.get(),
                DABlocks.YAGROOT_WALL_HANGING_SIGN.get(),
                DABlocks.CRUDEROOT_WALL_HANGING_SIGN.get(),
                DABlocks.CONBERRY_WALL_HANGING_SIGN.get(),
                DABlocks.SUNROOT_WALL_HANGING_SIGN.get(),
                DABlocks.ROSEROOT_HANGING_SIGN.get(),
                DABlocks.YAGROOT_HANGING_SIGN.get(),
                DABlocks.CRUDEROOT_HANGING_SIGN.get(),
                DABlocks.CONBERRY_HANGING_SIGN.get(),
                DABlocks.SUNROOT_HANGING_SIGN.get()
        );


        tag(BlockTags.WALLS).add(
                DABlocks.ROSEROOT_WALL.get(),
                DABlocks.STRIPPED_ROSEROOT_WALL.get(),
                DABlocks.YAGROOT_WALL.get(),
                DABlocks.STRIPPED_YAGROOT_WALL.get(),
                DABlocks.CRUDEROOT_WALL.get(),
                DABlocks.STRIPPED_CRUDEROOT_WALL.get(),
                DABlocks.CONBERRY_WALL.get(),
                DABlocks.STRIPPED_CONBERRY_WALL.get(),
                DABlocks.SUNROOT_WALL.get(),
                DABlocks.STRIPPED_SUNROOT_WALL.get(),
                DABlocks.CLORITE_WALL.get(),
                DABlocks.RAW_CLORITE_WALL.get(),
                DABlocks.ASETERITE_WALL.get(),
                DABlocks.COBBLED_ASETERITE_WALL.get(),
                DABlocks.ASETERITE_BRICKS_WALL.get(),
                DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(),
                DABlocks.HOLYSTONE_TILE_WALL.get(),
                DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get(),
                DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(),
                DABlocks.GILDED_HOLYSTONE_BRICK_WALL.get(),
                DABlocks.GILDED_HOLYSTONE_TILE_WALL.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_WALL.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get(),
                DABlocks.ROSEROOT_LOG_WALL.get(),
                DABlocks.STRIPPED_ROSEROOT_LOG_WALL.get(),
                DABlocks.YAGROOT_LOG_WALL.get(),
                DABlocks.STRIPPED_YAGROOT_LOG_WALL.get(),
                DABlocks.CRUDEROOT_LOG_WALL.get(),
                DABlocks.STRIPPED_CRUDEROOT_LOG_WALL.get(),
                DABlocks.CONBERRY_LOG_WALL.get(),
                DABlocks.STRIPPED_CONBERRY_LOG_WALL.get(),
                DABlocks.SUNROOT_LOG_WALL.get(),
                DABlocks.STRIPPED_SUNROOT_LOG_WALL.get(),
                DABlocks.NIMBUS_WALL.get()
        );

        tag(BlockTags.FENCE_GATES).add(
                DABlocks.ROSEROOT_FENCE_GATE.get(),
                DABlocks.YAGROOT_FENCE_GATE.get(),
                DABlocks.CRUDEROOT_FENCE_GATE.get(),
                DABlocks.CONBERRY_FENCE_GATE.get(),
                DABlocks.SUNROOT_FENCE_GATE.get()
        );
        tag(BlockTags.LEAVES).add(
                DABlocks.ROSEROOT_LEAVES.get(),
                DABlocks.BLUE_ROSEROOT_LEAVES.get(),
                DABlocks.FLOWERING_ROSEROOT_LEAVES.get(),
                DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(),
                DABlocks.YAGROOT_LEAVES.get(),
                DABlocks.CRUDEROOT_LEAVES.get(),
                DABlocks.CONBERRY_LEAVES.get(),
                DABlocks.SUNROOT_LEAVES.get()
        );

        tag(DATags.Blocks.STERLING_AERCLOUD_REPLACEABLE).add(
                DABlocks.RAIN_AERCLOUD.get()
        );

        tag(DATags.Blocks.TOTEMS).add(
                DABlocks.MOA_TOTEM.get(),
                DABlocks.ZEPHYR_TOTEM.get(),
                DABlocks.AERWHALE_TOTEM.get()
        );

        tag(BlockTags.NEEDS_STONE_TOOL).add(
                DABlocks.NIMBUS_STONE.get(),
                DABlocks.LIGHT_NIMBUS_STONE.get(),
                DABlocks.NIMBUS_STAIRS.get(),
                DABlocks.NIMBUS_SLAB.get(),
                DABlocks.NIMBUS_WALL.get()
        );

        tag(BlockTags.NEEDS_IRON_TOOL).add(
                DABlocks.SKYJADE_BLOCK.get(),
                DABlocks.SKYJADE_ORE.get()
        );

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                DABlocks.STRATUS_BLOCK.get()
        );
        tag(BlockTags.CLIMBABLE).add(
                DABlocks.YAGROOT_VINE.get(),
                DABlocks.GLOWING_VINE.get(),
                DABlocks.GOLDEN_VINES.get(),
                DABlocks.GOLDEN_VINES_PLANT.get(),
                DABlocks.SUNROOT_HANGER.get()
        );

        tag(AetherTags.Blocks.AETHER_DIRT).add(
                DABlocks.GOLDEN_GRASS_BLOCK.get(),
                DABlocks.AERCLOUD_GRASS_BLOCK.get()
        );
        tag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH).add(
                UnityBlocks.FLUTEMOSS_BLOCK.get(),
                UnityBlocks.FLUTEMOSS_CARPET.get(),
                DABlocks.CLOUDBLOOM_CARPET.get(),
                DABlocks.YAGROOT_LOG.get(),
                DABlocks.MUDDY_YAGROOT_ROOTS.get(),
                DABlocks.YAGROOT_ROOTS.get(),
                DABlocks.TALL_AETHER_CATTAILS.get(),
                DABlocks.TALL_GLOWING_GRASS.get(),
                DABlocks.AETHER_CATTAILS.get()
        );
        tag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH).add(
                UnityBlocks.FLUTEMOSS_BLOCK.get(),
                UnityBlocks.FLUTEMOSS_CARPET.get(),
                DABlocks.CLOUDBLOOM_CARPET.get(),
                DABlocks.YAGROOT_ROOTS.get()
        );
        tag(BlockTags.SCULK_REPLACEABLE).add(
                DABlocks.ASETERITE.get(),
                DABlocks.CLORITE.get()
        );
        tag(BlockTags.WOODEN_FENCES).add(
                DABlocks.ROSEROOT_FENCE.get(),
                DABlocks.YAGROOT_FENCE.get(),
                DABlocks.CRUDEROOT_FENCE.get(),
                DABlocks.CONBERRY_FENCE.get(),
                DABlocks.SUNROOT_FENCE.get()
        );

        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                DABlocks.MUDDY_YAGROOT_ROOTS.get(),
                DABlocks.GOLDEN_GRASS_BLOCK.get(),
                DABlocks.GOLDEN_DIRT_PATH.get()
        );
        tag(BlockTags.MINEABLE_WITH_HOE).add(
                DABlocks.YAGROOT_LEAVES.get(),
                DABlocks.FLOWERING_ROSEROOT_LEAVES.get(),
                DABlocks.ROSEROOT_LEAVES.get(),
                DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(),
                DABlocks.BLUE_ROSEROOT_LEAVES.get(),
                DABlocks.CRUDEROOT_LEAVES.get(),
                DABlocks.CLOUDBLOOM_CARPET.get(),
                DABlocks.AERGLOW_BLOSSOM_BLOCK.get(),
                DABlocks.CONBERRY_LEAVES.get(),
                DABlocks.SUNROOT_LEAVES.get(),
                DABlocks.SUNROOT_HANGER.get()
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                DABlocks.LIGHTCAP_MUSHROOM_BLOCK.get(),
                DABlocks.ROSEROOT_WOOD.get(),
                DABlocks.STRIPPED_ROSEROOT_WOOD.get(),
                DABlocks.STRIPPED_ROSEROOT_LOG.get(),
                DABlocks.ROSEROOT_LOG.get(),
                DABlocks.ROSEROOT_PLANKS.get(),
                DABlocks.ROSEROOT_DOOR.get(),
                DABlocks.ROSEROOT_TRAPDOOR.get(),
                DABlocks.ROSEROOT_WALL.get(),
                DABlocks.STRIPPED_ROSEROOT_WALL.get(),
                DABlocks.ROSEROOT_SLAB.get(),
                DABlocks.ROSEROOT_STAIRS.get(),
                DABlocks.ROSEROOT_BUTTON.get(),
                DABlocks.ROSEROOT_PRESSURE_PLATE.get(),
                DABlocks.ROSEROOT_FENCE.get(),
                DABlocks.ROSEROOT_SAPLING.get(),
                DABlocks.YAGROOT_WOOD.get(),
                DABlocks.ROSEROOT_FENCE_GATE.get(),
                DABlocks.ROSEROOT_SIGN.get(),
                DABlocks.ROSEROOT_WALL_SIGN.get(),
                DABlocks.BLUE_SQUASH.get(),
                DABlocks.GREEN_SQUASH.get(),
                DABlocks.PURPLE_SQUASH.get(),

                DABlocks.STRIPPED_YAGROOT_WOOD.get(),
                DABlocks.STRIPPED_YAGROOT_LOG.get(),
                DABlocks.YAGROOT_LOG.get(),
                DABlocks.YAGROOT_PLANKS.get(),
                DABlocks.YAGROOT_DOOR.get(),
                DABlocks.YAGROOT_TRAPDOOR.get(),
                DABlocks.YAGROOT_WALL.get(),
                DABlocks.YAGROOT_ROOTS.get(),
                DABlocks.STRIPPED_YAGROOT_WALL.get(),
                DABlocks.YAGROOT_SLAB.get(),
                DABlocks.YAGROOT_STAIRS.get(),
                DABlocks.YAGROOT_BUTTON.get(),
                DABlocks.YAGROOT_PRESSURE_PLATE.get(),
                DABlocks.YAGROOT_FENCE.get(),
                DABlocks.YAGROOT_SAPLING.get(),
                DABlocks.YAGROOT_FENCE_GATE.get(),
                DABlocks.YAGROOT_SIGN.get(),
                DABlocks.YAGROOT_WALL_SIGN.get(),

                DABlocks.CRUDEROOT_WOOD.get(),
                DABlocks.STRIPPED_CRUDEROOT_WOOD.get(),
                DABlocks.STRIPPED_CRUDEROOT_LOG.get(),
                DABlocks.CRUDEROOT_LOG.get(),
                DABlocks.CRUDEROOT_PLANKS.get(),
                DABlocks.CRUDEROOT_DOOR.get(),
                DABlocks.CRUDEROOT_TRAPDOOR.get(),
                DABlocks.CRUDEROOT_WALL.get(),
                DABlocks.STRIPPED_CRUDEROOT_WALL.get(),
                DABlocks.CRUDEROOT_SLAB.get(),
                DABlocks.CRUDEROOT_STAIRS.get(),
                DABlocks.CRUDEROOT_BUTTON.get(),
                DABlocks.CRUDEROOT_PRESSURE_PLATE.get(),
                DABlocks.CRUDEROOT_FENCE.get(),
                DABlocks.CRUDEROOT_SAPLING.get(),
                DABlocks.CRUDEROOT_FENCE_GATE.get(),
                DABlocks.CRUDEROOT_SIGN.get(),
                DABlocks.CRUDEROOT_WALL_SIGN.get(),

                DABlocks.CONBERRY_WOOD.get(),
                DABlocks.STRIPPED_CONBERRY_WOOD.get(),
                DABlocks.STRIPPED_CONBERRY_LOG.get(),
                DABlocks.CONBERRY_LOG.get(),
                DABlocks.CONBERRY_PLANKS.get(),
                DABlocks.CONBERRY_DOOR.get(),
                DABlocks.CONBERRY_TRAPDOOR.get(),
                DABlocks.CONBERRY_WALL.get(),
                DABlocks.STRIPPED_CONBERRY_WALL.get(),
                DABlocks.CONBERRY_SLAB.get(),
                DABlocks.CONBERRY_STAIRS.get(),
                DABlocks.CONBERRY_BUTTON.get(),
                DABlocks.CONBERRY_PRESSURE_PLATE.get(),
                DABlocks.CONBERRY_FENCE.get(),
                DABlocks.CONBERRY_SAPLING.get(),
                DABlocks.CONBERRY_FENCE_GATE.get(),
                DABlocks.CONBERRY_SIGN.get(),
                DABlocks.CONBERRY_WALL_SIGN.get(),

                DABlocks.SUNROOT_WOOD.get(),
                DABlocks.STRIPPED_SUNROOT_WOOD.get(),
                DABlocks.STRIPPED_SUNROOT_LOG.get(),
                DABlocks.SUNROOT_LOG.get(),
                DABlocks.SUNROOT_PLANKS.get(),
                DABlocks.SUNROOT_DOOR.get(),
                DABlocks.SUNROOT_TRAPDOOR.get(),
                DABlocks.SUNROOT_WALL.get(),
                DABlocks.STRIPPED_SUNROOT_WALL.get(),
                DABlocks.SUNROOT_SLAB.get(),
                DABlocks.SUNROOT_STAIRS.get(),
                DABlocks.SUNROOT_BUTTON.get(),
                DABlocks.SUNROOT_PRESSURE_PLATE.get(),
                DABlocks.SUNROOT_FENCE.get(),
                DABlocks.SUNROOT_SAPLING.get(),
                DABlocks.SUNROOT_FENCE_GATE.get(),
                DABlocks.SUNROOT_SIGN.get(),
                DABlocks.SUNROOT_WALL_SIGN.get(),

                DABlocks.ROSEROOT_HANGING_SIGN.get(),
                DABlocks.YAGROOT_HANGING_SIGN.get(),
                DABlocks.CRUDEROOT_HANGING_SIGN.get(),
                DABlocks.CONBERRY_HANGING_SIGN.get(),
                DABlocks.SUNROOT_HANGING_SIGN.get(),
                DABlocks.ROSEROOT_WALL_HANGING_SIGN.get(),
                DABlocks.YAGROOT_WALL_HANGING_SIGN.get(),
                DABlocks.CRUDEROOT_WALL_HANGING_SIGN.get(),
                DABlocks.CONBERRY_WALL_HANGING_SIGN.get(),
                DABlocks.SUNROOT_WALL_HANGING_SIGN.get()
        );

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                DABlocks.BIG_HOLYSTONE_BRICKS.get(),
                DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get(),
                DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get(),
                DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(),

                DABlocks.COBBLED_ASETERITE.get(),
                DABlocks.COBBLED_ASETERITE_SLAB.get(),
                DABlocks.COBBLED_ASETERITE_STAIRS.get(),
                DABlocks.COBBLED_ASETERITE_WALL.get(),

                DABlocks.ASETERITE.get(),
                DABlocks.ASETERITE_SLAB.get(),
                DABlocks.ASETERITE_STAIRS.get(),
                DABlocks.ASETERITE_WALL.get(),

                DABlocks.POLISHED_ASETERITE.get(),
                DABlocks.POLISHED_ASETERITE_STAIRS.get(),
                DABlocks.POLISHED_ASETERITE_SLAB.get(),

                DABlocks.ASETERITE_BRICKS.get(),
                DABlocks.ASETERITE_BRICKS_SLAB.get(),
                DABlocks.ASETERITE_BRICKS_STAIRS.get(),
                DABlocks.ASETERITE_BRICKS_WALL.get(),

                DABlocks.RAW_CLORITE.get(),
                DABlocks.RAW_CLORITE_SLAB.get(),
                DABlocks.RAW_CLORITE_STAIRS.get(),

                DABlocks.CLORITE.get(),
                DABlocks.POLISHED_CLORITE.get(),
                DABlocks.CLORITE_STAIRS.get(),
                DABlocks.POLISHED_CLORITE_STAIRS.get(),
                DABlocks.CLORITE_SLAB.get(),
                DABlocks.POLISHED_CLORITE_SLAB.get(),
                DABlocks.CLORITE_WALL.get(),
                DABlocks.RAW_CLORITE_WALL.get(),
                DABlocks.CLORITE_PILLAR.get(),

                DABlocks.SKYJADE_ORE.get(),
                DABlocks.SKYJADE_BLOCK.get(),
                DABlocks.STRATUS_BLOCK.get(),

                DABlocks.HOLYSTONE_TILES.get(),
                DABlocks.HOLYSTONE_TILE_SLAB.get(),
                DABlocks.HOLYSTONE_TILE_STAIRS.get(),
                DABlocks.HOLYSTONE_TILE_WALL.get(),

                DABlocks.BIG_HOLYSTONE_BRICKS.get(),
                DABlocks.CHISELED_HOLYSTONE.get(),
                DABlocks.HOLYSTONE_PILLAR.get(),
                DABlocks.HOLYSTONE_PILLAR_UP.get(),
                DABlocks.HOLYSTONE_PILLAR_DOWN.get(),


                DABlocks.MOSSY_HOLYSTONE_BRICKS.get(),
                DABlocks.MOSSY_HOLYSTONE_BRICK_SLAB.get(),
                DABlocks.MOSSY_HOLYSTONE_BRICK_STAIRS.get(),
                DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get(),

                DABlocks.MOSSY_HOLYSTONE_TILES.get(),
                DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get(),
                DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get(),
                DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(),

                DABlocks.GILDED_HOLYSTONE_BRICKS.get(),
                DABlocks.GILDED_HOLYSTONE_BRICK_SLAB.get(),
                DABlocks.GILDED_HOLYSTONE_BRICK_STAIRS.get(),
                DABlocks.GILDED_HOLYSTONE_BRICK_WALL.get(),
                DABlocks.GILDED_HOLYSTONE_TILES.get(),
                DABlocks.GILDED_HOLYSTONE_TILE_SLAB.get(),
                DABlocks.GILDED_HOLYSTONE_TILE_STAIRS.get(),
                DABlocks.GILDED_HOLYSTONE_TILE_WALL.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_SLAB.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_STAIRS.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_WALL.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_SLAB.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_STAIRS.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get(),

                DABlocks.NIMBUS_PILLAR.get(),
                DABlocks.LIGHT_NIMBUS_PILLAR.get(),
                DABlocks.NIMBUS_STONE.get(),
                DABlocks.LIGHT_NIMBUS_STONE.get(),
                DABlocks.NIMBUS_STAIRS.get(),
                DABlocks.NIMBUS_SLAB.get(),
                DABlocks.NIMBUS_WALL.get()
        );

        tag(BlockTags.SNAPS_GOAT_HORN).add(
                DABlocks.ROSEROOT_LOG.get(),
                DABlocks.ROSEROOT_WOOD.get(),
                DABlocks.STRIPPED_ROSEROOT_LOG.get(),
                DABlocks.STRIPPED_ROSEROOT_WOOD.get(),
                DABlocks.YAGROOT_LOG.get(),
                DABlocks.YAGROOT_WOOD.get(),
                DABlocks.STRIPPED_YAGROOT_LOG.get(),
                DABlocks.STRIPPED_YAGROOT_WOOD.get(),
                DABlocks.CRUDEROOT_LOG.get(),
                DABlocks.CRUDEROOT_WOOD.get(),
                DABlocks.STRIPPED_CRUDEROOT_LOG.get(),
                DABlocks.STRIPPED_CRUDEROOT_WOOD.get(),
                DABlocks.CONBERRY_LOG.get(),
                DABlocks.CONBERRY_WOOD.get(),
                DABlocks.STRIPPED_CONBERRY_LOG.get(),
                DABlocks.STRIPPED_CONBERRY_WOOD.get(),
                DABlocks.SUNROOT_LOG.get(),
                DABlocks.SUNROOT_WOOD.get(),
                DABlocks.STRIPPED_SUNROOT_LOG.get(),
                DABlocks.STRIPPED_SUNROOT_WOOD.get(),
                DABlocks.SKYJADE_ORE.get(),
                DABlocks.RAW_CLORITE.get(),
                DABlocks.ASETERITE.get(),
                DABlocks.COBBLED_ASETERITE.get()
        );

        tag(BlockTags.REPLACEABLE_BY_TREES).add(
                DABlocks.MINI_GOLDEN_GRASS.get(),
                DABlocks.MEDIUM_GOLDEN_GRASS.get(),
                DABlocks.SHORT_GOLDEN_GRASS.get(),
                DABlocks.TALL_GOLDEN_GRASS.get(),
                DABlocks.GOLDEN_ASPESS.get(),
                DABlocks.GOLDEN_FLOWER.get(),
                DABlocks.GOLDEN_VINES_PLANT.get(),
                DABlocks.GOLDEN_VINES.get(),
                DABlocks.ENCHANTED_BLOSSOM.get(),
                DABlocks.RADIANT_ORCHID.get(),
                DABlocks.SKY_TULIPS.get(),
                DABlocks.IASPOVE.get(),
                DABlocks.FEATHER_GRASS.get(),
                DABlocks.TALL_FEATHER_GRASS.get(),
                DABlocks.TALL_GLOWING_GRASS.get()
        );


        tag(BlockTags.SAPLINGS).add(
                DABlocks.SUNROOT_SAPLING.get(),
                DABlocks.BLUE_ROSEROOT_SAPLING.get(),
                DABlocks.CONBERRY_SAPLING.get(),
                DABlocks.CRUDEROOT_SAPLING.get(),
                DABlocks.ROSEROOT_SAPLING.get(),
                DABlocks.YAGROOT_SAPLING.get()
        );

        tag(DATags.Blocks.HAS_GLOWING_SPORES).add(
                DABlocks.GLOWING_VINE.get(),
                DABlocks.TALL_GLOWING_GRASS.get()
        );

        tag(AetherTags.Blocks.DUNGEON_BLOCKS).add(
                DABlocks.NIMBUS_STONE.get(),
                DABlocks.LIGHT_NIMBUS_STONE.get(),
                DABlocks.NIMBUS_PILLAR.get(),
                DABlocks.LIGHT_NIMBUS_PILLAR.get()
        );

        tag(AetherTags.Blocks.TRAPPED_DUNGEON_BLOCKS).add(
                DABlocks.TRAPPED_NIMBUS_STONE.get(),
                DABlocks.TRAPPED_SKYROOT_PLANKS.get(),
                DABlocks.TRAPPED_LIGHT_NIMBUS_STONE.get(),
                DABlocks.TRAPPED_NIMBUS_PILLAR.get(),
                DABlocks.TRAPPED_LIGHT_NIMBUS_PILLAR.get()
        );


        tag(AetherTags.Blocks.LOCKED_DUNGEON_BLOCKS).add(
                DABlocks.LOCKED_NIMBUS_STONE.get(),
                DABlocks.LOCKED_LIGHT_NIMBUS_STONE.get(),
                DABlocks.LOCKED_NIMBUS_PILLAR.get(),
                DABlocks.LOCKED_LIGHT_NIMBUS_PILLAR.get(),
                DABlocks.LOCKED_SKYROOT_PLANKS.get()
        );
    }
}