
package net.pravekypetr.wh.fluid;

import com.mojang.math.Vector3f;
// import net.kaupenjoe.tutorialmod.TutorialMod;
import net.pravekypetr.wh.WH;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation(WH.MOD_ID, "block/liquid_warpstone_sludge_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation(WH.MOD_ID, "block/liquid_warpstone_sludge_flow");
    public static final ResourceLocation SOAP_OVERLAY_RL = new ResourceLocation(WH.MOD_ID, "misc/in_warpstone_sludge");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, WH.MOD_ID);

    public static final RegistryObject<FluidType> SOAP_WATER_FLUID_TYPE = register("warpstone_sludge_fluid",
            FluidType.Properties.create()
            .lightLevel(8)
            .pathType(BlockPathTypes.LAVA)
            .density(3000)
            .viscosity(3000)
            .temperature(1300)
    );



    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, SOAP_OVERLAY_RL,
                0xA109BD10, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}