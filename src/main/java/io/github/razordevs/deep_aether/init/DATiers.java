package io.github.razordevs.deep_aether.init;

import io.github.razordevs.deep_aether.datagen.tags.DATags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum DATiers implements Tier {
    SKYJADE(BlockTags.INCORRECT_FOR_GOLD_TOOL, 150, 10.0F, 2.0F, 0, () -> Ingredient.of(DATags.Items.SKYJADE_REPAIRING)),
    STRATUS(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2031, 9.0F, 4.0F, 15,  () -> Ingredient.of(DATags.Items.STRATUS_REPAIRING)),
    FIRE(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 500, 0, 2.0F, 0, () -> Ingredient.of(Items.FIRE_CHARGE)),
    STORM(BlockTags.INCORRECT_FOR_IRON_TOOL, 502, 8.0F, 3.0F, 10, () -> Ingredient.of(DATags.Items.STORM_REPAIRING));

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    DATiers(TagKey<Block> incorrectBlocksForDrops, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
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
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return incorrectBlocksForDrops;
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
