package teamrazor.deepaether.item.gear.other;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class SpookyRing extends RingItem {
    public SpookyRing(Supplier<? extends SoundEvent> ringSound, Properties properties) {
        super(ringSound, properties);
    }
}
