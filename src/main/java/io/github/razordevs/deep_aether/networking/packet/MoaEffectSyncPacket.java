package io.github.razordevs.deep_aether.networking.packet;

import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.github.razordevs.deep_aether.networking.attachment.MoaEffectAttachment;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import oshi.util.tuples.Quartet;

import java.util.function.Supplier;

public class MoaEffectSyncPacket extends SyncEntityPacket<MoaEffectAttachment> {
    public static final Type<MoaEffectSyncPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "sync_moa_effect_attachment"));

    public static final StreamCodec<RegistryFriendlyByteBuf, MoaEffectSyncPacket> STREAM_CODEC = CustomPacketPayload.codec(
            MoaEffectSyncPacket::write,
            MoaEffectSyncPacket::decode);

    public MoaEffectSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public MoaEffectSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    public static MoaEffectSyncPacket decode(RegistryFriendlyByteBuf buf) {
        return new MoaEffectSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public Supplier<AttachmentType<MoaEffectAttachment>> getAttachment() {
        return DAAttachments.MOA_EFFECT;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void execute(MoaEffectSyncPacket payload, IPayloadContext context) {
        SyncEntityPacket.execute(payload, context.player());
    }
}
