package teamrazor.deepaether.init;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import teamrazor.deepaether.datagen.tags.DATags;

import java.util.function.Supplier;

public enum DATiers implements Tier {
    SKYJADE(2, 150, 10.0F, 2.0F, 0, () -> Ingredient.of(DATags.Items.SKYJADE_REPAIRING)),
    STRATUS(4, 2031, 9.0F, 4.0F, 15,  () -> Ingredient.of(DATags.Items.STRATUS_REPAIRING)),
    LUCK(2, 500, 9.0F, 1.0F, 15,  () -> Ingredient.of(DATags.Items.STRATUS_REPAIRING)),
    FIRE(0, 500, 0, 2.0F, 0, () -> Ingredient.of(Items.FIRE_CHARGE)),
    STORM(3, 502, 8.0F, 3.0F, 10, () -> Ingredient.of(DATags.Items.STORM_REPAIRING));


    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    DATiers(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }
}
