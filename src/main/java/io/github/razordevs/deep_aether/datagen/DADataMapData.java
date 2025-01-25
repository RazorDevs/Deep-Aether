package io.github.razordevs.deep_aether.datagen;

import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class DADataMapData extends DataMapProvider {
    public DADataMapData(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void gather() {
        var compostables = this.builder(NeoForgeDataMaps.COMPOSTABLES);
        this.addCompost(compostables, DABlocks.ROSEROOT_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.BLUE_ROSEROOT_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.FLOWERING_ROSEROOT_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.YAGROOT_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.CRUDEROOT_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.CLOUDBLOOM_CARPET.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.ROSEROOT_SAPLING.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.BLUE_ROSEROOT_SAPLING.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.YAGROOT_SAPLING.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.CRUDEROOT_SAPLING.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.CONBERRY_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.CONBERRY_SAPLING.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.SUNROOT_LEAVES.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.SUNROOT_SAPLING.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.YAGROOT_ROOTS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.YAGROOT_VINE.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.GLOWING_VINE.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.SUNROOT_HANGER.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.AERGLOW_BLOSSOM_BLOCK.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.MINI_GOLDEN_GRASS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.SHORT_GOLDEN_GRASS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.MEDIUM_GOLDEN_GRASS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.TALL_GOLDEN_GRASS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.RADIANT_ORCHID.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.AERLAVENDER.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.TALL_AERLAVENDER.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.AETHER_CATTAILS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.TALL_AETHER_CATTAILS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.GOLDEN_FLOWER.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.SKY_TULIPS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.IASPOVE.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.ENCHANTED_BLOSSOM.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.ECHAISY.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.LIGHTCAP_MUSHROOMS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.GOLDEN_ASPESS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.FEATHER_GRASS.get().asItem(), 0.3F);
        this.addCompost(compostables, DABlocks.TALL_FEATHER_GRASS.get().asItem(), 0.3F);
        this.addCompost(compostables, DAItems.AERGLOW_BLOSSOM.get(), 0.1F);
        this.addCompost(compostables, DAItems.GOLDEN_BERRIES.get(), 0.2F);
        this.addCompost(compostables, DAItems.GOLDEN_GRASS_SEEDS.get(), 0.1F);
        this.addCompost(compostables, DAItems.SQUASH_SEEDS.get(), 0.1F);
        this.addCompost(compostables, DAItems.CLOUDBLOOM_BOUQUET.get(), 0.1F);
    }

    private void addCompost(DataMapProvider.Builder<Compostable, Item> map, ItemLike item, float chance) {
        map.add(item.asItem().builtInRegistryHolder(), new Compostable(chance), false);
    }
}