package io.github.razordevs.deep_aether.item.gear;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = DeepAether.MODID)
public class ToolAbilityListener {

    @SubscribeEvent
    public static void setupToolModifications(BlockEvent.BlockToolModificationEvent event) {
        BlockState oldState = event.getState();
        ItemAbility toolAction = event.getItemAbility();
        BlockState newState = DAAbilityHooks.ToolHooks.setupToolActions(oldState, toolAction);
        if (newState != oldState && !event.isSimulated()) {
            event.setFinalState(newState);
        }
    }
}