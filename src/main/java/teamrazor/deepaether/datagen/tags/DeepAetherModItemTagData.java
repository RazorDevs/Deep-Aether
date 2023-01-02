package teamrazor.deepaether.datagen.tags;

import com.gildedgames.aether.Aether;
import net.minecraft.core.HolderLookup;

import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DeepAetherModBlocks;

import javax.annotation.Nonnull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DeepAetherModItemTagData extends ItemTagsProvider {
    public DeepAetherModItemTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, TagsProvider<Block> blockTags, @Nullable ExistingFileHelper helper) {
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
        tag(DeepAetherModTags.Items.CRAFTS_ROSEROOT_PLANKS).add(
                DeepAetherModBlocks.ROSE_LOG.get().asItem(),
                DeepAetherModBlocks.ROSE_WOOD.get().asItem(),
                DeepAetherModBlocks.STRIPPED_ROSE_LOG.get().asItem(),
                DeepAetherModBlocks.STRIPPED_ROSE_WOOD.get().asItem()
        );
        tag(DeepAetherModTags.Items.CRAFTS_YAGROOT_PLANKS).add(
                DeepAetherModBlocks.YAGROOT_LOG.get().asItem(),
                DeepAetherModBlocks.YAGROOT_WOOD.get().asItem(),
                DeepAetherModBlocks.STRIPPED_YAGROOT_LOG.get().asItem(),
                DeepAetherModBlocks.STRIPPED_YAGROOT_WOOD.get().asItem()
        );
        tag(DeepAetherModTags.Items.CRAFTS_CRUDEROOT_PLANKS).add(
                DeepAetherModBlocks.CRUDEROOT_LOG.get().asItem(),
                DeepAetherModBlocks.CRUDEROOT_WOOD.get().asItem(),
                DeepAetherModBlocks.STRIPPED_CRUDEROOT_LOG.get().asItem(),
                DeepAetherModBlocks.STRIPPED_CRUDEROOT_WOOD.get().asItem()
        );
    }
}
