package teamrazor.deepaether.datagen.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.entity.AetherEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DAEntities;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class DAEntityTagData extends EntityTypeTagsProvider {
    public DAEntityTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, DeepAetherMod.MODID, helper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether EntityType Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
                DAEntities.QUAIL.get(),
                DAEntities.STEER.get()
        );
        tag(AetherTags.Entities.WHIRLWIND_UNAFFECTED).add(
                DAEntities.WIND_CHARGE.get()
        );

        tag(DATags.Entities.WIND_CHARGE_BLACKLIST).add(
                DAEntities.AERCLOUD_SWET.get(),
                DAEntities.EOTS.get(),
                DAEntities.EOTS_CLOUD.get()
        );
        tag(DATags.Entities.STERLING_AERCLOUD_BLACKLIST).add(
                AetherEntityTypes.AERWHALE.get(),
                AetherEntityTypes.EVIL_WHIRLWIND.get(),
                AetherEntityTypes.WHIRLWIND.get(),
                AetherEntityTypes.ZEPHYR.get(),
                AetherEntityTypes.ZEPHYR_SNOWBALL.get(),
                DAEntities.AERCLOUD_SWET.get()
        );
    }
}
