package teamrazor.deepaether.datagen.loot.modifiers;

import com.mojang.serialization.Codec;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DAItems;

import java.util.List;

public class DALootDataProvider extends GlobalLootModifierProvider {

    public DALootDataProvider(PackOutput output)
    {
        super(output, DeepAetherMod.MODID);
    }

    @Override
    protected void start() {

        //add("bronze_loot", new DAAddDungeonLootModifier(
        //        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("aether:chests/dungeon/bronze/bronze_dungeon_reward")).build() },
        //        List.of(
        //                WeightedEntry.wrap(new ItemStack(GraviItems.DIG_STONE.get(), 2), 30),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.DIG_STONE.get()), 70)
        //        ),
        //        100,
        //        0.6f
        //));

        //add("silver_loot", new DAAddDungeonLootModifier(
        //        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("aether:chests/dungeon/silver/silver_dungeon_reward")).build() },
        //        List.of(
        //                WeightedEntry.wrap(new ItemStack(GraviItems.NEPTUNE_AXE.get()), 30),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.NEPTUNE_BATTLEAXE.get()), 30),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.NEPTUNE_CUTLASS.get()), 30),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.NEPTUNE_PICKAXE.get()), 30),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.NEPTUNE_SHOVEL.get()), 30),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.NEPTUNE_RING.get()), 10),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.NEPTUNE_PENDANT.get()), 10),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.VALKYRIE_BATTLEAXE.get()), 50),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.GREATER_HEALING_STONE.get()), 40),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.DENSE_STONE.get(), 2), 40),
        //                WeightedEntry.wrap(new ItemStack(GraviItems.DENSE_STONE.get()), 40)
        //        ),
        //        360,
        //        0.7f
        //));

        //add("gold_loot", new DAAddDungeonLootModifier (
        //        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("aether:chests/dungeon/gold/gold_dungeon_reward")).build() },
        //        List.of(
        //                WeightedEntry.wrap(new ItemStack(DAItems.STRATUS_SMITHING_TEMPLATE.get(), 2), 30),
        //                WeightedEntry.wrap(new ItemStack(DAItems.STRATUS_SMITHING_TEMPLATE.get()), 70)
        //        ),
        //        100,
        //        0.6f
        //));

        add("gold_loot_stratus_upgrade", new DAAddForceDungeonLootModifier (
                new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("aether:chests/dungeon/gold/gold_dungeon_reward")).build() },
                    new ItemStack(DAItems.STRATUS_SMITHING_TEMPLATE.get(), 1),
                0.0f
        ));
    }
}