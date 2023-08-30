package teamrazor.deepaether.init;


import com.aetherteam.aether.data.resources.AetherMobCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.*;
import teamrazor.deepaether.entity.boss.EOTSEntity;
import teamrazor.deepaether.entity.quail.Quail;
import teamrazor.deepaether.entity.quail.ThrownQuailEgg;
import teamrazor.deepaether.entity.swet.AercloudSwet;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DeepAetherMod.MODID);

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

	public static final RegistryObject<EntityType<AerglowFish>> AETHER_FISH = register("aerglow_fish",
			EntityType.Builder.of(AerglowFish::new, MobCategory.WATER_CREATURE)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3)
					.clientTrackingRange(10)
					.sized(0.5f, 0.5f));

	public static final RegistryObject<EntityType<Quail>> QUAIL = register("quail",
			EntityType.Builder.<Quail>of(Quail::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Quail::new)
					.sized(0.35F, 0.7f));

	public static final RegistryObject<EntityType<AercloudSwet>> AERCLOUD_SWET = ENTITY_TYPES.register("aercloud_swet",
			() -> EntityType.Builder.of(AercloudSwet::new, AetherMobCategory.AETHER_SURFACE_MONSTER)
					.sized(0.9F, 0.95F)
					.clientTrackingRange(10)
					.build("aercloud_swet"));


	public static final RegistryObject<EntityType<Steer>> STEER = register("steer",
			EntityType.Builder.<Steer>of(Steer::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Steer::new));

	//BOSSES
	public static final RegistryObject<EntityType<EOTSEntity>> EOTS = register("eots",
			EntityType.Builder.<EOTSEntity>of(EOTSEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(EOTSEntity::new));

	//MISC

	public static final RegistryObject<EntityType<WindCharge>> WIND_CHARGE = ENTITY_TYPES.register("wind_charge",
			() -> {
				return EntityType.Builder.<WindCharge>of(WindCharge::new, MobCategory.MISC).sized(0.85F, 0.85F)
						.clientTrackingRange(4).updateInterval(10).fireImmune().build("wind_charge");
			});


	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return ENTITY_TYPES.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			AerglowFish.createAttributes();
			Quail.init();
			Steer.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(AETHER_FISH.get(), AerglowFish.createAttributes().build());
		event.put(QUAIL.get(), Quail.createAttributes().build());
		event.put(STEER.get(), Steer.createAttributes().build());
		event.put(AERCLOUD_SWET.get(), AercloudSwet.createMobAttributes().build());
		event.put(EOTS.get(), EOTSEntity.createAttributes().build());
	}

	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(DAEntities.AERCLOUD_SWET.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AercloudSwet::checkSwetSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
	}
}