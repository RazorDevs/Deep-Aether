package teamrazor.deepaether.mixin.flawless;

import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.NpcDialogue;
import com.aetherteam.aether.entity.monster.dungeon.AbstractValkyrie;
import com.aetherteam.aether.entity.monster.dungeon.boss.ValkyrieQueen;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamrazor.deepaether.advancement.DAAdvancementTriggers;
import teamrazor.deepaether.entity.IFlawlessBossDrop;
import teamrazor.deepaether.entity.IPlayerBossFight;

import javax.annotation.Nullable;

@Mixin(value = ValkyrieQueen.class)
public abstract class ValkyrieQueenMixin extends AbstractValkyrie implements AetherBossMob<ValkyrieQueen>, NpcDialogue, IEntityAdditionalSpawnData, IFlawlessBossDrop{
    @Shadow(remap = false) @Final private ServerBossEvent bossFight;
    @Unique
    @Nullable
    private static final EntityDataAccessor<Boolean> DATA_HAS_BEEN_HIT_ID = SynchedEntityData.defineId(ValkyrieQueen.class, EntityDataSerializers.BOOLEAN);

    public ValkyrieQueenMixin(EntityType<? extends AbstractValkyrie> type, Level level) {
        super(type, level);
    }


    @Inject(at = @At(("TAIL")), method = "setBossFight", remap = false)
    private void start(CallbackInfo ci) {
        deep_Aether$setHasBeenHurt(false);
    }

    @Inject(at = @At(("TAIL")), method = "defineSynchedData")
    private void defineSynchedData(CallbackInfo ci) {
        this.getEntityData().define(DATA_HAS_BEEN_HIT_ID, false);
    }

    @Unique
    @Override
    public boolean deep_Aether$hasBeenHurt() {
        return this.getEntityData().get(DATA_HAS_BEEN_HIT_ID);
    }

    @Unique
    @Override
    public void deep_Aether$setHasBeenHurt(boolean bool) {
        this.getEntityData().set(DATA_HAS_BEEN_HIT_ID, bool);
    }
    @Inject(at = @At("TAIL"), method = "onDungeonPlayerAdded", remap = false)
    private void onDungeonPlayerAdded(Player player, CallbackInfo ci) {
        if (player instanceof ServerPlayer serverPlayer) {
            ((IPlayerBossFight) serverPlayer).deep_Aether$setBoss(this);
        }
    }
    @Inject(at = @At("HEAD"), method = "die")
    private void die(DamageSource source, CallbackInfo ci) {
        if(!deep_Aether$hasBeenHurt() && this.getDungeon() != null)  {
            this.spawnAtLocation(new ItemStack(AetherItems.HOLY_SWORD.get(), 1));

            for (ServerPlayer player: this.bossFight.getPlayers()) {
                DAAdvancementTriggers.FLAWLESS.trigger(player, this, source);
            }
        }
    }
}
