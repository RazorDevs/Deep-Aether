
package teamrazor.deepaether.entity;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.level.ServerLevelAccessor;
import teamrazor.deepaether.init.DeepAetherModEntities;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.Packet;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Mod.EventBusSubscriber
public class AetherFishEntity extends Cod {
	private static final Set<ResourceLocation> SPAWN_BIOMES = Set.of(new ResourceLocation("warm_ocean"));
	@Nullable
	private AetherFishEntity leader;
	private int schoolSize = 1;

	@SubscribeEvent
	public static void addLivingEntityToBiomes(BiomeLoadingEvent event) {
		if (SPAWN_BIOMES.contains(event.getName()))
			event.getSpawns().getSpawner(MobCategory.WATER_CREATURE)
					.add(new MobSpawnSettings.SpawnerData(DeepAetherModEntities.AETHER_FISH.get(), 10, 2, 4));
	}

	public AetherFishEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(DeepAetherModEntities.AETHER_FISH.get(), world);
	}

	public AetherFishEntity(EntityType<AetherFishEntity> type, Level world) {
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
	}

	@Override
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
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
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
		return SoundEvents.COD_DEATH;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source == DamageSource.DROWN)
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return world.isUnobstructed(this);
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	public static void init() {
		SpawnPlacements.register(DeepAetherModEntities.AETHER_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos,
						random) -> (world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER)));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 3);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(ForgeMod.SWIM_SPEED.get(), 0.3);
		return builder;
	}

	protected boolean canRandomSwim() {
		return !this.isFollower();
	}

	public boolean isFollower() {
		return this.leader != null && this.leader.isAlive();
	}

	public AbstractSchoolingFish startFollowing(AetherFishEntity p_27526_) {
		this.leader = p_27526_;
		p_27526_.addFollow();
		return p_27526_;
	}

	public void stopFollowing() {
		this.leader.removeFollow();
		this.leader = null;
	}

	private void addFollow() {
		++this.schoolSize;
	}

	private void removeFollow() {
		--this.schoolSize;
	}

	public boolean canBeFollowed() {
		return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
	}

	public void tick() {
		super.tick();
		if (this.hasFollowers() && this.level.random.nextInt(200) == 1) {
			List<? extends AbstractFish> list = this.level.getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (list.size() <= 1) {
				this.schoolSize = 1;
			}
		}

	}

	public boolean hasFollowers() {
		return this.schoolSize > 1;
	}

	public boolean inRangeOfLeader() {
		return this.distanceToSqr(this.leader) <= 121.0D;
	}

	public void pathToLeader() {
		if (this.isFollower()) {
			this.getNavigation().moveTo(this.leader, 1.0D);
		}

	}

	public void addFollowers(Stream<? extends AbstractSchoolingFish> p_27534_) {
		p_27534_.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((p_27538_) -> {
			return p_27538_ != this;
		}).forEach((p_27536_) -> {
			p_27536_.startFollowing(this);
		});
	}

	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_27528_, DifficultyInstance p_27529_, MobSpawnType p_27530_, @Nullable SpawnGroupData p_27531_, @Nullable CompoundTag p_27532_) {
		super.finalizeSpawn(p_27528_, p_27529_, p_27530_, p_27531_, p_27532_);
		if (p_27531_ == null) {
			p_27531_ = new AbstractSchoolingFish.SchoolSpawnGroupData(this);
		} else {
			this.startFollowing(((AbstractSchoolingFish.SchoolSpawnGroupData)p_27531_).leader);
		}

		return p_27531_;
	}

	public static class SchoolSpawnGroupData implements SpawnGroupData {
		public final AbstractSchoolingFish leader;

		public SchoolSpawnGroupData(AbstractSchoolingFish p_27553_) {
			this.leader = p_27553_;
		}
	}
}

