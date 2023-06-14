

package teamrazor.deepaether.init;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DATabs {
    public static CreativeModeTab TAB_DEEP_AETHER_BLOCKS_TAB;
    public static CreativeModeTab TAB_DEEP_AETHER_ITEMS_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        TAB_DEEP_AETHER_BLOCKS_TAB = event.registerCreativeModeTab(
                new ResourceLocation(DeepAetherMod.MODID, "deep_aether_blocks"),
                builder -> builder.icon(() -> new ItemStack(DABlocks.FLOWERING_ROSEROOT_LEAVES.get()))
                        .title(Component.translatable("itemGroup." + DeepAetherMod.MODID + ".deep_aether_blocks"))
                        .displayItems((features, output) -> {
                            output.accept(DABlocks.ROSEROOT_LOG.get());
                            output.accept(DABlocks.ROSEROOT_WOOD.get());
                            output.accept(DABlocks.STRIPPED_ROSEROOT_LOG.get());
                            output.accept(DABlocks.STRIPPED_ROSEROOT_WOOD.get());
                            output.accept(DABlocks.ROSEROOT_PLANKS.get());
                            output.accept(DABlocks.ROSEROOT_STAIRS.get());
                            output.accept(DABlocks.ROSEROOT_SLAB.get());
                            output.accept(DABlocks.ROSEROOT_FENCE.get());
                            output.accept(DABlocks.ROSEROOT_FENCE_GATE.get());
                            output.accept(DABlocks.ROSEROOT_DOOR.get());
                            output.accept(DABlocks.ROSEROOT_TRAPDOOR.get());
                            output.accept(DABlocks.ROSEROOT_PRESSURE_PLATE.get());
                            output.accept(DABlocks.ROSEROOT_BUTTON.get());
                            output.accept(DABlocks.ROSEROOT_WALL.get());
                            output.accept(DABlocks.STRIPPED_ROSEROOT_WALL.get());
                            output.accept(DAItems.ROSEROOT_SIGN.get());
                            output.accept(DABlocks.ROSEROOT_LEAVES.get());
                            output.accept(DABlocks.FLOWERING_ROSEROOT_LEAVES.get());
                            output.accept(DABlocks.AERGLOW_PETAL_BLOCK.get());
                            output.accept(DABlocks.ROSEROOT_SAPLING.get());

                            output.accept(DABlocks.BLUE_ROSEROOT_LEAVES.get());
                            output.accept(DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get());
                            output.accept(DABlocks.BLUE_ROSEROOT_SAPLING.get());

                            output.accept(DABlocks.YAGROOT_LOG.get());
                            output.accept(DABlocks.YAGROOT_WOOD.get());
                            output.accept(DABlocks.STRIPPED_YAGROOT_LOG.get());
                            output.accept(DABlocks.STRIPPED_YAGROOT_WOOD.get());
                            output.accept(DABlocks.YAGROOT_PLANKS.get());
                            output.accept(DABlocks.YAGROOT_STAIRS.get());
                            output.accept(DABlocks.YAGROOT_SLAB.get());
                            output.accept(DABlocks.YAGROOT_FENCE.get());
                            output.accept(DABlocks.YAGROOT_FENCE_GATE.get());
                            output.accept(DABlocks.YAGROOT_DOOR.get());
                            output.accept(DABlocks.YAGROOT_TRAPDOOR.get());
                            output.accept(DABlocks.YAGROOT_PRESSURE_PLATE.get());
                            output.accept(DABlocks.YAGROOT_BUTTON.get());
                            output.accept(DABlocks.YAGROOT_WALL.get());
                            output.accept(DABlocks.STRIPPED_YAGROOT_WALL.get());
                            output.accept(DABlocks.YAGROOT_ROOTS.get());
                            output.accept(DABlocks.YAGROOT_VINE.get());
                            output.accept(DAItems.YAGROOT_SIGN.get());
                            output.accept(DABlocks.YAGROOT_LEAVES.get());
                            output.accept(DABlocks.YAGROOT_SAPLING.get());


                            output.accept(DABlocks.CRUDEROOT_LOG.get());
                            output.accept(DABlocks.CRUDEROOT_WOOD.get());
                            output.accept(DABlocks.STRIPPED_CRUDEROOT_LOG.get());
                            output.accept(DABlocks.STRIPPED_CRUDEROOT_WOOD.get());
                            output.accept(DABlocks.CRUDEROOT_PLANKS.get());
                            output.accept(DABlocks.CRUDEROOT_STAIRS.get());
                            output.accept(DABlocks.CRUDEROOT_SLAB.get());
                            output.accept(DABlocks.CRUDEROOT_FENCE.get());
                            output.accept(DABlocks.CRUDEROOT_FENCE_GATE.get());
                            output.accept(DABlocks.CRUDEROOT_DOOR.get());
                            output.accept(DABlocks.CRUDEROOT_TRAPDOOR.get());
                            output.accept(DABlocks.CRUDEROOT_PRESSURE_PLATE.get());
                            output.accept(DABlocks.CRUDEROOT_WALL.get());
                            output.accept(DABlocks.STRIPPED_CRUDEROOT_WALL.get());
                            output.accept(DABlocks.CRUDEROOT_BUTTON.get());
                            output.accept(DAItems.CRUDEROOT_SIGN.get());
                            output.accept(DABlocks.CRUDEROOT_LEAVES.get());
                            output.accept(DABlocks.CRUDEROOT_SAPLING.get());

                            output.accept(DABlocks.CONBERRY_LOG.get());
                            output.accept(DABlocks.CONBERRY_WOOD.get());
                            output.accept(DABlocks.STRIPPED_CONBERRY_LOG.get());
                            output.accept(DABlocks.STRIPPED_CONBERRY_WOOD.get());
                            output.accept(DABlocks.CONBERRY_PLANKS.get());
                            output.accept(DABlocks.CONBERRY_STAIRS.get());
                            output.accept(DABlocks.CONBERRY_SLAB.get());
                            output.accept(DABlocks.CONBERRY_FENCE.get());
                            output.accept(DABlocks.CONBERRY_FENCE_GATE.get());
                            output.accept(DABlocks.CONBERRY_DOOR.get());
                            output.accept(DABlocks.CONBERRY_TRAPDOOR.get());
                            output.accept(DABlocks.CONBERRY_PRESSURE_PLATE.get());
                            output.accept(DABlocks.CONBERRY_WALL.get());
                            output.accept(DABlocks.STRIPPED_CONBERRY_WALL.get());
                            output.accept(DABlocks.CONBERRY_BUTTON.get());
                            output.accept(DAItems.CONBERRY_SIGN.get());
                            output.accept(DABlocks.CONBERRY_LEAVES.get());
                            output.accept(DABlocks.CONBERRY_SAPLING.get());

                            output.accept(DABlocks.AETHER_MOSS_BLOCK.get());
                            output.accept(DABlocks.AETHER_MOSS_CARPET.get());

                            output.accept(DABlocks.AETHER_MUD.get());
                            output.accept(DABlocks.PACKED_AETHER_MUD.get());
                            output.accept(DABlocks.MUDDY_YAGROOT_ROOTS.get());
                            output.accept(DABlocks.AETHER_MUD_BRICKS.get());
                            output.accept(DABlocks.AETHER_MUD_BRICKS_STAIRS.get());
                            output.accept(DABlocks.AETHER_MUD_BRICKS_SLAB.get());
                            output.accept(DABlocks.AETHER_MUD_BRICKS_WALL.get());

                            output.accept(DABlocks.ASETERITE.get());
                            output.accept(DABlocks.ASETERITE_STAIRS.get());
                            output.accept(DABlocks.ASETERITE_SLAB.get());
                            output.accept(DABlocks.ASETERITE_WALL.get());
                            output.accept(DABlocks.POLISHED_ASETERITE.get());
                            output.accept(DABlocks.POLISHED_ASETERITE_STAIRS.get());
                            output.accept(DABlocks.POLISHED_ASETERITE_SLAB.get());

                            output.accept(DABlocks.CLORITE.get());
                            output.accept(DABlocks.CLORITE_STAIRS.get());
                            output.accept(DABlocks.CLORITE_SLAB.get());
                            output.accept(DABlocks.CLORITE_WALL.get());
                            output.accept(DABlocks.POLISHED_CLORITE.get());
                            output.accept(DABlocks.POLISHED_CLORITE_STAIRS.get());
                            output.accept(DABlocks.POLISHED_CLORITE_SLAB.get());
                            output.accept(DABlocks.CLORITE_PILLAR.get());

                            output.accept(DABlocks.SKYJADE_ORE.get());
                            output.accept(DABlocks.SKYJADE_BLOCK.get());
                            output.accept(DABlocks.CLOUDIUM_DEBRIS.get());
                            output.accept(DABlocks.CLOUDIUM_BLOCK.get());

                            output.accept(DABlocks.HOLYSTONE_TILES.get());
                            output.accept(DABlocks.HOLYSTONE_TILE_STAIRS.get());
                            output.accept(DABlocks.HOLYSTONE_TILE_SLAB.get());
                            output.accept(DABlocks.HOLYSTONE_TILE_WALL.get());
                            output.accept(DABlocks.MOSSY_HOLYSTONE_TILES.get());
                            output.accept(DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get());
                            output.accept(DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get());
                            output.accept(DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get());

                            if(ModList.get().isLoaded("aether_redux")){
                                output.accept(DABlocks.GILDED_HOLYSTONE_TILES.get());
                                output.accept(DABlocks.GILDED_HOLYSTONE_TILE_STAIRS.get());
                                output.accept(DABlocks.GILDED_HOLYSTONE_TILE_SLAB.get());
                                output.accept(DABlocks.GILDED_HOLYSTONE_TILE_WALL.get());
                                output.accept(DABlocks.BLIGHTMOSS_HOLYSTONE_TILES.get());
                                output.accept(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_STAIRS.get());
                                output.accept(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_SLAB.get());
                                output.accept(DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get());
                            }

                            output.accept(DABlocks.RADIANT_ORCHID.get());
                            output.accept(DABlocks.AERLAVENDER.get());
                            output.accept(DABlocks.TALL_AERLAVENDER.get());
                            output.accept(DABlocks.AETHER_CATTAILS.get());
                            output.accept(DABlocks.TALL_AETHER_CATTAILS.get());

                            output.accept(DABlocks.GOLDEN_GRASS_BLOCK.get());
                            output.accept(DABlocks.GOLDEN_DIRT_PATH.get());
                            output.accept(DABlocks.GOLDEN_FLOWER.get());
                            output.accept(DABlocks.MINI_GOLDEN_GRASS.get());
                            output.accept(DABlocks.SHORT_GOLDEN_GRASS.get());
                            output.accept(DABlocks.MEDIUM_GOLDEN_GRASS.get());
                            output.accept(DABlocks.TALL_GOLDEN_GRASS.get());

                            output.accept(DAItems.VIRULENT_QUICKSAND_BUCKET.get());
                            output.accept(DAItems.SKYROOT_VIRULENT_QUICKSAND_BUCKET.get());
                            output.accept(DAItems.PLACEABLE_POISON_BUCKET.get());
                        }));

        TAB_DEEP_AETHER_ITEMS_TAB = event.registerCreativeModeTab(
                new ResourceLocation(DeepAetherMod.MODID, "deep_aether_items"),
                builder -> builder.icon(() -> new ItemStack(DAItems.SKYJADE_TOOLS_SWORD.get()))
                        .title(Component.translatable("itemGroup." + DeepAetherMod.MODID + ".deep_aether_items"))
                        .displayItems((features, output) -> {
                            output.accept(DAItems.SKYJADE.get());
                            output.accept(DAItems.SKYJADE_TOOLS_SWORD.get());
                            output.accept(DAItems.SKYJADE_TOOLS_AXE.get());
                            output.accept(DAItems.SKYJADE_TOOLS_PICKAXE.get());
                            output.accept(DAItems.SKYJADE_TOOLS_SHOVEL.get());
                            output.accept(DAItems.SKYJADE_TOOLS_HOE.get());
                            output.accept(DAItems.SKYJADE_ARMOR_BOOTS.get());
                            output.accept(DAItems.SKYJADE_ARMOR_LEGGINGS.get());
                            output.accept(DAItems.SKYJADE_ARMOR_CHESTPLATE.get());
                            output.accept(DAItems.SKYJADE_ARMOR_HELMET.get());
                            output.accept(DAItems.SKYJADE_RING.get());
                            output.accept(DAItems.SKYJADE_GLOVES.get());

                            output.accept(DAItems.CLOUDIUM_INGOT.get());
                            output.accept(DAItems.CLOUDIUM_SCRAP.get());
                            output.accept(DAItems.CLOUDIUM_SWORD.get());
                            output.accept(DAItems.CLOUDIUM_AXE.get());
                            output.accept(DAItems.CLOUDIUM_PICKAXE.get());
                            output.accept(DAItems.CLOUDIUM_SHOVEL.get());
                            output.accept(DAItems.CLOUDIUM_HOE.get());
                            output.accept(DAItems.CLOUDIUM_BOOTS.get());
                            output.accept(DAItems.CLOUDIUM_LEGGINGS.get());
                            output.accept(DAItems.CLOUDIUM_CHESTPLATE.get());
                            output.accept(DAItems.CLOUDIUM_HELMET.get());
                            output.accept(DAItems.CLOUDIUM_RING.get());
                            output.accept(DAItems.CLOUDIUM_GLOVES.get());

                            output.accept(DAItems.GRAVITIE_RING.get());

                            output.accept(DAItems.RAW_AERGLOW_FISH.get());
                            output.accept(DAItems.COOKED_AERGLOW_FISH.get());
                            output.accept(DAItems.AERGLOW_FISH_BUCKET.get());
                            output.accept(DAItems.SKYROOT_AERGLOW_FISH_BUCKET.get());
                            output.accept(DAItems.RAW_QUAIL.get());
                            output.accept(DAItems.COOKED_QUAIL.get());
                            output.accept(DAItems.QUAIL_EGG.get());
                            output.accept(DAItems.GOLDEN_BERRIES.get());
                            output.accept(DAItems.GOLDEN_GRASS_SEEDS.get());
                            if(!ModList.get().isLoaded("aether_redux") && !ModList.get().isLoaded("aether_genesis")) {
                                output.accept(DAItems.GOLDEN_SWET_BALL.get());
                            }
                            output.accept(DAItems.ROSEROOT_BOAT.get());
                            output.accept(DAItems.ROSEROOT_CHEST_BOAT.get());
                            output.accept(DAItems.YAGROOT_BOAT.get());
                            output.accept(DAItems.YAGROOT_CHEST_BOAT.get());
                            output.accept(DAItems.CRUDEROOT_BOAT.get());
                            output.accept(DAItems.CRUDEROOT_CHEST_BOAT.get());
                            output.accept(DAItems.CONBERRY_BOAT.get());
                            output.accept(DAItems.CONBERRY_CHEST_BOAT.get());

                            output.accept(DAItems.MUSIC_DISC_NABOORU.get());
                            output.accept(DAItems.MUSIC_DISC_A_MORNING_WISH.get());

                            output.accept(DAItems.AETHER_FISH_SPAWN_EGG.get());
                            output.accept(DAItems.QUAIL_SPAWN_EGG.get());

                            output.accept(DAItems.AERGLOW_PETAL.get());
                            if(ModList.get().isLoaded("lost_aether_content")){
                                output.accept(DAItems.SKYJADE_SHIELD.get());
                                output.accept(DAItems.CLOUDIUM_SHIELD.get());
                            }
                        }));
    }
}
