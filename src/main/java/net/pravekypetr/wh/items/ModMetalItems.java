package net.pravekypetr.wh.items;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh._ModItemQualities;
import net.pravekypetr.wh.creativeTabs.ModCreativeTab;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import javax.annotation.Nullable;

public class ModMetalItems {
    public static final DeferredRegister<Item> METALITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WH.MOD_ID);

    // ITEMS
    public static final RegistryObject<Item> HOT_STEEL_INGOT = METALITEMS.register("hot_steel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.METAL_TAB)) {
        // Hover tooltip
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.literal("Uncommon").withStyle(_ModItemQualities.UNCOMMON));
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable("wh.info.hot_steel_ingot").withStyle(ChatFormatting.WHITE));
            } else {
                components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, components, flag);
        }
    });
    public static final RegistryObject<Item> STEEL_INGOT = METALITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.METAL_TAB)) {
        // Hover tooltip
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.literal("Uncommon").withStyle(_ModItemQualities.UNCOMMON));
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable("wh.info.steel_ingot").withStyle(ChatFormatting.WHITE));
            } else {
                components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
            }
            super.appendHoverText(stack, level, components, flag);
        }
    });



    // Register method
    public static void register(IEventBus eventBus) {
        METALITEMS.register(eventBus);
    }
}
