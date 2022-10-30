package teamrazor.deepaether.client;

import com.gildedgames.aether.block.AetherWoodTypes;
import net.minecraft.client.renderer.Sheets;
import teamrazor.deepaether.blockentity.DeepAetherModWoodTypes;

public class DeepAetherModAtlases {
    public DeepAetherModAtlases() {
    }
    public static void registerWoodTypeAtlases() {
        Sheets.addWoodType(DeepAetherModWoodTypes.ROSE);
        Sheets.addWoodType(DeepAetherModWoodTypes.CRUDEROOT);
        Sheets.addWoodType(DeepAetherModWoodTypes.YAGROOT);
    }
}
