package teamrazor.deepaether.networking;

import com.aetherteam.aether.capability.AetherCapabilities;
import com.aetherteam.aether.capability.player.AetherPlayer;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

public interface DeepAetherPlayer extends INBTSynchable<CompoundTag> {

    Player getPlayer();

    static LazyOptional<DeepAetherPlayer> get(Player player) {
        return player.getCapability(DACapabilities.DEEP_AETHER_PLAYER_CAPABILITY);
    }
    boolean isSliderSlamActivated();

    void setSliderSlamActivated(boolean var1);
}
