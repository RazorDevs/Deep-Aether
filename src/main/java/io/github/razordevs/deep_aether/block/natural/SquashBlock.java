package io.github.razordevs.deep_aether.block.natural;

import io.github.razordevs.deep_aether.block.building.CarvedSquashBlock;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;

public class SquashBlock extends Block {
    private final Block carved;

    public SquashBlock(Properties properties, Block carved) {
        super(properties);
        this.carved = carved;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!itemStack.canPerformAction(ItemAbilities.SHEARS_CARVE)) {
            return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
        } else if (level.isClientSide()) {
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        } else {
            Direction direction = blockHitResult.getDirection();
            Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : direction;
            level.playSound(null, blockPos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlock(blockPos, this.carved.defaultBlockState().setValue(CarvedSquashBlock.FACING, direction1), 11);
            ItemEntity itementity = new ItemEntity(level, (double)blockPos.getX() + 0.5 + (double)direction1.getStepX() * 0.65, (double)blockPos.getY() + 0.1, (double)blockPos.getZ() + 0.5 + (double)direction1.getStepZ() * 0.65, new ItemStack(DAItems.SQUASH_SEEDS.get(), 4));
            itementity.setDeltaMovement(0.05 * (double)direction1.getStepX() + level.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction1.getStepZ() + level.random.nextDouble() * 0.02);
            level.addFreshEntity(itementity);
            itemStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(interactionHand));
            level.gameEvent(player, GameEvent.SHEAR, blockPos);
            player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
    }
}
