package net.pravekypetr.wh.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.fluid.ModFluids;

public class ModFluidBlocks {
    public static final DeferredRegister<Block> FLUIDBLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WH.MOD_ID);

    public static final RegistryObject<LiquidBlock> LIQUID_WARPSTONE_SLUDGE_BLOCK = FLUIDBLOCKS.register("warpstone_sludge_block", 
        () -> new LiquidBlock(ModFluids.SOURCE_WARPSTONE_SLUDGE, BlockBehaviour.Properties.of(Material.LAVA).noCollission().strength(100.0F).noLootTable()));

    public static final RegistryObject<LiquidBlock> LIQUID_WARPSTONE_BLOCK = FLUIDBLOCKS.register("liquid_warpstone_block", 
        () -> new LiquidBlock(ModFluids.SOURCE_WARPSTONE, BlockBehaviour.Properties.of(Material.LAVA).noCollission().strength(100.0F).noLootTable()));

    public static final RegistryObject<LiquidBlock> PURIFIED_LIQUID_WARPSTONE_BLOCK = FLUIDBLOCKS.register("purified_liquid_warpstone_block", 
        () -> new LiquidBlock(ModFluids.SOURCE_PURIFIED_WARPSTONE, BlockBehaviour.Properties.of(Material.LAVA).noCollission().strength(100.0F).noLootTable()));

    public static void register(IEventBus eventBus) {
        FLUIDBLOCKS.register(eventBus);
    }
}
