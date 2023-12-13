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
    private boolean sliderSlamActivated = true;
    private final Player player;

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
        this.synchableFunctions = Map.ofEntries(Map.entry("setSliderSlamActivated", Triple.of(Type.BOOLEAN, (object) -> {
            this.setSliderSlamActivated((Boolean)object);
        }, this::isSliderSlamActivated)));
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
}
