package teamrazor.deepaether.init;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import teamrazor.deepaether.DeepAether;

public class DAWoodTypes {
    public static final BlockSetType ROSEROOT_BLOCK_SET = new BlockSetType(DeepAether.MODID + ":roseroot");
    public static final WoodType ROSEROOT = new WoodType(DeepAether.MODID + ":roseroot", ROSEROOT_BLOCK_SET);
    public static final BlockSetType CRUDEROOT_BLOCK_SET = new BlockSetType(DeepAether.MODID + ":cruderoot");
    public static final WoodType CRUDEROOT = new WoodType(DeepAether.MODID + ":cruderoot", CRUDEROOT_BLOCK_SET);
    public static final BlockSetType YAGROOT_BLOCK_SET = new BlockSetType(DeepAether.MODID + ":yagroot");
    public static final WoodType YAGROOT = new WoodType(DeepAether.MODID + ":yagroot", YAGROOT_BLOCK_SET);
    public static final BlockSetType CONBERRY_BLOCK_SET = new BlockSetType(DeepAether.MODID + ":conberry");
    public static final WoodType CONBERRY = new WoodType(DeepAether.MODID + ":conberry", CONBERRY_BLOCK_SET);
    public static final BlockSetType SUNROOT_BLOCK_SET = new BlockSetType(DeepAether.MODID + ":sunroot");
    public static final WoodType SUNROOT = new WoodType(DeepAether.MODID + ":sunroot", SUNROOT_BLOCK_SET);
}
