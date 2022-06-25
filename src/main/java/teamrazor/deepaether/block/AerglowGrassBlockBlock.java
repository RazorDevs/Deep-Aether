
package teamrazor.deepaether.block;

import com.gildedgames.aether.common.block.natural.AetherGrassBlock;
import com.gildedgames.aether.common.registry.AetherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class AerglowGrassBlockBlock extends AetherGrassBlock {

	public AerglowGrassBlockBlock() {
		super(BlockBehaviour.Properties.of(Material.GRASS)
				.sound(SoundType.GRAVEL)
				.strength(1f, 10f)
				.randomTicks());
	}
}
