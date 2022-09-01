package teamrazor.deepaether.block;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import teamrazor.deepaether.init.DeepAetherModBlocks;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.world.feature.tree.RosewoodTreeGrower;


public class RoseWoodSapling extends SaplingBlock{
    public RoseWoodSapling() {
        super((new RosewoodTreeGrower()), Properties.copy(Blocks.OAK_SAPLING));
    }
}
