package io.github.razordevs.deep_aether.item.gear.other;

import io.github.razordevs.deep_aether.entity.AerwhaleSaddleable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AerwhaleSaddle extends Item implements FlawlessDrop {
    public AerwhaleSaddle(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        if (entity instanceof AerwhaleSaddleable saddleable && entity.isAlive()) {
            if (!saddleable.isSaddled() && saddleable.isSaddleable()) {
                if (!player.level().isClientSide) {
                    saddleable.equipSaddle(SoundSource.NEUTRAL);
                    entity.level().gameEvent(entity, GameEvent.EQUIP, entity.position());
                    stack.shrink(1);
                }

                return InteractionResult.sidedSuccess(player.level().isClientSide);
            }
        }

        return InteractionResult.PASS;
    }

    int i = 0;
    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        flawlessComponent(components, i);
        i = i < 80 ? i + 1 : 0;
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
