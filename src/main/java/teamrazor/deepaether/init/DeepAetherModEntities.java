/*

package teamrazor.deepaether.init;

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
import teamrazor.deepaether.entity.QuailEntity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepAetherModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, DeepAetherMod.MODID);

	public static final RegistryObject<EntityType<AetherFishEntity>> AETHER_FISH = register("aether_fish",
			EntityType.Builder.<AetherFishEntity>of(AetherFishEntity::new, MobCategory.WATER_CREATURE).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(AetherFishEntity::new)
					.sized(0.5f, 0.5f));

	public static final RegistryObject<EntityType<QuailEntity>> QUAIL = register("quail",
			EntityType.Builder.<QuailEntity>of(QuailEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(QuailEntity::new)
					.sized(0.5f, 0.3f));

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
*/