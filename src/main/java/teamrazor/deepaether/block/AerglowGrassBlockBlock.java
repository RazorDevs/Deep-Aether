
package teamrazor.deepaether.block;

import com.gildedgames.aether.block.natural.AetherGrassBlock;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolAction;
import teamrazor.deepaether.init.DeepAetherModBlocks;

public class AerglowGrassBlockBlock extends AetherGrassBlock {

	public AerglowGrassBlockBlock() {
		super(BlockBehaviour.Properties.of(Material.GRASS)
				.sound(SoundType.GRAVEL)
				.strength(1f, 10f)
				.randomTicks());
	}
	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
		if (context.getItemInHand().getItem() instanceof ShovelItem) {
			return DeepAetherModBlocks.AERGLOW_GRASS_PATH.get().defaultBlockState();
		}
		return super.getToolModifiedState(state, context, toolAction, simulate);
	}
}
