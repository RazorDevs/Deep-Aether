package teamrazor.deepaether.networking;

import com.aetherteam.nitrogen.capability.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import oshi.util.tuples.Quartet;

public class DAPlayerSyncPacket extends SyncEntityPacket<DeepAetherPlayer> {
    public DAPlayerSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public DAPlayerSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    public static DAPlayerSyncPacket decode(FriendlyByteBuf buf) {
        return new DAPlayerSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    public LazyOptional<DeepAetherPlayer> getCapability(Entity entity) {
        return DeepAetherPlayer.get((Player) entity);
    }
}
