package teamrazor.deepaether.datagen;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.data.providers.AetherItemModelProvider;
import com.aetherteam.nitrogen.data.providers.NitrogenItemModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

import java.util.Iterator;

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
        this.item(DAItems.ROSEROOT_SIGN.get());
        this.item(DAItems.ROSEROOT_HANGING_SIGN.get());

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


        this.itemBlock(DABlocks.AETHER_MUD.get());
        this.itemBlock(DABlocks.PACKED_AETHER_MUD.get());
        this.itemBlock(DABlocks.AETHER_MUD_BRICKS.get());
        this.itemBlock(DABlocks.AETHER_MUD_BRICKS_SLAB.get());
        this.itemBlock(DABlocks.AETHER_MUD_BRICKS_STAIRS.get());


        this.itemBlock(DABlocks.SKYJADE_BLOCK.get());
        this.itemBlock(DABlocks.SKYJADE_ORE.get());

        this.itemBlock(DABlocks.STRATUS_BLOCK.get());

        this.itemBlock(DABlocks.ASETERITE.get());
        this.itemBlock(DABlocks.ASETERITE_STAIRS.get());
        this.itemBlock(DABlocks.ASETERITE_SLAB.get());
        this.itemWallBlock(DABlocks.ASETERITE_WALL.get(), DABlocks.ASETERITE.get());
        this.itemBlock(DABlocks.POLISHED_ASETERITE.get());
        this.itemBlock(DABlocks.POLISHED_ASETERITE_STAIRS.get());
        this.itemBlock(DABlocks.POLISHED_ASETERITE_SLAB.get());

        this.itemBlock(DABlocks.RAW_CLORITE.get());
        this.itemBlock(DABlocks.RAW_CLORITE_STAIRS.get());
        this.itemBlock(DABlocks.RAW_CLORITE_SLAB.get());
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

        this.itemBlock(DABlocks.GILDED_HOLYSTONE_TILES.get());
        this.itemBlock(DABlocks.GILDED_HOLYSTONE_TILE_STAIRS.get());
        this.itemBlock(DABlocks.GILDED_HOLYSTONE_TILE_SLAB.get());
        this.itemWallBlock(DABlocks.GILDED_HOLYSTONE_TILE_WALL.get(), DABlocks.GILDED_HOLYSTONE_TILES.get());

        this.itemBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());
        this.itemBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_STAIRS.get());
        this.itemBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_SLAB.get());
        this.itemWallBlock(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());


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
        this.itemBlock(DABlocks.AERSMOG.get());
        this.itemBlock(DABlocks.STERLING_AERCLOUD.get());
        this.itemBlock(DABlocks.CHROMATIC_AERCLOUD.get());

        this.itemBlockFlat(DABlocks.MINI_GOLDEN_GRASS.get());
        this.itemBlockFlat(DABlocks.SHORT_GOLDEN_GRASS.get());
        this.itemBlockFlat(DABlocks.MEDIUM_GOLDEN_GRASS.get());
        this.itemBlockFlatName(DABlocks.TALL_GOLDEN_GRASS.get(), "tall_golden_grass_top");

        //Aether Genesis
            this.itemLogWallBlock(DABlocks.ROSEROOT_LOG_WALL.get(), DABlocks.ROSEROOT_LOG.get(), "", DeepAetherMod.MODID);
            this.itemLogWallBlock(DABlocks.STRIPPED_ROSEROOT_LOG_WALL.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get(), "", DeepAetherMod.MODID);
            this.itemLogWallBlock(DABlocks.CRUDEROOT_LOG_WALL.get(), DABlocks.CRUDEROOT_LOG.get(), "", DeepAetherMod.MODID);
            this.itemLogWallBlock(DABlocks.STRIPPED_CRUDEROOT_LOG_WALL.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get(), "", DeepAetherMod.MODID);
            this.itemLogWallBlock(DABlocks.YAGROOT_LOG_WALL.get(), DABlocks.YAGROOT_LOG.get(), "", DeepAetherMod.MODID);
            this.itemLogWallBlock(DABlocks.STRIPPED_YAGROOT_LOG_WALL.get(), DABlocks.STRIPPED_YAGROOT_LOG.get(), "", DeepAetherMod.MODID);
            this.itemLogWallBlock(DABlocks.CONBERRY_LOG_WALL.get(), DABlocks.CONBERRY_LOG.get(), "", DeepAetherMod.MODID);
            this.itemLogWallBlock(DABlocks.STRIPPED_CONBERRY_LOG_WALL.get(), DABlocks.STRIPPED_CONBERRY_LOG.get(), "", DeepAetherMod.MODID);
            this.itemLogWallBlock(DABlocks.SUNROOT_LOG_WALL.get(), DABlocks.SUNROOT_LOG.get(), "", DeepAetherMod.MODID);
            this.itemLogWallBlock(DABlocks.STRIPPED_SUNROOT_LOG_WALL.get(), DABlocks.STRIPPED_SUNROOT_LOG.get(), "", DeepAetherMod.MODID);



        //ITEMS
        this.item(DAItems.SKYJADE.get());
        this.handheldItem(DAItems.SKYJADE_TOOLS_SWORD.get());
        this.handheldItem(DAItems.SKYJADE_TOOLS_AXE.get());
        this.handheldItem(DAItems.SKYJADE_TOOLS_PICKAXE.get());
        this.handheldItem(DAItems.SKYJADE_TOOLS_SHOVEL.get());
        this.handheldItem(DAItems.SKYJADE_TOOLS_HOE.get());

        this.bootsItem(DAItems.SKYJADE_BOOTS.get());
        this.leggingsItem(DAItems.SKYJADE_LEGGINGS.get());
        this.chestplateItem(DAItems.SKYJADE_CHESTPLATE.get());
        this.helmetItem(DAItems.SKYJADE_HELMET.get());
        this.glovesItem(DAItems.SKYJADE_GLOVES.get());

        this.item(DAItems.SKYJADE_RING.get());

        this.item(DAItems.STRATUS_INGOT.get());
        this.handheldItem(DAItems.STRATUS_SWORD.get());
        this.handheldItem(DAItems.STRATUS_AXE.get());
        this.handheldItem(DAItems.STRATUS_PICKAXE.get());
        this.handheldItem(DAItems.STRATUS_SHOVEL.get());
        this.handheldItem(DAItems.STRATUS_HOE.get());

        this.bootsItem(DAItems.STRATUS_BOOTS.get());
        this.leggingsItem(DAItems.STRATUS_LEGGINGS.get());
        this.chestplateItem(DAItems.STRATUS_CHESTPLATE.get());
        this.helmetItem(DAItems.STRATUS_HELMET.get());
        this.glovesItem(DAItems.STRATUS_GLOVES.get());

        this.item(DAItems.STRATUS_RING.get());

        this.item(DAItems.GRAVITITE_RING.get());

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
        this.item(DAItems.SUNROOT_BOAT.get());
        this.item(DAItems.SUNROOT_CHEST_BOAT.get());

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
        this.itemBlockFlat(DABlocks.ENCHANTED_BLOSSOM.get());

        this.item(DAItems.GOLDEN_GRASS_SEEDS.get());
        this.item(DAItems.GOLDEN_SWET_BALL.get());

        this.itemWallBlock(DABlocks.AETHER_MUD_BRICKS_WALL.get(), DABlocks.AETHER_MUD_BRICKS.get());
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


    public void itemLogWallBlock(Block block, Block baseBlock, String location, String modid) {
        ResourceLocation baseTexture = new ResourceLocation(modid, "block/" + location + this.blockName(baseBlock));
        this.withExistingParent(this.blockName(block), this.mcLoc("block/block"))
                .transforms()
                .transform(ItemDisplayContext.GUI).rotation(30.0F, 135.0F, 0.0F).translation(0.0F, 0.0F, 0.0F).scale(0.625F, 0.625F, 0.625F).end()
                .transform(ItemDisplayContext.FIXED).rotation(0.0F, 90.0F, 0.0F).translation(0.0F, 0.0F, 0.0F).scale(0.5F, 0.5F, 0.5F).end()
                .end()
                .texture("top", baseTexture + "_top").texture("side", baseTexture)
                .element().from(4.0F, 0.0F, 4.0F).to(12.0F, 16.0F, 12.0F)
                .face(Direction.DOWN).uvs(4.0F, 4.0F, 12.0F, 12.0F).texture("#top").cullface(Direction.DOWN).end()
                .face(Direction.UP).uvs(4.0F, 4.0F, 12.0F, 12.0F).texture("#top").end()
                .face(Direction.NORTH).uvs(4.0F, 0.0F, 12.0F, 16.0F).texture("#side").end()
                .face(Direction.SOUTH).uvs(4.0F, 0.0F, 12.0F, 16.0F).texture("#side").end()
                .face(Direction.WEST).uvs(4.0F, 0.0F, 12.0F, 16.0F).texture("#side").end()
                .face(Direction.EAST).uvs(4.0F, 0.0F, 12.0F, 16.0F).texture("#side").end().end()
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, 13.0F, 16.0F)
                .face(Direction.DOWN).uvs(5.0F, 0.0F, 11.0F, 16.0F).texture("#top").cullface(Direction.DOWN).end()
                .face(Direction.UP).uvs(5.0F, 0.0F, 11.0F, 16.0F).texture("#top").end()
                .face(Direction.NORTH).uvs(5.0F, 3.0F, 11.0F, 16.0F).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).uvs(5.0F, 3.0F, 11.0F, 16.0F).texture("#side").cullface(Direction.SOUTH).end()
                .face(Direction.WEST).uvs(0.0F, 3.0F, 16.0F, 16.0F).texture("#side").end()
                .face(Direction.EAST).uvs(0.0F, 3.0F, 16.0F, 16.0F).texture("#side").end().end();
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

        for(Iterator var7 = VANILLA_TRIM_MATERIALS.iterator(); var7.hasNext(); index += 0.1) {
            ResourceKey<TrimMaterial> trimMaterial = (ResourceKey)var7.next();
            String material = trimMaterial.location().getPath();
            String var10000 = this.itemName(item);
            String name = var10000 + "_" + material + "_trim";
            this.withExistingParent(name, this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + this.itemName(item))).texture("layer1", this.mcLoc("trims/items/" + type + "_trim_" + material));
            builder.override().predicate(new ResourceLocation("trim_type"), (float)index).model(this.getExistingFile(this.modLoc("item/" + name))).end();
        }

    }

    public void glovesItem(Item item) {
        ItemModelBuilder builder = this.withExistingParent(this.itemName(item), this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/"  + this.itemName(item)));
        double index = 0.1;

        for(Iterator var6 = NitrogenItemModelProvider.VANILLA_TRIM_MATERIALS.iterator(); var6.hasNext(); index += 0.1) {
            ResourceKey<TrimMaterial> trimMaterial = (ResourceKey)var6.next();
            String material = trimMaterial.location().getPath();
            String var10000 = this.itemName(item);
            String name = var10000 + "_" + material + "_trim";
            this.withExistingParent(name, this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + this.itemName(item))).texture("layer1", new ResourceLocation(Aether.MODID,"trims/items/gloves_trim_" + material));
            builder.override().predicate(new ResourceLocation("trim_type"), (float)index).model(this.getExistingFile(this.modLoc("item/" + name))).end();
        }

    }
}