package io.github.razordevs.deep_aether.item.gear.stratus;

import com.aetherteam.aether.item.tools.abilities.GravititeTool;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class StratusPickaxeItem extends PickaxeItem implements GravititeTool {
    public StratusPickaxeItem(Tier p_42961_, Properties p_42964_) {
        super(p_42961_, p_42964_);
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!this.floatBlock(context)) {
            return super.useOn(context);
        } else {
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
    }
}



