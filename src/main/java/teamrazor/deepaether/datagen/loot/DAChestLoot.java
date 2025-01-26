package teamrazor.deepaether.datagen.loot;


import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;

import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;

import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAEnchantments;
import teamrazor.deepaether.init.DAItems;

import java.util.function.BiConsumer;

public class DAChestLoot implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        builder.accept(DALoot.BRASS_DUNGEON_COMBINDER_LOOT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_COMBINDER_LOOT_SUB_0).setWeight(1))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_COMBINDER_LOOT_SUB_1).setWeight(1))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_COMBINDER_LOOT_SUB_2).setWeight(1)))
        );

        builder.accept(DALoot.BRASS_DUNGEON_COMBINDER_LOOT_SUB_0, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 6.0F))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_AMBER.get()))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 6.0F))
                        .add(LootItem.lootTableItem(DAItems.FROZEN_GOLDEN_BERRIES.get()))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 6.0F))
                        .add(LootItem.lootTableItem(DAItems.QUAIL_EGG.get()))
                )
        );

        builder.accept(DALoot.BRASS_DUNGEON_COMBINDER_LOOT_SUB_1, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 6.0F))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_AMBER.get()))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 6.0F))
                        .add(LootItem.lootTableItem(DAItems.FROZEN_GOLDEN_BERRIES.get()))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 6.0F))
                        .add(LootItem.lootTableItem(AetherBlocks.ICESTONE.get()))
                )
        );

        builder.accept(DALoot.BRASS_DUNGEON_COMBINDER_LOOT_SUB_2, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 6.0F))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_AMBER.get()))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 6.0F))
                        .add(LootItem.lootTableItem(DAItems.FROZEN_GOLDEN_BERRIES.get()))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 6.0F))
                        .add(LootItem.lootTableItem(AetherItems.BLUE_BERRY.get()))
                )
        );

        builder.accept(DALoot.BRASS_DUNGEON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_LOOT).setWeight(8))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_DISC).setWeight(1))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_TRASH).setWeight(1))
                )
        );

        builder.accept(DALoot.BRASS_DUNGEON_FODDER_LOOT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.3F))
                        .add(LootItem.lootTableItem(DAItems.MOA_FODDER.get())).apply(SetNbtFunction.setTag(getMoaFodderNBT(new ResourceLocation("fire_resistance")))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.3F))
                        .add(LootItem.lootTableItem(DAItems.MOA_FODDER.get())).apply(SetNbtFunction.setTag(getMoaFodderNBT(new ResourceLocation("jump_boost")))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(LootItemRandomChanceCondition.randomChance(0.3F))
                        .add(LootItem.lootTableItem(DAItems.MOA_FODDER.get())).apply(SetNbtFunction.setTag(getMoaFodderNBT(new ResourceLocation(DeepAether.MODID, "moa_bonus_jumps")))))
        );

        builder.accept(DALoot.BRASS_DUNGEON_LOOT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_PICKAXE.get()).setWeight(4))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_AXE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_SWORD.get()).setWeight(3))
                        .add(LootItem.lootTableItem(AetherItems.BLUE_GUMMY_SWET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DABlocks.STERLING_AERCLOUD.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.ICE_PENDANT.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_SHOVEL.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_HOE.get()).setWeight(1))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_FODDER_LOOT).setWeight(2))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 6.0F))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_DART.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 10.0F))))
                        .add(LootItem.lootTableItem(AetherItems.ENCHANTED_DART.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
                        .add(LootItem.lootTableItem(AetherItems.POISON_DART.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_PICKAXE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.IRON_RING.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_GEMSTONE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.HOLYSTONE_PICKAXE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherBlocks.COLD_AERCLOUD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.BLUE_AERCLOUD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 10.0F))))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 4.0F))
                        .add(LootItem.lootTableItem(DAItems.AERGLOW_BLOSSOM.get()).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 10.0F))))
                        .add(LootItem.lootTableItem(DAItems.GOLDEN_BERRIES.get()).setWeight(3))
                        .add(LootItem.lootTableItem(DAItems.FROZEN_GOLDEN_BERRIES.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.ANTIDOTE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(Items.ARROW).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 10.0F))))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_RING.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DABlocks.ROSEROOT_PLANKS.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                )
        );

        builder.accept(DALoot.BRASS_DUNGEON_TRASH, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 6.0F))
                        .add(LootItem.lootTableItem(DAItems.AERGLOW_BLOSSOM.get()).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 8.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.COLD_AERCLOUD.get()).setWeight(3))
                        .add(LootItem.lootTableItem(AetherBlocks.BLUE_AERCLOUD.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherBlocks.GOLDEN_AERCLOUD.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DABlocks.ROSEROOT_PLANKS.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_PICKAXE.get()).setWeight(1))
                )
        );

        builder.accept(DALoot.BRASS_DUNGEON_DISC, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(AetherItems.MUSIC_DISC_AETHER_TUNE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_CAT).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 10.0F))
                        .add(LootItem.lootTableItem(DAItems.AERGLOW_BLOSSOM.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 16.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.COLD_AERCLOUD.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_TOOLS_PICKAXE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_PICKAXE.get()).setWeight(2))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_REWARD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_FODDER_LOOT)))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_STORM_FORGED).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_TREASURE).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_GUMMIES).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 5.0F))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_HELMET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_CHESTPLATE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_LEGGINGS.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_BOOTS.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_GLOVES.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE_RING.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.WIND_SHIELD.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.CLOUD_STAFF.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.STORM_BOW.get()).setWeight(4))
                        .add(LootItem.lootTableItem(DAItems.STORM_SWORD.get()).setWeight(4))
                        .add(LootItem.lootTableItem(DAItems.CLOUD_CAPE.get()).setWeight(4))
                        .add(LootItem.lootTableItem(DAItems.SKYJADE.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.COLD_AERCLOUD.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.BLADE_OF_LUCK.get()).setWeight(2))
                        .add(LootItem.lootTableItem(DAItems.MUSIC_DISC_CYCLONE.get()).setWeight(2))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_STORM_FORGED).setWeight(10))
                        //.add(LootItem.lootTableItem(AetherItems.SENTRY_BOOTS.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DABlocks.NIMBUS_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DABlocks.NIMBUS_PILLAR.get()).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 15.0F))
                        //.add(LootItem.lootTableItem(AetherItems.LIGHTNING_KNIFE.get()).setWeight(5))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_DART.get()).setWeight(3))
                        .add(LootItem.lootTableItem(Items.ARROW).setWeight(2))
                        .add(LootItem.lootTableItem(DABlocks.NIMBUS_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DABlocks.NIMBUS_PILLAR.get()).setWeight(1))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_TREASURE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootItem.lootTableItem(DAItems.CLOUD_CAPE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(DAItems.AERCLOUD_NECKLACE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder().withEnchantment(DAEnchantments.GLOVES_REACH.get())).setWeight(3))
                ));
        builder.accept(DALoot.BRASS_DUNGEON_STORM_FORGED, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(DAItems.STORMFORGED_HELMET.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.STORMFORGED_CHESTPLATE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.STORMFORGED_LEGGINGS.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.STORMFORGED_BOOTS.get()).setWeight(1))
                        .add(LootItem.lootTableItem(DAItems.STORMFORGED_GLOVES.get()).setWeight(1))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_GUMMIES, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(AetherItems.BLUE_GUMMY_SWET.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_GUMMY_SWET.get()).setWeight(2))
                )
        );
    }

    private static CompoundTag getMoaFodderNBT(ResourceLocation effect) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("amplifier", 1);
        tag.putInt("time", 14400);
        tag.putString("effect", effect.toString());
        return tag;
    }
}