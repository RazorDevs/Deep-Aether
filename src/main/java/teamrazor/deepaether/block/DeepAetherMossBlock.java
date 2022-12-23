package teamrazor.deepaether.block;

import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MossBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.world.feature.DeepAetherModConfiguredFeatures;

public class DeepAetherMossBlock extends Block /*implements BonemealableBlock*/ {
    public DeepAetherMossBlock(BlockBehaviour.Properties p_153790_) {
        super(p_153790_);
    }
/*
    @Override
    public boolean isValidBonemealTarget(LevelReader p_256559_, BlockPos p_50898_, BlockState p_50899_, boolean p_50900_) {
        return p_256559_.getBlockState(p_50898_.above()).isAir();
    }

    public boolean isBonemealSuccess(Level p_221538_, RandomSource p_221539_, BlockPos p_221540_, BlockState p_221541_) {
        return true;
    }
}

    public void performBonemeal(ServerLevel p_221533_, RandomSource p_221534_, BlockPos p_221535_, BlockState p_221536_) {
        DeepAetherModConfiguredFeatures.AETHER_MOSS_PATCH_BONEMEAL.value().place(p_221533_, p_221533_.getChunkSource().getGenerator(), p_221534_, p_221535_.above());
    }*/
}