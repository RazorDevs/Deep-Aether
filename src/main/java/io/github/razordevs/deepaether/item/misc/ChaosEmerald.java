package io.github.razordevs.deepaether.deepaether.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChaosEmerald extends Item {
    public ChaosEmerald(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        player.sendSystemMessage(Component.literal("You mean the Chaos Emeralds?"));
        return super.use(level, player, interactionHand);
    }
}
