package io.github.razordevs.deep_aether.block.behavior;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;

public class DADispenseBehaviors {


    /**
     * [CODE COPY] - {@link DispenseItemBehavior#bootStrap()}.<br><br>
     * Based on default dispenser behavior for empty buckets.
     */
    public static final DispenseItemBehavior DEEP_AETHER_BUCKET_PICKUP_DISPENSE_BEHAVIOR = new DefaultDispenseItemBehavior() {
        private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

        @Override
        public ItemStack execute(BlockSource source, ItemStack stack) {
            DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)stack.getItem();
            BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
            Level level = source.level();
            if (dispensiblecontaineritem.emptyContents(null, level, blockpos, null, stack)) {
                dispensiblecontaineritem.checkExtraContent(null, level, stack, blockpos);
                return new ItemStack(Items.BUCKET);
            } else {
                return this.defaultDispenseItemBehavior.dispense(source, stack);
            }
        }
    };

    /**
     * [MODIFIED CODE] - {@link com.aetherteam.aether.block.dispenser.AetherDispenseBehaviors#SKYROOT_BUCKET_DISPENSE_BEHAVIOR()}.<br><br>
     * Dispenser behavior specifically for the Skyroot Poison Bucket
     */
    public static final DispenseItemBehavior SKYROOT_POISON_BUCKET_DISPENSE_BEHAVIOR = new DefaultDispenseItemBehavior() {
        private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

        @Override
        public ItemStack execute(BlockSource source, ItemStack stack) {
            DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem) DAItems.PLACEABLE_POISON_BUCKET.get();
            BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
            Level level = source.level();
            if (dispensiblecontaineritem.emptyContents(null, level, blockpos, null, stack)) {
                return new ItemStack(AetherItems.SKYROOT_BUCKET.get());
            } else {
                return this.defaultDispenseItemBehavior.dispense(source, stack);
            }
        }
    };
}