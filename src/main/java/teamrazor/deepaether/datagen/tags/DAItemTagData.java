package teamrazor.deepaether.datagen.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.item.AetherItems;
import com.legacy.lost_aether.data.LCTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;
import net.zepalesque.aether.item.ReduxItems;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DAItemTagData extends ItemTagsProvider {
    public DAItemTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, registries, blockTags, DeepAetherMod.MODID, helper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether Item Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(AetherTags.Items.PLANKS_CRAFTING).add(
                DABlocks.ROSEROOT_PLANKS.get().asItem(),
                DABlocks.YAGROOT_PLANKS.get().asItem(),
                DABlocks.CRUDEROOT_PLANKS.get().asItem(),
                DABlocks.CONBERRY_PLANKS.get().asItem()
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
        tag(AetherTags.Items.SKYROOT_STICK_CRAFTING).add(
                DABlocks.ROSEROOT_PLANKS.get().asItem(),
                DABlocks.YAGROOT_PLANKS.get().asItem(),
                DABlocks.CRUDEROOT_PLANKS.get().asItem(),
                DABlocks.CONBERRY_PLANKS.get().asItem()
        );
        tag(AetherTags.Items.SKYROOT_TOOL_CRAFTING).add(
                DABlocks.ROSEROOT_PLANKS.get().asItem(),
                DABlocks.YAGROOT_PLANKS.get().asItem(),
                DABlocks.CRUDEROOT_PLANKS.get().asItem(),
                DABlocks.CONBERRY_PLANKS.get().asItem()
        );
        tag(AetherTags.Items.SKYROOT_REPAIRING).add(
                DABlocks.ROSEROOT_PLANKS.get().asItem(),
                DABlocks.YAGROOT_PLANKS.get().asItem(),
                DABlocks.CRUDEROOT_PLANKS.get().asItem(),
                DABlocks.CONBERRY_PLANKS.get().asItem()
        );
        tag(AetherTags.Items.SLIDER_DAMAGING_ITEMS).add(
                DAItems.SKYJADE_TOOLS_PICKAXE.get().asItem(),
                DAItems.CLOUDIUM_PICKAXE.get().asItem()
        );
        tag(AetherTags.Items.TREATED_AS_AETHER_ITEM).add(
                DAItems.SKYJADE_TOOLS_SWORD.get().asItem(),
                DAItems.SKYJADE_TOOLS_PICKAXE.get().asItem(),
                DAItems.SKYJADE_TOOLS_AXE.get().asItem(),
                DAItems.SKYJADE_TOOLS_SHOVEL.get().asItem(),
                DAItems.SKYJADE_TOOLS_HOE.get().asItem(),
                DAItems.CLOUDIUM_SWORD.get().asItem(),
                DAItems.CLOUDIUM_PICKAXE.get().asItem(),
                DAItems.CLOUDIUM_AXE.get().asItem(),
                DAItems.CLOUDIUM_SHOVEL.get().asItem(),
                DAItems.CLOUDIUM_HOE.get().asItem()
        );
        tag(ItemTags.SMALL_FLOWERS).add(
                DABlocks.AERLAVENDER.get().asItem(),
                DABlocks.AETHER_CATTAILS.get().asItem(),
                DABlocks.GOLDEN_FLOWER.get().asItem(),
                DABlocks.RADIANT_ORCHID.get().asItem()
        );

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
                DABlocks.STRIPPED_CONBERRY_WOOD.get().asItem()
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
                DABlocks.STRIPPED_CONBERRY_WOOD.get().asItem()
        );
        tag(ItemTags.SIGNS).add(
                DABlocks.ROSEROOT_SIGN.get().asItem(),
                DABlocks.YAGROOT_SIGN.get().asItem(),
                DABlocks.CRUDEROOT_SIGN.get().asItem(),
                DABlocks.CONBERRY_SIGN.get().asItem()
        );
        tag(ItemTags.HANGING_SIGNS).add(
                DABlocks.ROSEROOT_WALL_SIGN.get().asItem(),
                DABlocks.YAGROOT_WALL_SIGN.get().asItem(),
                DABlocks.CRUDEROOT_WALL_SIGN.get().asItem(),
                DABlocks.CONBERRY_WALL_SIGN.get().asItem()
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
                DABlocks.SKYROOT_WALL.get().asItem(),
                DABlocks.CLORITE_WALL.get().asItem(),
                DABlocks.ASETERITE_WALL.get().asItem(),
                DABlocks.AETHER_MUD_BRICKS_WALL.get().asItem(),
                DABlocks.HOLYSTONE_TILE_WALL.get().asItem(),
                DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get().asItem(),
                DABlocks.GILDED_HOLYSTONE_TILE_WALL.get().asItem(),
                DABlocks.BLIGHTMOSS_HOLYSTONE_TILE_WALL.get().asItem()
        );
        tag(ItemTags.FENCE_GATES).add(
                DABlocks.ROSEROOT_FENCE_GATE.get().asItem(),
                DABlocks.YAGROOT_FENCE_GATE.get().asItem(),
                DABlocks.CRUDEROOT_FENCE_GATE.get().asItem(),
                DABlocks.CONBERRY_FENCE_GATE.get().asItem()
        );
        tag(ItemTags.LEAVES).add(
                DABlocks.ROSEROOT_LEAVES.get().asItem(),
                DABlocks.BLUE_ROSEROOT_LEAVES.get().asItem(),
                DABlocks.FLOWERING_ROSEROOT_LEAVES.get().asItem(),
                DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get().asItem(),
                DABlocks.YAGROOT_LEAVES.get().asItem(),
                DABlocks.CRUDEROOT_LEAVES.get().asItem(),
                DABlocks.CONBERRY_LEAVES.get().asItem()
        );
        tag(DATags.Items.EGGS).add(
                DAItems.QUAIL_EGG.get(),
                Items.EGG
        );
        tag(DATags.Items.MILK_BUCKETS).add(
                AetherItems.SKYROOT_MILK_BUCKET.get(),
                Items.MILK_BUCKET
        );
        tag(DATags.Items.CLOUDIUM_REPAIRING).add(
                DAItems.CLOUDIUM_INGOT.get()
        );
        tag(DATags.Items.SKYJADE_REPAIRING).add(
                DAItems.SKYJADE.get()
        );
        tag(DATags.Items.IS_GOLDEN_SWET_BALL).add(
                DAItems.GOLDEN_SWET_BALL.get()
        ).addOptional(
                ReduxItems.GOLDEN_SWET_BALL.getKey().location()
        ).addOptional(
                new ResourceLocation("aether_genesis:golden_swet_ball")
        );

        if(ModList.get().isLoaded("lost_aether_content")) {
            tag(LCTags.Items.AETHER_SHIELDS).add(
                    DAItems.SKYJADE_SHIELD.get(),
                    DAItems.CLOUDIUM_SHIELD.get()
            );
        }
    }
}
