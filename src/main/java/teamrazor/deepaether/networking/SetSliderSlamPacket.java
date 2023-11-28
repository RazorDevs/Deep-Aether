package teamrazor.deepaether.networking;

import com.aetherteam.nitrogen.network.BasePacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public record SetSliderSlamPacket(int playerID, boolean isActive) implements BasePacket {
    public SetSliderSlamPacket(int playerID, boolean isActive) {
        this.playerID = playerID;
        this.isActive = isActive;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.playerID());
        buf.writeBoolean(this.isActive());
    }

    public static SetSliderSlamPacket decode(FriendlyByteBuf buf) {
        int playerID = buf.readInt();
        boolean isActive = buf.readBoolean();
        return new SetSliderSlamPacket(playerID, isActive);
    }

    public void execute(Player playerEntity) {
        //if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
        //    Entity var3 = Minecraft.getInstance().level.getEntity(this.playerID());
        //    if (var3 instanceof Player) {
        //        Player player = (Player)var3;
        //        player.setInvisible(this.isActive());
        //    }
        //}
        System.out.println("test");
    }

    public int playerID() {
        return this.playerID;
    }

    public boolean isActive() {
        return this.isActive;
    }
}

