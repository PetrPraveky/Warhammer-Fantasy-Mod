package net.pravekypetr.wh.items;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.creativeTabs.ModCreativeTab;
import net.minecraft.world.item.Item;

public class ModOreItems {
    public static final DeferredRegister<Item> OREITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WH.MOD_ID);

    // ITEMS
    public static final RegistryObject<Item> WARPSTONE = OREITEMS.register("warpstone", () -> new Item(new Item.Properties().tab(ModCreativeTab.ORE_TAB)));



    // Register method
    public static void register(IEventBus eventBus) {
        OREITEMS.register(eventBus);
    }
}
