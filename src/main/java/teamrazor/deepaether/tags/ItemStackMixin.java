package teamrazor.deepaether.tags;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    /*@ModifyVariable(method = "getAttributeModifiers", at = @At(value = "INVOKE",  target = "Lnet/minecraftforge/common/ForgeHooks;getAttributeModifiers(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;Lcom/google/common/collect/Multimap;)Lcom/google/common/collect/Multimap;", shift = At.Shift.BEFORE))
    private Multimap<Attribute, AttributeModifier> getAttributeModifiers(Multimap<Attribute, AttributeModifier> map, EquipmentSlot slot) {
        ItemStack itemStack = (ItemStack) (Object) this;
        map = SkyjadeWeapon.increaseDamage(map, itemStack, slot);
        return map;
    }*/
}