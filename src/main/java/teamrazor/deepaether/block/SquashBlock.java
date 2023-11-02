package teamrazor.deepaether.block;

import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.StemGrownBlock;
import teamrazor.deepaether.init.DABlocks;

public class SquashBlock extends StemGrownBlock {

    public SquashBlock(Properties properties) {
        super(properties);
    }

    @Override
    public StemBlock getStem() {
        return (StemBlock) DABlocks.SQUASH_STEM.get();
    }

    @Override
    public AttachedStemBlock getAttachedStem() {
        return (AttachedStemBlock) DABlocks.ATTACHED_SQUASH_STEM.get();
    }
}
