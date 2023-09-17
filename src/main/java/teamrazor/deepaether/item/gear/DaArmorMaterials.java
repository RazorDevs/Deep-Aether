package teamrazor.deepaether.item.gear;

import com.aetherteam.aether.client.AetherSoundEvents;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamrazor.deepaether.datagen.tags.DATags;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum DaArmorMaterials implements StringRepresentable, ArmorMaterial {
    STRATUS("stratus", 37, Util.make(new EnumMap<>(EquipmentSlot.class), map -> {
        map.put(EquipmentSlot.FEET, 3);
        map.put(EquipmentSlot.LEGS, 5);
        map.put(EquipmentSlot.CHEST, 7);
        map.put(EquipmentSlot.HEAD, 3);
    }), 15, AetherSoundEvents.ITEM_ARMOR_EQUIP_GRAVITITE, 3.0F, 0.15F, () -> Ingredient.of(DATags.Items.STRATUS_REPAIRING)),

    SKYJADE("skyjade", 3, Util.make(new EnumMap<>(EquipmentSlot.class), map -> {
        map.put(EquipmentSlot.FEET, 3);
        map.put(EquipmentSlot.LEGS, 6);
        map.put(EquipmentSlot.CHEST, 8);
        map.put(EquipmentSlot.HEAD, 3);
    }), 0, AetherSoundEvents.ITEM_ARMOR_EQUIP_ZANITE, 0.0F, 0.0F, () -> Ingredient.of(DATags.Items.SKYJADE_REPAIRING));

    private static final EnumMap<EquipmentSlot, Integer> DURABILITY_MAP = Util.make(new EnumMap<>(EquipmentSlot.class), map -> {
        map.put(EquipmentSlot.FEET, 13);
        map.put(EquipmentSlot.LEGS, 15);
        map.put(EquipmentSlot.CHEST, 16);
        map.put(EquipmentSlot.HEAD, 11);
    });

    private final String name;
    private final int maxDamageFactor;
    private final EnumMap<EquipmentSlot, Integer> protectionAmountMap;
    private final int enchantability;
    private final Supplier<SoundEvent> soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;



    DaArmorMaterials(String name, int maxDamageFactor, EnumMap<EquipmentSlot, Integer> protectionAmountMap, int enchantability, Supplier<SoundEvent> soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.protectionAmountMap = protectionAmountMap;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return DURABILITY_MAP.get(slot) * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.protectionAmountMap.get(slot);
    }


    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent.get();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
    public String getSerializedName() {
        return this.name;
    }
}
