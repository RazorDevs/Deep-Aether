package teamrazor.deepaether.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DAItems;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID)
public class AetherFishEntity extends AbstractSchoolingFish {

	public AetherFishEntity(EntityType<? extends AetherFishEntity> p_29790_, Level p_29791_) {
		super(p_29790_, p_29791_);
	}

	public int getMaxSchoolSize() {
		return 5;
	}

	public ItemStack getBucketItemStack() {
		return new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get());
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.SALMON_AMBIENT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.SALMON_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource p_29795_) {
		return SoundEvents.SALMON_HURT;
	}

	protected SoundEvent getFlopSound() {
		return SoundEvents.SALMON_FLOP;
	}
}