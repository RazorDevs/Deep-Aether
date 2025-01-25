package io.github.razordevs.deep_aether.item.misc;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.attachment.AetherDataAttachments;
import com.aetherteam.aether.attachment.AetherTimeAttachment;
import com.aetherteam.aether.command.SunAltarWhitelist;
import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.aetherteam.aether.network.packet.clientbound.OpenSunAltarPacket;
import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public class SunClock extends Item {

    public SunClock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand pUsedHand) {
        if (!level.isClientSide()) {
            if (AetherConfig.SERVER.sun_altar_whitelist.get() && !player.hasPermissions(4) && !SunAltarWhitelist.INSTANCE.isWhiteListed(player.getGameProfile())) { // Prevents non-operator or non-whitelisted players from using the Sun Altar on servers
                player.displayClientMessage(Component.translatable(Aether.MODID + ".sun_altar.no_permission"), true); // Player doesn't have permission to use the Sun Altar.
            } else {
                if (level.dimension().location().toString().equals("aether:the_aether")) {
                    if (level.hasData(AetherDataAttachments.AETHER_TIME)) { // Checks if the level has the capability used for Aether time, which determines if the Sun Altar has control over the time of a dimension.
                        if (!level.getData(AetherDataAttachments.AETHER_TIME).isEternalDay()) { // Checks if the time is locked into eternal day or not.
                            this.openScreen(level, player, AetherTimeAttachment.getTicksPerDay());
                        } else {
                            player.displayClientMessage(Component.translatable(Aether.MODID + ".sun_altar.in_control"), true); // Sun Spirit is still in control of the realm.
                        }
                    } else {
                        this.openScreen(level, player, 24000);
                    }
                } else {
                    player.displayClientMessage(Component.translatable(Aether.MODID + ".sun_altar.no_power"), true); // Sun Altar has no power in the dimension.
                }
            }
        }
        return InteractionResultHolder.success(player.getItemInHand(pUsedHand));
    }

    protected void openScreen(Level level, Player player, int timeScale) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, new OpenSunAltarPacket(Component.translatable("menu." + DeepAether.MODID + ".sun_clock"), timeScale));
        }
    }

    @Override
    public Component getDescription() {
        return Component.translatable("deep_aether.item.disabled_item").withStyle(Style.EMPTY.withItalic(true)
                .withColor(TextColor.parseColor("#d1362b").result().get()));
    }
}
