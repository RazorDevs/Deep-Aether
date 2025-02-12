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

    int getFloatyScarfColor0();
    int getFloatyScarfColor1();
    int getFloatyScarfColor2();
    int getFloatyScarfColor3();

    void setFloatyScarfColor0(int color);
    void setFloatyScarfColor1(int color);
    void setFloatyScarfColor2(int color);
    void setFloatyScarfColor3(int color);



}
