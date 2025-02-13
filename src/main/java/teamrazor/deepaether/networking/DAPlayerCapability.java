package teamrazor.deepaether.networking;

import com.aetherteam.nitrogen.capability.INBTSynchable;
import com.aetherteam.nitrogen.network.BasePacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DAPlayerCapability implements DeepAetherPlayer {
    private int bladeOfLuckDamage;
    private int oldBladeOfLuckDamage = 6;
    public boolean changeBladeOfLuckState;

    private boolean sliderSlamActivated = false;
    private final Player player;
    public int windShieldCooldown;

    private boolean isFloatyScarfWrappedAroundNeck = false;

    int floatyScarfColor0 = -1;
    int floatyScarfColor1 = -1;
    int floatyScarfColor2 = -1;
    int floatyScarfColor3 = -1;
    int floatyScarfColor4 = -1;

    private final Map<String, Triple<INBTSynchable.Type, Consumer<Object>, Supplier<Object>>> synchableFunctions;

    @Override
    public boolean isSliderSlamActivated() {
        return sliderSlamActivated;
    }

    @Override
    public void setSliderSlamActivated(boolean var1) {
        sliderSlamActivated = var1;
    }

    public DAPlayerCapability(Player player) {
        this.synchableFunctions = Map.ofEntries(
                Map.entry("setSliderSlamActivated", Triple.of(Type.BOOLEAN, (object) -> this.setSliderSlamActivated((Boolean) object), this::isSliderSlamActivated)),
                Map.entry("setBladeOfLuckDamage", Triple.of(Type.INT, (object) -> this.setBladeOfLuckDamage((Integer) object), this::getBladeOfLuckDamage)),
                Map.entry("setWindShieldCooldown", Triple.of(Type.INT, (object) -> this.setWindShieldCooldown((int) object), this::getWindShieldCooldown)),
                Map.entry("setFloatyScarfWrappedAroundNeck", Triple.of(Type.BOOLEAN, (object) -> this.setFloatyScarfWrappedAroundNeck((boolean) object), this::isFloatyScarfWrappedAroundNeck)),

                Map.entry("setFloatyScarfColor0", Triple.of(Type.INT, (object) -> this.setFloatyScarfColor0((int) object), this::getFloatyScarfColor0)),
                Map.entry("setFloatyScarfColor1", Triple.of(Type.INT, (object) -> this.setFloatyScarfColor1((int) object), this::getFloatyScarfColor1)),
                Map.entry("setFloatyScarfColor2", Triple.of(Type.INT, (object) -> this.setFloatyScarfColor2((int) object), this::getFloatyScarfColor2)),
                Map.entry("setFloatyScarfColor3", Triple.of(Type.INT, (object) -> this.setFloatyScarfColor3((int) object), this::getFloatyScarfColor3)),
                Map.entry("setFloatyScarfColor4", Triple.of(Type.INT, (object) -> this.setFloatyScarfColor4((int) object), this::getFloatyScarfColor4))

        );
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Map<String, Triple<INBTSynchable.Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    @Override
    public BasePacket getSyncPacket(String key, INBTSynchable.Type type, Object value) {
        return new DAPlayerSyncPacket(this.getPlayer().getId(), key, type, value);
    }

    @Override
    public SimpleChannel getPacketChannel() {
        return DAPacketHandler.INSTANCE;
    }

    @Override
    public CompoundTag serializeNBT() {
         return new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
    }

    public int getBladeOfLuckDamage() {
        return bladeOfLuckDamage;
    }

    public int getOldBladeOfLuckDamage() {
        return oldBladeOfLuckDamage;
    }

    public void setBladeOfLuckDamage(int bladeOfLuckDamage) {
        this.oldBladeOfLuckDamage = this.getBladeOfLuckDamage();
        changeBladeOfLuckState = true;
        this.bladeOfLuckDamage = bladeOfLuckDamage;
    }


    public void setWindShieldCooldown(int windShieldCooldown) {
        this.windShieldCooldown = windShieldCooldown;
    }

    public int getWindShieldCooldown() {
        return windShieldCooldown;
    }

    public void setChangeBladeOfLuckState(boolean bool) {
        this.changeBladeOfLuckState = bool;
    }

    public boolean getChangeBladeOfLuckState() {
        return changeBladeOfLuckState;
    }

    public boolean isFloatyScarfWrappedAroundNeck() {
        return isFloatyScarfWrappedAroundNeck;
    }

    public void setFloatyScarfWrappedAroundNeck(boolean bool) {
        this.isFloatyScarfWrappedAroundNeck = bool;
    }

    public int getFloatyScarfColor0() {
        return this.floatyScarfColor0;
    }
    public int getFloatyScarfColor1() {
        return this.floatyScarfColor1;
    }
    public int getFloatyScarfColor2() {
        return this.floatyScarfColor2;
    }
    public int getFloatyScarfColor3() {
        return this.floatyScarfColor3;
    }
    public int getFloatyScarfColor4() {
        return this.floatyScarfColor4;
    }

    public void setFloatyScarfColor0(int color) {
        this.floatyScarfColor0 = color;
    }
    public void setFloatyScarfColor1(int color) {
        this.floatyScarfColor1 = color;
    }
    public void setFloatyScarfColor2(int color) {
        this.floatyScarfColor2 = color;
    }
    public void setFloatyScarfColor3(int color) {
        this.floatyScarfColor3 = color;
    }
    public void setFloatyScarfColor4(int color) {
        this.floatyScarfColor4 = color;
    }
}
