package teamrazor.deepaether.block;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import teamrazor.deepaether.fluids.DAFluidInteraction;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAParticles;

import java.util.function.Supplier;

public class PoisonBlock extends LiquidBlock {

    public PoisonBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    boolean COUNT = false;
    float TIME = 0;
    Item TRANSFORM_ITEM;
    boolean CAN_TRANSFORM = false;

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 250, 0, false, false));
        }
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter level, BlockPos pos, FluidState fluidState) {
        return true;
    }

    @Override
        public void animateTick (BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource){
            double d0 = blockPos.getX();
            double d1 = blockPos.getY();
            double d2 = blockPos.getZ();
            level.addAlwaysVisibleParticle(DAParticles.POISON_BUBBLES.get(), d0 + (double) randomSource.nextFloat(), d1 + (double) randomSource.nextFloat(), d2 + (double) randomSource.nextFloat(), 0.0D, 0.04D, 0.0D);
            if (randomSource.nextInt(10) == 0) {
                level.playLocalSound(d0, d1, d2, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.BLOCKS, 0.2F + randomSource.nextFloat() * 0.2F, 0.9F + randomSource.nextFloat() * 0.15F, false);
            }
            super.animateTick(blockState, level, blockPos, randomSource);


            if (COUNT && TIME < 100) {
                TIME += 0.05;
            } else {
                TIME = 0;
                COUNT = false;
            }
        }

        @Override
        public void entityInside (BlockState blockState, Level level, BlockPos pos, Entity entity) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 250, 0, false, false));
            } else if (entity instanceof ItemEntity itemEntity) {

                ItemEntity TRANSFORMED_ITEM_ENTITY = (new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.DIRT, 1)));
                int count = itemEntity.getItem().getCount();

                CAN_TRANSFORM = false;

                if (itemEntity.getItem().getItem() == AetherItems.ENCHANTED_DART.get()) {
                    TRANSFORM_ITEM = AetherItems.GOLDEN_DART.get();
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == AetherItems.ENCHANTED_DART_SHOOTER.get()) {
                    TRANSFORM_ITEM = AetherItems.GOLDEN_DART_SHOOTER.get();
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == AetherItems.HEALING_STONE.get()) {
                    TRANSFORM_ITEM = AetherBlocks.HOLYSTONE.get().asItem();
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == AetherBlocks.ENCHANTED_GRAVITITE.get().asItem()) {
                    TRANSFORM_ITEM = AetherBlocks.GRAVITITE_ORE.get().asItem();
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == AetherItems.ENCHANTED_BERRY.get()) {
                    TRANSFORM_ITEM = AetherItems.BLUE_BERRY.get();
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == AetherBlocks.QUICKSOIL_GLASS.get().asItem()) {
                    TRANSFORM_ITEM = AetherBlocks.QUICKSOIL.get().asItem();
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == AetherItems.SKYROOT_REMEDY_BUCKET.get()) {
                    TRANSFORM_ITEM = AetherItems.SKYROOT_POISON_BUCKET.get();
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == AetherItems.MUSIC_DISC_CHINCHILLA.get()) {
                    TRANSFORM_ITEM = Items.MUSIC_DISC_STRAD;
                    CAN_TRANSFORM = true;
                }

                if (itemEntity.getItem().getItem() == DABlocks.BLUE_SQUASH.get().asItem()) {
                    TRANSFORM_ITEM = DABlocks.PURPLE_SQUASH.get().asItem();
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == DABlocks.GREEN_SQUASH.get().asItem()) {
                    TRANSFORM_ITEM = DABlocks.PURPLE_SQUASH.get().asItem();
                    CAN_TRANSFORM = true;
                }

                if (!level.isClientSide && CAN_TRANSFORM) {
                    assert TRANSFORMED_ITEM_ENTITY != null;
                    if ((TRANSFORMED_ITEM_ENTITY.getFeetBlockState().getBlock() == this || level.getBlockState(TRANSFORMED_ITEM_ENTITY.getOnPos().below(1)).getBlock() == this) && TRANSFORMED_ITEM_ENTITY.isAlive()) {
                        BlockPos itemPos = TRANSFORMED_ITEM_ENTITY.getOnPos();
                        ServerLevel serverlevel = (ServerLevel) level;
                        serverlevel.sendParticles(DAParticles.POISON_BUBBLES.get(), (double) itemPos.getX() + level.random.nextDouble(), pos.getY() + 1, (double) itemPos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.0D, 0.2D, 0.3D);
                        if (level.random.nextInt(25) == 0) {
                            serverlevel.playSound(itemEntity, itemPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.2F + level.random.nextFloat() * 0.2F, 0.9F + level.random.nextFloat() * 0.15F);
                        }
                    }
                }

                COUNT = true;
                if ((TIME > 90) && itemEntity.isAlive() && CAN_TRANSFORM) {
                    CAN_TRANSFORM = false;
                    COUNT = false;
                    itemEntity.discard();
                    entity.spawnAtLocation(new ItemStack(TRANSFORM_ITEM, count), 0);
                    entity.setNoGravity(true);
                }
            }
        }

    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState state, boolean b) {
        if (!DAFluidInteraction.canInteract(level, blockPos)) {
            level.scheduleTick(blockPos, blockState.getFluidState().getType(), this.getFluid().getTickDelay(level));
        }
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        if (!DAFluidInteraction.canInteract(level, blockPos)) {
            level.scheduleTick(blockPos, blockState.getFluidState().getType(), this.getFluid().getTickDelay(level));
        }
    }
}

