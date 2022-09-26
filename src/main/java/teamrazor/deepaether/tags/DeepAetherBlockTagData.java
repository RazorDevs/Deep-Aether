package teamrazor.deepaether.tags;

import com.gildedgames.aether.block.AetherBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DeepAetherBlockTagData extends BlockTagsProvider {

    public DeepAetherBlockTagData(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, DeepAetherMod.MODID, existingFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "DeepAether Block Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {
        tag(BlockTags.CONVERTABLE_TO_MUD).remove(AetherBlocks.AETHER_DIRT.get());
    }
}
