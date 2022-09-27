package net.pravekypetr.wh.fluid;


import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.blocks.ModFluidBlocks;
import net.pravekypetr.wh.items.ModTools;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, WH.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_WARPSTONE_SLUDGE = FLUIDS.register("warpstone_sludge_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.SOAP_WATER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWIN_WARPSTONE_SLUDGE = FLUIDS.register("flowing_warpstone_sludge",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SOAP_WATER_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties SOAP_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.SOAP_WATER_FLUID_TYPE, SOURCE_WARPSTONE_SLUDGE, FLOWIN_WARPSTONE_SLUDGE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModFluidBlocks.LIQUID_WARPSTONE_SLUDGE_BLOCK)
            .bucket(ModTools.WARPSTONE_SLUDGE_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}