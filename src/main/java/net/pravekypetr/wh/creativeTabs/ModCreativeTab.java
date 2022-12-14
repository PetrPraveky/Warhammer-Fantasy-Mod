package net.pravekypetr.wh.creativeTabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.pravekypetr.wh.blocks.ModStationBlocks;
import net.pravekypetr.wh.items.ModMetalItems;
import net.pravekypetr.wh.items.ModOreItems;
import net.pravekypetr.wh.items.ModTools;
import net.pravekypetr.wh.items.ModWeapons;
import software.bernie.example.GeckoLibMod;

public class ModCreativeTab {
    // OLD WORLD ITEMS
    public static final CreativeModeTab ORE_TAB = new CreativeModeTab("ore_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModOreItems.WARPSTONE.get());
        }
    };
    public static final CreativeModeTab METAL_TAB = new CreativeModeTab("metal_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModMetalItems.STEEL_INGOT.get());
        }
    };
    public static final CreativeModeTab ITEM_TAB = new CreativeModeTab("item_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModOreItems.WARPSTONE.get());
        }
    };
    public static final CreativeModeTab FLUID_TAB = new CreativeModeTab("fluid_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModTools.WARPSTONE_SLUDGE_BUCKET.get());
        }
    };
    public static final CreativeModeTab WEAPON_TAB = new CreativeModeTab("weapon_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModWeapons.IRON_WARHAMMER.get());
        }
    };


    // SKAVEN
    public static final CreativeModeTab SKAVEN_TECHNOLOGY = new CreativeModeTab("skaven_technology") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModStationBlocks.SKAVEN_BLAST_FURNACE.get().asItem());
        }
    };
}
