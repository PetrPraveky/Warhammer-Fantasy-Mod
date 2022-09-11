package net.pravekypetr.wh.event;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pravekypetr.wh.WH;
import net.pravekypetr.wh.itemInit.Dagger;
import net.pravekypetr.wh.itemInit.Spear;

@Mod.EventBusSubscriber(modid = WH.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void attackEntity(AttackEntityEvent event) {
        ItemStack heldItem = event.getEntity().getMainHandItem();
        if (heldItem.getItem() instanceof Spear || heldItem.getItem() instanceof Dagger) {
            event.setCanceled(true);
        }

    }
}
