package teamrazor.deepaether.item.moa_food;

import com.aetherteam.aether.entity.passive.Moa;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FodderItem extends Item {

    private MobEffectInstance effect;

    public FodderItem(Properties properties, MobEffectInstance effect) {
        super(properties);
        this.effect = effect;
    }

    public MobEffectInstance getMobEffect(){
        return effect;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if(!(livingEntity instanceof Moa))
            return InteractionResult.FAIL;

        if(!player.isCreative())
            itemStack.shrink(1);

        if(applyMoaEffect(livingEntity))
            return InteractionResult.SUCCESS;

        return InteractionResult.PASS;
    }

    @Override
    public boolean isEdible() {
        return false;
    }

    private boolean applyMoaEffect(LivingEntity livingEntity) {
        return livingEntity.addEffect(getMobEffect());
    }
}
