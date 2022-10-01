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

	// Warpstone sludge fluid
    public static final RegistryObject<FlowingFluid> SOURCE_WARPSTONE_SLUDGE = FLUIDS.register("warpstone_sludge_fluid",
	    () -> new ForgeFlowingFluid.Source(ModFluids.WARPSTONE_SLUDGE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWIN_WARPSTONE_SLUDGE = FLUIDS.register("flowing_warpstone_sludge",
	    () -> new ForgeFlowingFluid.Flowing(ModFluids.WARPSTONE_SLUDGE_PROPERTIES));

	// Warpstone fluid
    public static final RegistryObject<FlowingFluid> SOURCE_WARPSTONE = FLUIDS.register("warpstone_fluid",
	    () -> new ForgeFlowingFluid.Source(ModFluids.WARPSTONE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWIN_WARPSTONE = FLUIDS.register("flowing_sludge",
	    () -> new ForgeFlowingFluid.Flowing(ModFluids.WARPSTONE_PROPERTIES));

	// Purified Warpstone fluid
	public static final RegistryObject<FlowingFluid> SOURCE_PURIFIED_WARPSTONE = FLUIDS.register("purified_warpstone_fluid",
		() -> new ForgeFlowingFluid.Source(ModFluids.PURIFIED_WARPSTONE_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWIN_PURIFIED_WARPSTONE = FLUIDS.register("purified_flowing_sludge",
		() -> new ForgeFlowingFluid.Flowing(ModFluids.PURIFIED_WARPSTONE_PROPERTIES));
	


    public static final ForgeFlowingFluid.Properties WARPSTONE_SLUDGE_PROPERTIES = new ForgeFlowingFluid.Properties(
	ModFluidTypes.WARPSTONE_SLUDGE_FLUID_TYPE, SOURCE_WARPSTONE_SLUDGE, FLOWIN_WARPSTONE_SLUDGE)
	    .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModFluidBlocks.LIQUID_WARPSTONE_SLUDGE_BLOCK)
	    .bucket(ModTools.WARPSTONE_SLUDGE_BUCKET);

	public static final ForgeFlowingFluid.Properties WARPSTONE_PROPERTIES = new ForgeFlowingFluid.Properties(
	ModFluidTypes.WARPSTONE_FLUID_TYPE, SOURCE_WARPSTONE, FLOWIN_WARPSTONE)
	    .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModFluidBlocks.LIQUID_WARPSTONE_BLOCK)
	    .bucket(ModTools.WARPSTONE_BUCKET);

	public static final ForgeFlowingFluid.Properties PURIFIED_WARPSTONE_PROPERTIES = new ForgeFlowingFluid.Properties(
	ModFluidTypes.PURIFIED_WARPSTONE_FLUID_TYPE, SOURCE_PURIFIED_WARPSTONE, FLOWIN_PURIFIED_WARPSTONE)
	    .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModFluidBlocks.PURIFIED_LIQUID_WARPSTONE_BLOCK)
	    .bucket(ModTools.PURIFIED_WARPSTONE_BUCKET);

    public static void register(IEventBus eventBus) {
	FLUIDS.register(eventBus);
    }
}