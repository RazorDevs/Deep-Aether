
package teamrazor.deepaether.block;

import com.gildedgames.aether.block.AetherBlocks;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.material.MaterialColor;
import teamrazor.deepaether.init.DeepAetherModBlocks;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import java.util.List;
import java.util.Collections;

public class AerglowGrassPathBlock extends DirtPathBlock {
	public AerglowGrassPathBlock() {
		super(Properties.of(Material.DIRT).strength(0.65F).sound(SoundType.GRAVEL));
	}

}
