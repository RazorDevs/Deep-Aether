package teamrazor.deepaether.datagen.tags;

import net.minecraft.core.HolderLookup;

import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;

import javax.annotation.Nonnull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DAItemTagData extends ItemTagsProvider {
    public DAItemTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, TagsProvider<Block> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, DeepAetherMod.MODID, helper);
    }


    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether Item Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(DATags.Items.CRAFTS_ROSEROOT_PLANKS).add(
                DABlocks.ROSE_LOG.get().asItem(),
                DABlocks.ROSE_WOOD.get().asItem(),
                DABlocks.STRIPPED_ROSE_LOG.get().asItem(),
                DABlocks.STRIPPED_ROSE_WOOD.get().asItem()
        );
        tag(DATags.Items.CRAFTS_YAGROOT_PLANKS).add(
                DABlocks.YAGROOT_LOG.get().asItem(),
                DABlocks.YAGROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_YAGROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_YAGROOT_WOOD.get().asItem()
        );
        tag(DATags.Items.CRAFTS_CRUDEROOT_PLANKS).add(
                DABlocks.CRUDEROOT_LOG.get().asItem(),
                DABlocks.CRUDEROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_CRUDEROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_CRUDEROOT_WOOD.get().asItem()
        );
    }
}
