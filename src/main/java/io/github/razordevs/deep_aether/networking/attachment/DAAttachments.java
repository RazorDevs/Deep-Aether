package io.github.razordevs.deep_aether.networking.attachment;

import com.mojang.serialization.Codec;
import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.util.profiling.jfr.event.PacketEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DAAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, DeepAether.MODID);
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<DAPlayerAttachment>> PLAYER = ATTACHMENTS.register("player", ()
            -> AttachmentType.builder(DAPlayerAttachment::new).serialize(DAPlayerAttachment.CODEC).copyOnDeath().build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<MoaEffectAttachment>> MOA_EFFECT = ATTACHMENTS.register("moa_effect", ()
            -> AttachmentType.builder(MoaEffectAttachment::new).serialize(MoaEffectAttachment.CODEC).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> PLAYER_BOSS_FIGHT = ATTACHMENTS.register("player_boss_fight", ()
            -> AttachmentType.builder(()->false).serialize(Codec.BOOL).build());
}
