package teamrazor.deepaether.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.Material;
import teamrazor.deepaether.init.DeepAetherModBlocks;

public class Polished_Aseterite_Bricks_Stairs extends StairBlock {
    public Polished_Aseterite_Bricks_Stairs() {
        super(() -> DeepAetherModBlocks.POLISHED_ASETERITE.get().defaultBlockState(),
                Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops());
    }
}
