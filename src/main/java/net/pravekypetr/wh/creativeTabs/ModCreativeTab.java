package net.pravekypetr.wh.creativeTabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.pravekypetr.wh.items.ModOreItems;

public class ModCreativeTab {
    public static final CreativeModeTab ORE_TAB = new CreativeModeTab("ore_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModOreItems.WARPSTONE.get());
        }
    };
    public static final CreativeModeTab SKAVEN_TECHNOLOGY = new CreativeModeTab("skaven_technology") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModOreItems.WARPSTONE.get());
        }
    };
}
