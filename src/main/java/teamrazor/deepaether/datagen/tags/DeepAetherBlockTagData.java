package teamrazor.deepaether.datagen.tags;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.block.AetherBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;

import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DeepAetherBlockTagData extends BlockTagsProvider {

    public DeepAetherBlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, Aether.MODID, helper);
    }
    @Nonnull
    @Override
    public String getName() {
        return "DeepAether Block Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {

    }
}