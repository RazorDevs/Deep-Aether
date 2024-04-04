package teamrazor.deepaether.block.behavior;

import com.aetherteam.aether.item.AetherItems;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
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
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

import java.util.Map;

public interface DaCauldronInteraction {
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
    Map<Item, net.minecraft.core.cauldron.CauldronInteraction> POISON = newInteractionMap();


    CauldronInteraction FILL_POISON = (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.emptyBucket(level, blockPos, player, hand, itemStack, DABlocks.POISON_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);
    CauldronInteraction FILL_POISON_SKYROOT = (blockState, level, blockPos, player, hand, itemStack) -> emptySkyrootBucket(level, blockPos, player, hand, itemStack, DABlocks.POISON_CAULDRON.get().defaultBlockState());


    static Object2ObjectOpenHashMap<Item, net.minecraft.core.cauldron.CauldronInteraction> newInteractionMap() {
        return Util.make(new Object2ObjectOpenHashMap<>(), (interaction) -> {
            interaction.defaultReturnValue((blockState, level, blockPos, player, hand, itemStack) -> InteractionResult.PASS);
        });
    }

    static void bootStrap() {
        POISON.put(Items.BUCKET, (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.fillBucket(blockState, level, blockPos, player, hand, itemStack, new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get()), (state) -> true, SoundEvents.BUCKET_FILL));

        POISON.put(AetherItems.SKYROOT_BUCKET.get(), (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.fillBucket(blockState, level, blockPos, player, hand, itemStack, new ItemStack(AetherItems.SKYROOT_POISON_BUCKET.get()), (state) -> true, SoundEvents.BUCKET_FILL));

        CauldronInteraction.EMPTY.put(AetherItems.SKYROOT_POISON_BUCKET.get(), DaCauldronInteraction.FILL_POISON_SKYROOT);
        CauldronInteraction.EMPTY.put(DAItems.PLACEABLE_POISON_BUCKET.get(), DaCauldronInteraction.FILL_POISON);

        DaCauldronInteraction.POISON.put(Items.BUCKET, (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.fillBucket(blockState, level, blockPos, player, hand, itemStack, new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get()), (state) -> true, SoundEvents.BUCKET_FILL));

        DaCauldronInteraction.POISON.put(AetherItems.SKYROOT_BUCKET.get(), (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.fillBucket(blockState, level, blockPos, player, hand, itemStack, new ItemStack(AetherItems.SKYROOT_POISON_BUCKET.get()), (state) -> true, SoundEvents.BUCKET_FILL));
    }
}