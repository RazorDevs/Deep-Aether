package teamrazor.deepaether.tags;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import net.minecraft.world.entity.ai.attributes.Attribute;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    /*@ModifyVariable(method = "getAttributeModifiers", at = @At(value = "INVOKE",  target = "Lnet/minecraftforge/common/ForgeHooks;getAttributeModifiers(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;Lcom/google/common/collect/Multimap;)Lcom/google/common/collect/Multimap;", shift = At.Shift.BEFORE))
    private Multimap<Attribute, AttributeModifier> getAttributeModifiers(Multimap<Attribute, AttributeModifier> map, EquipmentSlot slot) {
        ItemStack itemStack = (ItemStack) (Object) this;
        map = SkyjadeWeapon.increaseDamage(map, itemStack, slot);
        return map;
    }*/
}