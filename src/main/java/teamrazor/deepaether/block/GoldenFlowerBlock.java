package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.datagen.tags.DATags;

import java.util.function.Supplier;

public class GoldenFlowerBlock extends FlowerBlock {


    public GoldenFlowerBlock(MobEffect p_53512_, int p_53513_, Properties p_53514_) {
        super(p_53512_, p_53513_, p_53514_);
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return p_51042_.is(DATags.Blocks.CAN_GOLDEN_FLOWERS_SURVIVE_ON);
    }
}
