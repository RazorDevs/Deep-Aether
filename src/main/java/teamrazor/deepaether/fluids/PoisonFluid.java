package teamrazor.deepaether.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import teamrazor.deepaether.datagen.tags.DATags;
import teamrazor.deepaether.init.DABlocks;

public abstract class PoisonFluid extends ForgeFlowingFluid {

    protected PoisonFluid(Properties properties) {
        super(properties);
    }

    private void fizz(LevelAccessor levelAccessor, BlockPos blockPos) {
        levelAccessor.levelEvent(1501, blockPos, 0);
    }

    protected void spreadTo(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Direction direction, FluidState fluidState) {
        if (direction == Direction.DOWN) {
            FluidState fluidstate = levelAccessor.getFluidState(blockPos);
            if (this.is(DATags.Fluids.POISON) && fluidstate.is(FluidTags.LAVA)) {
                if (blockState.getBlock() instanceof LiquidBlock) {
                    levelAccessor.setBlock(blockPos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(levelAccessor, blockPos, blockPos, Blocks.CRYING_OBSIDIAN.defaultBlockState()), 3);
                }

                this.fizz(levelAccessor, blockPos);
                return;
            }

            if (this.is(DATags.Fluids.POISON) && fluidstate.is(FluidTags.WATER)) {
                if (blockState.getBlock() instanceof LiquidBlock) {
                    levelAccessor.setBlock(blockPos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(levelAccessor, blockPos, blockPos, DABlocks.AERSMOG.get().defaultBlockState()), 3);
                }

                this.fizz(levelAccessor, blockPos);
                return;
            }
        }

        super.spreadTo(levelAccessor, blockPos, blockState, direction, fluidState);
    }

    public int getAmount(FluidState fluidState) {
        return fluidState.getValue(LEVEL);
    }

    public boolean isSource(FluidState fluidState) {
        return false;
    }

    public static class Flowing extends PoisonFluid {

        public Flowing(Properties properties) {
            super(properties);
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> fluidStateBuilder) {
            super.createFluidStateDefinition(fluidStateBuilder);
            fluidStateBuilder.add(LEVEL);
        }

        public int getAmount(FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }

        public boolean isSource(FluidState fluidState) {
            return false;
        }
    }

    public static class Source extends PoisonFluid {
        protected Source(Properties properties) {
            super(properties);
        }

        public int getAmount(FluidState fluidState) {
            return 8;
        }

        public boolean isSource(FluidState fluidState) {
            return true;
        }
    }
}
