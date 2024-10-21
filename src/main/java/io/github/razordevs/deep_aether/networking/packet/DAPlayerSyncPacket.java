package io.github.razordevs.deep_aether.networking.packet;

import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.github.razordevs.deep_aether.networking.attachment.DAPlayerAttachment;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import oshi.util.tuples.Quartet;

import java.util.function.Supplier;

public class DAPlayerSyncPacket extends SyncEntityPacket<DAPlayerAttachment> {
    public static final Type<DAPlayerSyncPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "sync_da_player_attachment"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DAPlayerSyncPacket> STREAM_CODEC = CustomPacketPayload.codec(
            DAPlayerSyncPacket::write,
            DAPlayerSyncPacket::decode);

    public DAPlayerSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public DAPlayerSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    public static DAPlayerSyncPacket decode(RegistryFriendlyByteBuf buf) {
        return new DAPlayerSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public Supplier<AttachmentType<DAPlayerAttachment>> getAttachment() {
        return DAAttachments.PLAYER;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void execute(DAPlayerSyncPacket payload, IPayloadContext context) {
        SyncEntityPacket.execute(payload, context.player());
    }
}
