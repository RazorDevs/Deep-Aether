package teamrazor.deepaether.deepaether.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;

import teamrazor.deepaether.world.feature.tree.RosewoodTreeGrower;


public class RoseWoodSapling extends SaplingBlock{
    public RoseWoodSapling() {
        super((new RosewoodTreeGrower()), Properties.copy(Blocks.OAK_SAPLING));
    }
}
