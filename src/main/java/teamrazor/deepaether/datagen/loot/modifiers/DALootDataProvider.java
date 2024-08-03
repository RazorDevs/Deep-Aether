package teamrazor.deepaether.datagen.loot.modifiers;

import com.aetherteam.aether.loot.AetherLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

import java.util.List;

public class DALootDataProvider extends GlobalLootModifierProvider {

    public DALootDataProvider(PackOutput output)
    {
        super(output, DeepAether.MODID);
    }

    @Override
    protected void start() {
        add("silver_loot_modifiers", new DAAddDungeonLootModifierNoReplacements(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(DAItems.SKYJADE.get(), 1), 90),
                        WeightedEntry.wrap(new ItemStack(DAItems.SKYJADE.get(), 2), 10)
                        ),
                100,
                0.8f
        ));

        add("silver_loot_reward_modifiers", new DAAddDungeonLootModifierNoReplacements(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON_REWARD).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(DAItems.SKYJADE.get(), 2), 90),
                        WeightedEntry.wrap(new ItemStack(DAItems.STRATUS_INGOT.get(), 1), 5),
                        WeightedEntry.wrap(new ItemStack(DABlocks.STERLING_AERCLOUD.get(), 1), 10),
                        WeightedEntry.wrap(new ItemStack(DAItems.STRATUS_SMITHING_TEMPLATE.get(), 1), 25)
                ),
                130,
                0.5f
        ));

        add("gold_loot_modifiers", new DAAddDungeonLootModifierNoReplacements(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.GOLD_DUNGEON_REWARD).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(DAItems.SKYJADE.get(), 2), 60),
                        WeightedEntry.wrap(new ItemStack(DAItems.SKYJADE.get(), 5), 20),
                        WeightedEntry.wrap(new ItemStack(DAItems.STRATUS_INGOT.get(), 1), 10),
                        WeightedEntry.wrap(new ItemStack(DABlocks.STERLING_AERCLOUD.get(), 2), 5),
                        WeightedEntry.wrap(new ItemStack(DABlocks.STERLING_AERCLOUD.get(), 1), 15)
                ),
                110,
                0.55f
        ));

        add("gold_loot_stratus_upgrade", new DAAddFlatDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.GOLD_DUNGEON_REWARD).build() },
                    new ItemStack(DAItems.STRATUS_SMITHING_TEMPLATE.get(), 1),
                0.0f
        ));
    }
}