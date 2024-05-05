package teamrazor.deepaether.networking;

import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.nitrogen.network.BasePacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MoaEffectCapability implements MoaEffect {
    private int effectAmplifier = 0;
    private final Moa moa;

    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions;

    @Override
    public int getMoaEffectAmplifier() {
        return effectAmplifier;
    }

    @Override
    public void setMoaEffectAmplifier(int var1) {
        effectAmplifier = var1;
    }

    public MoaEffectCapability(Moa moa) {
        this.synchableFunctions = Map.ofEntries(Map.entry("setMoaEffectAmplifier", Triple.of(Type.INT, (object) -> {
            this.setMoaEffectAmplifier((int)object);
        }, this::getMoaEffectAmplifier)));
        this.moa = moa;
    }

    public Moa getMoa() {
        return this.moa;
    }

    @Override
    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    @Override
    public BasePacket getSyncPacket(String key, Type type, Object value) {
        return new DAMoasyncPacket(this.moa.getId(), key, type, value);
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
    public void deserializeNBT(CompoundTag tag) {

    }
}
