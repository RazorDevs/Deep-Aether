package teamrazor.deepaether.item.misc;

import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.item.miscellaneous.ConsumableItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.registries.RegistryObject;

public class DrinkableBucketItem extends BucketItem implements ConsumableItem {

    boolean CAN_CONSUME = false;
    public DrinkableBucketItem(RegistryObject<FlowingFluid> fluid, Properties properties) {
        super(fluid, properties);
    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        if(CAN_CONSUME) {
            if (!level.isClientSide()) {
                user.addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 500, 0));
            }

            this.consume(this, stack, user);
            return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
        }
        else return stack;
    }

    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

   // BlockHitResult blockhitresult1 = Item.getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
   //     if (blockhitresult1.getType() == HitResult.Type.MISS) {
   //     CAN_CONSUME = true;
   //     return ItemUtils.startUsingInstantly(world, player, hand);
   // }
   //     else {
   //     CAN_CONSUME = false;
   //     return new InteractionResultHolder<>(InteractionResult.PASS, player.getItemInHand(hand));
   // }

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(world, player, this.getFluid() == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(player, world, itemstack, blockhitresult);
        if (ret != null)
            return ret;
        if (blockhitresult.getType() == HitResult.Type.MISS) {
            CAN_CONSUME = true;
            return ItemUtils.startUsingInstantly(world, player, hand);
        } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
            CAN_CONSUME = true;
            return ItemUtils.startUsingInstantly(world, player, hand);
        } else {
            CAN_CONSUME = false;
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (world.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos1, direction, itemstack)) {
                if (this.getFluid() == Fluids.EMPTY) {
                    BlockState blockstate1 = world.getBlockState(blockpos);
                    if (blockstate1.getBlock() instanceof BucketPickup) {
                        BucketPickup bucketpickup = (BucketPickup)blockstate1.getBlock();
                        ItemStack itemstack1 = bucketpickup.pickupBlock(world, blockpos, blockstate1);
                        if (!itemstack1.isEmpty()) {
                            player.awardStat(Stats.ITEM_USED.get(this));
                            bucketpickup.getPickupSound(blockstate1).ifPresent((p_150709_) -> {
                                player.playSound(p_150709_, 1.0F, 1.0F);
                            });
                            world.gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);
                            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, player, itemstack1);
                            if (!world.isClientSide) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, itemstack1);
                            }

                            return InteractionResultHolder.sidedSuccess(itemstack2, world.isClientSide());
                        }
                    }

                    return InteractionResultHolder.fail(itemstack);
                } else {
                    BlockState blockstate = world.getBlockState(blockpos);
                    BlockPos blockpos2 = canBlockContainFluid(world, blockpos, blockstate) ? blockpos : blockpos1;
                    if (this.emptyContents(player, world, blockpos2, blockhitresult, itemstack)) {
                        this.checkExtraContent(player, world, itemstack, blockpos2);
                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos2, itemstack);
                        }

                        player.awardStat(Stats.ITEM_USED.get(this));
                        return InteractionResultHolder.sidedSuccess(getEmptySuccessItem(itemstack, player), world.isClientSide());
                    } else {
                        return InteractionResultHolder.fail(itemstack);
                    }
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }
}
