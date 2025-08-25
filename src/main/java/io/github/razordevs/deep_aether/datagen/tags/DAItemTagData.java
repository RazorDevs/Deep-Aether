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
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
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
        tag(ItemTags.LOGS).add(
                DABlocks.ROSEROOT_LOG.get().asItem(),
                DABlocks.ROSEROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_ROSEROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_ROSEROOT_WOOD.get().asItem(),
                DABlocks.YAGROOT_LOG.get().asItem(),
                DABlocks.YAGROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_YAGROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_YAGROOT_WOOD.get().asItem(),
                DABlocks.CRUDEROOT_LOG.get().asItem(),
                DABlocks.CRUDEROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_CRUDEROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_CRUDEROOT_WOOD.get().asItem(),
                DABlocks.CONBERRY_LOG.get().asItem(),
                DABlocks.CONBERRY_WOOD.get().asItem(),
                DABlocks.STRIPPED_CONBERRY_LOG.get().asItem(),
                DABlocks.STRIPPED_CONBERRY_WOOD.get().asItem(),
                DABlocks.SUNROOT_LOG.get().asItem(),
                DABlocks.SUNROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_SUNROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_SUNROOT_WOOD.get().asItem()
        );
        tag(ItemTags.LOGS_THAT_BURN).add(
                DABlocks.ROSEROOT_LOG.get().asItem(),
                DABlocks.ROSEROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_ROSEROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_ROSEROOT_WOOD.get().asItem(),
                DABlocks.YAGROOT_LOG.get().asItem(),
                DABlocks.YAGROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_YAGROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_YAGROOT_WOOD.get().asItem(),
                DABlocks.CRUDEROOT_LOG.get().asItem(),
                DABlocks.CRUDEROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_CRUDEROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_CRUDEROOT_WOOD.get().asItem(),
                DABlocks.CONBERRY_LOG.get().asItem(),
                DABlocks.CONBERRY_WOOD.get().asItem(),
                DABlocks.STRIPPED_CONBERRY_LOG.get().asItem(),
                DABlocks.STRIPPED_CONBERRY_WOOD.get().asItem(),
                DABlocks.SUNROOT_LOG.get().asItem(),
                DABlocks.SUNROOT_WOOD.get().asItem(),
                DABlocks.STRIPPED_SUNROOT_LOG.get().asItem(),
                DABlocks.STRIPPED_SUNROOT_WOOD.get().asItem()
        );
        tag(ItemTags.SIGNS).add(
                DABlocks.ROSEROOT_SIGN.get().asItem(),
                DABlocks.YAGROOT_SIGN.get().asItem(),
                DABlocks.CRUDEROOT_SIGN.get().asItem(),
                DABlocks.CONBERRY_SIGN.get().asItem(),
                DABlocks.SUNROOT_SIGN.get().asItem()
        );
        tag(ItemTags.HANGING_SIGNS).add(
                DAItems.CONBERRY_HANGING_SIGN.get(),
                DAItems.CRUDEROOT_HANGING_SIGN.get(),
                DAItems.ROSEROOT_HANGING_SIGN.get(),
                DAItems.YAGROOT_HANGING_SIGN.get(),
                DAItems.SUNROOT_HANGING_SIGN.get()
        );
        tag(ItemTags.STAIRS).add(
                DABlocks.ROSEROOT_STAIRS.get().asItem(),
                DABlocks.YAGROOT_STAIRS.get().asItem(),
                DABlocks.CRUDEROOT_STAIRS.get().asItem(),
                DABlocks.CONBERRY_STAIRS.get().asItem(),
                DABlocks.SUNROOT_STAIRS.get().asItem(),
                DABlocks.CLORITE_STAIRS.get().asItem(),
                DABlocks.ASETERITE_STAIRS.get().asItem(),
                DABlocks.COBBLED_ASETERITE_STAIRS.get().asItem(),
                DABlocks.ASETERITE_BRICKS_STAIRS.get().asItem(),
                DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get().asItem(),
                DABlocks.AETHER_MUD_BRICKS_STAIRS.get().asItem(),
                DABlocks.HOLYSTONE_TILE_STAIRS.get().asItem(),
                DABlocks.MOSSY_HOLYSTONE_BRICK_STAIRS.get().asItem(),
                DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get().asItem(),
                DABlocks.GILDED_HOLYSTONE_BRICK_STAIRS.get().asItem(),
                DABlocks.GILDED_HOLYSTONE_TILE_STAIRS.get().asItem(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_STAIRS.get().asItem(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_STAIRS.get().asItem(),
                DABlocks.NIMBUS_STAIRS.get().asItem()
        );
        tag(ItemTags.SLABS).add(
                DABlocks.ROSEROOT_SLAB.get().asItem(),
                DABlocks.YAGROOT_SLAB.get().asItem(),
                DABlocks.CRUDEROOT_SLAB.get().asItem(),
                DABlocks.CONBERRY_SLAB.get().asItem(),
                DABlocks.SUNROOT_SLAB.get().asItem(),
                DABlocks.CLORITE_SLAB.get().asItem(),
                DABlocks.ASETERITE_SLAB.get().asItem(),
                DABlocks.COBBLED_ASETERITE_SLAB.get().asItem(),
                DABlocks.ASETERITE_BRICKS_SLAB.get().asItem(),
                DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get().asItem(),
                DABlocks.AETHER_MUD_BRICKS_SLAB.get().asItem(),
                DABlocks.HOLYSTONE_TILE_SLAB.get().asItem(),
                DABlocks.MOSSY_HOLYSTONE_BRICK_SLAB.get().asItem(),
                DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get().asItem(),
                DABlocks.GILDED_HOLYSTONE_BRICK_SLAB.get().asItem(),
                DABlocks.GILDED_HOLYSTONE_TILE_SLAB.get().asItem(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_SLAB.get().asItem(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_SLAB.get().asItem(),
                DABlocks.NIMBUS_SLAB.get().asItem()
        );
        tag(ItemTags.WALLS).add(
                DABlocks.ROSEROOT_WALL.get().asItem(),
                DABlocks.STRIPPED_ROSEROOT_WALL.get().asItem(),
                DABlocks.YAGROOT_WALL.get().asItem(),
                DABlocks.STRIPPED_YAGROOT_WALL.get().asItem(),
                DABlocks.CRUDEROOT_WALL.get().asItem(),
                DABlocks.STRIPPED_CRUDEROOT_WALL.get().asItem(),
                DABlocks.CONBERRY_WALL.get().asItem(),
                DABlocks.STRIPPED_CONBERRY_WALL.get().asItem(),
                DABlocks.SUNROOT_WALL.get().asItem(),
                DABlocks.STRIPPED_SUNROOT_WALL.get().asItem(),
                DABlocks.CLORITE_WALL.get().asItem(),
                DABlocks.POLISHED_CLORITE_WALL.get().asItem(),
                DABlocks.ASETERITE_WALL.get().asItem(),
                DABlocks.COBBLED_ASETERITE_WALL.get().asItem(),
                DABlocks.POLISHED_ASETERITE_WALL.get().asItem(),
                DABlocks.ASETERITE_BRICKS_WALL.get().asItem(),
                DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get().asItem(),
                DABlocks.AETHER_MUD_BRICKS_WALL.get().asItem(),
                DABlocks.HOLYSTONE_TILE_WALL.get().asItem(),
                DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get().asItem(),
                DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get().asItem(),
                DABlocks.GILDED_HOLYSTONE_BRICK_WALL.get().asItem(),
                DABlocks.GILDED_HOLYSTONE_TILE_WALL.get().asItem(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_WALL.get().asItem(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get().asItem(),
                DABlocks.NIMBUS_WALL.get().asItem()
        );
        tag(ItemTags.FENCE_GATES).add(
                DABlocks.ROSEROOT_FENCE_GATE.get().asItem(),
                DABlocks.YAGROOT_FENCE_GATE.get().asItem(),
                DABlocks.CRUDEROOT_FENCE_GATE.get().asItem(),
                DABlocks.CONBERRY_FENCE_GATE.get().asItem(),
                DABlocks.SUNROOT_FENCE_GATE.get().asItem()
        );

        // Flora & Related
        tag(ItemTags.LEAVES).add(
                DABlocks.ROSEROOT_LEAVES.get().asItem(),
                DABlocks.BLUE_ROSEROOT_LEAVES.get().asItem(),
                DABlocks.FLOWERING_ROSEROOT_LEAVES.get().asItem(),
                DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get().asItem(),
                DABlocks.YAGROOT_LEAVES.get().asItem(),
                DABlocks.CRUDEROOT_LEAVES.get().asItem(),
                DABlocks.CONBERRY_LEAVES.get().asItem(),
                DABlocks.SUNROOT_LEAVES.get().asItem()
        );
        tag(ItemTags.SAPLINGS).add(
                DABlocks.SUNROOT_SAPLING.get().asItem(),
                DABlocks.BLUE_ROSEROOT_SAPLING.get().asItem(),
                DABlocks.CONBERRY_SAPLING.get().asItem(),
                DABlocks.CRUDEROOT_SAPLING.get().asItem(),
                DABlocks.ROSEROOT_SAPLING.get().asItem(),
                DABlocks.YAGROOT_SAPLING.get().asItem()
        );
        tag(ItemTags.SMALL_FLOWERS).add(
                DABlocks.AERLAVENDER.get().asItem(),
                DABlocks.AETHER_CATTAILS.get().asItem(),
                DABlocks.GOLDEN_FLOWER.get().asItem(),
                DABlocks.RADIANT_ORCHID.get().asItem(),
                DABlocks.ENCHANTED_BLOSSOM.get().asItem(),
                DABlocks.SKY_TULIPS.get().asItem(),
                DABlocks.IASPOVE.get().asItem(),
                DABlocks.GOLDEN_ASPESS.get().asItem(),
                DABlocks.ECHAISY.get().asItem()
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
                DAItems.WIND_SHIELD.asItem()
        );
        tag(AetherTags.Items.ACCESSORIES_MISCELLANEOUS).add(
                DAItems.SLIDER_EYE.get()
        );
        /*tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(DeepAether.LOST_AETHER_CONTENT, "aether_shields"))).add(
                DAItems.SKYJADE_SHIELD.get(),
                DAItems.STRATUS_SHIELD.get()
        );*/

        // Materials & Related
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(
                DAItems.SKYJADE.get(),
                DAItems.STRATUS_INGOT.get(),
                DAItems.SQUALL_PLATE.get()
        );
        tag(Tags.Items.STORAGE_BLOCKS).add(
                DABlocks.SKYJADE_BLOCK.get().asItem(),
                DABlocks.STRATUS_BLOCK.get().asItem(),
                DABlocks.SQUALL_BLOCK.get().asItem()
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

        // Food & Related
        tag(Tags.Items.FOODS_BERRY).add(
                DAItems.FROZEN_GOLDEN_BERRIES.get(),
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

        tag(ItemTags.MEAT).add(
                DAItems.RAW_QUAIL.get(),
                DAItems.COOKED_QUAIL.get()
        );
        tag(ItemTags.WOLF_FOOD).add(
                DAItems.RAW_QUAIL.get(),
                DAItems.COOKED_QUAIL.get()
        );
        tag(Tags.Items.ANIMAL_FOODS).add(
                DAItems.RAW_QUAIL.get(),
                DAItems.COOKED_QUAIL.get()
        );
        tag(Tags.Items.FOODS_RAW_MEAT).add(
                DAItems.RAW_QUAIL.get()
        );
        tag(Tags.Items.FOODS_COOKED_MEAT).add(
                DAItems.COOKED_QUAIL.get()
        );

        tag(Tags.Items.FOODS_FRUIT).add(
                DAItems.GREEN_SQUASH_SLICE.get(),
                DAItems.BLUE_SQUASH_SLICE.get(),
                DAItems.PURPLE_SQUASH_SLICE.get()
        );

        // Misc.
        tag(Tags.Items.EGGS).add(
                DAItems.QUAIL_EGG.get()
        );

        tag(AetherTags.Items.SLIDER_DAMAGING_ITEMS).add(
                DAItems.SKYJADE_TOOLS_PICKAXE.get().asItem(),
                DAItems.STRATUS_PICKAXE.get().asItem()
        );

        tag(ItemTags.COMPASSES).add(
                DAItems.BRONZE_COMPASS.get(),
                DAItems.SILVER_COMPASS.get(),
                DAItems.GOLD_COMPASS.get()
        );

        tag(AetherTags.Items.NO_SKYROOT_DOUBLE_DROPS).add(
                DAItems.BRASS_DUNGEON_KEY.get()
        );

        tag(DATags.Items.IS_GOLDEN_SWET_BALL).add(
                DAItems.GOLDEN_SWET_BALL.get()
        ).addOptional(
                ResourceLocation.fromNamespaceAndPath(DeepAether.AETHER_REDUX, "golden_swet_ball")
        ).addOptional(
                ResourceLocation.fromNamespaceAndPath(DeepAether.AETHER_GENESIS,"golden_swet_ball")
        );

        tag(AetherTags.Items.AERCLOUDS).add(
                DABlocks.CHROMATIC_AERCLOUD.get().asItem(),
                DABlocks.STERLING_AERCLOUD.get().asItem(),
                DABlocks.AERSMOG.get().asItem(),
                DABlocks.AERCLOUD_GRASS_BLOCK.get().asItem(),
                DABlocks.RAIN_AERCLOUD.get().asItem()
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
    }
}
