package io.github.razordevs.deepaether.deepaether.item.gear.stratus;

import com.aetherteam.aether.item.tools.abilities.GravititeTool;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class StratusShovelItem extends ShovelItem implements GravititeTool {
    public StratusShovelItem(Tier p_43114_, float p_43115_, float p_43116_, Properties p_43117_) {
        super(p_43114_, p_43115_, p_43116_, p_43117_);
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



