package teamrazor.deepaether.networking.attachment;

import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.BasePacket;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.tuple.Triple;
import teamrazor.deepaether.client.keys.DeepAetherKeys;
import teamrazor.deepaether.event.DAGeneralEvents;
import teamrazor.deepaether.networking.packet.DAPlayerSyncPacket;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DAPlayerAttachment implements INBTSynchable {
    private boolean isSliderSlamActivated;
    private boolean hasSkyjadeSet;
    private boolean isSkyjadeAbilityActivated;

    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions = Map.ofEntries(
            Map.entry("setSliderSlamActivated", Triple.of(Type.BOOLEAN, (object) -> this.setSliderSlamActivated((boolean) object), this::isSliderSlamActivated)),
            Map.entry("hasSkyjadeSet", Triple.of(Type.BOOLEAN, (object) -> this.setHasSkyjadeSet((boolean) object), this::hasSkyjadeSet)),
            Map.entry("setSkyjadeAbilityActivated", Triple.of(Type.BOOLEAN, (object) -> this.setSkyjadeAbilityActivated((boolean) object), this::isSkyjadeAbilityActivated))
    );

    public static final Codec<DAPlayerAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("is_slider_slam_activated").forGetter(DAPlayerAttachment::isSliderSlamActivated),
            Codec.BOOL.fieldOf("has_skyjade_set").forGetter(DAPlayerAttachment::isSliderSlamActivated),
            Codec.BOOL.fieldOf("is_skyjade_ability_activated").forGetter(DAPlayerAttachment::isSkyjadeAbilityActivated)
    ).apply(instance, DAPlayerAttachment::new));

    private DAPlayerAttachment(boolean isSliderSlamActivated, boolean hasSkyjadeSet, boolean isSkyjadeAbilityActivated) {
        this.setSliderSlamActivated(isSliderSlamActivated);
        this.setSliderSlamActivated(hasSkyjadeSet);
        this.setSkyjadeAbilityActivated(isSkyjadeAbilityActivated);
    }

    public DAPlayerAttachment() {
        this(false, false, false);
    }

    public boolean isSliderSlamActivated() {
        return isSliderSlamActivated;
    }

    public void setSliderSlamActivated(boolean var1) {
        isSliderSlamActivated = var1;
    }

    public boolean hasSkyjadeSet() {
        return hasSkyjadeSet;
    }

    public void setHasSkyjadeSet(boolean var1) {
        hasSkyjadeSet = var1;
    }


    public boolean isSkyjadeAbilityActivated() {
        return isSkyjadeAbilityActivated;
    }

    public void setSkyjadeAbilityActivated(boolean var1) {
        isSkyjadeAbilityActivated = var1;
    }

    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    public BasePacket getSyncPacket(int entityID, String key, Type type, Object value) {
        return new DAPlayerSyncPacket(entityID, key, type, value);
    }
}
