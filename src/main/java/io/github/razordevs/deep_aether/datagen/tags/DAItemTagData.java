package io.github.razordevs.deep_aether.datagen.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.item.AetherItems;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class DAItemTagData extends ItemTagsProvider {

    public DAItemTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, DeepAether.MODID, helper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.copy(DATags.Blocks.ROSEROOT_LOGS, DATags.Items.ROSEROOT_LOGS);
        this.copy(DATags.Blocks.YAGROOT_LOGS, DATags.Items.YAGROOT_LOGS);
        this.copy(DATags.Blocks.CRUDEROOT_LOGS, DATags.Items.CRUDEROOT_LOGS);
        this.copy(DATags.Blocks.CONBERRY_LOGS, DATags.Items.CONBERRY_LOGS);
        this.copy(DATags.Blocks.SUNROOT_LOGS, DATags.Items.SUNROOT_LOGS);
        this.copy(DATags.Blocks.NIMBUS_BLOCKS, DATags.Items.NIMBUS_BLOCKS);
        this.copy(AetherTags.Blocks.AERCLOUDS, AetherTags.Items.AERCLOUDS);
        this.copy(Tags.Blocks.CHAINS, Tags.Items.CHAINS);
        this.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        this.copy(BlockTags.LOGS, ItemTags.LOGS);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.FENCES, ItemTags.FENCES);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(Tags.Blocks.FENCES, Tags.Items.FENCES);
        this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
        this.copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        this.copy(Tags.Blocks.FENCE_GATES, Tags.Items.FENCE_GATES);
        this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        this.copy(BlockTags.DOORS, ItemTags.DOORS);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WALLS, ItemTags.WALLS);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
        this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
        this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);

        Collection<DeferredHolder<Item, ? extends Item>> items = DAItems.ITEMS.getEntries();

        IntrinsicTagAppender<Item> tag = this.tag(AetherTags.Items.TREATED_AS_AETHER_ITEM);
        items.forEach(item -> tag.add(item.get()));

        // Planks & Related
        tag(AetherTags.Items.PLANKS_CRAFTING).add(
                DABlocks.ROSEROOT_PLANKS.get().asItem(),
                DABlocks.YAGROOT_PLANKS.get().asItem(),
                DABlocks.CRUDEROOT_PLANKS.get().asItem(),
                DABlocks.CONBERRY_PLANKS.get().asItem(),
                DABlocks.SUNROOT_PLANKS.get().asItem()
        );
        tag(DATags.Items.CRAFTS_ROSEROOT_PLANKS).add(
                DABlocks.ROSEROOT_LOG.get().asItem(),
                DABlocks.ROSEROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_ROSEROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_ROSEROOT_WOOD.get().asItem()
        );
        tag(DATags.Items.CRAFTS_YAGROOT_PLANKS).add(
                DABlocks.YAGROOT_LOG.get().asItem(),
                DABlocks.YAGROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_YAGROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_YAGROOT_WOOD.get().asItem()
        );
        tag(DATags.Items.CRAFTS_CRUDEROOT_PLANKS).add(
                DABlocks.CRUDEROOT_LOG.get().asItem(),
                DABlocks.CRUDEROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_CRUDEROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_CRUDEROOT_WOOD.get().asItem()
        );
        tag(DATags.Items.CRAFTS_CONBERRY_PLANKS).add(
                DABlocks.CONBERRY_LOG.get().asItem(),
                DABlocks.CONBERRY_WOOD.get().asItem(),
                DABlocks.STRIPPED_CONBERRY_LOG.get().asItem(),
                DABlocks.STRIPPED_CONBERRY_WOOD.get().asItem()
        );
        tag(DATags.Items.CRAFTS_SUNROOT_PLANKS).add(
                DABlocks.SUNROOT_LOG.get().asItem(),
                DABlocks.SUNROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_SUNROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_SUNROOT_WOOD.get().asItem()
        );

        // Skyroot & Related
        tag(AetherTags.Items.SKYROOT_STICK_CRAFTING).add(
                DABlocks.ROSEROOT_PLANKS.get().asItem(),
                DABlocks.YAGROOT_PLANKS.get().asItem(),
                DABlocks.CRUDEROOT_PLANKS.get().asItem(),
                DABlocks.CONBERRY_PLANKS.get().asItem(),
                DABlocks.SUNROOT_PLANKS.get().asItem()
        );
        tag(AetherTags.Items.SKYROOT_TOOL_CRAFTING).add(
                DABlocks.ROSEROOT_PLANKS.get().asItem(),
                DABlocks.YAGROOT_PLANKS.get().asItem(),
                DABlocks.CRUDEROOT_PLANKS.get().asItem(),
                DABlocks.CONBERRY_PLANKS.get().asItem(),
                DABlocks.SUNROOT_PLANKS.get().asItem()
        );
        tag(AetherTags.Items.SKYROOT_REPAIRING).add(
                DABlocks.ROSEROOT_PLANKS.get().asItem(),
                DABlocks.YAGROOT_PLANKS.get().asItem(),
                DABlocks.CRUDEROOT_PLANKS.get().asItem(),
                DABlocks.CONBERRY_PLANKS.get().asItem(),
                DABlocks.SUNROOT_PLANKS.get().asItem()
        );

        // Armor Related
        tag(ItemTags.HEAD_ARMOR).add(
                DAItems.SKYJADE_HELMET.get(),
                DAItems.STRATUS_HELMET.get(),
                DAItems.STORMFORGED_HELMET.get()
        );
        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE).add(
                DAItems.SKYJADE_HELMET.get(),
                DAItems.STRATUS_HELMET.get(),
                DAItems.STORMFORGED_HELMET.get()
        );
        tag(ItemTags.CHEST_ARMOR).add(
                DAItems.SKYJADE_CHESTPLATE.get(),
                DAItems.STRATUS_CHESTPLATE.get(),
                DAItems.STORMFORGED_CHESTPLATE.get()
        );
        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE).add(
                DAItems.SKYJADE_CHESTPLATE.get(),
                DAItems.STRATUS_CHESTPLATE.get(),
                DAItems.STORMFORGED_CHESTPLATE.get()
        );
        tag(ItemTags.LEG_ARMOR).add(
                DAItems.SKYJADE_LEGGINGS.get(),
                DAItems.STRATUS_LEGGINGS.get(),
                DAItems.STORMFORGED_LEGGINGS.get()
        );
        tag(ItemTags.LEG_ARMOR_ENCHANTABLE).add(
                DAItems.SKYJADE_LEGGINGS.get(),
                DAItems.STRATUS_LEGGINGS.get(),
                DAItems.STORMFORGED_LEGGINGS.get()
        );
        tag(ItemTags.FOOT_ARMOR).add(
                DAItems.SKYJADE_BOOTS.get(),
                DAItems.STRATUS_BOOTS.get(),
                DAItems.STORMFORGED_BOOTS.get()
        );
        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE).add(
                DAItems.SKYJADE_BOOTS.get(),
                DAItems.STRATUS_BOOTS.get(),
                DAItems.STORMFORGED_BOOTS.get()
        );
        tag(ItemTags.TRIMMABLE_ARMOR).add(
                DAItems.SKYJADE_HELMET.get(),
                DAItems.SKYJADE_CHESTPLATE.get(),
                DAItems.SKYJADE_LEGGINGS.get(),
                DAItems.SKYJADE_BOOTS.get(),
                DAItems.SKYJADE_GLOVES.get(),

                DAItems.STORMFORGED_HELMET.get(),
                DAItems.STORMFORGED_CHESTPLATE.get(),
                DAItems.STORMFORGED_LEGGINGS.get(),
                DAItems.STORMFORGED_BOOTS.get(),
                DAItems.STORMFORGED_GLOVES.get(),

                DAItems.STRATUS_HELMET.get(),
                DAItems.STRATUS_CHESTPLATE.get(),
                DAItems.STRATUS_LEGGINGS.get(),
                DAItems.STRATUS_BOOTS.get(),
                DAItems.STRATUS_GLOVES.get()
        );

        // Tool Related
        tag(ItemTags.SWORDS).add(
                DAItems.SKYJADE_TOOLS_SWORD.get(),
                DAItems.STRATUS_SWORD.get(),
                DAItems.BLADE_OF_LUCK.get(),
                DAItems.STORM_SWORD.get()
        );
        tag(ItemTags.PICKAXES).add(
                DAItems.SKYJADE_TOOLS_PICKAXE.get(),
                DAItems.STRATUS_PICKAXE.get()
        );
        tag(ItemTags.SHOVELS).add(
                DAItems.SKYJADE_TOOLS_SHOVEL.get(),
                DAItems.STRATUS_SHOVEL.get()
        );
        tag(ItemTags.AXES).add(
                DAItems.SKYJADE_TOOLS_AXE.get(),
                DAItems.STRATUS_AXE.get()
        );
        tag(ItemTags.HOES).add(
                DAItems.SKYJADE_TOOLS_HOE.get(),
                DAItems.STRATUS_HOE.get()
        );
        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE).add(
                DAItems.SKYJADE_TOOLS_SWORD.get(),
                DAItems.STRATUS_SWORD.get(),
                DAItems.BLADE_OF_LUCK.get(),
                DAItems.STORM_SWORD.get()
        );
        tag(ItemTags.WEAPON_ENCHANTABLE).add(
                DAItems.SKYJADE_TOOLS_SWORD.get(),
                DAItems.STRATUS_SWORD.get(),
                DAItems.BLADE_OF_LUCK.get(),
                DAItems.STORM_SWORD.get()
        );
        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE).add(
                DAItems.SKYJADE_TOOLS_SWORD.get(),
                DAItems.STRATUS_SWORD.get(),
                DAItems.BLADE_OF_LUCK.get(),
                DAItems.STORM_SWORD.get()
        );
        tag(ItemTags.SWORD_ENCHANTABLE).add(
                DAItems.SKYJADE_TOOLS_SWORD.get(),
                DAItems.STRATUS_SWORD.get(),
                DAItems.BLADE_OF_LUCK.get(),
                DAItems.STORM_SWORD.get()
        );
        tag(ItemTags.MINING_ENCHANTABLE).add(
                DAItems.SKYJADE_TOOLS_PICKAXE.get(),
                DAItems.STRATUS_PICKAXE.get(),
                DAItems.SKYJADE_TOOLS_AXE.get(),
                DAItems.STRATUS_AXE.get(),
                DAItems.SKYJADE_TOOLS_SHOVEL.get(),
                DAItems.STRATUS_SHOVEL.get(),
                DAItems.SKYJADE_TOOLS_HOE.get(),
                DAItems.STRATUS_HOE.get()
        );
        tag(ItemTags.BOW_ENCHANTABLE).add(
                DAItems.STORM_BOW.get()
        );
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(
                DAItems.SKYJADE_TOOLS_SWORD.get(),
                DAItems.STRATUS_SWORD.get(),
                DAItems.BLADE_OF_LUCK.get(),
                DAItems.STORM_SWORD.get(),
                DAItems.SKYJADE_TOOLS_PICKAXE.get(),
                DAItems.STRATUS_PICKAXE.get(),
                DAItems.SKYJADE_TOOLS_SHOVEL.get(),
                DAItems.STRATUS_SHOVEL.get(),
                DAItems.SKYJADE_TOOLS_AXE.get(),
                DAItems.STRATUS_AXE.get(),
                DAItems.SKYJADE_TOOLS_HOE.get(),
                DAItems.STRATUS_HOE.get(),
                DAItems.SKYJADE_HELMET.get(),
                DAItems.STRATUS_HELMET.get(),
                DAItems.SKYJADE_CHESTPLATE.get(),
                DAItems.STRATUS_CHESTPLATE.get(),
                DAItems.SKYJADE_LEGGINGS.get(),
                DAItems.STRATUS_LEGGINGS.get(),
                DAItems.SKYJADE_BOOTS.get(),
                DAItems.STRATUS_BOOTS.get(),
                DAItems.STORMFORGED_HELMET.get(),
                DAItems.STORMFORGED_CHESTPLATE.get(),
                DAItems.STORMFORGED_LEGGINGS.get(),
                DAItems.STORMFORGED_BOOTS.get(),
                DAItems.STORM_BOW.get(),
                DAItems.SKYJADE_GLOVES.get(),
                DAItems.STRATUS_GLOVES.get(),
                DAItems.STORMFORGED_GLOVES.get(),
                DAItems.SKYJADE_RING.get(),
                DAItems.STRATUS_RING.get(),
                DAItems.WIND_SHIELD.get(),
                DAItems.SLIDER_EYE.get(),
                DAItems.AFTERBURNER.get()
        );

        // Blocks >> Items
        tag(DATags.Items.CRAFTS_MOSSY_BLOCKS).add(
                DABlocks.AETHER_MOSS_BLOCK.get().asItem(),
                Blocks.MOSS_BLOCK.asItem(),
                Blocks.VINE.asItem()
        );
        tag(ItemTags.HANGING_SIGNS).add(
                DAItems.CONBERRY_HANGING_SIGN.get(),
                DAItems.CRUDEROOT_HANGING_SIGN.get(),
                DAItems.ROSEROOT_HANGING_SIGN.get(),
                DAItems.YAGROOT_HANGING_SIGN.get(),
                DAItems.SUNROOT_HANGING_SIGN.get()
        );

        // Skyjade Related
        tag(DATags.Items.SKYJADE_ARMOR).add(
                DAItems.SKYJADE_HELMET.get(),
                DAItems.SKYJADE_CHESTPLATE.get(),
                DAItems.SKYJADE_LEGGINGS.get(),
                DAItems.SKYJADE_BOOTS.get(),
                DAItems.SKYJADE_GLOVES.get()
        );
        // Stratus Related
        tag(DATags.Items.STRATUS_ARMOR).add(
                DAItems.STRATUS_HELMET.get(),
                DAItems.STRATUS_CHESTPLATE.get(),
                DAItems.STRATUS_LEGGINGS.get(),
                DAItems.STRATUS_BOOTS.get(),
                DAItems.STRATUS_GLOVES.get()
        );
        // Stormforged Related
        tag(DATags.Items.STORM_ARMOR).add(
                DAItems.STORMFORGED_HELMET.get(),
                DAItems.STORMFORGED_CHESTPLATE.get(),
                DAItems.STORMFORGED_LEGGINGS.get(),
                DAItems.STORMFORGED_BOOTS.get(),
                DAItems.STORMFORGED_GLOVES.get()
        );

        // Dungeon Loot
        tag(AetherTags.Items.BRONZE_DUNGEON_LOOT).add(
                DAItems.MUSIC_DISC_ATTA.get()
        );
        tag(AetherTags.Items.SILVER_DUNGEON_LOOT).add(
                DAItems.MUSIC_DISC_FAENT.get()
        );
        tag(AetherTags.Items.GOLD_DUNGEON_LOOT).add(
                DAItems.MUSIC_DISC_HIMININN.get(),
                DAItems.STRATUS_SMITHING_TEMPLATE.get()
        );
        tag(DATags.Items.BRASS_DUNGEON_LOOT).add(
                DAItems.STORMFORGED_SMITHING_TEMPLATE.get(),
                DAItems.CLOUD_CAPE.get(),
                DAItems.WIND_SHIELD.get(),
                DAItems.AERCLOUD_NECKLACE.get(),
                DAItems.STORM_SWORD.get(),
                DAItems.STORM_BOW.get(),
                DAItems.BLADE_OF_LUCK.get(),
                DAItems.MUSIC_DISC_CYCLONE.get()
        );
        tag(DATags.Items.BRASS_DUNGEON_LOOT).addTag(
                DATags.Items.STORM_ARMOR
        );

        //Accessories & Related
        tag(DATags.Items.FLAWLESS_ITEMS).add(
                DAItems.SLIDER_EYE.get(),
                DAItems.MEDAL_OF_HONOR.get(),
                DAItems.SUN_CORE.get(),
                DAItems.AFTERBURNER.get(),
                DAItems.AERWHALE_SADDLE.get(),
                DAItems.FLOATY_SCARF.get()
        );
        tag(AetherTags.Items.ACCESSORIES_GLOVES).add(
                DAItems.SKYJADE_GLOVES.get(),
                DAItems.STORMFORGED_GLOVES.get(),
                DAItems.STRATUS_GLOVES.get()
        );
        tag(AetherTags.Items.ACCESSORIES_RINGS).add(
                DAItems.SKYJADE_RING.get(),
                DAItems.STRATUS_RING.get(),
                DAItems.SPOOKY_RING.get(),
                DAItems.GRAVITITE_RING.get()
        );
        tag(AetherTags.Items.ACCESSORIES_PENDANTS).add(
                DAItems.MEDAL_OF_HONOR.get(),
                DAItems.AERCLOUD_NECKLACE.get(),
                DAItems.FLOATY_SCARF.get()
        );
        tag(AetherTags.Items.ACCESSORIES_CAPES).add(
                DAItems.CLOUD_CAPE.get()
        );
        tag(AetherTags.Items.ACCESSORIES_SHIELDS).add(
                DAItems.WIND_SHIELD.get()
        );
        tag(AetherTags.Items.ACCESSORIES_MISCELLANEOUS).add(
                DAItems.SLIDER_EYE.get()
        );
        /*tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(DeepAether.LOST_AETHER_CONTENT, "aether_shields"))).add(
                DAItems.SKYJADE_SHIELD.get(),
                DAItems.STRATUS_SHIELD.get()
        );*/

        // Storm Bow
        tag(Tags.Items.TOOLS_BOW).add(
                DAItems.STORM_BOW.get()
        );
        tag(Tags.Items.RANGED_WEAPON_TOOLS).add(
                DAItems.STORM_BOW.get()
        );

        // Materials & Related
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(
                DAItems.SKYJADE.get(),
                DAItems.STRATUS_INGOT.get(),
                DAItems.SQUALL_PLATE.get()
        );
        tag(ItemTags.TRIM_MATERIALS).add(
                DAItems.SKYJADE.get(),
                DAItems.STRATUS_INGOT.get(),
                DAItems.SQUALL_PLATE.get()
        );
        tag(DATags.Items.SKYJADE_REPAIRING).add(
                DAItems.SKYJADE.get()
        );
        tag(Tags.Items.GEMS).add(
                DAItems.SKYJADE.get()
        );
        tag(Tags.Items.NUGGETS).add(
                DAItems.SKYJADE_NUGGET.get()
        );
        tag(DATags.Items.STRATUS_REPAIRING).add(
                DAItems.STRATUS_INGOT.get()
        );
        tag(Tags.Items.INGOTS).add(
                DAItems.STRATUS_INGOT.get()
        );
        tag(DATags.Items.STORM_REPAIRING).add(
                DAItems.SQUALL_PLATE.get()
        );
        tag(AetherTags.Items.ORES_IN_GROUND_HOLYSTONE).add(
                DABlocks.SKYJADE_ORE.get().asItem()
        );
        tag(Tags.Items.ORES).add(
                DABlocks.SKYJADE_ORE.get().asItem()
        );
        tag(Tags.Items.ORE_RATES_SINGULAR).add(
                DABlocks.SKYJADE_ORE.get().asItem()
        );

        // Boats & Chests
        tag(ItemTags.BOATS).add(
                DAItems.ROSEROOT_BOAT.get(),
                DAItems.YAGROOT_BOAT.get(),
                DAItems.CRUDEROOT_BOAT.get(),
                DAItems.CONBERRY_BOAT.get(),
                DAItems.SUNROOT_BOAT.get(),
                DAItems.ROSEROOT_CHEST_BOAT.get(),
                DAItems.YAGROOT_CHEST_BOAT.get(),
                DAItems.CRUDEROOT_CHEST_BOAT.get(),
                DAItems.CONBERRY_CHEST_BOAT.get(),
                DAItems.SUNROOT_CHEST_BOAT.get()
        );

        tag(ItemTags.CHEST_BOATS).add(
                DAItems.ROSEROOT_CHEST_BOAT.get(),
                DAItems.YAGROOT_CHEST_BOAT.get(),
                DAItems.CRUDEROOT_CHEST_BOAT.get(),
                DAItems.CONBERRY_CHEST_BOAT.get(),
                DAItems.SUNROOT_CHEST_BOAT.get()
        );

        // Foods & Related
        tag(Tags.Items.FOODS_BERRY).add(
                DAItems.FROZEN_GOLDEN_BERRIES.get(),
                DAItems.GOLDEN_BERRIES.get()
        );
        tag(ItemTags.FOX_FOOD).add(
                DAItems.GOLDEN_BERRIES.get()
        );

        tag(ItemTags.FISHES).add(
                DAItems.RAW_AERGLOW_FISH.get(),
                DAItems.COOKED_AERGLOW_FISH.get()
        );
        tag(ItemTags.CAT_FOOD).add(
                DAItems.RAW_AERGLOW_FISH.get()
        );
        tag(ItemTags.OCELOT_FOOD).add(
                DAItems.RAW_AERGLOW_FISH.get()
        );
        tag(Tags.Items.FOODS_RAW_FISH).add(
                DAItems.RAW_AERGLOW_FISH.get()
        );
        tag(Tags.Items.FOODS_COOKED_FISH).add(
                DAItems.COOKED_AERGLOW_FISH.get()
        );

        tag(ItemTags.WOLF_FOOD).add(
                DAItems.RAW_QUAIL.get(),
                DAItems.COOKED_QUAIL.get()
        );
        tag(ItemTags.MEAT).add(
                DAItems.RAW_QUAIL.get(),
                DAItems.COOKED_QUAIL.get()
        );
        tag(Tags.Items.FOODS_RAW_MEAT).add(
                DAItems.RAW_QUAIL.get()
        );
        tag(Tags.Items.FOODS_COOKED_MEAT).add(
                DAItems.COOKED_QUAIL.get()
        );

        tag(ItemTags.CHICKEN_FOOD).add(
                DAItems.SQUASH_SEEDS.get(),
                DAItems.GOLDEN_GRASS_SEEDS.get()
        );
        tag(DATags.Items.QUAIL_FOOD).add(
                Items.WHEAT_SEEDS,
                Items.MELON_SEEDS,
                Items.PUMPKIN_SEEDS,
                Items.BEETROOT_SEEDS,
                Items.TORCHFLOWER_SEEDS,
                Items.PITCHER_POD,
                DAItems.SQUASH_SEEDS.get(),
                DAItems.GOLDEN_GRASS_SEEDS.get()
        );

        tag(ItemTags.PARROT_FOOD).add(
                DAItems.SQUASH_SEEDS.get(),
                DAItems.GOLDEN_GRASS_SEEDS.get()
        );
        tag(Tags.Items.SEEDS).add(
                DAItems.SQUASH_SEEDS.get(),
                DAItems.GOLDEN_GRASS_SEEDS.get()
        );

        tag(Tags.Items.EGGS).add(
                DAItems.QUAIL_EGG.get()
        );
        tag(Tags.Items.MUSHROOMS).add(
                DABlocks.LIGHTCAP_MUSHROOMS.get().asItem()
        );
        tag(Tags.Items.CROPS).add(
                DABlocks.GREEN_SQUASH.get().asItem(),
                DABlocks.BLUE_SQUASH.get().asItem(),
                DABlocks.PURPLE_SQUASH.get().asItem()
        );
        tag(Tags.Items.FOODS_FRUIT).add(
                DAItems.GREEN_SQUASH_SLICE.get(),
                DAItems.BLUE_SQUASH_SLICE.get(),
                DAItems.PURPLE_SQUASH_SLICE.get()
        );
        tag(DATags.Items.SQUASH_SLICE).add(
                DAItems.GREEN_SQUASH_SLICE.get(),
                DAItems.BLUE_SQUASH_SLICE.get(),
                DAItems.PURPLE_SQUASH_SLICE.get()
        );

        tag(Tags.Items.ANIMAL_FOODS).add(
                DAItems.SQUASH_SEEDS.get(),
                DAItems.GOLDEN_GRASS_SEEDS.get(),
                DAItems.RAW_AERGLOW_FISH.get(),
                DAItems.GOLDEN_BERRIES.get(),
                DAItems.RAW_QUAIL.get(),
                DAItems.COOKED_QUAIL.get()
        );

        // Miscellaneous
        tag(AetherTags.Items.SLIDER_DAMAGING_ITEMS).add(
                DAItems.SKYJADE_TOOLS_PICKAXE.get(),
                DAItems.STRATUS_PICKAXE.get()
        );

        tag(ItemTags.COMPASSES).add(
                DAItems.BRONZE_COMPASS.get(),
                DAItems.SILVER_COMPASS.get(),
                DAItems.GOLD_COMPASS.get()
        );

        tag(AetherTags.Items.NO_SKYROOT_DOUBLE_DROPS).add(
                DAItems.BRASS_DUNGEON_KEY.get()
        );
        tag(AetherTags.Items.DUNGEON_KEYS).add(
                DAItems.BRASS_DUNGEON_KEY.get()
        );

        tag(Tags.Items.BUCKETS).add(
                DAItems.PLACEABLE_POISON_BUCKET.get(),
                DAItems.AERGLOW_FISH_BUCKET.get(),
                DAItems.SKYROOT_AERGLOW_FISH_BUCKET.get(),
                DAItems.VIRULENT_QUICKSAND_BUCKET.get(),
                DAItems.SKYROOT_VIRULENT_QUICKSAND_BUCKET.get()
        );
        tag(Tags.Items.BUCKETS_ENTITY_WATER).add(
                DAItems.AERGLOW_FISH_BUCKET.get(),
                DAItems.SKYROOT_AERGLOW_FISH_BUCKET.get()
        );

        tag(DATags.Items.IS_GOLDEN_SWET_BALL).add(
                DAItems.GOLDEN_SWET_BALL.get()
        ).addOptional(
                ResourceLocation.fromNamespaceAndPath(DeepAether.AETHER_REDUX, "golden_swet_ball")
        ).addOptional(
                ResourceLocation.fromNamespaceAndPath(DeepAether.AETHER_GENESIS,"golden_swet_ball")
        );

        tag(Tags.Items.MUSIC_DISCS).add(
                DAItems.MUSIC_DISC_A_MORNING_WISH.get(),
                DAItems.MUSIC_DISC_ABOVE_THE_RAIN.get(),
                DAItems.MUSIC_DISC_ATTA.get(),
                DAItems.MUSIC_DISC_CYCLONE.get(),
                DAItems.MUSIC_DISC_FAENT.get(),
                DAItems.MUSIC_DISC_HIMININN.get(),
                DAItems.MUSIC_DISC_NABOORU.get()
        );

        tag(DATags.Items.POISON_BUCKET).add(
                DAItems.PLACEABLE_POISON_BUCKET.get(),
                AetherItems.SKYROOT_POISON_BUCKET.get()
        );
    }
}
