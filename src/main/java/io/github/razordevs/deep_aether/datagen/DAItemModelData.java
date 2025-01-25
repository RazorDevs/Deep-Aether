package io.github.razordevs.deep_aether.datagen;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.data.providers.AetherItemModelProvider;
import com.aetherteam.nitrogen.data.providers.NitrogenItemModelProvider;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Iterator;

public class DAItemModelData extends AetherItemModelProvider {
    public DAItemModelData(PackOutput output, ExistingFileHelper helper) {
        super(output, DeepAether.MODID, helper);
    }
    
    
    @Override
    protected void registerModels() {
        this.itemBlock(DABlocks.HIGHSTONE.get());

        //Roseroot woodset
        this.itemBlock(DABlocks.ROSEROOT_WOOD.get());
        this.itemBlock(DABlocks.ROSEROOT_LOG.get());
        this.itemBlock(DABlocks.STRIPPED_ROSEROOT_WOOD.get());
        this.itemBlock(DABlocks.STRIPPED_ROSEROOT_LOG.get());
        this.itemBlock(DABlocks.ROSEROOT_PLANKS.get());
        this.itemBlock(DABlocks.ROSEROOT_SLAB.get());
        this.itemBlock(DABlocks.ROSEROOT_STAIRS.get());
        this.itemFence(DABlocks.ROSEROOT_FENCE.get(), DABlocks.ROSEROOT_PLANKS.get());
        this.itemBlock(DABlocks.ROSEROOT_FENCE_GATE.get());
        this.item(DABlocks.ROSEROOT_DOOR.get().asItem());
        this.itemBlock(DABlocks.ROSEROOT_TRAPDOOR.get(), "_bottom");
        this.itemButton(DABlocks.ROSEROOT_BUTTON.get(), DABlocks.ROSEROOT_PLANKS.get());
        this.itemBlock(DABlocks.ROSEROOT_PRESSURE_PLATE.get());
        this.itemWallBlock(DABlocks.ROSEROOT_WALL.get(), DABlocks.ROSEROOT_LOG.get());
        this.itemWallBlock(DABlocks.STRIPPED_ROSEROOT_WALL.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get());
        this.itemBlockFlat(DABlocks.ROSEROOT_SAPLING.get());
        this.itemBlockFlat(DABlocks.BLUE_ROSEROOT_SAPLING.get());
        this.itemBlock(DABlocks.ROSEROOT_LEAVES.get());
        this.itemBlock(DABlocks.FLOWERING_ROSEROOT_LEAVES.get());
        this.itemBlock(DABlocks.BLUE_ROSEROOT_LEAVES.get());
        this.itemBlock(DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get());
        this.itemBlock(DABlocks.AERGLOW_BLOSSOM_BLOCK.get());
        this.item(DAItems.ROSEROOT_SIGN.get());
        this.item(DAItems.ROSEROOT_HANGING_SIGN.get());
        this.item(DAItems.ROSEROOT_BOAT.get());
        this.item(DAItems.ROSEROOT_CHEST_BOAT.get());

        //Yagroot woodset
        this.itemBlock(DABlocks.YAGROOT_WOOD.get());
        this.itemBlock(DABlocks.YAGROOT_LOG.get());
        this.itemBlock(DABlocks.STRIPPED_YAGROOT_WOOD.get());
        this.itemBlock(DABlocks.STRIPPED_YAGROOT_LOG.get());
        this.itemBlock(DABlocks.YAGROOT_PLANKS.get());
        this.itemBlock(DABlocks.YAGROOT_SLAB.get());
        this.itemBlock(DABlocks.YAGROOT_STAIRS.get());
        this.itemFence(DABlocks.YAGROOT_FENCE.get(), DABlocks.YAGROOT_PLANKS.get());
        this.itemBlock(DABlocks.YAGROOT_FENCE_GATE.get());
        this.item(DABlocks.YAGROOT_DOOR.get().asItem());
        this.itemBlock(DABlocks.YAGROOT_TRAPDOOR.get(), "_bottom");
        this.itemButton(DABlocks.YAGROOT_BUTTON.get(), DABlocks.YAGROOT_PLANKS.get());
        this.itemBlock(DABlocks.YAGROOT_PRESSURE_PLATE.get());
        this.itemWallBlock(DABlocks.YAGROOT_WALL.get(), DABlocks.YAGROOT_LOG.get());
        this.itemWallBlock(DABlocks.STRIPPED_YAGROOT_WALL.get(), DABlocks.STRIPPED_YAGROOT_LOG.get());
        this.itemBlockFlat(DABlocks.YAGROOT_SAPLING.get());
        this.itemBlock(DABlocks.YAGROOT_LEAVES.get());
        this.item(DAItems.YAGROOT_SIGN.get());
        this.itemBlock(DABlocks.YAGROOT_ROOTS.get());
        this.itemBlock(DABlocks.MUDDY_YAGROOT_ROOTS.get());
        this.item(DAItems.YAGROOT_HANGING_SIGN.get());
        this.item(DAItems.YAGROOT_BOAT.get());
        this.item(DAItems.YAGROOT_CHEST_BOAT.get());

        //Cruderoot woodset
        this.itemBlock(DABlocks.CRUDEROOT_WOOD.get());
        this.itemBlock(DABlocks.CRUDEROOT_LOG.get());
        this.itemBlock(DABlocks.STRIPPED_CRUDEROOT_WOOD.get());
        this.itemBlock(DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        this.itemBlock(DABlocks.CRUDEROOT_PLANKS.get());
        this.itemBlock(DABlocks.CRUDEROOT_SLAB.get());
        this.itemBlock(DABlocks.CRUDEROOT_STAIRS.get());
        this.itemFence(DABlocks.CRUDEROOT_FENCE.get(), DABlocks.CRUDEROOT_PLANKS.get());
        this.itemBlock(DABlocks.CRUDEROOT_FENCE_GATE.get());
        this.item(DABlocks.CRUDEROOT_DOOR.get().asItem());
        this.itemBlock(DABlocks.CRUDEROOT_TRAPDOOR.get(), "_bottom");
        this.itemButton(DABlocks.CRUDEROOT_BUTTON.get(), DABlocks.CRUDEROOT_PLANKS.get());
        this.itemBlock(DABlocks.CRUDEROOT_PRESSURE_PLATE.get());
        this.itemWallBlock(DABlocks.CRUDEROOT_WALL.get(), DABlocks.CRUDEROOT_LOG.get());
        this.itemWallBlock(DABlocks.STRIPPED_CRUDEROOT_WALL.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        this.itemBlockFlat(DABlocks.CRUDEROOT_SAPLING.get());
        this.itemBlock(DABlocks.CRUDEROOT_LEAVES.get());
        this.item(DAItems.CRUDEROOT_SIGN.get());
        this.item(DAItems.CRUDEROOT_HANGING_SIGN.get());
        this.item(DAItems.CRUDEROOT_BOAT.get());
        this.item(DAItems.CRUDEROOT_CHEST_BOAT.get());

        //Conberry woodset
        this.itemBlock(DABlocks.CONBERRY_WOOD.get());
        this.itemBlock(DABlocks.CONBERRY_LOG.get());
        this.itemBlock(DABlocks.STRIPPED_CONBERRY_WOOD.get());
        this.itemBlock(DABlocks.STRIPPED_CONBERRY_LOG.get());
        this.itemBlock(DABlocks.CONBERRY_PLANKS.get());
        this.itemBlock(DABlocks.CONBERRY_SLAB.get());
        this.itemBlock(DABlocks.CONBERRY_STAIRS.get());
        this.itemFence(DABlocks.CONBERRY_FENCE.get(), DABlocks.CONBERRY_PLANKS.get());
        this.itemBlock(DABlocks.CONBERRY_FENCE_GATE.get());
        this.item(DABlocks.CONBERRY_DOOR.get().asItem());
        this.itemBlock(DABlocks.CONBERRY_TRAPDOOR.get(), "_bottom");
        this.itemButton(DABlocks.CONBERRY_BUTTON.get(), DABlocks.CONBERRY_PLANKS.get());
        this.itemBlock(DABlocks.CONBERRY_PRESSURE_PLATE.get());
        this.itemWallBlock(DABlocks.CONBERRY_WALL.get(), DABlocks.CONBERRY_LOG.get());
        this.itemWallBlock(DABlocks.STRIPPED_CONBERRY_WALL.get(), DABlocks.STRIPPED_CONBERRY_LOG.get());
        this.itemBlockFlat(DABlocks.CONBERRY_SAPLING.get());
        this.itemBlock(DABlocks.CONBERRY_LEAVES.get());
        this.item(DAItems.CONBERRY_SIGN.get());
        this.item(DAItems.CONBERRY_HANGING_SIGN.get());
        this.item(DAItems.CONBERRY_BOAT.get());
        this.item(DAItems.CONBERRY_CHEST_BOAT.get());

        //Sunroot woodset
        this.itemBlock(DABlocks.SUNROOT_WOOD.get());
        this.itemBlock(DABlocks.SUNROOT_LOG.get());
        this.itemBlock(DABlocks.STRIPPED_SUNROOT_WOOD.get());
        this.itemBlock(DABlocks.STRIPPED_SUNROOT_LOG.get());
        this.itemBlock(DABlocks.SUNROOT_PLANKS.get());
        this.itemBlock(DABlocks.SUNROOT_SLAB.get());
        this.itemBlock(DABlocks.SUNROOT_STAIRS.get());
        this.itemFence(DABlocks.SUNROOT_FENCE.get(), DABlocks.SUNROOT_PLANKS.get());
        this.itemBlock(DABlocks.SUNROOT_FENCE_GATE.get());
        this.item(DABlocks.SUNROOT_DOOR.get().asItem());
        this.itemBlock(DABlocks.SUNROOT_TRAPDOOR.get(), "_bottom");
        this.itemButton(DABlocks.SUNROOT_BUTTON.get(), DABlocks.SUNROOT_PLANKS.get());
        this.itemBlock(DABlocks.SUNROOT_PRESSURE_PLATE.get());
        this.itemWallBlock(DABlocks.SUNROOT_WALL.get(), DABlocks.SUNROOT_LOG.get());
        this.itemWallBlock(DABlocks.STRIPPED_SUNROOT_WALL.get(), DABlocks.STRIPPED_SUNROOT_LOG.get());
        this.itemBlockFlat(DABlocks.SUNROOT_SAPLING.get());
        this.itemBlock(DABlocks.SUNROOT_LEAVES.get());
        this.item(DAItems.SUNROOT_SIGN.get());
        this.item(DAItems.SUNROOT_HANGING_SIGN.get());
        this.item(DAItems.SUNROOT_BOAT.get());
        this.item(DAItems.SUNROOT_CHEST_BOAT.get());

        //Ores
        this.itemBlock(DABlocks.SKYJADE_BLOCK.get());
        this.itemBlock(DABlocks.SKYJADE_ORE.get());
        this.itemBlock(DABlocks.STRATUS_BLOCK.get());

        //Stone types
        this.itemBlock(DABlocks.COBBLED_ASETERITE.get());
        this.itemBlock(DABlocks.COBBLED_ASETERITE_STAIRS.get());
        this.itemBlock(DABlocks.COBBLED_ASETERITE_SLAB.get());
        this.itemWallBlock(DABlocks.COBBLED_ASETERITE_WALL.get(), DABlocks.COBBLED_ASETERITE.get());

        this.itemBlock(DABlocks.ASETERITE.get());
        this.itemBlock(DABlocks.ASETERITE_STAIRS.get());
        this.itemBlock(DABlocks.ASETERITE_SLAB.get());
        this.itemWallBlock(DABlocks.ASETERITE_WALL.get(), DABlocks.ASETERITE.get());

        this.itemBlock(DABlocks.POLISHED_ASETERITE.get());
        this.itemBlock(DABlocks.POLISHED_ASETERITE_STAIRS.get());
        this.itemBlock(DABlocks.POLISHED_ASETERITE_SLAB.get());

        this.itemBlock(DABlocks.ASETERITE_BRICKS.get());
        this.itemBlock(DABlocks.ASETERITE_BRICKS_STAIRS.get());
        this.itemBlock(DABlocks.ASETERITE_BRICKS_SLAB.get());
        this.itemWallBlock(DABlocks.ASETERITE_BRICKS_WALL.get(), DABlocks.ASETERITE_BRICKS.get());

        this.itemBlock(DABlocks.RAW_CLORITE.get());
        this.itemBlock(DABlocks.RAW_CLORITE_STAIRS.get());
        this.itemBlock(DABlocks.RAW_CLORITE_SLAB.get());
        this.itemBlock(DABlocks.CLORITE.get());
        this.itemBlock(DABlocks.CLORITE_STAIRS.get());
        this.itemBlock(DABlocks.CLORITE_SLAB.get());
        this.itemWallBlock(DABlocks.CLORITE_WALL.get(), DABlocks.CLORITE.get());
        this.itemWallBlock(DABlocks.RAW_CLORITE_WALL.get(), DABlocks.RAW_CLORITE.get());
        this.itemBlock(DABlocks.POLISHED_CLORITE.get());
        this.itemBlock(DABlocks.POLISHED_CLORITE_STAIRS.get());
        this.itemBlock(DABlocks.POLISHED_CLORITE_SLAB.get());
        this.itemBlock(DABlocks.CLORITE_PILLAR.get());

        //Tiles
        this.itemBlock(DABlocks.HOLYSTONE_TILES.get());
        this.itemBlock(DABlocks.HOLYSTONE_TILE_STAIRS.get());
        this.itemBlock(DABlocks.HOLYSTONE_TILE_SLAB.get());
        this.itemWallBlock(DABlocks.HOLYSTONE_TILE_WALL.get(), DABlocks.HOLYSTONE_TILES.get());

        this.itemBlock(DABlocks.MOSSY_HOLYSTONE_BRICKS.get());
        this.itemBlock(DABlocks.MOSSY_HOLYSTONE_BRICK_STAIRS.get());
        this.itemBlock(DABlocks.MOSSY_HOLYSTONE_BRICK_SLAB.get());
        this.itemWallBlock(DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());

        this.itemBlock(DABlocks.GILDED_HOLYSTONE_BRICKS.get());
        this.itemBlock(DABlocks.GILDED_HOLYSTONE_BRICK_STAIRS.get());
        this.itemBlock(DABlocks.GILDED_HOLYSTONE_BRICK_SLAB.get());
        this.itemWallBlock(DABlocks.GILDED_HOLYSTONE_BRICK_WALL.get(), DABlocks.GILDED_HOLYSTONE_BRICKS.get());

        this.itemBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get());
        this.itemBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_STAIRS.get());
        this.itemBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_SLAB.get());
        this.itemWallBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_WALL.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get());

        this.itemBlock(DABlocks.BIG_HOLYSTONE_BRICKS.get());
        this.itemBlock(DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get());
        this.itemBlock(DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get());
        this.itemWallBlock(DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());

        this.itemBlock(DABlocks.MOSSY_HOLYSTONE_TILES.get());
        this.itemBlock(DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get());
        this.itemBlock(DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get());
        this.itemWallBlock(DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(), DABlocks.MOSSY_HOLYSTONE_TILES.get());

        this.itemBlock(DABlocks.GILDED_HOLYSTONE_TILES.get());
        this.itemBlock(DABlocks.GILDED_HOLYSTONE_TILE_STAIRS.get());
        this.itemBlock(DABlocks.GILDED_HOLYSTONE_TILE_SLAB.get());
        this.itemWallBlock(DABlocks.GILDED_HOLYSTONE_TILE_WALL.get(), DABlocks.GILDED_HOLYSTONE_TILES.get());

        this.itemBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());
        this.itemBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_STAIRS.get());
        this.itemBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_SLAB.get());
        this.itemWallBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());

        this.itemBlock(DABlocks.HOLYSTONE_PILLAR.get());
        this.itemBlock(DABlocks.HOLYSTONE_PILLAR_UP.get());
        this.itemBlock(DABlocks.HOLYSTONE_PILLAR_DOWN.get());
        this.itemBlock(DABlocks.CHISELED_HOLYSTONE.get());

        //Brass Dungeon
        this.itemBlock(DABlocks.NIMBUS_STONE.get());
        this.itemBlock(DABlocks.LIGHT_NIMBUS_STONE.get());
        this.itemBlock(DABlocks.NIMBUS_STAIRS.get());
        this.itemBlock(DABlocks.NIMBUS_SLAB.get());
        this.itemWallBlock(DABlocks.NIMBUS_WALL.get(), DABlocks.NIMBUS_STONE.get());
        this.itemLockedDungeonBlock(DABlocks.LOCKED_NIMBUS_STONE.get(), DABlocks.NIMBUS_STONE.get());
        this.itemLockedDungeonBlock(DABlocks.LOCKED_LIGHT_NIMBUS_STONE.get(), DABlocks.LIGHT_NIMBUS_STONE.get());
        this.itemTrappedDungeonBlock(DABlocks.TRAPPED_NIMBUS_STONE.get(), DABlocks.NIMBUS_STONE.get());
        this.itemTrappedDungeonBlock(DABlocks.TRAPPED_LIGHT_NIMBUS_STONE.get(), DABlocks.LIGHT_NIMBUS_STONE.get());
        this.itemBossDoorwayDungeonBlock(DABlocks.BOSS_DOORWAY_NIMBUS_STONE.get(), DABlocks.NIMBUS_STONE.get());
        this.itemBossDoorwayDungeonBlock(DABlocks.BOSS_DOORWAY_LIGHT_NIMBUS_STONE.get(), DABlocks.LIGHT_NIMBUS_STONE.get());
        this.itemTreasureDoorwayDungeonBlock(DABlocks.TREASURE_DOORWAY_NIMBUS_STONE.get(), DABlocks.NIMBUS_STONE.get());
        this.itemTreasureDoorwayDungeonBlock(DABlocks.TREASURE_DOORWAY_LIGHT_NIMBUS_STONE.get(), DABlocks.LIGHT_NIMBUS_STONE.get());

        this.itemBlock(DABlocks.NIMBUS_PILLAR.get());
        this.itemBlock(DABlocks.LIGHT_NIMBUS_PILLAR.get());
        this.itemLockedDungeonBlock(DABlocks.LOCKED_NIMBUS_PILLAR.get(), DABlocks.NIMBUS_PILLAR.get());
        this.itemLockedDungeonBlock(DABlocks.LOCKED_LIGHT_NIMBUS_PILLAR.get(), DABlocks.LIGHT_NIMBUS_PILLAR.get());
        this.itemTrappedDungeonBlock(DABlocks.TRAPPED_NIMBUS_PILLAR.get(), DABlocks.NIMBUS_PILLAR.get());
        this.itemTrappedDungeonBlock(DABlocks.TRAPPED_LIGHT_NIMBUS_PILLAR.get(), DABlocks.LIGHT_NIMBUS_PILLAR.get());
        this.itemBossDoorwayDungeonBlock(DABlocks.BOSS_DOORWAY_NIMBUS_PILLAR.get(), DABlocks.NIMBUS_PILLAR.get());
        this.itemBossDoorwayDungeonBlock(DABlocks.BOSS_DOORWAY_LIGHT_NIMBUS_PILLAR.get(), DABlocks.LIGHT_NIMBUS_PILLAR.get());
        this.itemTreasureDoorwayDungeonBlock(DABlocks.TREASURE_DOORWAY_NIMBUS_PILLAR.get(), DABlocks.NIMBUS_PILLAR.get());
        this.itemTreasureDoorwayDungeonBlock(DABlocks.TREASURE_DOORWAY_LIGHT_NIMBUS_PILLAR.get(), DABlocks.LIGHT_NIMBUS_PILLAR.get());

        //Plants
        this.itemBlockFlat(DABlocks.AERLAVENDER.get());
        this.itemBlockFlat(DABlocks.TALL_AERLAVENDER.get());
        this.itemBlockFlat(DABlocks.AETHER_CATTAILS.get());
        this.itemBlockFlatName(DABlocks.TALL_AETHER_CATTAILS.get(), "tall_aether_cattails_top");
        this.itemBlockFlat(DABlocks.RADIANT_ORCHID.get());
        this.itemBlockFlat(DABlocks.LIGHTCAP_MUSHROOMS.get());

        this.itemBlock(DABlocks.BLUE_SQUASH.get());
        this.itemBlock(DABlocks.GREEN_SQUASH.get());
        this.itemBlock(DABlocks.PURPLE_SQUASH.get());

        this.itemBlock(DABlocks.CLOUDBLOOM_CARPET.get());

        this.item(DAItems.VIRULENT_QUICKSAND_BUCKET.get());
        this.item(DAItems.SKYROOT_VIRULENT_QUICKSAND_BUCKET.get());

        this.itemBlock(DABlocks.RAIN_AERCLOUD.get());
        this.itemBlock(DABlocks.AERSMOG.get());
        this.itemBlock(DABlocks.STERLING_AERCLOUD.get());
        this.itemBlock(DABlocks.CHROMATIC_AERCLOUD.get());
        this.itemBlock(DABlocks.AERCLOUD_ROOT_CARPET.get());
        this.itemBlockFlat(DABlocks.PINK_AERCLOUD_MUSHROOMS.get());
        this.itemBlockFlat(DABlocks.BLUE_AERCLOUD_MUSHROOMS.get());

        this.itemBlockFlat(DABlocks.MINI_GOLDEN_GRASS.get());
        this.itemBlockFlat(DABlocks.SHORT_GOLDEN_GRASS.get());
        this.itemBlockFlat(DABlocks.MEDIUM_GOLDEN_GRASS.get());
        this.itemBlockFlatName(DABlocks.TALL_GOLDEN_GRASS.get(), "tall_golden_grass_top");

        //Genesis compat
        this.itemLogWallBlock(DABlocks.ROSEROOT_LOG_WALL.get(), DABlocks.ROSEROOT_LOG.get(), "", DeepAether.MODID);
        this.itemLogWallBlock(DABlocks.STRIPPED_ROSEROOT_LOG_WALL.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get(), "", DeepAether.MODID);
        this.itemLogWallBlock(DABlocks.CRUDEROOT_LOG_WALL.get(), DABlocks.CRUDEROOT_LOG.get(), "", DeepAether.MODID);
        this.itemLogWallBlock(DABlocks.STRIPPED_CRUDEROOT_LOG_WALL.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get(), "", DeepAether.MODID);
        this.itemLogWallBlock(DABlocks.YAGROOT_LOG_WALL.get(), DABlocks.YAGROOT_LOG.get(), "", DeepAether.MODID);
        this.itemLogWallBlock(DABlocks.STRIPPED_YAGROOT_LOG_WALL.get(), DABlocks.STRIPPED_YAGROOT_LOG.get(), "", DeepAether.MODID);
        this.itemLogWallBlock(DABlocks.CONBERRY_LOG_WALL.get(), DABlocks.CONBERRY_LOG.get(), "", DeepAether.MODID);
        this.itemLogWallBlock(DABlocks.STRIPPED_CONBERRY_LOG_WALL.get(), DABlocks.STRIPPED_CONBERRY_LOG.get(), "", DeepAether.MODID);
        this.itemLogWallBlock(DABlocks.SUNROOT_LOG_WALL.get(), DABlocks.SUNROOT_LOG.get(), "", DeepAether.MODID);
        this.itemLogWallBlock(DABlocks.STRIPPED_SUNROOT_LOG_WALL.get(), DABlocks.STRIPPED_SUNROOT_LOG.get(), "", DeepAether.MODID);


        //Flawless boss drops
        this.item(DAItems.AFTERBURNER.get());
        this.item(DAItems.SUN_CORE.get());
        this.item(DAItems.AERWHALE_SADDLE.get());
        this.item(DAItems.SLIDER_EYE.get());
        this.item(DAItems.MEDAL_OF_HONOR.get());

        //Skyjade set
        this.item(DAItems.SKYJADE.get());
        this.item(DAItems.SKYJADE_NUGGET.get());
        this.handHeld(DAItems.SKYJADE_TOOLS_SWORD.get());
        this.handHeld(DAItems.SKYJADE_TOOLS_AXE.get());
        this.handHeld(DAItems.SKYJADE_TOOLS_PICKAXE.get());
        this.handHeld(DAItems.SKYJADE_TOOLS_SHOVEL.get());
        this.handHeld(DAItems.SKYJADE_TOOLS_HOE.get());

        this.bootsItem(DAItems.SKYJADE_BOOTS.get());
        this.leggingsItem(DAItems.SKYJADE_LEGGINGS.get());
        this.chestplateItem(DAItems.SKYJADE_CHESTPLATE.get());
        this.helmetItem(DAItems.SKYJADE_HELMET.get());
        this.glovesItem(DAItems.SKYJADE_GLOVES.get());
        this.item(DAItems.SKYJADE_RING.get());
        //this.item(DAItems.SKYJADE_MOA_ARMOR.get());

        //Stratus set
        this.item(DAItems.STRATUS_INGOT.get());
        this.handHeld(DAItems.STRATUS_SWORD.get());
        this.handHeld(DAItems.STRATUS_AXE.get());
        this.handHeld(DAItems.STRATUS_PICKAXE.get());
        this.handHeld(DAItems.STRATUS_SHOVEL.get());
        this.handHeld(DAItems.STRATUS_HOE.get());

        this.bootsItem(DAItems.STRATUS_BOOTS.get());
        this.leggingsItem(DAItems.STRATUS_LEGGINGS.get());
        this.chestplateItem(DAItems.STRATUS_CHESTPLATE.get());
        this.helmetItem(DAItems.STRATUS_HELMET.get());
        this.glovesItem(DAItems.STRATUS_GLOVES.get());

        this.bootsItem(DAItems.STORMFORGED_BOOTS.get());
        this.leggingsItem(DAItems.STORMFORGED_LEGGINGS.get());
        this.chestplateItem(DAItems.STORMFORGED_CHESTPLATE.get());
        this.helmetItem(DAItems.STORMFORGED_HELMET.get());
        this.glovesItem(DAItems.STORMFORGED_GLOVES.get());

        this.item(DAItems.STRATUS_RING.get());
        this.item(DAItems.STRATUS_SMITHING_TEMPLATE.get());

        this.item(DAItems.GRAVITITE_RING.get());

        //Brass loot
        this.bowItem(DAItems.STORM_BOW.get());
        this.handHeld(DAItems.STORM_SWORD.get());
        this.item(DAItems.WIND_SHIELD.get());
        this.item(DAItems.AERCLOUD_NECKLACE.get());
        this.translucentItem(DAItems.CLOUD_CAPE.get());

        //Food
        this.item(DAItems.RAW_AERGLOW_FISH.get());
        this.item(DAItems.COOKED_AERGLOW_FISH.get());
        this.item(DAItems.AERGLOW_FISH_BUCKET.get());
        this.item(DAItems.SKYROOT_AERGLOW_FISH_BUCKET.get());

        this.item(DAItems.RAW_QUAIL.get());
        this.item(DAItems.COOKED_QUAIL.get());
        this.item(DAItems.QUAIL_EGG.get());

        this.item(DAItems.GOLDEN_BERRIES.get());
        this.item(DAItems.ANTIDOTE.get());
        this.altItem(DAItems.ENCHANTED_ANTIDOTE.get(), DAItems.ANTIDOTE.get());

        //Plants
        this.itemBlockFlat(DABlocks.MEDIUM_GOLDEN_GRASS.get());
        this.itemBlockFlat(DABlocks.SHORT_GOLDEN_GRASS.get());
        this.itemBlockFlat(DABlocks.MINI_GOLDEN_GRASS.get());
        this.itemBlockFlatName(DABlocks.TALL_GOLDEN_GRASS.get(), "tall_golden_grass_top");
        this.itemBlock(DABlocks.GOLDEN_GRASS_BLOCK.get());

        this.itemBlock(DABlocks.AERCLOUD_GRASS_BLOCK.get());
        this.itemBlock(DABlocks.AERCLOUD_ROOTS.get());

        this.itemBlockFlat(DABlocks.FEATHER_GRASS.get());
        this.itemBlockFlatName(DABlocks.TALL_FEATHER_GRASS.get(), "tall_feather_grass_top");
        this.itemBlockFlatName(DABlocks.TALL_ALIEN_PLANT.get(), "tall_alien_plant_top");

        this.itemBlockFlatName(DABlocks.GOLDEN_FLOWER.get(), "golden_flower_top");
        this.itemBlockFlat(DABlocks.ENCHANTED_BLOSSOM.get());
        this.itemBlockFlat(DABlocks.SKY_TULIPS.get());
        this.itemBlockFlat(DABlocks.IASPOVE.get());
        this.itemBlockFlat(DABlocks.GOLDEN_ASPESS.get());
        this.itemBlockFlat(DABlocks.ECHAISY.get());
        this.item(DAItems.CLOUDBLOOM_BOUQUET.get());

        this.item(DAItems.GOLDEN_GRASS_SEEDS.get());
        this.item(DAItems.SQUASH_SEEDS.get());
        this.item(DAItems.GOLDEN_SWET_BALL.get());
        this.itemBlockFlatName(DABlocks.GLOWING_SPORES.get(), "glowing_spores_item");

        this.item(DAItems.GREEN_SQUASH_SLICE.get());
        this.item(DAItems.BLUE_SQUASH_SLICE.get());
        this.item(DAItems.PURPLE_SQUASH_SLICE.get());


        //Spawn eggs
        this.eggItem(DAItems.AETHER_FISH_SPAWN_EGG.get());
        this.eggItem(DAItems.QUAIL_SPAWN_EGG.get());
        this.eggItem(DAItems.VENOMITE_SPAWN_EGG.get());
        this.eggItem(DAItems.WINDFLY_SPAWN_EGG.get());
        this.eggItem(DAItems.BABY_ZEPHYR_SPAWN_EGG.get());
        this.eggItem(DAItems.GENTLE_WIND_SPAWN_EGG.get());

        //Misc
        this.item(DAItems.CHAOS_EMERALD.get());
        this.item(DAItems.BRASS_DUNGEON_KEY.get());
        this.item(DAItems.SPOOKY_RING.get());
        this.item(DAItems.BIO_CRYSTAL.get());
        this.item(DABlocks.SKYJADE_CHAIN.asItem());
        this.item(DABlocks.SKYJADE_LANTERN.asItem());
        this.item(DAItems.MUSIC_DISC_NABOORU.get());
        this.item(DAItems.MUSIC_DISC_A_MORNING_WISH.get());
        this.item(DAItems.MUSIC_DISC_CYCLONE.get());
        this.item(DAItems.AERGLOW_BLOSSOM.get());
        this.item(DAItems.PLACEABLE_POISON_BUCKET.get());
        this.item(DAItems.FROZEN_GOLDEN_BERRIES.get());
        this.itemBlock(DABlocks.COMBINER.get());
        this.item(DAItems.MUSIC_DISC_ATTA.get());
        this.item(DAItems.MUSIC_DISC_FAENT.get());
        this.item(DAItems.MUSIC_DISC_HIMININN.get());

//        this.item(DAItems.SUN_CLOCK.get());

//        this.item(DAItems.BRONZE_COMPASS.get());
//        this.item(DAItems.SILVER_COMPASS.get());
//        this.item(DAItems.GOLD_COMPASS.get());
    }

    public void translucentItem(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .renderType(ResourceLocation.withDefaultNamespace("translucent"))
                .texture("layer0", modLoc("item/" + this.itemName(item)));


    }

    public void handHeld(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/handheld"))
                .texture("layer0", this.modLoc("item/"  + this.itemName(item)));
    }

    public void item(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + this.itemName(item)));
    }

    public void altItem(Item item, Item texture) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + this.itemName(texture)));
    }

    public void placeholder(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/placeholder"));
    }

    public void itemFence(Block block, Block baseBlock) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/fence_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock)));
    }

    public void itemBlockFlatName(Block block, String location) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/generated")).texture("layer0", this.texture(location));
    }
    public void itemButton(Block block, Block baseBlock) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/button_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock)));
    }

    public void itemWallBlock(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.texture(this.blockName(baseBlock)));
    }

    public void itemBlockFlat(Block block) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/generated"))
                .texture("layer0", this.texture(this.blockName(block)));
    }

    public void helmetItem(Item item) {
        this.armorItem(item, "helmet");
    }

    public void chestplateItem(Item item) {
        this.armorItem(item, "chestplate");
    }

    public void leggingsItem(Item item) {
        this.armorItem(item, "leggings");
    }

    public void bootsItem(Item item) {
        this.armorItem(item, "boots");
    }

    public void armorItem(Item item, String type) {
        ItemModelBuilder builder = this.withExistingParent(this.itemName(item), this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + this.itemName(item)));
        double index = 0.1;

        for(Iterator<ResourceKey<TrimMaterial>> var7 = VANILLA_TRIM_MATERIALS.iterator(); var7.hasNext(); index += 0.1) {
            ResourceKey<TrimMaterial> trimMaterial = var7.next();
            String material = trimMaterial.location().getPath();
            String var10000 = this.itemName(item);
            String name = var10000 + "_" + material + "_trim";
            this.withExistingParent(name, this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + this.itemName(item))).texture("layer1", this.mcLoc("trims/items/" + type + "_trim_" + material));
            builder.override().predicate(ResourceLocation.withDefaultNamespace("trim_type"), (float)index).model(this.getExistingFile(this.modLoc("item/" + name))).end();
        }

    }

    public void glovesItem(Item item) {
        ItemModelBuilder builder = this.withExistingParent(this.itemName(item), this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/"  + this.itemName(item)));
        double index = 0.1;

        for(Iterator<ResourceKey<TrimMaterial>> var6 = NitrogenItemModelProvider.VANILLA_TRIM_MATERIALS.iterator(); var6.hasNext(); index += 0.1) {
            ResourceKey<TrimMaterial> trimMaterial = var6.next();
            String material = trimMaterial.location().getPath();
            String var10000 = this.itemName(item);
            String name = var10000 + "_" + material + "_trim";
            this.withExistingParent(name, this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + this.itemName(item))).texture("layer1", ResourceLocation.fromNamespaceAndPath(Aether.MODID,"trims/items/gloves_trim_" + material));
            builder.override().predicate(ResourceLocation.withDefaultNamespace("trim_type"), (float)index).model(this.getExistingFile(this.modLoc("item/" + name))).end();
        }
    }
    public void bowItem(Item item) {
        //Normal
        this.withExistingParent(this.itemName(item) + "_pulling_0", this.mcLoc("item/bow")).texture("layer0", this.modLoc("item/" + this.itemName(item) + "_pulling_0"));
        this.withExistingParent(this.itemName(item) + "_pulling_1", this.mcLoc("item/bow")).texture("layer0", this.modLoc("item/" + this.itemName(item) + "_pulling_1"));
        this.withExistingParent(this.itemName(item) + "_pulling_2", this.mcLoc("item/bow")).texture("layer0", this.modLoc("item/" + this.itemName(item) + "_pulling_2"));
        //Special
        this.withExistingParent(this.itemName(item) + "_pulling_0_special", this.mcLoc("item/bow")).texture("layer0", this.modLoc("item/" + this.itemName(item) + "_pulling_0_special"));
        this.withExistingParent(this.itemName(item) + "_pulling_1_special", this.mcLoc("item/bow")).texture("layer0", this.modLoc("item/" + this.itemName(item) + "_pulling_1_special"));
        this.withExistingParent(this.itemName(item) + "_pulling_2_special", this.mcLoc("item/bow")).texture("layer0", this.modLoc("item/" + this.itemName(item) + "_pulling_2_special"));

        this.withExistingParent(this.itemName(item), this.mcLoc("item/bow"))
                //Normal
                .texture("layer0", this.modLoc("item/" + this.itemName(item)))
                .override().predicate(ResourceLocation.withDefaultNamespace("pulling"), 1).model(this.getExistingFile(this.modLoc("item/" + this.itemName(item) + "_pulling_0"))).end()
                .override().predicate(ResourceLocation.withDefaultNamespace("pulling"), 1).predicate(ResourceLocation.withDefaultNamespace("pull"), 0.65F).model(this.getExistingFile(this.modLoc("item/" + this.itemName(item) + "_pulling_1"))).end()
                .override().predicate(ResourceLocation.withDefaultNamespace("pulling"), 1).predicate(ResourceLocation.withDefaultNamespace("pull"), 0.9F).model(this.getExistingFile(this.modLoc("item/" + this.itemName(item) + "_pulling_2"))).end()
                //Special
                .override().predicate(ResourceLocation.withDefaultNamespace("pulling"), 1).predicate(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "enchanted"), 1.0F)
                .model(this.getExistingFile(this.modLoc("item/" + this.itemName(item) + "_pulling_0_special"))).end()
                .override().predicate(ResourceLocation.withDefaultNamespace("pulling"), 1).predicate(ResourceLocation.withDefaultNamespace("pull"), 0.65F).predicate(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "enchanted"), 1.0F)
                .model(this.getExistingFile(this.modLoc("item/" + this.itemName(item) + "_pulling_1_special"))).end()
                .override().predicate(ResourceLocation.withDefaultNamespace("pulling"), 1).predicate(ResourceLocation.withDefaultNamespace("pull"), 0.9F).predicate(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "enchanted"), 1.0F)
                .model(this.getExistingFile(this.modLoc("item/" + this.itemName(item) + "_pulling_2_special"))).end();
    }


    public void itemOverlayDungeonBlock(Block block, Block baseBlock, String overlay) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/cube")).texture("overlay", ResourceLocation.fromNamespaceAndPath("aether", "block/dungeon/" + overlay)).texture("face", this.texture(this.blockName(baseBlock))).element().from(0.0F, 0.0F, 0.0F).to(16.0F, 16.0F, 16.0F).allFaces((direction, builder) -> builder.texture("#face").cullface(direction).end()).end().element().from(0.0F, 0.0F, -0.1F).to(16.0F, 16.0F, -0.1F).rotation().angle(0.0F).axis(Direction.Axis.Y).origin(8.0F, 8.0F, 6.9F).end().face(Direction.NORTH).texture("#overlay").emissivity(15, 15).end().end().transforms().transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(75.0F, 45.0F, 0.0F).translation(0.0F, 2.5F, 0.0F).scale(0.375F, 0.375F, 0.375F).end().transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(75.0F, 45.0F, 0.0F).translation(0.0F, 2.5F, 0.0F).scale(0.375F, 0.375F, 0.375F).end().transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(-90.0F, -180.0F, -45.0F).scale(0.4F, 0.4F, 0.4F).end().transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(-90.0F, -180.0F, -45.0F).scale(0.4F, 0.4F, 0.4F).end().transform(ItemDisplayContext.GROUND).rotation(90.0F, 0.0F, 0.0F).translation(0.0F, 3.0F, 0.0F).scale(0.25F, 0.25F, 0.25F).end().transform(ItemDisplayContext.GUI).rotation(30.0F, 135.0F, 0.0F).scale(0.625F, 0.625F, 0.625F).end().transform(ItemDisplayContext.FIXED).scale(0.5F, 0.5F, 0.5F).end().end();
    }
}