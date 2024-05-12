package teamrazor.deepaether.networking.attachment;

import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.BasePacket;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.apache.commons.lang3.tuple.Triple;
import teamrazor.deepaether.networking.packet.DAPlayerSyncPacket;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DAPlayerAttachment implements INBTSynchable {
    private boolean isSliderSlamActivated;

    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions = Map.ofEntries(
            Map.entry("setSliderSlamActivated", Triple.of(Type.BOOLEAN, (object) -> this.setSliderSlamActivated((boolean) object), this::isSliderSlamActivated))
    );

    public static final Codec<DAPlayerAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("is_slider_slam_activated").forGetter(DAPlayerAttachment::isSliderSlamActivated)
    ).apply(instance, DAPlayerAttachment::new));

    private DAPlayerAttachment(boolean isSliderSlamActivated) {
        this.setSliderSlamActivated(isSliderSlamActivated);
    }

    public DAPlayerAttachment() {
        this(false);
    }
    public boolean isSliderSlamActivated() {
        return isSliderSlamActivated;
    }

    public void setSliderSlamActivated(boolean var1) {
        isSliderSlamActivated = var1;
    }


    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    public BasePacket getSyncPacket(int entityID, String key, Type type, Object value) {
        return new DAPlayerSyncPacket(entityID, key, type, value);
    }
}
