
package teamrazor.deepaether.block;

import net.minecraft.world.level.block.DirtPathBlock;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.SoundType;

public class AerglowGrassPathBlock extends DirtPathBlock {
	public AerglowGrassPathBlock() {
		super(Properties.of(Material.DIRT).strength(0.65F).sound(SoundType.GRAVEL));
	}

}
