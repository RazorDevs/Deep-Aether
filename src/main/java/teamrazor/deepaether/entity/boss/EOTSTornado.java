package teamrazor.deepaether.entity.boss;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.api.BossNameGenerator;
import com.aetherteam.aether.api.BossRoomTracker;
import com.aetherteam.aether.client.particle.AetherParticleTypes;
import com.aetherteam.aether.entity.BossMob;
import com.aetherteam.aether.entity.ai.controller.BlankMoveControl;
import com.aetherteam.aether.entity.monster.AbstractWhirlwind;
import com.aetherteam.aether.entity.monster.dungeon.boss.slider.Slider;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import com.aetherteam.aether.loot.AetherLoot;
import com.aetherteam.aether.network.AetherPacketHandler;
import com.aetherteam.aether.network.packet.client.BossInfoPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.effect.PullEffect;
import teamrazor.deepaether.entity.WindCharge;
import teamrazor.deepaether.init.DAEffects;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DASounds;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings({"unchecked", "SameReturnValue"})
@Mod.EventBusSubscriber
public class EOTSTornado extends AbstractWhirlwind {

    EOTSEntity eots;
    public EOTSTornado(EntityType<? extends EOTSTornado> type, Level level) {
        super(type, level);
    }

    public EOTSTornado(Level level, EOTSEntity eots) {
        this(DAEntities.EOTS_TORNADO.get(), level);
        this.setPos(eots.getX(), eots.getY(), eots.getZ());
        this.eots = eots;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ShootAirBall(this));
        this.goalSelector.addGoal(2, new MoveGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, false));
    }

    @Override
    public void tick() {
        super.tick();
        --this.lifeLeft;
        if (!this.level.isClientSide && (this.lifeLeft <= 0 || this.isInFluidType())) {
            this.discard();
        }

        if(!eots.isAlive() || eots == null) {
            this.discard();
        }

    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() instanceof DyeItem dyeItem && player.isCreative()) {
            this.setColorData(dyeItem.getDyeColor().getMaterialColor().col);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void updateParticles() {
        for (int i = 0; i < 2; i++) {
            double d1 = this.getX() + this.getRandom().nextDouble() * 0.25;
            double d4 = getY() + getBbHeight() + 0.125;
            double d7 = this.getZ() + this.getRandom().nextDouble() * 0.25;
            float f = this.getRandom().nextFloat() * 360;
            this.getLevel().addParticle(AetherParticleTypes.PASSIVE_WHIRLWIND.get(), d1, d4 - 0.25, d7, -Math.sin(0.0175F * f) * 0.75, 0.125, Math.cos(0.0175F * f) * 0.75);
        }
    }

    @Override
    public ResourceLocation getLootLocation() {
        return null;
    }

    @Override
    public int getDefaultColor() {
        return 16777215;
    }

    @Override
    public void aiStep() {
        if (!this.level.isClientSide) {
            if (this.verticalCollision && !this.verticalCollisionBelow) {
                this.stuckTick += 4;
            } else if (this.stuckTick > 0) {
                --this.stuckTick;
            }

            if (this.getTarget() != null) {
                ++this.actionTimer;
            }

        } else {
            this.updateParticles();
        }

        super.aiStep();
        List<Entity> entityList = this.level.getEntities(this, this.getBoundingBox().expandTowards(2.5, 2.5, 2.5)).stream().filter((entityx) -> {
            return !entityx.getType().is(AetherTags.Entities.WHIRLWIND_UNAFFECTED);
        }).toList();
        this.isPullingEntity = !entityList.isEmpty();
        Iterator var2 = entityList.iterator();

        while(var2.hasNext()) {
            Entity entity = (Entity)var2.next();
            double x = (float)entity.getX();
            double y = (double)((float)entity.getY()) - entity.getMyRidingOffset() * 0.6000000238418579;
            double z = (float)entity.getZ();
            double distance = this.distanceTo(entity);
            double d1 = y - this.getY();
            double d2;
            if (distance <= 1.5 + d1) {
                entity.setDeltaMovement(entity.getDeltaMovement().x, 0.15000000596046448, entity.getDeltaMovement().z);
                entity.resetFallDistance();
                if (d1 > 1.5) {
                    entity.setDeltaMovement(entity.getDeltaMovement().x, -0.44999998807907104 + d1 * 0.3499999940395355, entity.getDeltaMovement().z);
                    distance += d1 * 1.5;
                } else {
                    entity.setDeltaMovement(entity.getDeltaMovement().x, 0.125, entity.getDeltaMovement().z);
                }

                d2 = Math.atan2(this.getX() - x, this.getZ() - z) / 0.01745329424738884;
                d2 += 160.0;
                entity.setDeltaMovement(-Math.cos(0.01745329424738884 * d2) * (distance + 0.25) * 0.10000000149011612, entity.getDeltaMovement().y, Math.sin(0.01745329424738884 * d2) * (distance + 0.25) * 0.10000000149011612);
                if (entity instanceof AbstractWhirlwind) {
                    entity.discard();
                }
            } else {
                d2 = Math.atan2(this.getX() - x, this.getZ() - z) / 0.01745329424738884;
                entity.setDeltaMovement(entity.getDeltaMovement().add(Math.sin(0.01745329424738884 * d2) * 0.009999999776482582, entity.getDeltaMovement().y, Math.cos(0.01745329424738884 * d2) * 0.009999999776482582));
            }

            if (!this.level.isEmptyBlock(this.blockPosition())) {
                this.lifeLeft -= 50;
            }
        }

        if (this.stuckTick > 40) {
            this.lifeLeft = 0;
        }

    }
    public static class ShootAirBall extends Goal {
        private final EOTSTornado tornado;
        private int shootInterval;

        public ShootAirBall(EOTSTornado tornado) {
            this.tornado = tornado;
            this.shootInterval = 200 + tornado.random.nextInt(50);
        }

        @Override
        public boolean canUse() {
            return this.tornado.eots.isBossFight() && --this.shootInterval <= 0;
        }

        @Override
        public void start() {
            AbstractCrystal crystal;
            crystal = new WindCharge(this.tornado.level, this.tornado);
            //crystal.setDeltaMovement(0, 0.05, 0);
            this.tornado.level.addFreshEntity(crystal);
            this.shootInterval = 200 + tornado.random.nextInt(50);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}