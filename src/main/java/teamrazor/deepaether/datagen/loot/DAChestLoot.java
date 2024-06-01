package teamrazor.deepaether.datagen.loot;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class DAChestLoot implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        builder.accept(DALoot.BRASS_DUNGEON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_LOOT).setWeight(8))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_DISC).setWeight(1))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_TRASH).setWeight(1))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_LOOT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_PICKAXE.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_AXE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_SWORD.get()).setWeight(3))
                        .add(LootItem.lootTableItem(AetherItems.BLUE_GUMMY_SWET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.SWET_CAPE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.ICE_PENDANT.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_SHOVEL.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_HOE.get()).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 6.0F))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_DART.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 10.0F))))
                        .add(LootItem.lootTableItem(AetherItems.ENCHANTED_DART.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
                        .add(LootItem.lootTableItem(AetherItems.POISON_DART.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_PICKAXE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_POISON_BUCKET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.IRON_RING.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_AMBER.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_GEMSTONE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.HOLYSTONE_PICKAXE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherBlocks.ICESTONE.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 10.0F))))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 4.0F))
                        .add(LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get()).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 10.0F))))
                        .add(LootItem.lootTableItem(Items.FLINT).setWeight(3))
                        .add(LootItem.lootTableItem(Items.FEATHER).setWeight(2))
                        .add(LootItem.lootTableItem(Items.STRING).setWeight(2))
                        .add(LootItem.lootTableItem(Items.ARROW).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 10.0F))))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_RING.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherBlocks.SKYROOT_PLANKS.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_TRASH, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 6.0F))
                        .add(LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get()).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 8.0F))))
                        .add(LootItem.lootTableItem(Items.FLINT).setWeight(3))
                        .add(LootItem.lootTableItem(Items.FEATHER).setWeight(2))
                        .add(LootItem.lootTableItem(Items.STRING).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_GEMSTONE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherBlocks.SKYROOT_PLANKS.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_PICKAXE.get()).setWeight(1))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_DISC, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(AetherItems.MUSIC_DISC_AETHER_TUNE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_CAT).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 10.0F))
                        .add(LootItem.lootTableItem(AetherItems.AMBROSIUM_SHARD.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))
                        .add(LootItem.lootTableItem(AetherBlocks.AMBROSIUM_TORCH.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 16.0F))))
                        .add(LootItem.lootTableItem(Items.FLINT).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_GEMSTONE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.SKYROOT_PICKAXE.get()).setWeight(2))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_REWARD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_NEPTUNE).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_TREASURE).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_GUMMIES).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 5.0F))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_HELMET.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_CHESTPLATE.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_LEGGINGS.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_BOOTS.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_GLOVES.get()).setWeight(2))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_RING.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_PENDANT.get()).setWeight(1))
                        .add(LootItem.lootTableItem(Items.SADDLE).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.SHIELD_OF_REPULSION.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.CLOUD_STAFF.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.PHOENIX_BOW.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.FLAMING_SWORD.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.AGILITY_CAPE.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.ZANITE_GEMSTONE.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_AMBER.get()).setWeight(3))
                        .add(LootItem.lootTableItem(AetherItems.VALKYRIE_LANCE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.HAMMER_OF_KINGBDOGZ.get()).setWeight(1))
                        .add(LootTableReference.lootTableReference(DALoot.BRASS_DUNGEON_NEPTUNE).setWeight(8))
                        .add(LootItem.lootTableItem(AetherItems.SENTRY_BOOTS.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherBlocks.SENTRY_STONE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherBlocks.CARVED_STONE.get()).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 15.0F))
                        .add(LootItem.lootTableItem(AetherItems.LIGHTNING_KNIFE.get()).setWeight(5))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_DART.get()).setWeight(3))
                        .add(LootItem.lootTableItem(Items.ARROW).setWeight(2))
                        .add(LootItem.lootTableItem(AetherBlocks.SENTRY_STONE.get()).setWeight(1))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_TREASURE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(LootItem.lootTableItem(AetherItems.VALKYRIE_LANCE.get()).setWeight(3))
                        .add(LootItem.lootTableItem(AetherItems.HAMMER_OF_KINGBDOGZ.get()).setWeight(3))
                        .add(LootItem.lootTableItem(AetherItems.SENTRY_BOOTS.get()).setWeight(3))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_NEPTUNE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(AetherItems.NEPTUNE_HELMET.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.NEPTUNE_CHESTPLATE.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.NEPTUNE_LEGGINGS.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.NEPTUNE_BOOTS.get()).setWeight(1))
                        .add(LootItem.lootTableItem(AetherItems.NEPTUNE_GLOVES.get()).setWeight(1))
                )
        );
        builder.accept(DALoot.BRASS_DUNGEON_GUMMIES, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(AetherItems.BLUE_GUMMY_SWET.get()).setWeight(4))
                        .add(LootItem.lootTableItem(AetherItems.GOLDEN_GUMMY_SWET.get()).setWeight(2))
                )
        );
    }
}
