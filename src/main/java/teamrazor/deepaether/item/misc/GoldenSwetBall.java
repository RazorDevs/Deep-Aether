package teamrazor.deepaether.item.misc;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.init.DABlocks;

public class GoldenSwetBall extends Item {
    public GoldenSwetBall(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack item = context.getItemInHand();
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() == AetherBlocks.AETHER_DIRT.get()) {
            Player player = context.getPlayer();
            if (player != null && !level.isClientSide) {
                level.setBlockAndUpdate(pos, DABlocks.GOLDEN_GRASS_BLOCK.get().defaultBlockState());
                player.awardStat(Stats.ITEM_USED.get(item.getItem()));
                item.shrink(1);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        else {
            return InteractionResult.PASS;
        }
    }
}