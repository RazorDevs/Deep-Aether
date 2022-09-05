
package teamrazor.deepaether.deepaether.block;


import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

import java.util.List;
import java.util.Collections;

public class StrippedRoseWoodWallBlock extends WallBlock {
	public StrippedRoseWoodWallBlock() {
		super(Properties.of(Material.WOOD));
	}
}
