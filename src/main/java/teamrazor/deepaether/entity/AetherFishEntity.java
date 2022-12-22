package teamrazor.deepaether.entity;
/*
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

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import teamrazor.deepaether.init.DeepAetherModEntities;

import java.util.Set;

@Mod.EventBusSubscriber
public class AetherFishEntity extends Cod implements GeoAnimatable {

	private AnimatableInstanceCache factory = new AnimatableInstanceCache(this);

	public AetherFishEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(DeepAetherModEntities.AETHER_FISH.get(), world);
	}

	public AetherFishEntity(EntityType<AetherFishEntity> type, Level world) {
		super(type, world);
	}


	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MOVEMENT_SPEED, 1)
				.add(Attributes.MAX_HEALTH, 3)
				.add(ForgeMod.SWIM_SPEED.get(), 1.2);
	}

	public static void init() {
		SpawnPlacements.register(DeepAetherModEntities.AETHER_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos,
						random) -> (world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER)));
	}

	private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
		if (event.isMoving()) {
			event.getController().setAnimation(new RawAnimation().addAnimation("animation.aerglow_fish.swim", true));
			return PlayState.CONTINUE;
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager data) {
		data.addAnimationController(new AnimationController(this, "controller",
				0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}

}*/
