package teamrazor.deepaether.networking;

import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.LazyOptional;
import oshi.util.tuples.Quartet;

public class DAMoasyncPacket extends SyncEntityPacket<MoaEffect> {
    public DAMoasyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public DAMoasyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    public static DAMoasyncPacket decode(FriendlyByteBuf buf) {
        return new DAMoasyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    public LazyOptional<MoaEffect> getCapability(Entity entity) {
        return MoaEffect.get((Moa) entity);
    }
}
