package teamrazor.deepaether.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluids;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DAItems;

public class AerglowFish extends AbstractSchoolingFish {

	public AerglowFish(EntityType<? extends AerglowFish> entityType, Level level) {
		super(entityType, level);
	}

	public static void init() {
		SpawnPlacements.register(DAEntities.AERGLOW_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE,
				(entityType, world, reason, pos,
				 random) -> (world.getBlockState(pos).getFluidState().isSourceOfType(Fluids.WATER)));
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

	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundEvents.SALMON_HURT;
	}

	protected SoundEvent getFlopSound() {
		return SoundEvents.SALMON_FLOP;
	}
}