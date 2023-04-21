package teamrazor.deepaether.client.keys;

import com.aetherteam.aether.capability.player.AetherPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class DeepAetherCapabilityHooks {

    //TODO: Reorganize abilities.

    public static void keyInput(int key) {
        checkDashAbility(key);
    }
    private static void checkDashAbility(int input) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            AetherPlayer.get(player).ifPresent((aetherPlayer) -> {
                if (input == DeepAetherKeys.CLOUDIUM_DASH_ABILITY.getKey().getValue()) {
                    aetherPlayer.setGravititeJumpActive(DeepAetherKeys.CLOUDIUM_DASH_ABILITY.isDown());
                }
            });
        }
    }
}

