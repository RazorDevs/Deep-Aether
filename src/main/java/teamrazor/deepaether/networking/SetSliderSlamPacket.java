package teamrazor.deepaether.networking;

import com.aetherteam.nitrogen.network.BasePacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import teamrazor.deepaether.item.gear.other.SliderEye;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

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

    @Override
    public void execute(Player playerEntity) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            if (Minecraft.getInstance().level.getEntity(this.playerID()) instanceof Player player) {
                List<SlotResult> list = EquipmentUtil.getCurios(player, DAItems.SLIDER_EYE.get());
                System.out.println("something");
                if(list != null) {
                    if(list.get(0).stack().getItem() instanceof SliderEye eye) {
                        System.out.println("hello");
                        eye.maxFallTime = 200;
                    }
                }
            }
        }
    }

    public int playerID() {
        return this.playerID;
    }

    public boolean isActive() {
        return this.isActive;
    }
}

