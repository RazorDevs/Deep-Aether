package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.init.DABlocks;

public class GlossomGrassPlant extends TallGrassBlock {
    public GlossomGrassPlant(Properties p_57318_) {
        super(p_57318_);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {

        if (state.is(DABlocks.GLOSSOM_GRASS.get()))
            level.setBlockAndUpdate(pos, DABlocks.GLOSSOM_GRASS.get().defaultBlockState());
        }

        if (state.is(DABlocks.GLOSSOM_ROOTS.get()))
                level.setBlockAndUpdate(pos, DABlocks.GLOSSOM_ROOTS.get().defaultBlockState());
}
    }

}
