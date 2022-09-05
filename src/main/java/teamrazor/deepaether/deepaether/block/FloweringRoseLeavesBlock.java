
package teamrazor.deepaether.deepaether.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class FloweringRoseLeavesBlock extends LeavesBlock {

	public FloweringRoseLeavesBlock() {
		super(Properties.copy(Blocks.FLOWERING_AZALEA_LEAVES));
	}
	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 30;
	}
	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}
}
