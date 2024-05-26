package teamrazor.deepaether.item.moa_food;

import com.aetherteam.aether.entity.passive.Moa;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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

        if(applyMoaEffect(livingEntity)) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pPlayer.isPassenger())
            if(pPlayer.getVehicle() instanceof Moa moa){
                if(!pPlayer.isCreative())
                    pPlayer.getItemInHand(pUsedHand).shrink(1);
                applyMoaEffect(moa);
                return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
            }
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public boolean isEdible() {
        return false;
    }

    private boolean applyMoaEffect(LivingEntity livingEntity) {
        if(livingEntity.addEffect(getMobEffect())) {
            livingEntity.level().playLocalSound(livingEntity, SoundEvents.PLAYER_BURP, SoundSource.AMBIENT, 1f, 0.2f);
            return true;
        }
        return false;
    }
}
