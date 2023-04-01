package teamrazor.deepaether.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import teamrazor.deepaether.DeepAetherMod;

public class DAWoodTypes {
    public static final BlockSetType ROSEROOT_BLOCK_SET = new BlockSetType(DeepAetherMod.MODID + ":roseroot");
    public static final WoodType ROSEROOT = new WoodType(DeepAetherMod.MODID + ":roseroot", ROSEROOT_BLOCK_SET);
    public static final BlockSetType YAGROOT_BLOCK_SET = new BlockSetType(DeepAetherMod.MODID + ":yagroot");
    public static final WoodType YAGROOT = new WoodType(DeepAetherMod.MODID + ":yagroot", ROSEROOT_BLOCK_SET);

    public static final BlockSetType CRUDEROOT_BLOCK_SET = new BlockSetType(DeepAetherMod.MODID + ":cruderoot");
    public static final WoodType CRUDEROOT = new WoodType(DeepAetherMod.MODID + ":cruderoot", ROSEROOT_BLOCK_SET);

    public static final BlockSetType AMBERROOT_BLOCK_SET = new BlockSetType(DeepAetherMod.MODID + ":amberroot");
    public static final WoodType AMBERROOT = new WoodType(DeepAetherMod.MODID + ":amberroot", ROSEROOT_BLOCK_SET);
}
