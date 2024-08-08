package teamrazor.deepaether.networking.packet;

import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.packet.SyncEntityPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import oshi.util.tuples.Quartet;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.networking.attachment.DAAttachments;
import teamrazor.deepaether.networking.attachment.MoaEffectAttachment;

import java.util.function.Supplier;

public class MoaEffectSyncPacket extends SyncEntityPacket<MoaEffectAttachment> {
    public static final ResourceLocation ID = new ResourceLocation(DeepAether.MODID, "sync_moa_effect_attachment");
    public MoaEffectSyncPacket(Quartet<Integer, String, INBTSynchable.Type, Object> values) {
        super(values);
    }

    public MoaEffectSyncPacket(int playerID, String key, INBTSynchable.Type type, Object value) {
        super(playerID, key, type, value);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static MoaEffectSyncPacket decode(FriendlyByteBuf buf) {
        return new MoaEffectSyncPacket(SyncEntityPacket.decodeEntityValues(buf));
    }

    @Override
    public Supplier<AttachmentType<MoaEffectAttachment>> getAttachment() {
        return DAAttachments.MOA_EFFECT;
    }
}
