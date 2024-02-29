package teamrazor.deepaether.item.gear.other;

import com.aetherteam.aether.client.AetherSoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.entity.AerwhaleSaddleable;
import teamrazor.deepaether.entity.FireProjectile;
import teamrazor.deepaether.init.DATiers;

import java.util.List;

public class AerwhaleSaddle extends Item {
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
        if(i > 70)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_1"));
        else if(i > 60)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_2"));
        else if(i > 50)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_3"));
        else if(i > 40)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_4"));
        else if(i > 30)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_5"));
        else if(i > 20)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_6"));
        else if(i > 10)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_7"));
        else
            components.add(Component.translatable("gui.deep_aether.flawless_tier_8"));

        if(i < 80)
            i++;
        else i = 0;


        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
