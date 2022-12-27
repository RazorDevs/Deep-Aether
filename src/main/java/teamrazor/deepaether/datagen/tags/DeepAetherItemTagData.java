package teamrazor.deepaether.datagen.tags;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.AetherTags;
import com.gildedgames.aether.block.AetherBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;

import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DeepAetherModItems;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DeepAetherItemTagData extends ItemTagsProvider {
    public DeepAetherItemTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, TagsProvider<Block> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, Aether.MODID, helper);
    }


    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether Item Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
    /*    tag(DeepAetherTags.Items.SKYJADE_TOOLS).add(
                DeepAetherModItems.SKYJADE_TOOLS_PICKAXE.get(),
                DeepAetherModItems.SKYJADE_TOOLS_AXE.get(),
                DeepAetherModItems.SKYJADE_TOOLS_SHOVEL.get(),
                DeepAetherModItems.SKYJADE_TOOLS_HOE.get());
        tag(DeepAetherTags.Items.SKYJADE_WEAPONS).add(
                DeepAetherModItems.SKYJADE_TOOLS_SWORD.get());*/
    }
}
