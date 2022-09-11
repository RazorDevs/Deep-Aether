package teamrazor.deepaether.tags;

import com.gildedgames.aether.item.tools.abilities.GravititeTool;
import com.gildedgames.aether.item.tools.abilities.HolystoneTool;
import com.gildedgames.aether.item.tools.abilities.ZaniteTool;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.gildedgames.aether.event.hooks.AbilityHooks;

@Mod.EventBusSubscriber
public class ToolAbilityListener {

    @SubscribeEvent
    public static void doSkyjadeAbility(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        BlockState blockState = event.getState();
        ItemStack itemStack = player.getMainHandItem();
        Level level = player.getLevel();
        event.setNewSpeed(SkyjadeTool.decreaseSpeed(itemStack, event.getNewSpeed()));
        event.setNewSpeed(AbilityHooks.ToolHooks.reduceToolEffectiveness(level, blockState, itemStack, event.getNewSpeed()));
    }
}