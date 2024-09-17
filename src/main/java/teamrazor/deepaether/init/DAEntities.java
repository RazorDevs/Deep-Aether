package teamrazor.deepaether.init;

import com.aetherteam.aether.entity.passive.AetherAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.DABoatEntity;
import teamrazor.deepaether.entity.DAChestBoatEntity;
import teamrazor.deepaether.entity.StormArrow;
import teamrazor.deepaether.entity.living.AerglowFish;
import teamrazor.deepaether.entity.living.Venomite;
import teamrazor.deepaether.entity.living.Windfly;
import teamrazor.deepaether.entity.living.boss.eots.EOTSController;
import teamrazor.deepaether.entity.living.boss.eots.EOTSSegment;
import teamrazor.deepaether.entity.living.projectile.FireProjectile;
import teamrazor.deepaether.entity.living.projectile.ThrownQuailEgg;
import teamrazor.deepaether.entity.living.projectile.VenomiteBubble;
import teamrazor.deepaether.entity.living.projectile.WindCrystal;
import teamrazor.deepaether.entity.living.quail.Quail;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, DeepAether.MODID);

	public static final DeferredHolder<EntityType<?>, EntityType<DABoatEntity>> BOAT = ENTITY_TYPES.register("boat",
			() -> EntityType.Builder.<DABoatEntity>of(DABoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));

	public static final DeferredHolder<EntityType<?>, EntityType<DAChestBoatEntity>> CHEST_BOAT = ENTITY_TYPES.register("chest_boat",
			() -> EntityType.Builder.<DAChestBoatEntity>of(DAChestBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("chest_boat"));

	public static final DeferredHolder<EntityType<?>,EntityType<ThrownQuailEgg>> QUAIL_EGG = ENTITY_TYPES.register("quail_egg",
			() -> EntityType.Builder.<ThrownQuailEgg>of(ThrownQuailEgg::new, MobCategory.MISC)
					.sized(0.25F, 0.25F)
					.clientTrackingRange(4)
					.updateInterval(10)
					.build("quail_egg"));

	public static final DeferredHolder<EntityType<?>,EntityType<AerglowFish>> AETHER_FISH = register("aerglow_fish",
			EntityType.Builder.of(AerglowFish::new, MobCategory.WATER_CREATURE)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3)
					.clientTrackingRange(10)
					.sized(0.5f, 0.5f));

	public static final DeferredHolder<EntityType<?>,EntityType<Quail>> QUAIL = register("quail",
			EntityType.Builder.of(Quail::new, MobCategory.CREATURE)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3)
					.clientTrackingRange(10)
					.sized(0.35F, 0.7f));

	public static final DeferredHolder<EntityType<?>,EntityType<Venomite>> VENOMITE = register("venomite",
			Venomite::new, 0.7F, 0.6F);

	public static final DeferredHolder<EntityType<?>,EntityType<Windfly>> WINDFLY = register("windfly",
			Windfly::new, 1.0F, 0.3F);



	public static final DeferredHolder<EntityType<?>,EntityType<EOTSController>> EOTS_CONTROLLER = register("eots_controller",
			EOTSController::new, 3F, 3F);

	public static final DeferredHolder<EntityType<?>,EntityType<EOTSSegment>> EOTS_SEGMENT = register("eots_segment",
			EOTSSegment::new, 1F, 1F);

	public static final DeferredHolder<EntityType<?>, EntityType<WindCrystal>> WIND_CRYSTAL = ENTITY_TYPES.register("wind_crystal",
			() -> EntityType.Builder.<WindCrystal>of(WindCrystal::new, MobCategory.MISC).sized(0.85F, 0.85F)
					.clientTrackingRange(4).updateInterval(10).fireImmune().build("wind_crystal"));

	public static final DeferredHolder<EntityType<?>,EntityType<FireProjectile>> FIRE_PROJECTILE = ENTITY_TYPES.register("fire_projectile",
			() -> EntityType.Builder.<FireProjectile>of(FireProjectile::new, MobCategory.MISC).sized(0.35F, 0.35F).clientTrackingRange(4).updateInterval(10).build("fire_projectile"));

	public static final DeferredHolder<EntityType<?>,EntityType<StormArrow>> STORM_ARROW = ENTITY_TYPES.register("storm_arrow", () ->
			EntityType.Builder.<StormArrow>of(StormArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("storm_arrow"));

	public static final DeferredHolder<EntityType<?>,EntityType<VenomiteBubble>> VENOMITE_BUBBLE = ENTITY_TYPES.register("venomite_bubble",
			() -> EntityType.Builder.<VenomiteBubble>of(VenomiteBubble::new, MobCategory.MISC).sized(0.35F, 0.2F).clientTrackingRange(4).updateInterval(10).build("venomite_bubble"));

	private static <T extends Entity> DeferredHolder<EntityType<?>,EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return ENTITY_TYPES.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, EntityType.EntityFactory<T> entity, float width, float height) {
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(entity, MobCategory.CREATURE).sized(width, height).build(name));
	}

	@SubscribeEvent
	public static void spawnPlacementRegisterEvent(SpawnPlacementRegisterEvent event) {
		event.register(DAEntities.QUAIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(DAEntities.WINDFLY.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				DAEntities::checkWindFly, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(DAEntities.VENOMITE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AetherAnimal::checkAetherAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
	}

	public static boolean checkWindFly(EntityType<Windfly> animal, LevelAccessor level, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
		return level.isEmptyBlock(pos.above());
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(AETHER_FISH.get(), AerglowFish.createAttributes().build());
		event.put(QUAIL.get(), Quail.createAttributes().build());
		event.put(VENOMITE.get(), Venomite.createAttributes().build());
		event.put(EOTS_SEGMENT.get(), EOTSSegment.createMobAttributes().build());
		event.put(EOTS_CONTROLLER.get(), EOTSController.createMobAttributes().build());
		event.put(WINDFLY.get(), Windfly.createAttributes().build());
	}
}