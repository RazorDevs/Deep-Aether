package teamrazor.deepaether.init;


import com.aetherteam.aether.data.resources.AetherMobCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.*;
import teamrazor.deepaether.entity.eots.EOTSController;
import teamrazor.deepaether.entity.eots.EOTSSegment;
import teamrazor.deepaether.entity.quail.Quail;
import teamrazor.deepaether.entity.quail.ThrownQuailEgg;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DeepAether.MODID);

	public static final RegistryObject<EntityType<DABoatEntity>> BOAT = ENTITY_TYPES.register("boat",
			() -> EntityType.Builder.<DABoatEntity>of(DABoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));

	public static final RegistryObject<EntityType<DAChestBoatEntity>> CHEST_BOAT = ENTITY_TYPES.register("chest_boat",
			() -> EntityType.Builder.<DAChestBoatEntity>of(DAChestBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("chest_boat"));

	public static final RegistryObject<EntityType<ThrownQuailEgg>> QUAIL_EGG = ENTITY_TYPES.register("quail_egg",
			() -> EntityType.Builder.<ThrownQuailEgg>of(ThrownQuailEgg::new, MobCategory.MISC)
					.sized(0.25F, 0.25F)
					.clientTrackingRange(4)
					.updateInterval(10)
					.build("quail_egg"));

	public static final RegistryObject<EntityType<AerglowFish>> AERGLOW_FISH = register("aerglow_fish",
			EntityType.Builder.of(AerglowFish::new, MobCategory.WATER_CREATURE)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3)
					.clientTrackingRange(10)
					.sized(0.5f, 0.5f));

	public static final RegistryObject<EntityType<Quail>> QUAIL = register("quail",
			EntityType.Builder.of(Quail::new, MobCategory.CREATURE)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3)
					.clientTrackingRange(10)
					.sized(0.35F, 0.7f));

	public static final RegistryObject<EntityType<Venomite>> VENOMITE = register("venomite",
			Venomite::new, 0.7F, 0.6F);

	public static final RegistryObject<EntityType<Windfly>> WINDFLY = register("windfly",
			Windfly::new, 1.0F, 0.3F);

	public static final RegistryObject<EntityType<EOTSController>> EOTS_CONTROLLER = register("eots_controller",
			EOTSController::new, 3F, 3F);

	public static final RegistryObject<EntityType<EOTSSegment>> EOTS_SEGMENT = register("eots_segment",
			EOTSSegment::new, 1F, 1F);

	public static final RegistryObject<EntityType<WindCrystal>> WIND_CRYSTAL = ENTITY_TYPES.register("wind_crystal",
			() -> EntityType.Builder.<WindCrystal>of(WindCrystal::new, MobCategory.MISC).sized(0.85F, 0.85F)
					.clientTrackingRange(4).updateInterval(10).fireImmune().build("wind_crystal"));


	public static final RegistryObject<EntityType<FireProjectile>> FIRE_PROJECTILE = ENTITY_TYPES.register("fire_projectile",
			() -> EntityType.Builder.<FireProjectile>of(FireProjectile::new, MobCategory.MISC).sized(0.35F, 0.35F).clientTrackingRange(4).updateInterval(10).build("fire_projectile"));

	public static final RegistryObject<EntityType<VenomiteBubble>> VENOMITE_BUBBLE = ENTITY_TYPES.register("venomite_bubble",
			() -> EntityType.Builder.<VenomiteBubble>of(VenomiteBubble::new, MobCategory.MISC).sized(0.35F, 0.2F).clientTrackingRange(4).updateInterval(10).build("venomite_bubble"));

	public static final RegistryObject<EntityType<BabyZephyr>> BABY_ZEPHYR = ENTITY_TYPES.register("baby_zephyr",
			() -> EntityType.Builder.of(BabyZephyr::new,
							AetherMobCategory.AETHER_SKY_MONSTER)
					.sized(1.5F, 1.0F)
					.clientTrackingRange(10).build("baby_zephyr"));

	public static final RegistryObject<EntityType<StormArrow>> STORM_ARROW = ENTITY_TYPES.register("storm_arrow", () ->
			EntityType.Builder.<StormArrow>of(StormArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("storm_arrow"));

	public static final RegistryObject<EntityType<GentleWind>> GENTLE_WIND = register("gentle_wind",
			GentleWind::new, 1F, 0.3F);

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return ENTITY_TYPES.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	private static <T extends Mob> RegistryObject<EntityType<T>> register(String name, EntityType.EntityFactory<T> entity, float width, float height) {
		return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(entity, MobCategory.CREATURE).sized(width, height).build(name));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			Quail.init();
			AerglowFish.init();
			Venomite.init();
			Windfly.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(AERGLOW_FISH.get(), AerglowFish.createAttributes().build());
		event.put(QUAIL.get(), Quail.createAttributes().build());
		event.put(VENOMITE.get(), Venomite.createAttributes().build());
		event.put(EOTS_SEGMENT.get(), EOTSSegment.createMobAttributes().build());
		event.put(EOTS_CONTROLLER.get(), EOTSController.createMobAttributes().build());
		event.put(WINDFLY.get(), Windfly.createAttributes().build());
		event.put(BABY_ZEPHYR.get(), BabyZephyr.createMobAttributes().build());
		event.put(GENTLE_WIND.get(), GentleWind.createMobAttributes().build());
	}
}