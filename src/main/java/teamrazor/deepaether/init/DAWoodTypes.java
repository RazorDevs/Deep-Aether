package teamrazor.deepaether.init;

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
    public static final BlockSetType CONBERRY_BLOCK_SET = new BlockSetType(DeepAetherMod.MODID + ":conberry");
    public static final WoodType CONBERRY = new WoodType(DeepAetherMod.MODID + ":conberry", CONBERRY_BLOCK_SET);
}
