package teamrazor.deepaether.block.Behaviors;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import teamrazor.deepaether.init.DABlocks;

public class DADispenseBehaviors {
    public static final DispenseItemBehavior DEEP_AETHER_BUCKET_BUCKET_BEHAVIOR = new DefaultDispenseItemBehavior() {
        @Override
        public ItemStack execute(BlockSource blockSource, ItemStack itemStack) {
            LevelAccessor levelaccessor = blockSource.getLevel();
            BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
            BlockState blockstate = levelaccessor.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            if (block == DABlocks.VIRULENT_QUICKSAND.get()) {
                ItemStack itemstack = ((BucketPickup)block).pickupBlock(levelaccessor, blockpos, blockstate);
                if (itemstack.isEmpty()) {
                    return super.execute(blockSource, itemStack);
                } else {
                    levelaccessor.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, blockpos);
                    Item item = itemstack.getItem();
                    itemStack.shrink(1);
                    if (itemStack.isEmpty()) {
                        return new ItemStack(item);
                    } else {
                        if (blockSource.<DispenserBlockEntity>getEntity().addItem(new ItemStack(item)) < 0) {
                            super.execute(blockSource, new ItemStack(item));
                        }

                        return itemStack;
                    }
                }
            } else {
                return super.execute(blockSource, itemStack);
            }
        }
    };
    public static final DispenseItemBehavior WATER_BOTTLE_TO_AETHER_MUD_DISPENSE_BEHAVIOR = new DefaultDispenseItemBehavior() {
        @Override
        public ItemStack execute(BlockSource source, ItemStack stack) {
            if (PotionUtils.getPotion(stack) != Potions.WATER) {
                return super.execute(source, stack);
            } else {
                ServerLevel serverlevel = source.getLevel();
                BlockPos blockpos = source.getPos();
                BlockPos blockpos1 = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                if (!serverlevel.getBlockState(blockpos1).is(AetherBlocks.AETHER_DIRT.get())) {
                    return super.execute(source, stack);
                } else {
                    if (!serverlevel.isClientSide) {
                        for (int i = 0; i < 5; ++i) {
                            serverlevel.sendParticles(ParticleTypes.SPLASH, (double) blockpos.getX() + serverlevel.random.nextDouble(), (double) (blockpos.getY() + 1), (double) blockpos.getZ() + serverlevel.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                        }
                    }

                    serverlevel.playSound((Player) null, blockpos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    serverlevel.gameEvent((Entity) null, GameEvent.FLUID_PLACE, blockpos);
                    serverlevel.setBlockAndUpdate(blockpos1, DABlocks.AETHER_MUD.get().defaultBlockState());
                    return new ItemStack(Items.GLASS_BOTTLE);
                }
            }
        }
    };
    public static final DispenseItemBehavior DEEP_AETHER_BUCKET_PICKUP_DISPENSE_BEHAVIOR = new DefaultDispenseItemBehavior() {
        private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

        @Override
        public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
            DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)p_123562_.getItem();
            BlockPos blockpos = p_123561_.getPos().relative(p_123561_.getBlockState().getValue(DispenserBlock.FACING));
            Level level = p_123561_.getLevel();
            if (dispensiblecontaineritem.emptyContents((Player)null, level, blockpos, (BlockHitResult)null)) {
                dispensiblecontaineritem.checkExtraContent((Player)null, level, p_123562_, blockpos);
                return new ItemStack(Items.BUCKET);
            } else {
                return this.defaultDispenseItemBehavior.dispense(p_123561_, p_123562_);
            }
        }
    };
}