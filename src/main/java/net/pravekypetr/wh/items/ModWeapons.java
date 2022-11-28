package net.pravekypetr.wh.items;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.creativeTabs.ModCreativeTab;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Battleaxe;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Dagger;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Halbert;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Longsword;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Rapier;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Spear;
import net.pravekypetr.wh.itemInit.oldWorldWeapons.Warhammer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

public class ModWeapons {
    public static final DeferredRegister<Item> WEAPONS = DeferredRegister.create(ForgeRegistries.ITEMS, WH.MOD_ID);

    // DAGGERS
    public static final RegistryObject<Item> WOODEN_DAGGER = WEAPONS.register("wooden_dagger", () -> new Dagger(Tiers.WOOD, "Common", 2, -1.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> STONE_DAGGER = WEAPONS.register("stone_dagger", () -> new Dagger(Tiers.STONE, "Common", 2, -1.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> IRON_DAGGER = WEAPONS.register("iron_dagger", () -> new Dagger(Tiers.IRON, "Uncommon", 2, -1.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLDEN_DAGGER = WEAPONS.register("golden_dagger", () -> new Dagger(Tiers.GOLD, "Uncommon", 2, -1.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> DIAMOND_DAGGER = WEAPONS.register("diamond_dagger", () -> new Dagger(Tiers.DIAMOND, "Rare", 2, -1.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> NETHERITE_DAGGER = WEAPONS.register("netherite_dagger", () -> new Dagger(Tiers.NETHERITE, "Rare", 2, -1.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));

    // TWO-HANDED SWORD
    public static final RegistryObject<Item> WOODEN_LONGSWORD = WEAPONS.register("wooden_two_handed_sword", () -> new Longsword(Tiers.WOOD, "Common", 5, -2.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> STONE_LONGSWORD = WEAPONS.register("stone_two_handed_sword", () -> new Longsword(Tiers.STONE, "Common", 5, -2.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> IRON_LONGSWORD = WEAPONS.register("iron_two_handed_sword", () -> new Longsword(Tiers.IRON, "Uncommon", 5, -2.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLDEN_LONGSWORD = WEAPONS.register("golden_two_handed_sword", () -> new Longsword(Tiers.GOLD, "Uncommon", 5, -2.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> DIAMOND_LONGSWORD = WEAPONS.register("diamond_two_handed_sword", () -> new Longsword(Tiers.DIAMOND, "Rare", 5, -2.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> NETHERITE_LONGSWORD = WEAPONS.register("netherite_two_handed_sword", () -> new Longsword(Tiers.NETHERITE, "Rare", 5, -2.5f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));

    // SPEARS
    public static final RegistryObject<Item> WOODEN_SPEAR = WEAPONS.register("wooden_spear", () -> new Spear(Tiers.WOOD, "Common", 2, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> STONE_SPEAR = WEAPONS.register("stone_spear", () -> new Spear(Tiers.STONE, "Common", 2, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> IRON_SPEAR = WEAPONS.register("iron_spear", () -> new Spear(Tiers.IRON, "Uncommon", 2, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLDEN_SPEAR = WEAPONS.register("golden_spear", () -> new Spear(Tiers.GOLD, "Uncommon", 2, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> DIAMOND_SPEAR = WEAPONS.register("diamond_spear", () -> new Spear(Tiers.DIAMOND, "Rare", 2, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> NETHERITE_SPEAR = WEAPONS.register("netherite_spear", () -> new Spear(Tiers.NETHERITE, "Rare", 2, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));

    // HALBERT
    public static final RegistryObject<Item> WOODEN_HALBERT = WEAPONS.register("wooden_halbert", () -> new Halbert(Tiers.WOOD, "Common", 3, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> STONE_HALBERT = WEAPONS.register("stone_halbert", () -> new Halbert(Tiers.STONE, "Common", 3, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> IRON_HALBERT = WEAPONS.register("iron_halbert", () -> new Halbert(Tiers.IRON, "Uncommon", 3, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLDEN_HALBERT = WEAPONS.register("golden_halbert", () -> new Halbert(Tiers.GOLD, "Uncommon", 3, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> DIAMOND_HALBERT = WEAPONS.register("diamond_halbert", () -> new Halbert(Tiers.DIAMOND, "Rare", 3, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> NETHERITE_HALBERT = WEAPONS.register("netherite_halbert", () -> new Halbert(Tiers.NETHERITE, "Rare", 3, -2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));

    // ONE-HANDED WARHAMMER
    public static final RegistryObject<Item> ONE_HANDED_WOODEN_WARHAMMER = WEAPONS.register("wooden_one_handed_warhammer", () -> new Warhammer(Tiers.WOOD, "Common", 3, -3.2f, 30, 2, false, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> ONE_HANDED_STONE_WARHAMMER = WEAPONS.register("stone_one_handed_warhammer", () -> new Warhammer(Tiers.STONE, "Common", 3, -3.2f, 30, 2, false, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> ONE_HANDED_IRON_WARHAMMER = WEAPONS.register("iron_one_handed_warhammer", () -> new Warhammer(Tiers.IRON, "Uncommon", 3, -3.2f, 30, 2, false, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> ONE_HANDED_GOLDEN_WARHAMMER = WEAPONS.register("golden_one_handed_warhammer", () -> new Warhammer(Tiers.GOLD, "Uncommon", 3, -3.2f, 30, 2, false, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> ONE_HANDED_DIAMOND_WARHAMMER = WEAPONS.register("diamond_one_handed_warhammer", () -> new Warhammer(Tiers.DIAMOND, "Rare", 3, -3.2f, 30, 2, false, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> ONE_HANDED_NETHERITE_WARHAMMER = WEAPONS.register("netherite_one_handed_warhammer", () -> new Warhammer(Tiers.NETHERITE, "Rare", 3, -3.2f, 30, 2, false, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));

    // TWO-HANDED WARHAMMER
    public static final RegistryObject<Item> WOODEN_WARHAMMER = WEAPONS.register("wooden_warhammer", () -> new Warhammer(Tiers.WOOD, "Uncommon", 6, -3.2f, 40, 3, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> STONE_WARHAMMER = WEAPONS.register("stone_warhammer", () -> new Warhammer(Tiers.STONE, "Uncommon", 6, -3.2f, 40, 3, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> IRON_WARHAMMER = WEAPONS.register("iron_warhammer", () -> new Warhammer(Tiers.IRON, "Uncommon", 6, -3.2f, 40, 3, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLDEN_WARHAMMER = WEAPONS.register("golden_warhammer", () -> new Warhammer(Tiers.GOLD, "Uncommon", 6, -3.2f, 40, 3, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> DIAMOND_WARHAMMER = WEAPONS.register("diamond_warhammer", () -> new Warhammer(Tiers.DIAMOND, "Rare", 6, -3.2f, 40, 3, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> NETHERITE_WARHAMMER = WEAPONS.register("netherite_warhammer", () -> new Warhammer(Tiers.NETHERITE, "Rare", 6, -3.2f, 40, 3, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));

    // TWO-HANDED BATTLEAXE
    public static final RegistryObject<Item> WOODEN_BATTLEAXE = WEAPONS.register("wooden_battleaxe", () -> new Battleaxe(Tiers.WOOD, "Common", 8, -2.2f, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> STONE_BATTLEAXE = WEAPONS.register("stone_battleaxe", () -> new Battleaxe(Tiers.STONE, "Common", 8, -2.2f, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> IRON_BATTLEAXE = WEAPONS.register("iron_battleaxe", () -> new Battleaxe(Tiers.IRON, "Uncommon", 8, -2.2f, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLDEN_BATTLEAXE = WEAPONS.register("golden_battleaxe", () -> new Battleaxe(Tiers.GOLD, "Uncommon", 8, -2.2f, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> DIAMOND_BATTLEAXE = WEAPONS.register("diamond_battleaxe", () -> new Battleaxe(Tiers.DIAMOND, "Rare", 8, -2.2f, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> NETHERITE_BATTLEAXE = WEAPONS.register("netherite_battleaxe", () -> new Battleaxe(Tiers.NETHERITE, "Rare", 8, -2.2f, true, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));

    // RAPIER
    public static final RegistryObject<Item> WOODEN_RAPIER = WEAPONS.register("wooden_rapier", () -> new Rapier(Tiers.WOOD, "Common", 3, -1.2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> STONE_RAPIER = WEAPONS.register("stone_rapier", () -> new Rapier(Tiers.STONE, "Common", 3, -1.2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> IRON_RAPIER = WEAPONS.register("iron_rapier", () -> new Rapier(Tiers.IRON, "Uncommon", 3, -1.2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> GOLDEN_RAPIER = WEAPONS.register("golden_rapier", () -> new Rapier(Tiers.GOLD, "Uncommon", 3, -1.2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> DIAMOND_RAPIER = WEAPONS.register("diamond_rapier", () -> new Rapier(Tiers.DIAMOND, "Rare", 3, -1.2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));
    public static final RegistryObject<Item> NETHERITE_RAPIER = WEAPONS.register("netherite_rapier", () -> new Rapier(Tiers.NETHERITE, "Rare", 3, -1.2f, new Item.Properties().tab(ModCreativeTab.WEAPON_TAB)));

    // VANILLA

    // Register method
    public static void register(IEventBus eventBus) {
        WEAPONS.register(eventBus);
    }
}
