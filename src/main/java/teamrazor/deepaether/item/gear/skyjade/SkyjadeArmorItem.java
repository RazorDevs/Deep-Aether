
package teamrazor.deepaether.item.gear.skyjade;


import teamrazor.deepaether.init.DAItems;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

public abstract class SkyjadeArmorItem extends ArmorItem {
	public SkyjadeArmorItem(EquipmentSlot slot, Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForSlot(EquipmentSlot slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
			}

			@Override
			public int getDefenseForSlot(EquipmentSlot slot) {
				return new int[]{2, 5, 6, 2}[slot.getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 24;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(DAItems.SKYJADE.get()));
			}

			@Override
			public String getName() {
				return "skyjade_armor";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		}, slot, properties);
	}

	public static class Helmet extends SkyjadeArmorItem {
		public Helmet() {
			super(EquipmentSlot.HEAD, new Properties());
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "deep_aether:textures/models/armor/skyjade__layer_1.png";
		}
	}

	public static class Chestplate extends SkyjadeArmorItem {
		public Chestplate() {
			super(EquipmentSlot.CHEST, new Properties());
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "deep_aether:textures/models/armor/skyjade__layer_1.png";
		}
	}

	public static class Leggings extends SkyjadeArmorItem {
		public Leggings() {
			super(EquipmentSlot.LEGS, new Properties());
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "deep_aether:textures/models/armor/skyjade__layer_2.png";
		}
	}

	public static class Boots extends SkyjadeArmorItem {
		public Boots() {
			super(EquipmentSlot.FEET, new Properties());
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "deep_aether:textures/models/armor/skyjade__layer_1.png";
		}
	}
}
