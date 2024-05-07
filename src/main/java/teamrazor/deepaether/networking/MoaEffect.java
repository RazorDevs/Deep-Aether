package teamrazor.deepaether.networking;

import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

public interface MoaEffect extends INBTSynchable<CompoundTag> {

    Moa getMoa();

    static LazyOptional<MoaEffect> get(Moa moa) {
        return moa.getCapability(DACapabilities.MOA_CAPABILITY);
    }
    int getMoaEffectAmplifier();

    void setMoaEffectAmplifier(int var1);
}
