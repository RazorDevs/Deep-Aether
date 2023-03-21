package teamrazor.deepaether.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.init.DAEntities;

@SuppressWarnings({"unchecked", "SameReturnValue"})
@Mod.EventBusSubscriber
public class QuailEntity extends Chicken implements GeoEntity {

    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation FLAP_ANIM = RawAnimation.begin().thenPlay("animation.quail.flap");
    private static final RawAnimation WALK_ANIM = RawAnimation.begin().thenPlay("animation.quail.walk");
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenPlay("animation.quail.idle");

    public QuailEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(DAEntities.QUAIL.get(), world);
    }

    public QuailEntity(EntityType<QuailEntity> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    // EVENTUAL GOALS HERE

    public static void init() {
        SpawnPlacements.register(DAEntities.QUAIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos,
                 random) -> (world.getBlockState(pos.above()).is(Blocks.AIR)));
    }

    @Override
    public Chicken getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return DAEntities.QUAIL.get().create(serverLevel);
    }

    private PlayState predicate(AnimationState animationState) {

        if(animationState.isMoving()) {
            animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.quail.walk"));
            return PlayState.CONTINUE;
        }

        animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.quail.idle"));
        return PlayState.CONTINUE;

    }

    private PlayState flap(AnimationState animationState) {
        if(!this.onGround) {
            animationState.getController().setAnimation(RawAnimation.begin().thenPlayXTimes("animation.quail.flap_start", 1).then("animation.quail.flap", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        } else {
            animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.quail.idle"));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",
                0, this::predicate));
        controllers.add(new AnimationController(this, "flap_controller",
                0, this::flap));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }
}