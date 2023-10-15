package teamrazor.deepaether.datagen;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.data.providers.AetherBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;

import java.util.Map;


public class DABlockstateData extends AetherBlockStateProvider {
    public DABlockstateData(PackOutput output, ExistingFileHelper helper) {
        super(output, DeepAetherMod.MODID, helper);
    }

    @Override
    public void registerStatesAndModels() {

        //WOOD ROSEROOT

        this.wood((RotatedPillarBlock) DABlocks.ROSEROOT_WOOD.get(), (RotatedPillarBlock) DABlocks.ROSEROOT_LOG.get());
        this.log((RotatedPillarBlock) DABlocks.ROSEROOT_LOG.get());
        this.wood((RotatedPillarBlock) DABlocks.STRIPPED_ROSEROOT_WOOD.get(), (RotatedPillarBlock) DABlocks.STRIPPED_ROSEROOT_LOG.get());
        this.log((RotatedPillarBlock) DABlocks.STRIPPED_ROSEROOT_LOG.get());
        this.block(DABlocks.ROSEROOT_PLANKS.get());
        this.slab((SlabBlock) DABlocks.ROSEROOT_SLAB.get(), DABlocks.ROSEROOT_PLANKS.get());
        this.stairs((StairBlock) DABlocks.ROSEROOT_STAIRS.get(), DABlocks.ROSEROOT_PLANKS.get());
        this.fence((FenceBlock) DABlocks.ROSEROOT_FENCE.get(), DABlocks.ROSEROOT_PLANKS.get());
        this.fenceGateBlock((FenceGateBlock) DABlocks.ROSEROOT_FENCE_GATE.get(), DABlocks.ROSEROOT_PLANKS.get());
        this.doorBlock((DoorBlock) DABlocks.ROSEROOT_DOOR.get(), this.texture(this.name(DABlocks.ROSEROOT_DOOR.get()) + "_bottom"), this.texture(this.name(DABlocks.ROSEROOT_DOOR.get())+ "_top"));
        this.trapdoorBlock((TrapDoorBlock) DABlocks.ROSEROOT_TRAPDOOR.get(), this.texture(this.name(DABlocks.ROSEROOT_TRAPDOOR.get())), false);
        this.buttonBlock((ButtonBlock) DABlocks.ROSEROOT_BUTTON.get(), this.texture(this.name(DABlocks.ROSEROOT_PLANKS.get())));
        this.pressurePlateBlock((PressurePlateBlock) DABlocks.ROSEROOT_PRESSURE_PLATE.get(), this.texture(this.name(DABlocks.ROSEROOT_PLANKS.get())));
        this.wallBlock((WallBlock) DABlocks.ROSEROOT_WALL.get(), DABlocks.ROSEROOT_LOG.get());
        this.wallBlock((WallBlock) DABlocks.STRIPPED_ROSEROOT_WALL.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get());
        this.saplingBlock(DABlocks.ROSEROOT_SAPLING.get());
        this.saplingBlock(DABlocks.BLUE_ROSEROOT_SAPLING.get());
        this.pottedPlant(DABlocks.POTTED_ROSEROOT_SAPLING.get(), DABlocks.ROSEROOT_SAPLING.get());
        this.pottedPlant(DABlocks.POTTED_BLUE_ROSEROOT_SAPLING.get(), DABlocks.BLUE_ROSEROOT_SAPLING.get());
        this.block(DABlocks.ROSEROOT_LEAVES.get());
        this.block(DABlocks.FLOWERING_ROSEROOT_LEAVES.get());
        this.block(DABlocks.BLUE_ROSEROOT_LEAVES.get());
        this.block(DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get());
        this.block(DABlocks.AERGLOW_PETAL_BLOCK.get());
        this.signBlock((StandingSignBlock) DABlocks.ROSEROOT_SIGN.get(), (WallSignBlock) DABlocks.ROSEROOT_WALL_SIGN.get(), this.texture(this.name(DABlocks.ROSEROOT_PLANKS.get())));

        //WOOD YAGROOT

        this.wood((RotatedPillarBlock) DABlocks.YAGROOT_WOOD.get(), (RotatedPillarBlock) DABlocks.YAGROOT_LOG.get());
        this.log((RotatedPillarBlock) DABlocks.YAGROOT_LOG.get());
        this.wood((RotatedPillarBlock) DABlocks.STRIPPED_YAGROOT_WOOD.get(), (RotatedPillarBlock) DABlocks.STRIPPED_YAGROOT_LOG.get());
        this.log((RotatedPillarBlock) DABlocks.STRIPPED_YAGROOT_LOG.get());
        this.block(DABlocks.YAGROOT_PLANKS.get());
        this.slab((SlabBlock) DABlocks.YAGROOT_SLAB.get(), DABlocks.YAGROOT_PLANKS.get());
        this.stairs((StairBlock) DABlocks.YAGROOT_STAIRS.get(), DABlocks.YAGROOT_PLANKS.get());
        this.fence((FenceBlock) DABlocks.YAGROOT_FENCE.get(), DABlocks.YAGROOT_PLANKS.get());
        this.fenceGateBlock((FenceGateBlock) DABlocks.YAGROOT_FENCE_GATE.get(), DABlocks.YAGROOT_PLANKS.get());
        this.doorBlock((DoorBlock) DABlocks.YAGROOT_DOOR.get(), this.texture(this.name(DABlocks.YAGROOT_DOOR.get())+ "_bottom"), this.texture(this.name(DABlocks.YAGROOT_DOOR.get())+ "_top"));
        this.trapdoorBlock((TrapDoorBlock) DABlocks.YAGROOT_TRAPDOOR.get(), this.texture(this.name(DABlocks.YAGROOT_TRAPDOOR.get())), false);
        this.buttonBlock((ButtonBlock) DABlocks.YAGROOT_BUTTON.get(), this.texture(this.name(DABlocks.YAGROOT_PLANKS.get())));
        this.pressurePlateBlock((PressurePlateBlock) DABlocks.YAGROOT_PRESSURE_PLATE.get(), this.texture(this.name(DABlocks.YAGROOT_PLANKS.get())));
        this.wallBlock((WallBlock) DABlocks.YAGROOT_WALL.get(), DABlocks.YAGROOT_LOG.get());
        this.wallBlock((WallBlock) DABlocks.STRIPPED_YAGROOT_WALL.get(), DABlocks.STRIPPED_YAGROOT_LOG.get());
        this.saplingBlock(DABlocks.YAGROOT_SAPLING.get());
        this.pottedPlant(DABlocks.POTTED_YAGROOT_SAPLING.get(), DABlocks.YAGROOT_SAPLING.get());
        this.block(DABlocks.YAGROOT_LEAVES.get());
        this.signBlock((StandingSignBlock) DABlocks.YAGROOT_SIGN.get(), (WallSignBlock) DABlocks.YAGROOT_WALL_SIGN.get(), this.texture(this.name(DABlocks.YAGROOT_PLANKS.get())));

        //WOOD CRUDEROOT

        this.wood((RotatedPillarBlock) DABlocks.CRUDEROOT_WOOD.get(), (RotatedPillarBlock) DABlocks.CRUDEROOT_LOG.get());
        this.log((RotatedPillarBlock) DABlocks.CRUDEROOT_LOG.get());
        this.wood((RotatedPillarBlock) DABlocks.STRIPPED_CRUDEROOT_WOOD.get(), (RotatedPillarBlock) DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        this.log((RotatedPillarBlock) DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        this.block(DABlocks.CRUDEROOT_PLANKS.get());
        this.slab((SlabBlock) DABlocks.CRUDEROOT_SLAB.get(), DABlocks.CRUDEROOT_PLANKS.get());
        this.stairs((StairBlock) DABlocks.CRUDEROOT_STAIRS.get(), DABlocks.CRUDEROOT_PLANKS.get());
        this.fence((FenceBlock) DABlocks.CRUDEROOT_FENCE.get(), DABlocks.CRUDEROOT_PLANKS.get());
        this.fenceGateBlock((FenceGateBlock) DABlocks.CRUDEROOT_FENCE_GATE.get(), DABlocks.CRUDEROOT_PLANKS.get());
        this.doorBlock((DoorBlock) DABlocks.CRUDEROOT_DOOR.get(), this.texture(this.name(DABlocks.CRUDEROOT_DOOR.get())+ "_bottom"), this.texture(this.name(DABlocks.CRUDEROOT_DOOR.get())+ "_top"));
        this.trapdoorBlock((TrapDoorBlock) DABlocks.CRUDEROOT_TRAPDOOR.get(), this.texture(this.name(DABlocks.CRUDEROOT_TRAPDOOR.get())), false);
        this.buttonBlock((ButtonBlock) DABlocks.CRUDEROOT_BUTTON.get(), this.texture(this.name(DABlocks.CRUDEROOT_PLANKS.get())));
        this.pressurePlateBlock((PressurePlateBlock) DABlocks.CRUDEROOT_PRESSURE_PLATE.get(), this.texture(this.name(DABlocks.CRUDEROOT_PLANKS.get())));
        this.wallBlock((WallBlock) DABlocks.CRUDEROOT_WALL.get(), DABlocks.CRUDEROOT_LOG.get());
        this.wallBlock((WallBlock) DABlocks.STRIPPED_CRUDEROOT_WALL.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        this.saplingBlock(DABlocks.CRUDEROOT_SAPLING.get());
        this.pottedPlant(DABlocks.POTTED_CRUDEROOT_SAPLING.get(), DABlocks.CRUDEROOT_SAPLING.get());
        this.block(DABlocks.CRUDEROOT_LEAVES.get());
        this.signBlock((StandingSignBlock) DABlocks.CRUDEROOT_SIGN.get(), (WallSignBlock) DABlocks.CRUDEROOT_WALL_SIGN.get(), this.texture(this.name(DABlocks.CRUDEROOT_PLANKS.get())));


        //WOOD CONBERRY
        this.wood((RotatedPillarBlock) DABlocks.CONBERRY_WOOD.get(), (RotatedPillarBlock) DABlocks.CONBERRY_LOG.get());
        this.log((RotatedPillarBlock) DABlocks.CONBERRY_LOG.get());
        this.wood((RotatedPillarBlock) DABlocks.STRIPPED_CONBERRY_WOOD.get(), (RotatedPillarBlock) DABlocks.STRIPPED_CONBERRY_LOG.get());
        this.log((RotatedPillarBlock) DABlocks.STRIPPED_CONBERRY_LOG.get());
        this.block(DABlocks.CONBERRY_PLANKS.get());
        this.slab((SlabBlock) DABlocks.CONBERRY_SLAB.get(), DABlocks.CONBERRY_PLANKS.get());
        this.stairs((StairBlock) DABlocks.CONBERRY_STAIRS.get(), DABlocks.CONBERRY_PLANKS.get());
        this.fence((FenceBlock) DABlocks.CONBERRY_FENCE.get(), DABlocks.CONBERRY_PLANKS.get());
        this.fenceGateBlock((FenceGateBlock) DABlocks.CONBERRY_FENCE_GATE.get(), DABlocks.CONBERRY_PLANKS.get());
        this.doorBlock((DoorBlock) DABlocks.CONBERRY_DOOR.get(), this.texture(this.name(DABlocks.CONBERRY_DOOR.get())+ "_bottom"), this.texture(this.name(DABlocks.CONBERRY_DOOR.get())+ "_top"));
        this.trapdoorBlock((TrapDoorBlock) DABlocks.CONBERRY_TRAPDOOR.get(), this.texture(this.name(DABlocks.CONBERRY_TRAPDOOR.get())), false);
        this.buttonBlock((ButtonBlock) DABlocks.CONBERRY_BUTTON.get(), this.texture(this.name(DABlocks.CONBERRY_PLANKS.get())));
        this.pressurePlateBlock((PressurePlateBlock) DABlocks.CONBERRY_PRESSURE_PLATE.get(), this.texture(this.name(DABlocks.CONBERRY_PLANKS.get())));
        this.wallBlock((WallBlock) DABlocks.CONBERRY_WALL.get(), DABlocks.CONBERRY_LOG.get());
        this.wallBlock((WallBlock) DABlocks.STRIPPED_CONBERRY_WALL.get(), DABlocks.STRIPPED_CONBERRY_LOG.get());
        this.saplingBlock(DABlocks.CONBERRY_SAPLING.get());
        this.pottedPlant(DABlocks.POTTED_CONBERRY_SAPLING.get(), DABlocks.CONBERRY_SAPLING.get());
        this.block(DABlocks.CONBERRY_LEAVES.get());
        this.signBlock((StandingSignBlock) DABlocks.CONBERRY_SIGN.get(), (WallSignBlock) DABlocks.CONBERRY_WALL_SIGN.get(), this.texture(this.name(DABlocks.CONBERRY_PLANKS.get())));


        //WOOD SUNROOT
        this.wood((RotatedPillarBlock) DABlocks.SUNROOT_WOOD.get(), (RotatedPillarBlock) DABlocks.SUNROOT_LOG.get());
        this.log((RotatedPillarBlock) DABlocks.SUNROOT_LOG.get());
        this.wood((RotatedPillarBlock) DABlocks.STRIPPED_SUNROOT_WOOD.get(), (RotatedPillarBlock) DABlocks.STRIPPED_SUNROOT_LOG.get());
        this.log((RotatedPillarBlock) DABlocks.STRIPPED_SUNROOT_LOG.get());
        this.block(DABlocks.SUNROOT_PLANKS.get());
        this.slab((SlabBlock) DABlocks.SUNROOT_SLAB.get(), DABlocks.SUNROOT_PLANKS.get());
        this.stairs((StairBlock) DABlocks.SUNROOT_STAIRS.get(), DABlocks.SUNROOT_PLANKS.get());
        this.fence((FenceBlock) DABlocks.SUNROOT_FENCE.get(), DABlocks.SUNROOT_PLANKS.get());
        this.fenceGateBlock((FenceGateBlock) DABlocks.SUNROOT_FENCE_GATE.get(), DABlocks.SUNROOT_PLANKS.get());
        this.doorBlock((DoorBlock) DABlocks.SUNROOT_DOOR.get(), this.texture(this.name(DABlocks.SUNROOT_DOOR.get())+ "_bottom"), this.texture(this.name(DABlocks.SUNROOT_DOOR.get())+ "_top"));
        this.trapdoorBlock((TrapDoorBlock) DABlocks.SUNROOT_TRAPDOOR.get(), this.texture(this.name(DABlocks.SUNROOT_TRAPDOOR.get())), false);
        this.buttonBlock((ButtonBlock) DABlocks.SUNROOT_BUTTON.get(), this.texture(this.name(DABlocks.SUNROOT_PLANKS.get())));
        this.pressurePlateBlock((PressurePlateBlock) DABlocks.SUNROOT_PRESSURE_PLATE.get(), this.texture(this.name(DABlocks.SUNROOT_PLANKS.get())));
        this.wallBlock((WallBlock) DABlocks.SUNROOT_WALL.get(), DABlocks.SUNROOT_LOG.get());
        this.wallBlock((WallBlock) DABlocks.STRIPPED_SUNROOT_WALL.get(), DABlocks.STRIPPED_SUNROOT_LOG.get());
        this.saplingBlock(DABlocks.SUNROOT_SAPLING.get());
        this.pottedPlant(DABlocks.POTTED_SUNROOT_SAPLING.get(), DABlocks.SUNROOT_SAPLING.get());
        this.block(DABlocks.SUNROOT_LEAVES.get());
        this.signBlock((StandingSignBlock) DABlocks.SUNROOT_SIGN.get(), (WallSignBlock) DABlocks.SUNROOT_WALL_SIGN.get(), this.texture(this.name(DABlocks.SUNROOT_PLANKS.get())));

        //MUD

        this.blockDoubleDrops(DABlocks.AETHER_MUD.get());
        this.blockDoubleDrops(DABlocks.AETHER_COARSE_DIRT.get());
        this.block(DABlocks.PACKED_AETHER_MUD.get());
        this.block(DABlocks.AETHER_MUD_BRICKS.get());
        this.slab((SlabBlock) DABlocks.AETHER_MUD_BRICKS_SLAB.get(), DABlocks.AETHER_MUD_BRICKS.get());
        this.stairs((StairBlock) DABlocks.AETHER_MUD_BRICKS_STAIRS.get(), DABlocks.AETHER_MUD_BRICKS.get());
        this.wallBlock((WallBlock) DABlocks.AETHER_MUD_BRICKS_WALL.get(), DABlocks.AETHER_MUD_BRICKS.get());

        //ORES

        this.block(DABlocks.SKYJADE_ORE.get());
        this.block(DABlocks.SKYJADE_BLOCK.get());

        this.block(DABlocks.STRATUS_BLOCK.get());

    
        //STONE
        //ASETERITE
    
        this.blockDoubleDrops(DABlocks.ASETERITE.get());
        this.stairs((StairBlock) DABlocks.ASETERITE_STAIRS.get(), DABlocks.ASETERITE.get());
        this.slab((SlabBlock) DABlocks.ASETERITE_SLAB.get(), DABlocks.ASETERITE.get());
        this.wallBlock((WallBlock) DABlocks.ASETERITE_WALL.get(), DABlocks.ASETERITE.get());
        this.block(DABlocks.POLISHED_ASETERITE.get());
        this.stairs((StairBlock) DABlocks.POLISHED_ASETERITE_STAIRS.get(), DABlocks.POLISHED_ASETERITE.get());
        this.slab((SlabBlock) DABlocks.POLISHED_ASETERITE_SLAB.get(), DABlocks.POLISHED_ASETERITE.get());

    
        //CLORITE        

        this.blockDoubleDrops(DABlocks.RAW_CLORITE.get());
        this.stairs((StairBlock) DABlocks.RAW_CLORITE_STAIRS.get(), DABlocks.RAW_CLORITE.get());
        this.slab((SlabBlock) DABlocks.RAW_CLORITE_SLAB.get(), DABlocks.RAW_CLORITE.get());
        this.block(DABlocks.CLORITE.get());
        this.stairs((StairBlock) DABlocks.CLORITE_STAIRS.get(), DABlocks.CLORITE.get());
        this.slab((SlabBlock) DABlocks.CLORITE_SLAB.get(), DABlocks.CLORITE.get());
        this.wallBlock((WallBlock) DABlocks.CLORITE_WALL.get(), DABlocks.CLORITE.get());
        this.wallBlock((WallBlock) DABlocks.RAW_CLORITE_WALL.get(), DABlocks.RAW_CLORITE.get());
        this.block(DABlocks.POLISHED_CLORITE.get());
        this.stairs((StairBlock) DABlocks.POLISHED_CLORITE_STAIRS.get(), DABlocks.POLISHED_CLORITE.get());
        this.slab((SlabBlock) DABlocks.POLISHED_CLORITE_SLAB.get(), DABlocks.POLISHED_CLORITE.get());
        this.log((RotatedPillarBlock) DABlocks.CLORITE_PILLAR.get());

        //PLANTS

        this.crossBlock(DABlocks.AERLAVENDER.get());
        this.crossBlock(DABlocks.TALL_AERLAVENDER.get());
        this.crossBlock(DABlocks.AETHER_CATTAILS.get());
        this.crossBlock(DABlocks.RADIANT_ORCHID.get());
        this.pottedPlantFix(DABlocks.POTTED_AERLAVENDER.get(), DABlocks.AERLAVENDER.get());
        this.pottedPlantFix(DABlocks.POTTED_TALL_AERLAVENDER.get(), DABlocks.TALL_AERLAVENDER.get());
        this.pottedPlantFix(DABlocks.POTTED_AETHER_CATTAILS.get(), DABlocks.AETHER_CATTAILS.get());
        this.pottedPlant(DABlocks.POTTED_RADIANT_ORCHID.get(), DABlocks.RADIANT_ORCHID.get());
        this.crossBlock(DABlocks.MINI_GOLDEN_GRASS.get());
        this.crossBlock(DABlocks.MEDIUM_GOLDEN_GRASS.get());
        this.crossBlock(DABlocks.SHORT_GOLDEN_GRASS.get());
        this.crossBlock(DABlocks.GOLDEN_FLOWER.get());
        this.crossBlock(DABlocks.ENCHANTED_BLOSSOM.get());




        //TILES
        this.block(DABlocks.HOLYSTONE_TILES.get());
        this.stairs((StairBlock) DABlocks.HOLYSTONE_TILE_STAIRS.get(), DABlocks.HOLYSTONE_TILES.get());
        this.slab((SlabBlock) DABlocks.HOLYSTONE_TILE_SLAB.get(), DABlocks.HOLYSTONE_TILES.get());
        this.wallBlock((WallBlock) DABlocks.HOLYSTONE_TILE_WALL.get(), DABlocks.HOLYSTONE_TILES.get());

        this.block(DABlocks.MOSSY_HOLYSTONE_TILES.get());
        this.stairs((StairBlock) DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get(), DABlocks.MOSSY_HOLYSTONE_TILES.get());
        this.slab((SlabBlock) DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get(), DABlocks.MOSSY_HOLYSTONE_TILES.get());
        this.wallBlock((WallBlock) DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(), DABlocks.MOSSY_HOLYSTONE_TILES.get());

        this.block(DABlocks.GILDED_HOLYSTONE_TILES.get());
        this.stairs((StairBlock) DABlocks.GILDED_HOLYSTONE_TILE_STAIRS.get(), DABlocks.GILDED_HOLYSTONE_TILES.get());
        this.slab((SlabBlock) DABlocks.GILDED_HOLYSTONE_TILE_SLAB.get(), DABlocks.GILDED_HOLYSTONE_TILES.get());
        this.wallBlock((WallBlock) DABlocks.GILDED_HOLYSTONE_TILE_WALL.get(), DABlocks.GILDED_HOLYSTONE_TILES.get());
        this.block(DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());
        this.stairs((StairBlock) DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_STAIRS.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());
        this.slab((SlabBlock) DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_SLAB.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());
        this.wallBlock((WallBlock) DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());

        //MISC
        this.translucentBlock(DABlocks.RAIN_AERCLOUD.get());
        this.translucentBlock(DABlocks.AERSMOG.get());
        this.translucentBlock(DABlocks.CHROMATIC_AERCLOUD.get());
        this.translucentBlock(DABlocks.STERLING_AERCLOUD.get());

        //MOSS
        this.block(DABlocks.AETHER_MOSS_BLOCK.get());
    }

    public ResourceLocation texture(String name, String suffix) {
        return this.modLoc("block/"  + name + suffix);
    }

    public void fence(FenceBlock block, Block baseBlock) {
        this.fenceBlock(block, this.texture(this.name(baseBlock)));
        this.fenceColumn(block, this.name(baseBlock));
    }
    public void translucentBlock(Block block) {
        this.simpleBlock(block, this.cubeAllTranslucent(block));
    }

    public ModelFile cubeAllTranslucent(Block block) {
        return (this.models().cubeAll(this.name(block), this.texture(this.name(block)))).renderType(new ResourceLocation("translucent"));
    }
    public void fenceColumn(CrossCollisionBlock block, String side) {
        String baseName = this.name(block);
        this.fourWayBlock(block,
                this.models().fencePost(baseName + "_post", this.texture(side)),
                this.models().fenceSide(baseName + "_side", this.texture(side)));
    }

    public ModelFile cubeBottomTop(String block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        return this.models().cubeBottomTop(block, side, bottom, top);
    }

    public void fenceGateBlock(FenceGateBlock block, Block baseBlock) {
        this.fenceGateBlockInternal(block, this.name(block), this.texture(this.name(baseBlock)));
    }
    public void log(RotatedPillarBlock block) {
        this.axisBlock(block, this.texture(this.name(block)), this.extend(this.texture(this.name(block)), "_top"));
    }

    public void wood(RotatedPillarBlock block, RotatedPillarBlock baseBlock) {
        this.axisBlock(block, this.texture(this.name(baseBlock)), this.texture(this.name(baseBlock)));
    }

    public void block(Block block) {
        this.simpleBlock(block, this.cubeAll(block));
    }

    public void crossBlock(Block block) {
        this.crossBlock(block, models().cross(this.name(block), this.texture(this.name(block))).renderType(new ResourceLocation("cutout")));
    }
    public void pottedPlant(Block block, Block flower) {
        ModelFile pot = this.models().withExistingParent(this.name(block), this.mcLoc("block/flower_pot_cross")).texture("plant", this.modLoc("block/"  + this.name(flower))).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    public void pottedPlantFix(Block block, Block flower) {
        ModelFile pot = this.models().withExistingParent(this.name(block), this.mcLoc("block/flower_pot_cross")).texture("plant", this.modLoc("block/"  + this.name(flower) + "_pot")).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    public void saplingBlock(Block block) {
        ModelFile sapling = models().cross(this.name(block), this.texture(this.name(block))).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(sapling).build(), SaplingBlock.STAGE);
    }

    public void stairs(StairBlock block, Block baseBlock) {
        this.stairsBlock(block, this.texture(this.name(baseBlock)));
    }

    public void slab(SlabBlock block, Block baseBlock) {
        this.slabBlock(block, this.texture(this.name(baseBlock)), this.texture(this.name(baseBlock)));
    }

    public void wallBlock(WallBlock block, Block baseBlock) {
        this.wallBlockInternal(block, this.name(block), this.texture(this.name(baseBlock)));
    }
    public void blockDoubleDrops(Block block) {
        this.getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(this.cubeAll(block)).build(), AetherBlockStateProperties.DOUBLE_DROPS);
    }
    protected BlockModelBuilder makeWallPostModel(int width, int height, String name) {
        return models().withExistingParent(name, this.mcLoc("block/block"))
                .element().from(8 - width, 0.0F, 8 - width).to(8 + width, height, 8 + width)
                .face(Direction.DOWN).texture("#top").cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
    }

    protected BlockModelBuilder makeWallSideModel(int length, int height, String name, ModelBuilder.FaceRotation faceRotation, int u1, int u2) {
        return models().withExistingParent(name, this.mcLoc("block/block"))
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, height, length)
                .face(Direction.DOWN).texture("#top").rotation(faceRotation).uvs(u1, 5, u2, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(faceRotation).uvs(u1, 5, u2, 11).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
    }

    public void logWallBlock(WallBlock block, Block baseBlock, String location, String modid, boolean postUsesTop, ModelFile postBig, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt, ModelFile sideShort, ModelFile sideAltShort, ModelFile sideTallShort, ModelFile sideTallAltShort) {
        this.logWallBlockInternal(block, this.name(block), new ResourceLocation(modid, "block/" + location + this.name(baseBlock)), postUsesTop, postBig, postShort, postTall, side, sideAlt, sideTall, sideTallAlt, sideShort, sideAltShort, sideTallShort, sideTallAltShort);
    }

    private void logWallBlockInternal(WallBlock block, String baseName, ResourceLocation texture, boolean postUsesTop, ModelFile postBig, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt, ModelFile sideShort, ModelFile sideAltShort, ModelFile sideTallShort, ModelFile sideTallAltShort) {
        this.logWallBlock(
                this.getMultipartBuilder(block),
                models().getBuilder(baseName + "_post_short").parent(postShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_post_tall").parent(postTall).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side").parent(side).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_alt").parent(sideAlt).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall").parent(sideTall).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_alt").parent(sideTallAlt).texture("particle", texture).texture("top", texture).texture("side", texture)
        );

        this.logWallBlockWithPost(
                this.getMultipartBuilder(block),
                models().getBuilder(baseName + "_post").parent(postBig).texture("particle", texture).texture("top", postUsesTop ? (texture + "_top") : texture.toString()).texture("side", texture),
                models().getBuilder(baseName + "_side_short").parent(sideShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_alt_short").parent(sideAltShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_short").parent(sideTallShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_alt_short").parent(sideTallAltShort).texture("particle", texture).texture("top", texture).texture("side", texture)
        );
    }

    public void logWallBlock(MultiPartBlockStateBuilder builder, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt) {
        builder
                // Smaller post when West & East are both short while North & South being none
                .part().modelFile(postShort).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.LOW).condition(WallBlock.WEST_WALL, WallSide.LOW).end().end()
                // Taller thinner post when West & East are both tall while North & South being none
                .part().modelFile(postTall).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.TALL).condition(WallBlock.WEST_WALL, WallSide.TALL).end().end()
                // Rotated small post when West & East are both none while North & South are short
                .part().modelFile(postShort).rotationY(90).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.NONE).condition(WallBlock.NORTH_WALL, WallSide.LOW).condition(WallBlock.WEST_WALL, WallSide.NONE).condition(WallBlock.SOUTH_WALL, WallSide.LOW).end().end()
                // Rotated small post when West & East are both none while North & South are tall
                .part().modelFile(postTall).rotationY(90).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.NONE).condition(WallBlock.NORTH_WALL, WallSide.TALL).condition(WallBlock.WEST_WALL, WallSide.NONE).condition(WallBlock.SOUTH_WALL, WallSide.TALL).end().end();
        WALL_PROPS.entrySet().stream()
                .filter(e -> e.getKey().getAxis().isHorizontal())
                .forEach(e -> {
                    this.logWallSidePart(builder, side, sideAlt, e, WallSide.LOW, false);
                    this.logWallSidePart(builder, sideTall, sideTallAlt, e, WallSide.TALL, false);
                });
    }

    public void logWallBlockWithPost(MultiPartBlockStateBuilder builder, ModelFile postBig, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt) {
        builder
                // Big post for connections, typically including angled
                .part().modelFile(postBig).addModel()
                .condition(WallBlock.UP, true).end();
        WALL_PROPS.entrySet().stream()
                .filter(e -> e.getKey().getAxis().isHorizontal())
                .forEach(e -> {
                    this.logWallSidePart(builder, side, sideAlt, e, WallSide.LOW, true);
                    this.logWallSidePart(builder, sideTall, sideTallAlt, e, WallSide.TALL, true);
                });
    }

    private void logWallSidePart(MultiPartBlockStateBuilder builder, ModelFile model, ModelFile modelAlt, Map.Entry<Direction, Property<WallSide>> entry, WallSide height, boolean hasPost) {
        int rotation = (((int) entry.getKey().toYRot()) + 180) % 360;
        builder.part()
                .modelFile(rotation < 180 ? model : modelAlt)
                .rotationY(rotation)
                .addModel()
                .condition(entry.getValue(), height).condition(WallBlock.UP, hasPost);
    }
}