package io.github.razordevs.deep_aether.item.misc;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.materials.behavior.ItemUseConversion;
import io.github.razordevs.deep_aether.recipe.DARecipeTypes;
import io.github.razordevs.deep_aether.recipe.GoldenSwetBallRecipe;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class GoldenSwetBallItem extends Item implements ItemUseConversion<GoldenSwetBallRecipe> {

    public GoldenSwetBallItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result = this.convertBlock(DARecipeTypes.GOLDEN_SWET_BALL_RECIPE.get(), context);
        if (context.getLevel().isClientSide() && result == InteractionResult.SUCCESS) {
            context.getLevel().playSound(context.getPlayer(), context.getClickedPos(), AetherSoundEvents.ITEM_SWET_BALL_USE.get(), SoundSource.BLOCKS, 0.8F, 1.0F + (context.getLevel().getRandom().nextFloat() - context.getLevel().getRandom().nextFloat()) * 0.2F);
        }
        return result;
    }
}
