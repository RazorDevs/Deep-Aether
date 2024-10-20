package io.github.razordevs.deep_aether.item.gear.stratus;

import com.aetherteam.aether.item.tools.abilities.GravititeTool;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class StratusAxeItem extends AxeItem implements GravititeTool {
    public StratusAxeItem(Tier tier, Properties properties) {
        super(tier, properties);
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



