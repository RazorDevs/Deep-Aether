package teamrazor.deepaether.item.gear;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import teamrazor.deepaether.DeepAetherMod;

import java.util.function.Supplier;

public class DAGlovesItem extends GlovesItem {
    public DAGlovesItem(ArmorMaterial material, double punchDamage, String glovesName, Supplier<? extends SoundEvent> glovesSound, Item.Properties properties)
    {
        super(material, punchDamage, glovesName, glovesSound, properties);

        this.setRenderTexture(DeepAetherMod.MODID, glovesName);
    }
}

