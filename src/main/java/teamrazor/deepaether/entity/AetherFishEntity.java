
package teamrazor.deepaether.entity;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import teamrazor.deepaether.init.DeepAetherModEntities;

import java.util.Set;

@Mod.EventBusSubscriber
public class AetherFishEntity extends Cod {
	private static final Set<ResourceLocation> SPAWN_BIOMES = Set.of(new ResourceLocation("aether:skyroot_grove"),
			new ResourceLocation("aether:skyroot_forest"), new ResourceLocation("aether:skyroot_thicket"),
			new ResourceLocation("aether:golden_forest"));

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
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 1);
		builder = builder.add(Attributes.MAX_HEALTH, 3);
		builder = builder.add(ForgeMod.SWIM_SPEED.get(), 1.2);
		return builder;
	}

	public static void init() {
		SpawnPlacements.register(DeepAetherModEntities.AETHER_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos,
						random) -> (world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER)));
	}

}

