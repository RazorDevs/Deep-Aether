package teamrazor.deepaether.datagen.tags;

import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DASounds;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class DASoundTagData extends TagsProvider<SoundEvent> {

    public DASoundTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, Registries.SOUND_EVENT, registries, DeepAether.MODID, helper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether Sound Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(AetherTags.SoundEvents.BOSS_MUSIC).add(
                DASounds.MUSIC_BOSS_EOTS.getKey()
        );
    }
}
