package io.github.razordevs.deep_aether.entity;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.PassiveWhirlwind;
import io.github.razordevs.deep_aether.init.DAEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class StormArrow extends AbstractArrow {
Arrow

    public StormArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public StormArrow(double pX, double pY, double pZ, Level pLevel, ItemStack projectileStack, ItemStack weaponStack) {
        super(DAEntities.STORM_ARROW.get(), pX, pY, pZ, pLevel, projectileStack, weaponStack);
    }

    public StormArrow(LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack, ItemStack weaponStack) {
        super(DAEntities.STORM_ARROW.get(), pOwner, pLevel, pPickupItemStack, weaponStack);
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
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.ARROW);
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
