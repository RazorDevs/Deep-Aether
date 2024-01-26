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
import teamrazor.deepaether.DeepAetherConfig;
import teamrazor.deepaether.advancement.DAAdvancementTriggers;
import teamrazor.deepaether.entity.IPlayerBossFight;
import teamrazor.deepaether.init.DAItems;

import javax.annotation.Nullable;

@Mixin(value = ValkyrieQueen.class)
public abstract class ValkyrieQueenMixin implements AetherBossMob<ValkyrieQueen> {
    @Inject(at = @At("TAIL"), method = "onDungeonPlayerAdded", remap = false)
    private void onDungeonPlayerAdded(Player player, CallbackInfo ci) {
        if (player instanceof ServerPlayer serverPlayer) {
            ((IPlayerBossFight) serverPlayer).deep_Aether$setHasBeenHurt(false);
        }
    }
}
