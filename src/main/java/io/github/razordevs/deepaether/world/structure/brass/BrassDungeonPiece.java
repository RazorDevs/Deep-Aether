package io.github.razordevs.deepaether.deepaether.world.structure.brass;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.world.structurepiece.AetherTemplateStructurePiece;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import teamrazor.deepaether.DeepAether;
import io.github.razordevs.deepaether.deepaether.block.misc.TrappedSkyrootBlock;
import io.github.razordevs.deepaether.deepaether.init.DABlocks;

import java.util.function.Function;

public class BrassDungeonPiece extends AetherTemplateStructurePiece {
    protected static final RuleProcessor LOCKED_NIMBUS_STONE = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_NIMBUS_STONE.get(), 0.1F), AlwaysTrueTest.INSTANCE, DABlocks.LOCKED_LIGHT_NIMBUS_STONE.get().defaultBlockState())
    ));

    protected static final RuleProcessor TRAPPED_NIMBUS_STONE = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_NIMBUS_STONE.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_NIMBUS_STONE.get().defaultBlockState()),
            new ProcessorRule(new RandomBlockMatchTest(DABlocks.LOCKED_LIGHT_NIMBUS_PILLAR.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_LIGHT_NIMBUS_STONE.get().defaultBlockState())
    ));

    protected static final RuleProcessor TRAPPED_SKYROOT_PLANKS_NORMAL = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(AetherBlocks.SKYROOT_PLANKS.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_SKYROOT_PLANKS.get().defaultBlockState())
    ));

    protected static final RuleProcessor TRAPPED_SKYROOT_PLANKS_PLANT = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(AetherBlocks.SKYROOT_PLANKS.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_SKYROOT_PLANKS.get().defaultBlockState().setValue(TrappedSkyrootBlock.TRAPPED_MOB_TYPE, 1))
    ));

    protected static final RuleProcessor TRAPPED_SKYROOT_PLANKS_COCKATRICE = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new RandomBlockMatchTest(AetherBlocks.SKYROOT_PLANKS.get(), 0.05F), AlwaysTrueTest.INSTANCE, DABlocks.TRAPPED_SKYROOT_PLANKS.get().defaultBlockState().setValue(TrappedSkyrootBlock.TRAPPED_MOB_TYPE, 2))
    ));
    public BrassDungeonPiece(StructurePieceType type, StructureTemplateManager manager, String name, StructurePlaceSettings settings, BlockPos pos) {
        super(type, manager, makeLocation(name), settings, pos);
    }

    public BrassDungeonPiece(StructurePieceType type, CompoundTag tag, StructureTemplateManager manager, Function<ResourceLocation, StructurePlaceSettings> settingsFactory) {
        super(type, tag, manager, settingsFactory);
    }

    protected static ResourceLocation makeLocation(String name) {
        return new ResourceLocation(DeepAether.MODID, "brass_dungeon/" + name);
    }
}