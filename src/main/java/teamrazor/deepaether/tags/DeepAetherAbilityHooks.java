package teamrazor.deepaether.tags;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.item.gear.cloudium.CloudiumAbility;

import java.util.Map;

public class DeepAetherAbilityHooks {
    public static class AccessoryHooks {
    }
    public static class ArmorHooks {
        public static boolean fallCancellation(LivingEntity livingEntity) {
            return CloudiumAbility.hasFullCloudiumSet(livingEntity);
        }
    }


    public static class ToolHooks {
        public static final Map<Block, Block> STRIPPABLES = (new ImmutableMap.Builder<Block, Block>())
                .put(DABlocks.ROSEROOT_LOG.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get())
                .put(DABlocks.ROSEROOT_WOOD.get(), DABlocks.STRIPPED_ROSEROOT_WOOD.get())
                .put(DABlocks.YAGROOT_LOG.get(), DABlocks.STRIPPED_YAGROOT_LOG.get())
                .put(DABlocks.YAGROOT_WOOD.get(), DABlocks.STRIPPED_YAGROOT_WOOD.get())
                .build();

        public static BlockState setupToolActions(LevelAccessor accessor, BlockPos pos, BlockState old, ToolAction action) {
            Block oldBlock = old.getBlock();
            if (action == ToolActions.AXE_STRIP) {
                if (STRIPPABLES.containsKey(oldBlock)) {
                    return STRIPPABLES.get(oldBlock).withPropertiesOf(old);
                }
            }
            return old;
        }

        public static float handleSkyjadeToolAbility(ItemStack stack, float speed) {
            if (stack.getItem() instanceof SkyjadeTool skyjadeTool) {
                return skyjadeTool.decreaseSpeed(stack, speed);
            }
            return speed;
        }
    }
}
