package teamrazor.deepaether.entity.boss;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.client.particle.AetherParticleTypes;
import com.aetherteam.aether.entity.monster.AbstractWhirlwind;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.WindCharge;
import teamrazor.deepaether.init.DAEntities;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;


public class EOTSTornado extends AbstractWhirlwind {

    EOTSEntity eots;

    public EOTSTornado(EntityType<EOTSTornado> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    public void setEots(EOTSEntity eots) {
        this.eots = eots;
    }

    public EOTSTornado(Level level, Vec3 position) {
        this(DAEntities.EOTS_TORNADO.get(), level);
        this.setPos(position.add(0, 1,0));
    }


    @Nonnull
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ShootAirBall(this));
        this.goalSelector.addGoal(2, new MoveGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
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
    public void spawnParticles() {
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
        return 2129985;
    }


    @Override
    public void setColorData(int color) {
        this.entityData.set(DATA_COLOR_ID, 2129985);
    }

    public static class ShootAirBall extends Goal {
        private final EOTSTornado tornado;
        private int shootInterval;

        public ShootAirBall(EOTSTornado tornado) {
            this.tornado = tornado;
            this.shootInterval = 10 + tornado.random.nextInt(50);
        }

        @Override
        public boolean canUse() {
            return --this.shootInterval <= 0;
        }

        @Override
        public void start() {
            AbstractCrystal crystal;
            crystal = new WindCharge(this.tornado.level, this.tornado, WindCharge.AttackPatterns.FOUR);
            //crystal.setDeltaMovement(0, 0.05, 0);
            this.tornado.level.addFreshEntity(crystal);
            this.shootInterval = 10 + tornado.random.nextInt(50);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}