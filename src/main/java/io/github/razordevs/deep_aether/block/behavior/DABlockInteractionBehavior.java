package io.github.razordevs.deep_aether.block.behavior;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.item.miscellaneous.AetherPortalItem;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.datagen.tags.DATags;
import io.github.razordevs.deep_aether.fluids.DAFluidTypes;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = DeepAether.MODID, bus = EventBusSubscriber.Bus.GAME)
public class DABlockInteractionBehavior {

    @SubscribeEvent
    public static void bonemealEvent(BonemealEvent event) {
        if (event.getState().is(DATags.Blocks.HAS_GLOWING_SPORES)) {
            Block.popResource(event.getLevel(), event.getPos(), new ItemStack(DABlocks.GLOWING_SPORES.get()));
            event.getStack().consume(1, event.getPlayer());
            event.setSuccessful(true);
        }
    }

    /**
     * Used for Block and Item Interactions.
     */
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        ItemStack itemstack = event.getItemStack();
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState state = level.getBlockState(pos);
        Player player = event.getEntity();

        if(itemstack.is(Tags.Items.TOOLS_SHEAR)) {
            handleShears(event, itemstack, pos, level, state, player);
        }
        else if (itemstack.getItem() == AetherItems.SKYROOT_POISON_BUCKET.get()) {
            handleSkyrootPoisonBucket(event, itemstack, level, player);
        }
        else if ((itemstack.getItem() == AetherItems.SKYROOT_BUCKET.get())) {
            handleSkyrootBucket(event, itemstack, level, player);
        }
    }

    /**
     * Handles shearing of Luminescent Spores
     */
    private static void handleShears(PlayerInteractEvent.RightClickBlock event, ItemStack itemstack, BlockPos pos, Level level, BlockState state, Player player) {
        if(state.getBlock().equals(DABlocks.GLOWING_VINE.get())) {
            Block.popResource(level, pos, new ItemStack(DABlocks.GLOWING_SPORES.get()));
            level.setBlock(pos, Blocks.VINE.defaultBlockState().setValue(PipeBlock.UP, state.getValue(PipeBlock.UP))
                    .setValue(PipeBlock.NORTH, state.getValue(PipeBlock.NORTH))
                    .setValue(PipeBlock.EAST, state.getValue(PipeBlock.EAST))
                    .setValue(PipeBlock.SOUTH, state.getValue(PipeBlock.SOUTH))
                    .setValue(PipeBlock.WEST, state.getValue(PipeBlock.WEST)), 18);
            level.playSound(player, pos, SoundEvents.BOGGED_SHEAR, SoundSource.PLAYERS, 1.0F, 1.0F);
            if(!level.isClientSide())
                itemstack.hurtAndBreak(1, (ServerLevel) level, player, item -> {});
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
        }
        else if(state.getBlock().equals(DABlocks.TALL_GLOWING_GRASS.get())) {
            if(state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF).equals(DoubleBlockHalf.UPPER)) {
                Block.popResource(level, pos, new ItemStack(DABlocks.GLOWING_SPORES.get()));
                level.setBlock(pos.below(1), Blocks.TALL_GRASS.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER),18);
                level.setBlock(pos, Blocks.TALL_GRASS.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), 18);
                level.playSound(player, pos, SoundEvents.BOGGED_SHEAR, SoundSource.PLAYERS, 1.0F, 1.0F);
                if(!level.isClientSide())
                    itemstack.hurtAndBreak(1, (ServerLevel) level, player, item -> {});
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
            }
        }
    }

    /**
     * Handles the placing of poison from a skyroot poison bucket
     */
    private static void handleSkyrootPoisonBucket(PlayerInteractEvent.RightClickBlock event, ItemStack itemstack, Level level, Player player) {
        BlockHitResult blockRayTraceResult = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
        BlockState blockHitState = level.getBlockState(blockRayTraceResult.getBlockPos());
        if (blockRayTraceResult.getType() == HitResult.Type.MISS ||
                blockRayTraceResult.getType() != HitResult.Type.BLOCK ||
                blockHitState.getBlock() == Blocks.CAULDRON ||
                (!(player.isShiftKeyDown()) && blockHitState.hasBlockEntity() && (level.getBlockEntity(blockRayTraceResult.getBlockPos()) instanceof MenuProvider))) {
            event.setCancellationResult(InteractionResult.PASS);
        }
        else {
            BlockPos blockpos = blockRayTraceResult.getBlockPos();
            Direction direction = blockRayTraceResult.getDirection();
            BlockPos relativePos = blockpos.relative(direction);
            if (level.mayInteract(player, blockpos) && player.mayUseItemAt(relativePos, direction, itemstack)) {
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, relativePos, itemstack);
                }

                player.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                    ItemStack bucketStack = new ItemStack(AetherItems.SKYROOT_BUCKET.get());
                    if (!player.addItem(bucketStack)) {
                        Containers.dropItemStack(player.level(), player.getX(), player.getY(), player.getZ(), bucketStack);
                    }
                }
                level.setBlockAndUpdate(relativePos, DABlocks.POISON_BLOCK.get().defaultBlockState());
                level.playSound(null, relativePos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
        }
    }

    /**
     * Handles the obtaining of poison and quicksand with a skyroot bucket
     */
    private static void handleSkyrootBucket(PlayerInteractEvent.RightClickBlock event, ItemStack itemstack, Level level, Player player) {
        BlockHitResult blockhitresult = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
        if (blockhitresult.getType() == HitResult.Type.MISS ||
                level.getBlockState(blockhitresult.getBlockPos()).getBlock() == Blocks.AIR ||
                level.getBlockState(blockhitresult.getBlockPos()).getBlock() == DABlocks.POISON_CAULDRON.get()) {
            event.setCancellationResult(InteractionResult.PASS);
        } else {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos relativePos = blockpos.relative(direction);

            Item item = null;

            if (level.getFluidState(relativePos).getFluidType() == DAFluidTypes.POISON_FLUID_TYPE.value()) {
                item = AetherItems.SKYROOT_POISON_BUCKET.get();
            } else if (level.getBlockState(blockpos).is(DABlocks.VIRULENT_QUICKSAND.get())){
                item = DAItems.SKYROOT_VIRULENT_QUICKSAND_BUCKET.get();
                relativePos = blockpos;
            }

            System.out.println(item);

            if(item != null)
                skyrootBucketInteractionResult(event, level, relativePos, player, itemstack, item);
        }
    }

    private static void skyrootBucketInteractionResult(PlayerInteractEvent.RightClickBlock event, Level level, BlockPos relativePos, Player player, ItemStack itemstack, Item item){
        player.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
            ItemStack bucketStack = new ItemStack(item);
            if (!player.addItem(bucketStack)) {
                Containers.dropItemStack(player.level(), player.getX(), player.getY(), player.getZ(), bucketStack);
            }
        }
        level.setBlockAndUpdate(relativePos, Blocks.AIR.defaultBlockState());
        level.playSound(null, relativePos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
        event.setCancellationResult(InteractionResult.SUCCESS);
    }
 }