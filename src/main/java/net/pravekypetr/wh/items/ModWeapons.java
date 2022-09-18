package net.pravekypetr.wh.items;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.itemInit.Battleaxe;
import net.pravekypetr.wh.itemInit.Dagger;
import net.pravekypetr.wh.itemInit.Halbert;
import net.pravekypetr.wh.itemInit.Spear;
import net.pravekypetr.wh.itemInit.Warhammer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

public class ModWeapons {
    public static final DeferredRegister<Item> WEAPONS = DeferredRegister.create(ForgeRegistries.ITEMS, WH.MOD_ID);

    // DAGGERS
    public static final RegistryObject<Item> WOODEN_DAGGER = WEAPONS.register("wooden_dagger", () -> new Dagger(Tiers.WOOD, 2, -1.5f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> STONE_DAGGER = WEAPONS.register("stone_dagger", () -> new Dagger(Tiers.STONE, 2, -1.5f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> IRON_DAGGER = WEAPONS.register("iron_dagger", () -> new Dagger(Tiers.IRON, 2, -1.5f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> GOLDEN_DAGGER = WEAPONS.register("golden_dagger", () -> new Dagger(Tiers.GOLD, 2, -1.5f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> DIAMOND_DAGGER = WEAPONS.register("diamond_dagger", () -> new Dagger(Tiers.DIAMOND, 2, -1.5f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_DAGGER = WEAPONS.register("netherite_dagger", () -> new Dagger(Tiers.NETHERITE, 2, -1.5f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    // SPEARS
    public static final RegistryObject<Item> WOODEN_SPEAR = WEAPONS.register("wooden_spear", () -> new Spear(Tiers.WOOD, 2, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> STONE_SPEAR = WEAPONS.register("stone_spear", () -> new Spear(Tiers.STONE, 2, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> IRON_SPEAR = WEAPONS.register("iron_spear", () -> new Spear(Tiers.IRON, 2, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> GOLDEN_SPEAR = WEAPONS.register("golden_spear", () -> new Spear(Tiers.GOLD, 2, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> DIAMOND_SPEAR = WEAPONS.register("diamond_spear", () -> new Spear(Tiers.DIAMOND, 2, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_SPEAR = WEAPONS.register("netherite_spear", () -> new Spear(Tiers.NETHERITE, 2, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    // HALBERT
    public static final RegistryObject<Item> WOODEN_HALBERT = WEAPONS.register("wooden_halbert", () -> new Halbert(Tiers.WOOD, 3, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> STONE_HALBERT = WEAPONS.register("stone_halbert", () -> new Halbert(Tiers.STONE, 3, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> IRON_HALBERT = WEAPONS.register("iron_halbert", () -> new Halbert(Tiers.IRON, 3, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> GOLDEN_HALBERT = WEAPONS.register("golden_halbert", () -> new Halbert(Tiers.GOLD, 3, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> DIAMOND_HALBERT = WEAPONS.register("diamond_halbert", () -> new Halbert(Tiers.DIAMOND, 3, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_HALBERT = WEAPONS.register("netherite_halbert", () -> new Halbert(Tiers.NETHERITE, 3, -2f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    // ONE-HANDED WARHAMMER
    public static final RegistryObject<Item> ONE_HANDED_WOODEN_WARHAMMER = WEAPONS.register("wooden_one_handed_warhammer", () -> new Warhammer(Tiers.WOOD, 3, -3.2f, 30, 2, false, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> ONE_HANDED_STONE_WARHAMMER = WEAPONS.register("stone_one_handed_warhammer", () -> new Warhammer(Tiers.STONE, 3, -3.2f, 30, 2, false, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> ONE_HANDED_IRON_WARHAMMER = WEAPONS.register("iron_one_handed_warhammer", () -> new Warhammer(Tiers.IRON, 3, -3.2f, 30, 2, false, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> ONE_HANDED_GOLDEN_WARHAMMER = WEAPONS.register("golden_one_handed_warhammer", () -> new Warhammer(Tiers.GOLD, 3, -3.2f, 30, 2, false, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> ONE_HANDED_DIAMOND_WARHAMMER = WEAPONS.register("diamond_one_handed_warhammer", () -> new Warhammer(Tiers.DIAMOND, 3, -3.2f, 30, 2, false, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> ONE_HANDED_NETHERITE_WARHAMMER = WEAPONS.register("netherite_one_handed_warhammer", () -> new Warhammer(Tiers.NETHERITE, 3, -3.2f, 30, 2, false, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    // TWO-HANDED WARHAMMER
    public static final RegistryObject<Item> WOODEN_WARHAMMER = WEAPONS.register("wooden_warhammer", () -> new Warhammer(Tiers.WOOD, 6, -3.2f, 40, 3, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> STONE_WARHAMMER = WEAPONS.register("stone_warhammer", () -> new Warhammer(Tiers.STONE, 6, -3.2f, 40, 3, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> IRON_WARHAMMER = WEAPONS.register("iron_warhammer", () -> new Warhammer(Tiers.IRON, 6, -3.2f, 40, 3, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> GOLDEN_WARHAMMER = WEAPONS.register("golden_warhammer", () -> new Warhammer(Tiers.GOLD, 6, -3.2f, 40, 3, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> DIAMOND_WARHAMMER = WEAPONS.register("diamond_warhammer", () -> new Warhammer(Tiers.DIAMOND, 6, -3.2f, 40, 3, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_WARHAMMER = WEAPONS.register("netherite_warhammer", () -> new Warhammer(Tiers.NETHERITE, 6, -3.2f, 40, 3, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    // TWO-HANDED BATTLEAXE
    public static final RegistryObject<Item> WOODEN_BATTLEAXE = WEAPONS.register("wooden_battleaxe", () -> new Battleaxe(Tiers.WOOD, 8, -2.2f, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> STONE_BATTLEAXE = WEAPONS.register("stone_battleaxe", () -> new Battleaxe(Tiers.STONE, 8, -2.2f, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> IRON_BATTLEAXE = WEAPONS.register("iron_battleaxe", () -> new Battleaxe(Tiers.IRON, 8, -2.2f, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> GOLDEN_BATTLEAXE = WEAPONS.register("golden_battleaxe", () -> new Battleaxe(Tiers.GOLD, 8, -2.2f, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> DIAMOND_BATTLEAXE = WEAPONS.register("diamond_battleaxe", () -> new Battleaxe(Tiers.DIAMOND, 8, -2.2f, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_BATTLEAXE = WEAPONS.register("netherite_battleaxe", () -> new Battleaxe(Tiers.NETHERITE, 8, -2.2f, true, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    
    // VANILLA

    // Register method
    public static void register(IEventBus eventBus) {
        WEAPONS.register(eventBus);
    }
}
