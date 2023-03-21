
package teamrazor.deepaether.block;

import com.gildedgames.aether.block.AetherBlockStateProperties;
import com.gildedgames.aether.block.natural.AetherDoubleDropsLeaves;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class FloweringRoseLeavesBlock extends AetherDoubleDropsLeaves {

	public FloweringRoseLeavesBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.FLOWERING_AZALEA_LEAVES));
		this.registerDefaultState(this.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, false));
	}
	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 12;
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
