package teamrazor.deepaether.datagen.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        tag(DATags.Blocks.CAN_GOLDEN_VINES_SURVIVE_ON).add(
                AetherBlocks.QUICKSOIL.get(),
                Blocks.SAND
        );
    }
}