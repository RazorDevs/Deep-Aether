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
        tag(BlockTags.ALL_HANGING_SIGNS).add(
                DABlocks.ROSEROOT_WALL_SIGN.get(),
                DABlocks.YAGROOT_WALL_SIGN.get(),
                DABlocks.CRUDEROOT_WALL_SIGN.get(),
                DABlocks.CONBERRY_WALL_SIGN.get()
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
                DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(),
                DABlocks.GILDED_HOLYSTONE_TILE_WALL.get(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get()
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


        if (ModList.get().isLoaded("aether_genesis")) {
            tag(BlockTags.WALLS).add(
                    DABlocks.ROSEROOT_LOG_WALL.get(),
                    DABlocks.STRIPPED_ROSEROOT_LOG_WALL.get(),
                    DABlocks.YAGROOT_LOG_WALL.get(),
                    DABlocks.STRIPPED_YAGROOT_LOG_WALL.get(),
                    DABlocks.CRUDEROOT_LOG_WALL.get(),
                    DABlocks.STRIPPED_CRUDEROOT_LOG_WALL.get(),
                    DABlocks.CONBERRY_LOG_WALL.get(),
                    DABlocks.STRIPPED_CONBERRY_LOG_WALL.get()
            );
        }
    }
}