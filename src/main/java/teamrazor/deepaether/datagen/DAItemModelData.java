package teamrazor.deepaether.datagen;

import com.aetherteam.aether.data.providers.AetherItemModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

public class DAItemModelData extends AetherItemModelProvider {
    public DAItemModelData(PackOutput output, ExistingFileHelper helper) {
        super(output, DeepAetherMod.MODID, helper);
    }
    
    
    @Override
    protected void registerModels() {

        //BLOCKITEMS

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
        this.itemBlock(DABlocks.AERGLOW_PETAL_BLOCK.get());
        this.item(DABlocks.ROSEROOT_SIGN.get().asItem());


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
        this.item(DABlocks.YAGROOT_SIGN.get().asItem());
        this.itemBlock(DABlocks.YAGROOT_ROOTS.get());
        this.itemBlock(DABlocks.MUDDY_YAGROOT_ROOTS.get());



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
        this.item(DABlocks.CRUDEROOT_SIGN.get().asItem());


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
        this.item(DABlocks.CONBERRY_SIGN.get().asItem());



        this.itemBlock(DABlocks.AETHER_MUD.get());
        this.itemBlock(DABlocks.PACKED_AETHER_MUD.get());
        this.itemBlock(DABlocks.AETHER_MUD_BRICKS.get());
        this.itemBlock(DABlocks.AETHER_MUD_BRICKS_SLAB.get());
        this.itemBlock(DABlocks.AETHER_MUD_BRICKS_STAIRS.get());


        this.itemBlock(DABlocks.SKYJADE_BLOCK.get());
        this.itemBlock(DABlocks.SKYJADE_ORE.get());

        //this.itemBlock(DABlocks.AGATE_ORE.get());
        //this.itemBlock(DABlocks.HIGHSTONE_AGATE_ORE.get());
        //this.itemBlock(DABlocks.AGATE_BLOCK.get());

        //this.itemBlock(DABlocks.ADIBIUM_ORE.get());
        //this.itemBlock(DABlocks.HIGHSTONE_ADIBIUM_ORE.get());
        //this.itemBlock(DABlocks.ADIBIUM_BLOCK.get());

        //this.itemBlock(DABlocks.ORATIE_BLOCK.get());
        //this.itemBlock(DABlocks.ORATIE_ORE.get());
        //this.itemBlock(DABlocks.RAW_ORATIE_BLOCK.get());
        //this.itemBlock(DABlocks.HIGHSTONE_ORATIE_ORE.get());

        this.itemBlock(DABlocks.CLOUDIUM_DEBRIS.get());
        this.itemBlock(DABlocks.CLOUDIUM_BLOCK.get());

        //this.item(DAItems.ADIBIUM_GEMSTONE.get());
        //this.item(DAItems.AGATE_GEMSTONE.get());
        //this.item(DAItems.RAW_ORATIE.get());
        //this.item(DAItems.ORATIE_INGOT.get());

        this.itemBlock(DABlocks.ASETERITE.get());
        this.itemBlock(DABlocks.ASETERITE_STAIRS.get());
        this.itemBlock(DABlocks.ASETERITE_SLAB.get());
        this.itemWallBlock(DABlocks.ASETERITE_WALL.get(), DABlocks.ASETERITE.get());
        this.itemBlock(DABlocks.POLISHED_ASETERITE.get());
        this.itemBlock(DABlocks.POLISHED_ASETERITE_STAIRS.get());
        this.itemBlock(DABlocks.POLISHED_ASETERITE_SLAB.get());

        this.itemBlock(DABlocks.CLORITE.get());
        this.itemBlock(DABlocks.CLORITE_STAIRS.get());
        this.itemBlock(DABlocks.CLORITE_SLAB.get());
        this.itemWallBlock(DABlocks.CLORITE_WALL.get(), DABlocks.CLORITE.get());
        this.itemBlock(DABlocks.POLISHED_CLORITE.get());
        this.itemBlock(DABlocks.POLISHED_CLORITE_STAIRS.get());
        this.itemBlock(DABlocks.POLISHED_CLORITE_SLAB.get());
        this.itemBlock(DABlocks.CLORITE_PILLAR.get());

        this.itemBlock(DABlocks.HOLYSTONE_TILES.get());
        this.itemBlock(DABlocks.HOLYSTONE_TILE_STAIRS.get());
        this.itemBlock(DABlocks.HOLYSTONE_TILE_SLAB.get());
        this.itemWallBlock(DABlocks.HOLYSTONE_TILE_WALL.get(), DABlocks.HOLYSTONE_TILES.get());

        this.itemBlock(DABlocks.MOSSY_HOLYSTONE_TILES.get());
        this.itemBlock(DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get());
        this.itemBlock(DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get());
        this.itemWallBlock(DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(), DABlocks.MOSSY_HOLYSTONE_TILES.get());


        this.itemBlockFlat(DABlocks.AERLAVENDER.get());
        this.itemBlockFlat(DABlocks.TALL_AERLAVENDER.get());
        this.itemBlockFlat(DABlocks.AETHER_CATTAILS.get());
        this.itemBlockFlatName(DABlocks.TALL_AETHER_CATTAILS.get(), "tall_aether_cattails_top");
        this.itemBlockFlat(DABlocks.RADIANT_ORCHID.get());

        this.itemBlock(DABlocks.AETHER_MOSS_CARPET.get());
        this.itemBlock(DABlocks.AETHER_MOSS_BLOCK.get());

        this.item(DAItems.VIRULENT_QUICKSAND_BUCKET.get());
        this.item(DAItems.SKYROOT_VIRULENT_QUICKSAND_BUCKET.get());

        this.itemBlock(DABlocks.RAIN_AERCLOUD.get());

        this.itemBlockFlat(DABlocks.MINI_GOLDEN_GRASS.get());
        this.itemBlockFlat(DABlocks.SHORT_GOLDEN_GRASS.get());
        this.itemBlockFlat(DABlocks.MEDIUM_GOLDEN_GRASS.get());
        this.itemBlockFlatName(DABlocks.TALL_GOLDEN_GRASS.get(), "tall_golden_grass_top");
        //ITEMS
        this.item(DAItems.SKYJADE.get());
        this.handheldItem(DAItems.SKYJADE_TOOLS_SWORD.get());
        this.handheldItem(DAItems.SKYJADE_TOOLS_AXE.get());
        this.handheldItem(DAItems.SKYJADE_TOOLS_PICKAXE.get());
        this.handheldItem(DAItems.SKYJADE_TOOLS_SHOVEL.get());
        this.handheldItem(DAItems.SKYJADE_TOOLS_HOE.get());
        this.item(DAItems.SKYJADE_ARMOR_BOOTS.get());
        this.item(DAItems.SKYJADE_ARMOR_LEGGINGS.get());
        this.item(DAItems.SKYJADE_ARMOR_CHESTPLATE.get());
        this.item(DAItems.SKYJADE_ARMOR_HELMET.get());
        this.item(DAItems.SKYJADE_RING.get());
        this.item(DAItems.SKYJADE_GLOVES.get());


        this.item(DAItems.CLOUDIUM_SCRAP.get());
        this.item(DAItems.CLOUDIUM_INGOT.get());
        this.handheldItem(DAItems.CLOUDIUM_SWORD.get());
        this.handheldItem(DAItems.CLOUDIUM_AXE.get());
        this.handheldItem(DAItems.CLOUDIUM_PICKAXE.get());
        this.handheldItem(DAItems.CLOUDIUM_SHOVEL.get());
        this.handheldItem(DAItems.CLOUDIUM_HOE.get());
        this.item(DAItems.CLOUDIUM_BOOTS.get());
        this.item(DAItems.CLOUDIUM_LEGGINGS.get());
        this.item(DAItems.CLOUDIUM_CHESTPLATE.get());
        this.item(DAItems.CLOUDIUM_HELMET.get());
        this.item(DAItems.CLOUDIUM_RING.get());
        this.item(DAItems.CLOUDIUM_GLOVES.get());

        this.item(DAItems.GRAVITIE_RING.get());

        this.item(DAItems.RAW_AERGLOW_FISH.get());
        this.item(DAItems.COOKED_AERGLOW_FISH.get());
        this.item(DAItems.AERGLOW_FISH_BUCKET.get());
        this.item(DAItems.SKYROOT_AERGLOW_FISH_BUCKET.get());

        this.item(DAItems.RAW_QUAIL.get());
        this.item(DAItems.COOKED_QUAIL.get());
        this.item(DAItems.QUAIL_EGG.get());

        this.item(DAItems.ROSEROOT_BOAT.get());
        this.item(DAItems.ROSEROOT_CHEST_BOAT.get());
        this.item(DAItems.YAGROOT_BOAT.get());
        this.item(DAItems.YAGROOT_CHEST_BOAT.get());
        this.item(DAItems.CRUDEROOT_BOAT.get());
        this.item(DAItems.CRUDEROOT_CHEST_BOAT.get());
        this.item(DAItems.CONBERRY_BOAT.get());
        this.item(DAItems.CONBERRY_CHEST_BOAT.get());

        this.item(DAItems.MUSIC_DISC_NABOORU.get());
        this.item(DAItems.MUSIC_DISC_A_MORNING_WISH.get());

        this.eggItem(DAItems.AETHER_FISH_SPAWN_EGG.get());
        this.eggItem(DAItems.QUAIL_SPAWN_EGG.get());

        this.item(DAItems.AERGLOW_PETAL.get());
        this.item(DAItems.PLACEABLE_POISON_BUCKET.get());
        this.itemBlockFlat(DABlocks.MEDIUM_GOLDEN_GRASS.get());
        this.itemBlockFlat(DABlocks.SHORT_GOLDEN_GRASS.get());
        this.itemBlockFlat(DABlocks.MINI_GOLDEN_GRASS.get());
        this.itemBlockFlatName(DABlocks.TALL_GOLDEN_GRASS.get(), "tall_golden_grass_top");
        this.itemBlock(DABlocks.GOLDEN_GRASS_BLOCK.get());

        this.item(DAItems.GOLDEN_BERRIES.get());
        this.itemBlock(DABlocks.VIRULENT_QUICKSAND.get());
        this.itemBlockFlat(DABlocks.GOLDEN_FLOWER.get());

        this.item(DAItems.GOLDEN_GRASS_SEEDS.get());
        this.item(DAItems.GOLDEN_SWET_BALL.get());

        this.itemWallBlock(DABlocks.AETHER_MUD_BRICKS_WALL.get(), DABlocks.AETHER_MUD_BRICKS.get());

        this.itemBlock(DABlocks.RED_AERCLOUD.get());
    }

    public void handheldItem(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/handheld"))
                .texture("layer0", this.modLoc("item/"  + this.itemName(item)));
    }

    public void item(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + this.itemName(item)));
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
    public void eggItem(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/template_spawn_egg"));
    }
    public void itemWallBlock(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.texture(this.blockName(baseBlock)));
    }

    public void itemBlockFlat(Block block) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/generated"))
                .texture("layer0", this.texture(this.blockName(block)));
    }
}