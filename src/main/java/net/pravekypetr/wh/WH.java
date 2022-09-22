package net.pravekypetr.wh;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pravekypetr.wh.attributes.ModWeaponAttribute;
import net.pravekypetr.wh.blocks.ModOreBlocks;
import net.pravekypetr.wh.blocks.ModStationBlocks;
import net.pravekypetr.wh.blocks.entities.SkavenBlockEntities;
import net.pravekypetr.wh.items.ModOreItems;
import net.pravekypetr.wh.items.ModWeapons;
import net.pravekypetr.wh.networking.ModMessages;

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

        // Initiate items
        ModOreItems.register(modEventBus);
        ModWeapons.register(modEventBus);

        // Initiate blocks
        ModOreBlocks.register(modEventBus);
        ModStationBlocks.register(modEventBus);

        // Initiate attributes
        ModWeaponAttribute.register(modEventBus);

        // Blockentities
        SkavenBlockEntities.register(modEventBus);

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

        }
    }
}
