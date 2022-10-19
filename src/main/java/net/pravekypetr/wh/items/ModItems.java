package net.pravekypetr.wh.items;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh._ModItemQualities;
import net.pravekypetr.wh.creativeTabs.ModCreativeTab;
import net.minecraft.world.item.Item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import javax.annotation.Nullable;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WH.MOD_ID);

    // ITEMS
    public static final RegistryObject<Item> RAT_HIDE = ITEMS.register("rat_hide", () -> new Item(new Item.Properties().tab(ModCreativeTab.ITEM_TAB)) {
        // Hover tooltip
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.literal("Common").withStyle(_ModItemQualities.COMMON));
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable("wh.info.rat_hide").withStyle(ChatFormatting.WHITE));
            } else {
                components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, components, flag);
        }
    });
    public static final RegistryObject<Item> LIME_WET_RAT_HIDE = ITEMS.register("lime_wet_rat_hide", () -> new Item(new Item.Properties().tab(ModCreativeTab.ITEM_TAB)) {
        // Hover tooltip
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.literal("Uncommon").withStyle(_ModItemQualities.UNCOMMON));
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable("wh.info.lime_wet_rat_hide").withStyle(ChatFormatting.WHITE));
            } else {
                components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, components, flag);
        }
    });
    public static final RegistryObject<Item> LIME_WET_RAT_LEATHER = ITEMS.register("lime_wet_rat_leather", () -> new Item(new Item.Properties().tab(ModCreativeTab.ITEM_TAB)) {
        // Hover tooltip
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.literal("Uncommon").withStyle(_ModItemQualities.UNCOMMON));
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable("wh.info.lime_wet_rat_leather").withStyle(ChatFormatting.WHITE));
            } else {
                components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, components, flag);
        }
    });
    public static final RegistryObject<Item> RAT_FUR = ITEMS.register("rat_fur", () -> new Item(new Item.Properties().tab(ModCreativeTab.ITEM_TAB)) {
        // Hover tooltip
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.literal("Uncommon").withStyle(_ModItemQualities.UNCOMMON));
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable("wh.info.rat_fur").withStyle(ChatFormatting.WHITE));
            } else {
                components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, components, flag);
        }
    });
    public static final RegistryObject<Item> TOUGH_RAT_LEATHER = ITEMS.register("tough_rat_leather", () -> new Item(new Item.Properties().tab(ModCreativeTab.ITEM_TAB)) {
        // Hover tooltip
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.literal("Uncommon").withStyle(_ModItemQualities.UNCOMMON));
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable("wh.info.tough_rat_leather").withStyle(ChatFormatting.WHITE));
            } else {
                components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, components, flag);
        }
    });
    public static final RegistryObject<Item> TANNED_RAT_LEATHER_B = ITEMS.register("tanned_rat_leather_b", () -> new Item(new Item.Properties().tab(ModCreativeTab.ITEM_TAB)) {
        // Hover tooltip
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.literal("Uncommon").withStyle(_ModItemQualities.UNCOMMON));
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable("wh.info.tanned_rat_leather_b").withStyle(ChatFormatting.WHITE));
            } else {
                components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, components, flag);
        }
    });
    public static final RegistryObject<Item> TANNED_RAT_LEATHER_W = ITEMS.register("tanned_rat_leather_w", () -> new Item(new Item.Properties().tab(ModCreativeTab.ITEM_TAB)) {
        // Hover tooltip
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.literal("Uncommon").withStyle(_ModItemQualities.UNCOMMON));
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable("wh.info.tanned_rat_leather_w").withStyle(ChatFormatting.WHITE));
            } else {
                components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, components, flag);
        }
    });
    public static final RegistryObject<Item> LIGHT_RAT_LEATHER = ITEMS.register("light_rat_leather", () -> new Item(new Item.Properties().tab(ModCreativeTab.ITEM_TAB)) {
        // Hover tooltip
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.literal("Uncommon").withStyle(_ModItemQualities.UNCOMMON));
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable("wh.info.light_rat_leather").withStyle(ChatFormatting.WHITE));
            } else {
                components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, components, flag);
        }
    });
    


    // Register method
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
