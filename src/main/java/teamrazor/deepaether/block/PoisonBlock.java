package teamrazor.deepaether.block;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.effect.AetherEffects;
import com.gildedgames.aether.event.events.PlacementBanEvent;
import com.gildedgames.aether.item.AetherItems;
import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.Vec3;
import teamrazor.deepaether.init.DAParticles;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class PoisonBlock extends LiquidBlock {

    public PoisonBlock(Supplier<? extends FlowingFluid> p_54694_, Properties p_54695_) {
        super(p_54694_, p_54695_);
    }

    boolean CAN_DESTROY_ITEMS = true;
    boolean COUNT = false;
    float TIME = 0;
    Item TRANSFORM_ITEM;
    boolean CAN_TRANSFORM = false;

    @Override
    public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity entity) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 500, 0, false, false));
        }
    }

        @Override
        public void animateTick (BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource){
            double d0 = (double) blockPos.getX();
            double d1 = (double) blockPos.getY();
            double d2 = (double) blockPos.getZ();
            //level.addAlwaysVisibleParticle(DeepAetherModParticles.POISON_BUBBLES.get(), d0 + 0.5D, d1, d2 + 0.5D, 0.0D, 0.04D, 0.0D);
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
                ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 500, 0, false, false));
            } else if (entity instanceof ItemEntity itemEntity) {

                ItemEntity TRANSFORMED_ITEM_ENTITY = (new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.DIRT, 1)));
                int count = itemEntity.getItem().getCount();

                if (itemEntity.getItem().getItem() == AetherItems.MUSIC_DISC_LEGACY.get()) {
                    TRANSFORM_ITEM = Items.MUSIC_DISC_CAT;
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == AetherItems.ENCHANTED_DART.get()) {
                    TRANSFORM_ITEM = Items.GOLDEN_DART.get();
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == AetherItems.ENCHANTED_DART_SHOOTER.get()) {
                    TRANSFORM_ITEM = Items.GOLDEN_DART_SHOOTER.get();
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
                    TRANSFORM_ITEM = AetherBlocks.SKYROOT_POISON_BUCKET.get();
                    CAN_TRANSFORM = true;
                }
                if (itemEntity.getItem().getItem() == AetherItems.MUSIC_DISC_CHINCHILLA.get()) {
                    TRANSFORM_ITEM = Items.MUSIC_DISC_STRAD;
                    CAN_TRANSFORM = true;
                } else
                    CAN_TRANSFORM = false;

                COUNT = true;
                if ((TIME > 90) && itemEntity.isAlive() && CAN_TRANSFORM) {
                    CAN_TRANSFORM = false;
                    COUNT = false;
                    itemEntity.discard();
                    TRANSFORMED_ITEM_ENTITY = entity.spawnAtLocation(new ItemStack(TRANSFORM_ITEM, count), 0);
                    entity.setNoGravity(true);
                }



                if (!level.isClientSide && (TRANSFORMED_ITEM_ENTITY.getFeetBlockState().getBlock() == this || level.getBlockState(TRANSFORMED_ITEM_ENTITY.getOnPos().below(1)).getBlock() == this) && TRANSFORMED_ITEM_ENTITY.isAlive()) {
                    BlockPos itemPos = TRANSFORMED_ITEM_ENTITY.getOnPos();
                    ServerLevel serverlevel = (ServerLevel) level;
                    serverlevel.sendParticles(DAParticles.POISON_BUBBLES.get(), (double) itemPos.getX() + level.random.nextDouble(), (double) (pos.getY() + 1), (double) itemPos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.0D, 0.2D, 0.3D);
                    if (level.random.nextInt(25) == 0) {
                        serverlevel.playSound(itemEntity, itemPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.2F + level.random.nextFloat() * 0.2F, 0.9F + level.random.nextFloat() * 0.15F);
                    }
                }
            }
        }
}

