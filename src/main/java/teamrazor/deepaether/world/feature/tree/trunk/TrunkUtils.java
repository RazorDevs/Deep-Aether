package teamrazor.deepaether.world.feature.tree.trunk;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;


public class TrunkUtils {

    public static void FillBetween(BlockPos pos1, BlockPos pos2, Level level, BlockState state) {
        int xDelta = pos1.getX()-pos2.getX();

        BlockPos oldPos = pos1;
        level.setBlock(pos1, state, 3);
        BlockPos newPos;
        for (int x = 0; x < Math.abs(xDelta); x++) {
            newPos = new BlockPos(pos1.getX() + x, getY(pos1, pos2, x), pos1.getZ());

            int oldNewDelta = Math.abs(newPos.getY()) - Math.abs(oldPos.getY());
            for (int i = 1; i < oldNewDelta; i++) {
                level.setBlock(newPos.north(i), state, 3);
            }
            level.setBlock(newPos, state, 3);
        }
    }

    private static int getY(@NotNull BlockPos a, @NotNull BlockPos b, int x) {
        int k = (a.getY() - b.getY()) / (a.getX() - b.getX());
        int m = b.getY()-(b.getX()*k);

        return (k*x) + m;
    }
}
