package teamrazor.deepaether.block.Behaviors;

import com.gildedgames.aether.block.AetherCauldronInteractions;
import com.gildedgames.aether.item.AetherItems;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

import java.util.Map;
import java.util.function.Predicate;

public interface DaCauldronInteraction {
    private static InteractionResult emptySkyrootBucket(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, BlockState state, SoundEvent sound) {
        if (!level.isClientSide()) {
            Item item = stack.getItem();
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack((ItemLike)AetherItems.SKYROOT_BUCKET.get())));
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(item));
            level.setBlockAndUpdate(pos, state);
            level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }
    Map<Item, net.minecraft.core.cauldron.CauldronInteraction> POISON = newInteractionMap();


    net.minecraft.core.cauldron.CauldronInteraction FILL_POISON = (p_175676_, p_175677_, p_175678_, p_175679_, p_175680_, p_175681_) -> CauldronInteraction.emptyBucket(p_175677_, p_175678_, p_175679_, p_175680_, p_175681_, DABlocks.POISON_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);
    net.minecraft.core.cauldron.CauldronInteraction FILL_POISON_SKYROOT = (p_175676_, p_175677_, p_175678_, p_175679_, p_175680_, p_175681_) -> emptySkyrootBucket(p_175677_, p_175678_, p_175679_, p_175680_, p_175681_, DABlocks.POISON_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);


    static Object2ObjectOpenHashMap<Item, net.minecraft.core.cauldron.CauldronInteraction> newInteractionMap() {
        return Util.make(new Object2ObjectOpenHashMap<>(), (p_175646_) -> {
            p_175646_.defaultReturnValue((p_175739_, p_175740_, p_175741_, p_175742_, p_175743_, p_175744_) -> InteractionResult.PASS);
        });
    }
    static void bootStrap() {
        POISON.put(Items.BUCKET, (p_175697_, p_175698_, p_175699_, p_175700_, p_175701_, p_175702_) -> {
            return CauldronInteraction.fillBucket(p_175697_, p_175698_, p_175699_, p_175700_, p_175701_, p_175702_, new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get()), (p_175651_) -> {
                return true;
            }, SoundEvents.BUCKET_FILL);
        });


        POISON.put(AetherItems.SKYROOT_BUCKET.get(), (p_175697_, p_175698_, p_175699_, p_175700_, p_175701_, p_175702_) -> {
            return CauldronInteraction.fillBucket(p_175697_, p_175698_, p_175699_, p_175700_, p_175701_, p_175702_, new ItemStack(AetherItems.SKYROOT_POISON_BUCKET.get()), (p_175651_) -> {
                return true;
            }, SoundEvents.BUCKET_FILL);
        });

        CauldronInteraction.EMPTY.put(AetherItems.SKYROOT_POISON_BUCKET.get(), DaCauldronInteraction.FILL_POISON_SKYROOT);
        CauldronInteraction.EMPTY.put(DAItems.PLACEABLE_POISON_BUCKET.get(), DaCauldronInteraction.FILL_POISON);

        DaCauldronInteraction.POISON.put(Items.BUCKET, (p_175697_, p_175698_, p_175699_, p_175700_, p_175701_, p_175702_) -> {
            return CauldronInteraction.fillBucket(p_175697_, p_175698_, p_175699_, p_175700_, p_175701_, p_175702_, new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get()), (p_175651_) -> {
                return true;
            }, SoundEvents.BUCKET_FILL);
        });

        DaCauldronInteraction.POISON.put(AetherItems.SKYROOT_BUCKET.get(), (p_175697_, p_175698_, p_175699_, p_175700_, p_175701_, p_175702_) -> {
            return CauldronInteraction.fillBucket(p_175697_, p_175698_, p_175699_, p_175700_, p_175701_, p_175702_, new ItemStack(AetherItems.SKYROOT_POISON_BUCKET.get()), (p_175651_) -> {
                return true;
            }, SoundEvents.BUCKET_FILL);
        });
    }
    static void addDefaultInteractions(Map<Item, net.minecraft.core.cauldron.CauldronInteraction> p_175648_) {
        p_175648_.put(DAItems.PLACEABLE_POISON_BUCKET.get(), FILL_POISON);
        p_175648_.put(AetherItems.SKYROOT_POISON_BUCKET.get(), FILL_POISON_SKYROOT);
    }

}