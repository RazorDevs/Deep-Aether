package io.github.razordevs.deep_aether.networking.attachment;

import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncPacket;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.razordevs.deep_aether.networking.packet.MoaEffectSyncPacket;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MoaEffectAttachment implements INBTSynchable {
    private int effectAmplifier;

    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions = Map.ofEntries(
            Map.entry("setMoaEffectAmplifier", Triple.of(Type.INT, (object) -> this.setMoaEffectAmplifier((int) object), this::getMoaEffectAmplifier))
    );

    public static final Codec<MoaEffectAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("get_moa_effect_amplifier").forGetter(MoaEffectAttachment::getMoaEffectAmplifier)
    ).apply(instance, MoaEffectAttachment::new));

    private MoaEffectAttachment(int getMoaEffectAmplifier) {
        this.setMoaEffectAmplifier(getMoaEffectAmplifier);
    }

    public MoaEffectAttachment() {
        this(0);
    }

    public int getMoaEffectAmplifier() {
        return effectAmplifier;
    }
    public void setMoaEffectAmplifier(int var1) {
        effectAmplifier = var1;
    }

    @Override
    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    @Override
    public SyncPacket getSyncPacket(int entityID, String key, Type type, Object value) {
        return new MoaEffectSyncPacket(entityID, key, type, value);
    }

    public void onJoinLevel(Moa moa) {
        if (!moa.level().isClientSide()) {
            this.setSynched(moa.getId(), Direction.CLIENT, "setMoaEffectAmplifier", getMoaEffectAmplifier());
        }
    }
}
