package io.github.razordevs.deep_aether.item.gear;

import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.ToolAction;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber
public class ToolAbilityListener {

    @SubscribeEvent
    public static void setupToolModifications(BlockEvent.BlockToolModificationEvent event) {
        BlockState oldState = event.getState();
        ToolAction toolAction = event.getToolAction();
        BlockState newState = DAAbilityHooks.ToolHooks.setupToolActions(oldState, toolAction);
        if (newState != oldState && !event.isSimulated()) {
            event.setFinalState(newState);
        }
    }
}