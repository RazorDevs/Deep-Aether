package teamrazor.deepaether.entity.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class FollowPlayerGoal extends Goal {
    private static final TargetingConditions TEMP_TARGETING = TargetingConditions.forNonCombat().range(10.0D).ignoreLineOfSight();
    private final TargetingConditions targetingConditions;
    protected final PathfinderMob mob;
    private final double speedModifier;
    private double heightDifference;
    @Nullable
    protected Player player;
    private int calmDown;
    private boolean isRunning;

    public FollowPlayerGoal(PathfinderMob mob, double speedMod, double heightDifference) {
        this.mob = mob;
        this.speedModifier = speedMod;
        this.heightDifference = heightDifference;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.targetingConditions = TEMP_TARGETING.copy().selector(this::shouldFollow);
    }

    public boolean canUse() {
        if (this.calmDown > 0) {
            --this.calmDown;
            return false;
        } else {
            this.player = this.mob.level().getNearestPlayer(targetingConditions, this.mob);
            return this.player != null && !this.player.isCreative() && !this.player.isInWater();
        }
    }

    private boolean shouldFollow(LivingEntity livingEntity) {
        return true;
    }

    public boolean canContinueToUse() {
        return this.canUse();
    }

    public void start() {
        this.isRunning = true;
        this.mob.setNoGravity(true);
    }

    public void stop() {
        this.player = null;
        this.mob.getNavigation().stop();
        this.calmDown = reducedTickDelay(100);
        this.isRunning = false;
    }

    public void tick() {
        this.mob.getLookControl().setLookAt(this.player, (float)(this.mob.getMaxHeadYRot() + 20), (float)this.mob.getMaxHeadXRot());
        if (this.mob.distanceToSqr(this.player) < 6.25D) {
            this.mob.getNavigation().moveTo(this.mob.getX(), this.player.getY() + heightDifference, this.mob.getZ(), this.speedModifier);
        } else {
            this.mob.getNavigation().moveTo(this.player.getX(), this.player.getY() + heightDifference, this.player.getZ(), this.speedModifier);
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}