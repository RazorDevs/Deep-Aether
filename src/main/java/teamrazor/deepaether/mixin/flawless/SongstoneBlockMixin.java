package teamrazor.deepaether.mixin.flawless;

import com.legacy.lost_aether.block.SongstoneBlock;
import com.legacy.lost_aether.entity.AerwhaleKingEntity;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.entity.IFlawlessBossDrop;

import java.util.List;

@Pseudo
@Mixin(SongstoneBlock.class)
public abstract class SongstoneBlockMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getEntitiesOfClass(Ljava/lang/Class;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;"), method = "use")
    private void use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir, @Local List<AerwhaleKingEntity> aerwhaleList) {
        for (AerwhaleKingEntity aerwhaleKing: aerwhaleList) {
            ((IFlawlessBossDrop) aerwhaleKing).deep_Aether$setHasBeenHurt(false);
        }
    }
}
