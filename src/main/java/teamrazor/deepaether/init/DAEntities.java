package teamrazor.deepaether.init;


import teamrazor.deepaether.DeepAetherMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import teamrazor.deepaether.entity.AetherFishEntity;
import teamrazor.deepaether.entity.QuailEntity;
import teamrazor.deepaether.entity.boats.*;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DeepAetherMod.MODID);


    public static final RegistryObject<EntityType<RoserootBoat>> ROSEROOT_BOAT = ENTITY_TYPES.register("roseroot_boat",
            () -> EntityType.Builder.<RoserootBoat>of(RoserootBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("roseroot_boat"));

    public static final RegistryObject<EntityType<RoserootChestBoat>> ROSEROOT_CHEST_BOAT = ENTITY_TYPES.register("roseroot_chest_boat",
            () -> EntityType.Builder.<RoserootChestBoat>of(RoserootChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("roseroot_boat"));

    public static final RegistryObject<EntityType<YagrootBoat>> YAGROOT_BOAT = ENTITY_TYPES.register("yagroot_boat",
            () -> EntityType.Builder.<YagrootBoat>of(YagrootBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("yagroot_boat"));

    public static final RegistryObject<EntityType<YagrootChestBoat>> YAGROOT_CHEST_BOAT = ENTITY_TYPES.register("yagroot_chest_boat",
            () -> EntityType.Builder.<YagrootChestBoat>of(YagrootChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("yagroot_boat"));

    public static final RegistryObject<EntityType<CruderootBoat>> CRUDEROOT_BOAT = ENTITY_TYPES.register("cruderoot_boat",
            () -> EntityType.Builder.<CruderootBoat>of(CruderootBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("cruderoot_boat"));

    public static final RegistryObject<EntityType<CruderootChestBoat>> CRUDEROOT_CHEST_BOAT = ENTITY_TYPES.register("cruderoot_chest_boat",
            () -> EntityType.Builder.<CruderootChestBoat>of(CruderootChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("cruderoot_boat"));



	/*public static final RegistryObject<EntityType<AetherFishEntity>> AETHER_FISH = register("aether_fish",
			() -> EntityType.Builder.<AetherFishEntity>of(AetherFishEntity::new, MobCategory.MISC)
					.sized(0.5F, 0.5F).clientTrackingRange(10));**/
	public static final RegistryObject<EntityType<AetherFishEntity>> AETHER_FISH = register("aether_fish",
			EntityType.Builder.<AetherFishEntity>of(AetherFishEntity::new, MobCategory.WATER_CREATURE)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3)
					.clientTrackingRange(10)
					.sized(0.5f, 0.5f));

	public static final RegistryObject<EntityType<QuailEntity>> QUAIL = register("quail",
			EntityType.Builder.<QuailEntity>of(QuailEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(QuailEntity::new)
					.sized(0.3f, 0.5f));



	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return ENTITY_TYPES.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			AetherFishEntity.createAttributes();
			QuailEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(AETHER_FISH.get(), AetherFishEntity.createAttributes().build());
		event.put(QUAIL.get(), QuailEntity.createAttributes().build());
	}
}