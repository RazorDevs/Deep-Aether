package teamrazor.deepaether.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.fml.common.Mod;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DAItems;

public class AerglowFish extends AbstractSchoolingFish {

	public AerglowFish(EntityType<? extends AerglowFish> p_29790_, Level p_29791_) {
		super(p_29790_, p_29791_);
	}

	public int getMaxSchoolSize() {
		return 5;
	}

	public ItemStack getBucketItemStack() {
		return new ItemStack(DAItems.AERGLOW_FISH_BUCKET.get());
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