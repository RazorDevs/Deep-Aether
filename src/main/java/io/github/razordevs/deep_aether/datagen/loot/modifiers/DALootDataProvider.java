package io.github.razordevs.deep_aether.datagen.loot.modifiers;

import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.loot.AetherLoot;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DALootDataProvider extends GlobalLootModifierProvider {

    public DALootDataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, DeepAether.MODID);
    }

    @Override
    protected void start() {
        add("silver_loot_modifiers", new DAAddDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON.location()).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(DAItems.SKYJADE.get(), 1), 90),
                        WeightedEntry.wrap(new ItemStack(DAItems.SKYJADE.get(), 2), 10)
                        ),
                100,
                0.0f
        ));

        add("bronze_loot_reward_modifiers", new DAAddDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON_REWARD.location()).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(DAItems.MUSIC_DISC_ATTA.get(), 1), 40)
                ),
                100,
                0.0f
        ));

        add("silver_loot_reward_modifiers", new DAAddDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD.location()).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(DAItems.SKYJADE.get(), 2), 45),
                        WeightedEntry.wrap(new ItemStack(DAItems.STRATUS_INGOT.get(), 1), 10),
                        WeightedEntry.wrap(new ItemStack(DABlocks.STERLING_AERCLOUD.get(), 1), 15),
                        WeightedEntry.wrap(new ItemStack(DAItems.STRATUS_SMITHING_TEMPLATE.get(), 1), 25),
                        WeightedEntry.wrap(new ItemStack(DAItems.MUSIC_DISC_FAENT.get(), 1), 40)
                ),
                100,
                0.0f
        ));

        add("gold_loot_modifiers", new DAAddDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.GOLD_DUNGEON_REWARD.location()).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(DAItems.SKYJADE.get(), 2), 50),
                        WeightedEntry.wrap(new ItemStack(DAItems.SKYJADE.get(), 5), 20),
                        WeightedEntry.wrap(new ItemStack(DAItems.STRATUS_INGOT.get(), 1), 10),
                        WeightedEntry.wrap(new ItemStack(DABlocks.STERLING_AERCLOUD.get(), 2), 7),
                        WeightedEntry.wrap(new ItemStack(DABlocks.STERLING_AERCLOUD.get(), 1), 20),
                        WeightedEntry.wrap(new ItemStack(DAItems.MUSIC_DISC_HIMININN.get(), 1), 40)
                ),
                100,
                0.25f
        ));

        add("gold_loot_stratus_upgrade", new DAAddDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.GOLD_DUNGEON_REWARD.location()).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(DAItems.STRATUS_SMITHING_TEMPLATE.get(), 1), 100)
                ),
                100,
                0.0f
        ));

        add("fish_aether", new DAFishingLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(BuiltInLootTables.FISHING.location()).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(DAItems.RAW_AERGLOW_FISH.get(), 1), 100),
                        WeightedEntry.wrap(new ItemStack(DAItems.AERGLOW_BLOSSOM.get(), 1), 12),
                        WeightedEntry.wrap(new ItemStack(AetherItems.SKYROOT_STICK.get(), 1), 12),
                        WeightedEntry.wrap(new ItemStack(DAItems.MUSIC_DISC_ABOVE_THE_RAIN.get(), 1), 11)

                ),
                135,
                0.75f
        ));
    }
}