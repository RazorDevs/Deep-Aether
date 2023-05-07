package teamrazor.deepaether.datagen.tags;

import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DAItemTagData extends ItemTagsProvider {
    public DAItemTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
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
                DABlocks.ROSEROOT_LOG.get().asItem(),
                DABlocks.ROSEROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_ROSEROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_ROSEROOT_WOOD.get().asItem()
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
        tag(DATags.Items.CRAFTS_CONBERRY_PLANKS).add(
                DABlocks.CONBERRY_LOG.get().asItem(),
                DABlocks.CONBERRY_WOOD.get().asItem(),
                DABlocks.STRIPPED_CONBERRY_LOG.get().asItem(),
                DABlocks.STRIPPED_CONBERRY_WOOD.get().asItem()
        );
        tag(AetherTags.Items.SKYROOT_STICK_CRAFTING).add(
                DABlocks.ROSEROOT_PLANKS.get().asItem(),
                DABlocks.YAGROOT_PLANKS.get().asItem(),
                DABlocks.CRUDEROOT_PLANKS.get().asItem(),
                DABlocks.CONBERRY_PLANKS.get().asItem()
        );
        tag(AetherTags.Items.SKYROOT_TOOL_CRAFTING).add(
                DABlocks.ROSEROOT_PLANKS.get().asItem(),
                DABlocks.YAGROOT_PLANKS.get().asItem(),
                DABlocks.CRUDEROOT_PLANKS.get().asItem(),
                DABlocks.CONBERRY_PLANKS.get().asItem()
        );
        tag(AetherTags.Items.SLIDER_DAMAGING_ITEMS).add(
                DAItems.SKYJADE_TOOLS_PICKAXE.get().asItem(),
                DAItems.CLOUDIUM_PICKAXE.get().asItem()
        );
        tag(AetherTags.Items.TREATED_AS_AETHER_ITEM).add(
                DAItems.SKYJADE_TOOLS_SWORD.get().asItem(),
                DAItems.SKYJADE_TOOLS_PICKAXE.get().asItem(),
                DAItems.SKYJADE_TOOLS_AXE.get().asItem(),
                DAItems.SKYJADE_TOOLS_SHOVEL.get().asItem(),
                DAItems.SKYJADE_TOOLS_HOE.get().asItem(),
                DAItems.CLOUDIUM_SWORD.get().asItem(),
                DAItems.CLOUDIUM_PICKAXE.get().asItem(),
                DAItems.CLOUDIUM_AXE.get().asItem(),
                DAItems.CLOUDIUM_SHOVEL.get().asItem(),
                DAItems.CLOUDIUM_HOE.get().asItem()
        );
        tag(ItemTags.SMALL_FLOWERS).add(
                DABlocks.AERLAVENDER.get().asItem(),
                DABlocks.AETHER_CATTAILS.get().asItem(),
                DABlocks.GOLDEN_FLOWER.get().asItem(),
                DABlocks.RADIANT_ORCHID.get().asItem()
        );
    }

}
