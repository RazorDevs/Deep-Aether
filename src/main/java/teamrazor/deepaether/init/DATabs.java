

package teamrazor.deepaether.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DATabs {
    public static final CreativeModeTab TAB_DEEP_AETHER_BLOCKS_TAB = new CreativeModeTab("deep_aether_blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(DABlocks.FLOWERING_ROSEROOT_LEAVES.get());
        }
    };
    public static final CreativeModeTab TAB_DEEP_AETHER_ITEMS_TAB = new CreativeModeTab("deep_aether_items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(DAItems.SKYJADE_SWORD.get());
        }
    };
}
