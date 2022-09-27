package net.pravekypetr.wh._arch;

import com.mojang.math.Vector3f;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pravekypetr.wh.WH;

public class ModFluidTypes {
    public static final ResourceLocation WARPSTONE_SLUDGE_STILL_RL = new ResourceLocation(/*WH.MOD_ID, "block/liquid_warpstone_sludge_still" */ "block/water_still");
    public static final ResourceLocation WARPSTONE_SLUDGE_FLOWING_RL = new ResourceLocation(/*WH.MOD_ID, "block/liquid_warpstone_sludge_flow"*/ "block/water_flow");
    public static final ResourceLocation SOAP_OVERLAY_RL = new ResourceLocation(WH.MOD_ID, "misc/is_warpstone_sludge");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, WH.MOD_ID);

    public static final RegistryObject<FluidType> WARPSTONE_SLUDGE_FLUID_TYPE = register("warpstone_sludge_fluid", 
        FluidType.Properties.create()
        .canSwim(false)
        .canDrown(false)
        .pathType(BlockPathTypes.LAVA)
        .adjacentPathType(null)
        .lightLevel(8)
        .density(3000)
        .viscosity(3000)
        .temperature(1300)
    );


    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WARPSTONE_SLUDGE_STILL_RL, WARPSTONE_SLUDGE_FLOWING_RL, SOAP_OVERLAY_RL, 0xA1E038D0, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
