package io.github.razordevs.deep_aether.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.ArrayList;
import java.util.List;

public class StructureUtil {
    public static List<ResourceLocation> getAvailableStructureList(Level level) {
        List<ResourceLocation> structureList = new ArrayList<>();
        Registry<Structure> registry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
        registry.keySet().forEach(location -> {
            if (!structureList.contains(location)) {
                structureList.add(location);
            }
        });

        return structureList;
    }

    public static Pair<BlockPos, Holder<Structure>> findNearestMapStructure(ServerLevel serverLevel,
                                                                            HolderSet<Structure> structureHolderSet, BlockPos pos, int range, boolean findUnexplored) {
        ChunkGenerator generator = serverLevel.getChunkSource().getGenerator();
        Pair<BlockPos, Holder<Structure>> nearest = generator.findNearestMapStructure(serverLevel, structureHolderSet, pos, range, findUnexplored);
        if (nearest == null) return null;
        return nearest.getFirst().distManhattan(pos) <= 5000 ? nearest : null;
    }
}
