package net.pravekypetr.wh.items;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.creativeTabs.ModCreativeTab;
import net.minecraft.world.item.Item;

public class ModMetalItems {
    public static final DeferredRegister<Item> METALITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WH.MOD_ID);

    // ITEMS
    public static final RegistryObject<Item> HOT_STEEL_INGOT = METALITEMS.register("hot_steel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.METAL_TAB)));



    // Register method
    public static void register(IEventBus eventBus) {
        METALITEMS.register(eventBus);
    }
}
