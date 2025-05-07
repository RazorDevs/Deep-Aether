package teamrazor.deepaether.world.structure.brass;

import com.aetherteam.aether.world.structurepiece.AetherTemplateStructurePiece;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DABlocks;

import java.util.function.Function;

public class BrassDungeonPiece extends AetherTemplateStructurePiece {
    protected static final RuleProcessor LOCKED_NIMBUS_STONE = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_NIMBUS_STONE.get(), 0.1F), AlwaysTrueTest.INSTANCE, DABlocks.LOCKED_LIGHT_NIMBUS_STONE.get().defaultBlockState())
    ));

    protected static final RuleProcessor TRAPPED_SKYROOT_PLANKS = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_SKYROOT_PLANKS.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_SKYROOT_PLANKS.get().defaultBlockState())
    ));

    protected static final RuleProcessor MOSS_CARPET = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.AETHER_MOSS_CARPET.get(), 0.2F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState())
    ));

    protected static final RuleProcessor FLOWERING_ROSEROOT_LEAVES = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.ROSEROOT_LEAVES.get(), 0.2F), AlwaysTrueTest.INSTANCE, DABlocks.FLOWERING_ROSEROOT_LEAVES.get().defaultBlockState())
    ));

    protected static final RuleProcessor COBWEB = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(Blocks.COBWEB, 0.5F), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState())
    ));

    public BrassDungeonPiece(StructurePieceType type, StructureTemplateManager manager, String name, StructurePlaceSettings settings, BlockPos pos) {
        super(type, manager, makeLocation(name), settings, pos);
    }

    public BrassDungeonPiece(StructurePieceType type, CompoundTag tag, StructureTemplateManager manager, Function<ResourceLocation, StructurePlaceSettings> settingsFactory) {
        super(type, tag, manager, settingsFactory);
    }

    protected static ResourceLocation makeLocation(String name) {
        return DeepAether.getResource( "brass_dungeon/" + name);
    }
}