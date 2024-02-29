package teamrazor.deepaether.item.gear.other;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.entity.AerwhaleSaddleable;

public class AerwhaleSaddle extends Item {
    public AerwhaleSaddle(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if (livingEntity instanceof AerwhaleSaddleable saddleable && livingEntity.isAlive()) {
            if (!saddleable.isSaddled() && saddleable.isSaddleable()) {
                if (!player.level.isClientSide) {
                    saddleable.equipSaddle(SoundSource.NEUTRAL);
                    livingEntity.level.gameEvent(livingEntity, GameEvent.EQUIP, livingEntity.position());
                    itemStack.shrink(1);
                }

                return InteractionResult.sidedSuccess(player.level.isClientSide);
            }
        }

        return InteractionResult.PASS;
    }
}
