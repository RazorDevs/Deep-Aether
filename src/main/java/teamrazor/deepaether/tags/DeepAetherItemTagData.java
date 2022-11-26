package teamrazor.deepaether.tags;

import com.gildedgames.aether.AetherTags;
import com.gildedgames.aether.block.AetherBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DeepAetherModItems;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;

public class DeepAetherItemTagData extends ItemTagsProvider {
    public DeepAetherItemTagData(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, DeepAetherMod.MODID, existingFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether Item Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {/*
        tag(DeepAetherTags.Items.SKYJADE_TOOLS).add(
                DeepAetherModItems.SKYJADE_TOOLS_PICKAXE.get(),
                DeepAetherModItems.SKYJADE_TOOLS_AXE.get(),
                DeepAetherModItems.SKYJADE_TOOLS_SHOVEL.get(),
                DeepAetherModItems.SKYJADE_TOOLS_HOE.get());
        tag(DeepAetherTags.Items.SKYJADE_WEAPONS).add(DeepAetherModItems.SKYJADE_TOOLS_SWORD.get());*/
    }


    @Nonnull
    protected TagsProvider.TagAppender<Item> tag(@Nonnull TagKey<Item> tag) {
        return super.tag(tag);
    }
}
