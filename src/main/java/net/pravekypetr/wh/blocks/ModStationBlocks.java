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
import net.pravekypetr.wh.blocks.stations.skavenBlastFurnace.SkavenBlastFurnaceBlock;
import net.pravekypetr.wh.blocks.stations.skavenCastingBarrel.SkavenCastingBarrelBlock;
import net.pravekypetr.wh.creativeTabs.ModCreativeTab;
import net.pravekypetr.wh.items.ModOreItems;

public class ModStationBlocks {
    public static final DeferredRegister<Block> STATIONBLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WH.MOD_ID);

    public static final RegistryObject<Block> SKAVEN_BLAST_FURNACE = registerBlock("skaven_blast_furnace",
    () -> new SkavenBlastFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
    .strength(2f).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion().lightLevel(state -> state.getValue(SkavenBlastFurnaceBlock.UNLIT) ? 0 : 15)),
    ModCreativeTab.SKAVEN_TECHNOLOGY);

    public static final RegistryObject<Block> SKAVEN_CASTING_BARREL = registerBlock("skaven_casting_barrel",
    () -> new SkavenCastingBarrelBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
    .strength(2f).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion()),
    ModCreativeTab.SKAVEN_TECHNOLOGY);

    // public static final RegistryObject<Block> SKAVEN_BLAST_FURNACE_UPPER = registerBlock("skaven_blast_furnace_upper",
    // () -> new SkavenBlastFurnaceUpper(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(6f).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion()));

    // Register block
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = STATIONBLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = STATIONBLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Register item of block
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModOreItems.OREITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModOreItems.OREITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        STATIONBLOCKS.register(eventBus);
    }
}
