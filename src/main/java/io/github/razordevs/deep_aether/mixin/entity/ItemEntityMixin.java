package io.github.razordevs.deep_aether.mixin.entity;

import io.github.razordevs.deep_aether.advancement.DAAdvancementTriggers;
import io.github.razordevs.deep_aether.entity.PoisonItem;
import io.github.razordevs.deep_aether.recipe.DARecipeTypes;
import io.github.razordevs.deep_aether.recipe.poison.PoisonRecipe;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements PoisonItem {

    @Shadow public abstract ItemStack getItem();

    @Shadow @Nullable public abstract Entity getOwner();

    @Unique
    private int poisonTime = 0;

    @Unique
    private boolean isConverting = false;

    @Unique
    private ItemStack resultItem = null;

    public ItemEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V"), method = "tick")
    public void tick(CallbackInfo ci) {
        if (isConverting) {
            isConverting = false;
            if (poisonTime > 100) {

                //Grants the "Purple Magic" advancement.
                if (this.getOwner() instanceof ServerPlayer player) {
                    DAAdvancementTriggers.POISON_TRIGGER.get().trigger(player, this.getItem());
                }

                this.spawnAtLocation(resultItem, 0);
                this.discard();
            }
        }
        else {
            poisonTime = 0;
        }
    }

    @Unique
    @Override
    public void deep_Aether$increaseTime() {
        if(resultItem == null) {
            for (RecipeHolder<PoisonRecipe> recipe : this.level().getRecipeManager().getAllRecipesFor(DARecipeTypes.POISON_RECIPE.get())) {
                if (recipe.value().getIngredients().getFirst().getItems()[0].getItem() == this.getItem().getItem()) {
                    resultItem = new ItemStack(recipe.value().getResult().getItem(), this.getItem().getCount());
                }
            }

            if(resultItem == null) {
                resultItem = ItemStack.EMPTY;
            }
        }
        else if(!resultItem.isEmpty()) {
            this.poisonTime++;
            this.isConverting = true;
        }
    }

    @Unique
    @Override
    public boolean deep_Aether$canConvert() {
        return this.resultItem != null && !this.resultItem.isEmpty();
    }
}
