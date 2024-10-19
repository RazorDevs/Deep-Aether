package io.github.razordevs.deepaether.deepaether.networking.packet;

import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import io.github.razordevs.deepaether.deepaether.networking.attachment.DAAttachments;
import io.github.razordevs.deepaether.deepaether.networking.attachment.DAPlayerAttachment;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import oshi.util.tuples.Quartet;
import teamrazor.deepaether.DeepAether;

import java.util.function.Supplier;

public class DAPlayerSyncPacket extends SyncEntityPacket<DAPlayerAttachment> {
    public static final ResourceLocation ID = new ResourceLocation(DeepAether.MODID, "sync_da_player_attachment");
    public DAPlayerSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public DAPlayerSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static DAPlayerSyncPacket decode(FriendlyByteBuf buf) {
        return new DAPlayerSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public Supplier<AttachmentType<DAPlayerAttachment>> getAttachment() {
        return DAAttachments.PLAYER;
    }
}
