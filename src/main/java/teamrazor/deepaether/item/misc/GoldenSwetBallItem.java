package teamrazor.deepaether.item.misc;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.materials.behavior.ItemUseConversion;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import teamrazor.deepaether.entity.DABoatEntity;
import teamrazor.deepaether.entity.DAChestBoatEntity;
import teamrazor.deepaether.recipe.DARecipe;
import teamrazor.deepaether.recipe.GoldenSwetBallRecipe;

import java.util.List;
import java.util.function.Predicate;

public class GoldenSwetBallItem extends Item implements ItemUseConversion<GoldenSwetBallRecipe> {

    public GoldenSwetBallItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result = this.convertBlock(DARecipe.GOLDEN_SWET_BALL_RECIPE.get(), context);
        if (context.getLevel().isClientSide() && result == InteractionResult.SUCCESS) {
            context.getLevel().playSound(context.getPlayer(), context.getClickedPos(), AetherSoundEvents.ITEM_SWET_BALL_USE.get(), SoundSource.BLOCKS, 0.8F, 1.0F + (context.getLevel().getRandom().nextFloat() - context.getLevel().getRandom().nextFloat()) * 0.2F);
        }
        return result;
    }
}
