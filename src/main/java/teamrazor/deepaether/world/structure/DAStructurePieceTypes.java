package teamrazor.deepaether.world.structure;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.world.structurepiece.GlowstoneRuinedPortalPiece;
import com.aetherteam.aether.world.structurepiece.LargeAercloudChunk;
import com.aetherteam.aether.world.structurepiece.bronzedungeon.BronzeBossRoom;
import com.aetherteam.aether.world.structurepiece.bronzedungeon.BronzeDungeonRoom;
import com.aetherteam.aether.world.structurepiece.bronzedungeon.BronzeTunnel;
import com.aetherteam.aether.world.structurepiece.golddungeon.*;
import com.aetherteam.aether.world.structurepiece.silverdungeon.SilverBossRoom;
import com.aetherteam.aether.world.structurepiece.silverdungeon.SilverDungeonRoom;
import com.aetherteam.aether.world.structurepiece.silverdungeon.SilverFloorPiece;
import com.aetherteam.aether.world.structurepiece.silverdungeon.SilverTemplePiece;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.world.structure.brass.BrassBossRoom;

import java.util.Locale;

public class DAStructurePieceTypes {
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, DeepAether.MODID);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> BRASS_BOSS_ROOM = register("BBossRoom", BrassBossRoom::new);
    private static DeferredHolder<StructurePieceType, StructurePieceType> register(String name, StructurePieceType structurePieceType) {
        return STRUCTURE_PIECE_TYPES.register(name.toLowerCase(Locale.ROOT), () -> structurePieceType);
    }
}
