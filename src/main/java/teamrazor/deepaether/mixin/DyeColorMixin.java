package teamrazor.deepaether.mixin;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamrazor.deepaether.recipe.FloatyScarfColoring;

@Mixin(DyeColor.class)
public class DyeColorMixin {

    public DyeColorMixin() {
    }

    @Inject(at = @At("TAIL"), method = "<init>")
    private void DyeColor(String name, int i, int id, String p_41047_, int diffuseColor, MapColor p_285297_, int p_41050_, int p_41051_, CallbackInfo ci) {

        FloatyScarfColoring.TEXTURE_DIFFUSE_COLOR.put(id, diffuseColor);
    }
}
