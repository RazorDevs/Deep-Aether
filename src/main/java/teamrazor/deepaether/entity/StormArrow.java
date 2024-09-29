package teamrazor.deepaether.entity;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.PassiveWhirlwind;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import teamrazor.deepaether.init.DAEntities;

public class StormArrow extends AbstractArrow {


    public StormArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, new ItemStack(Items.ARROW));
    }

    public StormArrow(double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack) {
        super(DAEntities.STORM_ARROW.get(), pX, pY, pZ, pLevel, pPickupItemStack);
    }

    public StormArrow(LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack) {
        super(DAEntities.STORM_ARROW.get(), pOwner, pLevel, pPickupItemStack);
    }

    @Override
    protected void onHitBlock(BlockHitResult hit) {
        super.onHitBlock(hit);
        PassiveWhirlwind whirlwind = new PassiveWhirlwind(AetherEntityTypes.WHIRLWIND.get(), this.level());
        whirlwind.setPos(this.position());
        whirlwind.setLifeLeft(this.level().getRandom().nextInt(512) + 512);
        this.level().addFreshEntity(whirlwind);
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        PassiveWhirlwind whirlwind = new PassiveWhirlwind(AetherEntityTypes.WHIRLWIND.get(), this.level());
        whirlwind.setPos(this.position());
        whirlwind.setLifeLeft(this.level().getRandom().nextInt(512) + 512);
        this.level().addFreshEntity(whirlwind);
        this.discard();
    }
}
