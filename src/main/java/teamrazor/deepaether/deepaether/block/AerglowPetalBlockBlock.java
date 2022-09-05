
package teamrazor.deepaether.deepaether.block;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class AerglowPetalBlockBlock extends Block {
	public AerglowPetalBlockBlock() {
		super(Properties.of(Material.METAL).sound(SoundType.MOSS).strength(1f, 10f).lightLevel(s -> 9));
	}
}