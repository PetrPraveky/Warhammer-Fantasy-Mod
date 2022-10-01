
package net.pravekypetr.wh.fluid;

import com.mojang.math.Vector3f;
// import net.kaupenjoe.tutorialmod.TutorialMod;
import net.pravekypetr.wh.WH;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    // Warpstone sludge fluid
    public static final ResourceLocation WARPSTONE_SLUDGE_STILL_RL = new ResourceLocation(WH.MOD_ID, "block/liquid_warpstone_sludge_still");
    public static final ResourceLocation WARPSTONE_SLDUGE_FLOWING_RL = new ResourceLocation(WH.MOD_ID, "block/liquid_warpstone_sludge_flow");
    public static final ResourceLocation SOAP_OVERLAY_RL = new ResourceLocation(WH.MOD_ID, "misc/in_warpstone_sludge");

    // Warpstone fluid
    public static final ResourceLocation WARPSTONE_STILL_RL = new ResourceLocation(WH.MOD_ID, "block/liquid_warpstone_still");
    public static final ResourceLocation WARPSTONE_FLOWING_RL = new ResourceLocation(WH.MOD_ID, "block/liquid_warpstone_flow");
    // Warpstone fluid
    public static final ResourceLocation PURIFIED_WARPSTONE_STILL_RL = new ResourceLocation(WH.MOD_ID, "block/purified_liquid_warpstone_still");
    public static final ResourceLocation PURIFIED_WARPSTONE_FLOWING_RL = new ResourceLocation(WH.MOD_ID, "block/purified_liquid_warpstone_flow");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, WH.MOD_ID);

    public static final RegistryObject<FluidType> WARPSTONE_SLUDGE_FLUID_TYPE = FLUID_TYPES.register(
        "warpstone_sludge_fluid", () -> new BaseFluidType(WARPSTONE_SLUDGE_STILL_RL, WARPSTONE_SLDUGE_FLOWING_RL, SOAP_OVERLAY_RL,
        0xA109BD10, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
            FluidType.Properties.create()
            .lightLevel(8)
            .pathType(BlockPathTypes.LAVA)
            .density(3000)
            .viscosity(3000)
            .temperature(1300)
    ));

    public static final RegistryObject<FluidType> WARPSTONE_FLUID_TYPE = FLUID_TYPES.register(
        "warpstone_fluid", () -> new BaseFluidType(WARPSTONE_STILL_RL, WARPSTONE_FLOWING_RL, SOAP_OVERLAY_RL,
        0xA109BD10, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
            FluidType.Properties.create()
            .lightLevel(15)
            .pathType(BlockPathTypes.LAVA)
            .density(3000)
            .viscosity(3000)
            .temperature(1300)
    ));

    public static final RegistryObject<FluidType> PURIFIED_WARPSTONE_FLUID_TYPE = FLUID_TYPES.register(
        "purified_warpstone_fluid", () -> new BaseFluidType(PURIFIED_WARPSTONE_STILL_RL, PURIFIED_WARPSTONE_FLOWING_RL, SOAP_OVERLAY_RL,
        0xA109BD10, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
            FluidType.Properties.create()
            .lightLevel(15)
            .pathType(BlockPathTypes.LAVA)
            .density(3000)
            .viscosity(3000)
            .temperature(1300)
    ));


    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WARPSTONE_SLUDGE_STILL_RL, WARPSTONE_SLDUGE_FLOWING_RL, SOAP_OVERLAY_RL,
                0xA109BD10, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}