

package teamrazor.deepaether.init;

import com.gildedgames.aether.Aether;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;

import java.util.List;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepAetherModTabs {
    public static CreativeModeTab TAB_DEEP_AETHER_BLOCKS_TAB;
    public static CreativeModeTab TAB_DEEP_AETHER_ITEMS_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        TAB_DEEP_AETHER_BLOCKS_TAB = event.registerCreativeModeTab(
                new ResourceLocation(DeepAetherMod.MODID, "deep_aether_blocks"),
                builder -> builder.icon(() -> new ItemStack(DeepAetherModBlocks.FLOWERING_ROSE_LEAVES.get()))
                        .title(Component.translatable("itemGroup." + DeepAetherMod.MODID + ".deep_aether_blocks"))
                        .displayItems((features, output, hasPermissions) -> {
                            output.accept(DeepAetherModBlocks.ROSE_LOG.get());
                            output.accept(DeepAetherModBlocks.ROSE_WOOD.get());
                            output.accept(DeepAetherModBlocks.STRIPPED_ROSE_LOG.get());
                            output.accept(DeepAetherModBlocks.STRIPPED_ROSE_WOOD.get());
                            output.accept(DeepAetherModBlocks.ROSE_PLANKS.get());
                            output.accept(DeepAetherModBlocks.ROSE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.ROSE_SLAB.get());
                            output.accept(DeepAetherModBlocks.ROSE_FENCE.get());
                            output.accept(DeepAetherModBlocks.ROSE_FENCE_GATE.get());
                            output.accept(DeepAetherModBlocks.ROSE_DOOR.get());
                            output.accept(DeepAetherModBlocks.ROSE_TRAPDOOR.get());
                            output.accept(DeepAetherModBlocks.ROSE_PRESSURE_PLATE.get());
                            output.accept(DeepAetherModBlocks.ROSE_BUTTON.get());
                            output.accept(DeepAetherModBlocks.ROSE_WALL.get());
                            output.accept(DeepAetherModBlocks.STRIPPED_ROSE_WALL.get());
                            output.accept(DeepAetherModItems.ROSEROOT_SIGN.get());
                            output.accept(DeepAetherModBlocks.ROSE_LEAVES.get());
                            output.accept(DeepAetherModBlocks.FLOWERING_ROSE_LEAVES.get());
                            output.accept(DeepAetherModBlocks.AERGLOW_PETAL_BLOCK.get());
                            output.accept(DeepAetherModBlocks.ROSEWOOD_SAPLING.get());

                            output.accept(DeepAetherModBlocks.YAGROOT_LOG.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_WOOD.get());
                            output.accept(DeepAetherModBlocks.STRIPPED_YAGROOT_LOG.get());
                            output.accept(DeepAetherModBlocks.STRIPPED_YAGROOT_WOOD.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_PLANKS.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_STAIRS.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_SLAB.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_FENCE.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_FENCE_GATE.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_DOOR.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_TRAPDOOR.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_PRESSURE_PLATE.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_BUTTON.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_WALL.get());
                            output.accept(DeepAetherModBlocks.STRIPPED_YAGROOT_WALL.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_ROOTS.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_VINE.get());
                            output.accept(DeepAetherModItems.YAGROOT_SIGN.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_LEAVES.get());
                            output.accept(DeepAetherModBlocks.YAGROOT_SAPLING.get());


                            output.accept(DeepAetherModBlocks.CRUDEROOT_LOG.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_WOOD.get());
                            output.accept(DeepAetherModBlocks.STRIPPED_CRUDEROOT_LOG.get());
                            output.accept(DeepAetherModBlocks.STRIPPED_CRUDEROOT_WOOD.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_PLANKS.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_STAIRS.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_SLAB.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_FENCE.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_FENCE_GATE.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_DOOR.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_TRAPDOOR.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_PRESSURE_PLATE.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_WALL.get());
                            output.accept(DeepAetherModBlocks.STRIPPED_CRUDEROOT_WALL.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_BUTTON.get());
                            output.accept(DeepAetherModItems.CRUDEROOT_SIGN.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_LEAVES.get());
                            output.accept(DeepAetherModBlocks.CRUDEROOT_SAPLING.get());

                            output.accept(DeepAetherModBlocks.AETHER_MOSS_BLOCK.get());
                            output.accept(DeepAetherModBlocks.AETHER_MOSS_CARPET.get());

                            output.accept(DeepAetherModBlocks.AETHER_MUD.get());
                            output.accept(DeepAetherModBlocks.PACKED_AETHER_MUD.get());
                            output.accept(DeepAetherModBlocks.MUDDY_YAGROOT_ROOTS.get());
                            output.accept(DeepAetherModBlocks.AETHER_MUD_BRICKS_STAIRS.get());
                            output.accept(DeepAetherModBlocks.AETHER_MUD_BRICKS_SLAB.get());

                            output.accept(DeepAetherModBlocks.ASETERITE.get());
                            output.accept(DeepAetherModBlocks.ASETERITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.ASETERITE_SLAB.get());
                            output.accept(DeepAetherModBlocks.ASETERITE_WALL.get());
                            output.accept(DeepAetherModBlocks.POLISHED_ASETERITE.get());
                            output.accept(DeepAetherModBlocks.POLISHED_ASETERITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.POLISHED_ASETERITE_SLAB.get());

                            output.accept(DeepAetherModBlocks.JARINITE.get());
                            output.accept(DeepAetherModBlocks.JARINITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.JARINITE_SLAB.get());
                            output.accept(DeepAetherModBlocks.JARINITE_WALL.get());
                            output.accept(DeepAetherModBlocks.POLISHED_JARINITE.get());
                            output.accept(DeepAetherModBlocks.POLISHED_JARINITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.POLISHED_JARINITE_SLAB.get());

                            output.accept(DeepAetherModBlocks.GREOTITE.get());
                            output.accept(DeepAetherModBlocks.GREOTITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.GREOTITE_SLAB.get());
                            output.accept(DeepAetherModBlocks.GREOTITE_WALL.get());
                            output.accept(DeepAetherModBlocks.POLISHED_GREOTITE.get());
                            output.accept(DeepAetherModBlocks.POLISHED_GREOTITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.POLISHED_GREOTITE_SLAB.get());

                            output.accept(DeepAetherModBlocks.CLORITE.get());
                            output.accept(DeepAetherModBlocks.CLORITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.CLORITE_SLAB.get());
                            output.accept(DeepAetherModBlocks.CLORITE_WALL.get());
                            output.accept(DeepAetherModBlocks.POLISHED_CLORITE.get());
                            output.accept(DeepAetherModBlocks.POLISHED_CLORITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.POLISHED_CLORITE_SLAB.get());

                            output.accept(DeepAetherModBlocks.YALLESITE.get());
                            output.accept(DeepAetherModBlocks.YALLESITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.YALLESITE_SLAB.get());
                            output.accept(DeepAetherModBlocks.YALLESITE_WALL.get());
                            output.accept(DeepAetherModBlocks.POLISHED_YALLESITE.get());
                            output.accept(DeepAetherModBlocks.POLISHED_YALLESITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.POLISHED_YALLESITE_SLAB.get());

                            output.accept(DeepAetherModBlocks.DARKERITE.get());
                            output.accept(DeepAetherModBlocks.DARKERITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.DARKERITE_SLAB.get());
                            output.accept(DeepAetherModBlocks.DARKERITE_WALL.get());
                            output.accept(DeepAetherModBlocks.POLISHED_DARKERITE.get());
                            output.accept(DeepAetherModBlocks.POLISHED_DARKERITE_STAIRS.get());
                            output.accept(DeepAetherModBlocks.POLISHED_DARKERITE_SLAB.get());

                            output.accept(DeepAetherModBlocks.SKYJADE_ORE.get());
                            output.accept(DeepAetherModBlocks.SKYJADE_BLOCK.get());
                            output.accept(DeepAetherModBlocks.CLOUDIUM_DEBRIS.get());
                            output.accept(DeepAetherModBlocks.CLOUDIUM_BLOCK.get());

                            output.accept(DeepAetherModBlocks.HOLYSTONE_BRICKS.get());
                            output.accept(DeepAetherModBlocks.HOLYSTONE_BRICKS_SLAB.get());
                            output.accept(DeepAetherModBlocks.HOLYSTONE_BRICKS_STAIRS.get());

                            output.accept(DeepAetherModBlocks.RADIANT_ORCHID.get());
                            output.accept(DeepAetherModBlocks.AERLAVENDER.get());
                            output.accept(DeepAetherModBlocks.TALL_AERLAVENDER.get());

                            output.accept(DeepAetherModItems.VIRULENT_QUICKSAND_BUCKET.get());
                            output.accept(DeepAetherModItems.SKYROOT_VIRULENT_QUICKSAND_BUCKET.get());
                            output.accept(DeepAetherModItems.PLACEABLE_POISON_BUCKET.get());
                        }));

        TAB_DEEP_AETHER_ITEMS_TAB = event.registerCreativeModeTab(
                new ResourceLocation(DeepAetherMod.MODID, "deep_aether_items"),
                builder -> builder.icon(() -> new ItemStack(DeepAetherModBlocks.FLOWERING_ROSE_LEAVES.get()))
                        .title(Component.translatable("itemGroup." + DeepAetherMod.MODID + ".deep_aether_items"))
                        .displayItems((features, output, hasPermissions) -> {

                        }));
    }
}



/*
		};
		TAB_DEEP_AETHER_ITEMS_TAB = new CreativeModeTab("tabdeep_aether_items_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(DeepAetherModItems.SKYJADE_TOOLS_SWORD.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
*/