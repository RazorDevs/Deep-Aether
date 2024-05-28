package teamrazor.deepaether.item.misc;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.materials.behavior.ItemUseConversion;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import teamrazor.deepaether.recipe.DARecipe;
import teamrazor.deepaether.recipe.GlowingSporesRecipe;
import teamrazor.deepaether.recipe.GoldenSwetBallRecipe;

public class GlowingSporeItem extends Item implements ItemUseConversion<GlowingSporesRecipe> {

    public GlowingSporeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        InteractionResult result;
        BlockState state = level.getBlockState(context.getClickedPos());

        if(level.getBlockState(context.getClickedPos()).hasProperty(DoublePlantBlock.HALF)) {
            result = this.convertBlock(DARecipe.GLOWING_SPORES_RECIPE.get(), context);
            if(state.getValue(DoublePlantBlock.HALF).equals(DoubleBlockHalf.LOWER)) {
                this.convertBlock(DARecipe.GLOWING_SPORES_RECIPE.get(), new UseOnContext(context.getLevel(), context.getPlayer(), context.getHand(), context.getItemInHand(), new BlockHitResult(context.getClickedPos().above(1).getCenter(), context.getClickedFace(), context.getClickedPos().above(1), context.isInside())));
            }
            else this.convertBlock(DARecipe.GLOWING_SPORES_RECIPE.get(), new UseOnContext(context.getLevel(), context.getPlayer(), context.getHand(), context.getItemInHand(), new BlockHitResult(context.getClickedPos().below(1).getCenter(), context.getClickedFace(), context.getClickedPos().below(1), context.isInside())));
        }

        else result = this.convertBlock(DARecipe.GLOWING_SPORES_RECIPE.get(), context);
        return result;
    }
}
