
package teamrazor.deepaether.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Material;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import teamrazor.deepaether.init.DeepAetherModEntities;

import javax.annotation.Nullable;
import java.util.Set;

@Mod.EventBusSubscriber
public class AerglowFishEntity extends Cod {

	private static final Set<ResourceLocation> SPAWN_BIOMES = Set.of(new ResourceLocation("warm_ocean"));

	public AerglowFishEntity(EntityType<? extends Cod> p_28276_, Level p_28277_) {
		super(p_28276_, p_28277_);
	}

	@SubscribeEvent
	public static void addLivingEntityToBiomes(BiomeLoadingEvent event) {
		if (SPAWN_BIOMES.contains(event.getName()))
			event.getSpawns().getSpawner(MobCategory.WATER_CREATURE)
					.add(new MobSpawnSettings.SpawnerData(DeepAetherModEntities.AETHER_FISH.get(), 10, 2, 4));
	}


	/*public AerglowFishEntity(EntityType<AerglowFishEntity> type, Level world) {
		super(type, world);
		xpReward = 1;
		setNoAi(false);

		this.setPathfindingMalus(BlockPathTypes.WATER, 0);
		this.moveControl = new MoveControl(this) {
			@Override
			public void tick() {
				if (AetherFishEntity.this.isInWater())
					AetherFishEntity.this.setDeltaMovement(AetherFishEntity.this.getDeltaMovement().add(0, 0.005, 0));

				if (this.operation == MoveControl.Operation.MOVE_TO && !AetherFishEntity.this.getNavigation().isDone()) {
					double dx = this.wantedX - AetherFishEntity.this.getX();
					double dy = this.wantedY - AetherFishEntity.this.getY();
					double dz = this.wantedZ - AetherFishEntity.this.getZ();

					float f = (float) (Mth.atan2(dz, dx) * (double) (180 / Math.PI)) - 90;
					float f1 = (float) (this.speedModifier * AetherFishEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());

					AetherFishEntity.this.setYRot(this.rotlerp(AetherFishEntity.this.getYRot(), f, 10));
					AetherFishEntity.this.yBodyRot = AetherFishEntity.this.getYRot();
					AetherFishEntity.this.yHeadRot = AetherFishEntity.this.getYRot();

					if (AetherFishEntity.this.isInWater()) {
						AetherFishEntity.this.setSpeed((float) AetherFishEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());

						float f2 = -(float) (Mth.atan2(dy, (float) Math.sqrt(dx * dx + dz * dz)) * (180 / Math.PI));
						f2 = Mth.clamp(Mth.wrapDegrees(f2), -85, 85);
						AetherFishEntity.this.setXRot(this.rotlerp(AetherFishEntity.this.getXRot(), f2, 5));
						float f3 = Mth.cos(AetherFishEntity.this.getXRot() * (float) (Math.PI / 180.0));

						AetherFishEntity.this.setZza(f3 * f1);
						AetherFishEntity.this.setYya((float) (f1 * dy));
					} else {
						AetherFishEntity.this.setSpeed(f1 * 0.05F);
					}
				} else {
					AetherFishEntity.this.setSpeed(0);
					AetherFishEntity.this.setYya(0);
					AetherFishEntity.this.setZza(0);
				}
			}
		};
	}*/

	/*@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new WaterBoundPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

	}

	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return SoundEvents.COD_AMBIENT;
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.cod.flop")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return SoundEvents.COD_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.COD_HURT;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source == DamageSource.DROWN)
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return world.isUnobstructed(this);
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}*/

	public static void init() {
		SpawnPlacements.register(DeepAetherModEntities.AETHER_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos,
						random) -> (world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER)));

	}

}
