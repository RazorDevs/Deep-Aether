package teamrazor.deepaether.networking;

import com.aetherteam.aether.network.packet.AetherPlayerSyncPacket;
import com.aetherteam.aether.network.packet.AetherTimeSyncPacket;
import com.aetherteam.aether.network.packet.PhoenixArrowSyncPacket;
import com.aetherteam.aether.network.packet.clientbound.*;
import com.aetherteam.aether.network.packet.serverbound.*;
import com.aetherteam.nitrogen.network.BasePacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import teamrazor.deepaether.DeepAetherMod;

import java.util.function.Function;

public class DAPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(DeepAetherMod.MODID, "main"), () -> "1", "1"::equals, "1"::equals);
    private static int index;


    public static synchronized void register() {
        //register(SetSliderSlamPacket.class, SetSliderSlamPacket::decode);

        register(DAPlayerSyncPacket.class, DAPlayerSyncPacket::decode);
    }

    private static <MSG extends BasePacket> void register(Class<MSG> packet, Function<FriendlyByteBuf, MSG> decoder) {
        INSTANCE.messageBuilder(packet, index++).encoder(BasePacket::encode).decoder(decoder).consumerMainThread(BasePacket::handle).add();
    }
}
