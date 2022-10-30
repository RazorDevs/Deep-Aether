package teamrazor.deepaether.init;

import net.minecraft.resources.ResourceLocation;
import org.apache.http.client.entity.EntityBuilder;
import teamrazor.deepaether.entity.AetherFishEntity;
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

import teamrazor.deepaether.entity.DeepAetherModBoat;
import teamrazor.deepaether.entity.DeepAetherModChestBoat;
import teamrazor.deepaether.entity.QuailEntity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepAetherModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DeepAetherMod.MODID);

	public static final RegistryObject<EntityType<AetherFishEntity>> AETHER_FISH = register("aether_fish",
			EntityType.Builder.<AetherFishEntity>of(AetherFishEntity::new, MobCategory.WATER_CREATURE).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(AetherFishEntity::new)
					.sized(0.5f, 0.5f));

	public static final RegistryObject<EntityType<QuailEntity>> QUAIL = register("quail",
			EntityType.Builder.<QuailEntity>of(QuailEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(QuailEntity::new)
					.sized(0.3f, 0.5f));


	public static final RegistryObject<EntityType<DeepAetherModBoat>> BOAT = REGISTRY.register("boat", () -> EntityType.Builder.<DeepAetherModBoat>of(DeepAetherModBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(new ResourceLocation(DeepAetherMod.MODID, "boat").toString()));

	public static final RegistryObject<EntityType<DeepAetherModChestBoat>> CHEST_BOAT = REGISTRY.register("chest_boat", () -> EntityType.Builder.<DeepAetherModChestBoat>of(DeepAetherModChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(new ResourceLocation(DeepAetherMod.MODID, "chest_boat").toString()));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			AetherFishEntity.init();
			QuailEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(AETHER_FISH.get(), AetherFishEntity.createAttributes().build());
		event.put(QUAIL.get(), QuailEntity.createAttributes().build());
	}
}