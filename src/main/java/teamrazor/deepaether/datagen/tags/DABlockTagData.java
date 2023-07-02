package teamrazor.deepaether.datagen.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DABlockTagData extends BlockTagsProvider {

    public DABlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, DeepAetherMod.MODID, helper);
    }
    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether Block Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(AetherTags.Blocks.AETHER_ANIMALS_SPAWNABLE_ON).add(
                DABlocks.GOLDEN_GRASS_BLOCK.get()
        );
        tag(DATags.Blocks.CAN_GOLDEN_VINES_SURVIVE_ON).add(
                AetherBlocks.QUICKSOIL.get(),
                Blocks.SAND
        );
        tag(BlockTags.BEACON_BASE_BLOCKS).add(
                DABlocks.SKYJADE_BLOCK.get(),
                DABlocks.STRATUS_BLOCK.get()
        );

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
                DABlocks.STRIPPED_CONBERRY_WOOD.get()
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
                DABlocks.STRIPPED_CONBERRY_WOOD.get()
        );
        tag(BlockTags.ALL_SIGNS).add(
                DABlocks.ROSEROOT_SIGN.get(),
                DABlocks.YAGROOT_SIGN.get(),
                DABlocks.CRUDEROOT_SIGN.get(),
                DABlocks.CONBERRY_SIGN.get()
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
                DABlocks.SKYROOT_WALL.get(),
                DABlocks.CLORITE_WALL.get(),
                DABlocks.ASETERITE_WALL.get(),
                DABlocks.AETHER_MUD_BRICKS_WALL.get(),
                DABlocks.HOLYSTONE_TILE_WALL.get(),
                DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get())
                .addOptional(DABlocks.GILDED_HOLYSTONE_TILE_WALL.getId())
                .addOptional(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.getId())
                .addOptional(DABlocks.ROSEROOT_LOG_WALL.getId())
                .addOptional(DABlocks.STRIPPED_ROSEROOT_LOG_WALL.getId())
                .addOptional(DABlocks.YAGROOT_LOG_WALL.getId())
                .addOptional(DABlocks.STRIPPED_YAGROOT_LOG_WALL.getId())
                .addOptional(DABlocks.CRUDEROOT_LOG_WALL.getId())
                .addOptional(DABlocks.STRIPPED_CRUDEROOT_LOG_WALL.getId())
                .addOptional(DABlocks.CONBERRY_LOG_WALL.getId())
                .addOptional(DABlocks.STRIPPED_CONBERRY_LOG_WALL.getId()

                );
        tag(BlockTags.FENCE_GATES).add(
                DABlocks.ROSEROOT_FENCE_GATE.get(),
                DABlocks.YAGROOT_FENCE_GATE.get(),
                DABlocks.CRUDEROOT_FENCE_GATE.get(),
                DABlocks.CONBERRY_FENCE_GATE.get()
        );
        tag(BlockTags.LEAVES).add(
                DABlocks.ROSEROOT_LEAVES.get(),
                DABlocks.BLUE_ROSEROOT_LEAVES.get(),
                DABlocks.FLOWERING_ROSEROOT_LEAVES.get(),
                DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(),
                DABlocks.YAGROOT_LEAVES.get(),
                DABlocks.CRUDEROOT_LEAVES.get(),
                DABlocks.CONBERRY_LEAVES.get(),
                DABlocks.YAGROOT_ROOTS.get()
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
                DABlocks.GOLDEN_VINES.get(),
                DABlocks.GOLDEN_VINES_PLANT.get()
        );

        tag(BlockTags.DIRT).add(
                DABlocks.AETHER_MUD.get(),
                DABlocks.AETHER_MOSS_BLOCK.get(),
                DABlocks.GOLDEN_GRASS_BLOCK.get()
        );
        tag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH).add(
                DABlocks.AETHER_MOSS_BLOCK.get(),
                DABlocks.AETHER_MOSS_CARPET.get(),
                DABlocks.YAGROOT_LOG.get(),
                DABlocks.AETHER_MUD.get(),
                DABlocks.MUDDY_YAGROOT_ROOTS.get(),
                DABlocks.TALL_AETHER_CATTAILS.get(),
                DABlocks.AETHER_CATTAILS.get()
        );
        tag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH).add(
                DABlocks.AETHER_MOSS_BLOCK.get(),
                DABlocks.AETHER_MOSS_CARPET.get()
        );
        tag(BlockTags.SCULK_REPLACEABLE).add(
                DABlocks.ASETERITE.get(),
                DABlocks.CLORITE.get()
        );
        tag(BlockTags.WOODEN_FENCES).add(
                DABlocks.ROSEROOT_FENCE.get(),
                DABlocks.YAGROOT_FENCE.get(),
                DABlocks.CRUDEROOT_FENCE.get(),
                DABlocks.CONBERRY_FENCE.get()
        );

        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                DABlocks.AETHER_MUD.get(),
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
                DABlocks.AETHER_MOSS_BLOCK.get(),
                DABlocks.AETHER_MOSS_CARPET.get(),
                DABlocks.AERGLOW_PETAL_BLOCK.get(),
                DABlocks.CONBERRY_LEAVES.get()
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(
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
                DABlocks.CONBERRY_WALL_SIGN.get()
        );
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                DABlocks.ASETERITE.get(),
                DABlocks.POLISHED_ASETERITE.get(),
                DABlocks.ASETERITE_STAIRS.get(),
                DABlocks.POLISHED_ASETERITE_STAIRS.get(),
                DABlocks.ASETERITE_SLAB.get(),
                DABlocks.POLISHED_ASETERITE_SLAB.get(),
                DABlocks.ASETERITE_WALL.get(),

                DABlocks.CLORITE.get(),
                DABlocks.POLISHED_CLORITE.get(),
                DABlocks.CLORITE_STAIRS.get(),
                DABlocks.POLISHED_CLORITE_STAIRS.get(),
                DABlocks.CLORITE_SLAB.get(),
                DABlocks.POLISHED_CLORITE_SLAB.get(),
                DABlocks.CLORITE_WALL.get(),
                DABlocks.CLORITE_PILLAR.get(),

                DABlocks.SKYJADE_ORE.get(),
                DABlocks.SKYJADE_BLOCK.get(),
                DABlocks.STRATUS_BLOCK.get(),

                DABlocks.HOLYSTONE_TILES.get(),
                DABlocks.HOLYSTONE_TILE_SLAB.get(),
                DABlocks.HOLYSTONE_TILE_STAIRS.get(),
                DABlocks.HOLYSTONE_TILE_WALL.get(),

                DABlocks.MOSSY_HOLYSTONE_TILES.get(),
                DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get(),
                DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get(),
                DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(),

                DABlocks.PACKED_AETHER_MUD.get(),
                DABlocks.AETHER_MUD_BRICKS.get(),
                DABlocks.AETHER_MUD_BRICKS_SLAB.get(),
                DABlocks.AETHER_MUD_BRICKS_STAIRS.get(),
                DABlocks.AETHER_MUD_BRICKS_WALL.get())

                .addOptional(DABlocks.GILDED_HOLYSTONE_TILES.getId())
                .addOptional(DABlocks.GILDED_HOLYSTONE_TILE_SLAB.getId())
                .addOptional(DABlocks.GILDED_HOLYSTONE_TILE_STAIRS.getId())
                .addOptional(DABlocks.GILDED_HOLYSTONE_TILE_WALL.getId())

                .addOptional(DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.getId())
                .addOptional(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_SLAB.getId())
                .addOptional(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_STAIRS.getId())
                .addOptional(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.getId()

                );
    }
}