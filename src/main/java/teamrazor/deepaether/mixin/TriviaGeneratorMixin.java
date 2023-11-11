package teamrazor.deepaether.mixin;

import com.aetherteam.aether.api.AetherMoaTypes;
import com.aetherteam.aether.api.registers.MoaType;
import com.aetherteam.aether.client.TriviaGenerator;
import com.aetherteam.aether.entity.WingedBird;
import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.aether.entity.passive.MountableAnimal;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.entity.MoaBonusJump;

import javax.annotation.Nullable;

@Mixin(value = TriviaGenerator.class, remap = false)
public abstract class TriviaGeneratorMixin {


    @Shadow @Nullable protected abstract Component getTriviaComponent();

    @Inject(at = @At("HEAD"), method = "getTriviaLine", cancellable = true, remap = false)
    private void getTriviaLine(CallbackInfoReturnable<Component> cir) {
        if(this.getTriviaComponent() != null) {
            if (this.getTriviaComponent().getString().contains("§b")) {
                cir.setReturnValue(Component.translatable("gui.deep_aether.pro_tip").append(Component.literal(" ").append(this.getTriviaComponent())));
            }
        }
    }
}
