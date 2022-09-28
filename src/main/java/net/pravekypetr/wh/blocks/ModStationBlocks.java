package net.pravekypetr.wh.blocks;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh._ModItemQualities;
import net.pravekypetr.wh.blocks.stations.skavenBlastFurnace.SkavenBlastFurnaceBlock;
import net.pravekypetr.wh.blocks.stations.skavenCastingBarrel.SkavenCastingBarrelBlock;
import net.pravekypetr.wh.creativeTabs.ModCreativeTab;
import net.pravekypetr.wh.items.ModOreItems;

import javax.annotation.Nullable;

public class ModStationBlocks {
    public static final DeferredRegister<Block> STATIONBLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WH.MOD_ID);

    public static final RegistryObject<Block> SKAVEN_BLAST_FURNACE = registerBlock("skaven_blast_furnace",
    () -> new SkavenBlastFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
    .strength(2f).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion().lightLevel(state -> state.getValue(SkavenBlastFurnaceBlock.UNLIT) ? 0 : 15)),
    "wh.info.skaven.blastfurnace",
    ModCreativeTab.SKAVEN_TECHNOLOGY);

    public static final RegistryObject<Block> SKAVEN_CASTING_BARREL = registerBlock("skaven_casting_barrel",
    () -> new SkavenCastingBarrelBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
    .strength(2f).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion()),
    "wh.info.skaven.castingbarrel",
    ModCreativeTab.SKAVEN_TECHNOLOGY);

    

    // Register block
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, String tooltipKey, CreativeModeTab tab) {
        RegistryObject<T> toReturn = STATIONBLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tooltipKey, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, String tooltipKey) {
        RegistryObject<T> toReturn = STATIONBLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tooltipKey);
        return toReturn;
    }

    // Register item of block
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, String tooltipKey, CreativeModeTab tab) {
        return ModOreItems.OREITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)) {
            // Hover tooltip
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
                components.add(Component.literal("Uncomon").withStyle(_ModItemQualities.UNCOMMON));
                if (Screen.hasShiftDown()) {
                    components.add(Component.translatable(tooltipKey).withStyle(ChatFormatting.WHITE));
                } else {
                    components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
                }
                super.appendHoverText(stack, level, components, flag);
            }
        });
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, String tooltipKey) {
        return ModOreItems.OREITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()) {
            // Hover tooltip
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
                components.add(Component.literal("Uncomon").withStyle(_ModItemQualities.UNCOMMON));
                if (Screen.hasShiftDown()) {
                    components.add(Component.translatable(tooltipKey).withStyle(ChatFormatting.WHITE));
                } else {
                    components.add(Component.translatable("wh.info.description").withStyle(ChatFormatting.YELLOW));
                }
                super.appendHoverText(stack, level, components, flag);
            }
        });
    }


    public static void register(IEventBus eventBus) {
        STATIONBLOCKS.register(eventBus);
    }
}
