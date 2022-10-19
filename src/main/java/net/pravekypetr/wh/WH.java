package net.pravekypetr.wh;

import com.mojang.logging.LogUtils;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pravekypetr.wh.attributes.ModWeaponAttribute;
import net.pravekypetr.wh.blocks.ModFluidBlocks;
import net.pravekypetr.wh.blocks.ModOreBlocks;
import net.pravekypetr.wh.blocks.ModStationBlocks;
import net.pravekypetr.wh.blocks.entities.SkavenBlockEntities;
import net.pravekypetr.wh.fluid.ModFluidTypes;
import net.pravekypetr.wh.fluid.ModFluids;
import net.pravekypetr.wh.items.ModItems;
import net.pravekypetr.wh.items.ModMetalItems;
import net.pravekypetr.wh.items.ModOreItems;
import net.pravekypetr.wh.items.ModTools;
import net.pravekypetr.wh.items.ModWeapons;
import net.pravekypetr.wh.networking.ModMessages;
import net.pravekypetr.wh.recipe.ModRecipes;
import net.pravekypetr.wh.screen.ModMenuTypes;
import net.pravekypetr.wh.screen.skavenBlastFurnace.SkavenBlastFurnaceScreen;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WH.MOD_ID)
public class WH
{
    public static final String MOD_ID = "wh";
    private static final Logger LOGGER = LogUtils.getLogger();

    public WH()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // ITEMS
        ModOreItems.register(modEventBus);
        ModMetalItems.register(modEventBus);
        ModWeapons.register(modEventBus);
        ModTools.register(modEventBus);
        ModItems.register(modEventBus);

        // BLOCKS
        ModOreBlocks.register(modEventBus);
        ModStationBlocks.register(modEventBus);
        ModFluidBlocks.register(modEventBus);

        // ATTRIBUTES
        ModWeaponAttribute.register(modEventBus);

        // BLOCKENTITIES
        SkavenBlockEntities.register(modEventBus);

        // MENUS
        ModMenuTypes.register(modEventBus);

        // RECIPES
        ModRecipes.register(modEventBus);

        // FLUID
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);


        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ModMessages.register();
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.SKAVEN_BLAST_FURNACE_MENU.get(), SkavenBlastFurnaceScreen::new);
            // ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_WARPSTONE_SLUDGE.get(), RenderType.translucent());
            // ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWIN_WARPSTONE_SLUDGE.get(), RenderType.translucent());
        }
    }
}
