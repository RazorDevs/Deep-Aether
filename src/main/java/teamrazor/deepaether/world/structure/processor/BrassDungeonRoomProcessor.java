package teamrazor.deepaether.world.structure.processor;

import com.aetherteam.aether.world.processor.AetherStructureProcessors;
import com.aetherteam.aether.world.processor.BossRoomProcessor;
import com.aetherteam.nitrogen.entity.BossRoomTracker;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import teamrazor.deepaether.DeepAether;

import java.util.ArrayList;

public class BrassDungeonRoomProcessor  extends StructureProcessor {
    public static final BossRoomProcessor INSTANCE = new BossRoomProcessor();

    public static final Codec<BossRoomProcessor> CODEC = Codec.unit(BossRoomProcessor.INSTANCE);

    @Override
    public StructureTemplate.StructureEntityInfo processEntity(LevelReader level, BlockPos seedPos, StructureTemplate.StructureEntityInfo rawEntityInfo, StructureTemplate.StructureEntityInfo entityInfo, StructurePlaceSettings placementSettings, StructureTemplate template) {
        BossRoomTracker<?> tracker = new BossRoomTracker<>(null,
                entityInfo.pos,
                new AABB(seedPos.getX() - 8, seedPos.getY() -2, seedPos.getZ() - 8, seedPos.getX() + 9, seedPos.getY() + 32, seedPos.getZ() + 9),
                new ArrayList<>());
        entityInfo.nbt.put("Dungeon", tracker.addAdditionalSaveData());
        return super.processEntity(level, seedPos, rawEntityInfo, entityInfo, placementSettings, template);
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return AetherStructureProcessors.BOSS_ROOM.get();
    }
}