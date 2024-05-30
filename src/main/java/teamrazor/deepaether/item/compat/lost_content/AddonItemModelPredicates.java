package teamrazor.deepaether.item.compat.lost_content;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import teamrazor.deepaether.init.DAItems;

public class AddonItemModelPredicates {

    public static void init()
    {
        createActivePredicate(DAItems.SKYJADE_SHIELD.get(), "blocking");
        createActivePredicate(DAItems.STRATUS_SHIELD.get(), "blocking");
    }


    /**
     * Activates when the mainhand is active (EX: blocking with a shield, eating a
     * food item)
     */
    private static void createActivePredicate(Item item, String predicateName)
    {
        ItemProperties.register(item, new ResourceLocation(predicateName), (stack, world, entity, value) -> (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) ? 1.0F : 0.0F);
    }
}
