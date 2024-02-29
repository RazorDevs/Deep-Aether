package teamrazor.deepaether.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.FluidType;
import teamrazor.deepaether.init.DABlocks;

import java.util.*;

public class DAFluidInteraction {
    private static final Map<FluidType, List<FluidInteractionRegistry.InteractionInformation>> INTERACTIONS = new HashMap<>();

    /**
     * Adds an interaction between a source and its surroundings.
     *
     * @param source the source of the interaction, this will be replaced if the interaction occurs
     * @param interaction the interaction data to check and perform
     */
    public static synchronized void addInteraction(FluidType source, FluidInteractionRegistry.InteractionInformation interaction)
    {
        INTERACTIONS.computeIfAbsent(source, s -> new ArrayList<>()).add(interaction);
    }

    public static boolean canInteract(Level level, BlockPos pos)
    {
        FluidState state = level.getFluidState(pos);
        for (Direction direction : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS)
        {
            BlockPos relativePos = pos.relative(direction.getOpposite());
            List<FluidInteractionRegistry.InteractionInformation> interactions = INTERACTIONS.getOrDefault(state.getFluidType(), Collections.emptyList());
            for (FluidInteractionRegistry.InteractionInformation interaction : interactions)
            {
                if (interaction.predicate().test(level, pos, relativePos, state))
                {
                    interaction.interaction().interact(level, pos, relativePos, state);
                    return true;
                }
            }
        }

        return false;
    }


    static
    {
        // Poison + Water = Aersmog
        addInteraction(DAFluidTypes.POISON_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.WATER_TYPE.get(),
                fluidState -> DABlocks.AERSMOG.get().defaultBlockState()
        ));

        // Poison + Lava = Crying Obsidian
        addInteraction(DAFluidTypes.POISON_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                ForgeMod.LAVA_TYPE.get(),
                fluidState -> Blocks.CRYING_OBSIDIAN.defaultBlockState()
        ));
    }

}
