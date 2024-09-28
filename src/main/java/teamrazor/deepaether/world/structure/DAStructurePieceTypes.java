package teamrazor.deepaether.world.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.world.structure.brass.DefaultBrassRoom;
import teamrazor.deepaether.world.structure.brass.GardenBrassRoom;
import teamrazor.deepaether.world.structure.brass.InfestedBrassRoom;

import java.util.Locale;

public class DAStructurePieceTypes {
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, DeepAether.MODID);

    //BRASS DUNGEON
    public static final DeferredHolder<StructurePieceType, StructurePieceType> BRASS_BOSS_ROOM = register("BBossRoom", DefaultBrassRoom.BossRoom::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> BRASS_ROOM = register("BRoom", DefaultBrassRoom::new);

    public static final DeferredHolder<StructurePieceType, StructurePieceType> GARDEN_BRASS_BOSS_ROOM = register("GBBossRoom", GardenBrassRoom.BossRoom::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> GARDEN_BRASS_ROOM = register("GBRoom", GardenBrassRoom::new);

    public static final DeferredHolder<StructurePieceType, StructurePieceType> INFESTED_BRASS_BOSS_ROOM = register("IBBossRoom", InfestedBrassRoom.BossRoom::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> INFESTED_BRASS_ROOM = register("IBRoom", InfestedBrassRoom::new);

    private static DeferredHolder<StructurePieceType, StructurePieceType> register(String name, StructurePieceType structurePieceType) {
        return STRUCTURE_PIECE_TYPES.register(name.toLowerCase(Locale.ROOT), () -> structurePieceType);
    }
}
