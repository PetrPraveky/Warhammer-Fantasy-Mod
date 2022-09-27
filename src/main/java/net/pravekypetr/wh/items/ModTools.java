package net.pravekypetr.wh.items;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.creativeTabs.ModCreativeTab;
import net.pravekypetr.wh.fluid.ModFluids;

public class ModTools {
    public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(ForgeRegistries.ITEMS, WH.MOD_ID);

    public static final RegistryObject<Item> WARPSTONE_SLUDGE_BUCKET = TOOLS.register("warpstone_sludge_bucket", 
        () -> new BucketItem(ModFluids.SOURCE_WARPSTONE_SLUDGE, new Item.Properties().tab(ModCreativeTab.FLUID_TAB).stacksTo(1).craftRemainder(Items.BUCKET)));

    public static void register(IEventBus eventBus) {
        TOOLS.register(eventBus);
    }
}
