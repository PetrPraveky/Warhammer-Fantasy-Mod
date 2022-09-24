package net.pravekypetr.wh.blocks;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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
import net.pravekypetr.wh.creativeTabs.ModCreativeTab;
import net.pravekypetr.wh.items.ModOreItems;

public class ModOreBlocks {
    public static final DeferredRegister<Block> OREBLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WH.MOD_ID);


    public static final RegistryObject<Block> WARPSTONE_BLOCK = registerBlock("warpstone_block", 
        () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).strength(10.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().emissiveRendering((state, getter, position) -> {
            return true;
        }).lightLevel(state -> 7)),
        ModCreativeTab.ORE_TAB
    );
    
    public static final RegistryObject<Block> WARPSTONE_ORE = registerBlock("warpstone_ore", 
        () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).strength(10.0f).sound(SoundType.STONE).requiresCorrectToolForDrops().emissiveRendering((state, getter, position) -> {
            return true;
        }).lightLevel(state -> 7)),
        ModCreativeTab.ORE_TAB
    );

    public static final RegistryObject<Block> DEEPSLATE_WARPSTONE_ORE = registerBlock("deepslate_warpstone_ore", 
        () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).strength(11.5f).sound(SoundType.STONE).requiresCorrectToolForDrops().emissiveRendering((state, getter, position) -> {
            return true;
        }).lightLevel(state -> 9)),
        ModCreativeTab.ORE_TAB
    );


    // Register block
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = OREBLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    // Register item of block
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModOreItems.OREITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        OREBLOCKS.register(eventBus);
    }
}
