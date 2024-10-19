package io.github.razordevs.deepaether.deepaether.block.behavior;

import com.aetherteam.aether.item.AetherItems;
import io.github.razordevs.deepaether.deepaether.init.DABlocks;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import io.github.razordevs.deepaether.deepaether.init.DAItems;

public interface DaCauldronInteraction {
    //Map<String, CauldronInteraction.InteractionMap> INTERACTIONS = new Object2ObjectArrayMap<>();
    private static InteractionResult emptySkyrootBucket(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, BlockState state) {
        if (!level.isClientSide()) {
            Item item = stack.getItem();
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(AetherItems.SKYROOT_BUCKET.get())));
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(item));
            level.setBlockAndUpdate(pos, state);
            level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
    CauldronInteraction.InteractionMap POISON = newInteractionMap("poison");


    CauldronInteraction FILL_POISON = (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.emptyBucket(level, blockPos, player, hand, itemStack, DABlocks.POISON_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);
    CauldronInteraction FILL_POISON_SKYROOT = (blockState, level, blockPos, player, hand, itemStack) -> emptySkyrootBucket(level, blockPos, player, hand, itemStack, DABlocks.POISON_CAULDRON.get().defaultBlockState());

    static CauldronInteraction.InteractionMap newInteractionMap(String pName) {
        Object2ObjectOpenHashMap<Item, CauldronInteraction> object2objectopenhashmap = new Object2ObjectOpenHashMap<>();
        object2objectopenhashmap.defaultReturnValue((p_175739_, p_175740_, p_175741_, p_175742_, p_175743_, p_175744_) -> InteractionResult.PASS);
        CauldronInteraction.InteractionMap cauldroninteraction$interactionmap = new CauldronInteraction.InteractionMap(pName, object2objectopenhashmap);
        //INTERACTIONS.put(pName, cauldroninteraction$interactionmap);
        return cauldroninteraction$interactionmap;
    }
    static void bootStrap() {
        POISON.map().put(Items.BUCKET, (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.fillBucket(blockState, level, blockPos, player, hand, itemStack, new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get()), (state) -> true, SoundEvents.BUCKET_FILL));

        POISON.map().put(AetherItems.SKYROOT_BUCKET.get(), (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.fillBucket(blockState, level, blockPos, player, hand, itemStack, new ItemStack(AetherItems.SKYROOT_POISON_BUCKET.get()), (state) -> true, SoundEvents.BUCKET_FILL));

        CauldronInteraction.EMPTY.map().put(AetherItems.SKYROOT_POISON_BUCKET.get(), DaCauldronInteraction.FILL_POISON_SKYROOT);
        CauldronInteraction.EMPTY.map().put(DAItems.PLACEABLE_POISON_BUCKET.get(), DaCauldronInteraction.FILL_POISON);

        DaCauldronInteraction.POISON.map().put(Items.BUCKET, (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.fillBucket(blockState, level, blockPos, player, hand, itemStack, new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get()), (state) -> true, SoundEvents.BUCKET_FILL));

        DaCauldronInteraction.POISON.map().put(AetherItems.SKYROOT_BUCKET.get(), (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.fillBucket(blockState, level, blockPos, player, hand, itemStack, new ItemStack(AetherItems.SKYROOT_POISON_BUCKET.get()), (state) -> true, SoundEvents.BUCKET_FILL));
    }
}