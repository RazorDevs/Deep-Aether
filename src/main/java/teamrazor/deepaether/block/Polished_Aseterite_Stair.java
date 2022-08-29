package teamrazor.deepaether.block;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import teamrazor.deepaether.init.DeepAetherModBlocks;

public class Polished_Aseterite_Stair extends StairBlock {
    public Polished_Aseterite_Stair()  {
        super(DeepAetherModBlocks.POLISHED_ASETERITE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops());
    }
}
