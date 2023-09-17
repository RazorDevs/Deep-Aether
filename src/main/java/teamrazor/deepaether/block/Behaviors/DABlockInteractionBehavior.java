package teamrazor.deepaether.block.Behaviors;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.datagen.tags.DATags;
import teamrazor.deepaether.fluids.DAFluidTypes;
import teamrazor.deepaether.init.DABlocks;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DABlockInteractionBehavior {

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        ItemStack itemstack = event.getItemStack();
        BlockPos pos = event.getPos();
        Level world = event.getLevel();
        BlockState state = world.getBlockState(pos);
        if (itemstack.is(DATags.Items.IS_GOLDEN_SWET_BALL)) {
            if (state.getBlock() == AetherBlocks.AETHER_DIRT.get()) {
                BlockState newState = DABlocks.GOLDEN_GRASS_BLOCK.get().defaultBlockState();
                world.setBlockAndUpdate(pos, newState);
                itemstack.shrink(1);
                event.getEntity().awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }

        if ((event.getFace() != Direction.DOWN && PotionUtils.getPotion(itemstack) == Potions.WATER)) {
            if (state.getBlock() == AetherBlocks.AETHER_DIRT.get()) {
                BlockState newState = DABlocks.AETHER_MUD.get().defaultBlockState();

                world.setBlockAndUpdate(pos, newState);

                Player player = event.getEntity();


                player.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
                if (!world.isClientSide) {
                    ServerLevel serverlevel = (ServerLevel) world;
                    for (int i = 0; i < 5; ++i) {
                        serverlevel.sendParticles(ParticleTypes.SPLASH, (double) pos.getX() + world.random.nextDouble(), (double) (pos.getY() + 1), (double) pos.getZ() + world.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                    }
                }
                world.playSound(player, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.PLAYERS, 0.5F, 1F);
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
        if (itemstack.getItem() == AetherItems.SKYROOT_POISON_BUCKET.get()) {
            final Player player = event.getEntity();
            BlockHitResult blockRayTraceResult = Item.getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
            if (blockRayTraceResult.getType() == HitResult.Type.MISS) {
                event.setCancellationResult(InteractionResult.PASS);
            } else if (blockRayTraceResult.getType() != HitResult.Type.BLOCK) {
                event.setCancellationResult(InteractionResult.PASS);
            } else if (world.getBlockState(blockRayTraceResult.getBlockPos()).getBlock() == Blocks.CAULDRON) {
                event.setCancellationResult(InteractionResult.PASS);
            } else {
                BlockPos blockpos = blockRayTraceResult.getBlockPos();
                Direction direction = blockRayTraceResult.getDirection();
                BlockPos relativePos = blockpos.relative(direction);
                if (world.mayInteract(player, blockpos) && player.mayUseItemAt(relativePos, direction, itemstack)) {
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
                    world.setBlockAndUpdate(relativePos, DABlocks.POISON_BLOCK.get().defaultBlockState());
                    world.playSound(null, relativePos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
            }
        }
        if ((itemstack.getItem() == AetherItems.SKYROOT_BUCKET.get())) {
            Player player = event.getEntity();
            BlockHitResult blockhitresult = Item.getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
            if (blockhitresult.getType() == HitResult.Type.MISS) {
                event.setCancellationResult(InteractionResult.PASS);
            } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
                event.setCancellationResult(InteractionResult.PASS);
            } else if (world.getBlockState(blockhitresult.getBlockPos()).getBlock() == DABlocks.POISON_CAULDRON.get()) {
                event.setCancellationResult(InteractionResult.PASS);
            } else {
                BlockPos blockpos = blockhitresult.getBlockPos();
                Direction direction = blockhitresult.getDirection();
                BlockPos relativePos = blockpos.relative(direction);
                if (world.getFluidState(relativePos).getFluidType() == DAFluidTypes.POISON_FLUID_TYPE.get()) {

                    player.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        ItemStack bucketStack = new ItemStack(AetherItems.SKYROOT_POISON_BUCKET.get());
                        if (!player.addItem(bucketStack)) {
                            Containers.dropItemStack(player.level(), player.getX(), player.getY(), player.getZ(), bucketStack);
                        }
                    }
                    world.setBlockAndUpdate(relativePos, Blocks.AIR.defaultBlockState());
                    world.playSound(null, relativePos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
            }
        }
    }
}


