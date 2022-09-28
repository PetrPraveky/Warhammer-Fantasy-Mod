package net.pravekypetr.wh.items;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh._ModItemQualities;
import net.pravekypetr.wh.creativeTabs.ModCreativeTab;
import net.pravekypetr.wh.fluid.ModFluids;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import javax.annotation.Nullable;

public class ModTools {
    public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(ForgeRegistries.ITEMS, WH.MOD_ID);

    public static final RegistryObject<Item> WARPSTONE_SLUDGE_BUCKET = TOOLS.register("warpstone_sludge_bucket", 
        () -> new BucketItem(ModFluids.SOURCE_WARPSTONE_SLUDGE, new Item.Properties().tab(ModCreativeTab.FLUID_TAB).stacksTo(1).craftRemainder(Items.BUCKET)) {
            // Hover tooltip
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
                components.add(Component.literal("Uncommon").withStyle(_ModItemQualities.UNCOMMON));
                if (Screen.hasShiftDown()) {
                    components.add(Component.translatable("wh.info.warpstone_sludge_bucket").withStyle(ChatFormatting.WHITE));
                } else {
                    components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
                }
                super.appendHoverText(stack, level, components, flag);
            }
        });

    public static void register(IEventBus eventBus) {
        TOOLS.register(eventBus);
    }
}
