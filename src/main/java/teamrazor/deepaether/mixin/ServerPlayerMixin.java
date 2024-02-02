package teamrazor.deepaether.mixin;


import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamrazor.deepaether.entity.IPlayerBossFight;

/**
 * Used in the flawless boss check
 * See {@link teamrazor.deepaether.event.DAGeneralEvents}
 */
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player implements IPlayerBossFight {

    public ServerPlayerMixin(Level level, BlockPos blockPos, float v, GameProfile gameProfile, ProfilePublicKey profilePublicKey) {
        super(level, blockPos, v, gameProfile, profilePublicKey);
    }
    @Inject(at = @At(("TAIL")), method = "addAdditionalSaveData")
    private void addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        tag.putBoolean("deep_Aether$hasBeenHurt", this.deep_Aether$getHasBeenHurt());
    }

    @Inject(at = @At(("TAIL")), method = "readAdditionalSaveData")
    private void readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        this.deep_Aether$setHasBeenHurt(tag.getBoolean("deep_Aether$hasBeenHurt"));
    }


    @Unique
    boolean deep_Aether$hasBeenHurt = false;

    @Override
    public boolean deep_Aether$getHasBeenHurt() {
        return deep_Aether$hasBeenHurt;
    }

    @Override
    public void deep_Aether$setHasBeenHurt(boolean bool) {
        this.deep_Aether$hasBeenHurt = bool;
    }
}
