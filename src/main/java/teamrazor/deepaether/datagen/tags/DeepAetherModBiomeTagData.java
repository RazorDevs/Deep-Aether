package teamrazor.deepaether.datagen.tags;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.world.gen.biomes.DeepAetherModBiomes;


import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DeepAetherModBiomeTagData extends BiomeTagsProvider {
    public DeepAetherModBiomeTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, DeepAetherMod.MODID, helper);
    }
    @Override
    @SuppressWarnings("unchecked")
    public void addTags(HolderLookup.Provider provider) {
        this.tag(AetherTags.Biomes.IS_AETHER).add(
                DeepAetherModBiomes.AETHER_PLAINS
        );
    }
}