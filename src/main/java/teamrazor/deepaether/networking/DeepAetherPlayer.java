package teamrazor.deepaether.networking;

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

    int getBladeOfLuckDamage();
    int getOldBladeOfLuckDamage();
    void setBladeOfLuckDamage(int bladeOfLuckDamage);
    boolean getChangeBladeOfLuckState();
    void setChangeBladeOfLuckState(boolean bool);
    void setWindShieldCooldown(int windShieldCooldown);
    int getWindShieldCooldown();

    //Nothing to see here
    boolean isFloatyScarfWrappedAroundNeck();
    void setFloatyScarfWrappedAroundNeck(boolean bool);
}
