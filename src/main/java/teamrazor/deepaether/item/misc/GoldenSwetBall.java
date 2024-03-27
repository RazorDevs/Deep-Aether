package teamrazor.deepaether.item.misc;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.materials.behavior.ItemUseConversion;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.recipe.DARecipe;
import teamrazor.deepaether.recipe.GoldenSwetBallRecipe;

public class GoldenSwetBall extends Item implements ItemUseConversion<GoldenSwetBallRecipe> {
    public GoldenSwetBall(Properties properties) {
        super(properties);
    }
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        return this.convertBlock(DARecipe.GOLDEN_SWET_BALL_RECIPE.get(), context);
    }
}