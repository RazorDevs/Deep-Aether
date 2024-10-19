package io.github.razordevs.deepaether.deepaether.datagen.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class DALootTableData {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, DALoot.IMMUTABLE_LOOT_TABLES, List.of(
                new LootTableProvider.SubProviderEntry(DABlockLoot::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(DAChestLoot::new, LootContextParamSets.CHEST)
        ));
    }
}

