package teamrazor.deepaether.init;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.enchantments.GlovesReachEnchantment;

public class DAEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, DeepAether.MODID);

    public static final RegistryObject<GlovesReachEnchantment> GLOVES_REACH = ENCHANTMENTS.register("gloves_reach", () ->
            new GlovesReachEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.create("GLOVES",
                    (item -> item instanceof GlovesItem)),
                    EquipmentSlot.MAINHAND));
}
