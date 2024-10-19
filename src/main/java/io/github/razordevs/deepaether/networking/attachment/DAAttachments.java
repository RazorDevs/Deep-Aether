package io.github.razordevs.deepaether.deepaether.networking.attachment;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import teamrazor.deepaether.DeepAether;

public class DAAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, DeepAether.MODID);
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<DAPlayerAttachment>> PLAYER = ATTACHMENTS.register("player", ()
            -> AttachmentType.builder(DAPlayerAttachment::new).serialize(DAPlayerAttachment.CODEC).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<MoaEffectAttachment>> MOA_EFFECT = ATTACHMENTS.register("moa_effect", ()
            -> AttachmentType.builder(MoaEffectAttachment::new).serialize(MoaEffectAttachment.CODEC).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> PLAYER_BOSS_FIGHT = ATTACHMENTS.register("player_boss_fight", ()
            -> AttachmentType.builder(()->false).serialize(Codec.BOOL).build());
}
